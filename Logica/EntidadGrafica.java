package Logica;

import javax.swing.ImageIcon;

public class EntidadGrafica {

	private ImageIcon grafico;
	private String[] imagenes;
	private int valor;
	
	public EntidadGrafica() {
		this.grafico = new ImageIcon();
		this.imagenes = new String []{"/imagenes/0.png", "/imagenes/1.png", "/imagenes/2.png", "/imagenes/3.png", "/imagenes/4.png", "/imagenes/5.png", "/imagenes/6.png", "/imagenes/7.png", "/imagenes/8.png", "/imagenes/9.png"};
		valor = 0;
	}
	
	public void actualizar(int indice) {
		ImageIcon imageIcon;
		if ((indice-1 < this.imagenes.length) && (indice >= 0)) {
			imageIcon = new ImageIcon(this.getClass().getResource(this.imagenes[indice]));
			this.grafico.setImage(imageIcon.getImage());
		}
	}
	
	public void setGrafico(ImageIcon grafico) {
		this.grafico = grafico;
	}
	
	public String getImagen(int indice) {
		return imagenes[indice];
	}
	
	public ImageIcon getGrafico() {
		return grafico;
	}
	
	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
}
