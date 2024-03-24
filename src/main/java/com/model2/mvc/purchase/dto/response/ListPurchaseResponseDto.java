package com.model2.mvc.purchase.dto.response;

import com.model2.mvc.common.Page;
import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class ListPurchaseResponseDto {
    private Page pageInfo;
    private int count;
    private List<Purchase> purchaseList;
    private User loginUser;

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }
}
