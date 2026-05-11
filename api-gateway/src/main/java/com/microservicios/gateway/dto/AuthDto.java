package com.microservicios.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AuthDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequest {
        private String nombreUsuario;
        private String password;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginResponse {
        private String token;
        private String tipo = "Bearer";
        private String nombreUsuario;
        private String rol;
        private String mensaje;

        public LoginResponse(String token, String nombreUsuario, String rol) {
            this.token = token;
            this.nombreUsuario = nombreUsuario;
            this.rol = rol;
            this.mensaje = "Login exitoso";
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorResponse {
        private String mensaje;
        private int status;
    }
}
