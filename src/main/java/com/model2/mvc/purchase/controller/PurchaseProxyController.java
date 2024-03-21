package com.model2.mvc.purchase.controller;

import com.model2.mvc.common.SearchCondition;
import com.model2.mvc.common.propertyeditor.SearchConditionEditor;
import com.model2.mvc.purchase.domain.PaymentOption;
import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.purchase.domain.TranStatusCode;
import com.model2.mvc.purchase.dto.request.AddPurchaseRequestDTO;
import com.model2.mvc.purchase.dto.request.AddPurchaseViewResponseDTO;
import com.model2.mvc.purchase.dto.request.ListPurchaseRequestDTO;
import com.model2.mvc.purchase.dto.request.UpdatePurchaseRequestDTO;
import com.model2.mvc.purchase.dto.request.UpdateTranCodeRequestDTO;
import com.model2.mvc.purchase.dto.response.AddPurchaseResponseDTO;
import com.model2.mvc.purchase.dto.response.GetPurchaseResponseDTO;
import com.model2.mvc.purchase.dto.response.ListPurchaseResponseDTO;
import com.model2.mvc.purchase.service.PurchaseService;
import com.model2.mvc.purchase.util.LocalDateEditor;
import com.model2.mvc.purchase.util.PaymentOptionEditor;
import com.model2.mvc.purchase.util.TranStatusCodeEditor;
import com.model2.mvc.purchase.util.TransactionProductionEditor;
import com.model2.mvc.user.domain.Role;
import com.model2.mvc.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/purchases")
public class PurchaseProxyController {

    private final PurchaseService purchaseService;


    @Value("#{constantProperties['defaultPageSize']}")
    private int defaultPageSize;

    @Value("#{constantProperties['defaultPageDisplay']}")
    private int defaultPageDisplay;

    @Autowired
    public PurchaseProxyController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

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
    public String addPurchase(@ModelAttribute("requestDTO") AddPurchaseRequestDTO requestDTO, Model model) {
        AddPurchaseResponseDTO responseDTO = this.purchaseService.addPurchase(requestDTO);
        model.addAttribute("purchaseData", responseDTO);
        return "purchase/purchase-result";
    }

    @GetMapping("/new-form")
    public ModelAndView getPurchaseView(@RequestParam("purchase") List<String> purchase,
                                        @SessionAttribute("user") User loginUser) {
        if (purchase == null || purchase.isEmpty()) {
            return new ModelAndView("redirect:/products");
        }
        Map<Integer, Integer> prodNoQuantityMap = new HashMap<>();

        purchase.stream()
                .map(p -> p.split(("-")))
                .forEach(m -> prodNoQuantityMap.put(Integer.parseInt(m[0]), Integer.parseInt(m[1])));
        AddPurchaseViewResponseDTO responseDTO = this.purchaseService.getProductsWithQuantity(prodNoQuantityMap);

        ModelAndView mv = new ModelAndView("purchase/purchase-form");
        mv.addObject("data", responseDTO);
        mv.addObject("loginUser", loginUser);
        return mv;
    }

    @GetMapping("/sale")
    public ModelAndView listSale(@RequestParam(value = "menu", required = false) String menu,
                                 @RequestParam(value = "page", required = false) Integer page,
                                 @SessionAttribute("user") User loginUser) {
        int currentPage = page == null ? 1 : page;
        if ((menu == null || menu.equals("search")) || loginUser.getRole() != Role.SELLER) {
            return new ModelAndView("redirect:/products?menu=search&page=" + currentPage);
        }

        ListPurchaseResponseDTO responseDTO = this.purchaseService.getSaleList(currentPage, defaultPageSize);

        responseDTO.setLoginUser(loginUser);
        ModelAndView mv = new ModelAndView("purchase/purchase-list");
        mv.addObject("data", responseDTO);
        return mv;
    }

    @GetMapping("/{tranNo}")
    public String getPurchase(@PathVariable("tranNo") int tranNo, Model model) {
        GetPurchaseResponseDTO responseDTO = this.purchaseService.getPurchase(tranNo);
        model.addAttribute("purchaseData", responseDTO);
        return "purchase/purchase-info";
    }

    @GetMapping
    public ModelAndView listPurchase(@ModelAttribute("requestDTO") ListPurchaseRequestDTO requestDTO,
                                     @RequestParam(value = "menu", required = false) String menu,
                                     @SessionAttribute("user") User loginUser) {
        if ((menu != null && menu.equals("manage")) || loginUser.getRole() == Role.SELLER) {
            return new ModelAndView("redirect:/purchases/sale?menu=manage&page=1");
        }

        System.out.println(loginUser);
        ListPurchaseResponseDTO result = this.purchaseService.getPurchaseList(requestDTO, loginUser.getUserId());

        ModelAndView mv = new ModelAndView("purchase/purchase-list");
        result.setLoginUser(loginUser);
        mv.addObject("data", result);
        return mv;
    }

    @GetMapping("/{tranNo}/update-form")
    public String updatePurchaseView(@PathVariable("tranNo") int tranNo,
                                     Model model,
                                     @SessionAttribute("user") User loginUser) {
        GetPurchaseResponseDTO toUpdate = this.purchaseService.getPurchase(tranNo);
        model.addAttribute("purchaseData", toUpdate);
        model.addAttribute("loginUser", loginUser);
        return "purchase/updatePurchaseView";
    }

    @PostMapping("/update")
    public String updatePurchase(@ModelAttribute("requestDTO") UpdatePurchaseRequestDTO requestDTO, Model model) {
        Purchase result = this.purchaseService.updatePurchase(requestDTO);

        model.addAttribute("purchaseData", result);
        return "purchase/updatePurchaseResult";
    }

    @PostMapping("/tran-code/update")
    public String updateTranCode(@RequestParam("tranNo") int tranNo, @RequestParam("tranCode") String tranCode) {
        this.purchaseService.updateTranCode(new UpdateTranCodeRequestDTO(tranNo, TranStatusCode.getTranCode(tranCode)));
        return "redirect:/purchases";
    }

    @RequestMapping("/updateTranCodeByProd.do")
    public String updateTranCodeByProd() {
        throw new UnsupportedOperationException();
    }
}
