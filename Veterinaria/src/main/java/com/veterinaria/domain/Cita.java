/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.veterinaria.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name = "cita")
public class Cita implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre_mascota", nullable = false, length = 100)
    private String nombreMascota;

    @Column(name = "nombre_propietario", nullable = false, length = 100)
    private String nombrePropietario;

    @Column(nullable = false)
    private LocalDate fecha;

    // Relación muchos a uno: varias citas pueden usar el mismo servicio
    @ManyToOne
    @JoinColumn(name = "servicio_id", nullable = false)
    private Servicio servicio;
}
