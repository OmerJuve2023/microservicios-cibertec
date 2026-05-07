package com.microservicios.usuarios.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "personas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpersona")
    private Integer idPersona;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "apellido", length = 45)
    private String apellido;

    @Column(name = "email", length = 45)
    private String email;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "tipo_documento", length = 1)
    private String tipoDocumento;

    @Column(name = "doc_identidad", length = 20)
    private String docIdentidad;

    @Column(name = "direccion", length = 50)
    private String direccion;

    @Column(name = "distrito", length = 15)
    private String distrito;

    @Column(name = "provincia", length = 15)
    private String provincia;

    @Column(name = "departamento", length = 15)
    private String departamento;

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
