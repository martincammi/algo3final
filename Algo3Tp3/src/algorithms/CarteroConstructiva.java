package algorithms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.ListIterator;
import utils.*;

public class CarteroConstructiva {

	private Grafo grafo;
	private int[] primos = {1,3,5,7,11,13,17,19,23};
//PARAMETROS
	static float ALFA = (float) 0.1;  //CALCULA LA LISTA CON ESTE PARAMETRO. 
	static int CANT_ITERACIONES_MAXIMA = 200; 
	static int CANT_ITERACIONES_SIN_MEJORAR = 10; // Ver si puede ser un porcentaje de la cantidad de nodos. Para Daniel si :p
	static int CANT_ITERACIONES_BUSQUEDA_LOCAL = 100;
	static String decisionDefault = "E";// S Mandar Default Salida, E Mandar Default Entrada
	static int TIPO_ORIENTACION_ARISTAS = 3;
	
	
	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		//int[] parametroIteracionesGraspRandom= {435693, 344031, 460173, 227725, 430024, 132060, 352001, 211477, 515771, 747551, 504368, 760850, 978532, 751967, 184402, 418925, 461554, 994190, 473839, 59819, 633512, 213684, 127438, 481296, 923496, 682578, 179673, 185301, 94789, 299200, 628481, 507476, 832449, 394170, 131279, 433443, 579169, 265103, 411148, 22666, 569807, 117055, 990045, 349963, 386973, 653261, 941081, 270227, 388434, 34331, 583368, 113698, 41628, 303959, 430610, 627118, 541428, 665323, 248579, 90369, 84351, 184765, 776814, 558694, 825732, 597923, 526288, 607361, 855952, 898422, 77543, 788653, 497637, 300339, 472692, 250286, 847793, 983025, 619893, 609920, 767202, 750538, 110370, 232126, 453925, 367788, 485700, 512236, 328623, 871647, 787099, 635717, 206447, 597660, 168562, 552242, 633585, 11611, 462221, 580379, 826110, 690095, 846932, 141837, 864622, 955384, 190088, 613448, 670196, 737232, 207807, 180591, 815529, 207931, 320131, 877511, 409707, 616786, 965544, 707993, 135837, 536321, 183061, 854117, 17896, 424919, 470346, 501319, 646404, 9203, 282759, 734747, 402171, 494386, 50926, 294901, 683136, 21928, 263365, 719820, 261397, 111215, 985908, 546565, 675883, 45625, 171105, 796523, 633, 293227, 248470, 927049, 419530, 88475, 780399, 796243, 785342, 243661, 418782, 958144, 744320, 107508, 3756, 782382, 67085, 343190, 408760, 475373, 542441, 859163, 655009, 69334, 162753, 793611, 893659, 840972, 357902, 926922, 85419, 423411, 719354, 276488, 595056, 82949, 996741, 914285, 462158, 246494, 146391, 240029, 809742, 386024, 864581, 41372, 708736, 793196, 347859, 677478, 829047, 610208};//Random
		FileManager fm = new FileManager("Ej.in");
		fm.abrirArchivo();
		String filename = "ALFA_"+ALFA+"_CANT_ITERACIONES_MAXIMA_"+CANT_ITERACIONES_MAXIMA+"_CANT_ITERACIONES_SIN_MEJORAR_"+CANT_ITERACIONES_SIN_MEJORAR+"_CANT_ITERACIONES_BUSQUEDA_LOCAL_"+CANT_ITERACIONES_BUSQUEDA_LOCAL;
		String datasetFilename = filename+".dataset";
		String logFilename = filename+".log";
		//String outFilename = filename+".out";
		String outFilename = "Ej.out";
		Grafo grafo = fm.leerInstancia();
		fm.borrarArchivo(outFilename);
		fm.borrarArchivo(datasetFilename);
		fm.borrarArchivo(logFilename);
		
		//1 REALIZA UN BALANCEO ENTRE LOS GRADOS DE ENTRADA Y GRADOS DE SALIDA, 
		//  CALCULANDO PRIMERO AL NODO DONDE SE CUMPLA (CANTIDAD ARISTAS NODO = DIN NODO - DOUT NODO) 
		//2 ORIENTA PRIMERO CON LA MISMA CUENTA QUE EL PRIMERO, PERO EMPEZANDO DESDE UN CIERTO NODO QUE VIENE POR PARAMETRO
		//3 ORIENTA PRIMERO AL QUE TENGA MAYOR GRADO (CANTIDAD DE ARISTAS)
		//4 ORIENTA PRIMERO AL QUE TENGA MAYOR GRADO DE ENTRADA (DIN)
		//5 ORIENTA PRIMERO AL QUE TENGA MAYOR SALIDA DE ENTRADA (DOUT)

