package Sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Prueba {

	public void crearMatrizConNumerosLeidosDeUnArchivoYimprimir(String archivo) throws IOException {
		String matriz [][] = new String [9][9];
		String entero;
			FileReader f = new FileReader(archivo);
			BufferedReader b = new BufferedReader(f);
			while ((entero = b.readLine()) != null) {
				for (int i = 0; i < matriz.length; i++)
					for (int j = 0; j < matriz[0].length; j++)
						matriz[i][j] = entero;
		}
			for (int i = 0; i < matriz.length; i++)
				for (int j = 0; j < matriz[0].length; j++)
					System.out.println(matriz[i][j]);
	}
	public boolean validarArchivo() {
		return true;
	}
}
