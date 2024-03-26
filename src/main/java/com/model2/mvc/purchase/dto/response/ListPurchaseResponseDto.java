package com.model2.mvc.purchase.dto.response;

import com.model2.mvc.common.Page;
import com.model2.mvc.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class ListPurchaseResponseDto {
    private Page pageInfo;
    private int count;
    private List<GetPurchaseResponseDto> purchaseList;
    private User loginUser;
    private String menu;

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}
