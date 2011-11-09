package algorithms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.FileManager;
import utils.Grafo;
import utils.ListaInt;

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
			//DEVOLVER
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
		
		int dinExedente[] = new int[grafo.cantNodos];
		int doutExedente[] = new int[grafo.cantNodos];
		
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

	
}
