package problema2;

import org.junit.Test;


public class Tests {
	
	@Test
	public void testArbolGenerador(){
		int[] listaDeEntrada = {4, 4, 0, 1, 0, 2, 1, 3, 3, 2};
		Grafo grafo = new Grafo(listaDeEntrada);
		Ej2 ej2 = new Ej2();
		ej2.construirArbolGeneradorYEtiquetar(grafo);
		grafo.mostrarEjesMarcados();
	}
	
	@Test
	public void testArbolGenerador2(){
		int[] listaDeEntrada = {6, 5, 0, 1, 0, 5, 1, 2, 1, 3, 3, 4, 4, 2, 4, 5};
		Grafo grafo = new Grafo(listaDeEntrada);
		Ej2 ej2 = new Ej2();
		ej2.construirArbolGeneradorYEtiquetar(grafo);
		grafo.mostrarEjesMarcados();
	}
}
