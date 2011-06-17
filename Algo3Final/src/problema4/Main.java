package problema4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
	
	public static void main(String[] args) {

		//runTests();
		//runTests(0);
		//runForOnlineJudge();
	}
	
	 public List<Eje> fleury(Grafo grafo){
	    	
    	int aristasRecorridas = 0;
    	List<Eje> recorrido = new ArrayList<Eje>();
    	int aristasTotales = grafo.cantEjes;
    	Integer nodo1 = grafo.getNodo();
    	Integer nodo2 = nodo1;
    	while(nodo1 != nodo2 || aristasRecorridas != aristasTotales){
    		
    		for (Eje eje : grafo.ejes(nodo1)) {
				Integer nodoAdj = eje.nodo2;
				grafo.ocultarEje(eje);
				if (grafo.gradoNodo(nodoAdj) == 0) {
					grafo.ocultarNodo(nodo1);
				}
				if(grafo.esConexo()){
					
				}
			}
    	}

	  }
	
	
    public static class Grafo {
        private Map<Integer, LinkedHashSet<Eje>> mapaAdyacencia = new HashMap();
        private Map<Integer, Boolean> nodosVisibles = new HashMap();
        public int cantEjes = 0;

        /**
         * Devuelve un nodo cualquiera visible
         * @return
         */
        public Integer getNodo(){
        	
        	for (Integer nodo: mapaAdyacencia.keySet()) {
        		if(nodosVisibles.get(nodo)){
        			return nodo;
        		}
			}
        	return null;
        }
        
        public void agregarEje(Integer nodo1, Integer nodo2) {
        	agregarAAdjacentes(nodo1,nodo2);
        	agregarAAdjacentes(nodo2,nodo1);
        }
        
        public boolean hayEje(Integer nodo1, Integer nodo2){
        	LinkedHashSet<Eje> adyacentes = mapaAdyacencia.get(nodo1);
        	
        	for (Eje eje : adyacentes) {
				if(eje.nodo2.equals(nodo2)){
					return true;
				}
			}
        	return false;
        }
        
        private void agregarAAdjacentes(Integer nodo1, Integer nodo2){
        	LinkedHashSet<Eje> adyacentes = mapaAdyacencia.get(nodo1);
        	mostrarNodo(nodo1);
            
            if(adyacentes == null) {
            	adyacentes = new LinkedHashSet<Eje>();
            }

            //No se chequea que el eje exista -> podria ser un multigrafo.
            Eje unEje = new Eje(nodo1,nodo2);
        	adyacentes.add(unEje);
        	mapaAdyacencia.put(nodo1, adyacentes);
        	cantEjes++;
        }

        public Set<Eje> ejes(Integer nodo) {
        	LinkedHashSet<Eje> adyacentes = mapaAdyacencia.get(nodo);
        	LinkedHashSet<Eje> respuesta = new LinkedHashSet<Eje>();  
        	
        	for (Eje eje : adyacentes) {
				if(eje.visible){
					if(!estaOcultoNodo(eje.nodo1) && !estaOcultoNodo(eje.nodo2)){
						respuesta.add(eje);
					}
				}
			}
        	
        	return respuesta;
        }
        
        public int gradoNodo(Integer nodo){
        	LinkedHashSet<Eje> adyacentes = mapaAdyacencia.get(nodo);
        	return adyacentes.size();
        }
        
        public void ocultarNodo(Integer nodo){
        	nodosVisibles.put(nodo, false);
        }
        
        public void mostrarNodo(Integer nodo){
        	nodosVisibles.put(nodo, true);
        }
        
        public boolean estaOcultoNodo(Integer nodo){
        	return !nodosVisibles.get(nodo);
        }
        
        public void ocultarEje(Eje eje){
        	Integer nodo1 = eje.nodo1;
        	Integer nodo2 = eje.nodo2;
        	ocultarEje(nodo1,nodo2);
        }
        
        public void ocultarEje(Integer nodo1, Integer nodo2){
        	cambiarEstadoDeUnEje(nodo1,nodo2, false);
        	cambiarEstadoDeUnEje(nodo2,nodo1, false);
        }
        
        public void mostrarEje(Integer nodo1, Integer nodo2){
        	cambiarEstadoDeUnEje(nodo1,nodo2, true);
        	cambiarEstadoDeUnEje(nodo2,nodo1, true);
        }
        
        /**
         * Oculta un eje para los adyacentes del nodo1
         * @param nodo1
         * @param nodo2
         */
        private void cambiarEstadoDeUnEje(Integer nodo1, Integer nodo2, boolean visible){
        	LinkedHashSet<Eje> adyacentes = mapaAdyacencia.get(nodo1);
        	
        	for (Eje eje : adyacentes) {
    			if(eje.nodo2.equals(nodo2)){
    				if(visible){
    					eje.mostrar();
    				}else{
    					eje.ocultar();
    				}
    			}
			}        	
        }
        
        public boolean esConexo(){
        	
        	/*
        	choose a vertex x
        	make a list L of vertices reachable from x,
        	and another list K of vertices to be explored.
        	initially, L = K = x.

        	while K is nonempty
        	find and remove some vertex y in K
        	for each edge (y,z)
        	if (z is not in L)
        	add z to both L and K

        	if L has fewer than n items
        	return disconnected
        	else return connected
        	*/
        	return false;
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
}
