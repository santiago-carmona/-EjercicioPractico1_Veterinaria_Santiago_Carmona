/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.veterinaria.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author santi
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "servicio")
public class Servicio implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "El nombre del servicio no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;

    @Column(nullable = false, precision = 8, scale = 2)
    @NotNull(message = "El precio no puede estar vacío")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    private BigDecimal precio;

    @Column(name = "duracion_minutos", nullable = false)
    @NotNull(message = "La duración no puede estar vacía")
    @Min(value = 1, message = "La duración debe ser de al menos 1 minuto")
    private Integer duracionMinutos;

    // Relación muchos a uno: varios servicios pertenecen a un tipo de servicio
    @ManyToOne
    @JoinColumn(name = "tipo_servicio_id", nullable = false)
    @NotNull(message = "Debe seleccionar un tipo de servicio")
    private TipoServicio tipoServicio;
}
