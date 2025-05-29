package com.foro.web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaMedica {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    private LocalDate fecha;

 
    private LocalTime hora;

    private String motivo;
 
    private String estado ;

@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")

    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")

    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


}
