package problema4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * Para las complejidades mencionadas se notarà:
 * n como la cantidad de nodos
 * m como la cantidad de ejes
 * @author martin.cammi
 *
 */
public class Main {
	
	public static void main(String[] args) {

		//runTests();
		//runTests(0);
		//runForOnlineJudge();
	}
	//Soporta hasta dos nodos de grado impar, no más.
	public Integer carteroChino(GrafoNodos grafo){
		//No existe grafo con un nodo de grado impar, entonces tiene 0 o 2 nodos.
		List<Integer> nodosGradoImpar = grafo.getNodosGradoImpar();
		List<Eje> recorrido = null;
		
		//Si entra acá es que tiene dos nodos de grado impar
		if(nodosGradoImpar.size() == 2){
			Integer nodo1 = nodosGradoImpar.get(0);
			Integer nodo2 = nodosGradoImpar.get(1);
			
			if(grafo.hayEje(nodo1, nodo2)){
				grafo.agregarEje(new Eje(nodo1,nodo2,grafo.getPeso(nodo1,nodo2)));
			}else{
				recorrido = dijkstra(grafo, nodo1, nodo2);
			}
		}
		
		if(recorrido != null){
			for (Eje eje : recorrido) {
				grafo.agregarEje(eje);
			}
		}
		
		recorrido = fleury(grafo);
		
		Integer costoCaminoMinimo = 0;
		for (Eje eje : recorrido) {
			costoCaminoMinimo += eje.getPeso();
		}
		return costoCaminoMinimo;
	}
	
	/**
	 * Encuentra un circuito euleriano en un grafo euleriano.
	 * @complejidadTemporal 
	 * @precondicion: Todos los nodos tienen que tener grafo par.
	 *  
	 */
	 public List<Eje> fleury(GrafoEjes grafo){

		boolean algunoImpar = false;
			for (Integer nodo : grafo.getNodos()) {
				if(grafo.gradoNodo(nodo) %2 !=0){
					algunoImpar = true;
					break;
				}
			}
		
	 	if(algunoImpar){
	 		return null;
	 	}
		 
    	int aristasRecorridas = 0;
    	List<Eje> recorrido = new ArrayList<Eje>();
    	int aristasTotales = grafo.cantEjes;
    	Integer nodoActual = grafo.getNodo();
    	Integer nodoInicial = new Integer(nodoActual);
    	while(!nodoActual.equals(nodoInicial) || aristasRecorridas != aristasTotales){
    		
    		for (Eje eje : grafo.ejes(nodoActual)) { //O(n-1)
				Integer nodoSiguiente = eje.nodo2;
				grafo.ocultarEje(eje); //O(n-1)
				if (grafo.gradoNodo(nodoActual) == 0) {
					grafo.ocultarNodo(nodoActual); //O(n-1)
				}
				if(grafo.esConexo()){ //O((n-1)^3)
					recorrido.add(eje);
					aristasRecorridas++;
					nodoActual = nodoSiguiente;
					break;
				}else{
				  if (grafo.estaOcultoNodo(nodoActual)) {
                         grafo.mostrarNodo(nodoActual);
                  }
                  grafo.mostrarEje(eje); //O(n-1)
				}
			}
    	}
    	return recorrido;
	  }
	
	 /**
	  * Dados dos nodos encuentra el minimo camino para llegar de uno a otro.
	  * @complejidadTemporal O(n^2)
	  * @param grafo
	  * @return
	  */
	 public List<Eje> dijkstra(GrafoNodos grafo, Integer nodoInicial, Integer nodoFinal){
		 
		 Map<Integer, Integer> antecesores = new HashMap<Integer, Integer>();
		 grafo.setDistance(nodoInicial, 0);
		 List<Integer> sinVisitar = new ArrayList<Integer>();
		 
		 sinVisitar.add(nodoInicial);
		 
		 while (!sinVisitar.isEmpty()) {
			 
			 Integer nodo = getNodoMenorDistancia(sinVisitar,grafo); //O(n)
			 
			 List<Integer> vecinos = grafo.getVecinos(nodo);
			 for (Integer vecino: vecinos) { //O(n)
				if(!grafo.isVisited(vecino)){
					actulizarDistancia(nodo,vecino,grafo, antecesores); //O(1)
					sinVisitar.add(vecino);	
				}
			 }
			 grafo.visitarNodo(nodo);
			 sinVisitar.remove(nodo);
		 }
		 
		 List<Eje> recorrido = new ArrayList<Eje>();
		 Integer nodoActual = nodoFinal;
		 Integer nodoAnterior = antecesores.get(nodoFinal);
		 while(nodoAnterior != null){ //O(n-1)
			 recorrido.add(new Eje(nodoAnterior, nodoActual,grafo.getPeso(nodoAnterior, nodoActual)));
			 nodoActual = nodoAnterior;
			 nodoAnterior = antecesores.get(nodoActual);
		 }
		 
		 Collections.reverse(recorrido);
		 return recorrido;
	 }
	 
