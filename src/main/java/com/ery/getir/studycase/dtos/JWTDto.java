package com.ery.getir.studycase.dtos;

import javax.validation.constraints.NotBlank;

public class JWTDto {

    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "username is required")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
