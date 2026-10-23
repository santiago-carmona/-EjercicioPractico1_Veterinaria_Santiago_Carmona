/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.veterinaria.service;

import com.veterinaria.domain.TipoServicio;
import java.util.List;
/**
 *
 * @author santi
 */
public interface TipoServicioService {
    
    List<TipoServicio> obtenerTipos();

    void guardarTipo(TipoServicio tipoServicio);

    void eliminarTipo(Integer id);
}
