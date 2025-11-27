package com.Ecommerce.UserAPI.controllers;



import com.Ecommerce.UserAPI.business.dto.Request.AuthResquestDTO;
import com.Ecommerce.UserAPI.business.dto.Request.UsuarioRequestDTO;
import com.Ecommerce.UserAPI.business.dto.Response.UsuarioResponseDTO;
import com.Ecommerce.UserAPI.business.mapstruct.UsuarioMapper;
import com.Ecommerce.UserAPI.business.services.UsuarioService;
import com.Ecommerce.UserAPI.infrastructure.Repositories.UserRepository;
import com.Ecommerce.UserAPI.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UsuarioService usuarioService;
    private UserRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;
    private UsuarioMapper mapper;
    private AuthenticationManager authenticationManager;

    public AuthController(UsuarioService usuarioService, AuthenticationManager authenticationManager, UserRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, UsuarioMapper mapper) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@Valid @RequestBody UsuarioRequestDTO dto) {
    UsuarioResponseDTO usuario = usuarioService.criarUsuario(dto);

    {
        return ResponseEntity.ok(usuario);
    }
}

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid  @RequestBody AuthResquestDTO request){

        UsernamePasswordAuthenticationToken UserAndPass = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(UserAndPass);


        //exibindo token
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(user.getUsername());
        return  ResponseEntity.ok(Map.of("token", token));

    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable String email) {
        usuarioService.deleteUsuarioPorEmail(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verification")
    public ResponseEntity<?> verificar(@RequestBody Map<String, String> request) {

        String verificationCode = request.get("code");

        boolean verified = usuarioService.verify(verificationCode);

        if (verified) {
            return ResponseEntity.ok(
                    Map.of("message", "Conta verificada com sucesso!")
            );
        }
        return ResponseEntity.badRequest().body(
                Map.of("message", "Código inválido ou conta já verificada.")
        );
    }


    @GetMapping("/buscarEmail")
    public ResponseEntity<Boolean> buscarEmail(@RequestParam String email) {
        boolean existe = usuarioService.emailExiste(email);
        return ResponseEntity.ok(existe);
    }

}
