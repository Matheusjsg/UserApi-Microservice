package com.Ecommerce.UserAPI.business.dto.Response;




public class UsuarioResponseDTO {

    private long id;
    private String nome;
    private String email;
    private String Role;
    private String verificationCode;

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(long id, String nome, String email, String role) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.Role = role;
    }

    public UsuarioResponseDTO(Long id, String nome, String email) {

    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}
