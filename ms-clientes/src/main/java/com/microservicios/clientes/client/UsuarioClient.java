package com.microservicios.clientes.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Cliente REST para comunicarse con MS-Usuarios.
 * Usa RestTemplate con balanceo de carga via Eureka.
 */
@Component
@RequiredArgsConstructor
public class UsuarioClient {

    private final RestTemplate restTemplate;

    // Nombre del servicio registrado en Eureka
    private static final String MS_USUARIOS_URL = "http://ms-usuarios";

    /**
     * Obtiene los datos de una persona desde MS-Usuarios
     * @param idPersona ID de la persona a consultar
     * @return Map con los datos de la persona (o null si no existe)
     */
    public Map getPersonaById(Integer idPersona) {
        try {
            return restTemplate.getForObject(
                MS_USUARIOS_URL + "/personas/" + idPersona,
                Map.class
            );
        } catch (Exception e) {
            // TODO: Maneja el error según tu lógica (lanzar excepción, retornar null, etc.)
            return null;
        }
    }
}
