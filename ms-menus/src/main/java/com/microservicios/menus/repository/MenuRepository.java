package com.microservicios.menus.repository;

import com.microservicios.menus.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String> {
    List<Menu> findByIdAplicacion(String idAplicacion);
    List<Menu> findByIdMenuPadre(String idMenuPadre);
    List<Menu> findByNivelMenu(String nivelMenu);
    // TODO: Agrega queries personalizados aquí
}
