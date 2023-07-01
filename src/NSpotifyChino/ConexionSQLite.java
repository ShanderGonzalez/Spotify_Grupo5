/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NSpotifyChino;

/**
 *
 * @author tandr
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQLite {
    private Connection conexion;
    private static final String URL = "jdbc:sqlite:C:/Users/usuario/Desktop/spotify.db";

    public Connection conectar() {
        try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection(URL);
            System.out.println("Conexion exitosa a la base de datos SQLite");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar a la base de datos SQLite: " + e.getMessage());
        }
        return conexion;
    }

    public void desconectar() {
        try {
            if (conexion != null) {
                conexion.close();
                System.out.println("Desconexion exitosa de la base de datos SQLite");
            }
        } catch (SQLException e) {
            System.out.println("Error al desconectar de la base de datos SQLite: " + e.getMessage());
        }
    }
}