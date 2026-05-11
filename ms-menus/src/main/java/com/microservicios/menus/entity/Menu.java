package com.microservicios.menus.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "El ID del menú es obligatorio")
    @Size(max = 10, message = "El ID del menú no puede exceder 10 caracteres")
    @Column(name = "idmenu", length = 10)
    private String idMenu;

    @NotBlank(message = "El ID de aplicación es obligatorio")
    @Size(max = 15, message = "El ID de aplicación no puede exceder 15 caracteres")
    @Column(name = "idaplicacion", length = 15)
    private String idAplicacion;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 45, message = "El nombre no puede exceder 45 caracteres")
    @Column(name = "nombre", length = 45)
    private String nombre;

    @NotBlank(message = "El nivel del menú es obligatorio")
    @Pattern(regexp = "^[12]$", message = "El nivel del menú debe ser 1 o 2")
    @Column(name = "nivel_menu", length = 1)
    private String nivelMenu;

    @Size(max = 10, message = "El ID del menú padre no puede exceder 10 caracteres")
    @Column(name = "idmenu_padre", length = 10)
    private String idMenuPadre;

    @Column(name = "orden")
    private Integer orden;

    @Size(max = 25, message = "El icono no puede exceder 25 caracteres")
    @Column(name = "icono", length = 25)
    private String icono;

    @Column(name = "fecha_reg")
    private LocalDateTime fechaReg;

    @Column(name = "fecha_mod")
    private LocalDateTime fechaMod;

    @Column(name = "nivel_orden")
    private Integer nivelOrden;

    @Size(max = 25, message = "La ruta no puede exceder 25 caracteres")
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
