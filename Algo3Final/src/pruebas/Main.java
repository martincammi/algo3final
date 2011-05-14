package pruebas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//Problema2 Probando
public class Main {

    public static void main(String[] args) {
    	
    	String datos = Consola.leerDatos2();
    	Parser parser = new Parser(datos);
    	sendInfo();
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
    	
    	 public static String leerDatos2() {
			Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
			
			String datos = "";
				while(in.hasNext()){
					datos = datos + in.nextLine() + " ";
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
    		//while(index < datos.length()){
    			ejes = Integer.parseInt(datos.charAt(index) + "");
    			dato = datos.substring(index, index + (ejes *(CANT_SPACES + CANT_ELEMS) + 1));
        		cities.add(new City(dato,name));
    			index = index + dato.length() + EXTRA_SPACE;
    			name++;
			//}
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
    
    public static void sendInfo() {
    	try{
    		
		 URL yahoo = new URL("http://www.yahoo.com/");
	        URLConnection yc = yahoo.openConnection();
	        BufferedReader in = new BufferedReader(
	                                new InputStreamReader(
	                                yc.getInputStream()));
	        String inputLine;

	        while ((inputLine = in.readLine()) != null) 
	            System.out.println(inputLine);
	        in.close();
    	}
    	catch (Exception e) {
		}

	 }
    
}
