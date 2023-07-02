/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Usuario;

import NSpotifyChino.CompartirMusica;
import NSpotifyChino.ConexionSQLite;
import NSpotifyChino.ControlDeBusqueda;
import Administrador.GestorDeLista;
import Reproductor.Lista;
import Reproductor.PanelDeReproduccion;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author tandr
 */
public class GUI {

    /**
     * @param args the command line arguments
     */
        
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean sesionIniciada = false;
        ConexionSQLite conexionSQLite = new ConexionSQLite();
        Connection conexion = conexionSQLite.conectar();
        Autentificar usuario = new Autentificar();
        PanelDeReproduccion reproducir = new PanelDeReproduccion();
        
       System.out.println("                                ##       ##       ###\n" +
                          "                              ##               ## ##\n" +
                          "  #####   ######   ##  ##    #####    ###       #      ##  ##\n" +
                          " ##        ##  ##  ##  ##     ##       ##     ####     ##  ##\n" +
                          "  #####    ##  ##  ##  ##     ##       ##      ##      ##  ##\n" +
                          "      ##   #####   ##  ##     ## ##    ##      ##       #####\n" +
                          " ######    ##       ######     ###    ####    ####         ##\n" +
                          "          ####                                         #####");

        System.out.println();
        // Menú principal
        int idUsuarioObtenido=-1;
        Lista lista = new Lista();
       int opcionMenuPrincipal;
        do {
            System.out.println("===== MENU PRINCIPAL =====");
            System.out.println("1. Iniciar sesion");
            System.out.println("2. Crear usuario");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            opcionMenuPrincipal = scanner.nextInt();
            scanner.nextLine(); // Limpiar el salto de línea
            
            
            switch (opcionMenuPrincipal) {
                case 1:
                        espacios();
                    // Lógica para iniciar sesión
                        System.out.println("===== INICIO DE SESION =====");
                        System.out.println("Ingrese el nombre de usuario: ");
                        String Nombre_Usuario = scanner.next();
                        System.out.println("Ingrese la contraseña: ");
                        String contra = scanner.next();

                        // Validar el usuario en la base de datos
                        boolean validaSesion = usuario.validarCliente(conexion,Nombre_Usuario,contra);
                        
                        if (validaSesion) {
                            
                            ///SACAR ID USUARIO
                            
                             idUsuarioObtenido = obtenerIdUsuario(conexion,Nombre_Usuario,contra);

                            sesionIniciada = true;
                        } else {
                            System.out.println("*******************************");
                            System.out.println("*                             *");
                            System.out.println("*  El usuario o contraseña    *");
                            System.out.println("*       son incorrectos       *");
                            System.out.println("*                             *");
                            System.out.println("*******************************");
                        }
                    break;
                case 2:
                    espacios();
                    System.out.println("===== REGISTRO DE USUARIO =====");
        
                    System.out.print("Ingrese su nombre de usuario: ");
                    String nombreUsuario = scanner.nextLine();

                    System.out.print("Ingrese su contraseña: ");
                    String contrasenia = scanner.nextLine();

                    System.out.print("Ingrese su correo electronico: ");
                    String correoElectronico = scanner.nextLine();


                    usuario.setNombre_Usuario(nombreUsuario);
                    usuario.setContrasenia(contrasenia);
                    usuario.setCorreo(correoElectronico);
                    try {
                    // Establecer la conexión con la base de datos
                    
                    String sql = "INSERT INTO USUARIO (CONTRASENIA, NOMBRE_USUARIO, CORREO_ELECTRONICO) VALUES (?, ?, ?)";
                    PreparedStatement statement = conexion.prepareStatement(sql);
                    statement.setString(1, usuario.getContrasenia());
                    statement.setString(2, usuario.getNombre_Usuario());
                    statement.setString(3, usuario.getCorreo());

                    int filasInsertadas = statement.executeUpdate();

                    if (filasInsertadas > 0) {
                        
                        System.out.println("Usuario creado exitosamente.");
                         ResultSet generatedKeys = statement.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            idUsuarioObtenido = generatedKeys.getInt(1);
                        }
                    } else {
                        System.out.println("Error al crear el usuario.");
                    }

                    //liberar recursos
                    statement.close();
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                    sesionIniciada = true;
                    break;
                case 0:

                    System.out.println("Gracias por utilizar SPUTIFY. ¡Hasta luego!");
                    break;
                default:

                    System.out.println("Opcion invalida. Por favor, selecciona una opción valida.");
                    break;
            }
            
            System.out.println();
        } while (opcionMenuPrincipal != 0 && !sesionIniciada);
        
