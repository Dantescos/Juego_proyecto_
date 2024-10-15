package juego;
import java.awt.Color;
import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
    private Entorno entorno;
    private Prota prota;

    public Juego() {
        // Inicializa el objeto entorno
        this.entorno = new Entorno(this, "garcia mendez-rivero-gimenez", 800, 600);
        
        // colocamos al prota en el centro
        this.prota = new Prota(100, 500);
        
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

        // Disparar bolita con la barra espaciadora
        if (this.entorno.sePresiono(this.entorno.TECLA_ESPACIO)) {
            this.prota.dispararBolita();
        }

        // Mover la bolita si existe
        this.prota.moverBolita();
        
        // Dibujar el Prota
        this.entorno.dibujarTriangulo(
            this.prota.getX(), 
            this.prota.getY(), 
            40, 40, 
            this.prota.getAngulo(), 
            Color.GREEN
        );

        // Dibujar la bolita si existe
        Prota.Bolita bolita = this.prota.getBolita();
        if (bolita != null) {
            this.entorno.dibujarCirculo(bolita.getX(), bolita.getY(), 10, Color.RED);
        }
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}

