package juego;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Gojos {
    private double x;
    private double y;
    private double velocidad;
    private double velocidadVertical; // Velocidad en dirección vertical
    private boolean moviendoDerecha;
    private Image imagen;

    public Gojos(double x, double y) {
        this.x = x;
        this.y = y;
        this.velocidad = 2; // Velocidad horizontal inicial
        this.velocidadVertical = 0; // Inicializa la velocidad vertical
        this.moviendoDerecha = true; // Dirección inicial a la derecha
        this.imagen = new ImageIcon("imagenes/gojo.png").getImage(); // Imagen específica para Gojos
    }

    public void mover() {
        // Mover en la dirección correcta
        if (moviendoDerecha) {
            x += velocidad;
        } else {
            x -= velocidad;
        }
        
        // Cambiar de dirección si alcanza los bordes de la pantalla
        if (x <= 0 || x >= 750) {
            cambiarDireccion();
        }
        
        // Aplicar gravedad
        aplicarGravedad();
    }

    public void aplicarGravedad() {
        velocidadVertical += 0.5; // Incrementa la velocidad vertical (gravedad)
        y += velocidadVertical; // Mueve el objeto hacia abajo

        // Reinicia la posición si cae por debajo de la pantalla
        if (y > 600) {
            y = 0; // Reinicia altura (ajústalo según tus necesidades)
            velocidadVertical = 0; // Reinicia la velocidad vertical
        }
    }

    public void detenerCaida(double alturaPlataforma) {
        y = alturaPlataforma; // Coloca el objeto sobre la plataforma
        velocidadVertical = 0; // Detiene la velocidad vertical
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

    // Verifica si colisiona con el prota
    public boolean colisionaCon(Prota prota) {
        // Lógica de colisión
        return this.x < prota.getX() + 50 && this.x + 50 > prota.getX() &&
               this.y < prota.getY() + 50 && this.y + 50 > prota.getY();
    }
}
