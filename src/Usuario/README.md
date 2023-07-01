# Usuario

Basandose en la creacion del diagrama de subsistema, se implemento el paquete "Usuario" el cual contiene las clases de "Autentificar" y la clase "GUI" los cuales son indispensables para la perspectiva del usuario.
La clase "Autentificar" permite al usuario ingresar y validar si su logueo en el proyecto spotify esta especificado en la base de datos.
Esta clase cumple con cierto método para el usuario:
| Funciones      | Descripción | Tipo de retorno |
| ----------- | ----------- | ----------- |
| validarCliente | Verifica que el cliente este en la base de datos | boolen |

Puede revisar el código en [Autentificar.java](https://github.com/ShanderGonzalez/Spotify_Grupo5/blob/master/src/Usuario/Autentificar.java "Autentificar.java")

La clase "GUI" es la clase principal, el cual permite la ejecucion del programa, es aquella clase main que contiene los objetos de las diferentes intancias de las clases.
Además es la interfaz por la que el usuario se va a comunicar con nuestra aplicación de spotiy. 
Esta clase contiene los siguientes métodos implementados:
| Funciones      | Descripción | Tipo de retorno |
| ----------- | ----------- | ----------- |
| obtenerIdUsuario | Permite obtener el id del usuario | static int |
| obtenerIdLista | Permite obtener el id de la lista | static int |
| obtenerIdMusica | Permite obtener el id de la música | static int |

Puede revisar el código en [GUI.java](https://github.com/ShanderGonzalez/Spotify_Grupo5/blob/master/src/Usuario/GUI.java "GUI.java")
