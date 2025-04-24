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

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

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
        paciente.setNombres(pacienteDTO.getNombres());
        paciente.setDni(pacienteDTO.getDni());
        paciente.setFechaNacimiento(pacienteDTO.getFechaNacimiento());
        paciente.setContacto(pacienteDTO.getContacto());

        Paciente guardado = pacienteRepository.save(paciente);

        PacienteDTO responseDTO = new PacienteDTO(
                guardado.getId(),
                guardado.getNombres(),
                guardado.getDni(),
                guardado.getFechaNacimiento(),
                guardado.getContacto());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    /*
     * @GetMapping("/listar")
     * public ResponseEntity<List<Paciente>> obtenerPacientes() {
     * return ResponseEntity.ok(pacienteService.obtenerTodos());
     * }
     */

    @GetMapping("listar")
    public List<PacienteDTO> obtenerPaciente() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        List<PacienteDTO> pacienteDTO = pacientes.stream().map(paciente -> new PacienteDTO(
                paciente.getId(),
                paciente.getNombres(),
                paciente.getDni(),
                paciente.getFechaNacimiento(),
                paciente.getContacto())).collect(Collectors.toList());

        return pacienteDTO;
    }

}
