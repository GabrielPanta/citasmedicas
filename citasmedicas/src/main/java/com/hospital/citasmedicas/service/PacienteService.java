package com.hospital.citasmedicas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.citasmedicas.model.Paciente;
import com.hospital.citasmedicas.repository.PacienteRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class PacienteService {
    
     @Autowired
    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente registrarPaciente(Paciente paciente) {
        if (paciente.getDni() == null || paciente.getDni().isBlank()) {
            throw new IllegalArgumentException("El DNI es obligatorio");
        }
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> obtenerTodos() {
        return pacienteRepository.findAll();
    }
    
       public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepository.findById(id);
    }
       
      public Paciente actualizarPaciente(Paciente paciente) {
        if (!pacienteRepository.existsById(paciente.getId())) {
            throw new IllegalArgumentException("No existe un paciente con el ID: " + paciente.getId());
        }
        return pacienteRepository.save(paciente);
    } 
       
     public void eliminarPaciente(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe un paciente con el ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }  
     public Page<Paciente> obtenerPacientesPaginados(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return pacienteRepository.findAll(pageable);
    }
    
}
