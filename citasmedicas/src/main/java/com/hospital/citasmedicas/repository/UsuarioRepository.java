package com.hospital.citasmedicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.citasmedicas.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Usuario findByUsuario(String usuario);
 
    Usuario findByTokenActivacion(String tokenActivacion);
    
    boolean existsByUsuario(String usuario);

}
