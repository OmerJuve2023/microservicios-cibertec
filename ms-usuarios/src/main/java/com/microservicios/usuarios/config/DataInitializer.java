package com.microservicios.usuarios.config;

import com.microservicios.usuarios.entity.AccesoMenu;
import com.microservicios.usuarios.entity.Persona;
import com.microservicios.usuarios.entity.Usuario;
import com.microservicios.usuarios.repository.AccesoMenuRepository;
import com.microservicios.usuarios.repository.PersonaRepository;
import com.microservicios.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final PersonaRepository personaRepository;
    private final UsuarioRepository usuarioRepository;
    private final AccesoMenuRepository accesoMenuRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (personaRepository.count() > 0) {
            log.info("Los datos ya existen en la base de datos. DataInitializer omitido.");
            return;
        }

        log.info("Inicializando datos de prueba...");

        // ============================================================
        // PERSONAS (guardar individualmente para mantener orden de IDs)
        // ============================================================
        Persona p1 = personaRepository.save(crearPersona("Carlos", "Ramirez Torres", "carlos.ramirez@email.com", "987654321", "D", "45678901", "Av. Lima 123", "Miraflores", "Lima", "Lima"));
        Persona p2 = personaRepository.save(crearPersona("Ana", "Gutierrez Lopez", "ana.gutierrez@email.com", "912345678", "D", "32165498", "Jr. Cusco 456", "San Isidro", "Lima", "Lima"));
        Persona p3 = personaRepository.save(crearPersona("Luis", "Paredes Quispe", "luis.paredes@email.com", "956123478", "D", "78945612", "Calle Arequipa 789", "Surco", "Lima", "Lima"));
        Persona p4 = personaRepository.save(crearPersona("Maria", "Flores Chavez", "maria.flores@email.com", "945678123", "D", "65412398", "Av. Brasil 321", "Lince", "Lima", "Lima"));
        Persona p5 = personaRepository.save(crearPersona("Roberto", "Mendoza Salas", "roberto.mendoza@email.com", "934567812", "D", "12378945", "Jr. Tacna 654", "Barranco", "Lima", "Lima"));
        log.info("Personas creadas: 5");

        // ============================================================
        // USUARIOS
        // ============================================================
        // Passwords hasheados: admin123, user123, pass123
        // Roles: ADMIN (admin), USUARIO (los demas)
        List<Usuario> usuarios = Arrays.asList(
            crearUsuario(p1.getIdPersona(), "ACT", "admin", "admin123", "ADMIN"),
            crearUsuario(p2.getIdPersona(), "ACT", "jefa", "user123", "USUARIO"),
            crearUsuario(p3.getIdPersona(), "ACT", "lparedes", "pass123", "USUARIO"),
            crearUsuario(p4.getIdPersona(), "ACT", "mflores", "pass123", "USUARIO"),
            crearUsuario(p5.getIdPersona(), "INA", "rmendoza", "pass123", "USUARIO")
        );
        usuarios = usuarioRepository.saveAll(usuarios);
        log.info("Usuarios creados: {}", usuarios.size());

        // ============================================================
        // ACCESOS A MENUS
        // ============================================================
        List<AccesoMenu> accesos = Arrays.asList(
            crearAccesoMenu(usuarios.get(0).getIdUsuario(), "MNU001", "ACT"), // admin
            crearAccesoMenu(usuarios.get(0).getIdUsuario(), "MNU002", "ACT"),
            crearAccesoMenu(usuarios.get(0).getIdUsuario(), "MNU003", "ACT"),
            crearAccesoMenu(usuarios.get(0).getIdUsuario(), "MNU004", "ACT"),
            crearAccesoMenu(usuarios.get(1).getIdUsuario(), "MNU001", "ACT"), // jefa
            crearAccesoMenu(usuarios.get(1).getIdUsuario(), "MNU002", "ACT"),
            crearAccesoMenu(usuarios.get(2).getIdUsuario(), "MNU001", "ACT"), // lparedes
            crearAccesoMenu(usuarios.get(2).getIdUsuario(), "MNU003", "ACT"),
            crearAccesoMenu(usuarios.get(3).getIdUsuario(), "MNU002", "INA")  // mflores
        );
        accesoMenuRepository.saveAll(accesos);
        log.info("Accesos a menus creados: {}", accesos.size());

        log.info("============================================================");
        log.info("DATOS DE PRUEBA INICIALIZADOS CORRECTAMENTE");
        log.info("============================================================");
        log.info("USUARIOS DISPONIBLES:");
        log.info("  - admin / admin123 (ADMIN)");
        log.info("  - jefa / user123 (USUARIO)");
        log.info("  - lparedes / pass123 (USUARIO)");
        log.info("  - mflores / pass123 (USUARIO)");
        log.info("  - rmendoza / pass123 (USUARIO - INACTIVO)");
        log.info("============================================================");
    }

    private Persona crearPersona(String nombre, String apellido, String email, String telefono,
                                  String tipoDocumento, String docIdentidad, String direccion,
                                  String distrito, String provincia, String departamento) {
        Persona p = new Persona();
        p.setNombre(nombre);
        p.setApellido(apellido);
        p.setEmail(email);
        p.setTelefono(telefono);
        p.setTipoDocumento(tipoDocumento);
        p.setDocIdentidad(docIdentidad);
        p.setDireccion(direccion);
        p.setDistrito(distrito);
        p.setProvincia(provincia);
        p.setDepartamento(departamento);
        return p;
    }

    private Usuario crearUsuario(Integer idPersona, String estado, String nombreUsuario, String password, String rol) {
        Usuario u = new Usuario();
        u.setIdPersona(idPersona);
        u.setEstado(estado);
        u.setNombreUsuario(nombreUsuario);
        u.setPassword(passwordEncoder.encode(password));
        u.setRol(rol);
        return u;
    }

    private AccesoMenu crearAccesoMenu(Integer idUsuario, String idMenu, String estado) {
        AccesoMenu am = new AccesoMenu();
        am.setIdUsuario(idUsuario);
        am.setIdMenu(idMenu);
        am.setEstado(estado);
        return am;
    }
}