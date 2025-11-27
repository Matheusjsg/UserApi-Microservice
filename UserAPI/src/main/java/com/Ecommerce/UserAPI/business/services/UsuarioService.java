package com.Ecommerce.UserAPI.business.services;



import com.Ecommerce.UserAPI.Producers.UserProducer;
import com.Ecommerce.UserAPI.Util.RandomString;
import com.Ecommerce.UserAPI.business.dto.Request.UsuarioRequestDTO;
import com.Ecommerce.UserAPI.business.dto.Response.UsuarioResponseDTO;
import com.Ecommerce.UserAPI.business.mapstruct.UsuarioMapper;
import com.Ecommerce.UserAPI.infrastructure.Repositories.UserRepository;
import com.Ecommerce.UserAPI.infrastructure.entities.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private UserRepository userRepository;
    private UsuarioMapper mapper;
    private PasswordEncoder passwordEncoder;
    final UserProducer userProducer;

    public UsuarioService(UserRepository userRepository, UsuarioMapper mapper, PasswordEncoder passwordEncoder, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.userProducer = userProducer;
    }

    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO dto) {

        userRepository.findByEmail(dto.getEmail())

                .ifPresent(u -> {
                    throw new RuntimeException("Email já cadastrado!");
                });
        UserEntity usuario = new UserEntity();
        usuario.setEmail(dto.getEmail());
        usuario.setNome(dto.getNome());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));

        //confirmação da conta na criação
        String randomCode = RandomString.generenateRandomString(10);
        usuario.setVerificationCode(randomCode);
        usuario.setEnabled(false);


        usuario.setRole("USER");

        var novoUsuario = mapper.paraResponseDTO(userRepository.save(usuario));
        userProducer.publishMessageEmail(novoUsuario);

        return novoUsuario;

    }

    @Transactional
    public UsuarioResponseDTO salvaUsuario(UsuarioRequestDTO request) {

        var novoUsuario = mapper.paraResponseDTO(userRepository.save(mapper.paraUsuarioEntity(request)));
        userProducer.publishMessageEmail(novoUsuario);

        return novoUsuario;
    }

    public void deleteUsuarioPorNome(String nome) {
        userRepository.deleteByNome(nome);

    }

    public void deleteUsuarioPorEmail(String email) {
        userRepository.deleteByEmail(email);

    }

    public void deleteUsuarioPorId(Long id) {
        userRepository.deleteById(id);

    }

    public UsuarioResponseDTO buscarPorNome(String nome) {
        return mapper.paraResponseDTO(
                userRepository.findByNome(nome));

    }

    public UsuarioResponseDTO buscarPorEmail(String email) {

        UserEntity usuario = (UserEntity) userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return mapper.paraResponseDTO(usuario);
    }


    public List<UsuarioResponseDTO> MostrarUsuarios() {
        return mapper.paraListaUsuarioResponseDTO(
                userRepository.findAll());
    }


    public boolean verify(String code) {

        UserEntity userVerification = userRepository.findByVerificationCode(code);
        if (userVerification == null || userVerification.isVerified()) {
            return false;
        } else {
            userVerification.setVerificationCode(null);
            userVerification.setVerified(true);

            userRepository.save(userVerification);

            return true;
        }

    }

    public boolean emailExiste(String email) {
        return userRepository.existsByEmail(email);
    }
}

