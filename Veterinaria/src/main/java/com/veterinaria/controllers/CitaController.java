/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.veterinaria.controllers;

import com.veterinaria.service.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 *
 * @author santi
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/citas")
public class CitaController {
    
    private final CitaService citaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var citas = citaService.obtenerCitas();
        model.addAttribute("citas", citas);
        model.addAttribute("totalCitas", citas.size());
        return "citas/listado";
    }
}
