package Reproduccion;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PanelDeReproduccion {
    private Clip clip;
    private Long currentPosition;
    private boolean paused;
    
    public PanelDeReproduccion() {
    }

   public void reproducirMusica(String filePath) {
       try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // Reproducir el audio
            clip.start();

            // Menú de opciones durante la reproducción
            Scanner scanner = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("===== MENU DE REPRODUCCIÓN =====");
                System.out.println("1. Pausar");
                System.out.println("2. Reanudar");
                //System.out.println("3. Detener");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opcion: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        for (int i = 0; i < 50; i++) {
                            System.out.println();
                        }
                        if (!paused) {
                            currentPosition = clip.getMicrosecondPosition(); // Guardar la posición actual
                            clip.stop(); // Pausar la reproducción
                            paused = true;
                            System.out.println("Reproduccion pausada.");
                        }
                        break;
                    case 2:
                        for (int i = 0; i < 50; i++) {
                            System.out.println();
                        }
                        if (paused) {
                            clip.setMicrosecondPosition(currentPosition); // Restaurar la posición guardada
                            clip.start(); // Reanudar la reproducción
                            paused = false;
                            System.out.println("Reproduccion reanudada.");
                        }
                        break;
//                    case 3:
//                        for (int i = 0; i < 50; i++) {
//                            System.out.println();
//                        }
//                        clip.stop(); // Detener la reproducción
//                        clip.close(); // Cerrar el clip
//                        //System.out.println("Reproduccion detenida.");
                       // break;
                }
            } while (opcion != 0);

            clip.stop(); // Detener la reproducción al salir del bucle
            clip.close(); // Cerrar el clip

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
   }
}