	 /**
	  * Obtiene el nodo de menor distancia de la lista.
	  * @complejidadTemporal O(n)
	  * @param nodos
	  * @param grafo
	  * @return
	  */
	 public Integer getNodoMenorDistancia(List<Integer> nodos, GrafoNodos grafo){
		 Integer nodoMenorDistancia = null;
		 for (Integer nodo: nodos) {
			if(nodoMenorDistancia == null || grafo.getDistance(nodo) < grafo.getDistance(nodoMenorDistancia) ){
				nodoMenorDistancia = nodo;
			}
		}
		 return nodoMenorDistancia;
	 }
	 
	 /**
	  * Actualiza la menor distancia de nodoDesde hasta nodoHasta.
	  * @complejidadTemporal O(1)
	  * @param nodoDesde
	  * @param nodoHasta
	  * @param grafo
	  */
	 public void actulizarDistancia(Integer nodoDesde, Integer nodoHasta, GrafoNodos grafo, Map<Integer,Integer> antecesores){
		 int distNodoDesde = grafo.getDistance(nodoDesde);
		 int distNodoHasta = grafo.getDistance(nodoHasta);
		 Eje eje = new Eje(nodoDesde,nodoHasta);
		 int pesoEje = grafo.getPeso(eje);
		 
		 if(distNodoDesde + pesoEje < distNodoHasta || grafo.INFINITO.equals(distNodoHasta)){
			 grafo.setDistance(nodoHasta, distNodoDesde + pesoEje );
			 antecesores.put(nodoHasta, nodoDesde);
		 }
	 }
	 
    public static class GrafoEjes {
        private Map<Integer, List<Eje>> mapaAdyacencia = new HashMap();
        private Map<Integer, Boolean> nodosVisibles = new HashMap();
        public int cantEjes = 0;
        public int cantNodos = 0;

        public int getCantNodos(){
        	return cantNodos; 
        }
        
        public int getPeso(Eje eje){
        	return getPeso(eje.nodo1,eje.nodo2);
        }
        
        public int getPeso(Integer nodo1, Integer nodo2){
        	List<Eje> ejes = mapaAdyacencia.get(nodo1);
        	for (Eje eje : ejes) {
				if(eje.nodo2.equals(nodo2)){
					return eje.getPeso();
				}
			} 
        	
        	return 0;
        }
        
        public List<Integer> getVecinos (Integer nodo){
        	List<Integer> nodosVecinos =  new ArrayList<Integer>();
        	List<Eje> ejes =  mapaAdyacencia.get(nodo);
        	for (Eje eje : ejes) {
        		if(!estaOcultoNodo(eje.nodo2)){
        			nodosVecinos.add(eje.nodo2);
        		}
			}
        	
        	return nodosVecinos;
        }
        
