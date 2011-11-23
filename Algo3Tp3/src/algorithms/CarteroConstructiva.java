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

//PARAMETROS
		String decisionDefault = "E";  // S Mandar Default Salida, E Mandar Default Entrada
		int tipoOrientacionAristas = 2;
		int parametroBusqueda = 5;  //CALCULA LA LISTA CON ESTE PARAMETRO. 
//int parametroEleccionLista = 1;  //ESTE ME DICE CUAL DE LA LISTA ELEGIR, VA DESDE 0 A CANTIDAD DE LA LISTA QUE ME DIO ARRIBA. SI ESTE VALOR ES MAYOR, ME DEVUELVE LA PRIMERA POSICION DE LA LISTA
		int[] parametroIteracionesGraspRandom= {33, 52, 48, 96, 125};//Random
		FileManager fm = new FileManager("Ej1.in");
		fm.abrirArchivo();
		Grafo grafo = fm.leerInstancia();
		
		//1 REALIZA UN BALANCEO ENTRE LOS GRADOS DE ENTRADA Y GRADOS DE SALIDA, 
		//  CALCULANDO PRIMERO AL NODO DONDE SE CUMPLA (CANTIDAD ARISTAS NODO = DIN NODO - DOUT NODO) 
		//2 ORIENTA PRIMERO CON LA MISMA CUENTA QUE EL PRIMERO, PERO EMPEZANDO DESDE UN CIERTO NODO QUE VIENE POR PARAMETRO
		//3 ORIENTA PRIMERO AL QUE TENGA MAYOR GRADO (CANTIDAD DE ARISTAS)
		//4 ORIENTA PRIMERO AL QUE TENGA MAYOR GRADO DE ENTRADA (DIN)
		//5 ORIENTA PRIMERO AL QUE TENGA MAYOR SALIDA DE ENTRADA (DOUT)
		
		int i = 1;
		while (grafo != null)
		{
			if(FuertementeConexo.fuertementeConexo(grafo)){
				int[][] pesoCaminoMinimo = grafo.calcularDantzig();
				int sumaPesosEjes = grafo.sumaPesosEjes();
				System.out.println("Instancia " + i + ": ");
				CarteroConstructiva cartero = new CarteroConstructiva(grafo);
				
				List<Eje> matchingSolucion = null;
				Grafo mejorGrafoSolucion = null;
				int sumaMejorSolucion = Grafo.INFINITO;
				int j= 1;
				for(int parametroGRASP: parametroIteracionesGraspRandom){
				//MIENTRAS PARAMETRO DE ITERACIONES CONSTRUCTIVA HACER
					System.out.println("Iteración GRASP " + j + ": ");
					//1) HACER UNA COPIA DEL GRAFO
					Grafo grafoCopia = ((Grafo)grafo.clone());
					//2) ORIENTAR LAS ARISTAS
					grafoCopia.orientarTodasAristas(tipoOrientacionAristas, parametroBusqueda, parametroGRASP, decisionDefault);

					//3) Calcular un Matching cualquiera de Din Dout
					int aleatoriedad = 10; 
					List<Eje> unMatching = cartero.encontrarMatchingNodos(grafoCopia, aleatoriedad,pesoCaminoMinimo);
					//int pesoMatching = pesoMatching(unMatching);
					//3.1) Calcular de todos los vecinos el de menor matchingSolucion
					List<Eje> matchingMinimo = cartero.encontrarMatchingDeMenorPeso(unMatching,pesoCaminoMinimo);
					int pesoBusquedaLocal = pesoMatching(matchingMinimo);
					//System.out.println("Se mejoró el peso del mathcing de "+pesoMatching+" a "+pesoBusquedaLocal);
					int sumaSolucion = sumaPesosEjes + pesoBusquedaLocal;
					if(sumaSolucion < sumaMejorSolucion){
						System.out.println("Mejoró la solución de "+sumaMejorSolucion+" a "+sumaSolucion);
						sumaMejorSolucion = sumaSolucion;
						mejorGrafoSolucion = grafoCopia;
						matchingSolucion = matchingMinimo;
						if(unMatching.isEmpty()){
							System.out.println("Solución óptima ");
							break;//solucion optima
						}
					}
					++j;
				}
				//4) CALCULAR EULERIANO 
				cartero.agregarCaminosMatcheados(matchingSolucion,mejorGrafoSolucion);//en vez de grafo hay que pasarle la copia que ya tiene las aristas orientadas//hay que pasarle los pares de la mejor solucion que encontremos
				ListaInt circuitoEuleriano = CircuitoEuleriano.encontrarCircuitoEuleriano(mejorGrafoSolucion);
//				5) DEVOLVER | reemplazar por la llamada a archivo|
				System.out.println("Circuito: ");
//				System.out.println(sumaMejorSolucion);
				System.out.println(circuitoEuleriano.size()-1);
//				System.out.println(circuitoEuleriano);
				ListIterator<Integer> li = circuitoEuleriano.listIterator();
				int nodo1,nodo2 = li.next();
				while(li.hasNext()){
					nodo1 = nodo2;
					nodo2 = li.next();
					System.out.println(nodo1+" "+nodo2+" "+grafo.getPesoAristas()[nodo1][nodo2]);
				}
				System.out.println("----");

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
				
				if(posibleMinimo <= minimoInicial){
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
		
//		System.out.print("Vecino de Matching minimo: ");
//		for (Eje ejeShow : listaResultado) {
//			System.out.print("(" + ejeShow.getNodo1() + "," + ejeShow.getNodo2() + "," + ejeShow.getPeso() + "),");
//		}
//		System.out.println();
		
		return listaResultado;
	}
	
	public CarteroConstructiva(Grafo grafo){
		this.grafo = grafo;
	}
	
	/*Precondición: Grafo con todos los nodos orientados*/
	public List<Eje> encontrarMatchingNodos(Grafo grafo, int aleatoriedad,int[][] pesosMin){
		
		int primo = getPrimeIndex(aleatoriedad);
		int primo2 = primos[primo % primos.length];
//		System.out.println("primo: " + primo);
//		System.out.println("primo2: " + primo2);
		
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
		
		if(nodosDout.isEmpty()){
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
		int indexOut = primo2 % (nodosDout.size());
		
//		System.out.println(nodosDin);
//		System.out.println(nodosDout);
		
		while( nodosDin.size() > 0 && nodosDout.size() > 0){
			
			//las listas nodoDin y nodoDout son mutuamente excluyentes.
			//las listas nodoDin y nodoDout deberian tener la misma longitud.
			
			Integer nodoIn = nodosDin.get(indexIn);
			Integer nodoOut = nodosDout.get(indexOut);
			
			//int valorCaminoMinimo = grafo.pesoCaminoMinimo(nodoIn, nodoOut);
			int valorCaminoMinimo = pesosMin[nodoIn][nodoOut];
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
//		System.out.print("Matching: ");
//		for (Eje eje : triplaInOutPeso) {
//			System.out.print("(" + eje.getNodo1() + "," + eje.getNodo2() + "," + eje.getPeso() + "),");
//		}
//		System.out.println();
		
		return triplaInOutPeso;
		
	}
	
	private int getPrimeIndex(int aleatoriedad) {
		int modulo = aleatoriedad % primos.length;
		return primos[modulo];
	}
	
	private static int pesoMatching(List<Eje> matching) {
		int peso = 0;
		if(matching != null){
			for(Eje eje:matching){
				peso+=eje.getPeso();
			}
		}
		return peso;
	}

	private void agregarCaminosMatcheados(List<Eje> pares,Grafo gr) {
		ListaInt[][] sol = new ListaInt[grafo.getCantNodos()][grafo.getCantNodos()];
		if(pares!=null){
			for(Eje i:pares){
				int nodo1=i.getNodo1(), nodo2=i.getNodo2();
				if(sol[nodo1][nodo2] == null){
					sol[nodo1][nodo2] = Dijkstra.getPath(grafo, Dijkstra.dijkstra(grafo, nodo1), nodo1, nodo2);
//					System.out.println("Dijkstra: "+sol[nodo1][nodo2]);
				}
				ListIterator<Integer> li = sol[nodo1][nodo2].listIterator(1);
//				System.out.println("Arcos Agregados:");
				while(li.hasNext()){
					nodo2 = li.next();
//					System.out.println("("+nodo1+","+nodo2+")");
					gr.agregarAdyacencia(nodo1, nodo2, grafo.getPesoAristas()[nodo1][nodo2],true);
					nodo1=nodo2;
				}
			}
		}
	}
}