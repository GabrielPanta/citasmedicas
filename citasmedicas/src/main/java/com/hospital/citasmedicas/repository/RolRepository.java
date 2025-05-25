package com.hospital.citasmedicas.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hospital.citasmedicas.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByNombres(String nombres);
  boolean existsByNombres(String nombres); 
    
}

