package juego;

//import entorno.Entorno;
//import java.awt.Color;
//
//public class Plataforma {
//    private double x;
//    private double y;
//    private double ancho;
//    private double alto;
//
//    // Constructor
//    public Plataforma(double x, double y, double ancho, double alto) {
//        this.x = x;
//        this.y = y;
//        this.ancho = ancho;
//        this.alto = alto;
//    }
//
//    // Método para dibujar la plataforma
//    public void dibujar(Entorno entorno) {
//        entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.GREEN);
//    }
//}


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

    // Método para detectar colisión con el Prota
    public boolean colisionaCon(Prota prota) {
        return prota.getX() >= this.x - this.ancho / 2 &&
               prota.getX() <= this.x + this.ancho / 2 &&
               prota.getY() + 20 >= this.y - this.alto / 2 && // margen de colisión vertical
               prota.getY() <= this.y + this.alto / 2;
    }
    
    // Getters para la posición y dimensiones, si son necesarios
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
}