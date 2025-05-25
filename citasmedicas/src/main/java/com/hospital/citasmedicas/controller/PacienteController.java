package com.hospital.citasmedicas.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.citasmedicas.dto.PacienteDTO;
import com.hospital.citasmedicas.model.Paciente;
import com.hospital.citasmedicas.repository.PacienteRepository;
import com.hospital.citasmedicas.service.PacienteService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private PacienteService pacienteService;


    /*
     * @PostMapping("/guardar")
     * public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente
     * paciente) {
     * 
     * Paciente guardado = pacienteService.registrarPaciente(paciente);
     * return ResponseEntity.ok(guardado);
     * }
     */
    @PostMapping("/guardar")
    public ResponseEntity<PacienteDTO> guardarPaciente(@RequestBody PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente();
        paciente.setNombre(pacienteDTO.getNombre());

        // Campos nuevos
        if (pacienteDTO.getApellidos() != null) {
            paciente.setApellidos(pacienteDTO.getApellidos());
        }
        if (pacienteDTO.getGenero() != null) {
            paciente.setGenero(pacienteDTO.getGenero());
        }
        if (pacienteDTO.getEmail() != null) {
            paciente.setEmail(pacienteDTO.getEmail());
        }
        if (pacienteDTO.getDireccion() != null) {
            paciente.setDireccion(pacienteDTO.getDireccion());
        }

        paciente.setDni(pacienteDTO.getDni());

        if (pacienteDTO.getTelefono() != null) {
            paciente.setTelefono(pacienteDTO.getTelefono());
        }
        if (pacienteDTO.getGravedadAfeccion() != null) {
            paciente.setGravedadAfeccion(pacienteDTO.getGravedadAfeccion());
        }

        paciente.setFechaNacimiento(pacienteDTO.getFechaNacimiento());

        if (pacienteDTO.getFechaIngreso() != null) {
            paciente.setFechaIngreso(pacienteDTO.getFechaIngreso());
        }

        Paciente guardado = pacienteRepository.save(paciente);

        PacienteDTO responseDTO = new PacienteDTO(
                guardado.getId(),
                guardado.getNombre(),
                guardado.getApellidos(),
                guardado.getDni(),
                guardado.getGenero(),
                guardado.getEmail(),
                guardado.getDireccion(),
                guardado.getTelefono(),
                guardado.getGravedadAfeccion(),
                guardado.getFechaNacimiento(),
                guardado.getFechaIngreso());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("listar")
    public List<PacienteDTO> obtenerPaciente() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        List<PacienteDTO> pacienteDTO = pacientes.stream().map(paciente -> new PacienteDTO(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getApellidos(),
                paciente.getDni(),
                paciente.getGenero(),
                paciente.getEmail(),
                paciente.getDireccion(),
                paciente.getTelefono(),
                paciente.getGravedadAfeccion(),
                paciente.getFechaNacimiento(),
                paciente.getFechaIngreso()
        )).collect(Collectors.toList());
        return pacienteDTO;
    }

//Buscar por el id del Paciente
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        return pacienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Paciente> actualizarPaciente(
            @PathVariable Long id,
            @RequestBody Paciente paciente) {
        paciente.setId(id);
        try {
            Paciente actualizado = pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {
        try {
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
