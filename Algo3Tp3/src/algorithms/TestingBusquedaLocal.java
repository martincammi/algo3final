package algorithms;

import java.util.ArrayList;
import java.util.List;

import utils.Eje;
import utils.Grafo;

public class TestingBusquedaLocal {

	static int CANT_ITERACIONES_BUSQUEDA_LOCAL = 50;
	
	public static void main(String[] args) {
		
		int cantNodos = 8;
		int[][] pesoCaminoMinimo = new int[cantNodos][cantNodos];
		
		//Iniciando el resto
		for (int i = 0; i < cantNodos; i++) {
			for (int j = 0; j < cantNodos; j++) {
				pesoCaminoMinimo[i][j] = 100; //Numero grande.
			}
		}
				
		pesoCaminoMinimo[0][1] = 1;
		pesoCaminoMinimo[2][3] = 1;
		pesoCaminoMinimo[4][5] = 5;
		pesoCaminoMinimo[6][7] = 5;
		
		//pesoCaminoMinimo[0][3] = 1;
		//pesoCaminoMinimo[2][1] = 1;
		pesoCaminoMinimo[4][7] = 2;
		pesoCaminoMinimo[6][5] = 2;
		
		List<Eje> matchingMinimo = new ArrayList<Eje>();
		
		matchingMinimo.add(new Eje(0,1,1));
		matchingMinimo.add(new Eje(2,3,1));
		matchingMinimo.add(new Eje(4,5,5));
		matchingMinimo.add(new Eje(6,7,5));
		
		MutableBoolean1 mejoro = new MutableBoolean1();
		
		int iteracionesBL = 0;
		
		mejoro.True();
		for (Eje eje : matchingMinimo) {
			System.out.println(eje);
		}
		while (iteracionesBL < CANT_ITERACIONES_BUSQUEDA_LOCAL && mejoro.getValue()){
			matchingMinimo = encontrarMatchingDeMenorPeso(matchingMinimo,pesoCaminoMinimo, mejoro);
			System.out.println("mejoro?: " + mejoro.getValue());
			
			for (Eje eje : matchingMinimo) {
				System.out.println(eje);
			}
			iteracionesBL++;
		}
		
	}
	
	
	//La búsqueda local queda para el de búsqueda local
	//BUSQUEDA LOCAL SOBRE MATCHING EN LAS VECINDADES Los vecinos
	public static List<Eje> encontrarMatchingDeMenorPeso(List<Eje> matching, int[][] pesosMin, MutableBoolean1 mejoro){
		
		List<Eje> listaResultado = new ArrayList<Eje>();
		
		if(matching.size() <= 1){
			return matching;
		}
		
		Eje eje;
		Eje eje2;
		int swapIndex1 = 0;
		int swapIndex2 = 0;
		int sumaInicial = 0;
		int minimoActual = 0;
		int posibleMinimo;
		int valorLocal; 
		boolean primeraVez = true;
		
		for (Eje eje3 : matching) {
			sumaInicial += eje3.getPeso();
		}
		
		minimoActual = sumaInicial;
		
		for (int i = 0; i < matching.size(); i++) {
			
			eje = matching.get(i);
			
			for (int j = i+1; j < matching.size(); j++) {
				Grafo.complex++;
				eje2 = matching.get(j);

//				if(primeraVez){
//					minimoInicial = eje.getPeso() + eje2.getPeso();
//					primeraVez = false;
//				}
				
				posibleMinimo = pesosMin[eje.getNodo1()][eje2.getNodo2()] + pesosMin[eje2.getNodo1()][eje.getNodo2()];
				valorLocal = pesosMin[eje.getNodo1()][eje.getNodo2()] + pesosMin[eje2.getNodo1()][eje2.getNodo2()];

				if(posibleMinimo < valorLocal){
					if(sumaInicial - valorLocal + posibleMinimo < minimoActual){
						System.out.println("Encontro mejora de " + posibleMinimo + "a" + minimoActual);
						sumaInicial = posibleMinimo;
						swapIndex1 = i;
						swapIndex2 = j;
					}
					sumaInicial = posibleMinimo;
					swapIndex1 = i;
					swapIndex2 = j;
				}
					
//				if(posibleMinimo <= minimoInicial){
//					System.out.println("Encontro mejora de " + posibleMinimo + "a" + minimoInicial);
//					minimoInicial = posibleMinimo;
//					swapIndex1 = i;
//					swapIndex2 = j;
//				}
			}
		}
		
		//SI NO HAY NADA PARA SWAPEAR, DEVOLVE EL MISMO MATCHING. EL TEMA QUE ANTES SWAPEABA POSICION 0 CON POSICION 0 y AGREGABA ALGUNA ARISTA
		if (swapIndex1 != swapIndex2)
		{
			mejoro.True();
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
				Grafo.complex++;
				if(i != swapIndex1 && i != swapIndex2){
					listaResultado.add(matching.get(i));
				}
			}
		}
		
		else
		{
			mejoro.False();
			listaResultado = matching;
		}
		
	
		return listaResultado;
	}
	
	public static class MutableBoolean1{
		
		boolean flag;
		
		public MutableBoolean1(){
			flag = true;
		}

		public void True(){
			flag = true;
		}
		
		public void False(){
			flag = false;
		}
		
		public boolean getValue(){
			return flag;
		}
	}
}
