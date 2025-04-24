package com.hospital.citasmedicas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hospital.citasmedicas.model.Paciente;
import com.hospital.citasmedicas.repository.PacienteRepository;
import com.hospital.citasmedicas.service.PacienteService;

@ExtendWith(MockitoExtension.class)
class CitasmedicasApplicationTests {

    private Paciente paciente;

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteService pacienteService;

    @BeforeEach
    void setUp() {
        paciente = new Paciente(null, "Juan", "47401107", LocalDate.of(1990, 5, 10), "987654321", null);
    }

    @Test
    void debeRegistrarPaciente() {

        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        Paciente registrado = pacienteService.registrarPaciente(paciente);

        assertNotNull(registrado);

        assertEquals("Juan", registrado.getNombres());

        verify(pacienteRepository, times(1)).save(paciente);
    }

    @Test
    void debeListarPacientes() {
        when(pacienteRepository.findAll()).thenReturn(List.of(paciente));

        List<Paciente> pacientes = pacienteService.obtenerTodos();

        assertEquals(1, pacientes.size());
        verify(pacienteRepository, times(1)).findAll();
    }

    @Test
    void noDebeRegistrarPacienteSinDni() {
        paciente.setDni(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pacienteService.registrarPaciente(paciente);
        });

        assertEquals("El DNI es obligatorio", exception.getMessage());
        verify(pacienteRepository, never()).save(any());
    }
}
