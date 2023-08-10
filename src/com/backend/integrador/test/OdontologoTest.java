package com.backend.integrador.test;

import com.backend.integrador.dao.impl.OdontologoDaoH2;
import com.backend.integrador.entity.Odontologo;
import com.backend.integrador.service.OdontoloService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class OdontologoTest {

    OdontoloService odontoloService = new OdontoloService(new OdontologoDaoH2());
    @Test
    public void deberiaAgregarUnOdontologo(){
        Odontologo odont1 = new Odontologo(65971, "Carlos", "Bayet");
        Odontologo OdontologoInsertado = odontoloService.registrarOdontologo(odont1);

        assertNotNull(OdontologoInsertado.getId());
    }


    @Test
    public void deberiaHaberUnaListaNoVacia(){
        List<Odontologo> odontologoTest = odontoloService.listarTodosLosOdontologos();
        assertFalse(odontologoTest.isEmpty());
        assertTrue(odontologoTest.size() >= 1);

    }

}
