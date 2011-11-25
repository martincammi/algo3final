package utils;

public class Dijkstra {
// Dijkstra's algorithm to find shortest path from s to all other nodes
	public static int [] dijkstra (Grafo G, int s) {
		final int pesos[][] = G.getPesoAristas();
		final int [] dist = new int [G.getCantNodos()];  // shortest known distance from "s"
		final int [] pred = new int [G.getCantNodos()];  // preceeding node in path
		final boolean [] visited = new boolean [G.getCantNodos()]; // all false initially

		for (int i=0; i<dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
			Grafo.complex++;
		}
		dist[s] = 0;

		for (int i=0; i < dist.length; i++) {
			final int next = minVertex (dist, visited);
			visited[next] = true;
			Grafo.complex++;
			// The shortest path to next is dist[next] and via pred[next].

			final ListaInt adyacentesOr = G.adyacentesOr(next);
			for (Integer j: adyacentesOr) {
				Grafo.complex++;
				final int d = dist[next] + pesos[next][j];
				if (dist[j] > d) {
					dist[j] = d;
					pred[j] = next;
				}
			}
			final ListaInt adyacentesNoOr = G.adyacentesNoOr(next);
			for (Integer j: adyacentesNoOr) {
				Grafo.complex++;
				final int d = dist[next] + pesos[next][j];
				if (dist[j] > d) {
					dist[j] = d;
					pred[j] = next;
				}
			}
		}
		return pred;  // (ignore pred[s]==0!)
	}
 
	private static int minVertex (int [] dist, boolean [] v) {
		int x = Integer.MAX_VALUE;
		int y = -1;   // graph not connected, or no unvisited vertices
		for (int i=0; i<dist.length; i++) {
			Grafo.complex++;
			if (!v[i] && dist[i]<x) {
				y=i;
				x=dist[i];
			}
		}
		return y;
	}
 
	public static ListaInt getPath (Grafo G, int [] pred, int s, int e){
		final ListaInt path = new ListaInt();
		int x = e;
		while (x!=s){
			Grafo.complex++;
			path.add (0, x);
			x = pred[x];
		}
		path.add (0, s);
		return path;
	}

}
