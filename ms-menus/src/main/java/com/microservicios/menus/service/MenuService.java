package com.microservicios.menus.service;

import com.microservicios.menus.entity.Menu;
import com.microservicios.menus.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    public Optional<Menu> findById(String id) {
        return menuRepository.findById(id);
    }

    public List<Menu> findByAplicacion(String idAplicacion) {
        return menuRepository.findByIdAplicacion(idAplicacion);
    }

    public List<Menu> findByMenuPadre(String idMenuPadre) {
        return menuRepository.findByIdMenuPadre(idMenuPadre);
    }

    public List<Menu> findByNivel(String nivelMenu) {
        return menuRepository.findByNivelMenu(nivelMenu);
    }

    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

    public Optional<Menu> update(String id, Menu menu) {
        return menuRepository.findById(id).map(existing -> {
            menu.setIdMenu(id);
            return menuRepository.save(menu);
        });
    }

    public boolean delete(String id) {
        return menuRepository.findById(id).map(m -> {
            menuRepository.delete(m);
            return true;
        }).orElse(false);
    }
}
