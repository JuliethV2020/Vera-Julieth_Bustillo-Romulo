package com.clinica.clinica;

import com.clinica.clinica.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.clinica.clinica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.clinica.clinica.dto.entrada.paciente.DomicilioEntradaDto;
import com.clinica.clinica.dto.entrada.paciente.PacienteEntradaDto;
import com.clinica.clinica.dto.entrada.turno.TurnoEntradaDto;
import com.clinica.clinica.dto.salida.odontologo.OdontologoSalidaDto;
import com.clinica.clinica.dto.salida.paciente.PacienteSalidaDto;
import com.clinica.clinica.dto.salida.turno.TurnoSalidaDto;
import com.clinica.clinica.exceptions.BadRequestException;
import com.clinica.clinica.exceptions.ResourceNotFoundException;
import com.clinica.clinica.service.imple.OdontologoService;
import com.clinica.clinica.service.imple.PacienteService;
import com.clinica.clinica.service.imple.TurnoService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class TurnoServiceTest {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Test
    @Order(1)
    void RegistrarUnNuevoTurno()throws BadRequestException {
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("karolina", "barrera", 10089286, LocalDate.of(2023, 10, 20),
                new DomicilioEntradaDto("santafe", 1543, "privada", "mirador"));
        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);

        OdontologoEntradaDto odontologoEntradaDto=new OdontologoEntradaDto("matricula11","Julian","carrasco");
        OdontologoSalidaDto odontologoSalidaDto=odontologoService.registrarOdontologo(odontologoEntradaDto);

        TurnoEntradaDto turnoEntradaDto =new TurnoEntradaDto(1L,1L, LocalDateTime.of(2023,10,14,5,23));
        TurnoSalidaDto turnoSalidaDto = turnoService.registrarTurno(turnoEntradaDto);

        assertTrue(turnoSalidaDto.getId()>0);
    }


    @Test
    @Order(2)
    void ActualizarElPacienteId2_deberiaLanzarseUnaResourceNotFxceptionundException(){
        TurnoModificacionEntradaDto turnoModificacionEntradaDto = new TurnoModificacionEntradaDto();
        turnoModificacionEntradaDto.setId(2L);
        assertThrows(ResourceNotFoundException.class, () -> turnoService.modificarTurno(turnoModificacionEntradaDto));
    }

    @Test
    @Order(3)
    void BuscarPorIdDeTurnoQueNoExiste(){

        assertNotEquals(4,turnoService.buscarTurnoPorId(3L));
    }
}
