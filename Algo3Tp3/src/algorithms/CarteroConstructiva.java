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
		CarteroConstructiva cartero = new CarteroConstructiva(grafo);
		
		int i = 1;
		while (grafo != null)
		{
			//MIENTRAS PARAMETRO DE ITERACIONES CONSTRUCTIVA HACER
				System.out.println("Instancia " + i + ": ");
				//1) HACER UNA COPIA DEL GRAFO
				Grafo grafoCopia = ((Grafo)grafo.clone());
				//2) ORIENTAR LAS ARISTAS
				grafoCopia.orientarTodasAristas();
				//3) CALCULAR UN MATCHING DE DIN DOUT
				List<Eje> unMatching = cartero.encontrarMatchingNodos(grafoCopia);
				
				//3.1) Calcular de todos los vecinos el de menor matching
				//List<Eje> matchingMinimo = cartero.encontrarMatchingDeMenorPeso(unMatching,grafo.getPesoCaminoMinimo());
				
				//4) CALCULAR EULERIANO 
				// TODO Sebas Adaptar al nuevo matchingMinimo
//				cartero.agregarCaminosMatcheados(unMatching,grafoCopia);//en vez de grafo hay que pasarle la copia que ya tiene las aristas orientadas//hay que pasarle los pares de la mejor solucion que encontremos
//				ListaInt circuitoEuleriano = CircuitoEuleriano.encontrarCircuitoEuleriano(grafoCopia);
//				5) DEVOLVER | reemplazar por la llamada a archivo|
//				System.out.print("Circuito: ");
//				System.out.println(circuitoEuleriano);
//				System.out.println("----");
				grafo = fm.leerInstancia();
				i++;
		}
	}	
	//La búsqueda local queda para el de búsqueda local
	//BUSQUEDA LOCAL SOBRE MATCHING EN LAS VECINDADES//DEFINIR QUIENES SON VECINOS
	
	public List<Eje> encontrarMatchingDeMenorPeso(List<Eje> matching, int[][] pesosMin){
		
		List<Eje> listaResultado = new ArrayList<Eje>();
		
		if(matching.size() <= 1){
			return matching;
		}
		
		Eje eje;
		Eje eje2;
		int swapIndex1 = 0;
		int swapIndex2 = 0;
		int minimoInicial = 0;
		int posibleMinimo;
		boolean primeraVez = true;
		
		for (int i = 0; i < matching.size(); i++) {
			
			eje = matching.get(i);
			
			for (int j = i+1; j < matching.size(); j++) {

				eje2 = matching.get(j);

				if(primeraVez){
					minimoInicial = eje.getPeso() + eje2.getPeso();
					primeraVez = false;
				}
				
				posibleMinimo = pesosMin[eje.getNodo1()][eje2.getNodo2()] + pesosMin[eje2.getNodo1()][eje.getNodo2()];
				
				if(posibleMinimo < minimoInicial){
					minimoInicial = posibleMinimo;
					swapIndex1 = i;
					swapIndex2 = j;
				}
			}
		}
		
		eje = new Eje(matching.get(swapIndex1)); 
		eje2 = new Eje(matching.get(swapIndex2));
		
		int ejeNodo2 = eje.getNodo2();
		
		eje.setNodo2(eje2.getNodo2());
		eje.setPeso( pesosMin[eje.getNodo1()] [eje.getNodo2()] );
	
		eje2.setNodo2(ejeNodo2);
		eje2.setPeso( pesosMin[eje2.getNodo1()] [eje2.getNodo2()] );
		
		listaResultado.add(eje);
		listaResultado.add(eje2);
		
		for (int i = 0; i < matching.size(); i++) {
			if(i != swapIndex1 && i != swapIndex2){
				listaResultado.add(matching.get(i));
			}
		}
		
		System.out.print("Vecino de Matching minimo: ");
		for (Eje ejeShow : listaResultado) {
			System.out.print("(" + ejeShow.getNodo1() + "," + ejeShow.getNodo2() + "," + ejeShow.getPeso() + "),");
		}
		System.out.println();
		
		return listaResultado;
	}
	
	public CarteroConstructiva(Grafo grafo){
		this.grafo = grafo;
	}
	
	/*Precondición: Grafo con todos los nodos orientados*/
	public List<Eje> encontrarMatchingNodos(Grafo grafo){
		
		if(!grafo.todasDirigidas()){
			System.out.println("ERROR: El grafo no es dirigido");
			return null;
		}
		
		List<Eje> triplaInOutPeso;
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
		
		triplaInOutPeso = new ArrayList<Eje>(); 
		
		while( nodosDin.size() > 0 && nodosDout.size() > 0){
			
			//las listas nodoDin y nodoDout son mutuamente excluyentes.
			Integer nodoIn = nodosDin.get(0);
			Integer nodoOut = nodosDout.get(0);
			int valorCaminoMinimo = grafo.pesoCaminoMinimo(nodoIn, nodoOut);
			Eje eje = new Eje(nodoIn, nodoOut, valorCaminoMinimo);
			triplaInOutPeso.add(eje);

			dinExedente[nodoIn]--;
			doutExedente[nodoOut]--;
			
			if(dinExedente[nodoIn] == 0){
				nodosDin.remove(nodoIn);
			}
			
			if(doutExedente[nodoOut] == 0){
				nodosDout.remove(nodoOut);
			}
			
		}
		System.out.print("Matching: ");
		for (Eje eje : triplaInOutPeso) {
			System.out.print("(" + eje.getNodo1() + "," + eje.getNodo2() + "," + eje.getPeso() + "),");
		}
		System.out.println();
		
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
		if(pares!=null){
			for(int[] i:pares){
				int nodo1=i[0], nodo2=i[1];
				if(sol[nodo1][nodo2] == null){
					sol[nodo1][nodo2] = Dijkstra.getPath(grafo, Dijkstra.dijkstra(grafo, nodo1), nodo1, nodo2);
					System.out.println("Dijkstra: "+sol[nodo1][nodo2]);
				}
				ListIterator<Integer> li = sol[nodo1][nodo2].listIterator(1);
				System.out.println("Arcos Agregados:");
				while(li.hasNext()){
					nodo2 = li.next();
					System.out.println("("+nodo1+","+nodo2+")");
					gr.agregarAdyacencia(nodo1, nodo2, grafo.getPesoAristas()[nodo1][nodo2],true);
					nodo1=nodo2;
				}
			}
		}
	}
}