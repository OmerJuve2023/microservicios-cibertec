package com.microservicios.usuarios.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "accesos_menus")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccesoMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Referencia a Usuario (misma BD)
    @Column(name = "idusuario")
    private Integer idUsuario;

    // Referencia a Menu (en MS-Menus, solo guardamos el ID)
    @Column(name = "idmenu", length = 10)
    private String idMenu;

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
