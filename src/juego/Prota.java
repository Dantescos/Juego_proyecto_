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
        this.bolita = null; // el corte no existe al inicio
        this.ultimaDireccion = ' '; // No hay dirección inicial
    }

    // Movemos a nuestro sukuna
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
        if (this.bolita == null) { // Solo dispara si no hay un corte existente en pantalla
            // Si no hay dirección registrada, disparamos hacia la derecha por defecto
            if (this.ultimaDireccion == ' ') {
                this.ultimaDireccion = 'd';
            }
            this.bolita = new Bolita(this.x, this.y, this.ultimaDireccion);
        }
    }

    // Método para que salga el corte
    public void moverBolita() {
        if (this.bolita != null) {
            this.bolita.mover();
            if (this.bolita.fueraDePantalla()) { 
                this.bolita = null; // Elimina el corte al salir de pantalla
            }
        }
    }

    // Getter para la posición de sukuna
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

    // Clase interna del corte
    public class Bolita {
        private double x;
        private double y;
        private double velocidad;
        private char direccionInicial; // Almacena la dirección inicial al disparar
        private Image imagenBolita; // Imagen para el corte

        public Bolita(double x, double y, char direccion) {
            this.x = x;
            this.y = y;
            this.velocidad = 10;
            this.direccionInicial = direccion;
            this.imagenBolita = new ImageIcon("imagenes/corte_sukuna.png").getImage(); // Cargamos el corte
        }

        public void mover() {
            // el corte se mueve siempre en la dirección inicial
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

        // Verifica si la el corte salie  de la pantalla
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
}

