package problema2;

import java.util.ArrayList;
import java.util.List;

public class Ej2 {

	public void encontrarAristasPuente(Grafo grafo){
		
		Arbol arbol;
		//PASO1: Buscar Arbol Generador
		arbol = construirArbolGenerador(grafo);
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
		
		while(adyacente != grafo.NO_HAY_MAS_ADYACENTES){
			if(!grafo.estaMarcado(adyacente)){
				grafo.marcarNodo(adyacente);
				grafo.marcarEje(nodo, adyacente);	
				arbol.agregarEje(nodo, adyacente);

				recorrerYMarcarConDFS(grafo, arbol, adyacente);
			}
			adyacente = grafo.proximoAdyacente(nodo);
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
		
		while(adyacente != arbol.NO_HAY_MAS_ADYACENTES){
			if(!arbol.estaMarcado(adyacente)){
				arbol.etiquetarNodo(adyacente, Etiquetador.getEtiqueta());
				arbol.marcarNodo(adyacente);

				recorrerYEtiquetar(arbol, adyacente);
			}
			adyacente = arbol.proximoAdyacente(nodo);
		}
	}
	
	public void calcularHijos(Arbol arbol){
		
		int nodoInicial = arbol.cantNodos-1; 		
		recorrerYCalularHijos(arbol, nodoInicial);
	}
	
	public void recorrerYCalularHijos(Arbol arbol, int nodo){
		
		int adyacente = arbol.proximoAdyacente(nodo);
		
		while(adyacente != arbol.NO_HAY_MAS_ADYACENTES){
			if(arbol.etiqueta(adyacente) < arbol.etiqueta(nodo)){ //Es el nodo padre
				//arbol.hijos[adyacente]++ 
				//FALTA: contar la cantidad de hijos
				//SIN TERMINAR!
				recorrerYCalularHijos(arbol, adyacente);
			}
			adyacente = arbol.proximoAdyacente(nodo);
		}
		
		arbol.addHijo(1);
	}
}
