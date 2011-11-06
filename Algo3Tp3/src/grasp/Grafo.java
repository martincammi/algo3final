package grasp;

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
	private int[] din;
	private int[] dout;
	private int[][] pesoCaminoMinimo;
	
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
		adyacenciasNoOr = new ListaInt[cantNodos];
		adyacenciasOr = new ListaInt[cantNodos];
		adyacenciasVisitados = new ListaInt[cantNodos];
		pesosEjes = new int[cantNodos][cantNodos];
		pesoCaminoMinimo = new int [cantNodos][cantNodos];
		din = new int[cantNodos];
		dout = new int[cantNodos];
	}
	
	/**
	 * Suma al tamaño de la entrada en bits.
	 * @param n
	 */
	private void sumarBits(int n){
		T+= cantBits(n);
	}
	
	/**
	 * Constructor para la entrada de la cátedra
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
			
			agregarAdyacenciaNOr(par1, par2);
			agregarAdyacenciaNOr(par2, par1);
			pesosEjes[par1][par2] = peso;
			pesosEjes[par2][par1] = peso;
			
			complex++;
		}
		
		for (int i = (cantAristas+1)*3; i < (cantAristas+1)*3 + cantArcos*3; i=i+3) {
			
			int par1 = Integer.parseInt(paresAdyacencias[i]);
			int par2 = Integer.parseInt(paresAdyacencias[i+1]);
			int peso = Integer.parseInt(paresAdyacencias[i+2]);
			
			sumarBits(par1);
			sumarBits(par2);
			sumarBits(peso);
			
			agregarAdyacenciaOr(par1, par2);
			dout[par1]++;
			din[par2]++;
			pesosEjes[par1][par2] = peso;
			
			complex++;
		}
		
	}
	
	public int pesoCaminoMinimo (int nodo1, int nodo2){
		return pesoCaminoMinimo[nodo1][nodo2];
	}
	
	int INFINITO = 100; //Temporal probando
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
	
	protected final void agregarAdyacenciaNOr(int par1, int par2) {
		if(adyacenciasNoOr[par1] == null){
			adyacenciasNoOr[par1] = new ListaInt();
		}
		adyacenciasNoOr[par1].add(par2);
	}
	
	protected final void agregarAdyacenciaOr(int par1, int par2) {
		if(adyacenciasOr[par1] == null){
			adyacenciasOr[par1] = new ListaInt();
		}
		adyacenciasOr[par1].add(par2);
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