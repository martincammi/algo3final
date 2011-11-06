package tests;

import org.junit.Test;

import utils.GeneradorGrafos;
import utils.Grafo;

public class TestGrafo {

	@Test
	public void testDantzig(){
		Grafo grafo = GeneradorGrafos.generarGrafoOrSumidero();
		grafo.calcularDantzig();
		
		for (int i = 0; i < grafo.cantNodos; i++) {
			for (int j = 0; j < grafo.cantNodos; j++) {
				System.out.println("peso (" + i + "," + j + "): " + grafo.pesoCaminoMinimo(i, j));
			}
		}
		
		
	}
}
