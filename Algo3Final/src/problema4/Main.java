package problema4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
	
	public static void main(String[] args) {

		//runTests();
		//runTests(0);
		//runForOnlineJudge();
		runTestsGrafo();
	}
	
	public static void runTestsGrafo(){
		Grafo grafo = new Grafo();
		grafo.agregarEje(1,2);
		assertTrue(grafo.hayEje(1,2));
		
		grafo = new Grafo();
		grafo.agregarEje(2,1);
		assertTrue(grafo.hayEje(1,2));
		
		grafo = new Grafo();
		grafo.agregarEje(2,1);
		grafo.agregarEje(1,2);
		assertTrue(grafo.hayEje(1,2));
		assertTrue(grafo.hayEje(2,1));
		
		grafo = new Grafo();
		grafo.agregarEje(2,1);
		grafo.agregarEje(1,2);
		assertTrue("[(1,2)]".equals(printList(grafo.ejes(1))));
		assertTrue("[(2,1)]".equals(printList(grafo.ejes(2))));
		
		grafo = new Grafo();
		grafo.agregarEje(1,2);
		grafo.agregarEje(2,3);
		grafo.agregarEje(3,4);
		grafo.agregarEje(2,5);
		grafo.agregarEje(5,7);
		print(grafo.ejes(2));
	}
	
	private static void assertTrue(boolean valor){
		System.out.println(valor? "OK": "FAIL");
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
	
	private static void print(List<Eje> list){
		System.out.println(printList(list));
	}
	
	private static void print(String string){
		System.out.println(string);
	}
	
    public static class Grafo {
        private Map<Integer, LinkedHashSet<Integer>> nodoAdjacentes = new HashMap();
        public int cantEjes = 0;

        public Integer unNodo(){
        	return nodoAdjacentes.keySet().iterator().next();
        }
        
        public void agregarEje(Integer nodo1, Integer nodo2) {
        	boolean agrego1 = agregarAAdjacentes(nodo1,nodo2);
        	boolean agrego2 = agregarAAdjacentes(nodo2,nodo1);
        	
        	if(agrego1 || agrego2){
        		cantEjes++;
        	}
        }
        
        private boolean agregarAAdjacentes(Integer nodo1, Integer nodo2){
        	LinkedHashSet<Integer> adjacent = nodoAdjacentes.get(nodo1);
            
            if(adjacent == null) {
            	adjacent = new LinkedHashSet<Integer>();
            	nodoAdjacentes.put(nodo1, adjacent);
            }
            
            if(!adjacent.contains(nodo2)){
            	adjacent.add(nodo2);
            	return true;
            }
            return false;
        }

        public boolean hayEje(Integer node1, Integer node2) {
            Set<Integer> adyacentes1 = nodoAdjacentes.get(node1);
            Set<Integer> adyacentes2 = nodoAdjacentes.get(node2);
            
            if(adyacentes1 == null)  {
                return false;
            }
            return adyacentes1.contains(node2) || adyacentes2.contains(node1);
        }

        public LinkedList<Eje> ejes(Integer nodo) {
            LinkedHashSet<Integer> adyacentes = nodoAdjacentes.get(nodo);
            
            if(adyacentes == null) {
                return new LinkedList<Eje>();
            }
            
            LinkedList<Eje> ejes = new LinkedList<Eje>();
            for (Integer unNodo : adyacentes) {
				ejes.add(new Eje(nodo, unNodo));
			}
            
            return ejes;
        }
    }
    
    public static class Eje{
    	
    	Integer nodo1;
    	Integer nodo2;
    	boolean visible;
    	
    	public Eje(Integer nodo1, Integer nodo2){
    		this.nodo1 = nodo1;
    		this.nodo2 = nodo2;
    		visible = true;
    	}
    	
    	public void ocultar(){
    		visible = false;
    	}
    	
    	public void mostrar(){
    		visible = true;
    	}
    	
    	public Integer getNodo1(){
    		return nodo1; 
    	}
    	
    	public Integer getNodo2(){
    		return nodo2; 
    	}
    	
    	public Integer getOpposite(Integer nodo){
    		if(nodo1.equals(nodo)){
    			return nodo2;
    		}
    		if(nodo2.equals(nodo)){
    			return nodo1;
    		}
    		return null;
    	}
    }
    
    public List<Eje> fleury(Grafo grafo){
    	
    	int aristasRecorridas = 0;
    	List<Eje> recorrido = new ArrayList<Eje>();
    	int aristasTotales = grafo.cantEjes;
    	Integer nodo1 = grafo.unNodo();
    	Integer nodo2 = nodo1;
    	while(nodo1 != nodo2 || aristasRecorridas != aristasTotales){
    		
    		for (Eje eje : grafo.ejes(nodo1)) {
				Integer nodoAdj = eje.getOpposite(nodo1);
				
			}
    	}

    }
}
