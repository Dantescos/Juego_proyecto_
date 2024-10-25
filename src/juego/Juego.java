package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;
import java.util.ArrayList;
import java.util.Random;

public class Juego extends InterfaceJuego {
    private Entorno entorno;
    private Prota prota;
    private ArrayList<Tortuga> tortugas;
    private Random random;

    public Juego() {
        // Inicializa el objeto entorno
        this.entorno = new Entorno(this, "garcia mendez-rivero-gimenez", 800, 600);
        
        // Colocamos al prota en el centro
        this.prota = new Prota(400, 300);
        
        // Inicia el juego
        this.entorno.iniciar();
        this.tortugas = new ArrayList<>();
        this.random = new Random();
        
        // Generamos un número aleatorio de tortugas
        for (int i = 0; i < random.nextInt(3) + 2; i++) {
            double x = random.nextDouble() * 800; // Posición aleatoria en X
            double y = 50; // Altura inicial (cima de la pantalla)
            tortugas.add(new Tortuga(x, y));
        }
    }

    public void tick() {
        // Manejo de movimiento del Prota
        if (this.entorno.estaPresionada('d')) {
            this.prota.moverDerecha();
        }
        if (this.entorno.estaPresionada('a')) {
            this.prota.moverIzquierda();
        }
        if (this.entorno.estaPresionada('w')) {
            this.prota.moverArriba();
        }
        
        // Al presionar 's', mostrar Fukuma Mizushi sin mover al Prota
        if (this.entorno.estaPresionada('s')) {
            this.prota.mostrarFukumaMizushi(); // Muestra Fukuma Mizushi al presionar 's'
        } else {
            this.prota.ocultarFukumaMizushi(); // Oculta si no está presionada
        }

        if (this.entorno.sePresiono(this.entorno.TECLA_ESPACIO)) {
            this.prota.dispararBolita();
        }

        // Dibuja Fukuma Mizushi detrás de Prota si es visible
        if (this.prota.isFukumaVisible()) {
            this.entorno.dibujarImagen(this.prota.getFukumaMizushiImagen(), this.prota.getX(), this.prota.getY(), 0);
        }

        // Dibuja el Prota
        this.entorno.dibujarImagen(this.prota.getImagen(), this.prota.getX(), this.prota.getY(), this.prota.getAngulo());

        // Mueve y verifica colisión de tortugas
        Bolita bolita = this.prota.getBolita();
        for (int i = 0; i < tortugas.size(); i++) {
            Tortuga tortuga = tortugas.get(i);
            if (tortuga != null) {
                tortuga.mover();
                this.entorno.dibujarImagen(tortuga.getImagen(), tortuga.getX(), tortuga.getY(), 0);
                
                // Colisión con el Prota
                if (tortuga.colisionaCon(prota)) {
                    System.out.println("¡Colisión con una tortuga!");
                }

                // Colisión con la bolita
                if (bolita != null && tortuga.colisionaCon(bolita)) {
                    tortugas.set(i, null); // Vuelve la tortuga null al chocar con la bolita
                    System.out.println("¡La tortuga fue eliminada por la bolita!");
                }
            }
        }

        // Mueve y dibuja la bolita
        this.prota.moverBolita();
        if (bolita != null) {
            this.entorno.dibujarImagen(bolita.getImagenBolita(), bolita.getX(), bolita.getY(), 0);
        }
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}

