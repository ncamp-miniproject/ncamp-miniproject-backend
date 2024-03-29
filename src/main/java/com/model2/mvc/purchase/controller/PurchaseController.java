package com.model2.mvc.purchase.controller;

import com.model2.mvc.common.SearchCondition;
import com.model2.mvc.common.propertyeditor.SearchConditionEditor;
import com.model2.mvc.common.util.WebUtil;
import com.model2.mvc.purchase.controller.editor.LocalDateEditor;
import com.model2.mvc.purchase.controller.editor.PaymentOptionEditor;
import com.model2.mvc.purchase.controller.editor.TranStatusCodeEditor;
import com.model2.mvc.purchase.controller.editor.TransactionProductionEditor;
import com.model2.mvc.purchase.domain.PaymentOption;
import com.model2.mvc.purchase.domain.TranStatusCode;
import com.model2.mvc.purchase.dto.request.AddPurchaseRequestDTO;
import com.model2.mvc.purchase.dto.request.ListPurchaseRequestDto;
import com.model2.mvc.purchase.dto.request.UpdatePurchaseRequestDto;
import com.model2.mvc.purchase.dto.response.AddPurchaseResponseDTO;
import com.model2.mvc.purchase.dto.response.GetPurchaseResponseDto;
import com.model2.mvc.purchase.dto.response.ListPurchaseResponseDto;
import com.model2.mvc.purchase.service.PurchaseService;
import com.model2.mvc.user.domain.Role;
import com.model2.mvc.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;

    @InitBinder
    public void dateBinding(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, LocalDateEditor.getInstance());
        binder.registerCustomEditor(List.class, "tranProds", TransactionProductionEditor.getInstance());
        binder.registerCustomEditor(PaymentOption.class, "paymentOption", PaymentOptionEditor.getInstance());
        binder.registerCustomEditor(TranStatusCode.class, "tranStatusCode", TranStatusCodeEditor.getInstance());
        binder.registerCustomEditor(SearchCondition.class, SearchConditionEditor.getInstance());
    }

    @ModelAttribute("paymentOptions")
    public List<PaymentOption> paymentOptions() {
        return Arrays.asList(PaymentOption.values());
    }

    @PostMapping("/new")
    public String addPurchase(@ModelAttribute("requestDTO") AddPurchaseRequestDTO requestDTO, Model model)
    throws URISyntaxException {
        URI uri = new URI("http", null, "localhost", 8089, "/api/purchases", null, null);
        RequestEntity<AddPurchaseRequestDTO> requestEntity = RequestEntity.post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestDTO);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<AddPurchaseResponseDTO> responseEntity = restTemplate.exchange(requestEntity,
                                                                                          AddPurchaseResponseDTO.class);
            model.addAttribute("purchaseData", responseEntity.getBody());
            return "purchase/purchase-result";
        } catch (HttpClientErrorException.Forbidden e) {
            e.printStackTrace();
            // TODO: do some logic
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    @GetMapping("/new-form")
    public String getPurchaseView() {
        return "purchase/purchase-form";
    }

    @GetMapping("/sale")
    public ModelAndView listSale(@RequestParam(value = "menu", required = false) String menu,
                                 @RequestParam(value = "page", required = false) Integer page,
                                 @SessionAttribute("user") User loginUser) throws URISyntaxException {
        int currentPage = page == null ? 1 : page;
        if ((menu == null || menu.equals("search")) || loginUser.getRole() != Role.ADMIN) {
            return new ModelAndView("redirect:/products?menu=search&page=" + currentPage);
        }

        URIBuilder uriBuilder = new URIBuilder().setScheme("http")
                .setHost("localhost")
                .setPort(8089)
                .setPath("/api/purchases/sale");
        if (page != null) {
            uriBuilder = uriBuilder.setParameter("page", String.valueOf(page));
        }
        RequestEntity<Void> requestEntity = RequestEntity.get(uriBuilder.build())
                .accept(MediaType.APPLICATION_JSON)
                .build();

        ModelAndView mv = new ModelAndView("purchase/purchase-list");
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ListPurchaseResponseDto> responseEntity = restTemplate.exchange(requestEntity,
                                                                                           ListPurchaseResponseDto.class);
            ListPurchaseResponseDto result = responseEntity.getBody();
            mv.addObject("data", result);
        } catch (HttpClientErrorException.Forbidden e) {
            e.printStackTrace();
            return new ModelAndView("redirect:/products?menu=search&page=" + currentPage);
        }
        return mv;
    }

    @GetMapping("/{tranNo}")
    public String getPurchase(@PathVariable("tranNo") int tranNo, Model model) throws URISyntaxException {
        model.addAttribute("purchaseData", this.purchaseService.getPurchase(tranNo));
        return "purchase/purchase-info";
    }

    @GetMapping
    public ModelAndView listPurchase(@ModelAttribute("requestDTO") ListPurchaseRequestDto requestDTO,
                                     @RequestParam(value = "menu", required = false) String menu,
                                     @SessionAttribute("user") User loginUser) {
        if ((menu != null && menu.equals("manage")) || loginUser.getRole() == Role.ADMIN) {
            return new ModelAndView("redirect:/purchases/sale?menu=manage&page=1");
        }

        ModelAndView mv = new ModelAndView("purchase/purchase-list");
        mv.addObject("loginUser", loginUser);
        mv.addObject("menu", menu);
        return mv;
    }

    @GetMapping("/{tranNo}/update-form")
    public String updatePurchaseView(@PathVariable("tranNo") int tranNo,
                                     Model model,
                                     @SessionAttribute("user") User loginUser) {
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(8089)
                .path("/api/purchases/" + tranNo)
                .build()
                .toUri();
        RequestEntity<Void> requestEntity = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GetPurchaseResponseDto> responseEntity = restTemplate.exchange(requestEntity,
                                                                                      GetPurchaseResponseDto.class);

        model.addAttribute("purchaseData", responseEntity.getBody());
        model.addAttribute("loginUser", loginUser);
        return "purchase/purchase-update-form";
    }

    @PostMapping("/{tranNo}/update")
    public String updatePurchase(@PathVariable("tranNo") int tranNo,
                                 @ModelAttribute("requestDTO") UpdatePurchaseRequestDto requestDTO,
                                 Model model) {
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port("8089")
                .path("/api/purchases/" + tranNo)
                .build()
                .toUri();
        RequestEntity<UpdatePurchaseRequestDto> requestEntity = RequestEntity.patch(uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestDTO);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(requestEntity, Void.class);

        model.addAttribute("purchaseData", requestDTO);
        return "purchase/purchase-update-result";
    }

    @RequestMapping("/updateTranCodeByProd.do")
    public String updateTranCodeByProd() {
        throw new UnsupportedOperationException();
    }
}
