package com.microservicios.gateway.client;

import com.microservicios.gateway.dto.AuthDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class UsuarioClient {

    private final WebClient.Builder webClientBuilder;
    private final PasswordEncoder passwordEncoder;

    @Value("${services.ms-usuarios.url}")
    private String msUsuariosUrl;

    /**
     * Consulta al MS-Usuarios si existe el usuario con ese nombre.
     * Retorna el usuario si existe, vacío si no.
     */
    public Mono<Map> findByNombreUsuario(String nombreUsuario) {
        return webClientBuilder.build()
                .get()
                .uri(msUsuariosUrl + "/usuarios/buscar/{nombreUsuario}", nombreUsuario)
                .retrieve()
                .bodyToMono(Map.class)
                .onErrorResume(e -> {
                    log.error("Error consultando MS-Usuarios: {}", e.getMessage());
                    return Mono.empty();
                });
    }

    public Mono<Map> validarCredenciales(AuthDto.LoginRequest loginRequest) {
        return findByNombreUsuario(loginRequest.getNombreUsuario())
                .flatMap(usuario -> {
                    String passwordBD = (String) usuario.get("password");
                    String nombreUsuario = (String) usuario.get("nombreUsuario");

                    if (passwordBD != null && passwordEncoder.matches(loginRequest.getPassword(), passwordBD)) {
                        return Mono.just(usuario);
                    }
                    return Mono.empty();
                });
    }
}
