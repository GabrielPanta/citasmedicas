package com.hospital.citasmedicas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.citasmedicas.model.Medico;
import com.hospital.citasmedicas.repository.MedicoRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class MedicoService {

    @Autowired
    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository){
         this.medicoRepository=medicoRepository;
    }

   public Medico registrarMedico(Medico medico) {
        if (medico.getCmp() == null || medico.getCmp().isBlank()) {
            throw new IllegalArgumentException("El CMP es obligatorio");
        }
        if (medico.getNombre() == null || medico.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del médico es obligatorio");
        }
        return medicoRepository.save(medico);
    }

    public List <Medico>obtenerTodos(){
        return medicoRepository.findAll();
    }
    
    
    
    public Optional<Medico> buscarPorId(Long id) {
        return medicoRepository.findById(id);
    }
    
    public Optional<Medico> buscarPorCmp(String cmp) {
        return medicoRepository.findByCmp(cmp);
    }
    
    public Medico actualizarMedico(Medico medico) {
        if (!medicoRepository.existsById(medico.getId())) {
            throw new IllegalArgumentException("No existe un médico con el ID: " + medico.getId());
        }
        return medicoRepository.save(medico);
    }
    
    public void eliminarMedico(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe un médico con el ID: " + id);
        }
        medicoRepository.deleteById(id);
    }
    
    public Page<Medico> obtenerMedicosPaginados(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return medicoRepository.findAll(pageable);
    }
    
    public Page<Medico> buscarPorEspecialidad(String especialidad, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return medicoRepository.findByEspecialidad(especialidad, pageable);
    }
     public Page<Medico> buscarPorNombre(String nombre, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return medicoRepository.findByNombreContaining(nombre, pageable);
    }
}
