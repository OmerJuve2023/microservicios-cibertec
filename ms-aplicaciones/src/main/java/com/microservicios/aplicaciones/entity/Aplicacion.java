package com.microservicios.aplicaciones.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "aplicaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aplicacion {

    @Id
    @Column(name = "idaplicacion", length = 15)
    private String idAplicacion;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "estado", length = 3)
    private String estado;

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
