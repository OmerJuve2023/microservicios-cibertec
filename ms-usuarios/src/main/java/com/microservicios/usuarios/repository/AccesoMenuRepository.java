package com.microservicios.usuarios.repository;

import com.microservicios.usuarios.entity.AccesoMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AccesoMenuRepository extends JpaRepository<AccesoMenu, Integer> {
    List<AccesoMenu> findByIdUsuario(Integer idUsuario);
    // TODO: Agrega queries personalizados aquí
}
