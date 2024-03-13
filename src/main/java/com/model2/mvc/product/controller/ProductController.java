package com.model2.mvc.product.controller;

import com.model2.mvc.category.domain.Category;
import com.model2.mvc.common.SearchCondition;
import com.model2.mvc.common.propertyeditor.SearchConditionEditor;
import com.model2.mvc.common.util.RandomSerialGenerator;
import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.product.controller.editor.CategoryNoEditor;
import com.model2.mvc.product.dto.request.AddProductRequestDTO;
import com.model2.mvc.product.dto.request.ListProductRequestDTO;
import com.model2.mvc.product.dto.request.UpdateProductRequestDTO;
import com.model2.mvc.product.dto.response.GetProductResponseDTO;
import com.model2.mvc.product.dto.response.ListProductResponseDTO;
import com.model2.mvc.product.service.ProductService;
import com.model2.mvc.user.domain.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private final SearchConditionEditor searchConditionEditor;
    private final CategoryNoEditor categoryNoEditor;

    @Value("#{constantProperties['defaultPageSize']}")
    private int defaultPageSize;

    @Autowired
    public ProductController(ProductService productService,
                             SearchConditionEditor searchConditionEditor,
                             CategoryNoEditor categoryNoEditor) {
        this.productService = productService;
        this.searchConditionEditor = searchConditionEditor;
        this.categoryNoEditor = categoryNoEditor;
    }

    @InitBinder
    public void bindParameters(WebDataBinder binder) {
        binder.registerCustomEditor(SearchCondition.class, this.searchConditionEditor);
        binder.registerCustomEditor(Integer.class, "categoryNo", this.categoryNoEditor);
    }

    @PostMapping("/new")
    public String addProduct(HttpServletRequest request) {
        AddProductRequestDTO dto = new AddProductRequestDTO();

        ServletContext servletContext = request.getServletContext();
        File repository = new File(servletContext.getRealPath("/images/uploadFiles"));

        final int megabyte = 1024 * 1024;

        DiskFileItemFactory factory = new DiskFileItemFactory(megabyte * 10, repository);

        ServletFileUpload fileUpload = new ServletFileUpload(factory);

        Map<String, String> formData = new HashMap<>();
        String fileName = null;
        try {
            List<FileItem> fileItems = fileUpload.parseRequest(request);
            for (FileItem fileItem : fileItems) {
                if (fileItem.isFormField()) {
                    formData.put(fileItem.getFieldName(), fileItem.getString("EUC-KR"));
                } else {
                    try {
                        String fileItemName = fileItem.getName();
                        int idx = fileItemName.lastIndexOf(".");
                        fileName = RandomSerialGenerator.generate() + fileItemName.substring(idx);
                        File imageFile = new File(repository, fileName);
                        fileItem.write(imageFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileUploadException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        dto.setFileName(fileName);
        dto.setManuDate(formData.get("manuDate"));
        dto.setPrice(Integer.parseInt(formData.get("price")));
        dto.setProdDetail(formData.get("prodDetail"));
        dto.setProdName(formData.get("prodName"));
        dto.setStock(Integer.parseInt(formData.get("stock")));
        dto.setCategoryNo(Integer.parseInt(formData.get("categoryNo")));
        this.productService.addProduct(dto);
        return "redirect:/products";
    }

    @GetMapping("/add-form")
    public ModelAndView addProductView() {
        List<Category> categoryList = this.productService.getCategoryList();
        ModelAndView mv = new ModelAndView("product/addProductView");
        mv.addObject("categoryList", categoryList);
        return mv;
    }

    @GetMapping("/{prodNo}")
    public String getProduct(@PathVariable("prodNo") int prodNo,
                             @RequestParam(value = "menu", required = false) String menu,
                             @CookieValue(value = "history", required = false) String history,
                             @SessionAttribute(value = "user", required = false) User loginUser,
                             Model model) {
        if (menu != null && menu.equals("manage")) {
            return String.format("redirect:/products/%d/update-form", prodNo);
        }

        if (history == null) {
            history = "";
        }
        String updatedHistory = history.isEmpty()
                                ? String.valueOf(prodNo)
                                : StringUtil.addValueWithoutDuplicate(history, String.valueOf(prodNo), "-");

        model.addAttribute("cookie", new String[] { "history", updatedHistory });

        GetProductResponseDTO result = this.productService.getProduct(prodNo);

        if (loginUser != null) {
            result.setPurchasable(loginUser.getRole().equals("user") && result.getStock() > 0);
        } else {
            result.setPurchasable(false);
        }
        model.addAttribute("productData", result);
        return "product/getProduct";
    }

    @GetMapping("")
    public String listProduct(@ModelAttribute("requestDTO") ListProductRequestDTO requestDTO,
                              @SessionAttribute(value = "user", required = false) User loginUser,
                              Model model) {
        String menu = requestDTO.getMenu();
        if (menu == null || ((menu.equals("manage") && (loginUser == null || !loginUser.getRole().equals("admin"))))) {
            return "redirect:/products?menu=search";
        }

        requestDTO.setPageSize(defaultPageSize);

        ListProductResponseDTO responseDTO = this.productService.getProductList(requestDTO);

        model.addAttribute("data", responseDTO);
        return "product/listProduct";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("requestDTO") UpdateProductRequestDTO requestDTO) {
        this.productService.updateProduct(requestDTO);
        return "redirect:/products";
    }

    @GetMapping("/{prodNo}/update-form")
    public String updateProductView(@PathVariable("prodNo") int prodNo, Model model) {
        GetProductResponseDTO responseDTO = this.productService.getProduct(prodNo);
        model.addAttribute("data", responseDTO);
        return "product/updateProduct";
    }
}
