package com.microservicios.usuarios.service;

import com.microservicios.usuarios.entity.Usuario;
import com.microservicios.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> findByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    /**
     * Al crear un usuario, encodea el password con BCrypt
     * para que el Gateway pueda compararlo en el login
     */
    public Usuario save(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    /**
     * Al actualizar, si viene password nuevo lo encodea;
     * si viene vacío/null, mantiene el actual
     */
    public Optional<Usuario> update(Integer id, Usuario usuario) {
        return usuarioRepository.findById(id).map(existing -> {
            if (usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
                usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            } else {
                usuario.setPassword(existing.getPassword());
            }
            usuario.setIdUsuario(id);
            return usuarioRepository.save(usuario);
        });
    }

    public boolean delete(Integer id) {
        return usuarioRepository.findById(id).map(u -> {
            usuarioRepository.delete(u);
            return true;
        }).orElse(false);
    }
}
