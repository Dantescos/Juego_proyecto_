package juego;

public class Prota {
    private double x;
    private double y;
    private double angulo;
    private Bolita bolita; // Instancia de la bolita
    private char ultimaDireccion; // Para almacenar la ultima dirección de movimiento

    // inicializamos al prota
    public Prota(double x, double y) {
        this.x = x;
        this.y = y;
        this.angulo = 0;
        this.bolita = null; 
        this.ultimaDireccion = ' '; 
    }

    // Movemos a nuestro prota
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
        if (this.bolita == null) { //verifica que no exista la bolita
            // disparamos hacia la derecha por defecto
            if (this.ultimaDireccion == ' ') {
                this.ultimaDireccion = 'd';
            }
            this.bolita = new Bolita(this.x, this.y, this.ultimaDireccion);
        }
    }

    // Método para mover la bolita si existe
    public void moverBolita() {
        if (this.bolita != null) {
            this.bolita.mover();
            if (this.bolita.fueraDePantalla()) { 
                this.bolita = null; // Elimina la bolita si sale de la pantalla
            }
        }
    }

    // Getter para la posición de Prota
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getAngulo() {
        return this.angulo;
    }

    // Método para obtener la bolita
    public Bolita getBolita() {
        return this.bolita;
    }

    // Clase interna Bolita
    public class Bolita {
        private double x;
        private double y;
        private double velocidad;
        private char direccionInicial; // Almacena la dirección inicial al disparar

        public Bolita(double x, double y, char direccion) {
            this.x = x;
            this.y = y;
            this.velocidad = 10;
            this.direccionInicial = direccion; // Guardamos la dirección al disparar
        }

        public void mover() {
            // La bolita se mueve siempre en la dirección inicial
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

        // Verifica si la bolita salió de los límites de la pantalla
        public boolean fueraDePantalla() {
            return this.x < 0 || this.x > 800 || this.y < 0 || this.y > 600;
        }

        public double getX() {
            return this.x;
        }

        public double getY() {
            return this.y;
        }
    }
}

