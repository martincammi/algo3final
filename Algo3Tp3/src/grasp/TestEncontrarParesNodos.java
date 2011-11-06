package grasp;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestEncontrarParesNodos {

	@Test
	public void testGrafoSumidero(){
		Grafo grafo = GeneradorGrafos.generarGrafoOrSumidero();
		CarteroChino cartero = new CarteroChino(grafo);
		System.out.println("Grafo de " + grafo.cantNodos);
		List<int[]> pares = cartero.encontrarParesNodos();
		
		assertNotNull(pares);
		assertEquals(pares.size(), grafo.cantNodos-2-1);
		
		for (int[] par : pares) {
			assertTrue(par[0] == grafo.cantNodos-1);
			assertTrue(par[1] == 0);
		}
	}
	
	
	
}
