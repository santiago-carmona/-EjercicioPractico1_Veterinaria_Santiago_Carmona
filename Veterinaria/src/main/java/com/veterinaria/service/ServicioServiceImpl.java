/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.veterinaria.service;

import com.veterinaria.domain.Servicio;
import com.veterinaria.domain.TipoServicio;
import com.veterinaria.repository.ServicioRepository;
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
public class ServicioServiceImpl implements ServicioService {
    
    private final ServicioRepository servicioRepository;
    private final TipoServicioRepository tipoServicioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> obtenerServicios() {
        return servicioRepository.findAllByOrderByNombreAsc();
    }

    @Override
    @Transactional
    public void guardarServicio(Servicio servicio) {
        // Se eliminan espacios sobrantes antes de guardar
        servicio.setNombre(servicio.getNombre().trim());
        // El formulario solo envía el id del tipo, se busca el tipo real en la base de datos
        Integer idTipo = servicio.getTipoServicio().getId();
        TipoServicio tipoServicio = tipoServicioRepository.findById(idTipo)
                .orElseThrow(() -> new IllegalArgumentException("El tipo de servicio con ID " + idTipo + " no existe"));
        servicio.setTipoServicio(tipoServicio);
        servicioRepository.saveAndFlush(servicio);
    }

    @Override
    @Transactional
    public void eliminarServicio(Integer id) {
        // Verifica que el servicio exista antes de eliminarlo
        if (!servicioRepository.existsById(id)) {
            throw new IllegalArgumentException("El servicio con ID " + id + " no existe");
        }
        try {
            servicioRepository.deleteById(id);
            servicioRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("No se puede eliminar el servicio porque tiene citas asociadas", e);
        }
    }
}
