/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.restaurante.controller;

import com.example.restaurante.model.Plato;
import com.example.restaurante.servicio.PlatoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/platos")
public class PlatoController {

    private final PlatoService service;

    public PlatoController(PlatoService service) {
        this.service = service;
    }

    @GetMapping
    public String listarPlatos(Model model) {
        model.addAttribute("platos", service.listarTodos());
        return "listado_platos";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("plato", new Plato());
        return "formulario_plato";
    }

    @PostMapping
    public String guardarPlato(@ModelAttribute Plato plato) {
        service.guardar(plato);
        return "redirect:/platos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("plato", service.buscarPorId(id).orElseThrow(() -> new IllegalArgumentException("ID inválido " + id)));
        return "formulario_plato";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPlato(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/platos";
    }
}

