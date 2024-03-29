package com.model2.mvc.product.controller;

import com.model2.mvc.common.SearchCondition;
import com.model2.mvc.common.propertyeditor.SearchConditionEditor;
import com.model2.mvc.common.util.BeanUtil;
import com.model2.mvc.product.controller.editor.CategoryNoEditor;
import com.model2.mvc.product.dto.request.CreateProductFormRequestDto;
import com.model2.mvc.product.dto.request.CreateProductRequestDto;
import com.model2.mvc.product.dto.request.UpdateProductRequestDto;
import com.model2.mvc.product.dto.response.GetProductResponseDto;
import com.model2.mvc.purchase.controller.editor.LocalDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @InitBinder
    public void bindParameters(WebDataBinder binder) {
        binder.registerCustomEditor(SearchCondition.class, SearchConditionEditor.getInstance());
        binder.registerCustomEditor(Integer.class, "categoryNo", CategoryNoEditor.getInstance());
        binder.registerCustomEditor(LocalDate.class, LocalDateEditor.getInstance());
    }

    @GetMapping("/add-form")
    public ModelAndView addProductView() throws URISyntaxException {
        RequestEntity<Void> requestEntity = RequestEntity.get(new URI("http",
                                                                      null,
                                                                      "localhost",
                                                                      8089,
                                                                      "/api/categories",
                                                                      null,
                                                                      null)).accept(MediaType.APPLICATION_JSON).build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> result = restTemplate.exchange(requestEntity, List.class);

        ModelAndView mv = new ModelAndView("product/product-register");
        mv.addObject("categoryList", result.getBody());
        mv.addObject("productDTO", new CreateProductFormRequestDto());
        return mv;
    }

    @PostMapping
    public String addProduct(@ModelAttribute CreateProductFormRequestDto requestDto)
    throws URISyntaxException, InstantiationException, IllegalAccessException {
        URI uri = new URI("http", null, "localhost", 8089, "/api/products", null, null);
        // TODO: check if using BeanUtil.doMapping method doesn't make any problem
        CreateProductRequestDto requestEntityForApi = BeanUtil.doMapping(CreateProductRequestDto.class, requestDto);
        MultipartFile imageMultipart = requestDto.getImageFile();
        if (imageMultipart != null) {
            try {
                requestEntityForApi.setImageName(imageMultipart.getOriginalFilename());
                requestEntityForApi.setBase64ImageData(Base64.getEncoder().encodeToString(imageMultipart.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        RequestEntity<CreateProductRequestDto> requestEntity = RequestEntity.post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestEntityForApi);

        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.exchange(requestEntity, Void.class);
            return "redirect:/";
        } catch (HttpClientErrorException.NotFound e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    @GetMapping("/{prodNo}")
    public String getProduct(@PathVariable("prodNo") int prodNo,
                             @RequestParam(value = "menu", required = false) String menu,
                             @CookieValue(value = "history", required = false) String history,
                             @CookieValue(value = "JSESSIONID", required = false) String jSessionId,
                             Model model) throws URISyntaxException {
        if (menu != null && menu.equals("manage")) {
            return String.format("redirect:/products/%d/update-form", prodNo);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Cookie",
                    String.format("%s%s",
                                  history == null ? "" : "history=" + history + ";",
                                  jSessionId == null ? "" : "JSESSIONID=" + jSessionId));
        URI uri = new URI("http", null, "localhost", 8089, "/api/products/" + prodNo, "menu=" + menu, null);
        RequestEntity<Void> requestEntity = RequestEntity.get(uri).headers(headers).build();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GetProductResponseDto> result = restTemplate.exchange(requestEntity,
                                                                             GetProductResponseDto.class);
        model.addAttribute("productData", result.getBody());
        model.addAttribute("cookie", new String[] { "history", result.getHeaders().get("Set-Cookie").get(0) });
        return "product/product-info";
    }

    @GetMapping
    public String listProduct(@RequestParam("menu") String menu, Model model)
    throws URISyntaxException {
        model.addAttribute("menuMode", menu);
        return "product/product-list";
    }

    @PostMapping("/{prodNo}/update")
    public String updateProduct(@PathVariable("prodNo") int prodNo,
                                @ModelAttribute("requestDTO") UpdateProductRequestDto requestDTO)
    throws URISyntaxException {
        // TODO:
        // 1. Have to get binary data from client for image
        // 2. Have to load the image binary data into object (to be converted into JSON)
        // 3. Send the JSON formatted data in API controller

        URI uri = new URI("http", null, "localhost", 8089, "/api/products/" + prodNo, null, null);
        RequestEntity<UpdateProductRequestDto> requestEntity = RequestEntity.patch(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(requestDTO);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(requestEntity, Void.class);
        return "redirect:/products";
    }

    @GetMapping("/{prodNo}/update-form")
    public String updateProductView(@PathVariable("prodNo") int prodNo, Model model) {
        model.addAttribute("prodNo", prodNo);
        return "product/product-update";
    }
}
