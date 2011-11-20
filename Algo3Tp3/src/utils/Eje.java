package utils;

public class Eje {

	private int nodo1;
	private int nodo2;
	private int peso;
	
	public Eje(){}
	
	public Eje(int nodo1, int nodo2, int peso){
		this.nodo1 = nodo1;
		this.nodo2 = nodo2;
		this.peso = peso;
	}
	
	public Eje(Eje eje) {
		this.nodo1 = eje.nodo1;
		this.nodo2 = eje.nodo2;
		this.peso = eje.peso;
	}

	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public int getNodo1() {
		return nodo1;
	}
	public void setNodo1(int nodo1) {
		this.nodo1 = nodo1;
	}
	public int getNodo2() {
		return nodo2;
	}
	public void setNodo2(int nodo2) {
		this.nodo2 = nodo2;
	}
	
	@Override
	public String toString() {
		return "(" + nodo1 + "," + nodo2 + "," + peso + ")";
	}
}
