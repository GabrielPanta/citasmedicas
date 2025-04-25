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
import com.hospital.citasmedicas.service.MedicoService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/medicos")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;
    @Autowired
    private MedicoRepository medicoRepository;

   /*  @PostMapping("/guardar")
    public ResponseEntity<Medico> registrarMedico(@RequestBody Medico medico) {

        Medico guardado = medicoService.registrarMedico(medico);
        return ResponseEntity.ok(guardado);
    }*/

    @PostMapping("/guardar")
    public ResponseEntity<MedicoDto> guardarMedico(@RequestBody MedicoDto medicoDTO) {
        Medico medico = new Medico();
        medico.setNombre(medicoDTO.getNombre());
        
        // Actualizamos con campos nuevos
        if (medicoDTO.getApellidos() != null) {
            medico.setApellidos(medicoDTO.getApellidos());
        }
        if (medicoDTO.getDni() != null) {
            medico.setDni(medicoDTO.getDni());
        }
        if (medicoDTO.getEmail() != null) {
            medico.setEmail(medicoDTO.getEmail());
        }
        
        medico.setEspecialidad(medicoDTO.getEspecialidad());
        
        if (medicoDTO.getFechaGraduacion() != null) {
            medico.setFechaGraduacion(medicoDTO.getFechaGraduacion());
        }
        if (medicoDTO.getFechaIncorporacion() != null) {
            medico.setFechaIncorporacion(medicoDTO.getFechaIncorporacion());
        }
        
        medico.setCmp(medicoDTO.getCmp());
        
        Medico guardado = medicoRepository.save(medico);
        
        
        MedicoDto responseDTO = new MedicoDto(
                  guardado.getId(),
    guardado.getNombre(),
    guardado.getApellidos(),
    guardado.getDni(),
    guardado.getEmail(),
    guardado.getEspecialidad(),
    guardado.getFechaGraduacion(),
    guardado.getFechaIncorporacion(),
    guardado.getCmp()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

/*
    @GetMapping("/listar")
    public ResponseEntity<List<Medico>> obtenerMedicos() {
        return ResponseEntity.ok(medicoService.obtenerTodos());
    } */


    @GetMapping("/listar")
    public List<MedicoDto> obtenerMedicos() {
        List<Medico> medicos = medicoRepository.findAll();
       List<MedicoDto> medicoDtos = medicos.stream().map(medico -> new MedicoDto(
        medico.getId(),
        medico.getNombre(),
        medico.getApellidos(),
        medico.getDni(),
        medico.getEmail(),
        medico.getEspecialidad(),
        medico.getFechaGraduacion(),
        medico.getFechaIncorporacion(),
        medico.getCmp()
    )).collect(Collectors.toList());
    return medicoDtos;
}
    
    
   @GetMapping("/{id}")
    public ResponseEntity<MedicoDto> buscarPorId(@PathVariable Long id) {
        return medicoService.buscarPorId(id)
                 .map(medico -> {
                MedicoDto medicoDTO = new MedicoDto(
                    medico.getId(),
                    medico.getNombre(),
                    medico.getApellidos(),
                    medico.getDni(),
                    medico.getEmail(),
                    medico.getEspecialidad(),
                    medico.getFechaGraduacion(),
                    medico.getFechaIncorporacion(),
                    medico.getCmp()
                );
                    return ResponseEntity.ok(medicoDTO);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
  @GetMapping("/cmp/{cmp}")
    public ResponseEntity<MedicoDto> buscarPorCmp(@PathVariable String cmp) {
        return medicoService.buscarPorCmp(cmp)
                .map(medico -> {
                    MedicoDto medicoDTO = new MedicoDto(
                             medico.getId(),
                    medico.getNombre(),
                    medico.getApellidos(),
                    medico.getDni(),
                    medico.getEmail(),
                    medico.getEspecialidad(),
                    medico.getFechaGraduacion(),
                    medico.getFechaIncorporacion(),
                    medico.getCmp()
                    );
                    return ResponseEntity.ok(medicoDTO);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
  @PutMapping("/{id}")
    public ResponseEntity<MedicoDto> actualizarMedico(@PathVariable Long id, @RequestBody MedicoDto medicoDTO) {
        return medicoService.buscarPorId(id)
                .map(medicoExistente -> {
                    medicoExistente.setNombre(medicoDTO.getNombre());
                    
                   
                    if (medicoDTO.getApellidos() != null) {
                        medicoExistente.setApellidos(medicoDTO.getApellidos());
                    }
                    if (medicoDTO.getDni() != null) {
                        medicoExistente.setDni(medicoDTO.getDni());
                    }
                    if (medicoDTO.getEmail() != null) {
                        medicoExistente.setEmail(medicoDTO.getEmail());
                    }
                    
                    medicoExistente.setEspecialidad(medicoDTO.getEspecialidad());
                    
                    if (medicoDTO.getFechaGraduacion() != null) {
                        medicoExistente.setFechaGraduacion(medicoDTO.getFechaGraduacion());
                    }
                    if (medicoDTO.getFechaIncorporacion() != null) {
                        medicoExistente.setFechaIncorporacion(medicoDTO.getFechaIncorporacion());
                    }
                    
                    medicoExistente.setCmp(medicoDTO.getCmp());
                    
                    Medico actualizado = medicoService.actualizarMedico(medicoExistente);
                    
                    MedicoDto responseDTO = new MedicoDto(
                         actualizado.getId(),
    actualizado.getNombre(),
    actualizado.getApellidos(),
    actualizado.getDni(),
    actualizado.getEmail(),
    actualizado.getEspecialidad(),
    actualizado.getFechaGraduacion(),
    actualizado.getFechaIncorporacion(),
    actualizado.getCmp()
                    );
                    
                    return ResponseEntity.ok(responseDTO);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarMedico(@PathVariable Long id) {
        try {
            medicoService.eliminarMedico(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<List<MedicoDto>> buscarPorEspecialidad(
            @PathVariable String especialidad,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Page<Medico> medicosPage = medicoService.buscarPorEspecialidad(especialidad, page, size);
        
        List<MedicoDto> medicoDTOs = medicosPage.getContent().stream()
                .map(medico -> new MedicoDto(
                     medico.getId(),
                    medico.getNombre(),
                    medico.getApellidos(),
                    medico.getDni(),
                    medico.getEmail(),
                    medico.getEspecialidad(),
                    medico.getFechaGraduacion(),
                    medico.getFechaIncorporacion(),
                    medico.getCmp()
                ))
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(medicoDTOs);
    }
    
    
 @GetMapping("/buscar")
    public ResponseEntity<List<MedicoDto>> buscarPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Page<Medico> medicosPage = medicoService.buscarPorNombre(nombre, page, size);
        
        List<MedicoDto> medicoDTOs = medicosPage.getContent().stream()
                .map(medico -> new MedicoDto(
                     medico.getId(),
                    medico.getNombre(),
                    medico.getApellidos(),
                    medico.getDni(),
                    medico.getEmail(),
                    medico.getEspecialidad(),
                    medico.getFechaGraduacion(),
                    medico.getFechaIncorporacion(),
                    medico.getCmp()
                ))
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(medicoDTOs);
    }
}
