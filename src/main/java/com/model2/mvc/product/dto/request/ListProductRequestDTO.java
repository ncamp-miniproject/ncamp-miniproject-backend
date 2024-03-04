package com.model2.mvc.product.dto.request;

import com.model2.mvc.common.Buildable;
import com.model2.mvc.common.BuilderTemplate;
import com.model2.mvc.common.dto.Search;
import com.model2.mvc.user.domain.User;

public class ListProductRequestDTO implements Buildable<ListProductRequestDTO.Builder> {
    private int page;
    private int pageSize;
    private String searchKeyword;
    private String searchCondition;
    private String menuMode;
    private User loginUser;

    public ListProductRequestDTO() {
    }

    private ListProductRequestDTO(Builder from) {
        this.page = from.page;
        this.pageSize = from.pageSize;
        this.searchKeyword = from.searchKeyword;
        this.searchCondition = from.searchCondition;
        this.menuMode = from.menuMode;
        this.loginUser = from.loginUser;
    }

    @Override
    public Builder builder() {
        return new Builder(this);
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public String getSearchCondition() {
        return searchCondition;
    }

    public String getMenuMode() {
        return menuMode;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public static class Builder extends BuilderTemplate<ListProductRequestDTO> {
        private int page;
        private int pageSize;
        private String searchKeyword;
        private String searchCondition;
        private String menuMode;
        private User loginUser;

        public Builder(ListProductRequestDTO from) {
            this.page = from.page;
            this.pageSize = from.pageSize;
            this.searchKeyword = from.searchKeyword;
            this.searchCondition = from.searchCondition;
            this.menuMode = from.menuMode;
            this.loginUser = from.loginUser;
        }

        public Builder page(int p) {
            return super.setField(b -> b.page = p);
        }

        public Builder pageSize(int p) {
            return super.setField(b -> b.pageSize = p);
        }

        public Builder searchKeyword(String s) {
            return super.setField(b -> b.searchKeyword = s);
        }

        public Builder searchCondition(String s) {
            return super.setField(b -> b.searchCondition = s);
        }

        public Builder menuMode(String m) {
            return super.setField(b -> b.menuMode = m);
        }

        public Builder loginUser(User u) {
            return super.setField(b -> b.loginUser = u);
        }

        @Override
        public ListProductRequestDTO build() {
            return new ListProductRequestDTO(this);
        }
    }
}
