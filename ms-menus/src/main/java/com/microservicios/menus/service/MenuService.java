package com.microservicios.menus.service;

import com.microservicios.menus.entity.Menu;
import com.microservicios.menus.exception.EntidadNoEncontradaException;
import com.microservicios.menus.exception.ValidacionException;
import com.microservicios.menus.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Menu save(Menu menu) {
        validarMenu(menu);

        if (menu.getIdMenuPadre() != null && !menu.getIdMenuPadre().isEmpty()) {
            if (!menuRepository.existsById(menu.getIdMenuPadre())) {
                throw new ValidacionException("El menú padre con ID " + menu.getIdMenuPadre() + " no existe");
            }
        }

        if (menuRepository.existsById(menu.getIdMenu())) {
            throw new ValidacionException("Ya existe un menú con el ID " + menu.getIdMenu());
        }

        return menuRepository.save(menu);
    }

    @Transactional
    public Optional<Menu> update(String id, Menu menu) {
        validarMenu(menu);

        Optional<Menu> existente = menuRepository.findById(id);
        if (existente.isEmpty()) {
            return Optional.empty();
        }

        if (menu.getIdMenuPadre() != null && !menu.getIdMenuPadre().isEmpty()) {
            if (!menu.getIdMenuPadre().equals(id) && !menuRepository.existsById(menu.getIdMenuPadre())) {
                throw new ValidacionException("El menú padre con ID " + menu.getIdMenuPadre() + " no existe");
            }
            if (menu.getIdMenuPadre().equals(id)) {
                throw new ValidacionException("Un menú no puede ser padre de sí mismo");
            }
        }

        menu.setIdMenu(id);
        return Optional.of(menuRepository.save(menu));
    }

    @Transactional
    public void delete(String id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Menú con ID " + id + " no encontrado"));

        List<Menu> hijos = menuRepository.findByIdMenuPadre(id);
        if (!hijos.isEmpty()) {
            String idsHijos = String.join(", ", hijos.stream().map(Menu::getIdMenu).toList());
            throw new ValidacionException("No se puede eliminar el menú. Tiene submenús asociados: " + idsHijos + ". Elimine o reasigne los submenús primero.");
        }

        menuRepository.delete(menu);
    }

    public void validarMenu(Menu menu) {
        if (menu.getIdMenu() == null || menu.getIdMenu().trim().isEmpty()) {
            throw new ValidacionException("El ID del menú es obligatorio");
        }
        if (menu.getIdMenu().length() > 10) {
            throw new ValidacionException("El ID del menú no puede exceder 10 caracteres");
        }
        if (menu.getIdAplicacion() == null || menu.getIdAplicacion().trim().isEmpty()) {
            throw new ValidacionException("El ID de aplicación es obligatorio");
        }
        if (menu.getIdAplicacion().length() > 15) {
            throw new ValidacionException("El ID de aplicación no puede exceder 15 caracteres");
        }
        if (menu.getNombre() == null || menu.getNombre().trim().isEmpty()) {
            throw new ValidacionException("El nombre es obligatorio");
        }
        if (menu.getNombre().length() > 45) {
            throw new ValidacionException("El nombre no puede exceder 45 caracteres");
        }
        if (menu.getNivelMenu() == null) {
            throw new ValidacionException("El nivel del menú es obligatorio");
        }
        if (!menu.getNivelMenu().equals("1") && !menu.getNivelMenu().equals("2")) {
            throw new ValidacionException("El nivel del menú debe ser 1 o 2");
        }
        if ("2".equals(menu.getNivelMenu()) && (menu.getIdMenuPadre() == null || menu.getIdMenuPadre().isEmpty())) {
            throw new ValidacionException("Un menú de nivel 2 debe tener un menú padre");
        }
        if ("1".equals(menu.getNivelMenu()) && menu.getIdMenuPadre() != null && !menu.getIdMenuPadre().isEmpty()) {
            throw new ValidacionException("Un menú de nivel 1 no puede tener menú padre");
        }
    }
}
