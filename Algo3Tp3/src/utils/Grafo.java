package utils;

public class Grafo {
	public static long complex = 0;
	public long T = 0;
	public int cantNodos;
	protected int cantAristas; //No orientadadas
	protected int cantArcos;  //Orientadas
	protected ListaInt[] adyacenciasNoOr; //Guarda dado un nodo sus adyacencias no orientadas.
	public ListaInt[] adyacenciasOr; //Guarda dado un nodo sus adyacencias orientadas.
	protected int[][] pesosEjes;
	protected ListaInt[] adyacenciasVisitados;
	protected int din[];
	public int[] dout;
	private int[][] pesoCaminoMinimo;
	
	int INFINITO = Integer.MAX_VALUE; //Temporal probando
	/**
	 * @param cantNodos: cantidad de vértices
	 * @param cantAristas: cantidad de ejes no orientados
	 * @param cantArcos: cantidad de ejes orientados
	 */
	public Grafo(int cantNodos, int cantAristas, int cantArcos){
		complex = 0;
		this.T = 0;
		this.cantNodos = cantNodos;
		this.cantAristas = cantAristas;
		this.cantArcos = cantArcos;
		sumarBits(cantNodos);
		sumarBits(cantAristas);
		sumarBits(cantArcos);
		adyacenciasNoOr = new ListaInt[cantNodos];
		adyacenciasOr = new ListaInt[cantNodos];
		adyacenciasVisitados = new ListaInt[cantNodos];
		pesosEjes = new int[cantNodos][cantNodos];
		pesoCaminoMinimo = new int [cantNodos][cantNodos];
		din = new int[cantNodos];
		dout = new int[cantNodos];
		
		for(int i = 0; i < cantNodos; ++i){
			adyacenciasNoOr[i] = new ListaInt();
			adyacenciasOr[i] = new ListaInt();
			adyacenciasVisitados[i] = new ListaInt();
			din[i] = 0;
			for(int j = 0; j < cantNodos;++j){
				pesosEjes[i][j]= INFINITO;//CAMBIAR POR INFINITO
			}
		}
	}
	
	/**
	 * Suma al tamaño de la entrada en bits.
	 * @param n
	 */
	public final void sumarBits(int n){
		T+= cantBits(n);
	}
	
	public void orientarTodasAristas()
	{
		int nodo;
		int cantidadAristas = cantAristas;
		String[] direccionOrientacionArista = {null, null};
		for(int i = 0; i < cantidadAristas - 1; i++)
		{
			nodo = encontrarNodoAOrientar(direccionOrientacionArista);
			orientarNodo(nodo, direccionOrientacionArista);
		}
	}
	
	private int encontrarNodoAOrientar(String[] direccionOrientacionArista)
	{
		int result = -1;
		for (int i = 0; i < cantNodos; i++)
		{
			if (Math.abs(din[i] - dout[i]) == adyacenciasNoOr[i].size() && adyacenciasNoOr[i].size() > 0)
			{
				result = i;
				direccionOrientacionArista[0] = "S";
				direccionOrientacionArista[1] = din[i] > dout[i] ? "S" : "E";
				break;
			}
		}
		
		if (result == -1)
		{
			for (int i = 0; i < cantNodos; i++)
			{
				if (adyacenciasNoOr[i].size() > 0)
				{
					result = i;
					direccionOrientacionArista[0] = "N";
					direccionOrientacionArista[1] = null;
					break;
				}
			}
		}
			
		return result;
	}
	
