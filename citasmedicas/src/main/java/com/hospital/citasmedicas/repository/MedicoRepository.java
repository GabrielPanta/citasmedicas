package com.hospital.citasmedicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.citasmedicas.model.Medico;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MedicoRepository extends JpaRepository<Medico,Long> {
 Optional<Medico> findByCmp(String cmp);
    
    Page<Medico> findByEspecialidad(String especialidad, Pageable pageable);
    
        Page<Medico> findByNombreContaining(String nombre, Pageable pageable);
}
