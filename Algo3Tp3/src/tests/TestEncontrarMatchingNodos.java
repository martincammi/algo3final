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
	public void testGrafoSumidero(){
		Grafo grafo = GeneradorGrafos.generarGrafoOrSumidero();
		CarteroConstructiva cartero = new CarteroConstructiva(grafo);
		System.out.println("Grafo de " + grafo.cantNodos);
		List<Eje> pares = cartero.encontrarMatchingNodos(grafo);
		
		assertNotNull(pares);
		assertEquals(pares.size(), grafo.cantNodos-2-1);
		
		for (Eje eje : pares) {
			assertTrue(eje.getNodo1() == grafo.cantNodos-1);
			assertTrue(eje.getNodo2() == 0);
		}
	}
	
	
	
}
