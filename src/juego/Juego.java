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
        
       
        this.entorno.dibujarTriangulo(
            this.prota.getX(), 
            this.prota.getY(), 
            40, 40, 
            this.prota.getAngulo(), 
            Color.GREEN
        );
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}
