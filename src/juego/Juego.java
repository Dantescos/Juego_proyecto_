package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;
import java.util.ArrayList;
import java.util.Random;

public class Juego extends InterfaceJuego {
    private Entorno entorno;
    private Prota prota;
    private ArrayList<Tortuga> tortugas;
    private Random random;
    private ArrayList<Plataforma> plataformas;

    public Juego() {
        // Inicializa el objeto entorno
        this.entorno = new Entorno(this, "garcia mendez-rivero-gimenez", 800, 600);
        
        // Colocamos al prota en el centro
        this.prota = new Prota(14, 450);
        
        // Inicia el juego
        this.entorno.iniciar();
        this.tortugas = new ArrayList<>();
        this.random = new Random();
        
        // Generamos un número aleatorio de tortugas
        for (int i = 0; i < random.nextInt(3) + 2; i++) {
            double x = random.nextDouble() * 800; // Posición aleatoria en X
            double y = 50; // Altura inicial (cima de la pantalla)
            tortugas.add(new Tortuga(x, y));
        }
        
        this.plataformas = new ArrayList<>();

		// Definir plataformas para una pantalla de 800x600
		// Primera fila (una plataforma)
		plataformas.add(new Plataforma(400, 100, 150, 20)); // Centro superior

		// Segunda fila (dos plataformas)
		plataformas.add(new Plataforma(250, 200, 150, 20));
		plataformas.add(new Plataforma(550, 200, 150, 20));

		// Tercera fila (tres plataformas)
		plataformas.add(new Plataforma(150, 300, 150, 20));
		plataformas.add(new Plataforma(400, 300, 150, 20));
		plataformas.add(new Plataforma(650, 300, 150, 20));

		// Cuarta fila (cuatro plataformas)
		plataformas.add(new Plataforma(100, 400, 150, 20));
		plataformas.add(new Plataforma(300, 400, 150, 20));
		plataformas.add(new Plataforma(500, 400, 150, 20));
		plataformas.add(new Plataforma(700, 400, 150, 20));

		// Quinta fila (cinco plataformas)
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

		this.prota.aplicarGravedad(); // Aplica la gravedad en cada frame

        
        // Dibuja Fukuma Mizushi detrás de Prota si es visible
        if (this.prota.isFukumaVisible()) {
            this.entorno.dibujarImagen(this.prota.getFukumaMizushiImagen(), this.prota.getX(), this.prota.getY(), 0);
        }

    
        this.entorno.dibujarImagen(this.prota.getImagen(), this.prota.getX(), this.prota.getY(), this.prota.getAngulo());

       
        for (Tortuga tortuga : tortugas) {
            tortuga.mover(); 
            this.entorno.dibujarImagen(tortuga.getImagen(), tortuga.getX(), tortuga.getY(), 0);
            
           
            if (tortuga.colisionaCon(prota)) {
                System.out.println("¡Colisión con una tortuga!");
            }
        }
        
        
		// Dibujar y manejar las plataformas
		for (Plataforma p : plataformas) {
			p.dibujar(entorno);

			if (p.colisionaCon(this.prota)) {
				System.out.println("¡Colisión con la plataforma!");
				// Aquí puedes manejar lo que sucede cuando hay una colisión
			}
		}

        // Mueve y dibuja la bolita
        this.prota.moverBolita();
        Bolita bolita = this.prota.getBolita();
        if (bolita != null) {
            this.entorno.dibujarImagen(bolita.getImagenBolita(), bolita.getX(), bolita.getY(), 0);
        }
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
    }
}


