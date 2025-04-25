/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.hospital.citasmedicas.controller;

import com.hospital.citasmedicas.model.Medico;
import com.hospital.citasmedicas.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Controller
@RequestMapping("/medicos")
public class MedicoViewController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public String listarMedicos(@RequestParam(required = false) String buscar,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               Model model) {
        
        Page<Medico> medicoPage;
        
        if (buscar != null && !buscar.isEmpty()) {
            medicoPage = medicoService.buscarPorNombre(buscar, page, size);
            model.addAttribute("busqueda", buscar);
        } else {
            medicoPage = medicoService.obtenerMedicosPaginados(page, size, "nombre");
        }
        
        model.addAttribute("medicos", medicoPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", medicoPage.getTotalPages());
        model.addAttribute("totalItems", medicoPage.getTotalElements());
        
        return "medicos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("medico", new Medico());
        return "medicos/formulario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Medico medico = medicoService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de médico inválido: " + id));
        model.addAttribute("medico", medico);
        return "medicos/formulario";
    }

    @PostMapping
    public String guardarMedico(@ModelAttribute Medico medico) {
        if (medico.getId() == null) {
            medicoService.registrarMedico(medico);
        } else {
            medicoService.actualizarMedico(medico);
        }
        return "redirect:/medicos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMedico(@PathVariable Long id) {
        medicoService.eliminarMedico(id);
        return "redirect:/medicos";
    }
}
