package problema2;

import java.util.ArrayList;

public class Grafo {
	
	public static int NO_HAY_MAS_ADYACENTES = -1;  
	
	private int cantNodos;
	protected int cantEjes;
	private int raiz;

	private ArrayList[] adyacencias; //Guarda dado un nodo sus adyacencias.
	private int[] proximoAdyacente; //Puntero a un nodo adyacente de un nodo dado.
	private boolean[] marcado; //Indica si un vertice esta marcado.
	private boolean[][] ejes; //Indica que ejes que estan en el arbol generador.
	private int[] etiquetas; //Etiqueta a los nodos.
	
	
	public Grafo(int n){
		cantNodos = n;
		adyacencias = new ArrayList[cantNodos];
		proximoAdyacente = new int[cantNodos];
		marcado = new boolean[cantNodos];
		ejes = new boolean[cantNodos][cantNodos];
	}
	
	public Grafo(int[] paresAdyacencias){
		cantNodos = paresAdyacencias[0];
		cantEjes = paresAdyacencias[1];
		
		adyacencias = new ArrayList[cantNodos];
		proximoAdyacente = new int[cantNodos];
		marcado = new boolean[cantNodos];
		ejes = new boolean[cantNodos][cantNodos];
		
		for (int i = 2; i < paresAdyacencias.length; i=i+2) {
			
			int par1 = paresAdyacencias[i];
			int par2 = paresAdyacencias[i+1];
			
			agregarAdyacencia(par1, par2);
			agregarAdyacencia(par2, par1);
		}
	}
	
	public int getCantNodos(){
		return cantNodos;
	}
	
	protected void agregarAdyacencia(int par1, int par2) {
		if(adyacencias[par1] == null){
			adyacencias[par1] = new ArrayList();
		}
		adyacencias[par1].add(par2);
	}
	
	public int getNodo(){
		return 0;
	}
	
	public boolean esHoja(int nodo){
		return (getGrado(nodo) == 1);
	}

	public int getGrado(int nodo){
		return adyacencias[nodo].size();
	}
	
	public boolean esRaiz(int nodo){
		return (nodo == getRaiz());
	}
	
	//TODO REFACTOR: hacer mas legible
	/**
	 * Devuelve los adyacentes de un nodo en orden, devuelve el próximo adyacente. 
	 */
	public int proximoAdyacente(int nodo){
		int proximo = proximoAdyacente[nodo];
		
		if(proximo == cantAdyacentes(nodo)){
			proximoAdyacente[nodo] = 0;
			return NO_HAY_MAS_ADYACENTES;
		}

		Integer adyacente = (Integer) adyacencias[nodo].get(proximo);
		proximoAdyacente[nodo]++;

		return adyacente;
	}
	
	/**
	 * Devuelve la cantidad de adyacentes de un nodo
	 * @param nodo del que queremos conocer la cantidad de adyacentes
	 * @return
	 */
	private int cantAdyacentes(int nodo){
		return adyacencias[nodo].size();
	}
	
	public void setRaiz(int nodo){
		raiz = nodo;
	}
	
	public int getRaiz(){
		return raiz;
	}
	
	public boolean estaMarcado(int nodo){
		return marcado[nodo];
	}
	
	public void marcarNodo(int nodo){
		marcado[nodo] = true;
	}
	
	public void desMarcarNodo(int nodo){
		marcado[nodo] = false;
	}
	
	public boolean estaMarcado(int nodo1, int nodo2){
		return ejes[nodo1][nodo2];
	}
	
	public void marcarEje(int nodo1, int nodo2){
		ejes[nodo1][nodo2] = true;
	}
	
	public void desMarcarEje(int nodo1, int nodo2){
		ejes[nodo1][nodo2] = false;
	}

	public void marcarYEtiquetarNodo(int nodo, int etiqueta){
		marcado[nodo] = true;
		etiquetas[nodo] = etiqueta;
	}
	
	public void etiquetarNodo(int nodo, int etiqueta){
		etiquetas[nodo] = etiqueta;
	}
	
	public int etiqueta(int nodo){
		return etiquetas[nodo];
	}
	
	public boolean estaEtiquetado(int nodo){
		return etiquetas[nodo] != Etiquetador.ETIQUETA_NULA;
	}
	
	public void mostrarEjesMarcados(){
		for (int i = 0; i < ejes.length; i++) {
			for (int j = 0; j < ejes.length; j++) {
				if(ejes[i][j]){
					System.out.print("(" + i + "," + j + ")");
				}
			}
		}
		System.out.println("");
	}
}
