package com.microservicios.gateway.controller;

import com.microservicios.gateway.client.UsuarioClient;
import com.microservicios.gateway.dto.AuthDto;
import com.microservicios.gateway.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioClient usuarioClient;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public Mono<ResponseEntity<Object>> login(@RequestBody AuthDto.LoginRequest loginRequest) {
        log.info("Intento de login para usuario: {}", loginRequest.getNombreUsuario());

        return usuarioClient.validarCredenciales(loginRequest)
                .map(usuarioData -> {
                    String nombreUsuario = (String) usuarioData.get("nombreUsuario");
                    String rol = (String) usuarioData.getOrDefault("rol", "USUARIO");

                    String token = jwtUtil.generateToken(nombreUsuario, java.util.List.of(rol));
                    log.info("Login exitoso para usuario: {} con rol: {}", nombreUsuario, rol);
                    return ResponseEntity.ok()
                            .<Object>body(new AuthDto.LoginResponse(token, nombreUsuario, rol));
                })
                .defaultIfEmpty(
                    ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .<Object>body(new AuthDto.ErrorResponse("Credenciales incorrectas", 401))
                )
                .onErrorReturn(
                    ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                            .<Object>body(new AuthDto.ErrorResponse("Servicio de usuarios no disponible", 503))
                );
    }

    @GetMapping("/validate")
    public Mono<ResponseEntity<Object>> validateToken(@RequestParam String token) {
        if (jwtUtil.isTokenValid(token)) {
            String username = jwtUtil.getUsernameFromToken(token);
            return Mono.just(ResponseEntity.ok()
                    .<Object>body(new AuthDto.ErrorResponse("Token válido para: " + username, 200)));
        }
        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .<Object>body(new AuthDto.ErrorResponse("Token inválido o expirado", 401)));
    }
}
