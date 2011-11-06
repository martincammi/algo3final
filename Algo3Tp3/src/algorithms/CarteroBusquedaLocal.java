package algorithms;

import java.io.IOException;

import utils.FileManager;
import utils.Grafo;

public class CarteroBusquedaLocal {

	public static void main(String[] args) throws IOException {

		FileManager fm = new FileManager("Ej1.in");
		fm.abrirArchivo();
		Grafo grafo = fm.leerInstancia();
		while (grafo != null)
		{
			//MIENTRAS PARAMETRO DE ITERACIONES CONSTRUCTIVA HACER
				//HACER UNA COPIA DEL GRAFO
				//ORIENTAR LAS ARISTAS
				//CALCULAR UN MATCHING DE DIN DOUT
				//BUSQUEDA LOCAL SOBRE MATCHING EN LAS VECINDADES//DEFINIR QUIENES SON VECINOS
			//CALCULAR EULERIANO
			//DEVOLVER
			grafo = fm.leerInstancia();
		}
	}	
}
