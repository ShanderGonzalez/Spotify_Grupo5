# NSpotifyChino
Se tiene el paquete "NSpotifyChino" el cual contiene las clases de "CompartirMusica", "ConexionSQLite" y la clase "ControlDeBusqueda". Estas clasese respecto a este paquete son externos al subsistema ya que no se muestran al usuario por medio de una interfaz util. Es decir; que esto es parte del desarrollo interno del proyecto como la parte backend.

La clase "CompartirMusica" permite crear un servidor en base a la libreria ```nanoHTTPD.jar``` el cual establece una conexion en el puerto **8080** para compartir la música con otros usuarios.
Por ello se implemento un ```extends NanoHTTPD``` en el que se pudo hacer uso del método ```public Response serve(IHTTPSession session)```, esto permite establecer una conexion del servidor y compartir la música.

| Funciones      | Descripción | Tipo de retorno |
| ----------- | ----------- | ----------- |
| detenerServidor | Detiene el puerto del servidor que este en uso | void |

Puede encontrar el código en [CompartirMusica.java](https://github.com/ShanderGonzalez/Spotify_Grupo5/blob/master/src/NSpotifyChino/CompartirMusica.java "CompartirMusica.java")

La clase "ConexionSQLite" es aquella clase que permite comunicar al programa de Spotify con la base de datos generada en sqlite. Esto permite realizar una conexion entre todas las clases que implementen de ella para el uso y verificacion de datos.

Esta clase toma como atributo la URL de donde esta almacenado la base de datos en el computador. Tambien se establecen dos métodos como se presenta acontinuación:

| Funciones      | Descripción | Tipo de retorno |
| ----------- | ----------- | ----------- |
| conectar | Conectar con la base de datos de sqlite | void |
| desconectar | Desconectar con la base de datos de sqlite | void |

Puede encontar el código en [ConexionSQLite.java](https://github.com/ShanderGonzalez/Spotify_Grupo5/blob/master/src/NSpotifyChino/ConexionSQLite.java "ConexionSQLite.java")

La clase "ControlDeBusqueda" permite llevar un control de busqueda de toda la música, es decir; que permite realizar la busqueda de la música en la que el usuario este interesado escuchar. Por ello el usuario puede buscar la música en base al artísta o en base al título de la música.

Esta clase tiene un método con la funcionalidad de buscar música:
| Funciones      | Descripción | Tipo de retorno |
| ----------- | ----------- | ----------- |
| BuscarMusicaArtista | Busca la música respecto al título de la música o respecto al artísta | List |

Puede encontar el código en [ControlDeBusqueda.java](https://github.com/ShanderGonzalez/Spotify_Grupo5/blob/master/src/NSpotifyChino/ControlDeBusqueda.java "ControlDeBusqueda.java")
