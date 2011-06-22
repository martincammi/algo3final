package problema4;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import problema4.Main.Eje;
import problema4.Main.Grafo;


public class CarteroChinoTest {

	@Test
	public void testDosNodosFleury(){
		Grafo grafo = new Main.Grafo();
		grafo.agregarEje(1,2);
		grafo.agregarEje(1,2);
		Main main = new Main();
		List<Eje> recorrido = main.fleury(grafo);
		assertEquals("[(1,2)(2,1)]",printList(recorrido));
	}
	
	@Test
	public void testFleuryTresNodos(){
		Grafo grafo = new Main.Grafo();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,1);
		Main main = new Main();
		List<Eje> recorrido = main.fleury(grafo);
		assertEquals("[(1,2)(2,3)(3,1)]",printList(recorrido));
	}
	
	@Test
	public void testFleuryCuatroNodos(){
		Grafo grafo = new Main.Grafo();
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
		Grafo grafo = new Main.Grafo();
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
		Grafo grafo = new Main.Grafo();
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
		Grafo grafo = new Main.Grafo();
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
		Grafo grafo = new Main.Grafo();
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
		Grafo grafo = new Main.Grafo();
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
	public void testCarteroChino(){
		Main main = new Main();
		Grafo grafo = new Main.Grafo();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,4);
		grafo.agregarEje(4,2);
		List<Eje> recorrido = main.carteroChino(grafo);
		assertEquals("[(1,2)(2,3)(3,4)(4,2)(2,1)]",printList(recorrido));
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