	private void orientarNodo(int nodoAOrientar, String[] direccionOrientacionArista)
	{
		int peso = 0;
		int cantAristasNodo = adyacenciasNoOr[nodoAOrientar].size();
		
		if (direccionOrientacionArista[0].equals("S"))
		{
			for (int i = cantAristasNodo - 1; i >= 0; i--)
			{
				if (direccionOrientacionArista[1].equals("S"))
				{
					peso = (pesosEjes[adyacenciasNoOr[nodoAOrientar].get(i)][nodoAOrientar] == INFINITO) 
				 	  ? pesosEjes[nodoAOrientar][adyacenciasNoOr[nodoAOrientar].get(i)]
					  : pesosEjes[adyacenciasNoOr[nodoAOrientar].get(i)][nodoAOrientar];
				 	
				 	agregarAdyacencia(nodoAOrientar, adyacenciasNoOr[nodoAOrientar].get(i), peso, true);
				 	adyacenciasNoOr[adyacenciasNoOr[nodoAOrientar].get(i)].remove(new Integer(nodoAOrientar));
				 	adyacenciasNoOr[nodoAOrientar].remove(i);
				 	cantAristas--;
					
				}
				else
				{
					peso = (pesosEjes[adyacenciasNoOr[nodoAOrientar].get(i)][nodoAOrientar] == INFINITO) 
				 	  ? pesosEjes[nodoAOrientar][adyacenciasNoOr[nodoAOrientar].get(i)]
					  : pesosEjes[adyacenciasNoOr[nodoAOrientar].get(i)][nodoAOrientar];
				 	
				 	agregarAdyacencia(adyacenciasNoOr[nodoAOrientar].get(i), nodoAOrientar, peso, true);
				 	adyacenciasNoOr[adyacenciasNoOr[nodoAOrientar].get(i)].remove(new Integer(nodoAOrientar));
				 	adyacenciasNoOr[nodoAOrientar].remove(i);
				 	cantAristas--;
				
				}
			}
		}
		else
		{
			peso = (pesosEjes[adyacenciasNoOr[nodoAOrientar].get(0)][nodoAOrientar] == INFINITO) 
		 	  ? pesosEjes[nodoAOrientar][adyacenciasNoOr[nodoAOrientar].get(0)]
			  : pesosEjes[adyacenciasNoOr[nodoAOrientar].get(0)][nodoAOrientar];
		 	
		 	agregarAdyacencia(nodoAOrientar, adyacenciasNoOr[nodoAOrientar].get(0), peso, true);
		 	adyacenciasNoOr[adyacenciasNoOr[nodoAOrientar].get(0)].remove(new Integer(nodoAOrientar));
		 	adyacenciasNoOr[nodoAOrientar].remove(0);
		 	cantAristas--;
		}	
	}	
	
	
	public void orientarAristas(int semilla){

		for(int nodo1 = 0; nodo1 < cantNodos;++nodo1){
			int Ndout	= adyacenciasOr[nodo1].size();
			int Ndin	= din[nodo1];
			int Nd		= adyacenciasNoOr[nodo1].size();
			if(Nd == 1 && (int)Math.abs(Ndin - Ndout) == 1){//hay una unica direccion posible para la arista no dirigida
				int nodo2 = adyacenciasNoOr[nodo1].get(0);
			}
		}
	}
	
	/**
	 * Constructor para la entrada de la cátedra (no borrar uso para pruebas: Martin)
	 * @param paresAdyacencias
	 */
	public Grafo(String[] paresAdyacencias){

		this(Integer.parseInt(paresAdyacencias[0]),Integer.parseInt(paresAdyacencias[1]),Integer.parseInt(paresAdyacencias[2]));

		sumarBits(cantNodos);
		sumarBits(cantAristas);
		sumarBits(cantArcos);
		
		for (int i = 0; i < cantNodos; i++) {
			for (int j = 0; j < cantNodos; j++) {
				if(i == j){
					pesosEjes[i][j] = 0;
				}else{
					pesosEjes[i][j] = INFINITO;
				}
			}
			adyacenciasNoOr[i] = new ListaInt();
			adyacenciasOr[i] = new ListaInt();
			adyacenciasVisitados[i] = new ListaInt();
		}
		
		for (int i = 3; i < (cantAristas+1)*3; i=i+3) {
			
			int par1 = Integer.parseInt(paresAdyacencias[i]);
			int par2 = Integer.parseInt(paresAdyacencias[i+1]);
			int peso = Integer.parseInt(paresAdyacencias[i+2]);
			
			sumarBits(par1);
			sumarBits(par2);
			sumarBits(peso);
			
			agregarAdyacencia(par1, par2, peso, false);
			agregarAdyacencia(par2, par1, peso, false);
			
			complex++;
		}
		
		for (int i = (cantAristas+1)*3; i < (cantAristas+1)*3 + cantArcos*3; i=i+3) {
			
			int par1 = Integer.parseInt(paresAdyacencias[i]);
			int par2 = Integer.parseInt(paresAdyacencias[i+1]);
			int peso = Integer.parseInt(paresAdyacencias[i+2]);
			
			sumarBits(par1);
			sumarBits(par2);
			sumarBits(peso);
			
			agregarAdyacencia(par1, par2, peso, true);
			
			complex++;
		}
		
	}
	
