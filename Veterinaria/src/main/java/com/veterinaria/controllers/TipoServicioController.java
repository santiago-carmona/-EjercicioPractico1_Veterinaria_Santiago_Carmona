/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.veterinaria.controllers;
import com.veterinaria.domain.TipoServicio;
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
@RequestMapping("/tipos")
public class TipoServicioController {
    
    private final TipoServicioService tipoServicioService;
    private final MessageSource messageSource;

    @GetMapping("/listado")
    public String listado(Model model) {
        var tipos = tipoServicioService.obtenerTipos();
        model.addAttribute("tipos", tipos);
        model.addAttribute("totalTipos", tipos.size());
        // Objeto vacío que usa el formulario (th:object)
        model.addAttribute("tipoServicio", new TipoServicio());
        return "tipos/listado";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid TipoServicio tipoServicio, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        String titulo = "todoOk";
        String mensaje = (tipoServicio.getId() == null) ? "mensaje.agregado" : "mensaje.modificado";
        if (bindingResult.hasErrors()) {
            titulo = "error";
            mensaje = "tipo.error04";
        } else {
            try {
                tipoServicioService.guardarTipo(tipoServicio);
            } catch (DataIntegrityViolationException e) {
                titulo = "error";
                mensaje = "tipo.error05";
            }
        }
        redirectAttributes.addFlashAttribute(titulo, messageSource.getMessage(mensaje, null, Locale.getDefault()));
        return "redirect:/tipos/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
        String titulo = "todoOk";
        String detalle = "mensaje.eliminado";
        try {
            tipoServicioService.eliminarTipo(id);
        } catch (IllegalArgumentException e) {
            titulo = "error";
            detalle = "tipo.error01"; // El tipo no existe
        } catch (IllegalStateException e) {
            titulo = "error";
            detalle = "tipo.error02"; // El tipo tiene servicios asociados
        } catch (Exception e) {
            titulo = "error";
            detalle = "tipo.error03"; // Cualquier otro error inesperado
        }
        redirectAttributes.addFlashAttribute(titulo, messageSource.getMessage(detalle, null, Locale.getDefault()));
        return "redirect:/tipos/listado";
    }
}
