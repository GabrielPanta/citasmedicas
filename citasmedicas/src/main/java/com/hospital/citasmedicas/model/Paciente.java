package com.hospital.citasmedicas.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import java.time.LocalDate;
import java.util.List;

//mport com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

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
 
    private String nombres;


    @Column(unique = true)
    private String dni;
    
    private LocalDate fechaNacimiento;

    private String contacto;
    
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "paciente",cascade = CascadeType.ALL, orphanRemoval = true)
   // @JsonManagedReference
    private List<CitaMedica> citas = new ArrayList<>();
}
