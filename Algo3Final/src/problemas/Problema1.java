package problemas;

import java.util.ArrayList;
import java.util.List;

public class Problema1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Converter converter1 = new Converter("5 3 1 3 4");
		//resolverProblemaMochila(converter1);
		//Converter converter2 = new Converter("10 4 9 8 4 2");
		//resolverProblemaMochila(converter2);
		/*Converter converter3 = new Converter("20 4 10 5 7 4");
		resolverProblemaMochila(converter3);
		Converter converter4 = new Converter("90 8 10 23 1 2 3 4 5 7");
		resolverProblemaMochila(converter4);*/
		//Converter converter5 = new Converter("45 44 43 12 9 8 2");
		//resolverProblemaMochila(converter5);
		//Converter converter6 = new Converter("35 6 34 33 10 12 11 2");
		Converter converter6 = new Converter("6 5 5 4 1 2 3"); //Deberia elejir 1,2,3
		//Converter converter6 = new Converter("6 5 1 2 3 4 5"); //Deberia elejir 1,2,3
		resolverProblemaMochila(converter6);



		
	}

	public static void resolverProblemaMochila(Converter converter){
		int W = converter.maximo; //Maximo tamaño de cinta "w" 
		int numCanciones = converter.numCanciones; //Cantidad de canciones "n" //Replace by pistas
		int [] canciones = converter.canciones; //Vector de costo/beneficio
		int [] cost = canciones;
		int [] ben = canciones;
		int usoMax [][] = converter.getUsoMax();
		int rastroMigajas [][] = converter.getRastroMigajas();
		
		List solucion = new ArrayList();
		
		for (int w = 1; w <= W; ++w){
			for (int i = 1; i < numCanciones+1; ++i){
				if(cost[i-1] <= w){
					if(usoMax[i-1][w-cost[i-1]] + cost[i-1] > usoMax[i-1][w]){
						usoMax[i][w] = usoMax[i-1][w-cost[i-1]] + cost[i-1];
						rastroMigajas[i][w] = 1;
					}else if (usoMax[i-1][w-cost[i-1]] + cost[i-1] < usoMax[i-1][w]) {
						usoMax[i][w] = usoMax[i-1][w];
						rastroMigajas[i][w] = 0;
					}else{
						//if(rastroMigajas[i-1][w-cost[i-1]] = 0)
							usoMax[i][w] = usoMax[i-1][w];
						rastroMigajas[i][w] = 0;
					}
				}else{
					usoMax[i][w] = usoMax[i-1][w];
				}
			}
		}

		converter.mostrarMatriz();
		System.out.println("");
		converter.mostrarMatrizMigajas();
		converter.mostrarSuma();

		int k = W;
		StringBuffer sb = new StringBuffer(); 
		for (int i = numCanciones; i > 0 ; i--) {
			if(rastroMigajas[i][k] == 1){
				solucion.add(canciones[i-1]); 
				k = k - cost[i-1];
			}
		}
		for (int i = solucion.size()-1; i >= 0 ; i--) {
			System.out.print(solucion.get(i));
			System.out.print(" ");
		}
		System.out.println("");
	}
	
	public static class Converter{
		
		int maximo;
		int numCanciones;
		int [] canciones;
		int[][] usoMax;
		int[][] rastroMigajas;
		
		public Converter(String data){
			String[] numbers = data.split(" ");
			maximo = Integer.parseInt(numbers[0]);
			numCanciones = Integer.parseInt(numbers[1]);
			canciones = new int [numCanciones];
			usoMax = new int [numCanciones+1][maximo+1]; 
			rastroMigajas = new int[numCanciones+1][maximo+1];
			
			for (int i = 2, j = 0; i < numbers.length; i++, j++) {
				canciones[j] = Integer.parseInt(numbers[i]);
			}
		}
		
		public void mostrarSuma(){
			System.out.println("Sum: " + usoMax[numCanciones][maximo]);
		}
		
		public int[][] getUsoMax(){
			return usoMax;
		}
		
		public int[][] getRastroMigajas(){
			return rastroMigajas;
		}

		public int getMaximo() {
			return maximo;
		}

		public void setMaximo(int maximo) {
			this.maximo = maximo;
		}

		public int getNumCanciones() {
			return numCanciones;
		}

		public void setNumCanciones(int numCanciones) {
			this.numCanciones = numCanciones;
		}

		public int[] getCanciones() {
			return canciones;
		}

		public void setCanciones(int[] canciones) {
			this.canciones = canciones;
		}
		
		public void mostrarMatriz(){
			for (int i = 1; i < numCanciones+1; i++) {
				for (int j = 1; j < maximo+1; j++) {
					System.out.print(usoMax[i][j]);
					System.out.print("\t");
				}
				System.out.println("");
			}
		}
		
		public void mostrarMatrizMigajas(){
			for (int i = 1; i < numCanciones+1; i++) {
				for (int j = 1; j < maximo+1; j++) {
					System.out.print(rastroMigajas[i][j]);
					System.out.print("\t");
				}
				System.out.println("");
			}
		}
		
		public void mostrarSolucion(int[] solucion){
			
			for (int i = 0; i < solucion.length; i++) {
					if(solucion[i] != 0){
						System.out.print(solucion[i]);		
						System.out.print(" ");
					}
			}
		}
	}
	
}
