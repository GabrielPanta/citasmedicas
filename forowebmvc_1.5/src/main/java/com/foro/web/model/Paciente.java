package com.foro.web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    private List<CitaMedica> citas = new ArrayList<>();
}
