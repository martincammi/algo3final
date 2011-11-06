package algorithms;

import java.io.IOException;

import utils.FileManager;
import utils.Grafo;

public class CarteroGrasp {
	
	public static void main(String[] args) throws IOException {

		FileManager fm = new FileManager("Ej1.in");
		fm.abrirArchivo();
		Grafo grafo = fm.leerInstancia();
		while (grafo != null)
		{
			//GRASP
			grafo = fm.leerInstancia();
		}
	}
	
	
		
}
