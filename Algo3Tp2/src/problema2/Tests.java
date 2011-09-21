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
		arbol.getPreorder();
	}
	
	@Test
	public void testHojasTresNodos(){
		int[] listaDeEntrada = {3, 2, 0, 1, 0, 2};
		Grafo grafo = new Grafo(listaDeEntrada);
		Ej2 ej2 = new Ej2();
		Arbol arbol = ej2.construirArbolGenerador(grafo);
		arbol.showHojas();
	}
	
	@Test
	public void testHojasSeisNodos(){
		int[] listaDeEntrada = {6, 9, 0,1, 0,4, 1,2, 1,3, 1,4, 3,4, 3,5, 4,5};
		Grafo grafo = new Grafo(listaDeEntrada);
		Ej2 ej2 = new Ej2();
		Arbol arbol = ej2.construirArbolGenerador(grafo);
		arbol.showHojas();
	}
	
	@Test
	public void testHojasCompletoSieteNodos(){
		int[] listaDeEntrada = {7, 6, 0,1, 0,4, 1,2, 1,3, 4,5, 4,6};
		Grafo grafo = new Grafo(listaDeEntrada);
		Ej2 ej2 = new Ej2();
		Arbol arbol = ej2.construirArbolGenerador(grafo);
		arbol.showHojas();
	}
	
	@Test
	public void testCantidadHijosCompletoSieteNodos(){
		int[] listaDeEntrada = {7, 6, 0,1, 0,4, 1,2, 1,3, 4,5, 4,6};
		Grafo grafo = new Grafo(listaDeEntrada);
		Ej2 ej2 = new Ej2();
		Arbol arbol = ej2.construirArbolGenerador(grafo);
		ej2.calcularHijos(arbol);
		arbol.showCantHijos();
	}
	
	@Test
	public void testCantidadHijosCompletoQuinceNodos(){
		int[] listaDeEntrada = {15, 14, 0,1, 0,8, 1,2, 1,5, 2,3, 2,4, 5,6, 5,7, 8,9, 8,12, 9,10, 9,11, 12,13, 12,14};
		Grafo grafo = new Grafo(listaDeEntrada);
		Ej2 ej2 = new Ej2();
		Arbol arbol = ej2.construirArbolGenerador(grafo);
		ej2.calcularHijos(arbol);
		arbol.showCantHijos();
	}
}
