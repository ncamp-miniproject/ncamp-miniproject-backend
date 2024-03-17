package com.model2.mvc.product.controller;

import com.model2.mvc.common.propertyeditor.SearchConditionEditor;
import com.model2.mvc.product.controller.editor.CategoryNoEditor;
import com.model2.mvc.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/products")
public class ProductRestController {
    private final ProductService productService;

    @InitBinder
    public void bindParameters(WebDataBinder binder) {
        binder.registerCustomEditor(SearchConditionEditor.class, SearchConditionEditor.getInstance());
        binder.registerCustomEditor(Integer.class, "categoryNo", CategoryNoEditor.getInstance());
    }
}
