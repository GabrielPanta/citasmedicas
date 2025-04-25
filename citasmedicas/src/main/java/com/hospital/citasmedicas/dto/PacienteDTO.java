package com.hospital.citasmedicas.dto;

import com.hospital.citasmedicas.model.Paciente;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PacienteDTO {
    private Long id;
    private String nombre;
    private String apellidos;
    private String dni;
    private Paciente.Genero genero;
    private String email;
    private String direccion;
    private String telefono;
    private Paciente.GravedadAfeccion gravedadAfeccion;
    private LocalDate fechaNacimiento;
    private LocalDateTime fechaIngreso;

  
    public PacienteDTO(Long id, String nombre, String apellidos, String dni, Paciente.Genero genero,
                      String email, String direccion, String telefono, 
                      Paciente.GravedadAfeccion gravedadAfeccion, LocalDate fechaNacimiento,
                      LocalDateTime fechaIngreso) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.genero = genero;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.gravedadAfeccion = gravedadAfeccion;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
    }

  
// Getters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDni() {
        return dni;
    }

    public Paciente.Genero getGenero() {
        return genero;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public Paciente.GravedadAfeccion getGravedadAfeccion() {
        return gravedadAfeccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setGenero(Paciente.Genero genero) {
        this.genero = genero;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setGravedadAfeccion(Paciente.GravedadAfeccion gravedadAfeccion) {
        this.gravedadAfeccion = gravedadAfeccion;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    
}

