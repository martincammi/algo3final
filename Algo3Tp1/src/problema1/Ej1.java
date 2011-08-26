package problema1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ej1 {

	private static FileReader fr;
	private static BufferedReader br;
	/*Datos de entrada*/
	private static int jugadoresTorneo;
	private static int fixturePartidos[][];
	/*Fin de datos de entrada*/
	private static int QtyOps; //guardo la cantidad de operaciones
	
	//abre el archivo
	private static  void abrirInstancias()	throws FileNotFoundException {
		fr = new FileReader("Tp1Ej1.in");
		br = new BufferedReader(fr);
	}

//	//cierra el archivo
	private static  void cerrarInstancias() throws IOException {
		fr.close();
	}
	
	//escribe el resultado en el archivo out. Primero la cantidad de instancias, y luego los pares de soluciones
	public static void escribirEnLog() throws IOException {
		FileWriter fw = new FileWriter("Tp1Ej1.log", true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(jugadoresTorneo + "," + QtyOps + "\n");
		bw.close();
	}
	
	public static void escribirSalida(int cantJugadores, int diasTorneo) throws IOException {
		//FIXME LE PUSE PARENTESIS Y COSAS PARA LEER MEJOR. NO ENTREGAR ASI
		FileWriter fw = new FileWriter("Tp1Ej1.out", true);
		BufferedWriter bw = new BufferedWriter(fw);
		String linea;
		bw.write("Torneo realizado para " + cantJugadores + " jugadores" + "\n");
		
		for (int dia = 1; dia < diasTorneo; dia ++)
		{
			linea = "Dia " + dia + " - ";
			for (int jugador = 1; jugador <= cantJugadores; jugador++)
			{
				if (!linea.contains("(" + jugador + " , " + fixturePartidos[jugador][dia] + ") ") && !linea.contains("(" + fixturePartidos[jugador][dia] + " , " + jugador + ")"))
				{ 
					linea = linea + "(" + jugador + " , " + fixturePartidos[jugador][dia] + ") ";
				}
				
			}
			bw.write(linea + "\n");
			bw.write("\n");  //ESPACIO EN BLANCO
		}
		
		bw.close();
	}
	
	public static  void limpiarSalida() throws IOException {
		File f = new File("Tp1Ej1.out");
		f.delete();
	}
	
	public static  void limpiarLog() throws IOException {
		File f = new File("Tp1Ej1.log");
		f.delete();
	}
	
	private static  boolean hayMasInstancias() throws IOException
	{
		String line = br.readLine();

		if (line == null || line.equals("."))
			return false;
		
		jugadoresTorneo = Integer.parseInt(line.trim());

		return true;
	}
	
	private static void colocarJugadorFicticio(int cantJugadores)
	{		 
		for (int jugador = 1; jugador <= cantJugadores; jugador++)  
		{
			for (int dia = 1; dia <= cantJugadores; dia++)
			{
				if (fixturePartidos[jugador][dia] == cantJugadores+1)
				{
					fixturePartidos[jugador][dia]= 0;
				}
			}
		}
	}
	
	private static void sacarJugadorFicticio(int mitadJugadores)
	{
		for (int jugador = 1; jugador <= mitadJugadores; jugador++) 
		{
			for (int dia = 1; dia <= mitadJugadores; dia++)
			{
				if (fixturePartidos[jugador][dia] == 0) //APARECE JUGAdor ficticio
				{
					fixturePartidos[jugador][dia] = jugador + mitadJugadores; 
	 				fixturePartidos[jugador+mitadJugadores][dia] = jugador;
				}
			} 
		} 
	}
	
	private static void cuadranteInferiorIzquierdo(int mitadJugadores, int cantJugadores, boolean esPar)
	{
		if (esPar)
		{
			for (int jugador = mitadJugadores+1; jugador <=cantJugadores; jugador++) 
			{
				for (int dia = 1; dia <= mitadJugadores-1; dia++)
				{
						fixturePartidos[jugador][dia] = fixturePartidos[jugador-mitadJugadores][dia] + mitadJugadores; 
				} 
			}
		}
		else
		{
			for (int jugador = mitadJugadores + 1; jugador <= cantJugadores; jugador++) 
			{
				for (int dia = 1; dia <= mitadJugadores; dia++)
				{
					if (fixturePartidos[jugador - mitadJugadores][dia] == 0) 
					{
						fixturePartidos[jugador][dia]=0;
					}
					else
					{	
						fixturePartidos[jugador][dia]= fixturePartidos[jugador-mitadJugadores][dia] + mitadJugadores;
					}
				} 
			} 
			sacarJugadorFicticio(mitadJugadores);
		}
	}
	
	private static void cuadranteInferiorDerecho(int mitadJugadores, int cantJugadores, boolean esPar)
	{
		if (esPar)
		{
			for (int jugador = mitadJugadores+1; jugador<= cantJugadores; jugador++) 
			{
				for (int dia = mitadJugadores; dia <= cantJugadores-1; dia++)
				{
					if (jugador>dia) 
					{
						fixturePartidos[jugador][dia]=jugador-dia;
					}
					else
					{	
						fixturePartidos[jugador][dia] = (jugador + mitadJugadores)-dia;
					}
				} 
			}
		}
		else
		{
			for (int jugador = 1; jugador <= mitadJugadores; jugador++) 
			{
				for (int dia = mitadJugadores+1; dia <= cantJugadores-1; dia++)
				{
					if (jugador+dia <=cantJugadores) 
					{
						fixturePartidos[jugador][dia] = jugador+dia;
					}
					else
					{
						fixturePartidos[jugador][dia] = jugador + dia - mitadJugadores;
					}
					
				} 
			}
		}
	}
	
	private static void cuadranteSuperiorDerecho(int mitadJugadores, int cantJugadores, boolean esPar)
	{
		if (esPar)
		{
			for (int jugador = 1; jugador<= mitadJugadores; jugador++) //cuadrante superior derecho
			{
				for (int dia = mitadJugadores; dia <= cantJugadores-1; dia++)
				{
					if ((jugador+dia)<=cantJugadores) 
					{
						fixturePartidos[jugador][dia]=jugador+dia;
					}
					else
					{	
						fixturePartidos[jugador][dia]=jugador + dia - mitadJugadores;
					}
				} 
			}
		}
		else
		{
			for (int jugador = mitadJugadores+1; jugador <= cantJugadores; jugador++) 
			{
				for (int dia = mitadJugadores+1; dia <= cantJugadores-1; dia++)
				{
					if (jugador>dia) 
					{
						fixturePartidos[jugador][dia] = jugador -dia;
					}
					else
					{
						fixturePartidos[jugador][dia] = (jugador + mitadJugadores)-dia;
					}
					
				} 
			}
		}
	}
	
	private static void torneo(int cantJugadores)
	{
		int mitadJugadores;
		
		if (cantJugadores == 2)
		{
			fixturePartidos[1][1] = 2;		//caso base
			fixturePartidos [2][1] = 1;
		}
		else
		{
			if ((cantJugadores%2)!=0)      //si la cantJugadores impar
			{
				torneo(cantJugadores+1);
				
				colocarJugadorFicticio(cantJugadores);
			}
			else   //si cantJugadores es par
			{
				mitadJugadores = cantJugadores / 2; 
				
				torneo(mitadJugadores);      // primero el cuadrante sup. izq.  
				
				boolean esPar = (mitadJugadores%2) ==0;
				cuadranteInferiorIzquierdo(mitadJugadores, cantJugadores, esPar);
				cuadranteInferiorDerecho(mitadJugadores, cantJugadores, esPar);
				cuadranteSuperiorDerecho(mitadJugadores, cantJugadores, esPar);
			}
		}
	}
	
	public static void main(String[] args) {
		int dias = 0;
		try { 
			abrirInstancias();
			limpiarSalida();
			limpiarLog();
			
			while(hayMasInstancias())
			{
				QtyOps = 0;
				if (jugadoresTorneo%2!=0)  //SI ES IMPAR LA CANTIDAD DE JUGADORES, EL ARREGLO ES DE TAMA�O jugadoresTorneo -1
					{ dias = jugadoresTorneo + 1; }					
				else
					{ dias = jugadoresTorneo; }		
					
				fixturePartidos = new int[jugadoresTorneo+1][dias];
				torneo(jugadoresTorneo);
				
				escribirSalida(jugadoresTorneo, dias);
				escribirEnLog();  //FALTA AGREGAR LA CANTIDAD DE OPERACIONES
				
				//ESTO ES PARA VER MEJOR NOSOTROS; NO HAY QUE ENTREGARLO
				for (int fila = 1; fila <= jugadoresTorneo; fila++)
				{
					System.out.println("Solucion Fila " + fila);
					for (int columna = 1; columna < dias; columna++)
					{
						System.out.println(fixturePartidos[fila][columna]);
					}
				}
				//ESTO ES PARA VER MEJOR NOSOTROS; NO HAY QUE ENTREGARLO
				
			}
			
		    cerrarInstancias();
		}
		catch (FileNotFoundException e) {
			System.out.println("No se puede abrir el archivo in");
		} catch (IOException e) {
			System.out.println("Error manipulando el archivo");
		} catch (NumberFormatException nfe) {
			System.out.println("Error en el formato del archivo");
		}		
	}
	
	
		
}

