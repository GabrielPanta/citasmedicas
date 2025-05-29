/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.foro.web.repository;

import com.foro.web.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Buscar por email (para login y verificaciones)
    Usuario findByEmail(String email);

    // Buscar por DNI (para login)
    Usuario findByDni(String dni);

    // Buscar por DNI o email (útil para login con cualquiera de los dos)
    @Query("SELECT u FROM Usuario u WHERE u.dni = :identifier OR u.email = :identifier")
    Usuario findByDniOrEmail(@Param("identifier") String identifier);

    // Para verificación de token de activación - necesitamos agregar este campo al modelo Usuario
    Usuario findByTokenActivacion(String tokenActivacion);

    // Verificar si existe email
    boolean existsByEmail(String email);

    // Verificar si existe DNI
    boolean existsByDni(String dni);

    // Verificar si existe DNI o email (útil para validaciones de registro)
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Usuario u WHERE u.dni = :identifier OR u.email = :identifier")
    boolean existsByDniOrEmail(@Param("identifier") String identifier);

}
