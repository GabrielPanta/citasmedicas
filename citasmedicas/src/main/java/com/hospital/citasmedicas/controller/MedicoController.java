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

import com.hospital.citasmedicas.dto.MedicoDto;
import com.hospital.citasmedicas.model.Medico;
import com.hospital.citasmedicas.repository.MedicoRepository;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    /*
     * @PostMapping("/guardar")
     * public ResponseEntity<Medico> registrarMedico(@RequestBody Medico medico) {
     * 
     * Medico guardado = medicoService.registrarMedico(medico);
     * return ResponseEntity.ok(guardado);
     * }
     */

    @PostMapping("/guardar")
    public ResponseEntity<MedicoDto> guardarMedico(@RequestBody MedicoDto medicoDTO) {
        Medico medico = new Medico();
        medico.setNombre(medicoDTO.getNombre());
        medico.setEspecialidad(medicoDTO.getEspecialidad());
        medico.setCmp(medicoDTO.getCmp());

        Medico guardado = medicoRepository.save(medico);

        MedicoDto responseDTO = new MedicoDto(
                guardado.getId(),
                guardado.getNombre(),
                guardado.getEspecialidad(),
                guardado.getCmp());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    /*
     * @GetMapping("/listar")
     * public ResponseEntity<List<Medico>> obtenerMedicos() {
     * return ResponseEntity.ok(medicoService.obtenerTodos());
     * }
     */

    @GetMapping("/listar")
    public List<MedicoDto> obtenerMedicos() {
        List<Medico> medicos = medicoRepository.findAll();
        List<MedicoDto> medicoDto = medicos.stream().map(medico -> new MedicoDto(
                medico.getId(),
                medico.getNombre(),
                medico.getEspecialidad(),
                medico.getCmp())).collect(Collectors.toList());

        return medicoDto;
    }

}
