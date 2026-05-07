package com.microservicios.menus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "menus")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @Id
    @Column(name = "idmenu", length = 10)
    private String idMenu;

    // Referencia a Aplicacion en MS-Aplicaciones (solo ID, sin FK)
    @Column(name = "idaplicacion", length = 15)
    private String idAplicacion;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "nivel_menu", length = 1)
    private String nivelMenu;

    // Auto-referencia al menu padre (mismo MS, misma BD)
    @Column(name = "idmenu_padre", length = 10)
    private String idMenuPadre;

    @Column(name = "orden")
    private Integer orden;

    @Column(name = "icono", length = 25)
    private String icono;

    @Column(name = "fecha_reg")
    private LocalDateTime fechaReg;

    @Column(name = "fecha_mod")
    private LocalDateTime fechaMod;

    @Column(name = "nivel_orden")
    private Integer nivelOrden;

    @Column(name = "ruta", length = 25)
    private String ruta;

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
