package com.model2.mvc.user.domain;

import java.sql.Date;
import java.util.Objects;

public class User {
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

    public User(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User)o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "User{" +
               "userId='" +
               userId +
               '\'' +
               ", userName='" +
               userName +
               '\'' +
               ", password='" +
               password +
               '\'' +
               ", role='" +
               role +
               '\'' +
               ", ssn='" +
               ssn +
               '\'' +
               ", phone='" +
               phone +
               '\'' +
               ", addr='" +
               addr +
               '\'' +
               ", email='" +
               email +
               '\'' +
               ", regDate=" +
               regDate +
               '}';
    }
}
