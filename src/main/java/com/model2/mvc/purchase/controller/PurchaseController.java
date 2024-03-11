package com.model2.mvc.purchase.controller;

import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.purchase.domain.TranStatusCode;
import com.model2.mvc.purchase.domain.TransactionProduction;
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
import com.model2.mvc.purchase.util.TransactionProductionEditor;
import com.model2.mvc.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PurchaseController {

    private PurchaseService purchaseService;

    private LocalDateEditor localDateEditor;
    private TransactionProductionEditor tranProdEditor;

    @Value("#{constantProperties['defaultPageSize']}")
    private int defaultPageSize;

    @Value("#{constantProperties['defaultPageDisplay']}")
    private int defaultPageDisplay;

    @Autowired
    public PurchaseController(PurchaseService purchaseService,
                              LocalDateEditor localDateEditor,
                              TransactionProductionEditor tranProdEditor) {
        this.purchaseService = purchaseService;
        this.localDateEditor = localDateEditor;
        this.tranProdEditor = tranProdEditor;
    }

    @InitBinder
    public void dateBinding(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, "divyDate", this.localDateEditor);
        binder.registerCustomEditor(TransactionProduction.class, "transactionProductions", this.tranProdEditor);
    }

    @RequestMapping("/addPurchase.do")
    public String addPurchase(@ModelAttribute("requestDTO") AddPurchaseRequestDTO requestDTO, Model model) {
        AddPurchaseResponseDTO responseDTO = this.purchaseService.addPurchase(requestDTO);
        model.addAttribute("purchaseData", responseDTO);
        return "/purchase/addPurchaseResult.jsp";
    }

    @RequestMapping("/addPurchaseView.do")
    public ModelAndView getPurchaseView(@RequestParam("purchase") List<String> purchase,
                                        @SessionAttribute("user") User loginUser) {
        if (purchase == null || purchase.isEmpty()) {
            return new ModelAndView("redirect:/listProduct.do");
        }
        Map<Integer, Integer> prodNoQuantityMap = new HashMap<>();

        purchase.stream()
                .map(p -> p.split(("-")))
                .forEach(m -> prodNoQuantityMap.put(Integer.parseInt(m[0]), Integer.parseInt(m[1])));
        AddPurchaseViewResponseDTO responseDTO = this.purchaseService.getProductsWithQuantity(prodNoQuantityMap);

        ModelAndView mv = new ModelAndView("/purchase/addPurchaseView.jsp");
        mv.addObject("data", responseDTO);
        mv.addObject("loginUser", loginUser);
        return mv;
    }

    @RequestMapping("/listSale.do")
    public ModelAndView listSale(@RequestParam("menu") String menu,
                                 @RequestParam("page") int page,
                                 @SessionAttribute("user") User loginUser) {
        int currentPage = page == 0 ? 1 : page;
        if ((menu == null || menu.equals("search")) || !loginUser.getRole().equals("admin")) {
            return new ModelAndView("redirect:/listPurchase.do?menu=search&page=" + currentPage);
        }

        ListPurchaseResponseDTO responseDTO = this.purchaseService.getSaleList(currentPage, defaultPageSize);

        ModelAndView mv = new ModelAndView("/purchase/listPurchase.jsp");
        mv.addObject("data", responseDTO.builder().loginUser(loginUser).build());
        return mv;
    }

    @RequestMapping("/getPurchase.do")
    public String getPurchase(@RequestParam("tranNo") int tranNo, Model model) {
        GetPurchaseResponseDTO responseDTO = this.purchaseService.getPurchase(tranNo);
        model.addAttribute("purchaseData", responseDTO);
        return "/purchase/getPurchase.jsp";
    }

    @RequestMapping("/listPurchase.do")
    public ModelAndView listPurchase(@RequestParam("page") int page,
                                     @RequestParam("menu") String menu,
                                     @RequestParam("searchCondition") String searchCondition,
                                     @RequestParam("searchKeyword") String searchKeyword,
                                     @SessionAttribute("user") User loginUser) {
        int currentPage = page == 0 ? 1 : page;
        if ((menu != null && menu.equals("manage")) || loginUser.getRole().equals("admin")) {
            return new ModelAndView("redirect:/listSale.do?menu=manage&page=" + page);
        }

        ListPurchaseRequestDTO requestDTO = new ListPurchaseRequestDTO(currentPage,
                                                                       defaultPageSize,
                                                                       loginUser.getUserId());
        requestDTO.setSearchCondition(StringUtil.null2nullStr(searchCondition));
        requestDTO.setSearchKeyword(StringUtil.null2nullStr(searchKeyword));

        ListPurchaseResponseDTO result = this.purchaseService.getPurchaseList(requestDTO);

        ModelAndView mv = new ModelAndView("/purchase/listPurchase.jsp");
        mv.addObject("data", result.builder().loginUser(loginUser).build());
        return mv;
    }

    @RequestMapping("/updatePurchaseView.do")
    public String updatePurchaseView(@RequestParam("tranNo") int tranNo,
                                     Model model,
                                     @SessionAttribute("user") User loginUser) {
        GetPurchaseResponseDTO toUpdate = this.purchaseService.getPurchase(tranNo);
        model.addAttribute("purchaseData", toUpdate);
        model.addAttribute("loginUser", loginUser);
        return "/purchase/updatePurchaseView.jsp";
    }

    @RequestMapping("/updatePurchase.do")
    public String updatePurchase(@ModelAttribute("requestDTO") UpdatePurchaseRequestDTO requestDTO, Model model) {
        Purchase result = this.purchaseService.updatePurchase(requestDTO);

        model.addAttribute("purchaseData", result);
        return "/purchase/updatePurchaseResult.jsp";
    }

    @RequestMapping("/updateTranCode.do")
    public String updateTranCode(@RequestParam("tranNo") int tranNo, @RequestParam("tranCode") String tranCode) {
        this.purchaseService.updateTranCode(new UpdateTranCodeRequestDTO(tranNo, TranStatusCode.getTranCode(tranCode)));
        return "redirect:/listPurchase.do";
    }

    @RequestMapping("/updateTranCodeByProd.do")
    public String updateTranCodeByProd() {
        throw new UnsupportedOperationException();
    }
}
