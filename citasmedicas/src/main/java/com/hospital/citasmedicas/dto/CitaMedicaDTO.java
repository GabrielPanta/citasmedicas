package com.hospital.citasmedicas.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class CitaMedicaDTO {
    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private String motivo;
    private String estado;
    private Long pacienteId;
    private Long medicoId;
    private Long salaId; 

      public CitaMedicaDTO(Long id, LocalDate fecha, LocalTime hora, String motivo, String estado, 
                         Long pacienteId, Long medicoId, Long salaId) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.estado = estado;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.salaId = salaId;
    }
    // Getters
    public Long getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getEstado() {
        return estado;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public Long getSalaId() {
        return salaId;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public void setSalaId(Long salaId) {
        this.salaId = salaId;
    }
}
