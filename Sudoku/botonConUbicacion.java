package Sudoku;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class botonConUbicacion extends JButton {

	private int fila, col;
	
	public botonConUbicacion(int fila, int col) {
		this.fila = fila;
		this.col = col;
	}
	
	public int getFila() {
		return fila;
	}
	
	public int getCol() {
		return col;
	}
}
