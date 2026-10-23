/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.veterinaria.controllers;

import com.veterinaria.domain.Servicio;
import com.veterinaria.domain.TipoServicio;
import com.veterinaria.service.ServicioService;
import com.veterinaria.service.TipoServicioService;
import jakarta.validation.Valid;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/**
 *
 * @author santi
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/servicios")
public class ServicioController {
    
    private final ServicioService servicioService;
    private final TipoServicioService tipoServicioService;
    private final MessageSource messageSource;

    @GetMapping("/listado")
    public String listado(Model model) {
        var servicios = servicioService.obtenerServicios();
        model.addAttribute("servicios", servicios);
        model.addAttribute("totalServicios", servicios.size());
        // Tipos para la lista desplegable del formulario
        model.addAttribute("tiposServicio", tipoServicioService.obtenerTipos());
        // Objeto vacío que usa el formulario (th:object), con el tipo inicializado
        Servicio servicioNuevo = new Servicio();
        servicioNuevo.setTipoServicio(new TipoServicio());
        model.addAttribute("servicio", servicioNuevo);
        return "servicios/listado";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Servicio servicio, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        String titulo = "todoOk";
        String mensaje = (servicio.getId() == null) ? "mensaje.agregado" : "mensaje.modificado";
        boolean sinTipo = servicio.getTipoServicio() == null || servicio.getTipoServicio().getId() == null;
        if (bindingResult.hasErrors() || sinTipo) {
            titulo = "error";
            mensaje = "servicio.error04";
        } else {
            try {
                servicioService.guardarServicio(servicio);
            } catch (DataIntegrityViolationException | IllegalArgumentException e) {
                titulo = "error";
                mensaje = "servicio.error05";
            }
        }
        redirectAttributes.addFlashAttribute(titulo, messageSource.getMessage(mensaje, null, Locale.getDefault()));
        return "redirect:/servicios/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
        String titulo = "todoOk";
        String detalle = "mensaje.eliminado";
        try {
            servicioService.eliminarServicio(id);
        } catch (IllegalArgumentException e) {
            titulo = "error";
            detalle = "servicio.error01"; // El servicio no existe
        } catch (IllegalStateException e) {
            titulo = "error";
            detalle = "servicio.error02"; // El servicio tiene citas asociadas
        } catch (Exception e) {
            titulo = "error";
            detalle = "servicio.error03"; // Cualquier otro error inesperado
        }
        redirectAttributes.addFlashAttribute(titulo, messageSource.getMessage(detalle, null, Locale.getDefault()));
        return "redirect:/servicios/listado";
    }
}
