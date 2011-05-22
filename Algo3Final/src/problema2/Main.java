package problema2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

//Problema2
public class Main {

	static boolean logActivated = false;
	//City city = new City("7 0 1 0 2 0 4 2 4 2 3 3 1 4 3");
	//City city = new City("5 0 2 0 1 1 5 2 5 2 1");
	//City city = new City("9 0 1 0 2 0 3 0 4 1 4 2 1 2 0 3 0 3 1");
	//City city = new City("10 0 1 0 2 0 3 0 4 0 5 0 6 0 7 0 8 0 9");
	//Procesor.procesarDatos();
	
    public static void main(String[] args) {
    	
    	PathFinder encuentraCaminos;
    	List<Grafo> grafos = initTestGrafos();
    	
    	int i = 0;
    	int cantGrafos = grafos.size(); 
    	for (Grafo grafo : grafos) {
    		if(i == cantGrafos-1){
    			logActivated = true;
    		}
    		encuentraCaminos = new PathFinder(grafo);
    		encuentraCaminos.calcularCaminos();
    		check(encuentraCaminos.matCaminos,i);
    		i++;
		}
    	
		

				
    	//PathFinder encuentraCaminos = new PathFinder(grafo);
    	//encuentraCaminos.calcularCaminos();
    	
    	
    	//encuentraCaminos.mostrarCaminos();

    }


    
    private static void obtenerCaminos(City city){
    	//Converter converter = new Converter(datos);
    	
    	int max = city.getMax();
    	int matAdj[][] = city.getMatAdj();
    	int matCaminoI[][] = city.copyMatriz(matAdj);
    	int matSuma[][] = city.copyMatriz(matAdj);
    	int ITERACIONES = (max+1)* max;//max+1;
    	for (int i = 1; i < ITERACIONES;  i++) {
    		matCaminoI = city.multiplicarMatrices(matCaminoI,matAdj);
			matSuma = city.sumarMatrices(matSuma,matCaminoI);
		}
    	
    	logln("matrix for city " + city.getName());
    	city.marcarCaminosInfinitos(matSuma);
    	city.mostrarMatriz(matSuma);
    }
    
    public static class Grafo {
        private Map<Integer, LinkedHashSet<Integer>> map = new HashMap();
        List<Integer> nodos = new ArrayList<Integer>();
        public int cantNodos = 0;

        public Integer unNodo(){
        	return nodos.get(0);
        }
        
        public void agregarEje(Integer nodo1, Integer nodo2) {
            LinkedHashSet<Integer> adjacent = map.get(nodo1);
             if(!nodos.contains(nodo1)){
            	 nodos.add(nodo1);
            	 cantNodos++;
             }
             if(!nodos.contains(nodo2)){
            	 nodos.add(nodo2);
            	 cantNodos++;
             }
             
            if(adjacent==null) {
                adjacent = new LinkedHashSet<Integer>();
                map.put(nodo1, adjacent);
            }
            adjacent.add(nodo2);
        }

        public boolean hayEje(Integer node1, Integer node2) {
            Set<Integer> adyacente = map.get(node1);
            if(adyacente == null) {
                return false;
            }
            return adyacente.contains(node2);
        }

        public LinkedList<Integer> adyacentes(Integer last) {
            LinkedHashSet<Integer> adyacente = map.get(last);
            if(adyacente == null) {
                return new LinkedList<Integer>();
            }
            return new LinkedList<Integer>(adyacente);
        }
        
    }

    private static class PathFinder{
    	
    	private Grafo grafo;
    	private int[][] matCaminos;
    	private boolean[] fueVisitadoAlgunaVez;
    	
    	public PathFinder(Grafo grafo){
    		this.grafo = grafo;
    		matCaminos = new int[grafo.cantNodos][grafo.cantNodos];
    		fueVisitadoAlgunaVez = new boolean[grafo.cantNodos];
    	}
    	
