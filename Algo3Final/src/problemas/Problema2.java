package problemas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

//Problema2
public class Problema2 {

    public static void main(String[] args) {
    	
    	//City city = new City("7 0 1 0 2 0 4 2 4 2 3 3 1 4 3");
    	//City city = new City("5 0 2 0 1 1 5 2 5 2 1");
    	//City city = new City("9 0 1 0 2 0 3 0 4 1 4 2 1 2 0 3 0 3 1");
    	//City city = new City("10 0 1 0 2 0 3 0 4 0 5 0 6 0 7 0 8 0 9");
    	Procesor.procesarDatos();

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
