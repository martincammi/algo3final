package problemas;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Problema2 {

    public static void main(String[] args) {
    	
    	Converter converter = new Converter("7 0 1 0 2 0 4 2 4 2 3 3 1 4 3");
    	int max = converter.getMax();
    	int matRes[][] = converter.getMatAdj();
    	for (int i = 0; i < max+1;  i++) {
			matRes = converter.multiplicarMatrices(matRes,converter.getMatAdj());
		}
    	converter.mostrarMatriz(matRes);
    	//enter();
    	//converter.mostrarMatriz(mat1);
     
    }

    private static class Converter{
    	
    	int ejes;
		int[][] matAdj;
		int max;
    	
    	public Converter(String data){
			String[] numbers = data.split(" ");
			ejes = Integer.parseInt(numbers[0]);
			
			max = 0;
			for (int i = 0;  i < numbers.length; i++) {
				int nodo = Integer.parseInt(numbers[i]);
				if(nodo > max){
					max = nodo;
				}
			}

			matAdj = new int [max+1][max+1];
			
			for (int i = 1;  i < numbers.length-1; i++) {
				int f = Integer.parseInt(numbers[i]);
				int c = Integer.parseInt(numbers[i+1]);
				matAdj[f][c] = 1;
			}
		}

		public int getEjes() {
			return ejes;
		}

		public void setEjes(int ejes) {
			this.ejes = ejes;
		}

		public int[][] getMatAdj() {
			return matAdj;
		}

		public void setMatAdj(int[][] matAdj) {
			this.matAdj = matAdj;
		}
		
		public int getMax() {
			return max;
		}

		public void setMax(int max) {
			this.max = max;
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
			int matRes[][] = new int[max+1][max+1];
			
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
    }
    
    public static void enter(){
    	System.out.println("");
    }
        
}
