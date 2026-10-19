package com.aprendec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aprendec.conexion.Conexion;
import com.aprendec.model.Persona;

public class PersonaDAO {
    private Connection connection;
    private PreparedStatement statement;
    private boolean estadoOperacion;

    // guardar persona
    public boolean guardar(Persona persona) throws SQLException {
        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            connection.setAutoCommit(false);
            sql = "INSERT INTO personas (id, nombre, apellidos, edad, email, fecha_crear, fecha_actualizar) VALUES(?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);

            statement.setString(1, null);
            statement.setString(2, persona.getNombre());
            statement.setString(3, persona.getApellidos());
            statement.setInt(4, persona.getEdad());
            statement.setString(5, persona.getEmail());
            statement.setDate(6, persona.getFechaCrear());
            statement.setDate(7, persona.getFechaActualizar());

            estadoOperacion = statement.executeUpdate() > 0;

            connection.commit();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }

        return estadoOperacion;
    }

    // editar persona
    public boolean editar(Persona persona) throws SQLException {
        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();
        try {
            connection.setAutoCommit(false);
            sql = "UPDATE personas SET nombre=?, apellidos=?, edad=?, email=?, fecha_actualizar=? WHERE id=?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, persona.getNombre());
            statement.setString(2, persona.getApellidos());
            statement.setInt(3, persona.getEdad());
            statement.setString(4, persona.getEmail());
            statement.setDate(5, persona.getFechaActualizar());
            statement.setInt(6, persona.getId());

            estadoOperacion = statement.executeUpdate() > 0;
            connection.commit();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }

        return estadoOperacion;
    }

    // eliminar persona
    public boolean eliminar(int idPersona) throws SQLException {
        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();
        try {
            connection.setAutoCommit(false);
            sql = "DELETE FROM personas WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idPersona);

            estadoOperacion = statement.executeUpdate() > 0;
            connection.commit();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }

        return estadoOperacion;
    }

    // obtener lista de personas
    public List<Persona> obtenerPersonas() throws SQLException {
        ResultSet resultSet = null;
        List<Persona> listaPersonas = new ArrayList<>();

        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            sql = "SELECT * FROM personas";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Persona p = new Persona();
                p.setId(resultSet.getInt(1));
                p.setNombre(resultSet.getString(2));
                p.setApellidos(resultSet.getString(3));
                p.setEdad(resultSet.getInt(4));
                p.setEmail(resultSet.getString(5));
                p.setFechaCrear(resultSet.getDate(6));
                p.setFechaActualizar(resultSet.getDate(7));
                listaPersonas.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaPersonas;
    }

    // obtener persona
    public Persona obtenerPersona(int idPersona) throws SQLException {
        ResultSet resultSet = null;
        Persona p = new Persona();

        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            sql = "SELECT * FROM personas WHERE id =?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idPersona);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                p.setId(resultSet.getInt(1));
                p.setNombre(resultSet.getString(2));
                p.setApellidos(resultSet.getString(3));
                p.setEdad(resultSet.getInt(4));
                p.setEmail(resultSet.getString(5));
                p.setFechaCrear(resultSet.getDate(6));
                p.setFechaActualizar(resultSet.getDate(7));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    }

    // obtener conexion pool
    private Connection obtenerConexion() throws SQLException {
        return Conexion.getConnection();
    }

}