package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
    private Entorno entorno;
    private Prota prota;

    public Juego() {
        // Inicializa el objeto entorno
        this.entorno = new Entorno(this, "garcia mendez-rivero-gimenez", 800, 600);
        
        // Colocamos al prota en el centro
        this.prota = new Prota(400, 300);
        
        // Inicia el juego
        this.entorno.iniciar();
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
        
       
        this.entorno.dibujarImagen(
            this.prota.getImagen(), 
            this.prota.getX(), 
            this.prota.getY(), 
            this.prota.getAngulo()
        );
        
        
        this.prota.moverBolita();
        Prota.Bolita bolita = this.prota.getBolita();
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


