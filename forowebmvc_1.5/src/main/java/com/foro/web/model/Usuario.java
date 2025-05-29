 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.foro.web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


 @Entity
 @Table(name = "Usuarios", uniqueConstraints = {
         @UniqueConstraint(columnNames = "email"),
         @UniqueConstraint(columnNames = "dni")
 })
 @Data
 @NoArgsConstructor
 @AllArgsConstructor
 public class Usuario {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;

     @Column(name = "nombres")
     private String nombres;

     @Column(name = "apellidos")
     private String apellidos;

     @Column(name = "dni", unique = true, nullable = false, length = 8)
     private String dni;

     @Column(name = "email", unique = true, nullable = false)
     private String email;

     private String sexo;

     private LocalDateTime fechaRegistro;


     private String password;

     @Column(name = "activo")
     private Boolean activo = false;

     // AGREGAR ESTE CAMPO para la verificación por email
     @Column(name = "tokenActivacion")
     private String tokenActivacion;

     @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
     private Paciente paciente;

     @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
     private Medico medico;

     @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
     private List<CitaMedica> citasAgendadas;

     @ManyToMany(fetch = FetchType.EAGER)
     @JoinTable(
             name = "usuarios_roles",
             joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
             inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id")
     )
     private Set<Rol> roles = new HashSet<>();

     // Constructor personalizado para el registro
     public Usuario(String nombres, String apellidos, String dni, String email, String sexo,
                    LocalDateTime fechaRegistro, String password, Set<Rol> roles) {
         this.nombres = nombres;
         this.apellidos = apellidos;
         this.dni = dni;
         this.email = email;
         this.sexo = sexo;
         this.fechaRegistro = fechaRegistro;
         this.password = password;
         this.roles = roles;
         this.activo = false; // Por defecto inactivo hasta verificar email
     }
 }
