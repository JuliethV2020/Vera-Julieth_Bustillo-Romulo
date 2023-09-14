package com.clinica.clinica;

import com.clinica.clinica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.clinica.clinica.dto.salida.odontologo.OdontologoSalidaDto;
import com.clinica.clinica.exceptions.ResourceNotFoundException;
import com.clinica.clinica.service.imple.OdontologoService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class OdontoloServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void registarOdontologoConNombreJulianYMatriculaMayorQue10(){
        OdontologoEntradaDto odontologoEntradaDto=new OdontologoEntradaDto("matricula11","Julian","carrasco");
        OdontologoSalidaDto odontologoSalidaDto=odontologoService.registrarOdontologo(odontologoEntradaDto);
        assertEquals("Julian",odontologoSalidaDto.getNombre());
        assertTrue(odontologoSalidaDto.getMatricula().length()>10);
    }

    @Test
    @Order(2)
    void listarTodosLosOdontologoRegistrados(){
        OdontologoEntradaDto odontologoEntradaDto=new OdontologoEntradaDto("matricula11","Julian","carrasco");
        OdontologoSalidaDto odontologoSalidaDto=odontologoService.registrarOdontologo(odontologoEntradaDto);

        assertTrue(odontologoService.listarOdontologos().size()>0);
    }


    @Test
    @Order(3)
    void IntentarEliminarUnOdontologoYaEliminado_deberiaLanzarseUnResourceNotFoundException(){
        try{
            odontologoService.eliminarOdontologo(1L);
        } catch (Exception e){
            e.printStackTrace();
        }
        assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(1L));
    }



}
