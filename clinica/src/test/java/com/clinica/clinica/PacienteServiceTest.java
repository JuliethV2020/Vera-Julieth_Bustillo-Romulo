package com.clinica.clinica;

import com.clinica.clinica.dto.entrada.paciente.DomicilioEntradaDto;
import com.clinica.clinica.dto.entrada.paciente.PacienteEntradaDto;
import com.clinica.clinica.dto.salida.paciente.PacienteSalidaDto;
import com.clinica.clinica.exceptions.ResourceNotFoundException;
import com.clinica.clinica.service.imple.PacienteService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void registrarPacienteConApellidoNull(){
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("karolina", null, 10089286, LocalDate.of(2023, 10, 20),
                new DomicilioEntradaDto("santafe", 1543, "privada", "mirador"));

        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);
      assertNull(pacienteEntradaDto.getApellido());
    }


    @Test
    @Order(2)
    void registrarPacienteConTodosLosDatosDeligenciadosDeId1(){
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("karolina", "cargas", 10089286, LocalDate.of(2023, 10, 20),
                new DomicilioEntradaDto("santafe", 1543, "privada", "mirador"));

        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);
        assertEquals(1, pacienteSalidaDto.getId());
    }
    @Test
    @Order(3)
    void EliminarPacienteExistenteDeId() throws ResourceNotFoundException {
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("ivan", "rodri", 124567, LocalDate.of(2023, 10, 20),
                new DomicilioEntradaDto("san pedro", 15443, "privada", "mirador"));

        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);

        pacienteService.eliminarPaciente(1L);
        assertEquals(1,pacienteSalidaDto.getId());
    }

}
