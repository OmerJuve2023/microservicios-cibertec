package com.microservicios.usuarios.controller;

import com.microservicios.usuarios.entity.AccesoMenu;
import com.microservicios.usuarios.service.AccesoMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accesos-menus")
@RequiredArgsConstructor
public class AccesoMenuController {

    private final AccesoMenuService accesoMenuService;

    @GetMapping
    public ResponseEntity<List<AccesoMenu>> findAll() {
        return ResponseEntity.ok(accesoMenuService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccesoMenu> findById(@PathVariable Integer id) {
        return accesoMenuService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<AccesoMenu>> findByUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(accesoMenuService.findByUsuario(idUsuario));
    }

    @PostMapping
    public ResponseEntity<AccesoMenu> save(@RequestBody AccesoMenu accesoMenu) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accesoMenuService.save(accesoMenu));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return accesoMenuService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
