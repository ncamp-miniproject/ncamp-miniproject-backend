package com.model2.mvc.product.controller;

import com.model2.mvc.common.CommonConstants;
import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.product.dto.request.AddProductRequestDTO;
import com.model2.mvc.product.dto.request.ListProductRequestDTO;
import com.model2.mvc.product.dto.request.UpdateProductRequestDTO;
import com.model2.mvc.product.dto.response.GetProductResponseDTO;
import com.model2.mvc.product.dto.response.ListProductResponseDTO;
import com.model2.mvc.product.service.ProductService;
import com.model2.mvc.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/addProduct.do")
    public String addProduct(@ModelAttribute("addProductDTO") AddProductRequestDTO addProductDTO) {
        this.productService.addProduct(addProductDTO);
        return "redirect:/listProduct.do";
    }

    @RequestMapping("/getProduct.do")
    public String getProduct(@RequestParam("prodNo") int prodNo,
                             @RequestParam(value = "menu", required = false) String menu,
                             @CookieValue(value = "history", required = false) String history,
                             @SessionAttribute(value = "user", required = false) User loginUser,
                             Model model) {
        if (menu != null && menu.equals("manage")) {
            return "redirect:/updateProductView.do?prodNo=" + prodNo;
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
        return "/product/getProduct.jsp";
    }

    @RequestMapping("/listProduct.do")
    public String listProduct(@ModelAttribute("requestDTO") ListProductRequestDTO requestDTO,
                              @SessionAttribute(value = "user", required = false) User loginUser,
                              Model model) {
        String menu = requestDTO.getMenu();
        if (menu == null || ((menu.equals("manage") && (loginUser == null || !loginUser.getRole().equals("admin"))))) {
            return "redirect:/listProduct.do?menu=search";
        }

        requestDTO.setPageSize(CommonConstants.PAGE_SIZE);

        ListProductResponseDTO responseDTO = this.productService.getProductList(requestDTO);

        model.addAttribute("data", responseDTO);
        return "/product/listProduct.jsp";
    }

    @RequestMapping("/updateProduct.do")
    public String updateProduct(@ModelAttribute("requestDTO") UpdateProductRequestDTO requestDTO) {
        this.productService.updateProduct(requestDTO);
        return "redirect:/listProduct.do";
    }

    @RequestMapping("/updateProductView.do")
    public String updateProductView(@RequestParam("prodNo") int prodNo, Model model) {
        GetProductResponseDTO responseDTO = this.productService.getProduct(prodNo);
        model.addAttribute("data", responseDTO);
        return "/product/updateProduct.jsp";
    }
}
