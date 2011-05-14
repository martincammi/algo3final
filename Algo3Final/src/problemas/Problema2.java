package problemas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Problema2 {

    public static void main(String[] args) {
    	
    	//City city = new City("7 0 1 0 2 0 4 2 4 2 3 3 1 4 3");
    	//City city = new City("5 0 2 0 1 1 5 2 5 2 1");
    	//City city = new City("9 0 1 0 2 0 3 0 4 1 4 2 1 2 0 3 0 3 1");
    	String datos = Consola.leerDatos();
    	Parser parser = new Parser(datos);
    	
    	
    	for (City city : parser.getCities()) {
    		obtenerCaminos(city);
		}

    }
    
    private static void obtenerCaminos(City city){
    	//Converter converter = new Converter(datos);
    	
    	int max = city.getMax();
    	int matAdj[][] = city.getMatAdj();
    	int matCaminoI[][] = city.copyMatriz(matAdj);
    	int matSuma[][] = city.copyMatriz(matAdj);
    	
    	for (int i = 1; i < max+1;  i++) {
    		matCaminoI = city.multiplicarMatrices(matCaminoI,matAdj);
			matSuma = city.sumarMatrices(matSuma,matCaminoI);
		}
    	
    	System.out.println("matrix for city " + city.getName());
    	city.marcarCaminosInfinitos(matSuma);
    	city.mostrarMatriz(matSuma);
    }
    
    private static class Consola{
    	
    	public static String leerDatos() {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		String datos = "";
    		String aux;
    		try{
    			aux = br.readLine();
    		
	    		while (aux!=null && !aux.equals("")){
	    			datos = datos + aux + " ";
	    			aux = br.readLine(); 
	    		}
    		}
    		catch(IOException e){
    			aux = null;
    		}
    		return datos;
    	}
    }

    private static class Parser{
    
    	private List<City> cities = new ArrayList();
    	
    	public Parser(String datos){
    		int EXTRA_SPACE = 1;
    		int CANT_SPACES = 2;
    		int CANT_ELEMS = 2;
    		
    		String dato;
    		int ejes;
    		int index = 0;
    		int name = 0;
    		while(index < datos.length()){
    			ejes = Integer.parseInt(datos.charAt(index) + "");
    			dato = datos.substring(index, index + (ejes *(CANT_SPACES + CANT_ELEMS) + 1));
        		cities.add(new City(dato,name));
    			index = index + dato.length() + EXTRA_SPACE;
    			name++;
			}
    	}
    	
    	public List<City> getCities(){
    		return cities;
    	}
    }
    
    private static class City{
    	
    	int name;
    	int ejes;
		int[][] matAdj;
		int max;
    	
		public City(String data){
			this(data,0);
		}
		
    	public City(String data, int name){
    		this.name = name;
			String[] numbers = data.split(" ");
			ejes = Integer.parseInt(numbers[0]);
			
			max = 0;
			for (int i = 1;  i < numbers.length; i++) {
				int nodo = Integer.parseInt(numbers[i]);
				if(nodo > max){
					max = nodo;
				}
			}
			max = max + 1;
			
			matAdj = new int [max][max];
			
			for (int i = 1;  i < numbers.length-1; i=i+2) {
				int f = Integer.parseInt(numbers[i]);
				int c = Integer.parseInt(numbers[i+1]);
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
					System.out.print("\t");
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
		
		@Deprecated
		public boolean isMatrizCero(int mat[][]){
			for (int i = 0; i < max; i++) {
				for (int j = 0; j < max; j++) {
					if((mat[i][j]!=0)){
						return false;
					}
				}
			}
			return true;
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
