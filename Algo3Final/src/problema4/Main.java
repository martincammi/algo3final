package problema4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
	
	 //Precondicion: todos los nodos el grafo deben tener grado par.
	 public List<Eje> fleury(Grafo grafo){

		boolean algunoImpar = false;
			for (Integer nodo : grafo.getNodos()) {
				if(grafo.gradoNodo(nodo) %2 !=0){
					algunoImpar = true;
					break;
				}
			}
		
	 	if(algunoImpar){
	 		//return null;
	 	}
		 
    	int aristasRecorridas = 0;
    	List<Eje> recorrido = new ArrayList<Eje>();
    	int aristasTotales = grafo.cantEjes;
    	Integer nodoActual = grafo.getNodo();
    	Integer nodoInicial = new Integer(nodoActual);
    	while(!nodoActual.equals(nodoInicial) || aristasRecorridas != aristasTotales){
    		
    		for (Eje eje : grafo.ejes(nodoActual)) {
				Integer nodoSiguiente = eje.nodo2;
				grafo.ocultarEje(eje);
				if (grafo.gradoNodo(nodoActual) == 0) {
					grafo.ocultarNodo(nodoActual);
				}
				if(grafo.esConexo()){
					recorrido.add(eje);
					aristasRecorridas++;
					nodoActual = nodoSiguiente;
					break;
				}else{
				  if (grafo.estaOcultoNodo(nodoActual)) {
                         grafo.mostrarNodo(nodoActual);
                  }
                  grafo.mostrarEje(eje);
				}
			}
    	}
    	return recorrido;
	  }
	
	
    public static class Grafo {
        private Map<Integer, LinkedHashSet<Eje>> mapaAdyacencia = new HashMap();
        private Map<Integer, Boolean> nodosVisibles = new HashMap();
        public int cantEjes = 0;
        public int cantNodos = 0;

        public int getCantNodos(){
        	return cantNodos; 
        }
        
        public Set<Integer> getNodos(){
        	Set<Integer> nodos = new HashSet<Integer>();
        	for (Integer nodo : mapaAdyacencia.keySet()) {
        		if(nodosVisibles.get(nodo)){
        			nodos.add(nodo);
        		}
			}
        	
        	return nodos;
        	
        }
        
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
        	
        	if(!nodo1.equals(nodo2)){
        		agregarAAdjacentes(nodo2,nodo1);
        	}
        	cantEjes++;
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
        	//mostrarNodo(nodo1);
        	nodosVisibles.put(nodo1, true);
            
            if(adyacentes == null) {
            	adyacentes = new LinkedHashSet<Eje>();
            	cantNodos++; 
            }

            //No se chequea que el eje exista -> podria ser un multigrafo.
            Eje unEje = new Eje(nodo1,nodo2);
        	adyacentes.add(unEje);
        	mapaAdyacencia.put(nodo1, adyacentes);
        }

        /**
         * Devuelve todos los ejes de un nodo (no devuelve los ocultos)
         * @param nodo
         * @return
         */
        public LinkedHashSet<Eje> ejes(Integer nodo) {
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
        
        //Y si hay ejes no visibles?
        public int gradoNodo(Integer nodo){
        	if(nodosVisibles.get(nodo)){
        		Set<Eje> adyacentes = ejes(nodo);
        		return adyacentes.size();
        	}
        	return 0;
        }
        
        public void ocultarNodo(Integer nodo){
        	nodosVisibles.put(nodo, false);
        	cantNodos--;
        }
        
        public void mostrarNodo(Integer nodo){
        	nodosVisibles.put(nodo, true);
        	cantNodos++;
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
        	cantEjes--;
        }
        
        public void mostrarEje(Eje eje){
        	mostrarEje(eje.nodo1,eje.nodo2);
        }
        
        public void mostrarEje(Integer nodo1, Integer nodo2){
        	cambiarEstadoDeUnEje(nodo1,nodo2, true);
        	cambiarEstadoDeUnEje(nodo2,nodo1, true);
        	cantEjes++;
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
        	
        	Integer nodo = getNodo();
        	LinkedHashSet<Eje> ejesAdy = new LinkedHashSet<Eje>();
        	LinkedHashSet<Eje> adyacentes = ejes(nodo);
        	
        	for (Eje eje : adyacentes) {
				ejesAdy.add(eje);
			}
        	
        	LinkedHashSet<Eje> explorando = new LinkedHashSet<Eje>();
        	
        	for (Eje eje : ejesAdy) {
				explorando.add(eje);
			}
        	Eje unEjePrimerNodo;
        	
        	if(explorando.iterator().hasNext() || gradoNodo(nodo) == 0){
        		unEjePrimerNodo = new Eje(nodo,nodo);	
        	}else{
        		unEjePrimerNodo = null;
        	}
        	
        	while(!explorando.isEmpty()){
        		
        		Eje unEje = explorando.iterator().next();
        		explorando.remove(unEje);

        		for (Eje unEje2 : ejes(unEje.nodo2)) {
        			if(!ejesAdy.contains(unEje2)){
            			ejesAdy.add(unEje2);
            			explorando.addAll(ejes(unEje2.nodo2));
            		}	
				}
        	   	
        	}
        	

        		
        	/*
        	choose a vertex x
        	make a list "ejesAdj" of vertices reachable from x,
        	and another list "explorando" of vertices to be explored.
        	initially, "ejesAdj" = "explorando" = x.

        	while "explorando" is nonempty
        	find and remove some vertex y in "explorando"
        	for each edge (y,z)
        	if (z is not in "ejesAdj")
        	add z to both "ejesAdj" and "explorando"

        	if "ejesAdj" has fewer than n items
        	return disconnected
        	else return connected
        	*/
        	if(unEjePrimerNodo != null){
        		ejesAdy.add(unEjePrimerNodo);	
        	}
        	return ejesAdy.size() >= cantNodos;
        }
        
        public static boolean mismoEje(Eje eje1, Eje eje2){
        	return eje1.nodo1.equals(eje2.nodo1) && eje1.nodo2.equals(eje2.nodo2) ||
        		   eje1.nodo1.equals(eje2.nodo2) && eje1.nodo2.equals(eje2.nodo1);
        }
        
        public static Eje revert(Eje eje){
        	return new Eje(eje.nodo2, eje.nodo1);
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
    	
    	public String toString(){
    		return "["+nodo1+","+nodo2+"]";
    	}

    	@Override
		public boolean equals(Object obj) {
      	  Eje eje = (Eje) obj;
      	  return nodo1.equals(eje.nodo1) && nodo2.equals(eje.nodo2) ||
				 nodo1.equals(eje.nodo2) && nodo2.equals(eje.nodo1);
		}
    	
    	@Override
    	public int hashCode() {
   			return nodo1 + nodo2; 
    	}
      	
    }
}
