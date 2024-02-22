package com.model2.mvc.purchase.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.user.domain.User;

public class UpdatePurchaseAction extends PurchaseAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int tranNo = Integer.parseInt(request.getParameter("tranNo"));
        String buyerId = request.getParameter("buyerId");
        String paymentOption = request.getParameter("paymentOption");
        String receiverName = request.getParameter("receiverName");
        String receiverPhone = request.getParameter("receiverPhone");
        String divyAddr = request.getParameter("receiverAddr");
        String divyRequest = request.getParameter("receiverRequest");
        String divyDate = request.getParameter("divyDate");
        
        Purchase updateInfo = new Purchase();
        updateInfo.setTranNo(tranNo);
        updateInfo.setDivyAddr(divyAddr);
        updateInfo.setDivyRequest(divyRequest);
        updateInfo.setOrderDate(new Date(System.currentTimeMillis()));
        updateInfo.setPaymentOption(paymentOption);
        updateInfo.setReceiverName(receiverName);
        updateInfo.setReceiverPhone(receiverPhone);
        updateInfo.setBuyer(new User(buyerId));
        updateInfo.setDivyDate(divyDate);
        
        super.purchaseService.updatePurchase(updateInfo);
        
        Purchase result = super.purchaseService.getPurchase(tranNo);
        
        request.setAttribute("purchaseData", result);
        return "forward:/purchase/updatePurchaseResult.jsp"; // addPurchaseResult jsp
    }
}
