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
	/*Fin de datos de entrada*/
	private static int fixturePartidos[][]; //guardo la matriz con el fixture
	private static int QtyOps; //guardo la cantidad de operaciones
	
	//abre el archivo de entrada
	private static  void abrirInstancias()	throws FileNotFoundException {
		fr = new FileReader("Tp1Ej1.in");
		br = new BufferedReader(fr);
	}

//	//cierra el archivo de entrada
	private static  void cerrarInstancias() throws IOException {
		fr.close();
	}
	
	//escribe el resultado en el archivo out. Primero la cantidad de jugadores del torneo, y luego la cantidad de operaciones
	public static void escribirEnLog() throws IOException {
		FileWriter fw = new FileWriter("Tp1Ej1.log", true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(jugadoresTorneo + "\t" + QtyOps + "\n");
		bw.close();
	}
	
	//escribe el fixture en el archivo de salida
	public static void escribirSalida(int cantJugadores, int diasTorneo) throws IOException {
		FileWriter fw = new FileWriter("Tp1Ej1.out", true);
		BufferedWriter bw = new BufferedWriter(fw);
		String linea;
		bw.write("Torneo realizado para " + cantJugadores + " jugadores" + "\n");
		
		for (int dia = 1; dia < diasTorneo; dia ++)
		{
			linea = "Dia " + dia + " ";
			for (int jugador = 1; jugador <= cantJugadores; jugador++)
			{
				if (!linea.contains((jugador + "" + fixturePartidos[jugador][dia])) && !linea.contains(""+fixturePartidos[jugador][dia]+ "" + jugador))
				{
					if (fixturePartidos[jugador][dia] != 0)
					{
						linea = linea + jugador + fixturePartidos[jugador][dia] + " ";
					}
				}
				
			}
			bw.write(linea + "\n");
		}
		
		bw.close();
	}
	
	//borra el archivo de salida
	public static  void limpiarSalida() throws IOException {
		File f = new File("Tp1Ej1.out");
		f.delete();
	}
	
	//borra el archivo de log, donde guardo la cantidad de jugadores del torneo y la cantidad de operaciones que le demanda realizarlo
	public static  void limpiarLog() throws IOException {
		File f = new File("Tp1Ej1.log");
		f.delete();
	}
	
	//Si hay alguna instancia, devuelve true con la cantidad de jugadores del torneo. Devuelve false si llego al fin de archivo (Con un punto) 
	private static  boolean hayMasInstancias() throws IOException
	{
		String line = br.readLine();

		if (line == null || line.equals("."))
			return false;
		
		jugadoresTorneo = Integer.parseInt(line.trim());

		return true;
	}
	
	//Cuando la mitad de jugadores de alguna iteraci�n da impar, debe agregar un jugador "ficticio", que es asociado en esta funci�n con el valor 0.
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
				QtyOps++;
			}
		}
	}
	
	//Esta funci�n es la encargada de agarrar todos los jugadores ficticios, y asignarle su oponente.
	private static void sacarJugadorFicticio(int mitadJugadores)
	{
		for (int jugador = 1; jugador <= mitadJugadores; jugador++) 
		{
			for (int dia = 1; dia <= mitadJugadores; dia++)
			{
				if (fixturePartidos[jugador][dia] == 0) //Si aparece un jugador ficticio
				{
					fixturePartidos[jugador][dia] = jugador + mitadJugadores; 
	 				fixturePartidos[jugador+mitadJugadores][dia] = jugador;
				}
				QtyOps++;
			} 
		} 
	}
	
	//Calcula el fixture del cuadrante inferior izquierdo
	private static void cuadranteInferiorIzquierdo(int mitadJugadores, int cantJugadores, boolean esPar)
	{
		if (esPar)
		{
			for (int jugador = mitadJugadores+1; jugador <=cantJugadores; jugador++) 
			{
				for (int dia = 1; dia <= mitadJugadores-1; dia++)
				{
						fixturePartidos[jugador][dia] = fixturePartidos[jugador-mitadJugadores][dia] + mitadJugadores;
						QtyOps++;
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
					QtyOps++;
				} 
			} 
			sacarJugadorFicticio(mitadJugadores);
		}
	}
	
	//Calcula el fixture del cuadrante inferior derecho
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
					QtyOps++;
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
					QtyOps++;
				} 
			}
		}
	}
	
	//Calcula el fixture del cuadrante superior derecho
	private static void cuadranteSuperiorDerecho(int mitadJugadores, int cantJugadores, boolean esPar)
	{
		if (esPar)
		{
			for (int jugador = 1; jugador<= mitadJugadores; jugador++) 
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
					QtyOps++;
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
					QtyOps++;
				} 
			}
		}
	}
	
	//crea el torneo usando divide y vencer�s
	private static void crearTorneo(int cantJugadores)
	{
		int mitadJugadores;
		
		if (cantJugadores == 2)
		{
			fixturePartidos[1][1] = 2;		//caso base
			fixturePartidos [2][1] = 1;
			QtyOps++;
		}
		else
		{
			if ((cantJugadores%2)!=0)      //si la cantJugadores impar
			{
				QtyOps++;
				crearTorneo(cantJugadores+1);
				
				colocarJugadorFicticio(cantJugadores);
			}
			else   //si cantJugadores es par
			{
				mitadJugadores = cantJugadores / 2; 
				QtyOps++;
				crearTorneo(mitadJugadores);        
				
				boolean esPar = (mitadJugadores%2) ==0;
				cuadranteInferiorIzquierdo(mitadJugadores, cantJugadores, esPar);
				cuadranteInferiorDerecho(mitadJugadores, cantJugadores, esPar);
				cuadranteSuperiorDerecho(mitadJugadores, cantJugadores, esPar);
			}
		}
	}
	
	public static void main(String[] args) {
		int dias = 0;
		int cantJugadores;
		try { 
			abrirInstancias();
			limpiarSalida();
			limpiarLog();
		
			while(hayMasInstancias())
			{	
				if (jugadoresTorneo >= 2)
				{
					QtyOps = 0;
					if (jugadoresTorneo%2!=0)  //SI ES IMPAR LA CANTIDAD DE JUGADORES, EL ARREGLO ES DE TAMA�O jugadoresTorneo -1
					{ 
						cantJugadores = jugadoresTorneo + 2; 
						dias = jugadoresTorneo + 1; 
					}					
					else
					{
						cantJugadores = jugadoresTorneo + 1;
						dias = jugadoresTorneo;
					}		
					
					QtyOps++;
					
					fixturePartidos = new int[cantJugadores][dias];
					crearTorneo(jugadoresTorneo);
					
					escribirSalida(jugadoresTorneo, dias);
					escribirEnLog();  
				}
			}
			
		    cerrarInstancias();
		    System.out.println("Los torneos se han creado satisfactoriamente.");
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

