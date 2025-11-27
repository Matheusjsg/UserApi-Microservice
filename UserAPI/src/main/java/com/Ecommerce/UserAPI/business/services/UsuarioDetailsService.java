package com.Ecommerce.UserAPI.business.services;

import com.Ecommerce.UserAPI.infrastructure.Repositories.UserRepository;
import com.Ecommerce.UserAPI.infrastructure.entities.UserEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UserRepository usuarioRepository;

    public UsuarioDetailsService(UserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        UserEntity usuario = (UserEntity) usuarioRepository.findByEmail(userEmail)
                .orElseThrow(()-> new UsernameNotFoundException("E-mail não encontrado"));

        if (!usuario.isVerified()){
            throw new DisabledException("A conta ainda não foi verificada.");
        }

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .roles(usuario.getRole())
                .build();
    }
}
