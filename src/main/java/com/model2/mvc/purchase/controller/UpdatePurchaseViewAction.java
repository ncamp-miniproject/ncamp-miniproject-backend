package com.model2.mvc.purchase.controller;

import com.model2.mvc.framework.Action;
import com.model2.mvc.purchase.dto.response.GetPurchaseResponseDTO;
import com.model2.mvc.purchase.service.PurchaseService;
import com.model2.mvc.user.domain.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component("updatePurchaseViewAction")
public class UpdatePurchaseViewAction extends Action {
    private PurchaseService purchaseService;

    public UpdatePurchaseViewAction(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int tranNo = Integer.parseInt(request.getParameter("tranNo"));

        HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("user");

        GetPurchaseResponseDTO toUpdate = this.purchaseService.getPurchase(tranNo);

        request.setAttribute("purchaseData", toUpdate);
        request.setAttribute("loginUser", loginUser);
        return "forward:/purchase/updatePurchaseView.jsp";
    }
}
