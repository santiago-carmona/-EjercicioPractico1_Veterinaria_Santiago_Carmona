/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.veterinaria.controllers;

import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author santi
 */
@Controller
@RequiredArgsConstructor
public class IndexController {
    
    private final MessageSource messageSource;

    @GetMapping("/")
    public String cargarPaginaInicio() {
        return "index";
    }

    @PostMapping("/consultas/enviar")
    public String enviarConsulta(@RequestParam String nombre,
            @RequestParam String correo,
            @RequestParam String nombreMascota,
            @RequestParam String mensaje,
            RedirectAttributes redirectAttributes) {
        if (nombre.isBlank() || correo.isBlank() || nombreMascota.isBlank() || mensaje.isBlank()) {
            redirectAttributes.addFlashAttribute("error",
                    messageSource.getMessage("consulta.error", null, Locale.getDefault()));
        } else {
            Object[] datos = {nombre.trim(), nombreMascota.trim()};
            redirectAttributes.addFlashAttribute("todoOk",
                    messageSource.getMessage("consulta.gracias", datos, Locale.getDefault()));
        }
        return "redirect:/#consultas";
    }
}
