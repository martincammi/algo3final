package algorithms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.ListIterator;
import utils.*;

public class CarteroConstructiva {

	private Grafo grafo;
	
	public static void main(String[] args) throws IOException, CloneNotSupportedException {

		FileManager fm = new FileManager("Ej1.in");
		fm.abrirArchivo();
		Grafo grafo = fm.leerInstancia();
		CarteroConstructiva cartero = new CarteroConstructiva();
		
		while (grafo != null)
		{
			//MIENTRAS PARAMETRO DE ITERACIONES CONSTRUCTIVA HACER
			
				//1) HACER UNA COPIA DEL GRAFO
				//2) ORIENTAR LAS ARISTAS
				Grafo grafoCopia = ((Grafo)grafo.clone());
				grafoCopia.orientarTodasAristas();

				//3) CALCULAR UN MATCHING DE DIN DOUT
				List<int[]> pares = cartero.encontrarMatchingNodos(grafo);

				for (int[] par : pares) {
					System.out.println("(" + par[0] + "," + par[1] + "," + par[2] + ")");
				}
				
				//4) BUSQUEDA LOCAL SOBRE MATCHING EN LAS VECINDADES//DEFINIR QUIENES SON VECINOS
			//5) CALCULAR EULERIANO
			cartero.agregarCaminosMatcheados(pares,grafoCopia);//en vez de grafo hay que pasarle la copia que ya tiene las aristas orientadas//hay que pasarle los pares de la mejor solucion que encontremos
//			grafo.showGrafo();
			ListaInt path = CircuitoEuleriano.encontrarCircuitoEuleriano(grafoCopia);
			//DEVOLVER
			System.out.println(path);
			grafo = fm.leerInstancia();
		}
	}	
	
	/*Precondición: Grafo con todos los nodos orientados*/
	public List<int[]> encontrarMatchingNodos(Grafo grafo){
		
		if(!grafo.todasDirigidas()){
			System.out.println("El grafo no es dirigido");
			return null;
		}
		
		List<int[]> triplaInOutPeso;
		List<Integer> nodosDin = new ArrayList();
		List<Integer> nodosDout = new ArrayList();
		ListaInt[] orientados = grafo.adyacenciasOr;
		
		int dinExedente[] = new int[grafo.getCantNodos()];
		int doutExedente[] = new int[grafo.getCantNodos()];
		
		for (int i = 0; i < orientados.length; i++) {
			
			if(grafo.getDin(i) > grafo.getDout(i)){
				nodosDin.add(i);
				dinExedente[i] = grafo.getDin(i) - grafo.getDout(i) ;
			}
			
			if(grafo.getDout(i) > grafo.getDin(i)){
				nodosDout.add(i);
				doutExedente[i] = grafo.getDout(i) - grafo.getDin(i);
			}
		}
		
		int cantResult = 0;
		for (Integer nodoIn : nodosDin) {
			cantResult += dinExedente[nodoIn];
		}
		
		triplaInOutPeso = new ArrayList<int[]>(); 
		
		while( nodosDin.size() > 0 && nodosDout.size() > 0){
			
			//las listas nodoDin y nodoDout son mutuamente excluyentes.
			Integer nodoIn = nodosDin.get(0);
			Integer nodoOut = nodosDout.get(0);
			int valorCaminoMinimo = grafo.pesoCaminoMinimo(nodoIn, nodoOut);
			int[] tripla = {nodoIn,nodoOut,valorCaminoMinimo};
			triplaInOutPeso.add(tripla);

			dinExedente[nodoIn]--;
			doutExedente[nodoOut]--;
			
			if(dinExedente[nodoIn] == 0){
				nodosDin.remove(nodoIn);
			}
			
			if(doutExedente[nodoOut] == 0){
				nodosDout.remove(nodoOut);
			}
			
		}
		
		return triplaInOutPeso;
		
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

	private void agregarCaminosMatcheados(List<int[]> pares,Grafo gr) {
		ListaInt[][] sol = new ListaInt[grafo.getCantNodos()][grafo.getCantNodos()];
		for(int[] i:pares){
			int nodo1=i[0], nodo2=i[1];
			if(sol[nodo1][nodo2] == null){
				sol[nodo1][nodo2] = Dijkstra.getPath(grafo, Dijkstra.dijkstra(grafo, nodo1), nodo1, nodo2);
			}
			ListIterator<Integer> li = sol[nodo1][nodo2].listIterator(1);
			while(li.hasNext()){
				nodo2 = li.next();
				gr.agregarAdyacencia(nodo1, nodo2, grafo.getPesoAristas()[nodo1][nodo2],true);
				nodo1=nodo2;
			}
		}
	}
}