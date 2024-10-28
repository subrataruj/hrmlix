package com.example.hrmlix.models;

public class LoginRequest {
    private String company_id;
    private String password;
    private String device_id;

    public LoginRequest(String company_id, String password, String device_id) {
        this.company_id = company_id;
        this.password = password;
        this.device_id = device_id;

    }
    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
