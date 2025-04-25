package com.hospital.citasmedicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.citasmedicas.model.CitaMedica;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CitaMedicaRepository extends JpaRepository<CitaMedica,Long>{
List<CitaMedica> findByPacienteId(Long pacienteId);
    
    List<CitaMedica> findByMedicoId(Long medicoId);
    
    Page<CitaMedica> findByFechaAndEstado(LocalDate fecha, String estado, Pageable pageable);
}