    	public void calcularCaminos(){
    		List<Integer> nodos = new ArrayList<Integer>();
    		
    		nodos.add( grafo.unNodo() );
    		calcularCaminos( nodos );
    		nodos.remove(grafo.unNodo());
    		
    		for (int i = 0; i < fueVisitadoAlgunaVez.length; i++) {
    			if(!fueVisitadoAlgunaVez[i]){
    				nodos.add(i);
    				calcularCaminos( nodos );
    				nodos.remove(new Integer(i));
    			}
			}
    		
    	}
    	
    	public void calcularCaminos(List<Integer> visitados){
    		
    		int ultimo = visitados.size()-1;
    		boolean propagar = false;
    		Integer nodoActual = visitados.get(ultimo);
    		
    		List<Integer> adyacentes = grafo.adyacentes(nodoActual);
    		
    		/*
    		if(adyacentes.size() == 0){
    			for (int i = 0; i < grafo.cantNodos; i++) {
    				matCaminos[nodoActual][i] = 0; //Redundante ya es cero
    			}
    		}*/
    		
    		logln("Revisando adjacentes de " + nodoActual);
    		for (Integer nodoAdj : adyacentes) {
				//Encontramos un ciclo
    			//log(nodoAdj + " ");
    			if(visitados.contains(nodoAdj)){ //Encontr� ciclo.
    				//logln("Ya visitado.");
    				//matCaminos[nodoActual][nodo]++;
    				logln(nodoActual + " encontro ciclo con " + nodoAdj);
    				matCaminos[nodoActual][nodoAdj] = -1; //Ciclo con ese nodoAdj
    				//matCaminos[nodoActual][nodoActual] = -1; //Yo estoy en un ciclo (por ahora no me seteo) caso 5 en -1
    				matCaminos[nodoAdj][nodoActual] = -1; //El otro nodo tiene un ciclo conmigo. SI VA
    				matCaminos[nodoAdj][nodoAdj] = -1; //El otro nodo est� ya en un ciclo (esto determinara que ese nodo luego propague) SI Va
    				
    				//tenesCicloMutuo(nodoAdj);
    				tenesCicloMutuo(nodoActual);
    				
    				/*
    				if(grafo.hayEje(nodoAdj, nodoActual)){ // Hay ciclo mutuo, llego a todos mis adyacentes infinitamente.
    					for (Integer nodoAdjaAdj : adyacentes) {
    						if(nodoAdjaAdj != nodoAdj){
    							matCaminos[nodoAdj][nodoAdjaAdj] = -1;
    						}
    					}
    				}*/
    				
				}else{
					if(!fueVisitadoAlgunaVez[nodoAdj]){
						//logln("No visitado nunca.");
						visitados.add(nodoAdj);
						calcularCaminos(visitados);
						visitados.remove(nodoAdj);
						
						/*//Con pinzas
						if(yoTengoUnCiclo(nodoActual)){
							logln("nodo " + nodoActual + "tiene ciclo");
							//matCaminos[nodoActual][nodoActual] = -1; //Tengo un ciclo con mi mismo
							tengoCiclo = true;
						}*/
					}
					
					//log(nodoAdj + ") (");
					//propagar = false;
					logln(nodoActual + " Obteniendo Feedback de " + nodoAdj);
					for (int i = 0; i < grafo.cantNodos; i++) {
						//log(matCaminos[nodoAdj][i] + " ");
						
						//Si mi adyacente tiene un ciclo yo me lo copio
						if(matCaminos[nodoAdj][i] == -1){
							matCaminos[nodoActual][i] = -1;
							//Si el nodo adjacente tenia marcado ciclo conmigo yo propago los ciclos que tenga con otros nodos.
							if(nodoActual == i){
								propagar = true;
							}
						//Sino sumo los caminos de mi adjancente a los que ya tenga de otros adyacentes.
						}else{
							if(matCaminos[nodoActual][i] != -1){
								if(nodoAdj == i){
									matCaminos[nodoActual][i] += matCaminos[nodoAdj][i] + 1;
								}else{
									matCaminos[nodoActual][i] += matCaminos[nodoAdj][i];
								}
							}
						}
					}
					mostrarNodo(nodoAdj);
					mostrarNodo(nodoActual);
				}
			}
    		mostrarNodo(nodoActual);
    		
    	   	if(propagar){
    	   		List<Integer> nodosEnCiclo = new ArrayList<Integer>();
    	   		boolean existeCicloMutuo = false; 
    	   		for (int i = 0; i < grafo.cantNodos; i++) {
    				if(matCaminos[nodoActual][i] == -1 && nodoActual != i){
    					int nodoConCiclo = i;
    					logln(nodoActual + " propagando Ciclo en " + nodoConCiclo);
    					propagarCiclo(nodoActual, nodoConCiclo, nodosEnCiclo);
    				}
    			}
    	   		
    	   		for (int i = 0; i < grafo.cantNodos; i++) {
    	   			int nodoConCiclo = i;
	    	   		if( tieneCicloMutuo(nodoActual, nodoConCiclo)){
						tenesCicloMutuo(nodoConCiclo, nodoActual);
						existeCicloMutuo = true;
					}
    	   		}
    	   		//if(existeCicloMutuo){
    	   			tenesCicloMutuo(nodoActual);
    	   		//}
        	}
    	   	
    		fueVisitadoAlgunaVez[nodoActual] = true;
    	}
    	
