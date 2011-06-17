package problema4;

import java.util.Set;

import problema4.Main.Eje;
import problema4.Main.Grafo;
import org.junit.*;
import static org.junit.Assert.*;


public class GrafoTest {

	@Test
	public void testCantNodos(){
		Grafo grafo = new Main.Grafo();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,4);
		grafo.agregarEje(4,5);
		
		assertEquals(5,grafo.getCantNodos());
	}
	
	@Test
	public void testCantNodos2(){
		Grafo grafo = new Main.Grafo();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		
		grafo.ocultarNodo(2);
		
		assertEquals(2,grafo.getCantNodos());
	}
	
	@Test
	public void testUnNodo(){
		Grafo grafo = new Main.Grafo();
		grafo.agregarEje(1,2);
		Integer nodo = grafo.getNodo();
		assertEquals(nodo,new Integer(1));
	}
	
	@Test
	public void testAgregarEjes(){
		Grafo grafo = new Main.Grafo();
		grafo.agregarEje(1,2);
		assertTrue(grafo.hayEje(1,2));
		assertTrue(grafo.hayEje(2,1));
	}
	
	@Test
	public void testAgregarMasEjes(){
		Grafo grafo = new Grafo();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,4);
		grafo.agregarEje(2,5);
		grafo.agregarEje(5,7);
		assertEquals("[(2,1)(2,3)(2,5)]",printList(grafo.ejes(2)));
	}
	
	@Test
	public void testEjeOculto(){
		Grafo grafo = new Grafo();
		grafo.agregarEje(1,2);
		grafo.ocultarEje(1,2);
		assertEquals("[]",printList(grafo.ejes(1)));
	}
	
	@Test
	public void testEjesOcultos(){
		Grafo grafo = new Grafo();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,4);
		grafo.agregarEje(4,5);
		grafo.agregarEje(5,1);
		
		grafo.ocultarEje(2,3);
		grafo.ocultarEje(4,5);
		
		assertEquals("[(1,2)(1,5)]",printList(grafo.ejes(1)));
		assertEquals("[(2,1)]",printList(grafo.ejes(2)));
		assertEquals("[(3,4)]",printList(grafo.ejes(3)));
		assertEquals("[(4,3)]",printList(grafo.ejes(4)));
		assertEquals("[(5,1)]",printList(grafo.ejes(5)));
		
	}
	
	@Test
	public void testEjesOcultosYLuegoMostrados(){
		Grafo grafo = new Grafo();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,4);
		grafo.agregarEje(4,5);
		grafo.agregarEje(5,1);
		
		grafo.ocultarEje(1,2);
		grafo.ocultarEje(2,3);
		grafo.ocultarEje(4,5);
		grafo.ocultarEje(5,1);
		
		grafo.mostrarEje(1,2);
		grafo.mostrarEje(4,5);
		grafo.mostrarEje(2,3);
		grafo.mostrarEje(5,1);
		
		assertEquals("[(1,2)(1,5)]",printList(grafo.ejes(1)));
		assertEquals("[(2,1)(2,3)]",printList(grafo.ejes(2)));
		assertEquals("[(3,2)(3,4)]",printList(grafo.ejes(3)));
		assertEquals("[(4,3)(4,5)]",printList(grafo.ejes(4)));
		assertEquals("[(5,4)(5,1)]",printList(grafo.ejes(5)));
		
	}
	
	@Test
	public void testEstaOcultoNodo(){
		Grafo grafo = new Grafo();
		grafo.agregarEje(1,2);
		grafo.ocultarNodo(1);
		assertTrue(grafo.estaOcultoNodo(1));
	}
	
	@Test
	public void testNodoOculto(){
		Grafo grafo = new Grafo();
		grafo.agregarEje(1,2);
		grafo.ocultarNodo(2);
		assertEquals("[]",printList(grafo.ejes(1)));
		assertEquals("[]",printList(grafo.ejes(2)));
	}
	
	@Test
	public void testGradoDeUnNodo(){
		Grafo grafo = new Grafo();
		grafo.agregarEje(1,2);
		assertEquals(1,grafo.gradoNodo(1));
		assertEquals(1,grafo.gradoNodo(2));
	}
	
	@Test
	public void testGradoDeVariosNodos(){
		Grafo grafo = new Grafo();
		grafo.agregarEje(1,2);
		grafo.agregarEje(1,3);
		grafo.agregarEje(1,4);
		grafo.agregarEje(4,2);
		grafo.agregarEje(4,3);
		grafo.agregarEje(4,5);
		grafo.agregarEje(5,1);

		assertEquals(4,grafo.gradoNodo(1));
		assertEquals(2,grafo.gradoNodo(2));
		assertEquals(2,grafo.gradoNodo(3));
		assertEquals(4,grafo.gradoNodo(4));
		assertEquals(2,grafo.gradoNodo(5));
	}
	
	@Test
	public void testGradoDeUnNodosOculto(){
		Grafo grafo = new Main.Grafo();
		grafo.agregarEje(1,2);
		
		assertEquals(1,grafo.gradoNodo(1));
		grafo.ocultarNodo(1);
		assertEquals(0,grafo.gradoNodo(1));
		
		assertEquals(0,grafo.gradoNodo(2));
		grafo.ocultarNodo(2);
		assertEquals(0,grafo.gradoNodo(2));
		
		grafo.mostrarNodo(1);
		assertEquals(0,grafo.gradoNodo(1));
		
		grafo.mostrarNodo(2);
		assertEquals(1,grafo.gradoNodo(2));
		assertEquals(1,grafo.gradoNodo(1));
	}
	
	@Test
	public void testesConexo(){
		Grafo grafo = new Main.Grafo();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,4);
		assertTrue(grafo.esConexo());
	}
	
	
	private static String printList(Set<Eje> lista){
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (Eje eje : lista) {
			sb.append("(" + eje.getNodo1() + "," + eje.getNodo2() + ")");
		}
		sb.append("]");
		return sb.toString();
	}
	
}
