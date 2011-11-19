package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//import Grafo.Grafo;
//import Grafo.Nodo;


/**
 * Manejador para abrir y cerrar archivos.
 *
 */
public class FileManager {

	private static FileReader fr;
	private static BufferedReader br;
	private String fileNameIn;
	
	/**
	 * Crea un File Manager pasandole el nombre del archivo
	 * @param filename
	 */
	public FileManager(String filename){
		this.fileNameIn = filename;
	}
	
	public void abrirArchivo()	throws FileNotFoundException {
		fr = new FileReader(this.fileNameIn);
		br = new BufferedReader(fr);
	}
	
	private void borrarArchivo(String filename){
		File f = new File(filename);
		f.delete();
	}

	public void escribirArchivo(String data, String fileName) throws IOException {
		borrarArchivo(fileName);
		FileWriter fw = new FileWriter(fileName, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(data + "\n");
		bw.close();
	}
	
	public void cerrarArchivo() throws IOException {
		fr.close();
	}
	
	//LEE EL ENCABEZADO DEL ARCHIVO, (n, m1 y m2) CON CANTIDAD DE ARISTAS DIRIGIDAS Y NO DIRIGIDAS, Y LLAMA AL GETINSTANCIA,
	//DONDE LEE TODOS LOS NODOS Y LAS ARISTAS, Y LAS AGREGA AL GRAFO
	public Grafo leerInstancia() throws IOException
	{
		Grafo grafo = null;
		String linea = br.readLine();

		if (linea != null && !linea.equals("")) 			//SI LINEA = NULL, RETORNA GRAFO NULL (NO HAY MAS INSTANCIAS)
		{		
			int n, m1, m2;
			String lineaSplit[] = linea.split(" ");
			//cantidades
			n = Integer.parseInt(lineaSplit[0]);
			m1 = Integer.parseInt(lineaSplit[1]);
			m2 = Integer.parseInt(lineaSplit[2]);
			grafo = new Grafo(n,m1,m2);
			String[] lineaArray;
			int nodo1, nodo2, peso = 0;
			for (int i = 0; i < m1; ++i){  //LEE LAS PRIMERAS m1 LINEAS. ARISTAS SIN DIRECCION
				lineaArray = br.readLine().split(" ");  //LEO LA LINEA, y LA PASO A ARRAY CON SPLIT
				nodo1 = Integer.parseInt(lineaArray[0].trim());
				nodo2 = Integer.parseInt(lineaArray[1].trim());
				peso  = Integer.parseInt(lineaArray[2].trim());
				grafo.agregarAdyacencia(nodo1, nodo2, peso, false);
				//grafo.agregarAdyacencia(nodo2, nodo1, peso, false);/ /agrego las dos copias directamente en esta funcion para no contar dos veces el peso
				grafo.sumarBits(nodo1);
				grafo.sumarBits(nodo2);
				grafo.sumarBits(peso);
				Grafo.complex++;
			}
			for (int i = 0; i < m2; ++i){  //LEE LAS SIGUIENTES m2 LINEAS. ARCOS CON DIRECCION
				lineaArray = br.readLine().split(" ");  //LEO LA LINEA, y LA PASO A ARRAY CON SPLIT
				nodo1 = Integer.parseInt(lineaArray[0].trim());
				nodo2 = Integer.parseInt(lineaArray[1].trim());
				peso  = Integer.parseInt(lineaArray[2].trim());
				grafo.agregarAdyacencia(nodo1, nodo2, peso, true);
				grafo.sumarBits(nodo1);
				grafo.sumarBits(nodo2);
				grafo.sumarBits(peso);
				Grafo.complex++;
			}
			grafo.calcularDantzig(); //Terminé de armar el grafo, entonces calculo distancias minimas entre sus nodos.
		}
		return grafo;
	}
}
