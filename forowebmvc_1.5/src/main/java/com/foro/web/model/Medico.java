package com.foro.web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
