package com.hospital.citasmedicas.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.hospital.citasmedicas.dto.UsuarioRegistroDTO;
import com.hospital.citasmedicas.model.Usuario;

public interface UsuarioServicio extends UserDetailsService {

    public Usuario GuardarUsuario(UsuarioRegistroDTO registroDTO);

    boolean confirmarCuenta(String token);

    boolean existeEmail(String email);

     public Usuario obtenerUsuarioActual();
    
}
