package com.model2.mvc.category.controller;

import com.model2.mvc.category.dto.request.UpdateCategoryRequestDTO;
import com.model2.mvc.category.service.CategoryService;
import com.model2.mvc.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/new-form")
    public String addCategoryView(@SessionAttribute("user") User loginUser) {
        checkAuthorization(loginUser);

        return "category/addCategoryView";
    }

    @PostMapping("/new")
    public String addCategory(@RequestParam("categoryName") String categoryName,
                              @SessionAttribute("user") User loginUser) {
        checkAuthorization(loginUser);

        this.categoryService.insertCategory(categoryName);
        return "redirect:/products";
    }

    @PostMapping("/update")
    public String updateCategory(@ModelAttribute("requestDTO") UpdateCategoryRequestDTO requestDTO,
                                 @SessionAttribute("user") User loginUser) {
        checkAuthorization(loginUser);

        this.categoryService.updateCategory(requestDTO);
        return "redirect:/listProduct.do";
    }

    private void checkAuthorization(User loginUser) {
        if (!loginUser.getRole().equals("admin")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
