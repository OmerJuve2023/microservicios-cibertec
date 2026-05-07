package com.microservicios.aplicaciones.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cliente_aplicacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteAplicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Referencia a Cliente en MS-Clientes (solo ID, sin FK)
    @Column(name = "idcliente")
    private Integer idCliente;

    // Referencia a Aplicacion (misma BD)
    @Column(name = "idaplicacion", length = 15)
    private String idAplicacion;

    @Column(name = "periodouso", length = 1)
    private String periodoUso;

    @Column(name = "fecha_ini")
    private LocalDate fechaIni;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "estado", length = 3)
    private String estado;

    @Column(name = "preciocontrato", precision = 10, scale = 2)
    private BigDecimal precioContrato;

    @Column(name = "dominio", length = 250)
    private String dominio;

    @Column(name = "url", length = 100)
    private String url;

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
