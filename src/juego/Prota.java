package juego;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Prota {
    private double x;
    private double y;
    private double angulo;
    private Image imagen; // Imagen del Prota
    private Image fukumaMizushiImagen; // Imagen de Fukuma Mizushi
    private Bolita bolita; // Instancia de la bolita
    private char ultimaDireccion; // Para almacenar la última dirección de movimiento
    private boolean fukumaVisible; // Para controlar la visibilidad de Fukuma Mizushi

    // Atributos para el salto y gravedad
    private double velocidadY; // Velocidad vertical (para el salto y la gravedad)
    private final double GRAVEDAD = 0.5; // Gravedad constante
    private boolean enElAire; // Indica si el personaje está en el aire

    // Inicializamos al prota con la imagen
    public Prota(double x, double y) {
        this.x = x;
        this.y = y;
        this.angulo = 0; // Ángulo inicial
        this.fukumaMizushiImagen = new ImageIcon("imagenes/fukuma_mizushi.png").getImage(); // Cargamos la imagen de Fukuma Mizushi
        this.imagen = new ImageIcon("imagenes/sukuna.png").getImage(); // Cargamos la imagen de sukuna
        this.enElAire = false; // Inicia en el suelo
        this.bolita = null; // La bolita no existe al inicio
        this.ultimaDireccion = ' '; // No hay dirección inicial
        this.fukumaVisible = false; // Fukuma Mizushi no es visible al inicio
    }

    
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

    public void saltar() {
        if (!enElAire) { // Solo salta si está en el suelo
            this.velocidadY = -10; // Velocidad de salto (hacia arriba)
            this.enElAire = true; // Ahora está en el aire
        }
    }

  
    public void moverBolita() {
        if (this.bolita != null) {
            this.bolita.mover();
            if (this.bolita.fueraDePantalla()) { 
                this.bolita = null; // Elimina la bolita al salir de pantalla
            }
        }
    }

    public void aplicarGravedad() {
        if (enElAire) {
            this.velocidadY += GRAVEDAD; // Aumenta la velocidad de caída
            this.y += velocidadY; // Actualiza la posición vertical
        }
    }
    
    // Método para detener la caída al colisionar con una plataforma
    public void detenerCaida(double yPlataforma) {
        this.y = yPlataforma - 20; 
        this.velocidadY = 0; // Detenemos la velocidad vertical
        this.enElAire = false; // Está en el suelo
    }

    // Método para mostrar Fukuma Mizushi
    public void mostrarFukumaMizushi() {
        this.fukumaVisible = true; // Cambia el estado a visible
    }

    // Método para ocultar Fukuma Mizushi
    public void ocultarFukumaMizushi() {
        this.fukumaVisible = false; // Cambia el estado a no visible
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

    // Método para obtener la imagen de Fukuma Mizushi
    public Image getFukumaMizushiImagen() {
        return this.fukumaMizushiImagen;
    }

    // Método para verificar si Fukuma Mizushi es visible
    public boolean isFukumaVisible() {
        return this.fukumaVisible;
    }
}
