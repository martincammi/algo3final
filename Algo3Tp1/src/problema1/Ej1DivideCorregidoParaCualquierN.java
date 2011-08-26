package problema1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
	
public class Ej1DivideCorregidoParaCualquierN {

		private static FileReader fr;
		private static BufferedReader br;
		/*Datos de entrada*/
		private static int jugadoresTorneo;
		private static int fixturePartidos[][];
		/*Fin de datos de entrada*/
		private int res; //la suma mayor
		private int QtyOps; //guardo la cantidad de operaciones
		
		//abre el archivo
		private static  void abrirInstancias()	throws FileNotFoundException {
			fr = new FileReader("Tp1Ej1.in");
			br = new BufferedReader(fr);
		}

		//cierra el archivo
		private static  void cerrarInstancias() throws IOException {
			fr.close();
		}
		
		//escribe el resultado en el archivo out. Primero la cantidad de instancias, y luego los pares de soluciones
		public void escribirEnLog() throws IOException {
			FileWriter fw = new FileWriter("Tp1Ej1.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(jugadoresTorneo + "," + QtyOps + "\n");
			bw.close();
		}

		public void escribirResultado() throws IOException {
			FileWriter fw = new FileWriter("Tp1Ej1.out", true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(Integer.toString(res) + "\n");
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
					if (fixturePartidos[jugador][dia] == mitadJugadores + 1) 
					{
						fixturePartidos[jugador][dia] = jugador + mitadJugadores; 
		 				//fixturePartidos[jugador+mitadJugadores][dia] = jugador;
					}
				} 
			} 
		}
		
		private static  boolean hayMasInstancias() throws IOException
		{
			String line = br.readLine();

			if (line == null || line.equals("."))
				return false;
			
			jugadoresTorneo = Integer.parseInt(line.trim());

			return true;
		}
		
		private static  void completarTabla(int eqInf, int eqSup, int diaInf, int diaSup, int eqInic)
		{
			  for (int j = diaInf; j <= diaSup; j++)
			  {
				  fixturePartidos[eqInf][j] = eqInic + j - diaInf;
			  }
			  
			  for (int i = eqInf + 1; i <= eqSup; i++)
			  {
			      /*Intercambio de contrincante*/
				  fixturePartidos[i][diaInf] = fixturePartidos[i-1][diaSup];// {el �ltimo contrincante de i-1 es ahora el primer contrincante de i}
			      for (int j = diaInf + 1; j <= diaSup; j++)
			      {
			         /*rotaci�n de los contrincantes*/
			    	  fixturePartidos[i][j] = fixturePartidos[i-1][j-1]; //{el contrincante de ayer de i-1, es el contrincante de hoy para i}
			      }
			  }
			
		}
		
		private static  void formarTabla(int primero, int ultimo)
		{
			int medio;
			int cantJugadores = ultimo - primero + 1;
			
			if (ultimo - primero == 1)  /*caso base: compiten entre ambos (el mismo d�a)*/
			{
				fixturePartidos[primero][1]=ultimo;
				fixturePartidos[ultimo][1]=primero;
			}
			else
			{
				if ((cantJugadores%2)!=0)      //si la cantJugadores impar
				{
					formarTabla(primero, ultimo + 1);
					
					sacarJugadorFicticio(ultimo);
				}
				else
				{   //FUNCIONA CON POTENCIAS DE 2!
					
					medio = (primero+ultimo) / 2;
					
					formarTabla(primero,medio);  /*primera subsoluci�n: participantes de 1 a 2k-1*/
				    
					formarTabla(medio+1,ultimo);/*segunda subsoluci�n: participantes de 2k-1+1 a 2k*/
					
					//MAGIA
					Double cantidadBloquesADividir = Math.log(ultimo - primero + 1) / Math.log(2) - 1;
					Double potencia = Math.pow (2, cantidadBloquesADividir);
					Double suma = ultimo-medio-1 + potencia;
					int suma1 = (int)suma.longValue();
					//MAGIA
					
				    completarTabla(primero, medio, ultimo - medio, suma1, medio+1);   /*completa la tabla de los participantes de la primera subsoluci�n con los de la segunda*/ 
				    
				    completarTabla(medio+1, ultimo, ultimo - medio, suma1, primero); /*completa la tabla de los participantes de la segunda subsoluci�n con los de la primera*/
				}
			    
			}
		}
		
		public static void main(String[] args) {
			try { 
				abrirInstancias();
				limpiarSalida();
				limpiarLog();
				
				while(hayMasInstancias())
				{
					fixturePartidos = new int[jugadoresTorneo+2][jugadoresTorneo+2];
					formarTabla(1,jugadoresTorneo);
				}
				 
				for (int fila = 1; fila <= jugadoresTorneo+1; fila++)
				{
					System.out.println("Solucion Fila " + fila);
					for (int columna = 1; columna <= jugadoresTorneo+1; columna++)
					{
						System.out.println(fixturePartidos[fila][columna]);
					}
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



