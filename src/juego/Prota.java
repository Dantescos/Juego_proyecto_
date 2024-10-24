package juego;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Prota {
    private double x;
    private double y;
    private double angulo;
    private Image imagen; // Imagen del Prota
    private Bolita bolita; // Instancia de la bolita
    private char ultimaDireccion; // Para almacenar la última dirección de movimiento

    // Inicializamos al prota con la imagen
    public Prota(double x, double y) {
        this.x = x;
        this.y = y;
        this.angulo = 0; // Ángulo inicial
        this.imagen = new ImageIcon("imagenes/sukuna.png").getImage(); // Cargamos la imagen de sukuna
        this.bolita = null; // La bolita no existe al inicio
        this.ultimaDireccion = ' '; // No hay dirección inicial
    }

    // Movemos al prota
    public void moverDerecha() {
        this.x += 5;
        this.ultimaDireccion = 'd'; // Actualiza la última dirección
    }

    public void moverIzquierda() {
        this.x -= 5;
        this.ultimaDireccion = 'a'; // Actualiza la última dirección
    }

    // Movimiento hacia arriba
    public void moverArriba() {
        this.y -= 5;
        this.ultimaDireccion = 'w'; // Actualiza la última dirección
    }

    // Movimiento hacia abajo
    public void moverAbajo() {
        this.y += 5;
        this.ultimaDireccion = 's'; // Actualiza la última dirección
    }

    // Método para disparar la bolita
    public void dispararBolita() {
        if (this.bolita == null) { // Solo dispara si no hay una bolita existente en pantalla
            // Si no hay dirección registrada, disparamos hacia la derecha por defecto
            if (this.ultimaDireccion == ' ') {
                this.ultimaDireccion = 'd';
            }
            this.bolita = new Bolita(this.x, this.y, this.ultimaDireccion);
        }
    }

    // Método para mover la bolita
    public void moverBolita() {
        if (this.bolita != null) {
            this.bolita.mover();
            if (this.bolita.fueraDePantalla()) { 
                this.bolita = null; // Elimina la bolita al salir de pantalla
            }
        }
    }

    // Getters para la posición de Prota
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getAngulo() {
        return this.angulo;
    }

    public Bolita getBolita() {
        return this.bolita;
    }

    public Image getImagen() {
        return this.imagen;
    }
}
