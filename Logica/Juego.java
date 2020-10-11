package Logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Juego {

	private Celda [][] matriz;
	private int tamanio, totalNumeros;
	
	public Juego() {
		
	}
	
	public Juego(String archivo) throws IOException {
		this.tamanio = 9;
		totalNumeros = 0;
		matriz = new Celda [tamanio][tamanio];
		Celda c;
		File f = new File(archivo);
        Scanner s = new Scanner(f);
        int numeroLeido, fila, col, cantLeidos;
        fila = 0;
        col = 0;
        cantLeidos = 0;
        while (s.hasNextInt()) { //mientras queden enteros por leer
            numeroLeido = s.nextInt(); //se lee un entero del archivo
            cantLeidos++;
            c = new Celda();
            matriz[fila][col] = c;
        	c.setValor(numeroLeido);
        	c.setFila(fila);
        	c.setCol(col);
        	totalNumeros++;
        	if (cantLeidos < 82)
        		if (col == tamanio - 1) {
        			fila++;
        			col = 0;
        		}
        		else
        			col++;   			
        }
	}
	
	public boolean validarSudoku() {
		boolean toReturn = true;
		for (int fila = 0; fila < tamanio && toReturn == true; fila++)
			for (int col = 0; col < tamanio && toReturn == true; col++)
				if (!cumpleConLasReglas(this.getCelda(fila, col))) {
					toReturn = false;
				}
		return toReturn;
	}
	
	public int totalNumeros() {
		return totalNumeros;
	}
	
	public void eliminarCeldasParaComenzar() {
		int fila = 0;
		Random rndCol = new Random();
		int rnd1,rnd2,rnd3,rnd4;
		while (fila < tamanio) {
			rnd1 = rndCol.nextInt(9);
			rnd2 = rndCol.nextInt(9);
			rnd3 = rndCol.nextInt(9);
			rnd4 = rndCol.nextInt(9);
			
			while ((rnd1 == rnd2) || (rnd1 == rnd3) || (rnd1 == rnd4) || (rnd2 == rnd3) || (rnd2 == rnd4) || (rnd3 == rnd4)) {
				rnd1 = rndCol.nextInt(9);
				rnd2 = rndCol.nextInt(9);
				rnd3 = rndCol.nextInt(9);
				rnd4 = rndCol.nextInt(9);
			}
				
			totalNumeros -= 4;
			matriz[fila][rnd1] = null;
			matriz[fila][rnd2] = null;
			matriz[fila][rnd3] = null;
			matriz[fila][rnd4] = null;
			
			fila++;
		}
	}
	
	public void setCelda(int fila, int col, Celda c) {
		matriz[fila][col] = c;
		totalNumeros++;
	}
	
	public boolean cumpleConLasReglas(Celda c) {
		return !(estaEnFila(c) || estaEnColumna(c) || estaEnCuadrante(c));
	}
	
	public void accionar(Celda c) {
		c.actualizar();
	}
	
	public boolean estaEnFila(Celda c) {
		boolean toReturn = false;
		for (int i = 0; i < this.tamanio && toReturn == false; i++)
			if (c.getCol() != i) {
				if (this.matriz[c.getFila()][i] != null)
					if (this.matriz[c.getFila()][i].getValor() == c.getValor())
						toReturn = true;
			}
		return toReturn;
	}
	
	public boolean estaEnColumna(Celda c) {
		boolean toReturn = false;
		for (int i = 0; i < this.tamanio && toReturn == false; i++)
			if (c.getFila() != i) {
				if (this.matriz[i][c.getCol()] != null)
						if (this.matriz[i][c.getCol()].getValor() == c.getValor())
							toReturn = true;
			}
		return toReturn;
	}
		
	public Celda hayOtroIgualUnicoEnFila(Celda c) {
		int fila = c.getFila();
		int col = c.getCol();
		int contador = 0;
		Celda toReturn = null;
		boolean cumple = true;
		for (int i = 0; i < tamanio && cumple == true; i++) {
			if (matriz[fila][i] != null)
				if (c.getCol() != i)
					if (matriz[fila][i].getValor() == c.getValor()) {
						contador++;
						toReturn = matriz[fila][i];
						if (contador > 1) {
							toReturn = null;
							cumple = false;
						}
					}
		}
		return toReturn;
	}
	
	public boolean gano() {
		boolean toReturn = false;
		if (totalNumeros == 81)
			if (validarSudoku())
				toReturn = true;
		return toReturn;
	}
	
	private boolean estaMasDeUnaVez(int fila, int col, Celda c) {
		boolean toReturn = false;
		if (matriz[fila][col] != null)
			if (fila != c.getFila() || col != c.getCol())
				if (matriz[fila][col].getValor() == c.getValor()) 
					toReturn = true;
		return toReturn;
	}
	
	/*Recorre un cuadrante desde la primer celda en base al numero de fila y columna de la celda del parametro retornando falso en caso de celdas con mismo valor repetidas
	 * 
	 */
	public boolean estaEnCuadrante(Celda c) {
		boolean toReturn = false;
		int fila = c.getFila();
		int col = c.getCol();
		
		if ((fila == 0) | (fila == 3) | (fila == 6)) {
			if ((col == 0) | (col == 3) | (col == 6)) {
				for (int i = fila; i <= (fila+2) && toReturn == false; i++) 
					for (int j = col; j <= (col+2) && toReturn == false; j++) 
						toReturn = estaMasDeUnaVez(i, j, c);
			}
			if ((col == 1) || (col == 4) || (col == 7)) {
				for (int i = fila; i <= (fila+2) && toReturn == false; i++) 
					for (int j = col-1; j <= (col+1) && toReturn == false; j++) 
						toReturn = estaMasDeUnaVez(i, j, c);		
			}
			if ((col == 2) || (col == 5) || (col == 8)) {
				for (int i = fila; i <= fila+2 && toReturn == false; i++) 
					for (int j = col-2; j <= col && toReturn == false; j++) 
						toReturn = estaMasDeUnaVez(i, j, c);			
				}
		}
		else
			if ((fila == 1) || (fila == 4) || (fila == 7)) {
				if ((col == 0) || (col == 3) || (col == 6)) {
					for (int i = fila-1; i <= fila+1 && toReturn == false; i++)
						for (int j = col; j <= col+2 && toReturn == false; j++)
							toReturn = estaMasDeUnaVez(i, j, c);			
				}				
				if ((col == 1) || (col == 4) || (col == 7)) {
					for (int i = fila-1; i <= fila+1 && toReturn == false; i++) 
						for (int j = col-1; j <= col+1 && toReturn == false; j++) 
							toReturn = estaMasDeUnaVez(i, j, c);
				}
				if ((col == 2) || (col == 5) || (col == 8)) {
					for (int i = fila-1; i <= fila+1 && toReturn == false; i++)
						for (int j = col-2; j <= col && toReturn == false; j++)
							toReturn = estaMasDeUnaVez(i, j, c);
				}			
			}
			else
				if ((fila == 2) || (fila == 5) || (fila == 8)) {
					if ((col == 0) || (col == 3) || (col == 6)) {
						for (int i = fila-2; i <= fila && toReturn == false; i++) 
							for (int j = col; j <= col+2 && toReturn == false; j++) 
								toReturn = estaMasDeUnaVez(i, j, c);		
					}
					if ((col == 1) || (col == 4) || (col == 7)) {
						for (int i = fila-2; i <= fila && toReturn == false; i++) 
							for (int j = col-1; j <= col+1 && toReturn == false; j++) 
								toReturn = estaMasDeUnaVez(i, j, c);
					}				
					if ((col == 2) || (col == 5) || (col == 8)) {
						for (int i = fila-2; i <= fila && toReturn == false; i++) 
							for (int j = col-2; j <= col && toReturn == false; j++) 
								toReturn = estaMasDeUnaVez(i, j, c);
					}
				}
			return toReturn;	
	}
	
	public Celda [][] getMatriz() {
		return matriz;
	}
	
	public Celda getCelda(int fila, int columna) {
		return matriz[fila][columna];
	}
	
	public int getCantidadFilas() {
		return this.tamanio;
	}
	
	public int getCantidadColumnas() {
		return this.tamanio;
	}
	
	public int totalNumerosEnMatriz() {
		return totalNumeros;
	}
}
