package com.microservicios.usuarios.service;

import com.microservicios.usuarios.entity.AccesoMenu;
import com.microservicios.usuarios.repository.AccesoMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccesoMenuService {

    private final AccesoMenuRepository accesoMenuRepository;

    public List<AccesoMenu> findAll() {
        return accesoMenuRepository.findAll();
    }

    public List<AccesoMenu> findByUsuario(Integer idUsuario) {
        return accesoMenuRepository.findByIdUsuario(idUsuario);
    }

    public Optional<AccesoMenu> findById(Integer id) {
        return accesoMenuRepository.findById(id);
    }

    public AccesoMenu save(AccesoMenu accesoMenu) {
        return accesoMenuRepository.save(accesoMenu);
    }

    public boolean delete(Integer id) {
        return accesoMenuRepository.findById(id).map(a -> {
            accesoMenuRepository.delete(a);
            return true;
        }).orElse(false);
    }
}