    	public boolean tieneCicloMutuo(Integer nodo1, Integer nodo2){
    		return grafo.hayEje(nodo1, nodo2) && grafo.hayEje(nodo2, nodo1); 
    	}
    	
    	public void tenesCicloMutuo(Integer nodo){
    		List<Integer> adyacentes = grafo.adyacentes(nodo);
    		for (Integer nodoAdj : adyacentes) {
    			matCaminos[nodo][nodoAdj] = -1;
			}
    	}
    	
    	public void tenesCicloMutuo(Integer nodoConCicloMutuo1, Integer nodoConCicloMutuo2){
    		tenesCicloMutuo(nodoConCicloMutuo1);
    		
    		List<Integer> adyacentes = grafo.adyacentes(nodoConCicloMutuo2);
    		for (Integer nodoAdj : adyacentes) {
    			matCaminos[nodoConCicloMutuo1][nodoAdj] = -1;
			}
    	}
    	
    	/**
    	 * Avisa a todos los nodos que el nodoPropagador pueda alcanzar que marquen a
    	 * nodoConCiclo como que tiene infinitos caminos. tambi�n avisa a los que est�n
    	 * en la lista de nodosEnCiclo.
    	 * @param nodoPropagador
    	 * @param nodoConCiclo
    	 * @param nodosEnCiclo
    	 */
    	public void propagarCiclo(Integer nodoPropagador, int nodoConCiclo, List<Integer> nodosEnCiclo){
    		
    		if(nodosEnCiclo.contains(nodoPropagador)){
    			return;
    		}
    		nodosEnCiclo.add(nodoPropagador);

    		//Caso base
    		if(nodoPropagador.intValue() == nodoConCiclo){
    			//matCaminos[nodoPropagador][nodoPropagador] = -1; //Marcamos el que tiene el ciclo que lo tiene con si mismo finalmente. Comentado porque Ya esta en nodosEnCiclo
    			for (Integer nodoEnCiclo : nodosEnCiclo) {
					matCaminos[nodoPropagador][nodoEnCiclo] = -1;
				}
    			return;
    		}
    		
    		List<Integer> adyacentes = grafo.adyacentes(nodoPropagador);
    		
    		for (Integer nodoAdj : adyacentes) {
    			propagarCiclo(nodoAdj, nodoConCiclo, nodosEnCiclo);
    			if(matCaminos[nodoAdj][nodoConCiclo] == -1){ //Si el adjacente se actualiz�, actualizame a mi tambi�n
    				//matCaminos[nodoPropagador][nodoPropagador] = -1; Ya esta en nodosEnCiclo
    				
    				for (int i = 0; i < grafo.cantNodos; i++) {
    					if(matCaminos[nodoAdj][i] == -1){
    						matCaminos[nodoPropagador][i] = -1;
    					}
    				}
    				
    				for (Integer nodoEnCiclo : nodosEnCiclo) {
    					matCaminos[nodoPropagador][nodoEnCiclo] = -1;
					}
    			}
			}
    		nodosEnCiclo.remove(nodoPropagador);
    	}
    	