		String textoParametros = "ALFA: "+ALFA+"\nCANT_ITERACIONES_MAXIMA: "+CANT_ITERACIONES_MAXIMA+"\nCANT_ITERACIONES_SIN_MEJORAR: "+CANT_ITERACIONES_SIN_MEJORAR+"\nCANT_ITERACIONES_BUSQUEDA_LOCAL: "+CANT_ITERACIONES_BUSQUEDA_LOCAL+"\n";
		fm.escribirArchivo(textoParametros, logFilename);
		int i = 1;
		while (grafo != null)
		{
			if(FuertementeConexo.fuertementeConexo(grafo)){
				int[][] pesoCaminoMinimo = grafo.calcularDantzig();
				int sumaPesosEjes = grafo.sumaPesosEjes();
//				System.out.println("Instancia " + i + ": ");
//				System.out.println(grafo.toString());
//				int ejesTotales = grafo.getCantAristas() + grafo.getCantArcos();
//				System.out.println("cantNodos " + grafo.cantNodos + ": ");
//				System.out.println("cantEjes " + ejesTotales + ": ");
				String textoInstancia= "Instancia " + i + ": "+grafo.getCantNodos()+" Nodos "+grafo.getCantAristas()+" Aristas "+grafo.getCantArcos()+" Arcos\n Suma peso de los ejes: "+sumaPesosEjes;
				//System.out.println(textoInstancia);
				fm.escribirArchivo(textoInstancia, logFilename);
				CarteroConstructiva cartero = new CarteroConstructiva(grafo);
				
				List<Eje> matchingSolucion = null;
				Grafo mejorGrafoSolucion = null;
				int sumaMejorSolucion = Grafo.INFINITO;
				int j= 1;
				int cantidadIteraciones = 0;
				int iteracionesSinMejorar = 0;
				
				while (cantidadIteraciones < CANT_ITERACIONES_MAXIMA && iteracionesSinMejorar < CANT_ITERACIONES_SIN_MEJORAR){
				//MIENTRAS PARAMETRO DE ITERACIONES CONSTRUCTIVA HACER
					//1) HACER UNA COPIA DEL GRAFO
					Grafo grafoCopia = ((Grafo)grafo.clone());
					
					int parametroGRASP = ((Double)(Math.random() * 1000000)).intValue();
					//int parametroGRASP = parametroIteracionesGraspRandom[cantidadIteraciones];
					//CUANDO TENGAMOS DECIDIDOS LOS VALORES DE ALFA E ITERACIONES LO DEJAMOS ALEATORIO. By Seba
					//2) ORIENTAR LAS ARISTAS
					grafoCopia.orientarTodasAristas(TIPO_ORIENTACION_ARISTAS, ALFA, parametroGRASP, decisionDefault);

					//3) Calcular un Matching cualquiera de Din Dout
					int aleatoriedad = 10; 
					List<Eje> unMatching = cartero.encontrarMatchingNodos(grafoCopia, aleatoriedad,pesoCaminoMinimo);
					//int pesoMatching = pesoMatching(unMatching);
					//3.1) Calcular de todos los vecinos el de menor matchingSolucion
					List<Eje> matchingMinimo = unMatching;

					int iteracionesBL = 0;
					MutableBoolean mejoro = new MutableBoolean();
					
					//System.out.println("matching inicial: " + pesoMatching(unMatching));
					
					int mejoraAux = pesoMatching(unMatching);
					while (iteracionesBL < CANT_ITERACIONES_BUSQUEDA_LOCAL && mejoro.getValue()){
						matchingMinimo = cartero.encontrarMatchingDeMenorPeso(matchingMinimo, pesoCaminoMinimo, mejoro);
						if(pesoMatching(matchingMinimo) < mejoraAux){
							//System.out.println(iteracionesBL + ") encontró mejor matching: " + pesoMatching(matchingMinimo) + " " + (mejoraAux - pesoMatching(matchingMinimo)));
							mejoraAux = pesoMatching(matchingMinimo);
						}
						iteracionesBL++;
					}
					
					int pesoBusquedaLocal = pesoMatching(matchingMinimo);
					//System.out.println("Se mejoró el peso del mathcing de "+pesoMatching+" a "+pesoBusquedaLocal);
					int sumaSolucion = sumaPesosEjes + pesoBusquedaLocal;
					cantidadIteraciones++;
					if(sumaSolucion < sumaMejorSolucion){
						String textoIteracionGraspMejoro = "Iteración GRASP " + j + ": Mejoró la solución de "+sumaMejorSolucion+" a "+sumaSolucion;
						fm.escribirArchivo(textoIteracionGraspMejoro, logFilename);
						//System.out.println(textoIteracionGraspMejoro);
						sumaMejorSolucion = sumaSolucion;
						mejorGrafoSolucion = grafoCopia;
						matchingSolucion = matchingMinimo;
						iteracionesSinMejorar = 0;
						if(unMatching.isEmpty()){
							String textoSolOpt = "Solución Optima. El Grafo original es Euleriano";
							//System.out.println(textoSolOpt);
							fm.escribirArchivo(textoSolOpt, logFilename);
							break;//solucion optima
						}
					}
					else
					{
						iteracionesSinMejorar++;
						String textoIteracionGraspNoMejoro = "Iteración GRASP " + j + ": No se encontró una mejor solución en esta iteración "+sumaSolucion;
//						fm.escribirArchivo(textoIteracionGraspNoMejoro, logFilename);
						//System.out.println(textoIteracionGraspNoMejoro);
					}
					++j;
				}
				//4) CALCULAR EULERIANO 
				cartero.agregarCaminosMatcheados(matchingSolucion,mejorGrafoSolucion);//en vez de grafo hay que pasarle la copia que ya tiene las aristas orientadas//hay que pasarle los pares de la mejor solucion que encontremos
				ListaInt circuitoEuleriano = CircuitoEuleriano.encontrarCircuitoEuleriano(mejorGrafoSolucion);
//				5) DEVOLVER | reemplazar por la llamada a archivo|
//				System.out.println("Circuito: ");
//				System.out.println(sumaMejorSolucion);
//				System.out.println(circuitoEuleriano.size()-1);
				fm.escribirArchivo(String.valueOf(circuitoEuleriano.size()-1), outFilename);
				//fm.escribirArchivo(String.valueOf(circuitoEuleriano.size()-1)+" "+String.valueOf(sumaMejorSolucion), outFilename);
				ListIterator<Integer> li = circuitoEuleriano.listIterator();
				int nodo1,nodo2 = li.next();
				while(li.hasNext()){
					Grafo.complex++;
					nodo1 = nodo2;
					nodo2 = li.next();
					String textoCircuito= nodo1+" "+nodo2+" "+grafo.getPesoAristas()[nodo1][nodo2];
					fm.escribirArchivo(textoCircuito, outFilename);
				}
				String textoComplejidadYParametros = "Nodos: "+grafo.getCantNodos()+" Aristas: "+grafo.getCantAristas()+" Arcos: "+grafo.getCantArcos()+" Repetidos: "+(circuitoEuleriano.size() -1 - (grafo.getCantAristas() + grafo.getCantArcos()));
				String textoDataSet = grafo.getCantNodos()+"\t"+grafo.getCantAristas()+"\t"+grafo.getCantArcos()+"\t"+grafo.T+"\t"+Grafo.complex;
				fm.escribirArchivo(textoComplejidadYParametros, logFilename);
				fm.escribirArchivo(textoDataSet, datasetFilename);
				fm.escribirArchivo("----", logFilename);
			}else{
				System.out.println("No existe solución porque el grafo no es fuertemente conexo");
			}
			grafo = fm.leerInstancia();
			i++;
		}
	}	
	//La búsqueda local queda para el de búsqueda local
	//BUSQUEDA LOCAL SOBRE MATCHING EN LAS VECINDADES Los vecinos
	public List<Eje> encontrarMatchingDeMenorPeso(List<Eje> matching, int[][] pesosMin, MutableBoolean mejoro){
		
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
		//boolean primeraVez = true;
		
		for (Eje eje3 : matching) {
			sumaInicial += eje3.getPeso();
		}
		
		minimoActual = sumaInicial;
		
		for (int i = 0; i < matching.size(); i++) {
			
			eje = matching.get(i);
			
			for (int j = i+1; j < matching.size(); j++) {
				Grafo.complex++;
				eje2 = matching.get(j);

				
				posibleMinimo = pesosMin[eje.getNodo1()][eje2.getNodo2()] + pesosMin[eje2.getNodo1()][eje.getNodo2()];
				valorLocal = pesosMin[eje.getNodo1()][eje.getNodo2()] + pesosMin[eje2.getNodo1()][eje2.getNodo2()];

				if(posibleMinimo < valorLocal){
					if(sumaInicial - valorLocal + posibleMinimo < minimoActual){
						//System.out.println("Encontro mejora de " + posibleMinimo + "a" + minimoActual);
						sumaInicial = posibleMinimo;
						swapIndex1 = i;
						swapIndex2 = j;
					}
					sumaInicial = posibleMinimo;
					swapIndex1 = i;
					swapIndex2 = j;
				}
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
	
	public CarteroConstructiva(Grafo grafo){
		this.grafo = grafo;
	}
	
	/*Precondición: Grafo con todos los nodos orientados*/
	public List<Eje> encontrarMatchingNodos(Grafo grafo, int aleatoriedad,int[][] pesosMin){
		
		int primo = getPrimeIndex(aleatoriedad);
		int primo2 = primos[primo % primos.length];
		
		if(!grafo.todasDirigidas()){
			System.out.println("ERROR: El grafo no es dirigido");
			return null;
		}
		
		List<Eje> triplaInOutPeso;
		List<Integer> nodosDin = new ArrayList<Integer>();
		List<Integer> nodosDout = new ArrayList<Integer>();
		ListaInt[] orientados = grafo.adyacenciasOr;
		
		int dinExedente[] = new int[grafo.getCantNodos()];
		int doutExedente[] = new int[grafo.getCantNodos()];
		
		for (int i = 0; i < orientados.length; i++) {
			Grafo.complex++;
			if(grafo.getDin(i) > grafo.getDout(i)){
				nodosDin.add(i);
				dinExedente[i] = grafo.getDin(i) - grafo.getDout(i);
			}
			
			if(grafo.getDout(i) > grafo.getDin(i)){
				nodosDout.add(i);
				doutExedente[i] = grafo.getDout(i) - grafo.getDin(i);
			}
		}
		
		triplaInOutPeso = new ArrayList<Eje>();
		
		if(nodosDout.isEmpty()){
			return triplaInOutPeso;
		}
			
		int i = 0;
		
		int corte = 0;
		while(primo % nodosDin.size() == 0 && corte <= primos.length){
			Grafo.complex++;
			primo = getPrimeIndex( (aleatoriedad+1) % primos.length);
			corte++;
		}
		
		if(corte > primos.length){
			//Comprarle medio kilo de helado a Sebas.
			//bizarro - by Seba.
		}
		
		int indexIn = primo % (nodosDin.size());
		int indexOut = primo2 % (nodosDout.size());
		
		while( nodosDin.size() > 0 && nodosDout.size() > 0){
			Grafo.complex++;
			
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
				Grafo.complex++;
				peso+=eje.getPeso();
			}
		}
		return peso;
	}

	private void agregarCaminosMatcheados(List<Eje> pares,Grafo gr) {
		int[][] sol = new int[grafo.getCantNodos()][grafo.getCantNodos()];
		boolean[] inicializados = new boolean[grafo.getCantNodos()];
		for(int i = 0;i < grafo.getCantNodos();++i){
			inicializados[i] = false;
		}
		if(pares!=null){
			for(Eje i:pares){
				int nodo1=i.getNodo1(), nodo2=i.getNodo2();
				if(!inicializados[nodo1]){
					inicializados[nodo1] = true;
					sol[nodo1] = Dijkstra.dijkstra(grafo, nodo1);
//					System.out.println("Dijkstra: "+sol[nodo1][nodo2]);
				}
				ListIterator<Integer> li = Dijkstra.getPath(grafo, sol[nodo1], nodo1, nodo2).listIterator(1);
//				System.out.println("Arcos Agregados:");
				while(li.hasNext()){
					Grafo.complex++;
					nodo2 = li.next();
//					System.out.println("("+nodo1+","+nodo2+")");
					gr.agregarAdyacencia(nodo1, nodo2, grafo.getPesoAristas()[nodo1][nodo2],true);
					nodo1=nodo2;
				}
			}
		}
	}
}