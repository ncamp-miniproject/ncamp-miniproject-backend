package com.model2.mvc.purchase.dto.response;

import com.model2.mvc.common.dto.Page;
import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.user.domain.User;

import java.util.List;

public class ListPurchaseResponseDTO {
    private Page pageInfo;
    private int count;
    private List<Purchase> purchaseList;
    private User loginUser;


}
