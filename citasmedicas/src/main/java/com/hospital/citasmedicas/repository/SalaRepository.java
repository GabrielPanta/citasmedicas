/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.hospital.citasmedicas.repository;

import com.hospital.citasmedicas.model.Sala;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Carlos Abad
 */
public interface SalaRepository extends JpaRepository<Sala, Long> {
    Optional<Sala> findByNumeroSala(Integer numeroSala);
}
