package problema2;

import java.util.ArrayList;

public class Arbol extends Grafo {

	private int cantHijos[]; //cantidad de hijos de un nodo (se incluye a si mismo)
	private ArrayList hojas; //lista de nodos hoja.
	private int[] preorder;
	private int[] etiquetas; //Etiqueta a los nodos.
	private int raiz;
	
	public Arbol(int n){
		super(n);
		hojas = new ArrayList();
		preorder = new int[cantNodos];
		etiquetas = new int[cantNodos];
	}
	
	public Arbol(int[] paresAdyacencias){
		super(paresAdyacencias);
		hojas = new ArrayList();
		preorder = new int[cantNodos];
		etiquetas = new int[cantNodos];
	}
	
	public int crearRaiz(){
		int nodo = getNodo();
		setRaiz(nodo);
		return nodo;
	}
	
	public void setRaiz(int nodo){
		raiz = nodo;
	}
	
	public int getRaiz(){
		return raiz;
	}
	
	public boolean esRaiz(int nodo){
		return (nodo == getRaiz());
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
	
	public void agregarEje(int nodo1, int nodo2){
		agregarAdyacencia(nodo1,nodo2);
		//agregarAdyacencia(nodo2,nodo1);
		cantEjes++;
	}
	
	public ArrayList getHojas(){
		return hojas;
	}
	
	public void addHijo(int nodo){
		cantHijos[nodo]++;
	}
	
	public void getPreorder(){
		for (int i = 0; i < etiquetas.length; i++) {
			System.out.print(etiquetas[i] + ",");
		}
		System.out.println();
	}
	
}
