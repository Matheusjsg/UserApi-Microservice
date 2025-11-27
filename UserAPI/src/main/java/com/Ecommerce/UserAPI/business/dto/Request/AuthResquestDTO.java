package com.Ecommerce.UserAPI.business.dto.Request;


import jakarta.validation.constraints.NotEmpty;

public class AuthResquestDTO {

    private String email;
    private String password;

    public AuthResquestDTO(@NotEmpty (message = "Email Obrigatório") String email, @NotEmpty (message = "Senha Obrigatória")String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
