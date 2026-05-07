package com.microservicios.aplicaciones.service;

import com.microservicios.aplicaciones.entity.Aplicacion;
import com.microservicios.aplicaciones.repository.AplicacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AplicacionService {

    private final AplicacionRepository aplicacionRepository;

    public List<Aplicacion> findAll() {
        return aplicacionRepository.findAll();
    }

    public Optional<Aplicacion> findById(String id) {
        return aplicacionRepository.findById(id);
    }

    public Aplicacion save(Aplicacion aplicacion) {
        return aplicacionRepository.save(aplicacion);
    }

    public Optional<Aplicacion> update(String id, Aplicacion aplicacion) {
        return aplicacionRepository.findById(id).map(existing -> {
            aplicacion.setIdAplicacion(id);
            return aplicacionRepository.save(aplicacion);
        });
    }

    public boolean delete(String id) {
        return aplicacionRepository.findById(id).map(a -> {
            aplicacionRepository.delete(a);
            return true;
        }).orElse(false);
    }
}