	public int pesoCaminoMinimo (int nodo1, int nodo2){
		return pesoCaminoMinimo[nodo1][nodo2];
	}
	
	public void calcularDantzig(){
		
		for (int i = 0; i < cantNodos; i++) {
			for (int j = 0; j < cantNodos; j++) {
				
				if(pesosEjes[i][j] >= 0){
					pesoCaminoMinimo[i][j] = pesosEjes[i][j];
				}else{
					pesoCaminoMinimo[i][j] = INFINITO;
				}
			}
		}
		
		/*
		for (int i = 0; i < cantNodos; i++) {
			for (int j = i; j < cantNodos; j++) {
				for (int k = i; k < j; k++) {
					if(pesoCaminoMinimo[i][j] > pesoCaminoMinimo[i][k] + pesoCaminoMinimo[k][j]){
						pesoCaminoMinimo[i][j] = pesoCaminoMinimo[i][k] + pesoCaminoMinimo[k][j];
					}
				}
			}
		}*/
		
		for (int k = 0; k < cantNodos; k++) {
			for (int i = 0; i < cantNodos; i++) {
				for (int j = 0; j < cantNodos; j++) {
					//pesoCaminoMinimo[i][j] = min (a[i][j], a[i][k] + a[k][j])					
				}
			}
		}
		

		
	}
	
	public int getDin(int nodo){
		return din[nodo];
	}
	
	public int getDout(int nodo){
		return dout[nodo];
	}
	
	public final int getCantNodos() {
		return cantNodos;
	}
	
	public final int cantBits(int i){
		return (int)Math.floor((Math.log10((i==0?1:i ))/Math.log10(2)) + 1) ;
	}
	
	public boolean todasDirigidas(){
		boolean todasDirigidas = true;
		for (int i = 0; i < adyacenciasNoOr.length && todasDirigidas; i++) {
			todasDirigidas = todasDirigidas && adyacentesNoOr(i).size() == 0;
		}
		return todasDirigidas;
	}
	
	public final void agregarAdyacencia(int par1, int par2, int peso, boolean dirigido) {
		if(dirigido){
			adyacenciasOr[par1].add(par2);
			din[par2]++;
			dout[par1]++;
		}else{
			adyacenciasNoOr[par1].add(par2);
		}
		pesosEjes[par1][par2] = peso;
		
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
		System.out.println("CantNodos: "+this.cantNodos);
		System.out.println("CantAristas: "+this.cantAristas);
		System.out.println("CantArcos: "+this.cantArcos);
		for(int i=0; i < cantNodos; ++i){
			System.out.println("Nodo: "+i);
			System.out.println("\tGradoBi: "+this.adyacenciasNoOr[i].size());
			System.out.println("\tDin: "+this.din[i]);
			System.out.print("\tDout: "+this.dout[i]);
			System.out.println("== CantOrientados: "+this.adyacenciasOr[i].size());
			System.out.println("\tAdyacencias no orientadas:");
			if(this.adyacenciasNoOr[i].size()>0)System.out.print("\t\t");
			for(Integer nodo: this.adyacenciasNoOr[i]){
				System.out.print(nodo+" ");
			}
			System.out.println();
			System.out.println("\tArcos Salientes:");
			if(this.adyacenciasOr[i].size()>0)System.out.print("\t\t");
			for(Integer nodo: this.adyacenciasOr[i]){
				System.out.print(nodo+" ");
			}
			System.out.println();
		}
		for(int[] l:pesosEjes){
			for(Integer k:l){
				System.out.print(k+"\t");
			}
			System.out.println();
		}
	}
	
	public int getNodoInicial(int semilla){
		return semilla % cantNodos;
	}

	public int[][] getPesoAristas() {
		return this.pesosEjes;
	}
	
}