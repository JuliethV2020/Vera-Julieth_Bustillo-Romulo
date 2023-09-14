package com.clinica.clinica.service;


import com.clinica.clinica.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.clinica.clinica.dto.entrada.turno.TurnoEntradaDto;
import com.clinica.clinica.dto.salida.turno.TurnoSalidaDto;
import com.clinica.clinica.exceptions.BadRequestException;
import com.clinica.clinica.exceptions.ResourceNotFoundException;

import java.lang.module.ResolutionException;
import java.util.List;

public interface ITurnoService {
    TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) throws BadRequestException;

    List<TurnoSalidaDto> listarTurnos();

    TurnoSalidaDto buscarTurnoPorId(Long id);

    void eliminarTurno(Long id) throws ResourceNotFoundException;

    TurnoSalidaDto modificarTurno(TurnoModificacionEntradaDto turnoModificacionEntradaDto) throws ResourceNotFoundException;

}
