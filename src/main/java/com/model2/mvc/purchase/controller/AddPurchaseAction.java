package com.model2.mvc.purchase.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.product.domain.Product;
import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.user.domain.User;

public class AddPurchaseAction extends PurchaseAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Purchase purchaseData = new Purchase().builder()
                                              .build();
        purchaseData.setBuyer(new User(request.getParameter("buyerId")));
        purchaseData.setPaymentOption(request.getParameter("paymentOption"));
        purchaseData.setReceiverName(request.getParameter("receiverName"));
        purchaseData.setReceiverPhone(request.getParameter("receiverPhone"));
        purchaseData.setDivyAddr(request.getParameter("receiverAddr"));
        purchaseData.setDivyRequest(request.getParameter("receiverRequest"));
        purchaseData.setDivyDate(request.getParameter("receiverDate"));
        purchaseData.setOrderDate(new Date(System.currentTimeMillis()));
        
        System.out.println("Purchase Data: " + purchaseData);
        
        super.purchaseService.addPurchase(purchaseData);
        
        System.out.println("Purchase done");
        
        request.setAttribute("purchaseData", purchaseData);
        
        return "forward:/purchase/addPurchaseResult.jsp";
    }

}
