package com.Ecommerce.UserAPI.business.dto.Response;


    public class AuthResponseDTO {

        private String token;
        private UsuarioResponseDTO usuarioResponseDTO;


        public AuthResponseDTO(String token, UsuarioResponseDTO usuarioResponseDTO) {
            this.token = token;
            this.usuarioResponseDTO = usuarioResponseDTO;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UsuarioResponseDTO getUsuarioResponseDTO() {
            return usuarioResponseDTO;
        }

        public void setUsuarioResponseDTO(UsuarioResponseDTO usuarioResponseDTO) {
            this.usuarioResponseDTO = usuarioResponseDTO;
        }
    }

