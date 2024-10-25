package juego;

import java.awt.Color;
import entorno.Entorno;

public class Plataforma {
    private double x, y;
    private double ancho, alto;

    public Plataforma(double x, double y, double ancho, double alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.GRAY);
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

   
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
}