    	public void mostrarCaminos(){
			for (int i = 0; i < grafo.cantNodos; i++) {
				for (int j = 0; j < grafo.cantNodos; j++) {
					log(matCaminos[i][j]);
					log(" ");
				}
				logln("");
			}
		}
    
    	public void mostrarNodo(Integer nodo){
    		log(nodo + " ) (");
			for (int j = 0; j < grafo.cantNodos; j++) {
				log(matCaminos[nodo][j]);
				log(" ");
			}
			logln(")");
		}
    	
    }
    
    private static class Procesor{
    	public static void procesarDatos() {
    		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    		
    		int[] numbers;
    		int nodo;
    		int name = 0; 
   			
    		while(in.hasNextInt()){
   				int ejes = in.nextInt();
   				numbers = new int[ejes*2+1];
   				numbers[0] = ejes;
   				for (int i = 1; i < ejes*2+1; i=i+2) {
   					nodo = in.nextInt();
   					numbers[i] = nodo;
   					nodo = in.nextInt();
   					numbers[i+1] = nodo;
				}
   				
   				City city = new City(numbers,name);
   	    		obtenerCaminos(city);
   				name++;
    		}
   			in.close();
    	}
    	
    	public static void process(int[] numbers){
    		
    	}
    }

    private static class City{
    	
    	int name;
    	int ejes;
		int[][] matAdj;
		int max;
    	
		public City(int[] numbers){
			this(numbers,0);
		}
		
    	public City(int[] numbers, int name){
    		this.name = name;
			ejes = numbers[0];
			
			max = 0;
			for (int i = 1;  i < numbers.length; i++) {
				int nodo = numbers[i];
				if(nodo > max){
					max = nodo;
				}
			}
			max = max + 1;
			
			matAdj = new int [max][max];
			
			for (int i = 1;  i < numbers.length-1; i=i+2) {
				int f = numbers[i];
				int c = numbers[i+1];
				matAdj[f][c] = 1;
			}
		}

    	public int getName() {
			return name;
		}
    	
		public int[][] getMatAdj() {
			return matAdj;
		}

		public int getMax() {
			return max;
		}

		public void mostrarMatriz(){
			for (int i = 0; i < max; i++) {
				for (int j = 0; j < max; j++) {
					log(matAdj[i][j]);
					log(" ");
				}
				logln("");
			}
		}
		
		public void mostrarMatriz(int mat[][]){
			for (int i = 0; i < max; i++) {
				for (int j = 0; j < max; j++) {
					log(mat[i][j]);
					log(" ");
				}
				logln("");
			}
		}
		
		public int[][] multiplicarMatrices(int[][] mat1, int [][] mat2){
			int suma = 0;
			int matRes[][] = new int[max][max];
			
			for (int f = 0; f < max; f++) {
				for (int c = 0; c < max; c++) {
					int i = 0;
					suma = 0;
					for (i = 0; i < max; i++) {
						suma = suma + mat1[f][i] * mat2[i][c]; 
					}
					matRes[f][c] = suma;
				}
				

			}
			return matRes;
		}
		
		public int[][] copyMatriz(int mat[][]){
			
			int matRes[][] = new int[max][max];
				
				for (int i = 0; i < max; i++) {
					for (int j = 0; j < max; j++) {
						matRes[i][j] = mat[i][j];
					}
				}
			return matRes;
		}
		
