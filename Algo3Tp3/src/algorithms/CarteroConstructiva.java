package algorithms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.ListIterator;
import utils.*;

public class CarteroConstructiva {

	private Grafo grafo;
	private int[] primos = {1,3,5,7,11,13,17,19,23};
	
	public static void main(String[] args) throws IOException, CloneNotSupportedException {

		FileManager fm = new FileManager("Ej1.in");
		fm.abrirArchivo();
		Grafo grafo = fm.leerInstancia();
		CarteroConstructiva cartero = new CarteroConstructiva(grafo);
		int nodoIncial = 3;
		
		//1 REALIZA UN BALANCEO ENTRE LOS GRADOS DE ENTRADA Y GRADOS DE SALIDA, 
		//  CALCULANDO PRIMERO AL NODO DONDE SE CUMPLA (CANTIDAD ARISTAS NODO = DIN NODO - DOUT NODO) 
		//2 ORIENTA PRIMERO CON LA MISMA CUENTA QUE EL PRIMERO, PERO EMPEZANDO DESDE UN CIERTO NODO QUE VIENE POR PARAMETRO
		//3 ORIENTA PRIMERO AL QUE TENGA MAYOR GRADO (CANTIDAD DE ARISTAS)
		//4 ORIENTA PRIMERO AL QUE TENGA MAYOR GRADO DE ENTRADA (DIN)
		//5 ORIENTA PRIMERO AL QUE TENGA MAYOR SALIDA DE ENTRADA (DOUT)
		int tipoOrientacionAristas = 2;
		
		int parametroBusqueda = 2;  //CALCULA LA LISTA CON ESTE PARAMETRO. 
		int parametroEleccionLista = 1;  //ESTE ME DICE CUAL DE LA LISTA ELEGIR, VA DESDE 0 A CANTIDAD DE LA LISTA QUE ME DIO ARRIBA. SI ESTE VALOR ES MAYOR, ME DEVUELVE LA PRIMERA POSICION DE LA LISTA
		String decisionDefault = "E";  // S Mandar Default Salida, E Mandar Default Entrada
		
		int i = 1;
		while (grafo != null)
		{
			if(FuertementeConexo.fuertementeConexo(grafo)){
			//MIENTRAS PARAMETRO DE ITERACIONES CONSTRUCTIVA HACER
				System.out.println("Instancia " + i + ": ");
				//1) HACER UNA COPIA DEL GRAFO
				Grafo grafoCopia = ((Grafo)grafo.clone());
				//2) ORIENTAR LAS ARISTAS
				grafoCopia.orientarTodasAristas(tipoOrientacionAristas, parametroBusqueda, parametroEleccionLista, decisionDefault);
				
				//3) CALCULAR UN MATCHING DE DIN DOUT
				int aleatoriedad = 10; 
				List<Eje> unMatching = cartero.encontrarMatchingNodos(grafoCopia, aleatoriedad);
				
				//3.1) Calcular de todos los vecinos el de menor matching
				List<Eje> matchingMinimo = cartero.encontrarMatchingDeMenorPeso(unMatching,grafo.getPesoCaminoMinimo());
				
				//4) CALCULAR EULERIANO 
				// TODO Sebas Adaptar al nuevo matchingMinimo
//				cartero.agregarCaminosMatcheados(unMatching,grafoCopia);//en vez de grafo hay que pasarle la copia que ya tiene las aristas orientadas//hay que pasarle los pares de la mejor solucion que encontremos
//				ListaInt circuitoEuleriano = CircuitoEuleriano.encontrarCircuitoEuleriano(grafoCopia);
//				5) DEVOLVER | reemplazar por la llamada a archivo|
//				System.out.print("Circuito: ");
//				System.out.println(circuitoEuleriano);
//				System.out.println("----");
			}else{
				System.out.println("No existe solución porque el grafo no es fuertemente conexo");
			}
			grafo = fm.leerInstancia();
			i++;
		}
	}	
	//La búsqueda local queda para el de búsqueda local
	//BUSQUEDA LOCAL SOBRE MATCHING EN LAS VECINDADES Los vecinos
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
	public List<Eje> encontrarMatchingNodos(Grafo grafo, int aleatoriedad){
		
		int primo = getPrimeIndex(aleatoriedad);
		System.out.println("primo: " + primo);
		
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
				dinExedente[i] = grafo.getDin(i) - grafo.getDout(i);
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
		
		if(nodosDout.size() == 0){
			return triplaInOutPeso;
		}
			
		int i = 0;
		
		int corte = 0;
		while(primo % nodosDin.size() == 0 && corte <= primos.length){
			primo = getPrimeIndex( (aleatoriedad+1) % primos.length);
			corte++;
		}
		
		if(corte > primos.length){
			//Comprarle medio kilo de helado a Sebas.
		}
		
		int indexIn = primo % (nodosDin.size());
		int indexOut = nodosDout.size() - indexIn - 1;
		
		System.out.println(nodosDin);
		System.out.println(nodosDout);
		
		while( nodosDin.size() > 0 && nodosDout.size() > 0){
			
			//las listas nodoDin y nodoDout son mutuamente excluyentes.
			//las listas nodoDin y nodoDout deberian tener la misma longitud.
			
			Integer nodoIn = nodosDin.get(indexIn);
			Integer nodoOut = nodosDout.get(indexOut);
			
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
			
			if(nodosDin.size() > 0){
				indexIn = (indexIn + primo) % nodosDin.size();
				indexOut = (indexOut + primo) % nodosDout.size();
			}

			i++;
		}
		System.out.print("Matching: ");
		for (Eje eje : triplaInOutPeso) {
			System.out.print("(" + eje.getNodo1() + "," + eje.getNodo2() + "," + eje.getPeso() + "),");
		}
		System.out.println();
		
		return triplaInOutPeso;
		
	}
	
	private int getPrimeIndex(int aleatoriedad) {
		int modulo = aleatoriedad % primos.length;
		return primos[modulo];
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

	private void agregarCaminosMatcheados(List<Eje> pares,Grafo gr) {
		ListaInt[][] sol = new ListaInt[grafo.getCantNodos()][grafo.getCantNodos()];
		if(pares!=null){
			for(Eje i:pares){
				int nodo1=i.getNodo1(), nodo2=i.getNodo2();
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