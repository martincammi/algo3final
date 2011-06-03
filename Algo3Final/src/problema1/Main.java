package problema1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import problema3.Main.Aplicacion;
import problema3.Main.Encargado;
import problema3.Main.Trabajo;

/**
 * Problema1 - Problema del pasaje de CD a cassette.
 * @author martincammi
 * @pendings: 
 * - Corregir el algoritmo
 * - Corregir el Procesor para que lea de linea de comandos.
 * - Escribir informe
 * - Hacer mediciones
 */
public class Main {

	public static boolean logActivated = false;
	static boolean showResultOnConsole = true;
	static List<CompactDisk> compacts = new ArrayList<CompactDisk>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		runTests();
		//runTests(6);
		//runForOnlineJudge();
	}

	public static void runTests(int testId){
		showResultOnConsole = false;
		List<CompactDisk> compacts = initiateCompacts();
		
		String resultado;
		CompactDisk compactDisk = compacts.get(testId);
		resultado = resolverProblemaMochila(compactDisk);
		check(resultado, compactDisk, testId);
	}
	
	public static void runTests(){
		showResultOnConsole = false;
		List<CompactDisk> compacts = initiateCompacts();
		
		String resultado;
		int testId = 0; 
		for (CompactDisk compactDisk : compacts) {
			resultado = resolverProblemaMochila(compactDisk);
			check(resultado, compactDisk, testId);
			testId++;
		}
	}
	
	public static void runForOnlineJudge(){
		Procesor.procesarDatos();
	}
	
	public static List<CompactDisk> initiateCompacts(){
		
		//List<CompactDisk> compacts = new ArrayList<CompactDisk>();
		compacts.add(new CompactDisk("5 3 1 3 4"));
		compacts.add(new CompactDisk("10 4 9 8 4 2"));
		compacts.add(new CompactDisk("20 4 10 5 7 4"));
		compacts.add(new CompactDisk("90 8 10 23 1 2 3 4 5 7"));
		compacts.add(new CompactDisk("45 8 4 10 44 43 12 9 8 2"));
		compacts.add(new CompactDisk("35 6 34 33 10 12 11 2"));
		compacts.add(new CompactDisk("6 5 1 2 3 4 5"));
		compacts.add(new CompactDisk("6 5 5 4 1 2 3"));
		compacts.add(new CompactDisk("5 3 5 3 2"));
		
		return compacts;
	}
	
	
	public static void check(String resultado, CompactDisk compactDisk, int testId){
		
		List<String> resultados = new ArrayList<String>();
		
		resultados.add("1 4 sum:5");
		resultados.add("8 2 sum:10");
		resultados.add("10 5 4 sum:19");
		resultados.add("10 23 1 2 3 4 5 7 sum:55");
		resultados.add("4 10 12 9 8 2 sum:45");
		resultados.add("10 12 11 2 sum:35");
		resultados.add("1 2 3 sum:6");
		resultados.add("1 2 3 sum:6");
		resultados.add("3 2 sum:5");
		
		String resultadoParaElTest = resultados.get(testId);
		if(resultadoParaElTest.equals(resultado)){
			System.out.println("Test" + testId + " Ok: ");
		}else{
			System.out.println("Test" + testId + " FALLO: " + resultado + " expected: " + resultados.get(testId));
			compactDisk.mostrarMatriz();
			System.out.println("");
			compactDisk.mostrarMatrizMigajas();
			compactDisk.mostrarSuma();
		}
	}
	/*
	private static int costo(int index){
		return cost[index-1];
	}*/
	
	public static String resolverProblemaMochila(CompactDisk converter){
		int maximo = converter.maximo; //Maximo tamaño de cinta "w" 
		int numCanciones = converter.numCanciones; //Cantidad de canciones "n" //Replace by pistas
		int [] canciones = converter.canciones; //Vector de costo/beneficio
		int [] cost = canciones;
		int [] ben = canciones;
		int usoMax [][] = converter.getUsoMax();
		int rastroMigajas [][] = converter.getRastroMigajas();
		
		List solucion = new ArrayList();
		
		for (int w = 1; w <= maximo; ++w){
			for (int i = 1; i < numCanciones+1; ++i){
				if(cost[i-1] <= w){
					
					int elemNuevo = usoMax[i-1][w-cost[i-1]] + cost[i-1];
					int elemAnter = usoMax[i-1][w];
					
					if(elemNuevo > elemAnter){
						
						usoMax[i][w] = elemNuevo;
						rastroMigajas[i][w] = 1 + rastroMigajas[i-1][w-cost[i-1]];
						//rastroMigajas[i][w] = 1;
					}else if (elemNuevo < elemAnter) {
						
						usoMax[i][w] = elemAnter;
						rastroMigajas[i][w] = rastroMigajas[i-1][w];
						//rastroMigajas[i][w] = 0;
					}else{
						
						usoMax[i][w] = usoMax[i-1][w];
						rastroMigajas[i][w] = Math.max(rastroMigajas[i-1][w-cost[i-1]], rastroMigajas[i-1][w]);
						//rastroMigajas[i][w] = 0;
					}
				}else{
					usoMax[i][w] = usoMax[i-1][w];
					rastroMigajas[i][w] = rastroMigajas[i-1][w]; 
				}
			}
		}
		/*
		int k = maximo;
		for (int i = numCanciones; i > 0 ; i--) {
			if(rastroMigajas[i][k] == 1){
				solucion.add(canciones[i-1]); 
				k = k - cost[i-1];
			}
		}
		*/
		
		int k = maximo;
		for (int i = numCanciones; i > 0 ; i--) {
			
			int sumaNueva = usoMax[i-1][k-cost[i-1]] + cost[i-1];
			int sumaAnter = usoMax[i-1][k];
			int rastroMigasAnter = rastroMigajas[i-1][k];
			int rastroMigasNuevo = rastroMigajas[i-1][k-cost[i-1]] + 1;
			
			if(sumaAnter < sumaNueva){
				solucion.add(canciones[i-1]);
				k = k - cost[i-1];
			}
			
			if(sumaAnter > sumaNueva){
				//skip
			}
			
			if(sumaAnter == sumaNueva){
				if(rastroMigasAnter < rastroMigasNuevo){
					solucion.add(canciones[i-1]);
					k = k - cost[i-1];
				}
			}
				
		}
		
		
		String solucionStr = "";
		for (int i = 0; i < solucion.size() ; i++) {
			solucionStr = solucion.get(i) + " " + solucionStr;
		}
		solucionStr = solucionStr + "sum:" + converter.getSuma();
		return solucionStr;
	}
	
	private static class CompactDisk{
		
		int maximo;
		int numCanciones;
		int [] canciones;
		int[][] usoMax;
		int[][] rastroMigajas;
		
		public CompactDisk(String data){
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
		
		public String getSuma(){
			return usoMax[numCanciones][maximo] + "";
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
	
    private static class Procesor{
    	
    	public static void procesarDatos() {
    		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    		
    		List<Aplicacion> aplicaciones = new ArrayList<Aplicacion>();
    		Encargado encargado;
 
	    		while(in.hasNextLine()){
	    			
	    			String linea = "esteValorSeraPisado"; 
	    			
	    			while (linea.length() > 0){
	    				
	    				linea = in.nextLine();
		    			String valores[] = linea.split(" ");
		    			
		    			if(linea.length() > 0){
		    			
		    				int cant = Integer.parseInt((valores[0]).charAt(1) + "");
			   				
			   				String name = ((valores[0]).charAt(0)+ "");
			   				String[] listaCompus = valores[1].split("");
			   				List<Integer> listaComputadoras = new ArrayList<Integer>();
			   				for (int i = 1; i < listaCompus.length-1; i++) {
			   					listaComputadoras.add(new Integer(listaCompus[i]));
							}
	
			   				Aplicacion aplicacion = new Aplicacion(cant, name, listaComputadoras);
			   				aplicaciones.add(aplicacion);
		    			}
		   				/*
		   				if(in.hasNextLine()){
		   					linea = in.nextLine();
		   					valores = linea.split(" ");
		   				}else{
		   					valores = new String[0];
		   				}*/
	    			}
	    			
	    			if(aplicaciones.size() > 0){
		    			Trabajo trabajo = new Trabajo(aplicaciones);
		    			encargado = new Encargado(trabajo);
		    			encargado.distribuirAplicaciones();
		    			aplicaciones.clear();
	    			}
	    			
	    		}
    		
   			in.close();
    	}
    }
}
