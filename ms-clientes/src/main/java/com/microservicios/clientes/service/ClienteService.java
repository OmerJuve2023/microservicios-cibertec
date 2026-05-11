package com.microservicios.clientes.service;

import com.microservicios.clientes.client.UsuarioClient;
import com.microservicios.clientes.entity.Cliente;
import com.microservicios.clientes.exception.EntidadConRelacionesException;
import com.microservicios.clientes.exception.EntidadNoEncontradaException;
import com.microservicios.clientes.exception.ValidacionException;
import com.microservicios.clientes.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final UsuarioClient usuarioClient;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Integer id) {
        return clienteRepository.findById(id);
    }

    @Transactional
    public Cliente save(Cliente cliente) {
        validarCliente(cliente);

        if (cliente.getIdPersona() != null) {
            Map persona = usuarioClient.getPersonaById(cliente.getIdPersona());
            if (persona == null) {
                throw new ValidacionException("La persona con ID " + cliente.getIdPersona() + " no existe");
            }
        }

        return clienteRepository.save(cliente);
    }

    @Transactional
    public Optional<Cliente> update(Integer id, Cliente cliente) {
        validarCliente(cliente);

        Optional<Cliente> existente = clienteRepository.findById(id);
        if (existente.isEmpty()) {
            return Optional.empty();
        }

        if (cliente.getIdPersona() != null) {
            Map persona = usuarioClient.getPersonaById(cliente.getIdPersona());
            if (persona == null) {
                throw new ValidacionException("La persona con ID " + cliente.getIdPersona() + " no existe");
            }
        }

        cliente.setIdCliente(id);
        return Optional.of(clienteRepository.save(cliente));
    }

    @Transactional
    public void delete(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Cliente con ID " + id + " no encontrado"));

        // No se puede eliminar un cliente si tiene aplicaciones asignadas
        // Esto se verificaría en ms-aplicaciones via llamada REST o evento
        clienteRepository.delete(cliente);
    }

    public void validarCliente(Cliente cliente) {
        if (cliente.getRazonSocial() == null || cliente.getRazonSocial().trim().isEmpty()) {
            throw new ValidacionException("La razón social es obligatoria");
        }
        if (cliente.getRazonSocial().length() > 250) {
            throw new ValidacionException("La razón social no puede exceder 250 caracteres");
        }
        if (cliente.getTipoCliente() == null) {
            throw new ValidacionException("El tipo de cliente es obligatorio");
        }
        if (!cliente.getTipoCliente().equals("PN") && !cliente.getTipoCliente().equals("PJ")) {
            throw new ValidacionException("El tipo de cliente debe ser PN (Persona Natural) o PJ (Persona Jurídica)");
        }
        if (cliente.getSector() != null && cliente.getSector().length() > 15) {
            throw new ValidacionException("El sector no puede exceder 15 caracteres");
        }
        if (cliente.getRepresentanteLegal() != null && cliente.getRepresentanteLegal().length() > 45) {
            throw new ValidacionException("El representante legal no puede exceder 45 caracteres");
        }
        if (cliente.getEstado() == null) {
            throw new ValidacionException("El estado es obligatorio");
        }
        if (!cliente.getEstado().equals("ACT") && !cliente.getEstado().equals("INA")) {
            throw new ValidacionException("El estado debe ser ACT o INA");
        }
    }
}
