package com.model2.mvc.purchase.controller;

import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.purchase.domain.TransactionProduction;
import com.model2.mvc.purchase.dto.request.AddPurchaseRequestDTO;
import com.model2.mvc.purchase.dto.response.AddPurchaseResponseDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddPurchaseAction extends PurchaseAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AddPurchaseRequestDTO requestDTO = new AddPurchaseRequestDTO().builder()
                .buyerId(request.getParameter("buyerId"))
                .paymentOption(request.getParameter("paymentOption"))
                .receiverName(request.getParameter("receiverName"))
                .receiverPhone(request.getParameter("receiverPhone"))
                .receiverName(request.getParameter("receiverName"))
                .divyAddr(request.getParameter("receiverAddr"))
                .divyRequest(request.getParameter("receiverRequest"))
                .divyDate(StringUtil.parseDate(request.getParameter("receiverDate"), "-"))
                .transactionProductions(TransactionProduction.from(request.getParameterValues("tranProds")))
                .build();

        System.out.println("Purchase Data: " + requestDTO);

        AddPurchaseResponseDTO purchaseData = super.purchaseService.addPurchase(requestDTO);

        System.out.println("Purchase done");

        request.setAttribute("purchaseData", purchaseData);

        return "forward:/purchase/addPurchaseResult.jsp";
    }

}
