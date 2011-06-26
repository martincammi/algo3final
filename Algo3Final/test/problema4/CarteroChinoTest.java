package problema4;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import problema4.Main.Eje;
import problema4.Main.GrafoEjes;
import problema4.Main.GrafoNodos;


public class CarteroChinoTest {

	@Test
	public void testDosNodosFleury(){
		GrafoEjes grafo = new Main.GrafoEjes();
		grafo.agregarEje(1,2);
		grafo.agregarEje(1,2);
		Main main = new Main();
		List<Eje> recorrido = main.fleury(grafo);
		assertEquals("[(1,2)(2,1)]",printList(recorrido));
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
	}
	
	@Test
	public void testFleuryCincoNodos(){
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
	}
	
	@Test
	public void testFleuryDosNodosMultigrafo(){
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
	}
	
	@Test
	public void testCarteroChino3(){
		Main main = new Main();
		GrafoNodos grafo = new Main.GrafoNodos();
		grafo.agregarEje(1,2,1);
		grafo.agregarEje(2,3,1);
		grafo.agregarEje(3,4,1);
		assertEquals(new Integer(6),main.carteroChino(grafo));
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
