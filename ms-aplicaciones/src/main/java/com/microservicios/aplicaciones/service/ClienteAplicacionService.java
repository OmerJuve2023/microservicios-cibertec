package com.microservicios.aplicaciones.service;

import com.microservicios.aplicaciones.entity.ClienteAplicacion;
import com.microservicios.aplicaciones.repository.ClienteAplicacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteAplicacionService {

    private final ClienteAplicacionRepository clienteAplicacionRepository;

    public List<ClienteAplicacion> findAll() {
        return clienteAplicacionRepository.findAll();
    }

    public Optional<ClienteAplicacion> findById(Integer id) {
        return clienteAplicacionRepository.findById(id);
    }

    public List<ClienteAplicacion> findByCliente(Integer idCliente) {
        return clienteAplicacionRepository.findByIdCliente(idCliente);
    }

    public List<ClienteAplicacion> findByAplicacion(String idAplicacion) {
        return clienteAplicacionRepository.findByIdAplicacion(idAplicacion);
    }

    public ClienteAplicacion save(ClienteAplicacion clienteAplicacion) {
        return clienteAplicacionRepository.save(clienteAplicacion);
    }

    public Optional<ClienteAplicacion> update(Integer id, ClienteAplicacion clienteAplicacion) {
        return clienteAplicacionRepository.findById(id).map(existing -> {
            clienteAplicacion.setId(id);
            return clienteAplicacionRepository.save(clienteAplicacion);
        });
    }

    public boolean delete(Integer id) {
        return clienteAplicacionRepository.findById(id).map(ca -> {
            clienteAplicacionRepository.delete(ca);
            return true;
        }).orElse(false);
    }
}
