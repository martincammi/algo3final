package contructiva;

public class Grafo {
	public static long complex = 0;
	public long T = 0;
	protected int cantNodos;
	protected int cantAristas; //No orientadadas
	protected int cantArcos;  //Orientadas
	protected ListaInt[] adyacenciasNoOr; //Guarda dado un nodo sus adyacencias no orientadas.
	protected ListaInt[] adyacenciasOr; //Guarda dado un nodo sus adyacencias orientadas.
	protected int[][] pesosEjes;
	protected ListaInt[] adyacenciasVisitados;
	
	
	//TODO verificar si se vuela
	public Grafo(int n){
		cantNodos = n;
		cantAristas = 0;
		cantArcos = 0;
		adyacenciasNoOr = new ListaInt[cantNodos];
		adyacenciasOr = new ListaInt[cantNodos];
		adyacenciasVisitados = new ListaInt[cantNodos];
		pesosEjes = new int[cantNodos][cantNodos];
	}
	
	public final int getCantNodos() {
		return cantNodos;
	}
	
	public final int cantBits(int i){
		return (int)Math.floor((Math.log10((i==0?1:i ))/Math.log10(2)) + 1) ;
	}
	
	public Grafo(String[] paresAdyacencias){
		T=0;
		complex=0;
		cantNodos =Integer.parseInt(paresAdyacencias[0]);
		cantAristas = Integer.parseInt(paresAdyacencias[1]);
		cantArcos = Integer.parseInt(paresAdyacencias[2]);
		T+= cantBits(cantNodos);
		T+= cantBits(cantAristas);
		T+= cantBits(cantArcos);
		adyacenciasNoOr = new ListaInt[cantNodos];
		adyacenciasOr = new ListaInt[cantNodos];
		adyacenciasVisitados = new ListaInt[cantNodos];
		pesosEjes = new int[cantNodos][cantNodos];
		
		for (int i = 0; i < cantNodos; i++) {
			for (int j = 0; j < cantNodos; j++) {
				pesosEjes[i][j] = -1;
			}
			adyacenciasNoOr[i] = new ListaInt();
			adyacenciasOr[i] = new ListaInt();
			adyacenciasVisitados[i] = new ListaInt();
		}
		
		for (int i = 3; i < cantAristas; i=i+3) {
			
			int par1 = Integer.parseInt(paresAdyacencias[i]);
			int par2 = Integer.parseInt(paresAdyacencias[i+1]);
			int peso = Integer.parseInt(paresAdyacencias[i+2]);
			
			T+= cantBits(par1);
			T+= cantBits(par2);
			T+= cantBits(peso);
			
			agregarAdyacencia(par1, par2);
			agregarAdyacencia(par2, par1);
			pesosEjes[par1][par2] = peso;
			pesosEjes[par2][par1] = peso;
			
			complex++;
		}
		
		for (int i = (cantAristas+1)*3; i < cantArcos; i=i+3) {
			
			int par1 = Integer.parseInt(paresAdyacencias[i]);
			int par2 = Integer.parseInt(paresAdyacencias[i+1]);
			int peso = Integer.parseInt(paresAdyacencias[i+2]);
			
			T+= cantBits(par1);
			T+= cantBits(par2);
			T+= cantBits(peso);
			
			agregarAdyacencia(par1, par2);
			pesosEjes[par1][par2] = peso;
			
			complex++;
		}
		
	}
	
	protected final void agregarAdyacencia(int par1, int par2) {
		if(adyacenciasNoOr[par1] == null){
			adyacenciasNoOr[par1] = new ListaInt();
		}
		adyacenciasNoOr[par1].add(par2);
	}
	
	public ListaInt adyacentesNoOr(int nodo){
		return adyacenciasNoOr[nodo];
	}
	
	public ListaInt adyacentesOr(int nodo){
		return adyacenciasOr[nodo];
	}
	
	public ListaInt adyacentesVisitados(int nodo){
		return adyacenciasVisitados[nodo];
	}
	
	@Deprecated
	public void showGrafo(){
		for (int i = 0; i < adyacenciasNoOr.length; i++) {
			ListaInt adyacentes = adyacenciasNoOr[i];
			for (Integer adyacente : adyacentes) {
				System.out.print(i + "," + adyacente + ", ");
			}
		}
	}
	
	public int getNodoInicial(int semilla){
		return semilla % cantNodos;
	}
	
}