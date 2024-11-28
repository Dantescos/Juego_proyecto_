package juego;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Bolita {
    private double x;
    private double y;
    private double velocidad;
    private char direccionInicial; // Almacena la dirección inicial al disparar
    private Image imagenBolita; // Imagen para la bolita

    public Bolita(double x, double y, char direccion) {
        this.x = x;
        this.y = y;
        this.velocidad = 4;
        this.direccionInicial = direccion;
        this.imagenBolita = new ImageIcon("imagenes/corte_sukuna.png").getImage(); // Cargamos la imagen
    }

    public void mover() {
        // El corte se mueve siempre en la dirección inicial
        switch (this.direccionInicial) {
            case 'w':
                this.y -= velocidad;
                break;
            case 's':
                this.y += velocidad;
                break;
            case 'a':
                this.x -= velocidad;
                break;
            case 'd':
                this.x += velocidad;
                break;
        }
    }

    // Verifica si el corte sale de la pantalla
    public boolean fueraDePantalla() {
        return this.x < 0 || this.x > 800 || this.y < 0 || this.y > 600;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public Image getImagenBolita() {
        return this.imagenBolita;
    }
}
