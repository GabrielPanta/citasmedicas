package com.hospital.citasmedicas.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.hospital.citasmedicas.model.Rol;
import com.hospital.citasmedicas.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegistroDTO {

    private Integer id;

    private String nombres;

    private String apellidos;

    private String usuario;

    private String imagePerfil;

    private String sexo;

    private LocalDateTime fechaRegistro;

    private String sedeInstitucion;

    private String password;
    
    private List<String> roles;

    public UsuarioRegistroDTO(String nombres, String apellidos, String usuario, String imagePerfil, String sexo, LocalDateTime fechaRegistro, String sedeInstitucion, String password, List<String> roles) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.imagePerfil = imagePerfil;
        this.sexo = sexo;
        this.fechaRegistro = fechaRegistro;
        this.sedeInstitucion = sedeInstitucion;
        this.password = password;
        this.roles = roles;
    }

    



  
    public UsuarioRegistroDTO(Usuario usuario) {
        this.nombres = usuario.getNombres();
        this.apellidos = usuario.getApellidos();
        this.usuario = usuario.getUsuario();
        this.sexo = usuario.getSexo();
        this.fechaRegistro = usuario.getFechaRegistro();
        this.roles = usuario.getRoles().stream()
                     .map(Rol::getNombres)
                     .collect(Collectors.toList());
    }
    public UsuarioRegistroDTO(String usuario) {
        this.usuario = usuario;
    }



}
