package com.waci.erp.shared.api;

import lombok.Data;

@Data
public class AuthDTO {
    private String username;
    private String password;
    private boolean rememberMe;


}
