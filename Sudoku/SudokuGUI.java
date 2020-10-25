package Sudoku;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import Logica.Celda;
import Logica.EntidadGrafica;
import Logica.Juego;
import Logica.NumerosReloj;

@SuppressWarnings("serial")
public class SudokuGUI extends JFrame {

	private JPanel contentPane, panelIzquierdo, panelDerecho, panelTiempo;
	private JButton[][] botonesJuego;
	private Juego juego;
	private JPanel [] paneles;
	private JLabel [] reloj;
	private EntidadGrafica izqSegundos, derSegundos, izqMinutos, derMinutos;
	private Timer timer;
	private NumerosReloj minutos, segundos;
	
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
		juego = new Juego();
		if (juego.formatoValido() && juego.validarSudoku()) {
//			if (juego.validarSudoku()) { 
				juego.eliminarCeldasParaComenzar();
				inicializar();
				iniciarTiempo();
//			}
		}
		else
			terminar(0);
	}
	
	/**
	 * inicializa el reloj que lleva el tiempo transcurrido de juego.
	 */
	private void iniciarTiempo() {		
		izqMinutos = new EntidadGrafica();
		izqSegundos = new EntidadGrafica();
		derMinutos = new EntidadGrafica();
		derSegundos = new EntidadGrafica();
		
		minutos = new NumerosReloj(izqMinutos, derMinutos);
		segundos = new NumerosReloj(izqSegundos, derSegundos);
		
		reloj = new JLabel[5];
		
		minutos.getIzq().actualizar(0);
		minutos.getDer().actualizar(0);	
		segundos.getIzq().actualizar(0);
		segundos.getDer().actualizar(0);
		
		
		reloj[0] = new JLabel(minutos.getIzq().getGrafico());
		reloj[1] = new JLabel(minutos.getDer().getGrafico());
		reloj[2] = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/dosPuntos.png")));
		reloj[3] = new JLabel(segundos.getIzq().getGrafico());
		reloj[4] = new JLabel(segundos.getDer().getGrafico());
		
		for (int i = 0; i < reloj.length; i++)
			panelTiempo.add(reloj[i]);
		
		timer = new Timer();
		
		TimerTask tarea = new TimerTask() {		
				public void run() {
					
					if (segundos.getDer().getValor() < 9) {
						segundos.getDer().setValor(segundos.getDer().getValor() + 1);
						segundos.getDer().actualizar(segundos.getDer().getValor());		
					}
					else {
						segundos.getDer().setValor(0);
						segundos.getDer().actualizar(segundos.getDer().getValor());
						segundos.getIzq().setValor(segundos.getIzq().getValor() + 1);
						segundos.getIzq().actualizar(segundos.getIzq().getValor());
					}

					if (segundos.getIzq().getValor() == 6) {
						minutos.getDer().setValor(minutos.getDer().getValor() + 1);
						minutos.getDer().actualizar(minutos.getDer().getValor());
						segundos.getIzq().setValor(0);
						segundos.getIzq().actualizar(segundos.getIzq().getValor());
					}
					
					if (minutos.getDer().getValor() == 9) {
						if (minutos.getIzq().getValor() != 9) {
							minutos.getDer().setValor(0);
							minutos.getDer().actualizar(minutos.getDer().getValor());
							minutos.getIzq().setValor(minutos.getIzq().getValor() + 1);
							minutos.getIzq().actualizar(minutos.getIzq().getValor());
						}
					}
					
					if (minutos.getIzq().getValor() == 9 && minutos.getDer().getValor() == 9) {
							terminar(1);
						
					}
					repaint();
				}		
		};
		timer.schedule(tarea, 0 , 1000);
	}	
	
	public void inicializar() {	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 3, 889, 730);
		contentPane = new JPanel();
		setContentPane(contentPane);	
		panelIzquierdo = new JPanel();
		panelIzquierdo.setBounds(5, 5, 500, 680);
     	panelIzquierdo.setLayout(new GridLayout(3,1));
     	panelIzquierdo.setVisible(true);
		panelDerecho = new JPanel();
		panelDerecho.setBounds(513, 10, 0, 0);
		panelDerecho.setLayout(new FlowLayout());
		panelDerecho.setSize(350, 120);
		Border bGreyLine = BorderFactory.createLineBorder(Color.BLACK, 2, false);
		panelDerecho.setBorder(bGreyLine);
		panelTiempo = new JPanel();
		panelTiempo.setLayout(new FlowLayout());
		JLabel etiquetaTiempo = new JLabel("Tiempo de juego");
		etiquetaTiempo.setBounds(110, 110, 110, 110);
		etiquetaTiempo.setFont(new Font("Font.PLAIN", 5, 26));
		panelDerecho.add(etiquetaTiempo);
		panelDerecho.add(panelTiempo);
		inicializarPaneles(panelIzquierdo);
		contentPane.setLayout(null);
		contentPane.add(panelIzquierdo);
		contentPane.add(panelDerecho);
		crearBotones();		
	}
	
	/**
	 * termina la ejecución del programa de acuerdo al numero pasado por parametro. Si el numero es 0 es porque el archivo es inválido, si el numero es 1 el juego se cerró porque el usuario se excedió de tiempo.
	 * @param num, numero que representa el motivo por el cual el programa finalizó.
	 */
	private void terminar(int num) {
		if (num == 0)
			JOptionPane.showMessageDialog(null, "El archivo ingresado no contiene un sudoku válido.","ERROR",2);
		else
			if (num == 1)
				JOptionPane.showMessageDialog(null, "Ha excedido el tiempo neto de juego ","¡HAS PERDIDO!",2);
		System.exit(0);
	}
	
	private void inicializarPaneles(JPanel panel) {
		Border bGreyLine = BorderFactory.createLineBorder(Color.BLACK, 2, false);
		paneles = new JPanel[9];
		for (int i = 0; i < paneles.length; i++) {
			paneles[i] = new JPanel();
			paneles[i].setLayout(new GridLayout(3,1));
			paneles[i].setBorder(bGreyLine);
			panel.add(paneles[i]);
		}
	}
	
	/**
	 * crea los botones del "tablero" comenzando del primer "casillero" de cada cuadrante en base al numero de fila y columna pasado por parametro.
	 * @param fila, numero de fila del primer "casillero" del cuadrante
	 * @param col, numero de columna del primer "casillero" del cuadrante
	 * @param panel, panel en el cual van a ser insertados los botones.
	 */
	private void crearBotones(int fila, int col, JPanel panel) {
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
			
	private void crearBotones() {
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
		
		for (int i = 0; i < juego.getCantidadFilas(); i++) {
			for (int j = 0; j < juego.getCantidadFilas(); j++) {
				if (juego.getCelda(i, j) != null) {
					ImageIcon img = juego.getCelda(i, j).getEntidadGrafica().getGrafico();
					botonesJuego[i][j].setIcon(img);
					botonesJuego[i][j].setBackground(Color.white);
					botonesJuego[i][j].setEnabled(false);
				}
			}	
		}
		for (int i = 0; i < juego.getCantidadFilas(); i++)
			for (int j = 0; j < juego.getCantidadFilas(); j++) {
				if (botonesJuego[i][j] != null)
					botonesJuego[i][j].addActionListener(new ActionListener() {			
						public void actionPerformed(ActionEvent e) {
							botonConUbicacion botonEvento = (botonConUbicacion) e.getSource();
							int col = botonEvento.getCol();
							int fila = botonEvento.getFila();
							Celda c = juego.getCelda(fila, col);
							if (c == null) {
								c = new Celda();			
								c.setValor(1);							
								c.setCol(col);
								c.setFila(fila);
								juego.setCelda(c.getFila(), c.getCol(), c);
								actualizarBoton(botonesJuego[c.getFila()][c.getCol()], c.getFila(), c.getCol(), c);
							}
							else {
								juego.accionar(c);
								actualizarBoton(botonesJuego[fila][col], fila, col, c);
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
		
		if (juego.gano()) {
			timer.cancel();
			JOptionPane.showMessageDialog(null, "Tiempo de juego: "+minutos.getIzq().getValor()+""+minutos.getDer().getValor()+":"+segundos.getIzq().getValor()+""+segundos.getDer().getValor(),"¡FELICITACIONES, HA GANADO!",1);
			System.exit(0);
		}
		else 
			actualizarBotones();
	}
	
	private void actualizarBotones() {
		Celda c;
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego.length; j++) {
				c = juego.getCelda(i, j);
				if (c != null) 
					if (botonesJuego[i][j].isEnabled())
						if (juego.cumpleConLasReglas(c))
							botonesJuego[i][j].setBackground(Color.green);
						else
							botonesJuego[i][j].setBackground(Color.red);
			}
		}
	}
}	
		