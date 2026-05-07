package com.microservicios.clientes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcliente")
    private Integer idCliente;

    // Referencia a Persona en MS-Usuarios (solo ID, sin FK)
    @Column(name = "idpersona")
    private Integer idPersona;

    @Column(name = "razon_social", length = 25)
    private String razonSocial;

    @Column(name = "tipo_cliente", length = 2)
    private String tipoCliente;

    @Column(name = "sector", length = 15)
    private String sector;

    @Column(name = "representante_legal", length = 45)
    private String representanteLegal;

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
