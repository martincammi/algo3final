package utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Stack;

public class CircuitoEuleriano {
	public static ListaInt circuito;
	public static Stack< Queue <Integer> > circuitosParciales;
	public static int[] visitas;
	public static ListIterator[] adyacentes;
	
	public static void inicializar(Grafo G){
		circuito = new ListaInt();
		circuitosParciales = new Stack< Queue <Integer> >();
		visitas = new int[G.getCantNodos()];
		adyacentes = new ListIterator[G.getCantNodos()];
		for(int i = 0; i < G.getCantNodos();++i){
			visitas[i]=0;
			adyacentes[i]=G.adyacentesOr(i).listIterator();
		}
	}
	
	public static ListaInt encontrarCircuitoEuleriano(Grafo G){
		inicializar(G);
		int nodoInicial = 3;
		//circuitosParciales.add(new ListaInt());
		circuitosParciales.push(new LinkedList<Integer>());
		//circuitosParciales.get(0).add(nodoInicial);
		circuitosParciales.peek().offer(nodoInicial);
		while(!circuitosParciales.isEmpty()){
			int nodoCorteActual = circuitosParciales.peek().element();
			int nodo = nodoCorteActual;
			//while(G.adyacentesOr(nodoCorteActual).size() > 0){
			//armo un circuito
			while(visitas[nodoCorteActual] < G.dout[nodoCorteActual]){
				int ady = (Integer)adyacentes[nodo].next();
				circuitosParciales.peek().offer(ady);
				nodo = ady;
				visitas[nodo]++;
			}
			//voy agregando los que no le queden aristas sin revisar a la solucion
			while((!circuitosParciales.peek().isEmpty()) && (visitas[circuitosParciales.peek().element()] == G.dout[circuitosParciales.peek().element()])){
				circuito.add(circuitosParciales.peek().element());
				circuitosParciales.peek().remove();
			}
			if(circuitosParciales.peek().isEmpty()){//se acabo este circuito
				circuitosParciales.pop();
			}else{//en el nodo donde quedo todavia tiene para salir
				nodoCorteActual = circuitosParciales.peek().element();
				circuitosParciales.peek().remove();
				circuitosParciales.push(new LinkedList<Integer>());
				circuitosParciales.peek().offer(nodoCorteActual);
			}		
		}				
		return circuito;
	}
	
	public static ArrayList<Eje> encontrarCaminosDeRepetidos(ListaInt circuito, Grafo G){
		ArrayList<Eje> ret = new ArrayList<Eje>();
		int nodoInicio, nodoFin, nodo;
		int pesoCamino;
		int	i,j = 0;
		ListIterator<Integer> li = circuito.listIterator();
		nodo = nodoFin = li.next();//hay un proximo porque es el primero
		//Busco la primer arista que se repita
		boolean continua = true;
		i = j;
		pesoCamino = 0;
		while(li.hasNext() && continua){
			nodo = nodoFin;
			nodoFin = li.next();
			++i;
			continua = !estaRepetida(circuito,i,nodo,nodoFin);
		}
		if(continua){//no encontre entonces el grafo original era euleriano
			//termina
		}else{
			nodoInicio = nodo;
			pesoCamino+= G.getPesosEjes()[nodo][nodoFin];
			j = i;
			while(li.hasNext() && continua){
				nodo = nodoFin;
				nodoFin = li.next();
				++j;
				continua = estaRepetida(circuito,j,nodo,nodoFin);
			}
		}
		return ret;
	}
	
	private static boolean estaRepetida(ListaInt circuito, int i, int nodo1, int nodo2) {
		return true;
	}
}
