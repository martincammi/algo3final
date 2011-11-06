package grasp;

public class GeneradorGrafos {

	private static int SUMIDERO_MAX_NODOS  = 5;
	
	public static void main(String[] args) {
	
		Grafo grafo = generarGrafoOrSumidero();
		System.out.println("finish");
		
	}
	
	/**
	 * Genera un grafo en el que un nodo inicial se relaciona con varios otros
	 * y los varios otros se relacionan solamente con un nodo final
	 * y el nodo final se relaciona solo con el nodo inicial.
	 * @return
	 */
	public static Grafo generarGrafoOrSumidero(){
		
		int cantNodos = (int)(Math.random()*SUMIDERO_MAX_NODOS)+3; //Al menos deberia haber 3.
		int cantArcos = (cantNodos-2)*2 + 1; //Los ejes in y out de los del medio y el del final al primero.
		
		String[] params = new String[(cantArcos+1)*3];
		params[0] = String.valueOf(cantNodos);
		params[1] = String.valueOf(0);
		params[2] = String.valueOf(cantArcos);
		
		int nextIndex = 0;
		for (int i = 0; i < cantArcos-1; i=i+2) {
			int index = 3 + i*3;
			params[index] = String.valueOf(0);
			params[index+1] = String.valueOf((i/2)+1);
			params[index+2] = String.valueOf(1);
			
			index = 3 + (i+1)*3;
			params[index] = String.valueOf((i/2)+1);
			params[index+1] = String.valueOf(cantNodos-1);
			params[index+2] = String.valueOf(1);
			nextIndex = index+3;
		}
		
		params[nextIndex] = String.valueOf(cantNodos-1);
		params[nextIndex+1] = String.valueOf(0);
		params[nextIndex+2] = String.valueOf(1);
		
		return new Grafo(params);
		
	}
}
