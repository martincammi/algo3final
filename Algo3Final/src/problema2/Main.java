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

    public static void main(String[] args) {
    	
    	//City city = new City("7 0 1 0 2 0 4 2 4 2 3 3 1 4 3");
    	//City city = new City("5 0 2 0 1 1 5 2 5 2 1");
    	//City city = new City("9 0 1 0 2 0 3 0 4 1 4 2 1 2 0 3 0 3 1");
    	//City city = new City("10 0 1 0 2 0 3 0 4 0 5 0 6 0 7 0 8 0 9");
    	//Procesor.procesarDatos();
		Grafo grafo = new Grafo();
		grafo.agregarEje(0,1);
		grafo.agregarEje(0,2);
		grafo.agregarEje(1,2);
		grafo.agregarEje(1,3);
		grafo.agregarEje(2,3);
		
    	PathFinder encuentraCaminos = new PathFinder(grafo);
    	encuentraCaminos.calcularCaminos();
    	encuentraCaminos.mostrarCaminos();

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
    	
    	System.out.println("matrix for city " + city.getName());
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

        public boolean hayEje(String node1, String node2) {
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
    	
    	public PathFinder(Grafo grafo){
    		this.grafo = grafo;
    	}
    	
    	public void calcularCaminos(){
    		List<Integer> nodos = new ArrayList<Integer>();
    		nodos.add( grafo.unNodo() );
    		calcularCaminos( nodos );
    	}
    	
    	public void calcularCaminos(List<Integer> visitados){
    		
    		int ultimo = visitados.size()-1;
    		Integer primerNodo = visitados.get(ultimo);
    		
    		List<Integer> adyacentes = grafo.adyacentes(primerNodo);
    		
    		for (Integer nodo : adyacentes) {
				//Encontramos un ciclo
    			if(visitados.contains(nodo)){
					
				}else{
					matCaminos[primerNodo][nodo] = 1;
					visitados.add()
					calcularCaminos();
				}
			}
    	}
    	
    	public void mostrarCaminos(){
			for (int i = 0; i < grafo.cantNodos; i++) {
				for (int j = 0; j < grafo.cantNodos; j++) {
					System.out.print(matCaminos[i][j]);
					System.out.print(" ");
				}
				System.out.println("");
			}
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
					System.out.print(matAdj[i][j]);
					System.out.print("\t");
				}
				System.out.println("");
			}
		}
		
		public void mostrarMatriz(int mat[][]){
			for (int i = 0; i < max; i++) {
				for (int j = 0; j < max; j++) {
					System.out.print(mat[i][j]);
					System.out.print(" ");
				}
				System.out.println("");
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
    	System.out.println("");
    }
        
}
