package com.hospital.citasmedicas.model;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import java.util.List;

//import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Medico")
@AllArgsConstructor
public class Medico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     @Column(nullable = false, length = 100)
    private String nombre; 

   @Column(nullable = false, length = 50)
    private String especialidad; 

       @Column(nullable = false, unique = true, length = 20)
    private String cmp;             // número de colegiatura
    @OneToMany(mappedBy = "medico",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<CitaMedica> citas =  new ArrayList<>();
}