        /**
         * Obtiene todos los nodos de grado impar. 
         * @complejidadTemporal O(n)
         * @return lista de nodos
         */
        public List<Integer> getNodosGradoImpar(){
        	List<Integer> nodosGradoImpar = new ArrayList<Integer>();
        	
        	for (Integer nodo : mapaAdyacencia.keySet()) {
				int grado = mapaAdyacencia.get(nodo).size();
				if(grado%2==1){ //Es Impar
					nodosGradoImpar.add(nodo);
				}
			}
        	
        	return nodosGradoImpar;
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
        
        public void agregarEje(Eje eje) {
        	agregarEje(eje.nodo1, eje.nodo2,eje.getPeso());
        }
        
        /**
         * Agrega un eje con nodos nodo1, nodo2 y peso peso.
         * @complejidadTemporal O(1)
         * @param nodo1
         * @param nodo2
         * @param peso
         */
        public void agregarEje(Integer nodo1, Integer nodo2, Integer peso) {

        	agregarAAdjacentes(nodo1,nodo2, peso);
        	
        	if(!nodo1.equals(nodo2)){
        		agregarAAdjacentes(nodo2,nodo1, peso);
        	}
        	cantEjes++;
        }
        
        /**
         * Agrega un eje con nodos nodo1, nodo2.
         * @complejidadTemporal O(1)
         * @param nodo1
         * @param nodo2
         */
        public void agregarEje(Integer nodo1, Integer nodo2) {
        	Integer peso = 0;
        	agregarAAdjacentes(nodo1,nodo2, peso);
        	
        	if(!nodo1.equals(nodo2)){
        		agregarAAdjacentes(nodo2,nodo1, peso);
        	}
        	cantEjes++;
        }
        
        /**
         * Agrega un eje con nodos nodo1, nodo2 y peso peso.
         * @complejidadTemporal O(1)
         * @param nodo1
         * @param nodo2
         * @param peso
         */
        private void agregarAAdjacentes(Integer nodo1, Integer nodo2, Integer peso){
        	List<Eje> adyacentes = mapaAdyacencia.get(nodo1);
        	//mostrarNodo(nodo1);
        	nodosVisibles.put(nodo1, true);
            
            if(adyacentes == null) {
            	adyacentes = new ArrayList<Eje>();
            	cantNodos++; 
            }

            //No se chequea que el eje exista -> podria ser un multigrafo.
            Eje unEje = new Eje(nodo1,nodo2, peso);
        	adyacentes.add(unEje);
        	mapaAdyacencia.put(nodo1, adyacentes);
        }
        
        /**
         * Indica si existe un eje entre nodo1 y nodo2
         * @complejidadTemporal O(m)
         * @param nodo1
         * @param nodo2
         * @return
         */
        public boolean hayEje(Integer nodo1, Integer nodo2){
        	List<Eje> adyacentes = mapaAdyacencia.get(nodo1);
        	
        	for (Eje eje : adyacentes) {
        		if(eje.nodo2.equals(nodo2)){
        			return true;
        		}
        	}
        	return false;
        }

        /**
         * Devuelve todos los ejes de un nodo (no devuelve los ocultos)
         * @complejidadTemporal O(n-1)
         * @param nodo
         * @return
         */
        public List<Eje> ejes(Integer nodo) {
        	List<Eje> adyacentes = mapaAdyacencia.get(nodo);
        	List<Eje> respuesta = new ArrayList<Eje>();  
        	
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
        		List<Eje> adyacentes = ejes(nodo);
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
        
        /**
         * Oculta un eje
         * @complejidadTemporal O(n-1)
         * @param eje
         */
        public void ocultarEje(Eje eje){
        	Integer nodo1 = eje.nodo1;
        	Integer nodo2 = eje.nodo2;
        	ocultarEje(nodo1,nodo2);
        }
        
        /**
         * Oculta un Eje
         * @complejidadTemporal O(n-1)
         * @param nodo1
         * @param nodo2
         */
        public void ocultarEje(Integer nodo1, Integer nodo2){
        	cambiarEstadoDeUnEje(nodo1,nodo2, false);
        	cambiarEstadoDeUnEje(nodo2,nodo1, false);
        	cantEjes--;
        }
        
        /**
         * Muestra un eje
         * @complejidadTemporal O(n-1)
         * @param eje
         */
        public void mostrarEje(Eje eje){
        	mostrarEje(eje.nodo1,eje.nodo2);
        }
        
        /**
         * Muestra un eje
         * @complejidadTemporal O(n-1)
         * @param nodo1
         * @param nodo2
         */
        public void mostrarEje(Integer nodo1, Integer nodo2){
        	cambiarEstadoDeUnEje(nodo1,nodo2, true);
        	cambiarEstadoDeUnEje(nodo2,nodo1, true);
        	cantEjes++;
        }
        
        /**
         * Oculta un eje para los adyacentes del nodo1
         * @complejidadTemporal O(n-1)
         * @param nodo1
         * @param nodo2
         */
        private void cambiarEstadoDeUnEje(Integer nodo1, Integer nodo2, boolean loQuieroMostrar){
        	List<Eje> adyacentes = mapaAdyacencia.get(nodo1);
        	
        	for (Eje eje : adyacentes) { //O(n-1)
    			if(eje.nodo2.equals(nodo2)){
    				
    				if(!eje.visible && loQuieroMostrar){
    					eje.mostrar();
    					break;
    				}
    				
    				if(eje.visible && !loQuieroMostrar){
    					eje.ocultar();
    					break;
    				}
    			}
			}        	
        }
        
        /**
         * Indica si el grafo es conexo.
         * @complejidadTemporal  O((n-1)^3)
         * @return
         */
        public boolean esConexo(){
        	
        	Integer nodo = getNodo(); //O(n)
        	List<Eje> ejesAdy = new ArrayList<Eje>();
        	List<Eje> adyacentes = ejes(nodo); //O(n-1)
        	
        	for (Eje eje : adyacentes) { //O(n-1)
				ejesAdy.add(eje);
			}
        	
        	List<Eje> explorando = new ArrayList<Eje>();
        	
        	boolean tieneUnNodo = false;
        	for (Eje eje : ejesAdy) { //O(n-1)
				explorando.add(eje);
				tieneUnNodo = true;
			}
        	//Caso borde para determinar si recorrimos todos los nodos (ya que si habia uno solo quedaba fuera)
        	Eje unEjePrimerNodo;
        	 
        	if(tieneUnNodo || gradoNodo(nodo) == 0){
        		if(tieneUnNodo){
        			Eje primerEje = explorando.iterator().next();
        			unEjePrimerNodo = ejeSimetrico(primerEje) ? null: new Eje(nodo,nodo);
        		}else{
        			unEjePrimerNodo = new Eje(nodo,nodo);
        		}
        		
        	}else{
        		unEjePrimerNodo = null;
        	}
        	
        	//Total: O((n-1)^3)
        	while(!explorando.isEmpty()){ //O(n-1)
        		
        		Eje unEje = explorando.iterator().next();
        		explorando.remove(unEje);

        		for (Eje unEje2 : ejes(unEje.nodo2)) { //O(n-1)
        			if(!ejesAdy.contains(unEje2)){ //O(n-1)
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
        
        public static boolean ejeSimetrico(Eje eje){
        	return eje.nodo1.equals(eje.nodo2);
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
    	Integer peso;
    	
    	public Eje(Integer nodo1, Integer nodo2, Integer peso){
    		this(nodo1,nodo2);
    		setPeso(peso);
    	}
    	
    	public Eje(Integer nodo1, Integer nodo2){
    		this.nodo1 = nodo1;
    		this.nodo2 = nodo2;
    		visible = true;
    	}
    	
    	public Integer getPeso(){
    		return peso;
    	}
    	
    	public void setPeso(Integer peso){
    		this.peso = peso;
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
    
	public static class GrafoNodos extends GrafoEjes{

		private Integer INFINITO = -1; 
		private Map<Integer, Boolean> nodosVisitados = new HashMap<Integer,Boolean>();
		private Map<Integer, Integer> nodosDistancia = new HashMap<Integer,Integer>();
		
		public boolean isVisited(Integer nodo) {
			if(nodosVisitados.get(nodo) == null){
				nodosVisitados.put(nodo,false);
			}
			return nodosVisitados.get(nodo);
		}

		public void visitarNodo(Integer nodo) {
			nodosVisitados.put(nodo, true);
		}

		public void setDistance(Integer nodo, Integer distancia) {
			nodosDistancia.put(nodo, distancia);
		}

		public Integer getDistance(Integer nodo) {
			if(nodosDistancia.get(nodo) == null){
				nodosDistancia.put(nodo,INFINITO);
			}
			return nodosDistancia.get(nodo);
		}
		
	}
}
