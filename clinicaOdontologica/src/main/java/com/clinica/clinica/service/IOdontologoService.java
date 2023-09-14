package com.clinica.clinica.service;

import com.clinica.clinica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.clinica.clinica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.clinica.clinica.dto.salida.odontologo.OdontologoSalidaDto;
import com.clinica.clinica.exceptions.BadRequestException;
import com.clinica.clinica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {
    List<OdontologoSalidaDto> listarOdontologos();


    OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo);

    OdontologoSalidaDto buscarOdontologoPorId(Long id);

    void eliminarOdontologo(Long id) throws ResourceNotFoundException;

    OdontologoSalidaDto actualizarOdontologo(OdontologoModificacionEntradaDto odontologoModificacionEntradaDto) throws ResourceNotFoundException;

}
