package com.clinica.clinica.controller;

import com.clinica.clinica.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.clinica.clinica.dto.entrada.turno.TurnoEntradaDto;
import com.clinica.clinica.dto.salida.paciente.PacienteSalidaDto;
import com.clinica.clinica.dto.salida.turno.TurnoSalidaDto;
import com.clinica.clinica.exceptions.BadRequestException;
import com.clinica.clinica.exceptions.ResourceNotFoundException;
import com.clinica.clinica.service.ITurnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private ITurnoService turnoService;

    @Autowired
    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    //POST
    @Operation(summary = "Registro de un nuevo TURNO")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Turno guardado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TurnoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<TurnoSalidaDto> registrarTurno(@Valid @RequestBody TurnoEntradaDto turnoEntradaDto) throws BadRequestException {
        return new ResponseEntity<>(turnoService.registrarTurno(turnoEntradaDto), HttpStatus.CREATED);
    }

    //PUT
    @Operation(summary = "Actualización de un turno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "turno actualizado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TurnoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "turno no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "UServer error",
                    content = @Content)
    })
    @PutMapping("actualizar")
    public ResponseEntity<TurnoSalidaDto> actualizarTurno(@Valid @RequestBody TurnoModificacionEntradaDto turnoModificacionEntradaDto) throws ResourceNotFoundException {
        return new ResponseEntity<>(turnoService.modificarTurno(turnoModificacionEntradaDto), HttpStatus.OK);
    }

    //GET
    @Operation(summary = "Búsqueda de un turno por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "turno obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TurnoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "turno no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<TurnoSalidaDto> obtenerTurnoPorId(@PathVariable Long id) {
        return new ResponseEntity<>(turnoService.buscarTurnoPorId(id), HttpStatus.OK);
    }

    //GET
    @Operation(summary = "Listado de todos los turnos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de turnos obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TurnoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping()
    public ResponseEntity<List<TurnoSalidaDto>> listarTurnos() {
        return new ResponseEntity<>(turnoService.listarTurnos(), HttpStatus.OK);
    }

    //DELETE
    @Operation(summary = "Eliminación de un turno por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "turno eliminado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "turno no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return new ResponseEntity<>("Turno eliminado correctamente", HttpStatus.OK);
    }
}
