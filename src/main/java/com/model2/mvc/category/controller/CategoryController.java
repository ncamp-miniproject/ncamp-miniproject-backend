package com.model2.mvc.category.controller;

import com.model2.mvc.user.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @GetMapping("/new-form")
    public String addCategoryView(@SessionAttribute("user") User loginUser) {
        return "category/addCategoryView";
    }
}
