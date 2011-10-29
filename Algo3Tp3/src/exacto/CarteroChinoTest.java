package exacto;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import exacto.Main.Eje;
import exacto.Main.GrafoEjes;
import exacto.Main.GrafoNodos;


public class CarteroChinoTest {

	@Test
	public void testDosNodosFleury(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.agregarEje(1,2);
		Main main = new Main();
		List<Eje> recorrido = main.fleury(grafo);
		assertEquals("[(1,2)(2,1)]",printList(recorrido));
		System.out.println("Costo: Fleury dos nodos " + grafo.complejidadTemporal);
	}
	
	@Test
	public void testFleuryTresNodos(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,1);
		Main main = new Main();
		List<Eje> recorrido = main.fleury(grafo);
		assertEquals("[(1,2)(2,3)(3,1)]",printList(recorrido));
		System.out.println("Costo: Fleury tres nodos " + grafo.complejidadTemporal);
	}
	
	@Test
	public void testFleuryCuatroNodos(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,4);
		grafo.agregarEje(4,1);
		Main main = new Main();
		List<Eje> recorrido = main.fleury(grafo);
		assertEquals("[(1,2)(2,3)(3,4)(4,1)]",printList(recorrido));
		System.out.println("Costo: Fleury cuatro nodos " + grafo.complejidadTemporal);
	}
	
	@Test
	public void testFleurySieteNodos(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,4);
		grafo.agregarEje(4,5);
		grafo.agregarEje(5,6);
		grafo.agregarEje(6,1);
		grafo.agregarEje(6,2);
		grafo.agregarEje(6,7);
		grafo.agregarEje(7,2);
		Main main = new Main();
		List<Eje> recorrido = main.fleury(grafo);
		assertEquals("[(1,2)(2,3)(3,4)(4,5)(5,6)(6,2)(2,7)(7,6)(6,1)]",printList(recorrido));
		System.out.println("Costo: Fleury siete nodos " + grafo.complejidadTemporal);
	}
	
	@Test
	public void testFleuryNueveNodos(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.agregarEje(1,3);
		grafo.agregarEje(1,4);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,4);
		grafo.agregarEje(6,1);
		grafo.agregarEje(6,7);
		grafo.agregarEje(6,8);
		grafo.agregarEje(6,9);
		grafo.agregarEje(7,8);
		grafo.agregarEje(8,9);
		grafo.agregarEje(8,3);
		Main main = new Main();
		List<Eje> recorrido = main.fleury(grafo);
		assertEquals("[(1,2)(2,3)(3,1)(1,4)(4,3)(3,8)(8,6)(6,7)(7,8)(8,9)(9,6)(6,1)]",printList(recorrido));
		System.out.println("Costo: Fleury nueve nodos " + grafo.complejidadTemporal);
	}
	
	@Test
	public void testFleurySeisConDosNodosMultigrafo(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(2,1);
		grafo.agregarEje(2,3);
		grafo.agregarEje(1,3);
		grafo.agregarEje(4,5);
		grafo.agregarEje(4,6);
		grafo.agregarEje(5,6);
		grafo.agregarEje(2,4);
		grafo.agregarEje(2,4);
		Main main = new Main();
		List<Eje> recorrido = main.fleury(grafo);
		assertEquals("[(1,2)(2,4)(4,5)(5,6)(6,4)(4,2)(2,3)(3,1)]",printList(recorrido));
		System.out.println("Costo: Fleury seis nodos " + grafo.complejidadTemporal);
	}
	
	@Test
	public void testFleuryCuatroNodosMultigrafo(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,1);
		grafo.agregarEje(3,4);
		grafo.agregarEje(3,4);
		Main main = new Main();
		List<Eje> recorrido = main.fleury(grafo);
		assertEquals("[(1,2)(2,3)(3,4)(4,3)(3,1)]",printList(recorrido));
		System.out.println("Costo: Fleury cuatro nodos " + grafo.complejidadTemporal);
	}
	
	@Test
	public void testFleurySeisNodos(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,4);
		grafo.agregarEje(4,5);
		grafo.agregarEje(5,6);
		grafo.agregarEje(6,2);
		grafo.agregarEje(1,5);
		grafo.agregarEje(2,5);
		Main main = new Main();
		List<Eje> recorrido = main.fleury(grafo);
		System.out.println(printList(recorrido));
		System.out.println("Costo: Fleury seis nodos " + grafo.complejidadTemporal);
	}
	
	@Test
	public void testDijkstraUnNodo(){
		GrafoNodos grafo = new Main.GrafoNodos();
		grafo.agregarEje(1,2,1);
		Main main = new Main();
		List<Eje> camino = main.dijkstra(grafo,1,2);
		assertEquals("[(1,2)]", printList(camino));
	}
	
	@Test
	public void testDijkstraVariosNodos(){
		GrafoNodos grafo = new Main.GrafoNodos();
		grafo.agregarEje(1,2,1);
		grafo.agregarEje(1,3,2);
		grafo.agregarEje(2,4,3);
		grafo.agregarEje(3,4,1);
		Main main = new Main();
		List<Eje> camino = main.dijkstra(grafo,1,4);
		assertEquals("[(1,3)(3,4)]", printList(camino));
	}
	
	@Test
	public void testDijkstraVariosNodos2(){
		GrafoNodos grafo = new Main.GrafoNodos();
		grafo.agregarEje(1,2,2);
		grafo.agregarEje(1,3,1);
		grafo.agregarEje(2,4,1);
		grafo.agregarEje(3,4,3);
		Main main = new Main();
		List<Eje> camino = main.dijkstra(grafo,1,4);
		assertEquals("[(1,2)(2,4)]", printList(camino));
	}
	
	@Test
	public void testDijkstraVariosNodos3(){
		GrafoNodos grafo = new Main.GrafoNodos();
		grafo.agregarEje(1,2,1);
		grafo.agregarEje(1,3,2);
		grafo.agregarEje(1,4,3);
		grafo.agregarEje(2,5,6);
		grafo.agregarEje(3,6,4);
		grafo.agregarEje(4,7,5);
		grafo.agregarEje(5,8,9);
		grafo.agregarEje(6,8,10);
		grafo.agregarEje(7,8,7);
		Main main = new Main();
		List<Eje> camino = main.dijkstra(grafo,1,8);
		assertEquals("[(1,4)(4,7)(7,8)]", printList(camino));
	}
	
	@Test
	public void testCarteroChino(){
		Main main = new Main();
		GrafoNodos grafo = new Main.GrafoNodos();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,4);
		grafo.agregarEje(4,2);
		assertEquals(new Integer(0),main.carteroChino(grafo));
		System.out.println("Costo: Cartero Chino cuatro nodos " + grafo.complejidadTemporal);
	}
	
	@Test
	public void testCarteroChino2(){
		Main main = new Main();
		GrafoNodos grafo = new Main.GrafoNodos();
		grafo.agregarEje(1,2,1);
		grafo.agregarEje(2,3,6);
		grafo.agregarEje(2,4,6);
		grafo.agregarEje(2,5,1);
		grafo.agregarEje(3,6,1);
		grafo.agregarEje(4,6,1);
		grafo.agregarEje(5,6,5);
		assertEquals(new Integer(28),main.carteroChino(grafo));
		System.out.println("Costo: Cartero Chino seis nodos " + grafo.complejidadTemporal);
	}
	
	@Test
	public void testCarteroChino3(){
		Main main = new Main();
		GrafoNodos grafo = new Main.GrafoNodos();
		grafo.agregarEje(1,2,1);
		grafo.agregarEje(2,3,1);
		grafo.agregarEje(3,4,1);
		assertEquals(new Integer(6),main.carteroChino(grafo));
		System.out.println("Costo: Cartero Chino cuatro nodos " + grafo.complejidadTemporal);
	}
	
	@Test
	public void testCarteroChino4(){
		Main main = new Main();
		GrafoNodos grafo = new Main.GrafoNodos();
		grafo.agregarEje(1,2,1);
		grafo.agregarEje(2,3,1);
		grafo.agregarEje(2,5,1);
		grafo.agregarEje(2,7,1);
		grafo.agregarEje(3,4,1);
		grafo.agregarEje(3,6,1);
		grafo.agregarEje(3,7,1);
		grafo.agregarEje(5,6,1);
		assertEquals(new Integer(11),main.carteroChino(grafo));
		System.out.println("Costo: Cartero Chino siete nodos " + grafo.complejidadTemporal);
	}
	
	@Test
	public void testCarteroChino5(){
		Main main = new Main();
		GrafoNodos grafo = new Main.GrafoNodos();
		grafo.agregarEje(1,2,1);
		grafo.agregarEje(2,3,2);
		grafo.agregarEje(2,5,3);
		grafo.agregarEje(2,7,4);
		grafo.agregarEje(3,4,5);
		grafo.agregarEje(5,4,6);
		grafo.agregarEje(7,4,7);
		grafo.agregarEje(7,6,8);
		grafo.agregarEje(4,6,9);
		assertEquals(new Integer(50),main.carteroChino(grafo));
		System.out.println("Costo: Cartero Chino siete nodos " + grafo.complejidadTemporal);
	}
	
	@Test
	public void testCarteroChinoCiclosAutomatico(){
		Main main = new Main();
		int maxNodos = 100; 
		GrafoNodos grafo;
		for (int i = 100; i < maxNodos; i++) {
			grafo = generadorGrafosCiclos(i);
			assertEquals(new Integer(i),main.carteroChino(grafo));
			System.out.println(grafo.complejidadTemporal);
		}
	}
	
	@Test
	public void testCarteroChinoCiclosYDosImparesConsecutivos(){
		Main main = new Main();
		int maxNodos = 100; 
		GrafoNodos grafo;
		for (int i = 2; i < maxNodos; i++) {
			grafo = generadorGrafosCiclos(i);
			grafo.agregarEje(i,i-1,2); //Agrego un nodo con un eje esto creara dos nodos de grado impar
			
			assertEquals(new Integer(i+4),main.carteroChino(grafo));
			//System.out.println("Complejidad: " + grafo.costo);
			//System.out.println("---------------");
			System.out.println(grafo.complejidadTemporal);
		}
	}
	
	@Test
	public void testCarteroChinoGrafosCompletos(){
		Main main = new Main();
		int maxNodos = 50; 
		GrafoNodos grafo;
		for (int i = 3; i < maxNodos; i=i+2) {
			grafo = generadorGrafosCompletos(i);
			//System.out.println("Cartero chino sobre K" + i);
			assertEquals(new Integer(i*(i-1)/2),main.carteroChino(grafo));
			//System.out.println("---------------");
			System.out.println(grafo.complejidadTemporal);
		}
	}
	
	@Test
	public void testCarteroChinoGrafosSeisNodos(){
		Main main = new Main();
		GrafoNodos grafo = new Main.GrafoNodos();
		
		grafo.agregarEje(1,2,1);
		grafo.agregarEje(1,3,1);
		
		grafo.agregarEje(2,3,1);
		grafo.agregarEje(2,4,1);
		grafo.agregarEje(2,5,1);

		grafo.agregarEje(3,4,1);
		grafo.agregarEje(3,6,1);
		
		grafo.agregarEje(4,5,1);
		grafo.agregarEje(4,6,1);
		
		grafo.agregarEje(5,6,1);
		grafo.agregarEje(5,7,1);
		
		grafo.agregarEje(6,7,1);
		
		assertEquals(new Integer(12),main.carteroChino(grafo));
		System.out.println("Costo: Cartero Chino siete nodos " + grafo.complejidadTemporal);
	}
	
	@Test
	public void generadorGrafosCiclosTest(){
		generadorGrafosCiclos(1);	
	}
	
	public GrafoNodos generadorGrafosCompletos(int cantNodos){
		
		GrafoNodos grafo = new Main.GrafoNodos();
		//System.out.println("Generando grafo K" + cantNodos);
		for (int j = 0; j < cantNodos; j++) {
			for (int i = 0; i < cantNodos; i++) {
				if(!grafo.hayEje(j, i) && j!=i){
					grafo.agregarEje(j,i,1);
				}
			}
		}
		return grafo;
	}
	
	public GrafoNodos generadorGrafosCiclos(int cantNodos){
		
		GrafoNodos grafo = new Main.GrafoNodos();

		for (int i = 0; i < cantNodos; i++) {
			int nodoSiguiente  = (i+1)%cantNodos;
			grafo.agregarEje(i,nodoSiguiente,1);
		}
		
//		System.out.println("Grafo nuevo generado");
//		System.out.println("Nodos: " + grafo.cantNodos);
//		System.out.println("Ejes: " + grafo.cantEjes);
//		System.out.println("Grado Impar: " + grafo.getNodosGradoImpar().size());
		
		return grafo;
	}
	
	public static String printList(Set<Eje> lista){
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (Eje eje : lista) {
			sb.append("(" + eje.getNodo1() + "," + eje.getNodo2() + ")");
		}
		sb.append("]");
		return sb.toString();
	}
	
	public static String printList(List<Eje> lista){
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (Eje eje : lista) {
			sb.append("(" + eje.getNodo1() + "," + eje.getNodo2() + ")");
		}
		sb.append("]");
		return sb.toString();
	}
}