		public int[][] sumarMatrices(int mat1[][], int mat2[][]){
			
			int matRes[][] = new int[max][max];
				
				for (int i = 0; i < max; i++) {
					for (int j = 0; j < max; j++) {
						matRes[i][j] = mat1[i][j] + mat2[i][j];
					}
				}
			return matRes;
		}
		
	public void marcarCaminosInfinitos(int mat[][]){
			for (int i = 0; i < max; i++) {
				for (int j = 0; j < max; j++) {
					if((mat[i][j] > max)){
						mat[i][j] = -1;
					}
				}
			}
		}
		
    }
    
    public static void enter(){
    	logln("");
    }

    public static boolean check(int mat[][], int idGrafo){
    	
    	for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j] != getMat(i,j,idGrafo)){
					System.out.println("Fallo grafo " + idGrafo + " (" +i+ "," +j+ ")");
					mostrarMatriz(mat,mat.length);
					return false;
				}
			}
		}
    	System.out.println("Grafo " + idGrafo + " ok");
    	return true;
    }
    
    public static List<Grafo> initTestGrafos(){
    	
    	List<Grafo> grafos = new ArrayList<Grafo>();
    	Grafo grafo;
    	grafo = new Grafo(); grafo.agregarEje(0,1); grafo.agregarEje(0,2); grafo.agregarEje(1,2); grafo.agregarEje(1,3); grafo.agregarEje(2,3);
    	grafos.add(grafo);
    	grafo = new Grafo(); grafo.agregarEje(0,1); grafo.agregarEje(1,2); grafo.agregarEje(2,0);
    	grafos.add(grafo);
    	grafo = new Grafo(); grafo.agregarEje(0,1); grafo.agregarEje(1,2); grafo.agregarEje(2,3); grafo.agregarEje(3,0);
    	grafos.add(grafo);
    	grafo = new Grafo(); grafo.agregarEje(0,1); grafo.agregarEje(0,2); grafo.agregarEje(1,2); grafo.agregarEje(2,1);
    	grafos.add(grafo);
    	grafo = new Grafo(); grafo.agregarEje(0,1); grafo.agregarEje(1,2); grafo.agregarEje(2,3); grafo.agregarEje(3,1);
    	grafos.add(grafo);
		grafo = new Grafo(); grafo.agregarEje(0,1); grafo.agregarEje(0,2); grafo.agregarEje(1,2); grafo.agregarEje(1,3); grafo.agregarEje(2,3);grafo.agregarEje(0,3);
		grafos.add(grafo);
		grafo = new Grafo(); grafo.agregarEje(0,1);	grafo.agregarEje(1,2); grafo.agregarEje(4,2); grafo.agregarEje(3,2); grafo.agregarEje(2,3);
		grafos.add(grafo);
		grafo = new Grafo(); grafo.agregarEje(0,1);	grafo.agregarEje(1,2); grafo.agregarEje(2,3); grafo.agregarEje(3,4); grafo.agregarEje(4,1);
		grafos.add(grafo);
		//Test Practica
		grafo = new Grafo(); grafo.agregarEje(0,1); grafo.agregarEje(0,2);
		grafo.agregarEje(0,4); grafo.agregarEje(2,4); grafo.agregarEje(2,3);
		grafo.agregarEje(3,1); grafo.agregarEje(4,3);
		grafos.add(grafo);
		
		grafo = new Grafo(); grafo.agregarEje(0, 1); grafo.agregarEje(0, 2);
		grafo.agregarEje(0, 3); grafo.agregarEje(0, 4); grafo.agregarEje(1, 4);
		grafo.agregarEje(2, 1); grafo.agregarEje(2, 0); grafo.agregarEje(3, 0);
		grafo.agregarEje(3, 1);
		grafos.add(grafo);
		
		/*
		grafo = new Grafo(); grafo.agregarEje(0, 1); grafo.agregarEje(1, 2);
		grafo.agregarEje(2, 3); grafo.agregarEje(2, 4); grafo.agregarEje(4, 5);
		grafo.agregarEje(4, 6); grafo.agregarEje(6, 7); grafo.agregarEje(6, 1);
		grafos.add(grafo);
		*/
		
		grafo = new Grafo(); grafo.agregarEje(0, 1); grafo.agregarEje(1, 2);
		grafo.agregarEje(2, 1); grafo.agregarEje(1, 3);
		grafos.add(grafo);
		
		grafo = new Grafo(); grafo.agregarEje(0, 1); grafo.agregarEje(1, 2);
		grafo.agregarEje(2, 1); grafo.agregarEje(1, 3); grafo.agregarEje(3, 1);
		grafo.agregarEje(1, 4);
		grafos.add(grafo);
		
		grafo = new Grafo(); grafo.agregarEje(0, 1); grafo.agregarEje(1, 2);
		grafo.agregarEje(2, 3); grafo.agregarEje(3, 1);	grafo.agregarEje(1, 4);
		grafos.add(grafo);
		
		grafo = new Grafo(); grafo.agregarEje(0, 1); grafo.agregarEje(1, 2);
		grafo.agregarEje(2, 3); grafo.agregarEje(3, 4);	grafo.agregarEje(4, 1);
		grafo.agregarEje(4, 5);
		grafos.add(grafo);
		
		return grafos; 
    }
    
    public static int getMat (int i, int j, int idGrafo){
    	
    	int matRes0 [][] = {
    			{ 0, 1, 2, 3 },
    			{ 0, 0, 1, 2 },
    			{ 0, 0, 0, 1 },
    			{ 0, 0, 0, 0 }
    	};
    	if(idGrafo == 0){
    		return matRes0[i][j];
    	}
    	
    	int matRes1 [][] = {
        		{ -1, -1, -1 },
        		{ -1, -1, -1 },
        		{ -1, -1, -1 }
        	};
    	if(idGrafo == 1){
    		return matRes1[i][j];
    	}

    	int matRes2 [][] = {
        		{ -1, -1, -1, -1 },
        		{ -1, -1, -1, -1 },
        		{ -1, -1, -1, -1 },
        		{ -1, -1, -1, -1 }
        	};
    	if(idGrafo == 2){
    		return matRes2[i][j];
    	}
    	
    	int matRes3 [][] = {
        		{ 0, -1, -1 },
        		{ 0, -1, -1 },
        		{ 0, -1, -1 }
        	};
    	if(idGrafo == 3){
    		return matRes3[i][j];
    	}

    	int matRes4 [][] = {
        		{ 0, -1, -1, -1 },
        		{ 0, -1, -1, -1 },
        		{ 0, -1, -1, -1 },
        		{ 0, -1, -1, -1 }
        	};
    	if(idGrafo == 4){
    		return matRes4[i][j];
    	}

    	int matRes5 [][] = {
        		{ 0, 1, 2, 4 },
        		{ 0, 0, 1, 2 },
        		{ 0, 0, 0, 1 },
        		{ 0, 0, 0, 0 }
        	};
    	if(idGrafo == 5){
    		return matRes5[i][j];
    	}
    	
    	int matRes6 [][] = {
        		{ 0, 1, -1, -1, 0 },
        		{ 0, 0, -1, -1, 0 },
        		{ 0, 0, -1, -1, 0 },
        		{ 0, 0, -1, -1, 0 },
        		{ 0, 0, -1, -1, 0 },
        	};
    	if(idGrafo == 6){
    		return matRes6[i][j];
    	}
    	
    	int matRes7 [][] = {
        		{ 0, -1, -1, -1, -1 },
        		{ 0, -1, -1, -1, -1 },
        		{ 0, -1, -1, -1, -1 },
        		{ 0, -1, -1, -1, -1 },
        		{ 0, -1, -1, -1, -1 }
        	};
    	if(idGrafo == 7){
    		return matRes7[i][j];
    	}
    	
     	int matRes8 [][] = {
        		{ 0, 4, 1, 3, 2 },
        		{ 0, 0, 0, 0, 0 },
        		{ 0, 2, 0, 2, 1 },
        		{ 0, 1, 0, 0, 0 },
        		{ 0, 1, 0, 1, 0 }
        	};
    	if(idGrafo == 8){
    		return matRes8[i][j];
    	}
    	
    	int matRes9 [][] = {
    			{ -1, -1, -1, -1, -1 },
        		{ 0, 0, 0, 0, 1 },
        		{ -1, -1, -1, -1, -1 },
        		{ -1, -1, -1, -1, -1 },
        		{ 0, 0, 0, 0, 0 }
        	};
    	if(idGrafo == 9){
    		return matRes9[i][j];
    	}
    	/*
    	int matRes10 [][] = {
        		{ 0, -1, -1, -1, -1, -1, -1, -1 },
        		{ 0, -1, -1, -1, -1, -1, -1, -1 },
        		{ 0, -1, -1, -1, -1, -1, -1, -1 },
        		{ 0, 0, 0, 0, 0, 0, 0, 0 },
        		{ 0, -1, -1, -1, -1, -1, -1, -1 },
        		{ 0, 0, 0, 0, 0, 0, 0, 0 },
        		{ 0, -1, -1, -1, -1, -1, -1, -1 },
        		{ 0, 0, 0, 0, 0, 0, 0, 0 },
        	};
    	if(idGrafo == 10){
    		return matRes10[i][j];
    	}*/
    	int matRes10 [][] = {
    			{ 0, -1, -1, -1 },
        		{ 0, -1, -1, -1 },
        		{ 0, -1, -1, -1 },
        		{ 0, 0, 0, 0 }
        	};
    	if(idGrafo == 10){
    		return matRes10[i][j];
    	}
    	
    	int matRes11 [][] = {
    			{ 0, -1, -1, -1, -1 },
        		{ 0, -1, -1, -1, -1 },
        		{ 0, -1, -1, -1, -1 },
        		{ 0, -1, -1, -1, -1 },
        		{ 0, 0, 0, 0, 0 }
        	};
    	if(idGrafo == 11){
    		return matRes11[i][j];
    	}
    	
    	int matRes12 [][] = {
    			{ 0, -1, -1, -1, -1 },
        		{ 0, -1, -1, -1, -1 },
        		{ 0, -1, -1, -1, -1 },
        		{ 0, -1, -1, -1, -1 },
        		{ 0, 0, 0, 0, 0 }
        	};
    	if(idGrafo == 12){
    		return matRes12[i][j];
    	}
    	
    	
    	int matRes13 [][] = {
    			{ 0, -1, -1, -1, -1, -1 },
    			{ 0, -1, -1, -1, -1, -1 },
    			{ 0, -1, -1, -1, -1, -1 },
    			{ 0, -1, -1, -1, -1, -1 },
    			{ 0, -1, -1, -1, -1, -1 },
        		{ 0, 0, 0, 0, 0, 0 }
        	};
    	if(idGrafo == 13){
    		return matRes13[i][j];
    	}
    	
    	
    	return -1;
    }
 
    public static void log(String string){
    	if(logActivated){
    		System.out.print(string);
    	}
    }
    
    public static void logln(String string){
    	if(logActivated){
    		System.out.println(string);
    	}
    }
    
    public static void log(int value){
    	if(logActivated){
    		System.out.print(value);
    	}
    }
    
    public static void mostrarMatriz(int mat1[][], int max){
		for (int i = 0; i < max; i++) {
			for (int j = 0; j < max; j++) {
				System.out.print(mat1[i][j]);
				System.out.print(" ");
			}
			System.out.println("");
		}
	}
}
