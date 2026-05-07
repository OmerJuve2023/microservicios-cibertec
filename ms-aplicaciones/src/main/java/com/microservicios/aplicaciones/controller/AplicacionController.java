package com.microservicios.aplicaciones.controller;

import com.microservicios.aplicaciones.entity.Aplicacion;
import com.microservicios.aplicaciones.service.AplicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aplicaciones")
@RequiredArgsConstructor
public class AplicacionController {

    private final AplicacionService aplicacionService;

    @GetMapping
    public ResponseEntity<List<Aplicacion>> findAll() {
        return ResponseEntity.ok(aplicacionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aplicacion> findById(@PathVariable String id) {
        return aplicacionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Aplicacion> save(@RequestBody Aplicacion aplicacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(aplicacionService.save(aplicacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aplicacion> update(@PathVariable String id, @RequestBody Aplicacion aplicacion) {
        return aplicacionService.update(id, aplicacion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return aplicacionService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
