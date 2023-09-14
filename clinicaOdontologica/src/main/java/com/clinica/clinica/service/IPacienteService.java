package com.clinica.clinica.service;

import com.clinica.clinica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.clinica.clinica.dto.entrada.paciente.PacienteEntradaDto;
import com.clinica.clinica.dto.salida.paciente.PacienteSalidaDto;
import com.clinica.clinica.exceptions.BadRequestException;
import com.clinica.clinica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {
    List<PacienteSalidaDto> listarPacientes();

    PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente);

    PacienteSalidaDto buscarPacientePorId(Long id);

    void eliminarPaciente(Long id) throws ResourceNotFoundException;

    PacienteSalidaDto modificarPaciente(PacienteModificacionEntradaDto pacienteModificado) throws ResourceNotFoundException;


}