        if (sesionIniciada) {

            int opcionMenuUsuario;
            do {
                espacios();
                System.out.println("===== MENU DE USUARIO =====");
                System.out.println("1. Buscar musica");
                System.out.println("2. Crear lista de reproduccion");
                System.out.println("3. Ver listas de Reproduccion");
                System.out.println("4. Compartir Musica");
                System.out.println("0. Cerrar sesion");
                System.out.print("Seleccione una opcion: ");
                opcionMenuUsuario = scanner.nextInt();
                scanner.nextLine(); // Limpiar el salto de línea
                
                switch (opcionMenuUsuario) {
                    case 1:
                            espacios();
                            System.out.println("Has seleccionado la opcion Buscar musica");

                            // Submenú de búsqueda
                            System.out.println("Elige el tipo de busqueda:");
                            System.out.println("1. Por titulo");
                            System.out.println("2. Por artista");

                            // Capturar la opción elegida por el usuario
                            int opcionBusqueda = scanner.nextInt();
                            scanner.nextLine();

                            // Realizar la acción según la opción elegida
                            ControlDeBusqueda tipoBusqueda;
                            switch (opcionBusqueda) {
                                case 1:
                                    espacios();
                                    System.out.println("Has seleccionado busqueda por titulo");
                                    tipoBusqueda = new ControlDeBusqueda("Titulo",conexion);
                                    String titulo =  tipoBusqueda.getTipoDeBusqueda().toString();
                                    
                                    System.out.print("Inserte el titulo a buscar: ");
                                    String tituloABuscar = scanner.nextLine();
                                    
                                    
                                    
                                    List<String[]> listaResultadosTitulo= tipoBusqueda.BuscarMusicaArtista(titulo, conexion, tituloABuscar);
                                    if(!listaResultadosTitulo.isEmpty()){
                                        int numeroMusica = 1 ;
                                        for (String[] resultado : listaResultadosTitulo) {
                                            System.out.println(numeroMusica+". Nombre de la musica: " + resultado[0]);
                                            System.out.println("Ruta: " + resultado[1]);
                                            System.out.println();
                                            numeroMusica++;
                                        }
                                         System.out.print("Seleccione el numero de la musica que desea: ");
                                        int seleccion = scanner.nextInt();

                                        if (seleccion >= 1 && seleccion <= listaResultadosTitulo.size()) {
                                            String[] seleccionado = listaResultadosTitulo.get(seleccion - 1);
                                            System.out.println("Ha seleccionado la siguiente musica:");
                                            System.out.println("Nombre de la musica: " + seleccionado[0]);
                                            System.out.println("Ruta: " + seleccionado[1]);
                                            
                                             reproducir.reproducirMusica(seleccionado[1]);
                                        }
                                        else {
                                                System.out.println("Seleccion invalida.");
                                        }
                                    }
                                    else{
                                        System.out.println("No se encontraron resultados para la busqueda.");
                                    }
                                    break;


                                case 2:
                                    espacios();
                                    System.out.println("Has seleccionado busqueda por artista");
                                    tipoBusqueda = new ControlDeBusqueda("Artista",conexion);
                                    String artista =  tipoBusqueda.getTipoDeBusqueda().toString();
                                    
                                    System.out.println("Inserte el nombre del artista a buscar: ");
                                    String artistaABuscar = scanner.nextLine();
                                    
                                    List<String[]> listaResultadosArtista= tipoBusqueda.BuscarMusicaArtista(artista, conexion, artistaABuscar);
                                  
                                    if(!listaResultadosArtista.isEmpty()){
                                        int numeroMusica = 1 ;
                                       for (String[] resultado : listaResultadosArtista) {
                                            System.out.println(numeroMusica+". Nombre de la musica: " + resultado[0]);
                                            System.out.println("Ruta: " + resultado[1]);
                                            System.out.println();
                                            numeroMusica++;
                                        } 
                                        System.out.print("Seleccione el numero de la musica que desea: ");
                                        int seleccion = scanner.nextInt();

                                        if (seleccion >= 1 && seleccion <= listaResultadosArtista.size()) {
                                            String[] seleccionado = listaResultadosArtista.get(seleccion - 1);
                                            System.out.println("Ha seleccionado la siguiente musica:");
                                            System.out.println("Nombre de la musica: " + seleccionado[0]);
                                            System.out.println("Ruta: " + seleccionado[1]);
                                            
                                            reproducir.reproducirMusica(seleccionado[1]);
                                        }
                                        else {
                                                System.out.println("Seleccion invalida.");
                                        }
                                    }
                                    else{
                                        System.out.println("No se encontraron resultados para la busqueda.");
                                    }
                                    
                                    break;
                                default:
                                    System.out.println("Opcion invalida");
                                    break;
                            }
                            break;
                    case 2:
                        espacios();
                        System.out.println("Has seleccionado la opcion Crear lista de reproduccion");
                         System.out.println("¿Desea crear una lista de reproduccion? (S/N)");
                        String respuesta = scanner.nextLine();

                        if (respuesta.equalsIgnoreCase("S")) {;
                            espacios();
                            System.out.println("¿Cual sera el nombre de esta Lista?");
                            String nombreLista = scanner.next();
                            lista.crearLista(conexion,nombreLista,idUsuarioObtenido);
                        
                         boolean seguirBuscandoTituloArtista = true;
                            while (seguirBuscandoTituloArtista) {
                                System.out.println("Elige la opcion a buscar para poder elegir musica para tu lista:");
                                System.out.println("1. Por titulo");
                                System.out.println("2. Por artista");
                                System.out.println("0. Regresar al menu principal");

                                int opcionBusqueda1 = scanner.nextInt();
                                scanner.nextLine();

                                ControlDeBusqueda tipoBusqueda1;
                                switch (opcionBusqueda1) {
                                    case 1:
                                        espacios();
                                        boolean seguirBuscando = true;
                                    while (seguirBuscando) {    
                                        System.out.println("Has seleccionado busqueda por titulo");
                                        tipoBusqueda1 = new ControlDeBusqueda("Titulo",conexion);
                                        String titulo =  tipoBusqueda1.getTipoDeBusqueda().toString();

                                        System.out.print("Inserte el titulo a buscar: ");
                                        String tituloABuscar = scanner.nextLine();



                                        List<String[]> listaResultadosTitulo= tipoBusqueda1.BuscarMusicaArtista(titulo, conexion, tituloABuscar);
                                        if(!listaResultadosTitulo.isEmpty()){
                                            int numeroMusica = 1 ;
                                            for (String[] resultado : listaResultadosTitulo) {
                                                System.out.println(numeroMusica + ". Nombre de la musica: " + resultado[0]);
                                                System.out.println("Ruta: " + resultado[1]);
                                                System.out.println();
                                                numeroMusica++;
                                            }
                                            
                                            System.out.print("Seleccione el número de la musica que desea agregar a la lista: ");
     
                                            int seleccion = scanner.nextInt();

                                            if (seleccion >= 1 && seleccion <= listaResultadosTitulo.size()) {
                                                String[] seleccionado = listaResultadosTitulo.get(seleccion - 1);
                                                System.out.println("Desea agregar a la lista esta musica(S/N):");
                                                System.out.println("Nombre de la musica: " + seleccionado[0]);
                                                String respuesta1 = scanner.next();

                                                if (respuesta1.equalsIgnoreCase("S")) {
                                                    GestorDeLista gestorDeLista = new GestorDeLista(lista);

                                                    // Acceder a la lista a través del gestorDeLista
    //                                                Lista listaActual

                                                    // Obtener ID_LISTA y ID_MUSICA
                                                    int idMusicaObtenido = obtenerIdMusica(conexion,seleccionado);
                                                    int idListaObtenido = obtenerIdLista(conexion,nombreLista,idUsuarioObtenido);

                                                    gestorDeLista.agregarListaMusica(conexion,idListaObtenido,idMusicaObtenido );
                                                }



                                            }
                                            else {
                                                    System.out.println("Seleccion invalida.");
                                                }
                                                
                                              System.out.println("Deseas seguir buscando para agregar a la lista. \n1.Seguir buscando\n2.No"); 
                                              int buscarAgregar = scanner.nextInt();
                                                if(buscarAgregar == 2) {
                                                seguirBuscando = false;
                                                } 
                                                

                                        }
                                        else{
                                            System.out.println("No se encontraron resultados para la busqueda.");
                                        }
                                    }
                                        break;
                                    case 2:
                                        espacios();
                                        System.out.println("Has seleccionado busqueda por artista");
                                    boolean seguirBuscando1 = true;
                                    while (seguirBuscando1) { 
                                        tipoBusqueda1 = new ControlDeBusqueda("Artista",conexion);
                                        String artista =  tipoBusqueda1.getTipoDeBusqueda().toString();

                                        System.out.println("Inserte el nombre del artista a buscar: ");
                                        String artistaABuscar = scanner.nextLine();

                                        List<String[]> listaResultadosArtista= tipoBusqueda1.BuscarMusicaArtista(artista, conexion, artistaABuscar);

                                        if(!listaResultadosArtista.isEmpty()){
                                            int numeroMusica = 1 ;
                                           for (String[] resultado : listaResultadosArtista) {
                                                System.out.println(numeroMusica+". Nombre de la musica: " + resultado[0]);
                                                System.out.println("Ruta: " + resultado[1]);
                                                System.out.println();
                                                numeroMusica++;
                                            } 
                                            System.out.print("Seleccione el numero de la musica que desea guardar en su lista: ");
                                            int seleccion = scanner.nextInt();

                                            if (seleccion >= 1 && seleccion <= listaResultadosArtista.size()) {
                                                String[] seleccionado = listaResultadosArtista.get(seleccion - 1);
                                                System.out.println("Desea agregar a la lista esta musica(S/N):");
                                                System.out.println("Nombre de la musica: " + seleccionado[0]);
                                                String respuesta1 = scanner.next();

                                                if (respuesta1.equalsIgnoreCase("S")) {
                                                    GestorDeLista gestorDeLista = new GestorDeLista(lista);

                                                    // Obtener ID_LISTA y ID_MUSICA

                                                    int idMusicaObtenidoArtista = obtenerIdMusica(conexion,seleccionado);
                                                    int idListaObtenidoArtista = obtenerIdLista(conexion,nombreLista,idUsuarioObtenido);

                                                    gestorDeLista.agregarListaMusica(conexion,idListaObtenidoArtista,idMusicaObtenidoArtista );
                                                }

                                            }
                                            else {
                                                    System.out.println("Seleccion invalida.");
                                            }

                                            System.out.println("Deseas seguir buscando para agregar a la lista. \n1.Seguir buscando\n 2.No"); 
                                              int buscarAgregar = scanner.nextInt();
                                                if(buscarAgregar == 2) {
                                                seguirBuscando1 = false;
                                                } 
                                                else {
                                                    System.out.println("Seleccion invalida.");
                                                }
                                        }
                                        else{
                                            System.out.println("No se encontraron resultados para la busqueda.");
                                        }

                                        }
                                        break;
                                    case 0:
                                        espacios();
                                        seguirBuscandoTituloArtista = false;
                                        break;
                                default:
                                    System.out.println("Opcion invalida");
                                    break;
                                }
                            }
                        }
                                else{
                                    break;
                                }
                                break;
                    case 3:
                        espacios();
                        System.out.println("Has seleccionado la opcion Ver Lista de reproduccion");
                        GestorDeLista gestorDeLista = new GestorDeLista();
                        ArrayList<String[]> listas = gestorDeLista.obtenerListasReproduccion(conexion,idUsuarioObtenido);

                        // Mostrar las listas de reproducción disponibles
                        gestorDeLista.mostrarListasReproduccion(listas);
                        
                        // Solicitar al usuario que elija una lista
                        Scanner scanner1 = new Scanner(System.in);
                        System.out.print("Ingresa el numero de la lista que deseas ver: ");
                        int numeroLista = scanner1.nextInt();

                        // Verificar si el número de lista es válido
                        if (numeroLista >= 1 && numeroLista <= listas.size()) {
                            // Obtener el ID de la lista seleccionada
                            String[] listaSeleccionada = listas.get(numeroLista - 1);
                            int idLista = Integer.parseInt(listaSeleccionada[0]);

                            // Obtener las canciones o música de la lista seleccionada
                            ArrayList<String[]> canciones = gestorDeLista.obtenerCancionesLista(conexion, idLista);

                            // Mostrar las canciones o música de la lista seleccionada
                           boolean salir = false;
                            while (!salir) {
                                gestorDeLista.mostrarCancionesLista(canciones);
                                System.out.print("Seleccione el numero de la musica que desea escuchar (0 para salir): ");
                                int seleccion = scanner.nextInt();

                                if (seleccion == 0) {
                                    salir = true;
                                } else if (seleccion >= 1 && seleccion <= canciones.size()) {
                                    String[] seleccionado = canciones.get(seleccion - 1);
                                    System.out.println("Ha seleccionado la siguiente musica:");
                                    System.out.println("Nombre de la musica: " + seleccionado[0]);
                                    System.out.println("Ruta: " + seleccionado[1]);

                                    reproducir.reproducirMusica(seleccionado[1]);
                                } else {
                                    System.out.println("Seleccion invalida. Por favor, elija nuevamente.");
                                }
                            }
                        } else {
                            System.out.println("Número de lista invalido. Intentalo nuevamente.");
                        }
                        
                        break;
                    case 4:
                        espacios();
                        // Lógica para compartir
                        System.out.println("Has seleccionado la opción Compartir");
                        tipoBusqueda = new ControlDeBusqueda("Artista",conexion);
                        String artista =  tipoBusqueda.getTipoDeBusqueda().toString();
                                    
                        System.out.println("Inserte el nombre del artista a buscar: ");
                        String artistaABuscar = scanner.next();
                                    
                        List<String[]> listaResultadosArtista= tipoBusqueda.BuscarMusicaArtista(artista, conexion, artistaABuscar);
                                  
                            if(!listaResultadosArtista.isEmpty()){
                                int numeroMusica = 1 ;
                                for (String[] resultado : listaResultadosArtista) {
                                    System.out.println(numeroMusica+". Nombre de la música: " + resultado[0]);
                                    System.out.println("Ruta: " + resultado[1]);
                                    System.out.println();
                                    numeroMusica++;
                                } 
                                System.out.print("Seleccione el número de la música a compartir: ");
                                int seleccion = scanner.nextInt();
                                scanner.nextLine();

                                if (seleccion >= 1 && seleccion <= listaResultadosArtista.size()) {
                                    String[] seleccionado = listaResultadosArtista.get(seleccion - 1);
                                    System.out.println("Ha seleccionado la siguiente música:");
                                    System.out.println("Nombre de la música: " + seleccionado[0]);
                                    System.out.println("Ruta: " + seleccionado[1]);
                                            
                                    try {
                                        CompartirMusica musicacomp = new CompartirMusica(seleccionado[1]);
                                        System.out.print("Finalizar Musica Compartida? (s/n): ");
                                        String fin = scanner.nextLine();
                                        switch (fin) {
                                            case "s":
                                            case "S":
                                                musicacomp.detenerServidor();
                                                break;
                                            default:
                                                throw new AssertionError();
                                        }

                                    } catch (Exception e) {
                                    //e.printStackTrace();
                                    }
                                }
                                else {
                                        System.out.println("Selección inválida.");
                                }
                            }
                            else{
                                System.out.println("No se encontraron resultados para la búsqueda.");
                            }       
                        break;
                    case 0:
                        espacios();
                        System.out.println("Has cerrado sesion. ¡Hasta luego!");
                        break;
                    default:
                        espacios();
                        System.out.println("Opción invalida. Por favor, selecciona una opción valida.");
                        break;
                }
                
                System.out.println();
            } while (opcionMenuUsuario != 0);
        }
        
    }
    public static int obtenerIdUsuario(Connection conexion, String Nombre_Usuario, String contra){
       int idUsuario=-1;
        try {
        
        String sql = "SELECT ID_USUARIO FROM USUARIO WHERE NOMBRE_USUARIO = ? AND CONTRASENIA = ?";
       PreparedStatement statement = conexion.prepareStatement(sql);
       statement.setString(1, Nombre_Usuario);
       statement.setString(2, contra);
       // Ejecutar la consulta SQL
       ResultSet resultSet = statement.executeQuery();

       // Verificar si se encontró el usuario y obtener su ID_USUARIO
       if (resultSet.next()) {
          idUsuario = resultSet.getInt("ID_USUARIO");
       }

       // Cerrar el ResultSet
          resultSet.close();
          statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idUsuario;
    }
    
    public static int obtenerIdLista(Connection conexion, String nombreLista, int idUsuario){
        int idLista=-1;
        String listaQuery = "SELECT ID_LISTA FROM LISTADEREPRODUCCION WHERE NOMBRE_LISTA = ? AND ID_USUARIO = ?";
        try (PreparedStatement listaStatement = conexion.prepareStatement(listaQuery)) {
        listaStatement.setString(1, nombreLista);
        listaStatement.setInt(2, idUsuario);
        ResultSet listaResultSet = listaStatement.executeQuery();
        if (listaResultSet.next()) {
        idLista = listaResultSet.getInt("ID_LISTA");
        }
        } catch (SQLException e) {
           e.printStackTrace();
                                                    
          }
        return idLista;
                          
    }
    public static int obtenerIdMusica(Connection conexion,String seleccionado[]){
        int idMusica = -1;
        String musicaQuery = "SELECT ID_MUSICA FROM MUSICA WHERE NOMBRE_MUSICA = ?";

        try (PreparedStatement musicaStatement = conexion.prepareStatement(musicaQuery)) {
        musicaStatement.setString(1, seleccionado[0]);
        ResultSet musicaResultSet = musicaStatement.executeQuery();
            if (musicaResultSet.next()) {
                idMusica = musicaResultSet.getInt("ID_MUSICA");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idMusica;
    }
     public static void espacios()
     {
         for (int i = 0; i < 50; i++) {
            System.out.println();
        }
     }
    
}
    
    
    

