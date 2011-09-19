package problema2;

import java.util.ArrayList;

public class Arbol extends Grafo {

	private int cantHijos[]; //cantidad de hijos de un nodo (se incluye a si mismo)
	private ArrayList hojas; //lista de nodos hoja.

	public Arbol(int n){
		super(n);
		hojas = new ArrayList();
	}
	
	public Arbol(int[] paresAdyacencias){
		super(paresAdyacencias);
		hojas = new ArrayList();
	}
	
	public void agregarEje(int nodo1, int nodo2){
		agregarAdyacencia(nodo1,nodo2);
		agregarAdyacencia(nodo2,nodo1);
		if(esHoja(nodo1)){
			hojas.add(nodo1);
		}else{
			hojas.remove(new Integer(nodo1));
		}
		
		if(esHoja(nodo2)){
			hojas.add(nodo2);
		}else{
			hojas.remove(new Integer(nodo2));
		}
		cantEjes++;
	}
	
	public ArrayList getHojas(){
		return hojas;
	}
}
