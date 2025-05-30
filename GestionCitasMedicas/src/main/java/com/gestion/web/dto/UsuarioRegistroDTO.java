/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.gestion.web.dto;

import com.gestion.web.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 * @author Carlos Abad
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRegistroDTO {

    private String nombres;
    private String apellidos;
    private String dni;
    private String email;
    private String sexo;
    private String password;

    // Constructor para cuando necesites convertir desde Usuario a DTO (perfil, etc.)
    public UsuarioRegistroDTO(Usuario usuario) {
        this.nombres = usuario.getNombres();
        this.apellidos = usuario.getApellidos();
        this.dni = usuario.getDni();
        this.email = usuario.getEmail();
        this.sexo = usuario.getSexo();
        // No incluimos password por seguridad
    }


}
