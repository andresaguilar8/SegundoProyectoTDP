package Logica;

public class NumerosReloj   {

	private EntidadGrafica izquierda, derecha;
	
	public NumerosReloj(EntidadGrafica izquierda, EntidadGrafica derecha) {
		this.izquierda = izquierda;
		this.derecha = derecha;
	}
	
	public EntidadGrafica getIzq() {
		return izquierda;
	}
	
	public EntidadGrafica getDer() {
		return derecha;
	}
}
