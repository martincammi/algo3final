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
				//arbol.agregarEje(nodo, adyacente);
				arbol.agregarEje(adyacente, nodo);

				recorrerYMarcarConDFS(grafo, arbol, adyacente);
			}
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
		
		ArrayList hojas = arbol.getHojas();
		for (int i = 0; i < hojas.size(); i++) {
			Integer unaHoja = (Integer)hojas.get(i);
			recorrerYCalularHijos(arbol, unaHoja);
		}
		arbol.sumarHijos(arbol.getRaiz(), 1); // A la raiz le faltará uno
	}
	
	public void recorrerYCalularHijos(Arbol arbol, int nodo){
		
		boolean recienMeSumo = false;
		if(arbol.cantHijos(nodo) == 1){ // Si es padre visitado por 1ra vez (ya un hijo se agregó)
			arbol.sumarHijos(nodo,1);
			recienMeSumo = true;
		}
		
		if(arbol.cantHijos(nodo) == 0){// Si es hoja
			arbol.sumarHijos(nodo,1);
		}

		int padre = arbol.proximoAdyacente(nodo);
		boolean esRaiz = padre == arbol.NO_HAY_MAS_ADYACENTES;
		
		if(!esRaiz){
			
			if(recienMeSumo){
				arbol.sumarHijos(padre,arbol.cantHijos(nodo)); //Sumo lo que tengo al padre.
			}else{
				arbol.sumarHijos(padre,1);	
			}
			
			recorrerYCalularHijos(arbol, padre);
			padre = arbol.proximoAdyacente(nodo);
		}else{
			
		}
		arbol.sumarHijos(padre,1);
	}
	
}
