package com.model2.mvc.product.dto.request;

import com.model2.mvc.common.Buildable;
import com.model2.mvc.common.BuilderTemplate;
import com.model2.mvc.common.dto.Search;
import com.model2.mvc.user.domain.User;

public class ListProductRequestDTO implements Buildable<ListProductRequestDTO.Builder> {
    private int page;
    private String menuMode;
    private User loginUser;
    private Search search;

    public ListProductRequestDTO() {
    }

    private ListProductRequestDTO(Builder from) {
        this.page = from.page;
        this.menuMode = from.menuMode;
        this.loginUser = from.loginUser;
        this.search = from.search;
    }

    @Override
    public Builder builder() {
        return new Builder(this);
    }

    public int getPage() {
        return page;
    }

    public String getMenuMode() {
        return menuMode;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public Search getSearch() {
        return search;
    }

    public static class Builder extends BuilderTemplate<ListProductRequestDTO> {
        private int page;
        private String menuMode;
        private User loginUser;
        private Search search;

        public Builder(ListProductRequestDTO from) {
            this.page = from.page;
            this.menuMode = from.menuMode;
            this.loginUser = from.loginUser;
            this.search = from.search;
        }

        public Builder page(int p) {
            return super.setField(b -> b.page = p);
        }

        public Builder menuMode(String m) {
            return super.setField(b -> b.menuMode = m);
        }

        public Builder loginUser(User u) {
            return super.setField(b -> b.loginUser = u);
        }

        public Builder search(Search s) {
            return super.setField(b -> b.search = s);
        }

        @Override
        public ListProductRequestDTO build() {
            return new ListProductRequestDTO(this);
        }
    }
}
