package juego;

import java.awt.Image;
import java.awt.Color;
import javax.swing.ImageIcon;
import entorno.Entorno;

public class Plataforma {
    private double x, y;
    private double ancho, alto;
    private Image imagen; // Variable para almacenar la imagen

    public Plataforma(double x, double y, double ancho, double alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;

        // Cargar la imagen desde la carpeta "imagenes"
        this.imagen = new ImageIcon("imagenes/dedo_sukuna.png").getImage();
    }

    public void dibujar(Entorno entorno) {
        if (this.imagen != null) {
            // Dibujar la imagen en la posición de la plataforma
            entorno.dibujarImagen(this.imagen, this.x, this.y, 0, 1.0);
        } else {
            // Dibujar un rectángulo de respaldo si la imagen no está disponible
            entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.GRAY);
        }
    }

    public boolean colisionaCon(Prota prota) {
        return prota.getX() >= this.x - this.ancho / 2 &&
               prota.getX() <= this.x + this.ancho / 2 &&
               prota.getY() + 20 >= this.y - this.alto / 2 && // margen de colisión vertical
               prota.getY() <= this.y + this.alto / 2;
    }

    public boolean colisionaCon(Tortuga tortuga) {
        return tortuga.getX() >= this.x - this.ancho / 2 &&
               tortuga.getX() <= this.x + this.ancho / 2 &&
               tortuga.getY() + 20 >= this.y - this.alto / 2 && // margen de colisión vertical
               tortuga.getY() <= this.y + this.alto / 2;
    }
    
    public boolean colisionaCon(Gojos gojo) {
        return gojo.getX() < this.x + this.ancho / 2 && 
               gojo.getX() + 50 > this.x - this.ancho / 2 && // Ajusta el ancho
               gojo.getY() < this.y + this.alto / 2 &&
               gojo.getY() + 50 > this.y - this.alto / 2; // Ajusta el alto
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
}

