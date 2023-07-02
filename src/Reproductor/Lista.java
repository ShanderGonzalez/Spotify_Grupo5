/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reproductor;

import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author tandr
 */
public class Lista {
    private List<String[]> lista;
    private Connection conexion;

    public Lista() {
        lista = new ArrayList<>();
    }
    
    public Lista(List<String[]> lista) {
        this.lista = lista;
    }


    public List<String[]> getLista() {
        return lista;
    }

    public void setLista(List<String[]> lista) {
        this.lista = lista;
    }
    public void crearLista(Connection conexion,String nombreLista, int idUsuario) {
        // Insertar la lista de reproducción en la base de datos
        String insertQuery = "INSERT INTO LISTADEREPRODUCCION (ID_USUARIO, NOMBRE_LISTA) VALUES (?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(insertQuery)) {
            statement.setInt(1, idUsuario);
            statement.setString(2, nombreLista);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // Obtener el ID_LISTA generado automáticamente
        int idLista = -1;
        String selectQuery = "SELECT ID_LISTA FROM LISTADEREPRODUCCION WHERE NOMBRE_LISTA = ? AND ID_USUARIO = ?";

        try (PreparedStatement statement = conexion.prepareStatement(selectQuery)) {
            statement.setString(1, nombreLista);
            statement.setInt(2, idUsuario);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                idLista = resultSet.getInt("ID_LISTA");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        // Agregar la lista de reproducción a la instancia de Lista
        String[] listaReproduccion = { Integer.toString(idLista), nombreLista };
    }
    
    public void mostrarListasReproduccion(Connection conexion, int idUsuario) {
    // Consulta para obtener las listas de reproducción del usuario
    String selectQuery = "SELECT ID_LISTA, NOMBRE_LISTA FROM LISTADEREPRODUCCION WHERE ID_USUARIO = ?";

    try (PreparedStatement statement = conexion.prepareStatement(selectQuery)) {
        statement.setInt(1, idUsuario);
        ResultSet resultSet = statement.executeQuery();

        int numeroLista = 1;
        while (resultSet.next()) {
            int idLista = resultSet.getInt("ID_LISTA");
            String nombreLista = resultSet.getString("NOMBRE_LISTA");

            System.out.println(numeroLista + ". " + nombreLista);
            numeroLista++;
        }

        if (numeroLista == 1) {
            System.out.println("No tienes listas de reproducción.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
