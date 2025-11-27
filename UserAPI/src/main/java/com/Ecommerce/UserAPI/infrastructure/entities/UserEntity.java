package com.Ecommerce.UserAPI.infrastructure.entities;


import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name= "tb_users")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "senha")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "verification_Code")
    private String verificationCode;

    @Column (name = "Verified")
    private boolean verified;

    public UserEntity(Long id, String nome, String email, String password, String role, String verificationCode, boolean verified) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.role = role;
        this.verificationCode = verificationCode;
        this.verified = verified;
    }

    public UserEntity() {

    }

    public UserEntity(String nome, String mail, String number, String user) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setEmail(String email){
        this.email = email;}

    public void setPassword(String password){
        this.password = password;}

    public String getRole(){
        return role;}

    public void setRole(String role){
        this.role = role;}

    public void setEnabled(boolean enabled) {
        this.verified = enabled;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword(){
        return password;}

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}


