package com.Ecommerce.UserAPI.business.dto.Request;


import jakarta.validation.constraints.NotEmpty;

public class UsuarioRequestDTO {

    private String nome;
    private String email;
    private String password;

    public UsuarioRequestDTO(@NotEmpty(message = "Nome é obrigatório") String nome,
                             @NotEmpty(message = "Email é obrigatório")String email,
                             @NotEmpty(message = "Senha é obrigatório")String password) {
        this.nome = nome;
        this.email = email;
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

