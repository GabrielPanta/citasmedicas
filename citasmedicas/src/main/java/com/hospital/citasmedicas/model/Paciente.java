package com.hospital.citasmedicas.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import java.time.LocalDate;
import java.util.List;

//mport com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(nullable = false, length = 100)
    private String apellidos;
    
    @Column(nullable = false, unique = true, length = 20)
    private String dni;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;
    
    @Column(unique = true)
    private String email;
    
    private String direccion;
    
    private String telefono;
    
    @Enumerated(EnumType.STRING)
    private GravedadAfeccion gravedadAfeccion;
    
    private LocalDate fechaNacimiento;
    
    private LocalDateTime fechaIngreso;
    
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CitaMedica> citas = new ArrayList<>();
    
    public enum Genero {
        MASCULINO, FEMENINO, NO_ESPECIFICADO
    }
    
    public enum GravedadAfeccion {
        ASINTOMATICA, LEVE, MODERADA, GRAVE, CRITICA
    }
}
