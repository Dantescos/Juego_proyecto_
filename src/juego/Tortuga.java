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
        this.velocidad = 2; // Velocidad de movimiento de la tortuga
        this.moviendoDerecha = true; // Comienza moviéndose a la derecha
        this.imagen = new ImageIcon("imagenes/tortuga.png").getImage(); // Carga la imagen de la tortuga
    }

    public void mover() {
        if (moviendoDerecha) {
            x += velocidad;
        } else {
            x -= velocidad;
        }
    }

    public void cambiarDireccion() {
        moviendoDerecha = !moviendoDerecha; // Cambia la dirección
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

    // Verifica colisión con Pep
    public boolean colisionaCon(Prota prota) {
        // Simple lógica de colisión basada en coordenadas
        return this.x < prota.getX() + 50 && this.x + 50 > prota.getX() &&
               this.y < prota.getY() + 50 && this.y + 50 > prota.getY();
    }
}
