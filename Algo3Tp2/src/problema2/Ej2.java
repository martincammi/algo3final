package problema2;

import java.util.ArrayList;
import java.util.List;

public class Ej2 {

	public void encontrarAristasPuente(Grafo grafo){
		
		Arbol arbol;
		//PASO1: Buscar Arbol Generador
		arbol = construirArbolGenerador(grafo);
		arbol.showHojas();
		//PASO2: Elegir Raiz
		int raiz = arbol.getRaiz();
		//PASO3: Preorder
		etiquetarEnPreorder(arbol);
		arbol.getPreorder();
		//PASO4: Calula los hijos de cada nodo
		
		//calcularHijos(arbol);
		
		
	}
	
	/**
	 * Construye el arbol generador con DFS
	 */
	public Arbol construirArbolGenerador(Grafo grafo){
		Arbol arbol = new Arbol(grafo.getCantNodos());
		int inicial = grafo.getNodo();
		arbol.setRaiz(inicial);
		
		grafo.marcarNodo(inicial);
		recorrerYMarcarConDFS(grafo, arbol, inicial);
		
		return arbol;
	}
	
	public void recorrerYMarcarConDFS(Grafo grafo, Arbol arbol, int nodo){
		
		int adyacente = grafo.proximoAdyacente(nodo);
		boolean esHoja = true;
		
		while(adyacente != grafo.NO_HAY_MAS_ADYACENTES){
			if(!grafo.estaMarcado(adyacente)){
				esHoja = false;
				grafo.marcarNodo(adyacente);
				grafo.marcarEje(nodo, adyacente);	
				arbol.agregarEje(nodo, adyacente);
				//arbol.agregarEje(adyacente, nodo);
				//arbol.marcarEje(nodo, adyacente);

				recorrerYMarcarConDFS(grafo, arbol, adyacente);
			}//else{
				//grafo.ejesFueraDelArbol.put(nodo, adyacente);
			//}
			adyacente = grafo.proximoAdyacente(nodo);
		}
		if(esHoja){
			arbol.agregarHoja(nodo);
		}
	}
	
	public void etiquetarEnPreorder(Arbol arbol){
		
		int raiz = arbol.getRaiz();
		arbol.etiquetarNodo(raiz, Etiquetador.getEtiqueta());
		arbol.marcarNodo(raiz);
		recorrerYEtiquetar(arbol, raiz);
	}
	
	public void recorrerYEtiquetar(Arbol arbol, int nodo){
		
		int adyacente = arbol.proximoAdyacente(nodo);
		ArrayList lowerValues = new ArrayList();
		
		while(adyacente != arbol.NO_HAY_MAS_ADYACENTES){
			if(!arbol.estaMarcado(adyacente)){ //v -> w
				arbol.etiquetarNodo(adyacente, Etiquetador.getEtiqueta());
				arbol.marcarNodo(adyacente);
				
				recorrerYEtiquetar(arbol, adyacente); //Y seguimos etiquetando!
				lowerValues.add(arbol.lower[adyacente]); //L(w) | v -> w
			}
			
			
			
			arbol.sumarHijos(nodo, arbol.cantHijos(adyacente)); //Sumo los que acumulo mi hijo
			adyacente = arbol.proximoAdyacente(nodo);
		}
		
		// ejes w | v -- w
		//for (int i = 0; i < arbol.ejesFueraDelArbol.size(); i++) {
		//	lowerValues.add(arbol.etiqueta(adyacente)); // w | v -- w
		//}
		
		lowerValues.add(nodo);
		//arbol.lower[nodo] = minimoSinCero(lowerValues);
		arbol.sumarHijos(nodo, 1); //Sumo el correspondiente al nodo
	}
	
	
	public int minimoSinCero(ArrayList<Integer> values){
		
		if(values.size() == 1){
			return values.get(0);
		}
		
		int minimo = values.get(0);
		for (Integer value: values) {
			if (value < minimo){
				minimo = value;
			}
		}
		return minimo;
	}
	
	/**
	 * Calcula el minimo entre a, b, c sin tener en cuenta al cero como posible minimo 
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public int minimoSinCero(int a, int b, int c){
		
		if(b == 0 && c == 0){
			return a;
		}
		
		if(b == 0){
			return minimo(a,c);
		}
		
		if(c == 0){
			return minimo(a,b);
		}
		
		
		int min1 = minimo(a,b);
		int min2 = minimo(b,c);
		return minimo(min1, min2);
	}
	
	public int minimo(int a, int b){
		if(a < b){
			return a;
		}else{
			return b;
		}
	}
	
	public void calcularLowOrder(Arbol arbol, Grafo grafo){
		
		int raiz = arbol.getRaiz();
		recorrerCalcularLowOrder(arbol, grafo, raiz);
	}
	
	public void recorrerCalcularLowOrder(Arbol arbol, Grafo grafo, int nodo){
		
		ArrayList<Integer> adyacentes = grafo.adyacentes(nodo);
		ArrayList lowerValues = new ArrayList();
		
		//while(adyacente != arbol.NO_HAY_MAS_ADYACENTES){
		for (Integer adyacente : adyacentes) {
			if(grafo.estaMarcadoEjeArbol(nodo, adyacente)){ //Es arista del árbol
				lowerValues.add(arbol.lower[adyacente]); //L(w) | v -> w
			}else if (grafo.estaMarcadoEjeGrafo(nodo, adyacente)){
				lowerValues.add(arbol.etiqueta(adyacente)); //w | v -> w
			}
			recorrerCalcularLowOrder(arbol, grafo, adyacente); //Y seguimos recorriendo!			
			adyacente = arbol.proximoAdyacente(nodo);
		}
		arbol.lower[nodo] = minimoSinCero(lowerValues);
	}
}
