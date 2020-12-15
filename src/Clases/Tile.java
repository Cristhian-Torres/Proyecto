package Clases;

import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;

public class Tile extends ObjetoJuego{
	private int imagenX;
	private int imagenY;
	private int tipoTile;
	private int velocidad = 6;
	
	public Tile(int tipoTile, int x, int y, String nombreImagen, int velocidad, int ancho, int alto) {
		super(x, y, nombreImagen, velocidad);
		this.alto = alto;
		this.ancho = ancho;
		switch(tipoTile) {
			case 1:
				this.imagenX = 0;
				this.imagenY = 0;
				break;
			case 2:
				this.imagenX = 290;
				this.imagenY = 0;
				break;
			case 3:
				this.imagenX = 579;
				this.imagenY = 0;
				break;
		}
	}

	public int getImagenX() {
		return imagenX;
	}

	public void setImagenX(int imagenX) {
		this.imagenX = imagenX;
	}

	public int getImagenY() {
		return imagenY;
	}

	public void setImagenY(int imagenY) {
		this.imagenY = imagenY;
	}
	
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get(nombreImagen),imagenX, imagenY, ancho, alto, x, y, 70, 70);
	}
	
	public void mover() {
		if(Juego.derecha || Juego.S || Juego.H) {
			x-=velocidad;
		}
		if(Juego.izquierda || Juego.A || Juego.G) {
			x+=velocidad;
		}	
		if(Juego.arriba || Juego.W || Juego.Y) {
			y+=velocidad;
		}
		if(Juego.abajo || Juego.X || Juego.B) {
			y-=velocidad;
		}
	}
}
