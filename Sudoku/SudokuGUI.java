package Sudoku;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Logica.Celda;
import Logica.Juego;

public class SudokuGUI extends JFrame {

	private JPanel contentPane, panelIzquierdo, panelDerecho;
	private JButton[][] botonesJuego;
	private Juego juego;
	private JPanel [] paneles;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SudokuGUI frame = new SudokuGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SudokuGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 20, 889, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panelIzquierdo = new JPanel();
		panelIzquierdo.setBounds(5, 5, 500, 680);
     	panelIzquierdo.setLayout(new GridLayout(3,1));
     	panelIzquierdo.setVisible(true);
		panelDerecho = new JPanel();
		panelDerecho.setBounds(520, 60, 450, 429);
		
		panelDerecho.setLayout(new GridLayout(3,1));
		panelDerecho.setSize(240, 440);
		dividirPanel(panelDerecho);
		contentPane.setLayout(null);
		contentPane.add(panelIzquierdo);
		
		
		String archivo = "C:\\Users\\Andres\\Desktop\\src\\generadorSudoku.txt";
		try {
			juego = new Juego(archivo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		inicializarPaneles(panelIzquierdo);
		contentPane.add(panelDerecho);
		juego.eliminarCeldasParaComenzar();
		System.out.println("AC");
		crearBotones();
		
		System.out.println("TOTAL NUMEROS AL COMENZAR: "+juego.totalNumeros());
		
	}
	
	public void inicializarPaneles(JPanel panel) {
		Border bGreyLine = BorderFactory.createLineBorder(Color.BLACK, 2, false);
		paneles = new JPanel[9];
		for (int i = 0; i < paneles.length; i++) {
			paneles[i] = new JPanel();
			paneles[i].setLayout(new GridLayout(3,1));
			paneles[i].setBorder(bGreyLine);
			panel.add(paneles[i]);
		}
	}
	
	void dividirPanel(JPanel panel) {
		JPanel [] paneles = new JPanel[3];
		for (int i = 0 ; i < paneles.length; i++) {
			paneles[i] = new JPanel();
			panel.add(paneles[i]);
		}
		JLabel sudoku = new JLabel("SUDOKU");
		sudoku.setFont(new Font("Arial", Font.PLAIN, 50)); 
		paneles[0].add(sudoku);
		paneles[1].setLayout(new GridLayout(3,3));
	}
	
	public void crearBotones(int fila, int col, JPanel panel) {
		int auxFila = (fila+2);
		int auxColMax = (col+2);
		int auxColMin = col;
		while (fila <= auxFila) {
			
				
				botonesJuego[fila][col] = new botonConUbicacion(fila, col);
			
				botonesJuego[fila][col].setBackground(Color.white);
				panel.add(botonesJuego[fila][col]);
				
				if (col == auxColMax) {
					fila ++;
					col = auxColMin;
				}
				else 
					col++;
			}
			
		}
	
		
	void crearBotones() {
		botonesJuego = new botonConUbicacion[juego.getCantidadFilas()][juego.getCantidadColumnas()];
		
			crearBotones(0,0,paneles[0]);
			crearBotones(0,3,paneles[1]);
			crearBotones(0,6,paneles[2]);
			crearBotones(3,0,paneles[3]);
			crearBotones(3,3,paneles[4]);
			crearBotones(3,6,paneles[5]);
			crearBotones(6,0,paneles[6]);
			crearBotones(6,3,paneles[7]);
			crearBotones(6,6,paneles[8]);
		
		System.out.println("creo botones");
		
		for (int i = 0; i < juego.getCantidadFilas(); i++) {
			for (int j = 0; j < juego.getCantidadFilas(); j++) {
//				botonesJuego[i][j].setBackground(Color.white);
//				paneles[i].add(botonesJuego[i][j]);		
				if (juego.getCelda(i, j) != null) {
					ImageIcon img = juego.getCelda(i, j).getEntidadGrafica().getGrafico();
					System.out.println("columna de celda: "+juego.getCelda(i, j).getCol());
					botonesJuego[i][j].setIcon(img);
					botonesJuego[i][j].setBackground(Color.white);
					botonesJuego[i][j].setEnabled(false);
				}
			}	
		}
		for (int i = 0; i < juego.getCantidadFilas(); i++)
			for (int j = 0; j < juego.getCantidadFilas(); j++) {
				botonesJuego[i][j].addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						botonConUbicacion botonEvento = (botonConUbicacion) e.getSource();
						int col = botonEvento.getCol();
						int fila = botonEvento.getFila();
						Celda c = juego.getCelda(fila, col);
						if (c == null) {
							Celda nueva = new Celda();			
							nueva.setValor(1);							
							nueva.setCol(col);
							nueva.setFila(fila);
							juego.setCelda(fila, col, nueva);
							actualizarBoton(botonesJuego[nueva.getFila()][nueva.getCol()], nueva.getFila(), nueva.getCol(), nueva);
						}
						else {
							juego.accionar(c);
							actualizarBoton(botonesJuego[fila][col],fila,col,c);
						}
					}	
				});
			}
	}
	
	private void actualizarBoton (JButton boton, int fila, int col, Celda c) {
		boton.setBackground(Color.green);
		c = juego.getCelda(fila, col);
		ImageIcon img = c.getEntidadGrafica().getGrafico();
		boton.setIcon(img);
		actualizarBotones();
	}
	
	private void actualizarBotones() {
		Celda c;
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego.length; j++) {
				c = juego.getCelda(i, j);
				if (c != null) {
					if (juego.cumpleConLasReglas(c)) {
						if (botonesJuego[i][j].isEnabled() == true)
							botonesJuego[i][j].setBackground(Color.green);
					}
					else
						if (botonesJuego[i][j].isEnabled() == true)
							botonesJuego[i][j].setBackground(Color.red);
				}
			}
		}
	}
}	
		
	