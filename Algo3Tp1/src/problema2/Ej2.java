package problema2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ej2{
	private static FileReader fr;
	private static BufferedReader br;
	private static int cantIngredientes;
	private static Pizza pizza;
	
	//abre el archivo
	private static  void abrirInstancias()	throws FileNotFoundException {
		fr = new FileReader("Tp1Ej2.in");
		br = new BufferedReader(fr);
	}

//	//cierra el archivo
	private static void cerrarInstancias() throws IOException {
		fr.close();
	}
	
	//escribe el resultado en el archivo out. Primero la cantidad de instancias, y luego los pares de soluciones
	public static void escribirEnLog() throws IOException {
		FileWriter fw = new FileWriter("Tp1Ej2.log", true);
		BufferedWriter bw = new BufferedWriter(fw);
//		bw.write(jugadoresTorneo + "," + QtyOps + "\n");
		bw.close();
	}
	
	public static void escribirSalida(int cantJugadores, int diasTorneo) throws IOException {
		FileWriter fw = new FileWriter("Tp1Ej2.out", true);
		BufferedWriter bw = new BufferedWriter(fw);
		String linea;
		bw.write("Torneo realizado para " + cantJugadores + " jugadores" + "\n");
		bw.close();
	}
	
	public static  void limpiarSalida() throws IOException {
		File f = new File("Tp1Ej2.out");
		f.delete();
	}
	
	public static  void limpiarLog() throws IOException {
		File f = new File("Tp1Ej2.log");
		f.delete();
	}
	
	private static  boolean hayMasInstancias() throws IOException{
		String lineaArchivo = br.readLine();

		if (lineaArchivo == null){
			return false;
		}
		
		cantIngredientes = Integer.parseInt(lineaArchivo.trim());
		pizza = new Pizza(cantIngredientes);
		
		lineaArchivo = br.readLine();

		while (!lineaArchivo.equals(".")){
			pizza.addComenzal(lineaArchivo);
			lineaArchivo = br.readLine();
		}
		return true;
	}
	
	public static void main(String[] args) {
		try { 
			abrirInstancias();
			limpiarSalida();
			limpiarLog();
			
			while(hayMasInstancias())
			{
				String ingredientesPizza = pizza.resolver();
				System.out.println("solucion: \""+ ingredientesPizza+"\"");
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