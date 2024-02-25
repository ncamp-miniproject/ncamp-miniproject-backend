package com.model2.mvc.purchase.dto.response;

import com.model2.mvc.common.Buildable;
import com.model2.mvc.common.BuilderTemplate;
import com.model2.mvc.common.dto.Page;
import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.user.domain.User;

import java.util.List;

public class ListPurchaseResponseDTO implements Buildable<ListPurchaseResponseDTO.Builder> {
    private Page pageInfo;
    private int count;
    private List<Purchase> purchaseList;
    private User loginUser;

    public ListPurchaseResponseDTO() {
    }

    private ListPurchaseResponseDTO(ListPurchaseResponseDTO.Builder from) {
        this.pageInfo = from.pageInfo;
        this.count = from.count;
        this.purchaseList = from.purchaseList;
        this.loginUser = from.loginUser;
    }

    @Override
    public Builder builder() {
        return new Builder(this);
    }

    public Page getPageInfo() {
        return pageInfo;
    }

    public int getCount() {
        return count;
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public User getLoginUser() {
        return loginUser;
    }

    @Override
    public String toString() {
        return "ListPurchaseResponseDTO{" +
               "pageInfo=" +
               pageInfo +
               ", count=" +
               count +
               ", purchaseList=" +
               purchaseList +
               ", loginUser=" +
               loginUser +
               '}';
    }

    public static class Builder extends BuilderTemplate<ListPurchaseResponseDTO> {
        private Page pageInfo;
        private int count;
        private List<Purchase> purchaseList;
        private User loginUser;

        public Builder(ListPurchaseResponseDTO from) {
            this.pageInfo = from.pageInfo;
            this.count = from.count;
            this.purchaseList = from.purchaseList;
            this.loginUser = from.loginUser;
        }

        public Builder pageInfo(Page p) {
            return setField(i -> i.pageInfo = p);
        }

        public Builder count(int c) {
            return setField(i -> i.count = c);
        }

        public Builder purchaseList(List<Purchase> p) {
            return setField(i -> i.purchaseList = p);
        }

        public Builder loginUser(User u) {
            return setField(i -> i.loginUser = u);
        }

        @Override
        public ListPurchaseResponseDTO build() {
            return new ListPurchaseResponseDTO(this);
        }
    }
}
