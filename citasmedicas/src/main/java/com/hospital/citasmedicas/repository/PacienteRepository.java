package com.hospital.citasmedicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.citasmedicas.model.Paciente;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PacienteRepository extends JpaRepository <Paciente,Long>{
     // Métodos personalizados
    Optional<Paciente> findByDni(String dni);
    
    // Búsqueda por nombre (parcial)
    Page<Paciente> findByNombresContaining(String nombres, Pageable pageable);
}
