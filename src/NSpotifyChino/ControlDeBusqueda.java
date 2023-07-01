/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NSpotifyChino;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author tandr
 */
public class ControlDeBusqueda{
    
    private String tipoDeBusqueda;
    private Connection conexion;

    public ControlDeBusqueda(String tipoDeBusqueda, Connection conexion) {
        this.tipoDeBusqueda = tipoDeBusqueda;
        this.conexion = conexion;
    }

    
   

    public String getTipoDeBusqueda() {
        return tipoDeBusqueda;
    }

    public void setTipoDeBusqueda(String tipoDeBusqueda) {
        this.tipoDeBusqueda = tipoDeBusqueda;
    }
    
    
    public List<String[]> BuscarMusicaArtista(String tipoBusqueda,Connection conexion,String cadenaBuscar) {
       
       List<String[]> resultados = new ArrayList<>();
       
       if(tipoBusqueda == "Titulo")
       {
           try {
               String consultaMusica = "SELECT NOMBRE_MUSICA, RUTA " +
                                       "FROM MUSICA " +
                                       "WHERE NOMBRE_MUSICA LIKE ?";
                                      
               
               PreparedStatement statementMusica = conexion.prepareStatement(consultaMusica);
               statementMusica.setString(1, "%" + cadenaBuscar + "%");
               ResultSet resultadoMusica = statementMusica.executeQuery();
               
               while (resultadoMusica.next()) {
                   String nombreMusica = resultadoMusica.getString("NOMBRE_MUSICA");
                   String rutaMusica = resultadoMusica.getString("RUTA");
                   String[] resultado = {nombreMusica, rutaMusica};
                   resultados.add(resultado);
                   
               }
           } catch (SQLException ex) {
               Logger.getLogger(ControlDeBusqueda.class.getName()).log(Level.SEVERE, null, ex);
           }
           
       }
       else{
           try {
               String consultaArtista = "SELECT MUSICA.NOMBRE_MUSICA, MUSICA.RUTA\n" +
                       "FROM MUSICA\n" +
                       "JOIN ALBUMES ON MUSICA.ID_ALBUM = ALBUMES.ID_ALBUM\n" +
                       "JOIN ARTISTA ON ALBUMES.ID_ARTISTA = ARTISTA.ID_ARTISTA\n" +
                       "WHERE ARTISTA.NOMBRE_ARTISTA LIKE ?";
               
               PreparedStatement statementArtista = conexion.prepareStatement(consultaArtista);
               statementArtista.setString(1, "%" + cadenaBuscar + "%");
               ResultSet resultadoArtista = statementArtista.executeQuery();
               
               while (resultadoArtista.next()) {
                   String nombreMusica = resultadoArtista.getString("NOMBRE_MUSICA");
                   String rutaMusica = resultadoArtista.getString("RUTA");
                   String[] resultado = {nombreMusica, rutaMusica};
                   resultados.add(resultado);
                   
               }
           } catch (SQLException ex) {
               Logger.getLogger(ControlDeBusqueda.class.getName()).log(Level.SEVERE, null, ex);
           }
           
       }
        return resultados;

    }
}
