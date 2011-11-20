package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import utils.Eje;
import utils.GeneradorGrafos;
import utils.Grafo;
import algorithms.CarteroConstructiva;

public class TestEncontrarMatchingNodos {

	@Test
	//testea en un grafo fuente-sumidero. 
	public void testGrafoSumidero(){
		System.out.println("Test Sumidero");
		int cantNodos = 6;
		Grafo grafo = GeneradorGrafos.generarGrafoOrSumidero(cantNodos);
		CarteroConstructiva cartero = new CarteroConstructiva(grafo);
		System.out.println("Grafo de " + grafo.cantNodos);
		List<Eje> unMatching = cartero.encontrarMatchingNodos(grafo, 10);
		
		List<Eje> matchingMinimo = cartero.encontrarMatchingDeMenorPeso(unMatching,grafo.getPesoCaminoMinimo());
		
		assertEquals(matchingMinimo.size(), grafo.cantNodos-2-1); 
		//El dos es por la fuente y el sumiedro. y uno menos más por que
		//esa es la cantidad de grados incidentes que hay que equiparar
		//menos uno que debe quedar incidente.
		
		//Todos los matchings seran del ultimo nodo al primero
		for (Eje eje : matchingMinimo) {
			assertTrue(eje.getNodo1() == grafo.cantNodos-1);
			assertTrue(eje.getNodo2() == 0);
		}
	}
	
	@Test
	/**
	 * Muestra la cantidad de soluciones iniciales diferentes que pueden darse
	 */
	public void testMultiplesSolIniciales(){
		System.out.println("testMultiplesSolIniciales");
		Grafo grafo = grafoHexagonal();	
		CarteroConstructiva cartero = new CarteroConstructiva(grafo);
		for (int i = 1; i < 20; i++) {
			cartero.encontrarMatchingNodos(grafo, i);
		}
	}
	
	//para todos las posibles soluciones de entrada ver que da lo mismo
	@Test
	public void testGrafoHexagonal(){
		
		Grafo grafo = grafoHexagonal();
		
		CarteroConstructiva cartero = new CarteroConstructiva(grafo);
		System.out.println("Grafo de " + grafo.cantNodos);
		List<Eje> unMatching = cartero.encontrarMatchingNodos(grafo, 10);
		List<Eje> matchingMinimo = cartero.encontrarMatchingDeMenorPeso(unMatching,grafo.getPesoCaminoMinimo());
		
		assertEquals(matchingMinimo.size(), 4);
		
		int pesoMinimo = 0;
		for (Eje eje : matchingMinimo) {
			pesoMinimo += eje.getPeso();
		}
		assertEquals(pesoMinimo, 8);
		
	}
	
	
	public Grafo grafoHexagonal(){
		String[] adyacencias = {"8", "0", "12",
				"0", "1", "1",
				"0", "5", "1",
				"1", "2", "1",
				"2", "3", "1",
				"3", "7", "1",
				"3", "4", "1",
				"4", "6", "1",
				"5", "4", "1",
				"6", "5", "1",
				"6", "0", "1",
				"7", "1", "1",
				"7", "2", "1"};

		return new Grafo(adyacencias);	
	}
	
}
