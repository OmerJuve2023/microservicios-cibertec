package com.microservicios.aplicaciones.repository;

import com.microservicios.aplicaciones.entity.ClienteAplicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClienteAplicacionRepository extends JpaRepository<ClienteAplicacion, Integer> {
    List<ClienteAplicacion> findByIdCliente(Integer idCliente);
    List<ClienteAplicacion> findByIdAplicacion(String idAplicacion);
    // TODO: Agrega queries personalizados aquí
}
