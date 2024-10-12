package juego;

public class Prota {
    private double x;
    private double y;
    private double angulo;
    
    // inicializamos al prota
    public Prota(double x, double y) {
        this.x = x;
        this.y = y;
        this.angulo = 0; // Angulo inicial
    }

    // Movemos a nuestro prota
    public void moverDerecha() {
        this.x += 5;
    }

    public void moverIzquierda() {
        this.x -= 5;
    }
    //empezamos  a programar el salto
    public void moverArriba() {
        this.y -= 5; 
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getAngulo() {
        return this.angulo;
    }
}

