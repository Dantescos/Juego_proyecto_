package juego;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Tortuga {
    private double x;
    private double y;
    private double velocidad;
    private double velocidadVertical; // Nueva variable para la velocidad vertical
    private boolean moviendoDerecha;
    private Image imagen;
    private double limiteIzquierdo = 20;
    private double limiteDerecho = 750;

    public Tortuga(double x, double y) {
        this.x = x;
        this.y = y;
        this.velocidad = 2; // Inicializamos la velocidad horizontal
        this.velocidadVertical = 0; // Inicializa la velocidad vertical
        this.moviendoDerecha = true; // La tortuga comienza moviéndose a la derecha
        this.imagen = new ImageIcon("imagenes/itadori.png").getImage();
    }

    public void mover() {
        // Mover en la dirección correcta
        if (moviendoDerecha) {
            x += velocidad;
            if(x >= limiteDerecho) {
                cambiarDireccion();
            }
        } else {
            x -= velocidad;
            if(x <= limiteIzquierdo) {
                cambiarDireccion();
            }
        }
        
        // Aplicar gravedad
        aplicarGravedad();
    }

    public void aplicarGravedad() {
        velocidadVertical += 0.5; // Incrementa la velocidad vertical (gravedad)
        y += velocidadVertical; // Mueve la tortuga hacia abajo

        // Si la tortuga cae por debajo de la pantalla, reinicializa su posición
        if (y > 600) {
            y = 0; // Reinicia la altura (puedes ajustar esto según tus necesidades)
            velocidadVertical = 0; // Reinicia la velocidad vertical
        }
    }

    public void detenerCaida(double alturaPlataforma) {
        y = alturaPlataforma; // Coloca la tortuga sobre la plataforma
        velocidadVertical = 0; // Detiene la velocidad vertical
    }

    public void cambiarDireccion() {
        moviendoDerecha = !moviendoDerecha; // Cambia la dirección de la tortuga
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

    public void setLimites(double izquierdo, double derecho) {
        this.limiteIzquierdo = izquierdo;
        this.limiteDerecho = derecho;
    }

    // Verifica si colisiona con el prota
    public boolean colisionaCon(Prota prota) {
        // Lógica de colisión
        return this.x < prota.getX() + 50 && this.x + 50 > prota.getX() &&
               this.y < prota.getY() + 50 && this.y + 50 > prota.getY();
    }
}

