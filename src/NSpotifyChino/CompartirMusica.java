/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NSpotifyChino;

import fi.iki.elonen.NanoHTTPD;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tandr
 */
public class CompartirMusica extends NanoHTTPD{
    private Object Lista;
    private Object Musica;
    private static final int PUERTO = 8080;
    public String RUTA_ARCHIVO_MUSICA;
    
    public CompartirMusica(String Ruta) throws IOException {
        super(PUERTO);
        RUTA_ARCHIVO_MUSICA = Ruta;
        try {
            start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
            System.out.println("Servidor iniciado. Accede a http://localhost:8080/ para obtener los enlaces de música.");
        } catch(java.net.SocketException e){
            
        }
        
    }
    
    
    @Override
    public Response serve(IHTTPSession session) {
        String uri = session.getUri();
        if (uri.equals("/")) {
            String enlace = "<a href=\"musica\">Música Compartida</a>";
            String htmlResponse = "<html><body><p>Enlace para compartir música:</p>" + enlace + "</body></html>";
            return newFixedLengthResponse(Response.Status.OK, "text/html", htmlResponse);
        } else if (uri.equals("/musica")) {
            //System.out.println(RUTA_ARCHIVO_MUSICA);
            File archivoMusica = new File(RUTA_ARCHIVO_MUSICA);
            if (archivoMusica.exists() && archivoMusica.isFile()) {
                try {
                    InputStream inputStream = new FileInputStream(archivoMusica);
                    Response response = newFixedLengthResponse(Response.Status.OK, "audio/wav", inputStream, archivoMusica.length());
                    response.addHeader("Content-Disposition", "attachment; filename=\"" + archivoMusica.getName() + "\"");
                    return response;
                }//                    e.printStackTrace();
                catch (FileNotFoundException ex) {
                    Logger.getLogger(CompartirMusica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return newFixedLengthResponse(Response.Status.NOT_FOUND, NanoHTTPD.MIME_PLAINTEXT, "404 Not Found");
    }
   
    
    public void detenerServidor() {
        stop(); //e.printStackTrace();
        System.out.println("Servidor detenido.");
    }

    public Object getLista() {
        return Lista;
    }

    public void setLista(Object Lista) {
        this.Lista = Lista;
    }

    public Object getMusica() {
        return Musica;
    }

    public void setMusica(Object Musica) {
        this.Musica = Musica;
    }
}