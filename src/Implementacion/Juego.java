package Implementacion;

import java.util.ArrayList;
import java.util.HashMap;

import Clases.Jugador;
import Clases.Jugador2;
import Clases.Tile;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Juego extends Application{
	private GraphicsContext graficos;
	private Canvas lienzo;
	private Scene escena;
	private Group root;
	private Jugador jugador;
	private Jugador2 jugador2;
	public static boolean arriba;
	public static boolean abajo;
	public static boolean derecha;
	public static boolean izquierda;
	public static boolean A;
	public static boolean X;
	public static boolean S;
	public static boolean W;
	public static boolean Y;
	public static boolean H;
	public static boolean G;
	public static boolean B;
	public static HashMap<String, Image> imagenes;
	private ArrayList<Tile> tiles;
	
	private int tilemap[][] = {
			{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
			{2,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
			{2,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
			{2,2,2,2,2,2,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
			{2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
			{2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
			{2,2,2,2,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
			{2,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
			{2,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
			{2,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
			{2,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
			{2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
			{2,1,1,1,1,1,2,1,1,2,1,1,1,1,2,1,1,1,1,1,1,1,1,2},
			{2,2,2,2,2,2,2,1,1,2,1,1,1,1,2,1,1,1,1,1,1,1,1,2},
			{2,1,1,1,1,1,1,1,1,2,1,1,1,1,2,1,1,1,1,1,1,1,1,2},
			{2,1,1,1,1,1,1,1,1,2,1,1,1,1,2,1,1,1,1,1,1,1,1,2},
			{2,1,1,2,2,2,2,2,2,2,1,1,1,1,2,1,1,1,1,1,1,1,1,2},
			{2,1,1,2,1,1,1,1,1,1,1,1,2,2,2,1,1,1,1,1,1,1,1,2},
			{2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2},
			{2,1,1,2,1,1,1,1,1,2,2,2,2,1,1,1,1,1,1,1,1,1,1,2},
			{2,1,1,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
			{2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
			{2,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2},
			{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}
	};
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage ventana) throws Exception{
		iniciarComponentes();
		eventos();
		ventana.setScene(escena);
		ventana.setTitle("MUNDO GOLEM");
		ventana.show();
		cicloJuego();
	}
	
	public void cicloJuego() {
		long tiempoInicial = System.nanoTime();
		//8long tiempoInicial = System.nanoTime();
		AnimationTimer animationTimer = new AnimationTimer() {
			public void handle(long tiempoActual) {
				double t = (tiempoActual - tiempoInicial) - 1000000000.0;
				actualizarEstado(t);
				pintar();
				
			}
		};
		

		animationTimer.start();
	}
	
	public void actualizarEstado(double t) {
		jugador.calcularFrame(t);
		jugador.mover();
		jugador2.calcularFrame(t);
		jugador2.mover();
		for(int i=0; i<tiles.size(); i++) {
			tiles.get(i).mover();
		}
	}
	
	public void iniciarComponentes() {
		imagenes = new HashMap<String, Image>();
		cargarImagenes();
		jugador = new Jugador(280, 280, "sprite", 3, "derechaDescanso");
		jugador2 = new Jugador2(70, 70, "sprite2", 3, "derechaDescanso");
		iniciarTileMap();
		root = new Group();
		escena = new Scene(root, 1200, 600);
		lienzo = new Canvas(1200, 600);
		root.getChildren().add(lienzo);
		graficos = lienzo.getGraphicsContext2D();
	}
	
	public void iniciarTileMap() {
		tiles = new ArrayList<Tile>();
		for(int i=0; i<tilemap.length; i++) {
			for(int j=0; j<tilemap.length; j++) {
				this.tiles.add(new Tile(tilemap[i][j], j*70, i*70, "tile", 0, 70, 70));
			}
		}
	}
	
	public void cargarImagenes() {
		imagenes.put("tile", new Image("Tile.png"));
		imagenes.put("sprite", new Image("Agua.png"));
		imagenes.put("sprite2", new Image("Agua.png"));
	}
	
	public void pintar() {
		for(int i=0; i<tiles.size(); i++) {
			tiles.get(i).pintar(graficos); 
		}
		jugador.pintar(graficos);
		jugador2.pintar(graficos);
	}
	
	public void eventos() {
		escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent evento){
				switch(evento.getCode().toString()){
					case "RIGHT":
						derecha = true;
						jugador.setAnimacionActual("derecha");
						break;
					case "LEFT":
						izquierda = true;
						jugador.setAnimacionActual("izquierda");
						break;
					case "UP":
						arriba = true;
						jugador.setAnimacionActual("arriba");
						break;
					case "DOWN":
						abajo = true;
						jugador.setAnimacionActual("abajo");
						break;
					case "S":
						S = true;
						jugador2.setAnimacionActual("derecha");
						break;
					case "A":
						A = true;
						jugador2.setAnimacionActual("izquierda");
						break;
					case "W":
						W = true;
						jugador2.setAnimacionActual("arriba");
						break;
					case "X":
						X = true;
						jugador2.setAnimacionActual("abajo");
						break;
					case "H":
						H = true;
						jugador.setAnimacionActual("derecha");
						break;
					case "G":
						G = true;
						jugador.setAnimacionActual("izquierda");
						break;
					case "Y":
						Y = true;
						jugador.setAnimacionActual("arriba");
						break;
					case "B":
						B = true;
						jugador.setAnimacionActual("abajo");
						break;
					case "SPACE":
						jugador.setVelocidad(15);
						break;
				}
			}
		});
		
		escena.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent evento){
				switch(evento.getCode().toString()){
					case "RIGHT":
						derecha = false;
						jugador.setAnimacionActual("derechaDescanso");
						break;
					case "LEFT":
						izquierda = false;
						jugador.setAnimacionActual("izquierdaDescanso");
						break;
					case "UP":
						arriba = false;
						jugador.setAnimacionActual("arribaDescanso");
						break;
					case "DOWN":
						abajo = false;
						jugador.setAnimacionActual("abajoDescanso");
						break;
					case "S":
						S = false;
						jugador2.setAnimacionActual("derechaDescanso");
						break;
					case "A":
						A = false;
						jugador2.setAnimacionActual("izquierdaDescanso");
						break;
					case "W":
						W = false;
						jugador2.setAnimacionActual("arribaDescanso");
						break;
					case "X":
						X = false;
						jugador2.setAnimacionActual("abajoDescanso");
						break;
					case "H":
						H = false;
						jugador.setAnimacionActual("derechaDescanso");
						break;
					case "G":
						G = false;
						jugador.setAnimacionActual("izquierdaDescanso");
						break;
					case "Y":
						Y = false;
						jugador.setAnimacionActual("arribaDescanso");
						break;
					case "B":
						B = false;
						jugador.setAnimacionActual("abajoDescanso");
						break;
					case "SPACE":
						jugador.setVelocidad(3);
						break;
				}
			}
		});
	}
}
