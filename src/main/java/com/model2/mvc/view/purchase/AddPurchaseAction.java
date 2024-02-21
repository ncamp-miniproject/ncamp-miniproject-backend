package com.model2.mvc.view.purchase;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.service.product.domain.Product;
import com.model2.mvc.service.purchase.domain.Purchase;
import com.model2.mvc.service.user.domain.User;

public class AddPurchaseAction extends PurchaseAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Purchase purchaseData = new Purchase();
        purchaseData.setPurchaseProd(Product.builder().prodNo(Integer.parseInt(request.getParameter("prodNo"))).build());
        purchaseData.setBuyer(new User(request.getParameter("buyerId")));
        purchaseData.setPaymentOption(request.getParameter("paymentOption"));
        purchaseData.setReceiverName(request.getParameter("receiverName"));
        purchaseData.setReceiverPhone(request.getParameter("receiverPhone"));
        purchaseData.setDivyAddr(request.getParameter("receiverAddr"));
        purchaseData.setDivyRequest(request.getParameter("receiverRequest"));
        purchaseData.setDivyDate(request.getParameter("receiverDate"));
        purchaseData.setOrderDate(new Date(System.currentTimeMillis()));
        
        super.purchaseService.addPurchase(purchaseData);
        
        request.setAttribute("purchaseData", purchaseData);
        
        return "forward:/purchase/addPurchaseResult.jsp";
    }

}
