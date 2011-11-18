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
	private int sumaPesoAristas;
	
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
		sumaPesoAristas = 0;
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
	
	public void orientarTodasAristas(int formaElegirNodo, int nodoInicial, String decisionDefault)
	{
		String[] direccionOrientacionArista = {null, null}; 
		int nodoAOrientar = nodoInicial;
		
		if (formaElegirNodo == 2 && adyacenciasNoOr[nodoAOrientar].size() == 0)
		{
			nodoAOrientar = encontrarProximoNodoConAristas(nodoAOrientar);			
		}
		
		while (cantAristas > 0)
		{
			//LA POSICION CERO DE DIRECCIONORIENTACIONARISTA ME DICE SIEMPRE "S". ESTO LO HAGO ASI PARA QUE SE PASE COMO REFERENCIA
			//LA POSICION 1 ME DICE SI TODAS LAS ARISTAS DE ESE NODO DEBEN SER DE SALIDA (MARCADA CON UNA S) O DE ENTRADA (CON UNA E)
			
			switch (formaElegirNodo) {
			case 1:				//   DIREFERENCIA DIN CON DOUT
				nodoAOrientar = encontrarNodoAOrientar(1, decisionDefault, direccionOrientacionArista);
				break;
			case 2:				//   COMIENZO DESDE UN CIERTO NODO. DEBE ESTAR CARGADO EL PARAMETRO NODOINCIIAL
				direccionOrientacionArista[0] = "S";
				direccionOrientacionArista[1] = din[nodoAOrientar] == dout[nodoAOrientar] ? decisionDefault : (din[nodoAOrientar] > dout[nodoAOrientar] ? "S" : "E");
				break;
			case 3:				//   EL QUE TENGA MAYOR GRADO (MAS ARISTAS POR ORDENAR)
				nodoAOrientar = encontrarNodoAOrientar(3, decisionDefault, direccionOrientacionArista);
				break;
			case 4:				//   EL QUE TENGA MAYOR GRADO DE ENTRADA LO ORIENTO PRIMERO
				nodoAOrientar = encontrarNodoAOrientar(4, decisionDefault, direccionOrientacionArista);
				break;
			case 5:				//   EL QUE TENGA MAYOR GRADO DE SALIDA LO ORIENTO PRIMERO
				nodoAOrientar = encontrarNodoAOrientar(5, decisionDefault, direccionOrientacionArista);
				break;
			}
			
			orientarNodo(nodoAOrientar, direccionOrientacionArista);
			if (formaElegirNodo == 2 && adyacenciasNoOr[nodoAOrientar].size() == 0)
			{
				nodoAOrientar = encontrarProximoNodoConAristas(nodoAOrientar);
			}
		}
	}
	
	private int encontrarProximoNodoConAristas(int nodoAOrientar)
	{
		int result = 0;
		if (nodoAOrientar+1 >= cantNodos)
		{
			for (int i = 0; i < cantNodos; i++)
			{
				if (adyacenciasNoOr[i].size() > 0)
				{
					result = i;
					break;
				}
			}
		}
		else
		{
			for (int i = nodoAOrientar+1; i < cantNodos; i++)
			{
				if (adyacenciasNoOr[i].size() > 0)
				{
					result = i;
					break;
				}
			}
		}
		
		return result;
	}
	
	//ME DA UN NODO A ORIENTAR. SI ENCUENTRA UNO EN LA QUE LA DIFERENCIA DE DIN CON DOUT = CANTIDAD DE ARISTAS DE ESE NODO (Y QUE TENGA ALGUNA ARISTA PARA ORIENTAR)
	//ME DEVUELVE ESE, SINO, ME DA CUALQUIER OTRO NODO QUE TENGA ALGUNA ARISTA PARA ORIENTAR
	private int encontrarNodoAOrientar(int formaElegirNodo, String decisionDefault, String[] direccionOrientacionArista)
	{
		int result = -1;
		int grado = 0;
		
		if (formaElegirNodo == 1)
		{
			for (int i = 0; i < cantNodos; i++)
			{
				if (Math.abs(din[i] - dout[i]) == adyacenciasNoOr[i].size() && adyacenciasNoOr[i].size() > 0)
				{
					result = i;
					direccionOrientacionArista[0] = "S";
					direccionOrientacionArista[1] = din[i] == dout[i] ? decisionDefault : (din[i] > dout[i] ? "S" : "E");
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
						direccionOrientacionArista[0] = "S";
						direccionOrientacionArista[1] = din[i] == dout[i] ? decisionDefault : (din[i] > dout[i] ? "S" : "E");
						break;
					}
				}
			}
		}
		else
		{
			if (formaElegirNodo == 3)
			{
				for (int i = 0; i < cantNodos; i++)
				{
					if (adyacenciasNoOr[i].size() > 0)
					{
						if (adyacenciasNoOr[i].size() > grado)
						{
							grado = adyacenciasNoOr[i].size();
							result = i;
							direccionOrientacionArista[0] = "S";
							direccionOrientacionArista[1] = din[i] == dout[i] ? decisionDefault : (din[i] > dout[i] ? "S" : "E");
						}
					}
				}
			}
			else
			{
				if (formaElegirNodo == 4)
				{
					result = 0;
					for (int i = 0; i < cantNodos; i++)
					{
						if (adyacenciasNoOr[i].size() > grado)
						{
							if (din[i] > grado)
							{
								grado = din[i];
								result = i;
								direccionOrientacionArista[0] = "S";
								direccionOrientacionArista[1] = din[i] == dout[i] ? decisionDefault : (din[i] > dout[i] ? "S" : "E");
							}
						}
					}
				}
				else
				{
					if (formaElegirNodo == 5)
					{
						result = 0;
						for (int i = 0; i < cantNodos; i++)
						{
							if (adyacenciasNoOr[i].size() > 0)
							{
								if (dout[i] > grado)
								{
									grado = dout[i];
									result = i;
									direccionOrientacionArista[0] = "S";
									direccionOrientacionArista[1] = din[i] == dout[i] ? decisionDefault : (din[i] > dout[i] ? "S" : "E");
								}
							}
						}
					}
				}
			}
		}
		
		return result;
	}
	
	//ORIENTA EL NODO QUE VIENE COMO PARAMETRO Y ESTA DEFINIDO EN DIRECCIONORIENTACIONARISTA[1]
	private void orientarNodo(int nodoAOrientar, String[] direccionOrientacionArista)
	{
		if ("S".equals(direccionOrientacionArista[1]))  // SI ES UNA SALIDA
		{
			agregarEjeSalidaYRemueveArista(0, nodoAOrientar);
			
		}
		else   //SI ES UNA ENTRADA
		{
			agregarEjeEntradaYRemueveArista(0, nodoAOrientar);
		}
	}
	
	private void agregarEjeSalidaYRemueveArista(int index, int nodoAOrientar)
	{
		int peso;
		peso = (pesosEjes[adyacenciasNoOr[nodoAOrientar].get(index)][nodoAOrientar] == INFINITO) 
	 	  ? pesosEjes[nodoAOrientar][adyacenciasNoOr[nodoAOrientar].get(index)]
		  : pesosEjes[adyacenciasNoOr[nodoAOrientar].get(index)][nodoAOrientar];
	 	
	 	agregarAdyacencia(nodoAOrientar, adyacenciasNoOr[nodoAOrientar].get(index), peso, true);
	 	adyacenciasNoOr[adyacenciasNoOr[nodoAOrientar].get(index)].remove(new Integer(nodoAOrientar));
	 	adyacenciasNoOr[nodoAOrientar].remove(index);
	 	cantAristas--;
	}
	
	private void agregarEjeEntradaYRemueveArista(int index, int nodoAOrientar)
	{
		int peso;
		peso = (pesosEjes[adyacenciasNoOr[nodoAOrientar].get(index)][nodoAOrientar] == INFINITO) 
	 	  ? pesosEjes[nodoAOrientar][adyacenciasNoOr[nodoAOrientar].get(index)]
		  : pesosEjes[adyacenciasNoOr[nodoAOrientar].get(index)][nodoAOrientar];
	 	
	 	agregarAdyacencia(adyacenciasNoOr[nodoAOrientar].get(index), nodoAOrientar, peso, true);
	 	adyacenciasNoOr[adyacenciasNoOr[nodoAOrientar].get(index)].remove(new Integer(nodoAOrientar));
	 	adyacenciasNoOr[nodoAOrientar].remove(index);
	 	cantAristas--;
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

			agregarAdyacencia(par1, par2, peso, false);//agrego las dos copias directamente en esta funcion para no contar dos veces el peso
//			agregarAdyacencia(par2, par1, peso, false);
			
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
			adyacenciasNoOr[par2].add(par1);
			pesosEjes[par2][par1] = peso;
		}
		sumaPesoAristas+=peso;
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
	
	public void showGrafo(){
		System.out.println("CantNodos: "+this.cantNodos);
		System.out.println("CantAristas: "+this.cantAristas);
		System.out.println("CantArcos: "+this.cantArcos);
		System.out.println("sumaPesoAristas: "+this.sumaPesoAristas);
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
		for(int[] l:pesoCaminoMinimo){
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
		int[][] pesosDeCaminosMinimos = new int[cantNodos][cantNodos];
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
				pesosDeCaminosMinimos[i][j] = pesoCaminoMinimo[i][j];
			}
		}
		
		grafo.setAdyacenciasNoOr(adyacenteNoOrdenados);
		grafo.setAdyacenciasOr(adyacenteOrdenados);
		grafo.setPesosEjes(pesosDeEjes);
		grafo.setPesosCaminosMinimos(pesosDeCaminosMinimos);
		grafo.setDin(gradoEntrada);
		grafo.setDout(gradoSalida);
		grafo.setT(T);
		grafo.setSumaPesoAristas(sumaPesoAristas);
		
		return grafo;
	}

	private void setSumaPesoAristas(int sumaPesoAristas) {
		this.sumaPesoAristas = sumaPesoAristas;
	}

	private void setPesosCaminosMinimos(int[][] pesosDeCaminosMinimos) {
		this.pesoCaminoMinimo = pesosDeCaminosMinimos;
	}
	
}