package juego;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Tortuga {
    private double x;
    private double y;
    private double velocidad;
    private boolean moviendoDerecha;
    private Image imagen;

    public Tortuga(double x, double y) {
        this.x = x;
        this.y = y;
        this.velocidad = 2; // Inicializamos la velocidad
        this.moviendoDerecha = true; // la tortuga comienza moviendose a la derecha
        this.imagen = new ImageIcon("imagenes/itadori.png").getImage(); 
    }

    public void mover() {
        // Mover en la dirección correcta
        if (moviendoDerecha) {
            x += velocidad;
        } else {
            x -= velocidad;
        }
        
        // Cambiar de dirección si llega a los bordes de la pantalla
        if (x <= 0 || x >= 750) {
            cambiarDireccion();
        }
    }

    public void cambiarDireccion() {
        moviendoDerecha = !moviendoDerecha; // cambia la dirección de la tortuga
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public Image getImagen() {
        return this.imagen;
    }

    // Verifica si colisiona con el prota
    public boolean colisionaCon(Prota prota) {
        // Lógica de colisión simple basada en coordenadas
        return this.x < prota.getX() + 50 && this.x + 50 > prota.getX() &&
               this.y < prota.getY() + 50 && this.y + 50 > prota.getY();
    }
}
