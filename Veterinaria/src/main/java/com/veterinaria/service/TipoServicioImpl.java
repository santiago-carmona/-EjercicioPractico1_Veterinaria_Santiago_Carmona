/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.veterinaria.service;

import com.veterinaria.domain.TipoServicio;
import com.veterinaria.repository.TipoServicioRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author santi
 */
@Service
@RequiredArgsConstructor
public class TipoServicioImpl implements TipoServicioService {
    
    private final TipoServicioRepository tipoServicioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TipoServicio> obtenerTipos() {
        return tipoServicioRepository.findAllByOrderByNombreAsc();
    }

    @Override
    @Transactional
    public void guardarTipo(TipoServicio tipoServicio) {
        // Se eliminan espacios sobrantes antes de guardar
        tipoServicio.setNombre(tipoServicio.getNombre().trim());
        if (tipoServicio.getDescripcion() != null) {
            tipoServicio.setDescripcion(tipoServicio.getDescripcion().trim());
        }
        tipoServicioRepository.saveAndFlush(tipoServicio);
    }

    @Override
    @Transactional
    public void eliminarTipo(Integer id) {
        // Verifica que el tipo exista antes de eliminarlo
        if (!tipoServicioRepository.existsById(id)) {
            throw new IllegalArgumentException("El tipo de servicio con ID " + id + " no existe");
        }
        try {
            tipoServicioRepository.deleteById(id);
            tipoServicioRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("No se puede eliminar el tipo porque tiene servicios asociados", e);
        }
    }
}
