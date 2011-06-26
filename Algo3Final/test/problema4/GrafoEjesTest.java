package problema4;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import problema4.Main.Eje;
import problema4.Main.GrafoEjes;
import problema4.Main.GrafoNodos;

import org.junit.*;

import static org.junit.Assert.*;


public class GrafoEjesTest {

	@Test
	public void testCantNodos(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,4);
		grafo.agregarEje(4,5);
		
		assertEquals(5,grafo.getCantNodos());
	}
	
	@Test
	public void testCantNodosOcultando(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		
		assertEquals(3,grafo.getCantNodos());
		grafo.ocultarNodo(2);
		
		grafo.mostrarNodo(2);
		assertEquals(3,grafo.getCantNodos());
		
	}
	

	@Test
	public void testCantEjes(){
		GrafoEjes grafo = new Main.GrafoEjes();
		assertEquals(0,grafo.cantEjes);
		grafo.agregarEje(1,2);
		assertEquals(1,grafo.cantEjes);
	}
	
	@Test
	public void testCantEjesMultiGrafo(){
		GrafoEjes grafo = new Main.GrafoEjes();
		assertEquals(0,grafo.cantEjes);
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,1);
		assertEquals(2,grafo.cantEjes);
	}
	
	@Test
	public void testCantEjes2(){
		GrafoEjes grafo = new Main.GrafoEjes();
		assertEquals(0,grafo.cantEjes);
		grafo.agregarEje(1,2);
		grafo.agregarEje(1,3);
		assertEquals(2,grafo.cantEjes);
	}
	
	@Test
	public void testCantEjesOcultando(){
		GrafoEjes grafo = new Main.GrafoEjes();
		assertEquals(0,grafo.cantEjes);
		grafo.agregarEje(1,2);
		assertEquals(1,grafo.cantEjes);
		grafo.ocultarEje(1,2);
		assertEquals(0,grafo.cantEjes);
	}
	
	@Test
	public void testUnNodo(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,2);
		Integer nodo = grafo.getNodo();
		assertEquals(nodo,new Integer(1));
	}
	
	@Test
	public void testAgregarEjes(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,2);
		assertTrue(grafo.hayEje(1,2));
		assertTrue(grafo.hayEje(2,1));
	}
	
	@Test
	public void testAgregarMasEjes(){
		GrafoEjes grafo = new GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,4);
		grafo.agregarEje(2,5);
		grafo.agregarEje(5,7);
		assertEquals("[(2,1)(2,3)(2,5)]",printList(grafo.ejes(2)));
	}
	
	@Test
	public void testEjeOculto(){
		GrafoEjes grafo = new GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.ocultarEje(1,2);
		assertEquals("[]",printList(grafo.ejes(1)));
	}
	
	@Test
	public void testEjesDeNodo(){
		GrafoEjes grafo = new GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,4);
		assertEquals("[(1,2)]",printList(grafo.ejes(1)));
	}
	@Test
	public void testEjesDeNodo2(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,4);

		grafo.ocultarEje(2,3);
		
		assertEquals("[(1,2)]",printList(grafo.ejes(1)));
	}
	
	@Test
	public void testEjesOcultos(){
		GrafoEjes grafo = new GrafoEjes();
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
		GrafoEjes grafo = new GrafoEjes();
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
		GrafoEjes grafo = new GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.ocultarNodo(1);
		assertTrue(grafo.estaOcultoNodo(1));
	}
	
	@Test
	public void testVecinosDeNodo(){
		GrafoNodos grafo = new Main.GrafoNodos();
		grafo.agregarEje(1,2);
		grafo.agregarEje(1,3);
		grafo.agregarEje(1,4);
		List<Integer> vecinos = grafo.getVecinos(1);
		assertEquals(3, vecinos.size());
		assertTrue(vecinos.contains(2));
		assertTrue(vecinos.contains(3));
		assertTrue(vecinos.contains(4));
		
	}
	
	@Test
	public void testVecinosDeUnNodoOcultando(){
		GrafoNodos grafo = new Main.GrafoNodos();
		grafo.agregarEje(1,2);
		grafo.agregarEje(1,3);
		grafo.agregarEje(1,4);
		grafo.ocultarNodo(3);
		List<Integer> vecinos = grafo.getVecinos(1);
		assertEquals(2, vecinos.size());
		assertTrue(vecinos.contains(2));
		assertTrue(vecinos.contains(4));
	}
	
	
	@Test
	public void testNodoOculto(){
		GrafoEjes grafo = new GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.ocultarNodo(2);
		assertEquals("[]",printList(grafo.ejes(1)));
		assertEquals("[]",printList(grafo.ejes(2)));
	}
	
	@Test
	public void testGradoDeUnNodo(){
		GrafoEjes grafo = new GrafoEjes();
		grafo.agregarEje(1,2);
		assertEquals(1,grafo.gradoNodo(1));
		assertEquals(1,grafo.gradoNodo(2));
	}
	
	@Test
	public void testGradoDeDosNodos(){
		GrafoEjes grafo = new GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.agregarEje(1,2);
		assertEquals(2,grafo.gradoNodo(1));
		assertEquals(2,grafo.gradoNodo(2));
	}
	
	@Test
	public void testGradoDeVariosNodos(){
		GrafoEjes grafo = new GrafoEjes();
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
		GrafoEjes grafo = new Main.GrafoEjes();
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
	public void testGradoDeDosNodosConEjeOculto(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.agregarEje(1,2);
		assertEquals(2,grafo.gradoNodo(1));
		assertEquals(2,grafo.gradoNodo(2));
		grafo.ocultarEje(1,2);
		assertEquals(1,grafo.gradoNodo(1));
		assertEquals(1,grafo.gradoNodo(2));
		grafo.ocultarEje(1,2);
		assertEquals(0,grafo.gradoNodo(1));
		assertEquals(0,grafo.gradoNodo(2));
		
		grafo.mostrarEje(1,2);
		assertEquals(1,grafo.gradoNodo(1));
		assertEquals(1,grafo.gradoNodo(2));
		grafo.mostrarEje(1,2);
		assertEquals(2,grafo.gradoNodo(1));
		assertEquals(2,grafo.gradoNodo(2));

	}
	
	@Test
	public void testCeroNodosGradoImpar(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,4);
		grafo.agregarEje(4,1);
		assertEquals(0, grafo.getNodosGradoImpar().size());
	}
	
	@Test
	public void testUnNodoGradoImpar(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,1);
		assertEquals(1, grafo.getNodosGradoImpar().get(0).intValue());
	}
	
	@Test
	public void testTodosGradoImpar(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,1);
		grafo.agregarEje(2,2);
		grafo.agregarEje(3,3);
		grafo.agregarEje(4,4);
		assertEquals(4, grafo.getNodosGradoImpar().size());
		assertTrue(grafo.getNodosGradoImpar().contains(1));
		assertTrue(grafo.getNodosGradoImpar().contains(2));
		assertTrue(grafo.getNodosGradoImpar().contains(3));
		assertTrue(grafo.getNodosGradoImpar().contains(4));
	}
	
	@Test
	public void testUnNodoGradoImparYOtrosTresNodos(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,4);
		grafo.agregarEje(4,2);
		assertEquals(2, grafo.getNodosGradoImpar().size());
		assertTrue(grafo.getNodosGradoImpar().contains(1));
		assertTrue(grafo.getNodosGradoImpar().contains(2));
	}
	
	@Test
	public void testNodosGradoImparConK4(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.agregarEje(1,3);
		grafo.agregarEje(1,4);
		grafo.agregarEje(2,3);
		grafo.agregarEje(2,4);
		grafo.agregarEje(3,4);
		assertEquals(4, grafo.getNodosGradoImpar().size());
		assertTrue(grafo.getNodosGradoImpar().contains(1));
		assertTrue(grafo.getNodosGradoImpar().contains(2));
		assertTrue(grafo.getNodosGradoImpar().contains(3));
		assertTrue(grafo.getNodosGradoImpar().contains(4));
	}
	
	@Test
	public void testDosNodosGradoImpar(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,2);
		assertEquals(2, grafo.getNodosGradoImpar().size());
	}
	
	@Test
	public void testPeso(){
		Eje eje1 = new Eje(1,2,12);
		Eje eje2 = new Eje(2,2,22);
		
		assertEquals(12,eje1.getPeso().intValue());
		assertEquals(22,eje2.getPeso().intValue());
	}
	
	@Test
	public void testRevert(){
		Eje eje1 = new Eje(1,2);
		Eje eje2 = new Eje(2,2);
		
		assertEquals("[2,1]",GrafoEjes.revert(eje1).toString());
		assertEquals("[2,2]",GrafoEjes.revert(eje2).toString());
	}
	
	@Test
	public void testeMismoEje(){
		Eje eje1 = new Eje(1,2);
		Eje eje2 = new Eje(2,1);
		
		Eje eje3 = new Eje(1,3);
		
		assertTrue(GrafoEjes.mismoEje(eje1, eje2));
		assertTrue(GrafoEjes.mismoEje(eje2, eje1));
		
		assertFalse(GrafoEjes.mismoEje(eje1, eje3));
		assertFalse(GrafoEjes.mismoEje(eje2, eje3));
		assertFalse(GrafoEjes.mismoEje(eje3, eje1));
		assertFalse(GrafoEjes.mismoEje(eje3, eje2));
	}
	
	@Test
	public void testCompareEjes(){
		Eje eje1 = new Eje(1,2);
		Eje eje2 = new Eje(2,1);
		Eje eje3 = new Eje(3,1);
		
		assertTrue(eje1.equals(eje1));
		assertTrue(eje2.equals(eje2));
		assertTrue(eje1.equals(eje2));
		
		assertFalse(eje1.equals(eje3));
		assertFalse(eje2.equals(eje3));
		assertFalse(eje3.equals(eje1));
		assertFalse(eje3.equals(eje1));
	}
	
	@Test
	public void testUnNodoConexo(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,1);
		grafo.ocultarEje(1,1);
		assertTrue(grafo.esConexo());
	}
	
	@Test
	public void testUnEjeUnNodoConexo(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,1);
		assertTrue(grafo.esConexo());
	}
	
	@Test //Soporta PseudoGrafos
	public void testDosInConexo(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,1);
		grafo.agregarEje(2,2);
		assertFalse(grafo.esConexo());
	}
	
	@Test
	public void testDosInConexo2(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.ocultarEje(1,2);
		assertFalse(grafo.esConexo());
	}
	
	@Test
	public void testDosYDosInConexo(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		
		grafo.agregarEje(4,5);
		grafo.agregarEje(5,6);
		assertFalse(grafo.esConexo());
	}
	
	@Test
	public void testesCuatroConexo(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,4);
		assertTrue(grafo.esConexo());
	}
	
	@Test
	public void testOcultandoInConexo(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,4);
		assertTrue(grafo.esConexo());

		grafo.ocultarEje(1,2);
		assertFalse(grafo.esConexo());
		
		grafo.mostrarEje(1,2);
		assertTrue(grafo.esConexo());
		
		grafo.ocultarEje(2,3);
		assertFalse(grafo.esConexo());
		
		grafo.mostrarEje(2,3);
		assertTrue(grafo.esConexo());
		
		grafo.ocultarEje(3,4);
		assertFalse(grafo.esConexo());
		
		grafo.mostrarEje(3,4);
		assertTrue(grafo.esConexo());
	}
	
	@Test
	public void testContains(){
		LinkedHashSet<Eje> explorando = new LinkedHashSet<Eje>();
		Eje eje1 = new Eje(1,2);
		Eje eje1bis = new Eje(1,2);
		Eje eje2 = new Eje(2,1);
		
		explorando.add(eje1);
		assertTrue(explorando.contains(eje1));
		assertTrue(explorando.contains(eje1bis));
		assertTrue(explorando.contains(eje2));
	}
	
	@Test
	public void testOcultandoPuenteInConexo(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,4);
		
		grafo.agregarEje(5,6);
		grafo.agregarEje(6,7);
		grafo.agregarEje(7,8);
		
		grafo.agregarEje(1,8);
		assertTrue(grafo.esConexo());
		
		grafo.ocultarEje(1,8);
		assertFalse(grafo.esConexo());
	}
	
	@Test
	public void testOcultandoDosPuentesInConexo(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,4);
		
		grafo.agregarEje(5,6);
		grafo.agregarEje(6,7);
		grafo.agregarEje(7,8);
		
		grafo.agregarEje(1,8);
		grafo.agregarEje(2,7);
		assertTrue(grafo.esConexo());
		
		grafo.ocultarEje(1,8);
		assertTrue(grafo.esConexo());
		
		grafo.ocultarEje(2,7);
		assertFalse(grafo.esConexo());
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
	
	private static String printList(List<Eje> lista){
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (Eje eje : lista) {
			sb.append("(" + eje.getNodo1() + "," + eje.getNodo2() + ")");
		}
		sb.append("]");
		return sb.toString();
	}
	
}
