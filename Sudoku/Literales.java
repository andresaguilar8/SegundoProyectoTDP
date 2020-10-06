package Sudoku;

import java.awt.Color;
import java.awt.Font;

public class Literales {

	/// TEXTOS
	public static String NOMBRE 	= "Sudoku Solver";
	public static String VERSION 	= "1.1";
	public static String AUTOR 		= "Inazio Claver";
	public static String WEB 		= "programandoapasitos.com";
	public static String COMPLETADO = "SUDOKU SOLUCIONADO";
	public static String INCOMPLETO = "SUDOKU NO RESUELTO";
	public static String URL_GITHUB = "https://github.com/inazense/SudokuSolver";
	
	public static String MENU_ARCHIVO 				= "Archivo";
	public static String MENU_AYUDA 				= "Ayuda";
	public static String MENU_ITEM_NUEVO 			= "Nuevo";
	public static String MENU_ITEM_CARGAR_CSV 		= "Cargar desde CSV";
	public static String MENU_ITEM_CARGAR_IMAGEN 	= "Cargar desde imagen";
	public static String MENU_ITEM_EXPORTAR_CSV 	= "Exportar a CSV";
	public static String MENU_ITEM_FUNCIONAMIENTO 	= "¿Cómo funciona?";
	public static String MENU_ITEM_INFO 			= "Información";
	
	public static String BTN_SOLUCIONAR 	= "Solucionar Sudoku";
	public static String BTN_LIMPIAR 		= "Limpiar tablero";
	public static String BTN_DIALOGO_CERRAR = "Cerrar";
	
	public static String CSV_EXTENSION_TEXTO 	= "Archivos CSV";
	public static String CSV_EXTENSION 			= "csv";
	
	public static String ERROR 					= "Ocurrió un error";
	public static String INFORMACION 			= "Información";
	public static String CANCEL_JOPTIONCHOOSED 	= "CANCEL";
	
	public static String EN_CONSTRUCCION = "En construcción. \nEn breves estará disponible esta funcionalidad";
	
	public static String GUARDADO_CSV_OK 	= "CSV generado correctamente";
	public static String GUARDADO_CSV_FAIL 	= "Error al generar el CSV";
	
	/// RUTAS
	public static String RUTA_PROPERTIES 	= "conf/config.properties";
	public static String RUTA_LOGO 			= "resources/img/logo.png";
	
	/// CONFIGURACION GUI
	public static int JFRAME_ANCHO 	= 528;
	public static int JFRAME_LARGO 	= 405;
	public static int JFRAME_X 		= 100;
	public static int JFRAME_Y 		= 100;
	
	public static int JPANEL_EMPTY_BORDER = 5;
	
	public static int MENU_ANCHO 	= 547;
	public static int MENU_LARGO 	= 21;
	public static int MENU_X 		= 0;
	public static int MENU_Y 		= 0;
	
	public static int TEXTFIELD_DIAMETRO 			= 30;
	public static int TEXTFIELD_COLUMNAS 			= 10;
	public static Font TEXTFIELD_FUENTE 			= new Font("Tahoma", Font.PLAIN, 12);
	public static Color TEXTFIELD_COLOR_FIJO 		= Color.BLACK;
	public static Color TEXTFIELD_COLOR_VARIABLE 	= Color.RED;
	
	public static int SEPARADOR_VERTICAL_ANCHO 		= 5;
	public static int SEPARADOR_VERTICAL_LARGO 		= 322;
	public static int SEPARADOR_HORIZONTAL_ANCHO 	= 320;
	public static int SEPARADOR_HORIZONTAL_LARGO 	= 2;
	public static Color SEPARADOR_COLOR_FOREGROUND 	= Color.BLACK;
	public static Color SEPARADOR_COLOR_BACKGROUND 	= Color.BLACK;
	
	public static int BOTON_ANCHO 	= 162;
	public static int BOTON_LARGO 	= 23;
	public static Font BOTON_FUENTE = new Font("Tahoma", Font.BOLD, 14);
	
	public static int LOGO_X 		= 350;
	public static int LOGO_Y 		= 30;
	public static int LOGO_DIAMETRO = 150;
	
	public static int DIALOGO_X 		= 100;
	public static int DIALOGO_Y 		= 100;
	public static int DIALOGO_ANCHO 	= 400;
	public static int DIALOGO_LARGO 	= 440;
	public static int DIALOGO_BORDER 	= 5;
	
	public static int LBL_DIALOGO_X 		= 10;
	public static int LBL_DIALOGO_Y 		= 11;
	public static int LBL_DIALOGO_ANCHO 	= 370;
	public static int LBL_DIALOGO_LARGO 	= 400;
	
}