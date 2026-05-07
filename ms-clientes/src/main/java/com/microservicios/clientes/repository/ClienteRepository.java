package com.microservicios.clientes.repository;

import com.microservicios.clientes.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByEstado(String estado);
    // TODO: Agrega queries personalizados aquí
}
