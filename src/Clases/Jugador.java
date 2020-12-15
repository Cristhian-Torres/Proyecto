package Clases;

import java.util.HashMap;

import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Jugador extends ObjetoJuego{
	private HashMap<String, Animacion> animaciones;
	private int xImagen;
	private int yImagen;
	private int altoImagen;
	private int anchoImagen;
	private String animacionActual;
	
	public Jugador(int x, int y, String nombreImagen, int velocidad, String animacionActual) {
		super(x, y, nombreImagen, velocidad);
		this.animacionActual = animacionActual;
		animaciones = new HashMap<String, Animacion>();
		iniciarAnimacion();
	}
	
	public void iniciarAnimacion() {
		Rectangle movimientoDerecha[]= {
				new Rectangle (0,156,70,100),
				new Rectangle (101,156,70,100),
				new Rectangle (211,156,70,97),
				new Rectangle (320,156,70,86),
				new Rectangle (412,156,70,92),
				new Rectangle (505,156,70,101),
				new Rectangle (609,156,70,100),
				new Rectangle (713,156,70,107),
				new Rectangle (821,156,70,116)
			};
			
			Animacion animacionDerecha = new Animacion(0.1, movimientoDerecha);
			animaciones.put("derecha", animacionDerecha);
			
			Rectangle movimientoIzquierda[]= {
				new Rectangle (0,236,70,116),
				new Rectangle (116,236,70,107),
				new Rectangle (224,236,70,100),
				new Rectangle (332,236,70,101),
				new Rectangle (432,236,70,92),
				new Rectangle (525,236,70,86),
				new Rectangle (617,236,70,97),
				new Rectangle (726,236,70,100),
				new Rectangle (836,236,70,100)	
			};
			
			Animacion animacionIzquierda = new Animacion(0.1, movimientoIzquierda);
			animaciones.put("izquierda", animacionIzquierda);
			
			Rectangle movimientoAbajo[]= {
					new Rectangle (0,236,70,116),
					new Rectangle (116,236,70,107),
					new Rectangle (224,236,70,100),
					new Rectangle (332,236,70,101),
					new Rectangle (432,236,70,92),
					new Rectangle (525,236,70,86),
					new Rectangle (617,236,70,97),
					new Rectangle (726,236,70,100),
					new Rectangle (836,236,70,100)	
				};
			
			Animacion animacionAbajo = new Animacion(0.1, movimientoAbajo);
			animaciones.put("abajo", animacionAbajo);
			
			Rectangle movimientoArriba[]= {
					new Rectangle (0,236,70,116),
					new Rectangle (116,236,70,107),
					new Rectangle (224,236,70,100),
					new Rectangle (332,236,70,101),
					new Rectangle (432,236,70,92),
					new Rectangle (525,236,70,86),
					new Rectangle (617,236,70,97),
					new Rectangle (726,236,70,100),
					new Rectangle (836,236,70,100)
			};
			
			Animacion animacionArriba = new Animacion(0.1, movimientoArriba);
			animaciones.put("arriba", animacionArriba);
			
			Rectangle descansoIzquierda[]= {
					new Rectangle (836,236,70,100),
					new Rectangle (836,236,70,100),
					new Rectangle (836,236,70,100),
					new Rectangle (836,236,70,100)	
			};
			
			Animacion descansarIzquierda = new Animacion(0.1, descansoIzquierda);
			animaciones.put("izquierdaDescanso", descansarIzquierda);
			
			Rectangle descansoDerecha[]= {
					new Rectangle (0,156,70,100),
					new Rectangle (0,156,70,100),
					new Rectangle (0,156,70,100),
					new Rectangle (0,156,70,100)
			};
			
			Animacion descansarDerecha = new Animacion(0.1, descansoDerecha);
			animaciones.put("derechaDescanso", descansarDerecha);
			
			Rectangle descansoAbajo[]= {
					new Rectangle (0,156,70,100),
					new Rectangle (0,156,70,100),
					new Rectangle (0,156,70,100),
					new Rectangle (0,156,70,100)
					
			};
			
			Animacion descansarAbajo = new Animacion(0.1, descansoAbajo);
			animaciones.put("abajoDescanso", descansarAbajo);
			
			Rectangle descansoArriba[]= {
					new Rectangle (0,156,70,100),
					new Rectangle (0,156,70,100),
					new Rectangle (0,156,70,100),
					new Rectangle (0,156,70,100)
					
			};
			
			Animacion descansarArriba = new Animacion(0.1, descansoArriba);
			animaciones.put("arribaDescanso", descansarArriba);
			
	}
	
	public void calcularFrame(double t) {
		Rectangle coordenadas = animaciones.get(animacionActual).calcularFrameActual(t);
		this.xImagen = (int)coordenadas.getX();
		this.yImagen = (int)coordenadas.getY();
		this.anchoImagen = (int)coordenadas.getHeight();
		this.altoImagen = (int)coordenadas.getWidth();
	}
	
	@Override
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get(nombreImagen), xImagen, yImagen, anchoImagen, altoImagen, x, y, anchoImagen, altoImagen);
	}
	
	@Override
	public void mover() {
		if(Juego.derecha)
			x+=velocidad;
		if(Juego.izquierda) 
			x-=velocidad;
		if(Juego.arriba) 
			y-=velocidad;
		if(Juego.abajo) 
			y+=velocidad;
		if(Juego.H)
			x+=velocidad;
		if(Juego.G) 
			x-=velocidad;
		if(Juego.Y) 
			y-=velocidad;
		if(Juego.B) 
			y+=velocidad;
	}

	public String getAnimacionActual() {
		return animacionActual;
	}

	public void setAnimacionActual(String animacionActual) {
		this.animacionActual = animacionActual;
	}
}