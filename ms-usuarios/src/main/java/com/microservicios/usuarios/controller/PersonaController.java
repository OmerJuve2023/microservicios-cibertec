package com.microservicios.usuarios.controller;

import com.microservicios.usuarios.entity.Persona;
import com.microservicios.usuarios.service.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaService personaService;

    @GetMapping
    public ResponseEntity<List<Persona>> findAll() {
        return ResponseEntity.ok(personaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> findById(@PathVariable Integer id) {
        return personaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Persona> save(@RequestBody Persona persona) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personaService.save(persona));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> update(@PathVariable Integer id, @RequestBody Persona persona) {
        return personaService.update(id, persona)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return personaService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
