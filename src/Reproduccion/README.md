# Reproduccion
Basandose en la creacion del diagrama de subsistema, se implemento el paquete "Reproduccion" el cual contiene las clases de "Lista" y la clase "PanelDeReproduccion" los cuales son indispensables para la reproducción de la música en una lista establecida.

La clase "Lista" permite crear una lista en base a las músicas que se vayan creando, esta lista podrá ser creada siempre y cuando el usuario este interesado en una música y necesite crear una lista.
Esta clase contiene métodos que llevan a cabo su funcionalidad:
| Funciones      | Descripción | Tipo de retorno |
| ----------- | ----------- | ----------- |
| crearLista | Permite crear un lista de músicas | void |
| mostrarListasReproduccion | Permite mostrar la lista en reproducción | void |

Puede encontrar el código en [Lista.java](https://github.com/ShanderGonzalez/Spotify_Grupo5/blob/master/src/Reproduccion/Lista.java "Lista.java")

La clase "PanelDeReproduccion" es aquella que permite reproducir la música que el usuario desee escuchar, esta clase recibe como parámetro la ruta de donde se encuentra almacenada la música respecto a la base de datos.
Esta clase contiene un método que lleva a cabo su reproducción:
| Funciones      | Descripción | Tipo de retorno |
| ----------- | ----------- | ----------- |
| reproducirMusica | Reproduce la música que el usuario seleccione | void |

Puede encontrar el código en [PanelDeReproduccion.java](https://github.com/ShanderGonzalez/Spotify_Grupo5/blob/master/src/Reproduccion/PanelDeReproduccion.java "PanelDeReproduccion.java")
