package problema2;

import org.junit.Test;
import static org.junit.Assert.*;



public class Tests {
	
	@Test
	public void testArbolGenerador(){
		int[] listaDeEntrada = {4, 4, 0, 1, 0, 2, 1, 3, 3, 2};
		Grafo grafo = new Grafo(listaDeEntrada);
		Ej2 ej2 = new Ej2();
		ej2.construirArbolGenerador(grafo);
		grafo.mostrarEjesMarcados();
		assertEquals("(0,1)(1,3)(3,2)", grafo.getEjesMarcados());
	}
	
	@Test
	public void testArbolGenerador2(){
		int[] listaDeEntrada = {6, 5, 0, 1, 0, 5, 1, 2, 1, 3, 3, 4, 4, 2, 4, 5};
		Grafo grafo = new Grafo(listaDeEntrada);
		Ej2 ej2 = new Ej2();
		ej2.construirArbolGenerador(grafo);
		grafo.mostrarEjesMarcados();
		assertEquals("(0,1)(1,2)(2,4)(4,3)(4,5)", grafo.getEjesMarcados());
	}
	
	@Test
	public void testArbolGenerador3(){
		int[] listaDeEntrada = {5, 15, 0, 1, 0, 2, 0, 3, 0, 4, 1, 2, 1, 3, 1, 4, 2, 3, 2, 4, 3, 4};
		Grafo grafo = new Grafo(listaDeEntrada);
		Ej2 ej2 = new Ej2();
		ej2.construirArbolGenerador(grafo);
		grafo.mostrarEjesMarcados();
		assertEquals("(0,1)(1,2)(2,3)(3,4)", grafo.getEjesMarcados());
	}
	
	@Test
	public void testEtiquetarPreorder(){
		int[] listaDeEntrada = {5, 15, 0, 1, 0, 2, 0, 3, 0, 4, 1, 2, 1, 3, 1, 4, 2, 3, 2, 4, 3, 4};
		Grafo grafo = new Grafo(listaDeEntrada);
		Ej2 ej2 = new Ej2();
		Arbol arbol = ej2.construirArbolGenerador(grafo);
		ej2.etiquetarEnPreorder(arbol);
		arbol.showPreorder();
		String res = arbol.getPreorder();
		assertEquals("1,2,3,4,5,", res);
	}
	
	@Test
	public void testHojasTresNodos(){
		int[] listaDeEntrada = {3, 2, 0, 1, 0, 2};
		Grafo grafo = new Grafo(listaDeEntrada);
		Ej2 ej2 = new Ej2();
		Arbol arbol = ej2.construirArbolGenerador(grafo);
		String res = arbol.getTheHojas();
		assertEquals("1,2,", res);
	}
	
	@Test
	public void testHojasSeisNodos(){
		int[] listaDeEntrada = {6, 9, 0,1, 0,4, 1,2, 1,3, 1,4, 3,4, 3,5, 4,5};
		Grafo grafo = new Grafo(listaDeEntrada);
		Ej2 ej2 = new Ej2();
		Arbol arbol = ej2.construirArbolGenerador(grafo);
		arbol.showHojas();
		String res = arbol.getTheHojas();
		assertEquals("2,5,", res);
	}
	
	@Test
	public void testHojasCompletoSieteNodos(){
		int[] listaDeEntrada = {7, 6, 0,1, 0,4, 1,2, 1,3, 4,5, 4,6};
		Grafo grafo = new Grafo(listaDeEntrada);
		Ej2 ej2 = new Ej2();
		Arbol arbol = ej2.construirArbolGenerador(grafo);
		arbol.showHojas();
		String res = arbol.getTheHojas();
		assertEquals("2,3,5,6,", res);
	}
	
	@Test
	public void testCantidadHijosCompletoSieteNodos(){
		int[] listaDeEntrada = {7, 6, 0,1, 0,4, 1,2, 1,3, 4,5, 4,6};
		Grafo grafo = new Grafo(listaDeEntrada);
		Ej2 ej2 = new Ej2();
		Arbol arbol = ej2.construirArbolGenerador(grafo);
		ej2.etiquetarEnPreorder(arbol);
		arbol.showCantHijos();
		String res = arbol.getCantHijos();
		assertEquals("0,7 | 1,3 | 2,1 | 3,1 | 4,3 | 5,1 | 6,1 | ", res);
	}
	
	@Test
	public void testCantidadHijosCompletoQuinceNodos(){
		int[] listaDeEntrada = {15, 14, 0,1, 0,8, 1,2, 1,5, 2,3, 2,4, 5,6, 5,7, 8,9, 8,12, 9,10, 9,11, 12,13, 12,14};
		Grafo grafo = new Grafo(listaDeEntrada);
		Ej2 ej2 = new Ej2();
		Arbol arbol = ej2.construirArbolGenerador(grafo);
		ej2.etiquetarEnPreorder(arbol);
		arbol.showCantHijos();
		String res = arbol.getCantHijos();
		assertEquals("0,15 | 1,7 | 2,3 | 3,1 | 4,1 | 5,3 | 6,1 | 7,1 | 8,7 | 9,3 | 10,1 | 11,1 | 12,3 | 13,1 | 14,1 | ", res);
	}
	
	@Test
	public void testLower(){
		int[] listaDeEntrada = {6, 7, 0,1, 0,2, 1,2, 2,3, 3,4, 3,5, 4,5};
		Grafo grafo = new Grafo(listaDeEntrada);
		Ej2 ej2 = new Ej2();
		Arbol arbol = ej2.construirArbolGenerador(grafo);
		ej2.etiquetarEnPreorder(arbol);

		arbol.showLower();
		String res = arbol.getTheHojas();
		assertEquals("0,0,0,3,3,3,", res);
		//ej2.calcularLowOrder(arbol,grafo);
	}
}
