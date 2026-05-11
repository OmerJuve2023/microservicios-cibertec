package com.microservicios.clientes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @Column(name = "idpersona")
    private Integer idPersona;

    @NotBlank(message = "La razón social es obligatoria")
    @Size(max = 250, message = "La razón social no puede exceder 250 caracteres")
    @Column(name = "razon_social", length = 250)
    private String razonSocial;

    @NotBlank(message = "El tipo de cliente es obligatorio")
    @Pattern(regexp = "^(PN|PJ)$", message = "El tipo de cliente debe ser PN (Persona Natural) o PJ (Persona Jurídica)")
    @Column(name = "tipo_cliente", length = 2)
    private String tipoCliente;

    @Size(max = 15, message = "El sector no puede exceder 15 caracteres")
    @Column(name = "sector", length = 15)
    private String sector;

    @Size(max = 45, message = "El representante legal no puede exceder 45 caracteres")
    @Column(name = "representante_legal", length = 45)
    private String representanteLegal;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "^(ACT|INA)$", message = "El estado debe ser ACT o INA")
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
        if (estado == null) estado = "ACT";
    }

    @PreUpdate
    protected void onUpdate() {
        fechaMod = LocalDateTime.now();
    }
}
