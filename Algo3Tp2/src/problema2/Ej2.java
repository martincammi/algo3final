package problema2;

import java.util.ArrayList;
import java.util.List;

public class Ej2 {

	public void encontrarAristasPuente(Grafo grafo){
		
		Arbol arbol;
		//PASO1: Elijo la Raiz
		//PASO2: Armo el ArbolGenerador y lo etiqueto en preorder.
		arbol = construirArbolGeneradorYEtiquetar(grafo);
		//PASO3: Calula los hijos de cada nodo
		//calcularHijos(arbol);
		
		
	}
	
	/**
	 * Construye el arbol generador con DFS
	 */
	public Arbol construirArbolGeneradorYEtiquetar(Grafo grafo){
		Arbol arbol = new Arbol(grafo.getCantNodos());
		int raiz = grafo.getNodo();
		grafo.setRaiz(raiz);
		
		grafo.marcarYEtiquetarNodo(raiz, Etiquetador.getEtiqueta());
		recorrerYMarcarConDFS(grafo, arbol, raiz);
		
		return arbol;
	}
	
	public void recorrerYMarcarConDFS(Grafo grafo, Arbol arbol, int nodo){
		
		int adyacente = grafo.proximoAdyacente(nodo);
		
		while(adyacente != grafo.NO_HAY_MAS_ADYACENTES){
			if(!grafo.estaMarcado(adyacente)){
				grafo.marcarYEtiquetarNodo(adyacente, Etiquetador.getEtiqueta());
				grafo.marcarEje(nodo, adyacente);	
				arbol.agregarEje(nodo, adyacente);

				recorrerYMarcarConDFS(grafo, arbol, adyacente);
			}
			adyacente = grafo.proximoAdyacente(nodo);
		}
	}
	
	public void calcularHijos(Arbol arbol){
		
		for (int i = 0; i < arbol.getHojas().size(); i++) {
			Integer hijo = (Integer)arbol.getHojas().get(i);
			recorrerYCalularHijos(arbol, hijo);
		}
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
	}
}
