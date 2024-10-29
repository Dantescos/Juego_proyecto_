package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Juego extends InterfaceJuego {
    private Entorno entorno;
    private Prota prota;
    private ArrayList<Tortuga> tortugas;
    private ArrayList<Gojos> gojos; // Lista para los Gojos
    private Random random;
    private ArrayList<Plataforma> plataformas;
    private int contadorGojos = 0; // Contador para controlar la aparición de Gojos
    private double tiempoEntreApariciones = 120; // Tiempo en ticks entre apariciones
    private double tiempoTranscurrido = 0; // Contador de tiempo transcurrido

    public Juego() {
        // Inicializa el objeto entorno
        this.entorno = new Entorno(this, "garcia mendez-rivero-gimenez", 800, 600);
        
        // Coloca al Prota en el centro
        this.prota = new Prota(14, 450);
        
        // Inicia el juego
        this.entorno.iniciar();
        
        this.tortugas = new ArrayList<>();
        this.gojos = new ArrayList<>(); // Inicializa la lista de Gojos
        this.random = new Random();
        
        // Genera un número aleatorio de Tortugas
        for (int i = 0; i < random.nextInt(3) + 2; i++) {
            double x = random.nextDouble() * 800; // Posición aleatoria en X
            double y = 50; // Altura inicial (cima de la pantalla)
            tortugas.add(new Tortuga(x, y));
        }
        
     // Agrega un Gojo en la posición específica (30, 450) cuando inicie el juego
        gojos.add(new Gojos(400, 600)); // Cambia las coordenadas según sea necesario

        
        this.plataformas = new ArrayList<>(); // Inicializa la lista de plataformas
        
        // Definir plataformas para una pantalla de 800x600
        plataformas.add(new Plataforma(400, 100, 150, 20));
        plataformas.add(new Plataforma(250, 200, 150, 20));
        plataformas.add(new Plataforma(550, 200, 150, 20));
        plataformas.add(new Plataforma(150, 300, 150, 20));
        plataformas.add(new Plataforma(400, 300, 150, 20));
        plataformas.add(new Plataforma(650, 300, 150, 20));
        plataformas.add(new Plataforma(100, 400, 150, 20));
        plataformas.add(new Plataforma(300, 400, 150, 20));
        plataformas.add(new Plataforma(500, 400, 150, 20));
        plataformas.add(new Plataforma(700, 400, 150, 20));
        plataformas.add(new Plataforma(70, 500, 140, 20));
        plataformas.add(new Plataforma(245, 500, 135, 20));
        plataformas.add(new Plataforma(402, 500, 135, 20));
        plataformas.add(new Plataforma(565, 500, 135, 20));
        plataformas.add(new Plataforma(735, 500, 135, 20));
    }
        
    public void tick() {
        // Manejo de movimiento del Prota
        if (this.entorno.estaPresionada('d')) {
            this.prota.moverDerecha();
        }
        if (this.entorno.estaPresionada('a')) {
            this.prota.moverIzquierda();
        }
        if (this.entorno.estaPresionada('w')) {
            this.prota.moverArriba();
        }

        if (this.entorno.estaPresionada('s')) {
            this.prota.mostrarFukumaMizushi(); // Muestra Fukuma Mizushi al presionar 's'
        } else {
            this.prota.ocultarFukumaMizushi(); // Oculta si no está presionada
        }

        if (this.entorno.sePresiono(this.entorno.TECLA_ESPACIO)) {
            this.prota.dispararBolita();
        }

        if (this.entorno.estaPresionada('w')) {
            this.prota.saltar(); // Llama al método saltar
        }

        this.prota.aplicarGravedad(); 

        // Verifica colisiones con plataformas
        for (Plataforma p : plataformas) {
            if (p.colisionaCon(this.prota)) {
                // Detiene la caída y ajusta la posición del Prota
                this.prota.detenerCaida(p.getY());
            }
        }

        // Dibuja plataformas
        for (Plataforma p : plataformas) {
            p.dibujar(entorno); 
        }

        // Dibuja la imagen de Fukuma Mizushi si está visible
        if (this.prota.isFukumaVisible()) {
            this.entorno.dibujarImagen(this.prota.getFukumaMizushiImagen(), this.prota.getX(), this.prota.getY(), 0);
        }

        // Dibuja al Prota
        this.entorno.dibujarImagen(this.prota.getImagen(), this.prota.getX(), this.prota.getY(), this.prota.getAngulo());

        // Mueve y dibuja las Tortugas
        Iterator<Tortuga> iteratorTortuga = tortugas.iterator();
        while (iteratorTortuga.hasNext()) {
            Tortuga tortuga = iteratorTortuga.next();
            tortuga.mover(); 
            this.entorno.dibujarImagen(tortuga.getImagen(), tortuga.getX(), tortuga.getY(), 0);
            
            // Verifica colisiones con el Prota
            if (tortuga.colisionaCon(prota)) {
                System.out.println("¡Colisión con una tortuga!");
            }

            // Verifica colisiones con plataformas
            for (Plataforma p : plataformas) {
                if (p.colisionaCon(tortuga)) {
                    tortuga.detenerCaida(p.getY()); // Detiene la caída si colisiona con una plataforma
                    break; // Sale del bucle si ya colisionó
                }
            }
        }

     
     
        tiempoTranscurrido++;

        // Agrega gojos en 400,600 y tambien genera nuevos gojos
        if (tiempoTranscurrido >= tiempoEntreApariciones && contadorGojos < 5) {
            gojos.add(new Gojos(400, 600)); // Posición específica
            contadorGojos++;
            tiempoTranscurrido = 0;
        }

        // Mueve y dibuja los Gojos
        for (Gojos gojo : gojos) {
            gojo.mover();
            this.entorno.dibujarImagen(gojo.getImagen(), gojo.getX(), gojo.getY(), 0);
            
            // Verifica colisiones con el Prota
            if (gojo.colisionaCon(prota)) {
                System.out.println("choque");
            }

            // Verifica colisiones con plataformas
            for (Plataforma p : plataformas) {
                if (p.colisionaCon(gojo)) {
                    gojo.detenerCaida(p.getY()); // Detiene la caída si colisiona con una plataforma
                    break;
                }
        }

        // Mueve y dibuja la bolita
        this.prota.moverBolita();
        Bolita bolita = this.prota.getBolita();
        if (bolita != null) {
            this.entorno.dibujarImagen(bolita.getImagenBolita(), bolita.getX(), bolita.getY(), 0);
            
            // Verifica colisiones entre la bolita y cada tortuga
            Iterator<Tortuga> iter = tortugas.iterator();
            while (iter.hasNext()) {
                Tortuga tortuga = iter.next();
                if (colisiona(bolita, tortuga)) {
                    // Si hay colisión, elimina la tortuga de la lista
                    iter.remove();
                }
            }
        }
    }
    }

    // Método para verificar colisión entre la Bolita y una Tortuga
    private boolean colisiona(Bolita bolita, Tortuga tortuga) {
        return bolita.getX() < tortuga.getX() + 50 && bolita.getX() + 20 > tortuga.getX() &&
               bolita.getY() < tortuga.getY() + 50 && bolita.getY() + 20 > tortuga.getY();
    }

    
    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}

