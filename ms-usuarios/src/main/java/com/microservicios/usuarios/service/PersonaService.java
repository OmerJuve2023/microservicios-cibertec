package com.microservicios.usuarios.service;

import com.microservicios.usuarios.entity.Persona;
import com.microservicios.usuarios.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaService {

    private final PersonaRepository personaRepository;

    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    public Optional<Persona> findById(Integer id) {
        return personaRepository.findById(id);
    }

    public Persona save(Persona persona) {
        return personaRepository.save(persona);
    }

    public Optional<Persona> update(Integer id, Persona persona) {
        return personaRepository.findById(id).map(existing -> {
            persona.setIdPersona(id);
            return personaRepository.save(persona);
        });
    }

    public boolean delete(Integer id) {
        return personaRepository.findById(id).map(p -> {
            personaRepository.delete(p);
            return true;
        }).orElse(false);
    }
}
