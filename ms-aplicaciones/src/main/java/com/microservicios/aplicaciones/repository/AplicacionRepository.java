package com.microservicios.aplicaciones.repository;

import com.microservicios.aplicaciones.entity.Aplicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AplicacionRepository extends JpaRepository<Aplicacion, String> {
    // TODO: Agrega queries personalizados aquí
}
