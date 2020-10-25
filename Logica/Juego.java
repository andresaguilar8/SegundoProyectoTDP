package Logica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class Juego {

	private Celda [][] matriz;
	private int tamanio, totalNumeros;
	private boolean formatoValido = true;
	
	public Juego() {
		this.tamanio = 9;
		this.totalNumeros = 0;				
		String ruta = "generadorSudoku.txt";
        if (esArchivoValido(ruta))
        	crearJuego(ruta);
	}
	
	/**
	 * valida que el archivo cumpla con el formato
	 * @param ruta del archivo
	 * @return verdadero en caso de que el archivo cumpla con el formato, falso en caso contrario
	 */
	private boolean esArchivoValido(String ruta) {
		InputStream input = Juego.class.getClassLoader().getResourceAsStream(ruta);
		InputStreamReader isr = new InputStreamReader(input);
		BufferedReader bf = new BufferedReader(isr);
		String linea;
        String aux [];
        int numeroLeido;
        int cantLineas = 0;
        try {
			while( (linea = bf.readLine()) != null && formatoValido) {
				aux = linea.split(" ");
				if(aux.length == this.tamanio) {
					for(int i = 0; i < aux.length && formatoValido; i++) {
						numeroLeido = Integer.parseInt(aux[i]);
						if (!(numeroLeido > 0) && (numeroLeido <= 9)) 
							formatoValido = false;
					}
				}
				else 
					formatoValido = false;
				cantLineas++;
				if (cantLineas > this.tamanio) 
					formatoValido = false;
			}
	    }
		catch(IOException | NumberFormatException e) {
			formatoValido = false;
		}
		return formatoValido;
	}
	
	/**
	 * inicializa la matriz de celdas de acuerdo al numero leido en el archivo
	 * @param ruta del archivo
	 */
	private void crearJuego(String ruta) {				
		InputStream input = Juego.class.getClassLoader().getResourceAsStream(ruta);
		InputStreamReader isr = new InputStreamReader(input);
		BufferedReader bf = new BufferedReader(isr);
        String linea;
        String aux [];
        int numeroLeido;
        int fila = 0;
        matriz = new Celda [tamanio][tamanio];
        Celda c;
        try {
			while( (linea = bf.readLine()) != null) {
				aux = linea.split(" ");
					for(int i = 0; i < aux.length && formatoValido; i++) {
						numeroLeido = Integer.parseInt(aux[i]);
							c = new Celda();
							matriz[fila][i] = c;
				        	c.setValor(numeroLeido);
				        	c.setFila(fila);
				        	c.setCol(i);
							totalNumeros++;
					}
				fila++;	
			}
		}
		catch(IOException e) {
		}
	}
	
	public boolean formatoValido() {
		return formatoValido;
	}
	
	/**
	 * valida que sea una solución sudoku
	 * @return verdadero en caso de que sea valido, falso en caso contrario
	 */
	public boolean validarSudoku() {
		boolean toReturn = true;
		if (formatoValido) {
			for (int fila = 0; fila < tamanio && toReturn == true; fila++)
				for (int col = 0; col < tamanio && toReturn == true; col++)
					if (!cumpleConLasReglas(this.getCelda(fila, col))) 
						toReturn = false;
		}
		else
			toReturn = false;
		return toReturn;
	}
	
	/**
	 * Elimina aleatoriamente cinco celdas por fila manera que el sudoku este listo para ser jugado.
	 */
	public void eliminarCeldasParaComenzar() {
		int fila = 0;
		int rnd1, rnd2, rnd3, rnd4, rnd5;
		Random rndCol = new Random();
		while (fila < tamanio) {
			rnd1 = rndCol.nextInt(9);
			rnd2 = rndCol.nextInt(9);
			rnd3 = rndCol.nextInt(9);
			rnd4 = rndCol.nextInt(9);
			rnd5 = rndCol.nextInt(9);
			
			/* me aseguro de que los cinco numeros random sean distintos para eliminar cinco celdas por fila */
			while ((rnd1 == rnd2) || (rnd1 == rnd3) || (rnd1 == rnd4) || (rnd1 == rnd5) || (rnd2 == rnd3) || (rnd2 == rnd4) || (rnd2 == rnd5) || (rnd3 == rnd4) 
				|| (rnd3 == rnd5) || (rnd4 == rnd5))
			{
				rnd1 = rndCol.nextInt(9);
				rnd2 = rndCol.nextInt(9);
				rnd3 = rndCol.nextInt(9);
				rnd4 = rndCol.nextInt(9);
				rnd5 = rndCol.nextInt(9);
			}				
			
			totalNumeros -= 5;
			matriz[fila][rnd1] = null;
			matriz[fila][rnd2] = null;
			matriz[fila][rnd3] = null;
			matriz[fila][rnd4] = null;
			matriz[fila][rnd5] = null;
			
			fila++;
		}
	}
	
	public void setCelda(int fila, int col, Celda c) {
		matriz[fila][col] = c;
		totalNumeros++;
	}
	
	/**
	 * verifica que la celda pasada por parametro no esté rompiendo las reglas del juego
	 * @param c, celda a verificar
	 * @return verdadero si la celda esta rompiendo las reglas, falso en caso contrario
	 */
	public boolean cumpleConLasReglas(Celda c) {
		return !(estaEnFila(c) || estaEnColumna(c) || estaEnCuadrante(c));
	}
	
	public void accionar(Celda c) {
		c.actualizar();
	}
	
	/**
	 * verifica si el valor de la celda pasada por parametro ya se encontraba en la fila
	 * @param c, celda a verificar
	 * @return verdadero si el valor de la celda ya estaba, falso en caso contrario
	 */
	private boolean estaEnFila(Celda c) {
		boolean toReturn = false;
		for (int i = 0; i < this.tamanio && toReturn == false; i++)
			if (c.getCol() != i) {
				if (this.matriz[c.getFila()][i] != null)
					if (this.matriz[c.getFila()][i].getValor() == c.getValor())
						toReturn = true;
			}
		return toReturn;
	}
	
	/**
	 * verifica si el valor de la celda pasada por parametro ya se encontraba en la columna
	 * @param c, celda a verificar
	 * @return verdadero si el valor de la celda ya estaba, falso en caso contrario
	 */
	private boolean estaEnColumna(Celda c) {
		boolean toReturn = false;
		for (int i = 0; i < this.tamanio && toReturn == false; i++)
			if (c.getFila() != i) {
				if (this.matriz[i][c.getCol()] != null)
						if (this.matriz[i][c.getCol()].getValor() == c.getValor())
							toReturn = true;
			}
		return toReturn;
	}
	
	/**
	 * hace un chequeo de si el usuario gano una vez que la matriz esté llena de numeros
	 * @return verdadero si el usuario gano, falso en caso contrario
	 */
	public boolean gano() {
		boolean toReturn = false;
		if (totalNumeros == 81) 
			if (validarSudoku())
				toReturn = true;
		return toReturn;
	}
	
	/**
	 * controla si la celda esta repetida en el cuadrante.
	 * @param fila, fila de la celda a chequear.
	 * @param col, columna de la celda a chequear.
	 * @param c, celda a chequear.
	 * @return verdadero si la celda se repite, falso en caso contrario
	 */
	private boolean seRepite(int fila, int col, Celda c) {
		boolean seRepite = false;
		if (matriz[fila][col] != null)
			if (fila != c.getFila() || col != c.getCol())
				if (matriz[fila][col].getValor() == c.getValor()) 
					seRepite = true;
		return seRepite;
	}
	
	/**
	 * En base al numero de fila y columna que la celda pasada como parametro contenga, realiza el recorrido del cuadrante al que la celda pertenezca para ver si el valor de la celda ya estaba.
	 * @param c, celda a chequear
	 * @return verdadero si esta repetida, falso en caso contrario.
	 */
	public boolean estaEnCuadrante(Celda c) {
		boolean toReturn = false;
		int fila = c.getFila();
		int col = c.getCol();
		
		if ((fila == 0) | (fila == 3) | (fila == 6)) {
			if ((col == 0) | (col == 3) | (col == 6)) {
				for (int i = fila; i <= (fila+2) && toReturn == false; i++) 
					for (int j = col; j <= (col+2) && toReturn == false; j++) 
						toReturn = seRepite(i, j, c);
			}
			if ((col == 1) || (col == 4) || (col == 7)) {
				for (int i = fila; i <= (fila+2) && toReturn == false; i++) 
					for (int j = col-1; j <= (col+1) && toReturn == false; j++) 
						toReturn = seRepite(i, j, c);		
			}
			if ((col == 2) || (col == 5) || (col == 8)) {
				for (int i = fila; i <= fila+2 && toReturn == false; i++) 
					for (int j = col-2; j <= col && toReturn == false; j++) 
						toReturn = seRepite(i, j, c);			
				}
		}
		else
			if ((fila == 1) || (fila == 4) || (fila == 7)) {
				if ((col == 0) || (col == 3) || (col == 6)) {
					for (int i = fila-1; i <= fila+1 && toReturn == false; i++)
						for (int j = col; j <= col+2 && toReturn == false; j++)
							toReturn = seRepite(i, j, c);			
				}				
				if ((col == 1) || (col == 4) || (col == 7)) {
					for (int i = fila-1; i <= fila+1 && toReturn == false; i++) 
						for (int j = col-1; j <= col+1 && toReturn == false; j++) 
							toReturn = seRepite(i, j, c);
				}
				if ((col == 2) || (col == 5) || (col == 8)) {
					for (int i = fila-1; i <= fila+1 && toReturn == false; i++)
						for (int j = col-2; j <= col && toReturn == false; j++)
							toReturn = seRepite(i, j, c);
				}			
			}
			else
				if ((fila == 2) || (fila == 5) || (fila == 8)) {
					if ((col == 0) || (col == 3) || (col == 6)) {
						for (int i = fila-2; i <= fila && toReturn == false; i++) 
							for (int j = col; j <= col+2 && toReturn == false; j++) 
								toReturn = seRepite(i, j, c);		
					}
					if ((col == 1) || (col == 4) || (col == 7)) {
						for (int i = fila-2; i <= fila && toReturn == false; i++) 
							for (int j = col-1; j <= col+1 && toReturn == false; j++) 
								toReturn = seRepite(i, j, c);
					}				
					if ((col == 2) || (col == 5) || (col == 8)) {
						for (int i = fila-2; i <= fila && toReturn == false; i++) 
							for (int j = col-2; j <= col && toReturn == false; j++) 
								toReturn = seRepite(i, j, c);
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
