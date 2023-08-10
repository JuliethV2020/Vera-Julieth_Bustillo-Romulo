package com.backend.integrador.dao.impl;

import com.backend.integrador.dao.H2Connection;
import com.backend.integrador.dao.IDao;
import com.backend.integrador.entity.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private final Logger LOGGER= Logger.getLogger(OdontologoDaoH2.class);
    @Override
    public Odontologo registrar(Odontologo odontologo) {

        Connection connection=null;
        String insert = "INSERT INTO ODONTOLOGOS (NUMEROMATRICULA,NOMBRE,APELLIDO) VALUES(?,?,?)";
        Odontologo odontologo1=null;

        try {
            connection= H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement pr=connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            pr.setInt(1,odontologo.getNumeroMatricula());
            pr.setString(2,odontologo.getNombre());
            pr.setString(3,odontologo.getApellido());
            pr.execute();

            connection.commit();
            odontologo1 = new Odontologo(odontologo.getNumeroMatricula(), odontologo.getNombre(), odontologo.getApellido());
            ResultSet rs= pr.getGeneratedKeys();
                while (rs.next()){
                    odontologo1.setId(rs.getInt(1));
                }
                LOGGER.info("Odontologo guardado: " + odontologo1);


        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Tuvimos un problema");
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }

        return odontologo1;
    }

    @Override
    public List<Odontologo> listar() {
        Connection connection = null;
        List<Odontologo> odontologo = new ArrayList<>();
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement pr = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                odontologo.add(crearObjeto(rs));
            }
            connection.commit();
            LOGGER.info("Listado de todos los odontologos: " + odontologo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Tuvimos un problema");
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return odontologo;

    }


    private Odontologo crearObjeto(ResultSet rs) throws SQLException {

        int id =rs.getInt("id");
        int numeroMatricula =rs.getInt("numeroMatricula");
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");

        return new Odontologo(id, numeroMatricula, nombre, apellido);

    }

}
