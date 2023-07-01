/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Administrador;

import Reproduccion.Lista;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author tandr
 */
public class GestorDeLista {
    private Lista lista ;

    public GestorDeLista() {
    }

    
    public GestorDeLista(Lista lista) {
        this.lista = lista;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }
    
    public List<Integer> agregarListaMusica()
    {
        return null;
       
    }
    public void agregarListaMusica(Connection conexion, int idLista, int idMusica) {
        // Insertar el registro en la tabla MUSICA_LISTA
        String insertQuery = "INSERT INTO MUSICA_LISTA (ID_LISTA, ID_MUSICA) VALUES (?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(insertQuery)) {
            statement.setInt(1, idLista);
            statement.setInt(2, idMusica);
            statement.executeUpdate();
            System.out.println("Música agregada a la lista correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }
    public ArrayList<String[]> obtenerListasReproduccion(Connection conexion,int idUsuario) {
        ArrayList<String[]> listas = new ArrayList<>();

        // Consultar las listas de reproducción del usuario en la base de datos
        String query = "SELECT ID_LISTA, NOMBRE_LISTA FROM LISTADEREPRODUCCION WHERE ID_USUARIO = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, idUsuario);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idLista = resultSet.getInt("ID_LISTA");
                String nombreLista = resultSet.getString("NOMBRE_LISTA");
                String[] lista = { Integer.toString(idLista), nombreLista };
                listas.add(lista);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listas;
}

    
    public void mostrarListasReproduccion(ArrayList<String[]> listas) {
            if (listas.isEmpty()) {
                System.out.println("No existen listas de reproducción.");
            } else {
                System.out.println("Listas de Reproducción:");
                int numeroLista = 1;

                for (String[] lista : listas) {
                    String nombreLista = lista[1];
                    System.out.println(numeroLista + ". " + nombreLista);
                    numeroLista++;
            }
        }
    }

    public ArrayList<String[]> obtenerCancionesLista(Connection conexion, int idLista) {
        ArrayList<String[]> canciones = new ArrayList<>();

        // Aquí realizarías la consulta a la base de datos para obtener las canciones de la lista con el ID especificado
        // Utiliza la conexión 'conexion' para ejecutar la consulta y obtener los resultados
        // Luego, puedes almacenar los resultados en el ArrayList 'canciones' como arreglos de cadenas

        // Ejemplo de consulta ficticia
        String query = "SELECT  MUSICA.NOMBRE_MUSICA, RUTA FROM MUSICA INNER JOIN MUSICA_LISTA  ON MUSICA.ID_MUSICA = MUSICA_LISTA.ID_MUSICA WHERE MUSICA_LISTA.ID_LISTA = ?";
        try {
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, idLista);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Obtener los datos de cada canción y almacenarlos en un arreglo de cadenas
                String[] cancion = new String[2];
                cancion[0] = resultSet.getString("NOMBRE_MUSICA");
                cancion[1] = resultSet.getString("RUTA");
                canciones.add(cancion);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return canciones;
    }

    public void mostrarCancionesLista(ArrayList<String[]> canciones) {
        System.out.println("Canciones de la lista:");
        int contador =1;
        for (String[] cancion : canciones) {
            System.out.println(contador +". Título: " + cancion[0]);
            contador++;
        }
    }

    
}
