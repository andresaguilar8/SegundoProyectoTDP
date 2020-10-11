package Logica;

import javax.swing.ImageIcon;

public class Celda {

	private Integer valor;
	private EntidadGrafica entidadgrafica;
	private int fila, columna;
	
	public Celda() {
		this.valor = null;
		this.entidadgrafica = new EntidadGrafica();
	}
	
	public void actualizar() {
		if (valor < 9) {
			valor++;
		}
		else
			valor = 1;
		String ruta = entidadgrafica.getImagen(valor);
		ImageIcon img = new ImageIcon(this.getClass().getResource(ruta));
		entidadgrafica.setGrafico(img);
	}
	
	public EntidadGrafica getEntidadGrafica() {
		return entidadgrafica;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
		entidadgrafica.actualizar(valor);
	}
	
	public void setFila(int fila) {
		this.fila = fila;
	}
	
	public void setCol(int col) {
		this.columna = col;
	}
	
	public int getFila() {
		return fila;
	}
	
	public int getCol() {
		return columna;
	}
	
	public int getValor() {
		return valor;
	}
}
