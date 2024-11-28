package juego;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import entorno.Entorno;
import entorno.InterfaceJuego;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Juego extends InterfaceJuego {
    private Entorno entorno;
    private Prota prota;
    private Image fondo;
    private Tortuga[] tortugas ;
    private ArrayList<Gojos> gojos;
    private Random random;
    private ArrayList<Plataforma> plataformas;
    
    private int contadorGojos = 0;
    private double tiempoEntreApariciones = 120;
    private double tiempoTranscurrido = 0;

    private int marcadorTortugas = 0; // Marcador de tortugas eliminadas
    private int marcadorGojos = 0; // Marcador de colisiones con Gojos
    private double tiempoRestante = 90; // Temporizador en segundos (1:30 minutos)

    public Juego() {
        // Inicializa el objeto entorno
        this.entorno = new Entorno(this, "garcia mendez-rivero-gimenez", 800, 600);
        
        // Coloca al Prota en el centro
        this.prota = new Prota(14, 450);
        
        // Inicia el juego
        this.entorno.iniciar();
        this.fondo = new ImageIcon("imagenes/fukuma_fondo.jpg").getImage();
        
        this.tortugas = new Tortuga[2];
        // Inicia las tortugas con posiciones iniciales
        this.tortugas[0] = new Tortuga(100, 100); // La primera tortuga en la posición (100, 100)
        this.tortugas[1] = new Tortuga(500, 100); // La segunda tortuga en la posición (500, 100)
        this.gojos = new ArrayList<>();
        this.random = new Random();
        
       
        
        // Agrega un Gojo en la posición específica (30, 450) cuando inicie el juego
        gojos.add(new Gojos(400, 600));

        this.plataformas = new ArrayList<>();
        
        // Definir plataformas
        plataformas.add(new Plataforma(400, 100, 150, 20));
        plataformas.add(new Plataforma(250, 200, 150, 20));
        plataformas.add(new Plataforma(550, 200, 150, 20));
        plataformas.add(new Plataforma(150, 300, 150, 20));
        plataformas.add(new Plataforma(400, 300, 150, 20));
        plataformas.add(new Plataforma(650, 300, 150, 20));
        plataformas.add(new Plataforma(100, 400, 150, 20));
        plataformas.add(new Plataforma(300, 400, 150, 20));
        plataformas.add(new Plataforma(500, 400, 150, 20));
        plataformas.add(new Plataforma(700, 400, 150, 20));
        plataformas.add(new Plataforma(70, 500, 140, 20));
        plataformas.add(new Plataforma(245, 500, 135, 20));
        plataformas.add(new Plataforma(402, 500, 135, 20));
        plataformas.add(new Plataforma(565, 500, 135, 20));
        plataformas.add(new Plataforma(735, 500, 135, 20));
    }
    
    private void reproducirMusicaDeFondo(String nombreArchivo) throws Exception {
   
    	AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("Audio/Jujutsu-Kaisen-Opening.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY); 
        clip.start(); 
        
        	
        }
        	
        
    
    public void tick() {
        // Temporizador
        tiempoRestante -= 1.0 / 60.0;
        this.entorno.dibujarImagen(fondo, 400, 300, 0);
        // Movimiento del Prota
        if (this.entorno.estaPresionada('d')) {
            this.prota.moverDerecha();
        }
        if (this.entorno.estaPresionada('a')) {
            this.prota.moverIzquierda();
        }
        if (this.entorno.estaPresionada('w')) {
            this.prota.moverArriba();
        }
        if (this.entorno.estaPresionada('s')) {
            this.prota.mostrarFukumaMizushi();
        } else {
            this.prota.ocultarFukumaMizushi();
        }
        if (this.entorno.sePresiono(this.entorno.TECLA_ESPACIO)) {
            this.prota.dispararBolita();
        }
        if (this.entorno.estaPresionada('w')) {
            this.prota.saltar();
        }
        this.prota.aplicarGravedad();
        this.prota.aplicarGravedadEnSuelo();

        // Colisiones con plataformas
        for (Plataforma p : plataformas) {
            if (p.colisionaCon(this.prota)) {
                this.prota.detenerCaida(p.getY());
            }
        }      

        // Dibuja plataformas
        for (Plataforma p : plataformas) {
            p.dibujar(entorno);
        }

        // Dibuja al Prota
        if (prota != null && prota.getImagen() != null) {
            this.entorno.dibujarImagen(prota.getImagen(), prota.getX(), prota.getY(), prota.getAngulo());
        }

     // Mueve y dibuja las Tortugas
        for (int i = 0; i < tortugas.length; i++) {
            Tortuga tortuga = tortugas[i];
            if (tortuga != null) {
                tortuga.mover();  // Mueve la tortuga
                if (tortuga.getImagen() != null) {
                    this.entorno.dibujarImagen(tortuga.getImagen(), tortuga.getX(), tortuga.getY(), 0);
                }

                // Verifica colisión con el Prota
                if (tortuga.colisionaCon(prota)) {
                    System.out.println("¡Colisión con una tortuga!");
                }

                // Verifica colisión con plataformas
                for (Plataforma p : plataformas) {
                    if (p.colisionaCon(tortuga)) {
                        tortuga.detenerCaida(p.getY());
                        break; // Detiene la búsqueda de colisiones después de la primera
                    }
                }
            }
        }
        // Aparición de nuevos Gojos
        tiempoTranscurrido++;
        if (tiempoTranscurrido >= tiempoEntreApariciones && contadorGojos < 5) {
            gojos.add(new Gojos(400, 600));
            contadorGojos++;
            tiempoTranscurrido = 0;
        }

        // Mueve y dibuja los Gojos
        Iterator<Gojos> iteratorGojo = gojos.iterator();
        while (iteratorGojo.hasNext()) {
            Gojos gojo = iteratorGojo.next();
            gojo.mover();
            if (gojo.getImagen() != null) {
                this.entorno.dibujarImagen(gojo.getImagen(), gojo.getX(), gojo.getY(), 0);
            }

            if (gojo.colisionaCon(prota)) {
                System.out.println("¡Colisión con un Gojo!");
                marcadorGojos++;
                iteratorGojo.remove();
            }

            for (Plataforma p : plataformas) {
                if (p.colisionaCon(gojo)) {
                    gojo.detenerCaida(p.getY());
                    break;
                }
            }
        }

     // Mueve y dibuja la bolita
        this.prota.moverBolita();
        Bolita bolita = this.prota.getBolita();
        if (bolita != null && bolita.getImagenBolita() != null) {
            this.entorno.dibujarImagen(bolita.getImagenBolita(), bolita.getX(), bolita.getY(), 0);

            // Itera sobre las tortugas en el arreglo
            for (int i = 0; i < tortugas.length; i++) {
                Tortuga tortuga = tortugas[i];
                if (colisiona(bolita, tortuga)) {
                    // Remueve la tortuga del arreglo y actualiza el marcador
                    // Si estás usando un arreglo fijo, es más complicado remover elementos directamente.
                    // Una forma sería mover la tortuga a una posición "vacía" o usar un marcador para contar.
                    tortugas[i] = null; // Marca la tortuga como eliminada
                    marcadorTortugas++;
                }
            }
        }

        // Dibuja marcadores y temporizador
        this.entorno.cambiarFont("Georgia", 18, java.awt.Color.WHITE);
        this.entorno.escribirTexto("Itadoris Cortados: " + marcadorTortugas, 10, 20);
        this.entorno.escribirTexto("Gojos comidos: " + marcadorGojos, 10, 40);
        this.entorno.escribirTexto("Tiempo restante: " + (int) tiempoRestante + " segundos", 10, 60);
    }

    // Método para verificar colisión entre la Bolita y una Tortuga
    private boolean colisiona(Bolita bolita, Tortuga tortuga) {
        return bolita.getX() < tortuga.getX() + 50 && bolita.getX() + 20 > tortuga.getX() &&
               bolita.getY() < tortuga.getY() + 50 && bolita.getY() + 20 > tortuga.getY();
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}

