package utils;

public class FuertementeConexo {
	public static boolean fuertementeConexo(Grafo G){
		boolean ret = false;
		ListaInt[] aristas = new ListaInt[G.getCantNodos()];
		ListaInt[] arcosOriginales = new ListaInt[G.getCantNodos()];
		ListaInt[] arcosInvertidos = new ListaInt[G.getCantNodos()];
		boolean[] visitados = new boolean[G.getCantNodos()];
		for(int i=0; i < G.getCantNodos(); ++i){
			arcosOriginales[i] = new ListaInt();
			arcosInvertidos[i] = new ListaInt();
			aristas[i] = new ListaInt();
		}
		
		for(int i=0; i < G.getCantNodos(); ++i){
			for(int j:G.adyacentesOr(i)){
				arcosOriginales[i].add(j);
				arcosInvertidos[j].add(i);
			}
			for(int j:G.adyacentesNoOr(i)){
				aristas[i].add(j);
			}
		}
		//calculo la ida del DFS
		resetVisitados(visitados);
		visitarNodos(0,aristas,arcosOriginales,visitados);
		ret = checkVisitados(visitados);
		if(ret){
			resetVisitados(visitados);
			visitarNodos(0,aristas,arcosInvertidos,visitados);
			ret = checkVisitados(visitados);
		}
		return ret;
	}

	private static void visitarNodos(int i, ListaInt[] aristas, ListaInt[] arcos,boolean[] visitados) {
		for(int k:aristas[i]){
			if(!visitados[k]){
				visitados[k]=true;
				visitarNodos(k, aristas, arcos, visitados);
			}
		}
		for(int k:arcos[i]){
			if(!visitados[k]){
				visitados[k]=true;
				visitarNodos(k, aristas, arcos, visitados);
			}
		}
	}

	private static boolean checkVisitados(boolean[] visitados) {
		int i = 0;
		while(i < visitados.length && visitados[i]){
			++i;
		}
		return i== visitados.length;
	}

	private static void resetVisitados(boolean[] visitados) {
		for(int i = 0; i < visitados.length; ++i){
			visitados[i] = false;
		}
		visitados[0]=true;
	}
}
