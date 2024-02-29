package com.model2.mvc.user.domain;

import java.sql.Date;

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

    public User(String userId,
                String userName,
                String password,
                String role,
                String ssn,
                String phone,
                String addr,
                String email,
                Date regDate) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.ssn = ssn;
        this.phone = phone;
        this.addr = addr;
        this.email = email;
        this.regDate = regDate;
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
