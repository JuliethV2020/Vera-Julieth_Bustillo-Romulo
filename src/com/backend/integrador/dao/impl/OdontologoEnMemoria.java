package com.backend.integrador.dao.impl;

import com.backend.integrador.dao.IDao;
import com.backend.integrador.entity.Odontologo;

import java.util.List;
import org.apache.log4j.Logger;

public class OdontologoEnMemoria implements IDao<Odontologo> {

    private static final Logger LOGGER = Logger.getLogger(OdontologoEnMemoria.class);
    private List<Odontologo> odontologoRepository;

    public void OdontologoDaoEnMemoria(List<Odontologo> odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }


    @Override
    public Odontologo registrar(Odontologo odontologo) {
        odontologoRepository.add(odontologo);
        LOGGER.info("Odontologo Registrado: " + odontologo);
        return odontologo;

    }

    @Override
    public List<Odontologo> listar() {
        LOGGER.info("Listado de todos los odontologos: \n" + odontologoRepository);
        return odontologoRepository;

    }
}
