package com.microservicios.aplicaciones.controller;

import com.microservicios.aplicaciones.entity.ClienteAplicacion;
import com.microservicios.aplicaciones.service.ClienteAplicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente-aplicacion")
@RequiredArgsConstructor
public class ClienteAplicacionController {

    private final ClienteAplicacionService clienteAplicacionService;

    @GetMapping
    public ResponseEntity<List<ClienteAplicacion>> findAll() {
        return ResponseEntity.ok(clienteAplicacionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteAplicacion> findById(@PathVariable Integer id) {
        return clienteAplicacionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<ClienteAplicacion>> findByCliente(@PathVariable Integer idCliente) {
        return ResponseEntity.ok(clienteAplicacionService.findByCliente(idCliente));
    }

    @GetMapping("/aplicacion/{idAplicacion}")
    public ResponseEntity<List<ClienteAplicacion>> findByAplicacion(@PathVariable String idAplicacion) {
        return ResponseEntity.ok(clienteAplicacionService.findByAplicacion(idAplicacion));
    }

    @PostMapping
    public ResponseEntity<ClienteAplicacion> save(@RequestBody ClienteAplicacion clienteAplicacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteAplicacionService.save(clienteAplicacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteAplicacion> update(@PathVariable Integer id, @RequestBody ClienteAplicacion clienteAplicacion) {
        return clienteAplicacionService.update(id, clienteAplicacion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return clienteAplicacionService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
