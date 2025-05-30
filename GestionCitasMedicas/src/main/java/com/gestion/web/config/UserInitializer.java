/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.gestion.web.config;

import com.gestion.web.model.Rol;
import com.gestion.web.model.Usuario;
import com.gestion.web.repository.RolRepository;
import com.gestion.web.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Carlos Abad
 */
@Component
public class UserInitializer {
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private RolRepository rolRepository;
    
    @PostConstruct
    @Transactional
    public void init() {

        Rol rolUsuario = rolRepository.findByNombres("Rol_Usuario").orElse(null);
        if (rolUsuario == null) {
            rolUsuario = new Rol();
            rolUsuario.setNombres("Rol_Usuario");
            rolUsuario = rolRepository.save(rolUsuario);
        }

        Rol rolModerador = rolRepository.findByNombres("Moderador").orElse(null);
        if (rolModerador == null) {
            rolModerador = new Rol();
            rolModerador.setNombres("Moderador");
            rolModerador = rolRepository.save(rolModerador);
        }

        Rol rolAdministrador = rolRepository.findByNombres("Administrador").orElse(null);
        if (rolAdministrador == null) {
            rolAdministrador = new Rol();
            rolAdministrador.setNombres("Administrador");
            rolAdministrador = rolRepository.save(rolAdministrador);
        }


        if (!usuarioRepository.existsByEmail("moderador@gmail.com")) {
            Usuario moderador = new Usuario();


            moderador.setNombres("Usuario");
            moderador.setApellidos("Moderador");
            moderador.setDni("12345678");
            moderador.setEmail("moderador@gmail.com");
            moderador.setSexo("M");


            moderador.setFechaRegistro(LocalDateTime.now());


            moderador.setActivo(true);



            String rawPassword = "mod1234";
            moderador.setPassword(passwordEncoder.encode(rawPassword));


            moderador.setRoles(new HashSet<>());
            moderador.getRoles().add(rolModerador);


            usuarioRepository.save(moderador);
            System.out.println("Usuario Moderador creado exitosamente");
        }


    }
}
