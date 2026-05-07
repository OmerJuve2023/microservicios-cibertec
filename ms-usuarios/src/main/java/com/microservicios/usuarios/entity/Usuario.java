package com.microservicios.usuarios.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private Integer idUsuario;

    // Referencia a Persona por ID (sin FK directa, misma BD en este MS)
    @Column(name = "idpersona")
    private Integer idPersona;

    @Column(name = "estado", length = 3)
    private String estado;

    @Column(name = "nombre_usuario", length = 25)
    private String nombreUsuario;

    @Column(name = "password")
    private String password;

    @Column(name = "fecha_reg")
    private LocalDateTime fechaReg;

    @Column(name = "fecha_mod")
    private LocalDateTime fechaMod;

    @PrePersist
    protected void onCreate() {
        fechaReg = LocalDateTime.now();
        fechaMod = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        fechaMod = LocalDateTime.now();
    }
}
