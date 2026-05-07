package com.microservicios.menus.controller;

import com.microservicios.menus.entity.Menu;
import com.microservicios.menus.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    public ResponseEntity<List<Menu>> findAll() {
        return ResponseEntity.ok(menuService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> findById(@PathVariable String id) {
        return menuService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/aplicacion/{idAplicacion}")
    public ResponseEntity<List<Menu>> findByAplicacion(@PathVariable String idAplicacion) {
        return ResponseEntity.ok(menuService.findByAplicacion(idAplicacion));
    }

    @GetMapping("/padre/{idMenuPadre}")
    public ResponseEntity<List<Menu>> findByMenuPadre(@PathVariable String idMenuPadre) {
        return ResponseEntity.ok(menuService.findByMenuPadre(idMenuPadre));
    }

    @GetMapping("/nivel/{nivelMenu}")
    public ResponseEntity<List<Menu>> findByNivel(@PathVariable String nivelMenu) {
        return ResponseEntity.ok(menuService.findByNivel(nivelMenu));
    }

    @PostMapping
    public ResponseEntity<Menu> save(@RequestBody Menu menu) {
        return ResponseEntity.status(HttpStatus.CREATED).body(menuService.save(menu));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Menu> update(@PathVariable String id, @RequestBody Menu menu) {
        return menuService.update(id, menu)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return menuService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
