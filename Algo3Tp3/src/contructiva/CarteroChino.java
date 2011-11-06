package contructiva;

import java.util.ArrayList;
import java.util.List;

public class CarteroChino {

	private Grafo grafo;
	
	public static void main(String[] args) {
		String[] params = {"10","11","2",
							"0","9","1",
							"0","6","1",
							"6","9","1",
							"9","8","1",
							"6","8","1",
							"7","1","1",
							"8","1","1",
							"6","5","1",
							"1","2","1",
							"2","3","1",
							"3","4","1",
							"6","7","1",
							"5","4","1"
							};
		Grafo grafo = new Grafo(params);
		
		CarteroChino cartero = new CarteroChino(grafo);
		cartero.encontrarCamino(); 
	}
	
	public CarteroChino(Grafo grafo){
		this.grafo = grafo;
	}
	
	public void encontrarCamino() {
		
		int nodoInicial = grafo.getNodoInicial(3);
		int nodo1 = nodoInicial;
		List<Integer> visitados = new ArrayList<Integer>();
		
		while ( grafo.adyacentesNoOr(nodoInicial).size() + grafo.adyacentesOr(nodoInicial).size() > 0 ){
			
			if( grafo.adyacentesOr(nodo1).size() > 0){
				int nodo2 = grafo.adyacentesOr(nodo1).get(0);
				grafo.adyacentesVisitados(nodo1).add(nodo2);
				grafo.adyacentesOr(nodo1).remove(0);
				nodo1 = nodo2;
				
			}else if(grafo.adyacentesNoOr(nodo1).size() > 0){
				int nodo2 = grafo.adyacentesNoOr(nodo1).get(0);
				grafo.adyacentesVisitados(nodo1).add(nodo2);
				grafo.adyacentesNoOr(nodo1).remove(0);
				nodo1 = nodo2;
				
			}else{
				int nodo2 = grafo.adyacentesVisitados(nodo1).get(0);
				grafo.adyacentesVisitados(nodo1).add(nodo2);
				grafo.adyacentesVisitados(nodo1).remove(0);
				nodo1 = nodo2;
				
			}
			
			visitados.add(nodo1);
		}
		

		for (Integer i : visitados) {
			System.out.println("Nodo " + i);
		}
		
	}
	
}
