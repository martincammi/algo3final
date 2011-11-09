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
				if(i == j){
					pesosEjes[i][j] = 0;
					pesoCaminoMinimo[i][j] = 0;
				}else{
					pesosEjes[i][j] = INFINITO;
					pesoCaminoMinimo[i][j] = INFINITO;
				}
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
			//LA POSICION CERO DE DIRECCIONORIENTACIONARISTA ME DICE SI HAY ALGUN NODO DONDE LA CANTIDAD DE ARISTAS 
			//ES IGUAL A LA RESTA ENTRE GRADO DE ENTRADA Y DE SALIDA.
			//LA POSICION 1 ME DICE SI TODAS LAS ARISTAS DE ESE NODO DEBEN SER DE SALIDA (MARCADA CON UNA S) O DE ENTRADA (CON UNA E)
			nodo = encontrarNodoAOrientar(direccionOrientacionArista);
			orientarNodo(nodo, direccionOrientacionArista);
		}
	}
	
	//ME DA UN NODO A ORIENTAR. SI ENCUENTRA UNO EN LA QUE LA DIFERENCIA DE DIN CON DOUT = CANTIDAD DE ARISTAS DE ESE NODO (Y QUE TENGA ALGUNA ARISTA PARA ORIENTAR)
	//ME DEVUELVE ESE, SINO, ME DA CUALQUIER OTRO NODO QUE TENGA ALGUNA ARISTA PARA ORIENTAR
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
	
	//ORIENTA EL NODO QUE VIENE COMO PARAMETRO
	//SI DIRECCIONORIENTACIONARISTA[0] VIENE CON S, TODAS LAS ARISTAS DE ESE NODO SE ORIENTARAN PARA EL MISMO LADO
	//Y ESTA DEFINIDO EN DIRECCIONORIENTACIONARISTA[1]
	//CASO CONTRARIO, ORIENTO ESA ARISTA Y LA HAGO DE SALIDA
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
					pesoCaminoMinimo[i][j] = 0;
				}else{
					pesosEjes[i][j] = INFINITO;
					pesoCaminoMinimo[i][j] = INFINITO;
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
		calcularDantzig();
	}
	
	public int pesoCaminoMinimo (int nodo1, int nodo2){
		return pesoCaminoMinimo[nodo1][nodo2];
	}
	
	public void calcularDantzig(){
		
		for (int i = 0; i < cantNodos; i++) {
			for (int j = 0; j < cantNodos; j++) {
				pesoCaminoMinimo[i][j] = pesosEjes[i][j];
			}
		}
		
		
		for (int k = 0; k < cantNodos; k++) {
			for (int i = 0; i < cantNodos; i++) {
				for (int j = 0; j < cantNodos; j++) {
					if(pesoCaminoMinimo[i][k] != INFINITO && pesoCaminoMinimo[k][j] != INFINITO && i!=j){
						pesoCaminoMinimo[i][j] = min (pesoCaminoMinimo[i][j], pesoCaminoMinimo[i][k] + pesoCaminoMinimo[k][j]);
					}
				}
			}
		}
	}
	
	private int min(int a, int b){

		if(a == INFINITO){
			return b;
		}
		
		if(b == INFINITO){
			return a;
		}
		
		if(a < b){
			return a;
		}else{
			return b;
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
	
	public static long getComplex() {
		return complex;
	}

	public static void setComplex(long complex) {
		Grafo.complex = complex;
	}

	public long getT() {
		return T;
	}

	public void setT(long t) {
		T = t;
	}

	public int getCantAristas() {
		return cantAristas;
	}

	public void setCantAristas(int cantAristas) {
		this.cantAristas = cantAristas;
	}

	public int getCantArcos() {
		return cantArcos;
	}

	public void setCantArcos(int cantArcos) {
		this.cantArcos = cantArcos;
	}

	public ListaInt[] getAdyacenciasNoOr() {
		return adyacenciasNoOr;
	}

	public void setAdyacenciasNoOr(ListaInt[] adyacenciasNoOr) {
		this.adyacenciasNoOr = adyacenciasNoOr;
	}

	public ListaInt[] getAdyacenciasOr() {
		return adyacenciasOr;
	}

	public void setAdyacenciasOr(ListaInt[] adyacenciasOr) {
		this.adyacenciasOr = adyacenciasOr;
	}

	public int[][] getPesosEjes() {
		return pesosEjes;
	}

	public void setPesosEjes(int[][] pesosEjes) {
		this.pesosEjes = pesosEjes;
	}

	public ListaInt[] getAdyacenciasVisitados() {
		return adyacenciasVisitados;
	}

	public void setAdyacenciasVisitados(ListaInt[] adyacenciasVisitados) {
		this.adyacenciasVisitados = adyacenciasVisitados;
	}

	public int[] getDin() {
		return din;
	}

	public void setDin(int[] din) {
		this.din = din;
	}

	public int[] getDout() {
		return dout;
	}

	public void setDout(int[] dout) {
		this.dout = dout;
	}

	public int[][] getPesoCaminoMinimo() {
		return pesoCaminoMinimo;
	}

	public void setPesoCaminoMinimo(int[][] pesoCaminoMinimo) {
		this.pesoCaminoMinimo = pesoCaminoMinimo;
	}

	public int getINFINITO() {
		return INFINITO;
	}

	public void setINFINITO(int iNFINITO) {
		INFINITO = iNFINITO;
	}

	public void setCantNodos(int cantNodos) {
		this.cantNodos = cantNodos;
	}

	public Grafo clone() throws CloneNotSupportedException {
		Grafo grafo = new Grafo(cantNodos, cantAristas, cantArcos);
		ListaInt[] adyacenteNoOrdenados = new ListaInt[cantNodos];
		ListaInt[] adyacenteOrdenados = new ListaInt[cantNodos];
		int[][] pesosDeEjes = new int[cantNodos][cantNodos];
		int[] gradoEntrada = new int[cantNodos];
		int[] gradoSalida = new int[cantNodos];
		
		for (int i = 0; i < cantNodos; i++)
		{
			adyacenteNoOrdenados[i] = (ListaInt) adyacenciasNoOr[i].clone();
			adyacenteOrdenados[i] = (ListaInt) adyacenciasOr[i].clone();
			gradoEntrada[i] = din[i];
			gradoSalida[i] = dout[i];
			
			for (int j = 0; j < cantNodos; j++)
			{
				pesosDeEjes[i][j] = pesosEjes[i][j];
			}
		}
		
		grafo.setAdyacenciasNoOr(adyacenteNoOrdenados);
		grafo.setAdyacenciasOr(adyacenteOrdenados);
		grafo.setPesosEjes(pesosDeEjes);
		grafo.setDin(gradoEntrada);
		grafo.setDout(gradoSalida);
		grafo.setT(T);
		
		return grafo;
	}
	
}