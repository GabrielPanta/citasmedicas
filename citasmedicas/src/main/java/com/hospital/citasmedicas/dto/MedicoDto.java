package com.hospital.citasmedicas.dto;

import com.hospital.citasmedicas.model.Medico;
import java.time.LocalDate;
public class MedicoDto {
    private Long id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private Medico.Especialidad especialidad;
    private LocalDate fechaGraduacion;
    private LocalDate fechaIncorporacion;
    private String cmp;

 
    public MedicoDto(Long id, String nombre, String apellidos, String dni, String email, 
                     Medico.Especialidad especialidad, LocalDate fechaGraduacion, 
                     LocalDate fechaIncorporacion, String cmp) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
        this.especialidad = especialidad;
        this.fechaGraduacion = fechaGraduacion;
        this.fechaIncorporacion = fechaIncorporacion;
        this.cmp = cmp;
    }

    

    public MedicoDto() {}

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

    public String getEmail() {
        return email;
    }

    public Medico.Especialidad getEspecialidad() {
        return especialidad;
    }

    public LocalDate getFechaGraduacion() {
        return fechaGraduacion;
    }

    public LocalDate getFechaIncorporacion() {
        return fechaIncorporacion;
    }

    public String getCmp() {
        return cmp;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEspecialidad(Medico.Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public void setFechaGraduacion(LocalDate fechaGraduacion) {
        this.fechaGraduacion = fechaGraduacion;
    }

    public void setFechaIncorporacion(LocalDate fechaIncorporacion) {
        this.fechaIncorporacion = fechaIncorporacion;
    }

    public void setCmp(String cmp) {
        this.cmp = cmp;
    }
}
