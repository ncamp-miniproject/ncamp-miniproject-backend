package com.model2.mvc.product.controller;

import com.model2.mvc.category.domain.Category;
import com.model2.mvc.common.SearchCondition;
import com.model2.mvc.common.propertyeditor.SearchConditionEditor;
import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.product.controller.editor.CategoryNoEditor;
import com.model2.mvc.product.dto.request.AddProductRequestDto;
import com.model2.mvc.product.dto.request.ListProductRequestDto;
import com.model2.mvc.product.dto.request.UpdateProductRequestDto;
import com.model2.mvc.product.dto.response.GetProductResponseDto;
import com.model2.mvc.product.dto.response.ListProductResponseDto;
import com.model2.mvc.product.service.ProductService;
import com.model2.mvc.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.ServletContext;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/products")
public class ProductApi {
    private final ProductService productService;
    private final ServletContext servletContext;

    @Value("#{constantProperties['defaultPageSize']}")
    private int defaultPageSize;

    @InitBinder
    public void bindParameters(WebDataBinder binder) {
        binder.registerCustomEditor(SearchCondition.class, SearchConditionEditor.getInstance());
        binder.registerCustomEditor(Integer.class, "categoryNo", CategoryNoEditor.getInstance());
    }

    @PostMapping("/new")
    public ResponseEntity<Void> addProduct(@ModelAttribute AddProductRequestDto productDto) {
        this.productService.addProduct(productDto, this.servletContext.getRealPath("/images/uploadFiles"));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/products");
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping("/{prodNo}")
    public ResponseEntity<GetProductResponseDto> getProduct(@PathVariable("prodNo") int prodNo,
                                                            @RequestParam(value = "menu", required = false) String menu,
                                                            @CookieValue(value = "history",
                                                                         required = false) String history,
                                                            @SessionAttribute(value = "user",
                                                                              required = false) User loginUser) {
        String updatedHistory = StringUtil.addValueWithoutDuplicate(history, String.valueOf(prodNo), "-");

        GetProductResponseDto result = this.productService.getProduct(prodNo, loginUser);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Set-Cookie", "history=" + updatedHistory);
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ListProductResponseDto> listProduct(
            @ModelAttribute("requestDTO") ListProductRequestDto requestDTO) {
        requestDTO.setPageSize(requestDTO.getPageSize() == null ? defaultPageSize : requestDTO.getPageSize());

        ListProductResponseDto responseDTO = this.productService.getProductList(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateProduct(@RequestBody UpdateProductRequestDto requestDTO) {
        this.productService.updateProduct(requestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategoryList() {
        // TODO: This end point should be placed in api of controller
        List<Category> categories = this.productService.getCategoryList();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
