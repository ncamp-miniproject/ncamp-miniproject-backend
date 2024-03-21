package com.model2.mvc.product.controller;

import com.model2.mvc.common.SearchCondition;
import com.model2.mvc.common.propertyeditor.SearchConditionEditor;
import com.model2.mvc.product.controller.editor.CategoryNoEditor;
import com.model2.mvc.product.dto.request.AddProductRequestDTO;
import com.model2.mvc.product.dto.request.ListProductRequestDTO;
import com.model2.mvc.product.dto.request.UpdateProductRequestDTO;
import com.model2.mvc.product.dto.response.GetProductResponseDTO;
import com.model2.mvc.product.dto.response.ListProductResponseDTO;
import org.apache.http.client.utils.URIBuilder;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductProxyController {

    @InitBinder
    public void bindParameters(WebDataBinder binder) {
        binder.registerCustomEditor(SearchCondition.class, SearchConditionEditor.getInstance());
        binder.registerCustomEditor(Integer.class, "categoryNo", CategoryNoEditor.getInstance());
    }

    @GetMapping("/add-form")
    public ModelAndView addProductView() throws URISyntaxException {
        RequestEntity<Void> requestEntity = RequestEntity.get(new URI("http",
                                                                      null,
                                                                      "localhost",
                                                                      8089,
                                                                      "/app/products/categories",
                                                                      null,
                                                                      null)).accept(MediaType.APPLICATION_JSON).build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> result = restTemplate.exchange(requestEntity, List.class);

        ModelAndView mv = new ModelAndView("product/product-register");
        mv.addObject("categoryList", result.getBody());
        mv.addObject("productDTO", new AddProductRequestDTO());
        return mv;
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
        RequestEntity<Void> requestEntity = RequestEntity.get(new URI("http",
                                                                      null,
                                                                      "localhost",
                                                                      8089,
                                                                      "/app/products/" + prodNo,
                                                                      "menu=" + menu,
                                                                      null)).headers(headers).build();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GetProductResponseDTO> result = restTemplate.exchange(requestEntity,
                                                                             GetProductResponseDTO.class);
        model.addAttribute("productData", result.getBody());
        model.addAttribute("cookie", new String[] { "history", result.getHeaders().get("Set-Cookie").get(0) });
        return "product/product-info";
    }

    @GetMapping("")
    public String listProduct(@ModelAttribute("requestDTO") ListProductRequestDTO requestDTO, Model model)
    throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder().setScheme("http")
                .setHost("localhost")
                .setPort(8089)
                .setPath("/app/products");
        if (requestDTO.getPage() != null) {
            uriBuilder.addParameter("page", requestDTO.getPage().toString());
        }
        if (requestDTO.getPageSize() != null) {
            uriBuilder.addParameter("pageSize", requestDTO.getPageSize().toString());
        }
        if (requestDTO.getSearchKeyword() != null) {
            uriBuilder.addParameter("searchKeyword", requestDTO.getSearchKeyword());
        }
        if (requestDTO.getSearchCondition() != null) {
            uriBuilder.addParameter("searchCondition", requestDTO.getSearchCondition().getConditionCode());
        }
        if (requestDTO.getMenu() != null) {
            uriBuilder.addParameter("menu", requestDTO.getMenu());
        }
        if (requestDTO.getCategoryNo() != null) {
            uriBuilder.addParameter("categoryNo", requestDTO.getCategoryNo().toString());
        }
        URI uri = uriBuilder.build();

        RequestEntity<Void> requestEntity = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ListProductResponseDTO> result = restTemplate.exchange(requestEntity,
                                                                              ListProductResponseDTO.class);
        model.addAttribute("data", result.getBody());
        return "product/product-list";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("requestDTO") UpdateProductRequestDTO requestDTO)
    throws URISyntaxException {
        // TODO:
        // 1. Have to get binary data from client for image
        // 2. Have to load the image binary data into object (to be converted into JSON)
        // 3. Send the JSON formatted data in API controller
        RequestEntity<UpdateProductRequestDTO> requestEntity = RequestEntity.post(new URI("http",
                                                                                          null,
                                                                                          "localhost",
                                                                                          8089,
                                                                                          "/app/products/update",
                                                                                          null,
                                                                                          null))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(requestDTO);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(requestEntity, Void.class);
        return "redirect:/products";
    }

    @GetMapping("/{prodNo}/update-form")
    public String updateProductView(@PathVariable("prodNo") int prodNo, Model model) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        RequestEntity<Void> requestEntity = RequestEntity.get(new URI("http",
                                                                      null,
                                                                      "localhost",
                                                                      8089,
                                                                      "/app/products/" + prodNo,
                                                                      null,
                                                                      null)).headers(headers).build();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GetProductResponseDTO> result = restTemplate.exchange(requestEntity,
                                                                             GetProductResponseDTO.class);
        model.addAttribute("data", result.getBody());
        return "product/product-update";
    }
}
