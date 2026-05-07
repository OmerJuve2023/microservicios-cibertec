package com.microservicios.clientes.service;

import com.microservicios.clientes.entity.Cliente;
import com.microservicios.clientes.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Integer id) {
        return clienteRepository.findById(id);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> update(Integer id, Cliente cliente) {
        return clienteRepository.findById(id).map(existing -> {
            cliente.setIdCliente(id);
            return clienteRepository.save(cliente);
        });
    }

    public boolean delete(Integer id) {
        return clienteRepository.findById(id).map(c -> {
            clienteRepository.delete(c);
            return true;
        }).orElse(false);
    }
}
