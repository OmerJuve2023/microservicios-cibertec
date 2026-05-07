package com.microservicios.usuarios.repository;

import com.microservicios.usuarios.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    // TODO: Agrega queries personalizados aquí
}
