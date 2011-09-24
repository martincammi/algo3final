package problema2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Arbol extends Grafo {

	private Map<Integer,Integer> cantHijos; //cantidad de hijos de un nodo (se incluye a si mismo)
	private ArrayList hojas; //lista de nodos hoja.
	private int[] preorder;
	
	private int raiz;
	
	public Arbol(int n){
		super(n);
		hojas = new ArrayList();
		preorder = new int[cantNodos];
		cantHijos = new HashMap<Integer,Integer>();
	}
	
	public Arbol(int[] paresAdyacencias){
		super(paresAdyacencias);
		hojas = new ArrayList();
		preorder = new int[cantNodos];
		cantHijos = new HashMap<Integer,Integer>();
	}
	
	public void agregarHoja(int hoja){
		hojas.add(hoja);
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
	
	
	public void agregarEje(int nodo1, int nodo2){
		agregarAdyacencia(nodo1,nodo2);
		//agregarAdyacencia(nodo2,nodo1);
		cantEjes++;
	}
	
	public ArrayList getHojas(){
		return hojas;
	}
	
	/**
	 * Suma una cantidad de hijos a la cantidad de hijos ya existente de nodo
	 * @param nodo el nodo en cuestion
	 * @param suma la cantidad a agregar
	 */
	public void sumarHijos(int nodo, int suma){
		
		if(cantHijos.get(nodo) == null){
			cantHijos.put(nodo,new Integer(0)); 
		}
		
		Integer antiguoValor = cantHijos.get(nodo); 
		Integer nuevoValor = antiguoValor + suma;
		cantHijos.put(nodo, nuevoValor);
	}
	
	public int cantHijos(int nodo){
		if(cantHijos.get(nodo) == null){
			cantHijos.put(nodo, new Integer(0));
		}
		return cantHijos.get(nodo); 
	}
	
	
	public void showPreorder(){
		System.out.println(getPreorder());
	}
	
	public String getPreorder(){
		String res = "";
		for (int i = 0; i < etiquetas.length; i++) {
			res = res + (etiquetas[i] + ",");
		}
		return res;
	}
	
	public void showHojas(){
		System.out.println(getTheHojas());
	}
	
	public String getTheHojas(){
		String res = "";
		for (int i = 0; i < hojas.size(); i++) {
			res =res + (hojas.get(i) + ",");
		}
		return res;
	}
	
	public void showCantHijos(){
		System.out.println(getCantHijos());
	}
	
	public String getCantHijos(){
		String res = "";
		for (int i = 0; i < cantHijos.size(); i++) {
			res = res + (i + "," + cantHijos.get(i) + " | ");
		}
		return res;
	}
}
