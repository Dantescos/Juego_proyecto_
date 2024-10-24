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
        // Movimiento a la derecha
        if (this.entorno.estaPresionada('d')) { 
            this.prota.moverDerecha();
        }
        // Movimiento a la izquierda
        if (this.entorno.estaPresionada('a')) { 
            this.prota.moverIzquierda();
        }
        // Movimiento hacia arriba
        if (this.entorno.estaPresionada('w')) { 
            this.prota.moverArriba();
        }
        // Movimiento hacia abajo
        if (this.entorno.estaPresionada('s')) { 
            this.prota.moverAbajo();
        }

        // Disparo del corte con la barra espaciadora
        if (this.entorno.sePresiono(this.entorno.TECLA_ESPACIO)) {
            this.prota.dispararBolita();
        }
        
        // Mover y dibujar tortugas
        for (Tortuga tortuga : tortugas) {
            tortuga.mover(); // Ahora las tortugas se mueven
            this.entorno.dibujarImagen(tortuga.getImagen(), tortuga.getX(), tortuga.getY(), 0);
            
            // Colisión con el prota
            if (tortuga.colisionaCon(prota)) {
                System.out.println("¡Colisión con una tortuga!"); // Manejar la colisión
            }
        }
        
        // Dibujamos al prota
        this.entorno.dibujarImagen(
            this.prota.getImagen(), 
            this.prota.getX(), 
            this.prota.getY(), 
            this.prota.getAngulo()
        );
        
        // Movemos y dibujamos el corte
        this.prota.moverBolita();
        Bolita bolita = this.prota.getBolita();
        if (bolita != null) {
            this.entorno.dibujarImagen(
                bolita.getImagenBolita(), 
                bolita.getX(), 
                bolita.getY(), 
                0 
            );
        }
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}

