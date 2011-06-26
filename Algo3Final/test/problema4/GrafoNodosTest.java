package problema4;

import org.junit.Test;
import static org.junit.Assert.*;

import problema4.Main.GrafoNodos;


public class GrafoNodosTest {

	@Test
	public void testIsVisitedFalse(){
		GrafoNodos grafo = new GrafoNodos();
		grafo.agregarEje(1,2);
		assertFalse(grafo.isVisited(1));	
	}
	
	@Test
	public void testIsVisitedTrue(){
		GrafoNodos grafo = new GrafoNodos();
		grafo.agregarEje(1,2);
		grafo.visitarNodo(1);
		assertTrue(grafo.isVisited(1));	
	}
	
	@Test
	public void testSetDistance(){
		GrafoNodos grafo = new GrafoNodos();
		grafo.setDistance(1,3);
		assertEquals(new Integer(3),grafo.getDistance(1));
	}
}
