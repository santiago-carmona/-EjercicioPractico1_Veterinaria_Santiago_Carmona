/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.veterinaria.service;

import com.veterinaria.domain.Cita;
import com.veterinaria.repository.CitaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author santi
 */
@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {
    
    private final CitaRepository citaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cita> obtenerCitas() {
        return citaRepository.findAllByOrderByFechaAsc();
    }
}
