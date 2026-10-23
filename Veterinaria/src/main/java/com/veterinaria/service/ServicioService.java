/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.veterinaria.service;

import com.veterinaria.domain.Servicio;
import java.util.List;
/**
 *
 * @author santi
 */
public interface ServicioService {
    
    List<Servicio> obtenerServicios();

    void guardarServicio(Servicio servicio);

    void eliminarServicio(Integer id);
}
