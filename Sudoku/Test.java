package Sudoku;

import java.io.IOException;

public class Test {

	public static void main (String [] args) {
		Prueba p = new Prueba();
		try {
			p.crearMatrizConNumerosLeidosDeUnArchivoYimprimir("C:\\Users\\Andres\\eclipse-workspace\\Proyecto2\\src\\generadorSudoku.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
