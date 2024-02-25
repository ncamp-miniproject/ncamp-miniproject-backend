package com.model2.mvc.user.domain;

import com.model2.mvc.common.Buildable;
import com.model2.mvc.common.BuilderTemplate;

import java.sql.Date;

public class User implements Buildable<User.Builder> {
    private String userId;
    private String userName;
    private String password;
    private String role;
    private String ssn;
    private String phone;
    private String addr;
    private String email;
    private Date regDate;

    public User() {
    }

    private User(Builder from) {
        this.userId = from.userId;
        this.userName = from.userName;
        this.password = from.password;
        this.role = from.role;
        this.ssn = from.ssn;
        this.phone = from.phone;
        this.addr = from.addr;
        this.email = from.email;
        this.regDate = from.regDate;
    }

    @Override
    public Builder builder() {
        return new Builder(this);
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getSsn() {
        return ssn;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddr() {
        return addr;
    }

    public String getEmail() {
        return email;
    }

    public Date getRegDate() {
        return regDate;
    }

    public static class Builder extends BuilderTemplate<User> {
        private String userId;
        private String userName;
        private String password;
        private String role;
        private String ssn;
        private String phone;
        private String addr;
        private String email;
        private Date regDate;

        private Builder(User from) {
            this.userId = from.userId;
            this.userName = from.userName;
            this.password = from.password;
            this.role = from.role;
            this.ssn = from.ssn;
            this.phone = from.phone;
            this.addr = from.addr;
            this.email = from.email;
            this.regDate = from.regDate;
        }

        public User build() {
            return new User(this);
        }

        public Builder userId(String u) {
            return setField(b -> b.userId = u);
        }

        public Builder userName(String u) {
            return setField(b -> b.userName = u);
        }

        public Builder password(String p) {
            return setField(b -> b.password = p);
        }

        public Builder role(String r) {
            return setField(b -> b.role = r);
        }

        public Builder ssn(String s) {
            return setField(b -> b.ssn = s);
        }

        public Builder phone(String p) {
            return setField(b -> b.phone = p);
        }

        public Builder addr(String a) {
            return setField(b -> b.addr = a);
        }

        public Builder email(String e) {
            return setField(b -> b.email = e);
        }

        public Builder regDate(Date r) {
            return setField(b -> b.regDate = r);
        }
    }
}
