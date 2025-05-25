package com.hospital.citasmedicas.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.citasmedicas.dto.CitaMedicaDTO;
import com.hospital.citasmedicas.model.CitaMedica;
import com.hospital.citasmedicas.model.Medico;
import com.hospital.citasmedicas.model.Paciente;
import com.hospital.citasmedicas.model.Sala;
import com.hospital.citasmedicas.repository.CitaMedicaRepository;
import com.hospital.citasmedicas.repository.MedicoRepository;
import com.hospital.citasmedicas.repository.PacienteRepository;
import com.hospital.citasmedicas.repository.SalaRepository;
import com.hospital.citasmedicas.service.CitaMedicaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/citasmedicas")
public class CitaMedicaController {

    @Autowired
    private CitaMedicaService citaMedicaService;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private CitaMedicaRepository citaMedicaRepository;
     @Autowired
    private SalaRepository salaRepository;

   /*  @PostMapping("/guardar")
    public ResponseEntity<CitaMedica>registrarCitaMedica(@RequestBody CitaMedica citaMedica){
        CitaMedica guardado=citaMedicaService.registrarCitaMedica(citaMedica);
        return ResponseEntity.ok(guardado);
    }*/

   @PostMapping(value = "/guardar", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CitaMedicaDTO> guardarCita(@RequestBody CitaMedicaDTO citaDTO) {
        Paciente paciente = pacienteRepository.findById(citaDTO.getPacienteId())
                                 .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        Medico medico = medicoRepository.findById(citaDTO.getMedicoId())
                             .orElseThrow(() -> new RuntimeException("Medico no encontrado"));
     Sala sala = salaRepository.findById(citaDTO.getSalaId())
    .orElseThrow(() -> new RuntimeException("Sala no encontrada"));
        
        CitaMedica cita = new CitaMedica();
        cita.setFecha(citaDTO.getFecha());
        cita.setHora(citaDTO.getHora());
        cita.setMotivo(citaDTO.getMotivo());
        cita.setEstado(citaDTO.getEstado());
        cita.setPaciente(paciente);
        cita.setMedico(medico);
        cita.setSala(sala);
        
        CitaMedica guardada = citaMedicaRepository.save(cita);
        
        // Convertimos la entidad CitaMedica a DTO para enviarla como respuesta
        CitaMedicaDTO responseDTO = new CitaMedicaDTO(
            guardada.getId(),
            guardada.getFecha(),
            guardada.getHora(),
            guardada.getMotivo(),
            guardada.getEstado(),
            guardada.getPaciente().getId(),
            guardada.getMedico().getId(),
            guardada.getSala().getId()
        );
        return ResponseEntity.ok(responseDTO);
    }

   /*  @GetMapping("/listar")
    public ResponseEntity <List<CitaMedica>> obtenerCitasMedicas() {
        return ResponseEntity.ok(citaMedicaService.obtenerTodos());
    }*/

 @GetMapping("/listar")
    public List<CitaMedicaDTO> obtenerCitas() {
        List<CitaMedica> citas = citaMedicaRepository.findAll();
        List<CitaMedicaDTO> citasDTO = citas.stream().map(cita -> new CitaMedicaDTO(
            cita.getId(),
            cita.getFecha(),
            cita.getHora(),
            cita.getMotivo(),
            cita.getEstado(),
            cita.getPaciente().getId(),
            cita.getMedico().getId(),
            cita.getSala().getId()
        )).collect(Collectors.toList());
        return citasDTO;
    }
}
