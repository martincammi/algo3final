package problema3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {

	static boolean logActivated = false;
	static boolean showResultOnConsole = true;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		runTests();
		//runForOnlineJudge();

	}
	
	public static void runTests(){
		showResultOnConsole = false;
		List<Trabajo> trabajos = initiateTrabajos(); 
		Encargado encargado;
		
		int testId = 0;
		for (Trabajo trabajo : trabajos) {
			//trabajo.mostrar();
			encargado = new Encargado(trabajo);
			encargado.distribuirAplicaciones();
			//encargado.mostrarSolucion();
			check(encargado.pcs, encargado.existeSolucion, testId);
			testId++;
		}
	}
	
	public static void runForOnlineJudge(){
		Procesor.procesarDatos();
	}
	
	public static List<Trabajo> initiateTrabajos(){
		
		List<Trabajo> trabajos =  new ArrayList<Trabajo>();
		List<Aplicacion> aplicaciones =  new ArrayList<Aplicacion>();
		Trabajo trabajo;
		Aplicacion app;
		
		app = new Aplicacion(4, "A", Trabajo.parseList("01234"));
		aplicaciones.add(app);
		app = new Aplicacion(1, "Q", Trabajo.parseList("5"));
		aplicaciones.add(app);
		app = new Aplicacion(4, "P", Trabajo.parseList("56789"));
		aplicaciones.add(app);
		
		trabajo = new Trabajo(aplicaciones);
		trabajos.add(trabajo);

		 //---
		
		aplicaciones =  new ArrayList<Aplicacion>();
		
		app = new Aplicacion(4, "A", Trabajo.parseList("01234"));
		aplicaciones.add(app);
		app = new Aplicacion(1, "Q", Trabajo.parseList("5"));
		aplicaciones.add(app);
		app = new Aplicacion(5, "P", Trabajo.parseList("56789"));
		aplicaciones.add(app);
		
		trabajo = new Trabajo(aplicaciones);
		trabajos.add(trabajo);
		
		 //---
		
		aplicaciones =  new ArrayList<Aplicacion>();
		
		app = new Aplicacion(2, "A", Trabajo.parseList("01")); //ESTO ESTA MAL con 4 no puede dar bien
		aplicaciones.add(app);
		app = new Aplicacion(2, "B", Trabajo.parseList("0123"));
		aplicaciones.add(app);
		
		trabajo = new Trabajo(aplicaciones);
		trabajos.add(trabajo);
		
		 //---
		
		aplicaciones =  new ArrayList<Aplicacion>();
		
		app = new Aplicacion(2, "A", Trabajo.parseList("0123"));
		aplicaciones.add(app);
		app = new Aplicacion(2, "B", Trabajo.parseList("01"));
		aplicaciones.add(app);
		
		trabajo = new Trabajo(aplicaciones);
		trabajos.add(trabajo);
		
		 //---
		
		aplicaciones =  new ArrayList<Aplicacion>();
		
		app = new Aplicacion(2, "A", Trabajo.parseList("01234"));
		aplicaciones.add(app);
		app = new Aplicacion(2, "B", Trabajo.parseList("012"));
		aplicaciones.add(app);
		app = new Aplicacion(1, "Q", Trabajo.parseList("1"));
		aplicaciones.add(app);
		
		trabajo = new Trabajo(aplicaciones);
		trabajos.add(trabajo);
		
		 //---
		
		aplicaciones =  new ArrayList<Aplicacion>();
		
		app = new Aplicacion(2, "A", Trabajo.parseList("012"));
		aplicaciones.add(app);
		app = new Aplicacion(2, "B", Trabajo.parseList("345"));
		aplicaciones.add(app);
		app = new Aplicacion(2, "Q", Trabajo.parseList("25"));
		aplicaciones.add(app);
		
		trabajo = new Trabajo(aplicaciones);
		trabajos.add(trabajo);
		
		 //---
		
		aplicaciones =  new ArrayList<Aplicacion>();
		
		app = new Aplicacion(1, "A", Trabajo.parseList("01"));
		aplicaciones.add(app);
		app = new Aplicacion(1, "B", Trabajo.parseList("123"));
		aplicaciones.add(app);
		app = new Aplicacion(2, "Q", Trabajo.parseList("012"));
		aplicaciones.add(app);
		
		trabajo = new Trabajo(aplicaciones);
		trabajos.add(trabajo);
		
		 //---
		
		aplicaciones =  new ArrayList<Aplicacion>();
		
		app = new Aplicacion(1, "A", Trabajo.parseList("01"));
		aplicaciones.add(app);
		app = new Aplicacion(1, "B", Trabajo.parseList("12"));
		aplicaciones.add(app);
		app = new Aplicacion(2, "QQ", Trabajo.parseList("01"));
		aplicaciones.add(app);
		
		trabajo = new Trabajo(aplicaciones);
		trabajos.add(trabajo);
		
		//---
		
		aplicaciones =  new ArrayList<Aplicacion>();
		
		app = new Aplicacion(2, "A", Trabajo.parseList("0"));
		aplicaciones.add(app);
		app = new Aplicacion(1, "B", Trabajo.parseList("1"));
		aplicaciones.add(app);
		app = new Aplicacion(1, "Q", Trabajo.parseList("2"));
		aplicaciones.add(app);
		
		trabajo = new Trabajo(aplicaciones);
		trabajos.add(trabajo);
		
		//---
		
		aplicaciones =  new ArrayList<Aplicacion>();
		
		app = new Aplicacion(1, "A", Trabajo.parseList("0123"));
		aplicaciones.add(app);
		app = new Aplicacion(1, "B", Trabajo.parseList("012"));
		aplicaciones.add(app);
		app = new Aplicacion(1, "C", Trabajo.parseList("01"));
		aplicaciones.add(app);
		app = new Aplicacion(1, "D", Trabajo.parseList("0"));
		aplicaciones.add(app);
		
		trabajo = new Trabajo(aplicaciones);
		trabajos.add(trabajo);
		
		return trabajos; 
	}

	public static void check(String pcs[], boolean existeSolucion, int testId){
		
		
		List<String> resultados = new ArrayList<String>();
		resultados.add("AAAA_QPPPP");
		resultados.add("!");
		resultados.add("AABB______");
		resultados.add("BBAA______");
		resultados.add("BQBAA_____");
		resultados.add("AAQBBQ____");
		resultados.add("QAQB______");
		resultados.add("!");
		resultados.add("!");
		resultados.add("DCBA______");
		
		boolean correcto = true; 
		if(existeSolucion){
			for (int i = 0; i < pcs.length && correcto; i++) {
				if(!pcs[i].equals(resultados.get(testId).charAt(i) + "")){
					System.out.println("Test" + testId + " FALLO: " + mostrar(pcs) + " vs " + resultados.get(testId));
					return;
				}
			}
		}else{
			if(!resultados.get(testId).equals("!")){
				System.out.println("Test" + testId + " FALLO: " + "!" + " vs " + resultados.get(testId));
				return;
			}
		}
		System.out.println("Test" + testId + " Ok: " + mostrar(pcs));
		return;
	}
	
	public static class Encargado{

		static String MODO_BUSQUEDA = "busqueda";
		static String MODO_REEMPLAZO = "reemplazo";
		static String VACIO = "_";
		
		int MAX_PCS = 10;
		Trabajo listaDeTrabajo;
		boolean existeSolucion;
		String pcs[] = new String[MAX_PCS];
		
		public Encargado(Trabajo trabajo){
			setNuevoTrabajo(trabajo);
		}
		
		public void setNuevoTrabajo(Trabajo trabajo){
			this.listaDeTrabajo = trabajo;
			existeSolucion = true;
			
			for (int i = 0; i < pcs.length; i++) {
				pcs[i] = VACIO;
			}
		}
		
		public void distribuirAplicaciones(){
			if(validarAplicaciones()){
				buscarSolucion();
			}else{
				existeSolucion = false;
			}
			
			if(showResultOnConsole){
				if(existeSolucion){
					System.out.println(mostrar(pcs));
				}else{
					System.out.println("!");
				}
			}
			
		}
		
		public void buscarSolucion(){
			List<Aplicacion> aplicaciones = listaDeTrabajo.getAplicaciones();
			String modo = MODO_BUSQUEDA;
			for (Aplicacion app: aplicaciones) {
				existeSolucion = asignarAplicacion(app ,null ,modo);
				if(!existeSolucion){
					vaciarPcs();
					break;
				}
			}
		}
		
		public void vaciarPcs(){
			for (int i = 0; i < pcs.length; i++) {
				pcs[i] = VACIO;
			}
		}
		
		public boolean asignarAplicacion(Aplicacion app, Aplicacion appQueLaPiso, String modo){
			
				if(app == null){
					return true;
				}

				logln("Asignando app " + app.name + " modo: " + modo);
				
				//Intento asignar la aplicacion a todas las pcs que pueda
				for (Integer compuApp : app.computadoras) { //Mejora: para hacer comenzar de la posición ya asignada
					
					if(!app.appAsignada()){
						if(!estaOcupadaLaPc(compuApp)){
							logln("Asignando " + app.name + " a " + compuApp + "()");
							asignarAppAPc(app, compuApp); 
						}else{ //Si esta ocupada
							if( modoReemplazo(modo) ){
								if( puedoReemplazar(app, appQueLaPiso, compuApp) ){
									app.flagSearch = true;
									Aplicacion appPisada = asignarAppAPc(app, compuApp);
									logln("Asignando " + app.name + " a " + compuApp + "(" + (appPisada == null ? "" :appPisada.name)+ ")");
									boolean seAsigno = asignarAplicacion(appPisada, app, MODO_BUSQUEDA);
									app.flagSearch = false;
									if(!seAsigno){
										return false;
									}
								}
							}
							
						}
					}
				}
				//Si no logre asignar todas, reemplazo.
				if(!app.appAsignada()){
					if( modoBusqueda(modo)){
						boolean seAsigno = asignarAplicacion(app, appQueLaPiso, MODO_REEMPLAZO);
						if(!seAsigno){
							return false;
						}
					}else{ //No hay solución
						existeSolucion = false;
						return false;
					}
				}
				return true;
		}
		
		
		
		public boolean puedoReemplazar(Aplicacion appActual, Aplicacion appQueLaPiso, Integer compu){
			String appNameAReemplazar = pcs[compu];
			Aplicacion appAReemplazar = listaDeTrabajo.getAppByName(appNameAReemplazar);
			String appQueLaPisoName = (appQueLaPiso == null) ? "" : appQueLaPiso.name;
			
			return !appNameAReemplazar.equals(appActual.name) && !appNameAReemplazar.equals(appQueLaPisoName)
					&& !appAReemplazar.flagSearch;
		}
		
		public boolean modoBusqueda(String modo){
			return MODO_BUSQUEDA.equals(modo);
		}
		
		public boolean modoReemplazo(String modo){
			return MODO_REEMPLAZO.equals(modo);
		}
		
		/**
		 * Asigna la aplicacion como paràmetro a la compu tambièn pasada como paràmetro
		 * devuelve la aplicaciòn que estbaa antes si habia una sino null;
		 * Si una aplicaciòn fue quitada de esa computadora, se le avisa
		 * @param app
		 * @param compu
		 * @return
		 */
		public Aplicacion asignarAppAPc(Aplicacion app, Integer compu){ 
			Aplicacion appAnterior;
			String appNameAnterior = app.asignarA(compu, pcs);
			if(appNameAnterior == VACIO){
				return null;
			}else{
				appAnterior = listaDeTrabajo.getAppByName(appNameAnterior);
				appAnterior.desAsignarDe(compu);
			}
			return appAnterior;
		}
		
		public boolean estaOcupadaLaPc(int pcId){
			return pcs[pcId] != VACIO;
		}

		public boolean noEsPc(String nombreApp, int pcId){
			return !pcs[pcId].equals(nombreApp);
		}
		
		public void mostrarSolucion(){
			if(existeSolucion){
				for (int i = 0; i < pcs.length; i++) {
					if(pcs[i] != VACIO){
						System.out.print(pcs[i]);
					}else{
						System.out.print("_");
					}
				}
				System.out.println("");
			}else{
				System.out.println("!");
			}
		}
		
		/**
		 * Verifica que para la cantidad de aplicaciones de un tipo existan
		 * al menos esa misma cantidad de computadoras para ejecutarlos. 
		 * @param listaDeTrabajo
		 * @return true si es posible ejecutar las aplicaciones, false si la cantidad de 
		 * alguna aplicacion exece la cantidad de computadoras donde puede ejecutarse.
		 */
		private boolean validarAplicaciones(){
			List<Aplicacion> aplicaciones = listaDeTrabajo.getAplicaciones();

			for (Aplicacion app : aplicaciones) {
				if(app.cant > app.computadoras.size()){
					existeSolucion = false;
					break;
				}
			}
			return existeSolucion;
		}
	}
	
	public static class Trabajo{
		
		List<Aplicacion> aplicaciones = new ArrayList<Aplicacion>(); //Array con los nombres de las aplicaciones
		Map<String, Aplicacion> appByNameMap = new HashMap<String, Aplicacion>();
		
		public Trabajo(List<Aplicacion> aplicaciones){
			this.aplicaciones = aplicaciones;
			for (Aplicacion app : aplicaciones) {
				appByNameMap.put(app.name, app);
			}
		}
		
		public Aplicacion getAppByName(String appName){
			return appByNameMap.get(appName);
		}
		
		public List<Aplicacion> getAplicaciones(){
			return aplicaciones;	
		}
		
		public void mostrar(){
			
			for (Aplicacion aplicacion : aplicaciones) {
				aplicacion.mostrar();
			}
		}
		
		public static List<Integer> parseList(String list){
			List<Integer> listaCompus = new ArrayList<Integer>();
			String array[] = list.split("");
			for (int i = 1; i < array.length; i++) {
				listaCompus.add(Integer.parseInt(array[i]));
			}
			return listaCompus;
		}
	}
	
	public static class Aplicacion {
		int cant;
		String name;
		List<Integer> computadoras;
		boolean asignada[];
		boolean flagSearch; 
		
		public Aplicacion(int cant, String name, List<Integer> compus){
			this.cant = cant;
			this.name = name;
			this.computadoras = compus;
			asignada = new boolean[10];
			flagSearch = false;
		}
		
		public boolean estaAsignada(Integer compu){
			return asignada[compu];
		}
		
		public String asignarA(Integer compu, String pcs[]){
			String appAnterior = pcs[compu];
			asignada[compu] = true;
			pcs[compu] = name;
			cant--;
			return appAnterior;
		}
		
		public void desAsignarDe(Integer compu){
			asignada[compu] = false;
			cant++;
		}
		
		public boolean appAsignada(){
			return (cant==0);
		}
		
		public void mostrar(){
			System.out.print(cant);
			System.out.print(name);
			System.out.print(" ");
			for (Integer compu : computadoras) {
				System.out.print(compu);
			}
			System.out.println(";");

		}
	}
	
    public static void log(String string){
    	if(logActivated){
    		System.out.print(string);
    	}
    }
    
    public static void logln(String string){
    	if(logActivated){
    		System.out.println(string);
    	}
    }
    
    public static void log(int value){
    	if(logActivated){
    		System.out.print(value);
    	}
    }
    
    public static String mostrar(String arr[]){
    	String str = "";
    	for (int i = 0; i < arr.length; i++) {
    		str = str + arr[i];
		}
    	return str;
    }
	
    private static class Procesor{
    	
    	public static void procesarDatos() {
    		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    		
    		List<Aplicacion> aplicaciones = new ArrayList<Aplicacion>();
    		Encargado encargado;
 
	    		while(in.hasNextLine()){
	    			
	    			String linea = "esteValorSeraPisado"; 
	    			
	    			while (linea.length() > 0){
	    				
	    				linea = in.nextLine();
		    			String valores[] = linea.split(" ");
		    			
		    			if(linea.length() > 0){
		    			
		    				int cant = Integer.parseInt((valores[0]).charAt(1) + "");
			   				
			   				String name = ((valores[0]).charAt(0)+ "");
			   				String[] listaCompus = valores[1].split("");
			   				List<Integer> listaComputadoras = new ArrayList<Integer>();
			   				for (int i = 1; i < listaCompus.length-1; i++) {
			   					listaComputadoras.add(new Integer(listaCompus[i]));
							}
	
			   				Aplicacion aplicacion = new Aplicacion(cant, name, listaComputadoras);
			   				aplicaciones.add(aplicacion);
		    			}
		   				/*
		   				if(in.hasNextLine()){
		   					linea = in.nextLine();
		   					valores = linea.split(" ");
		   				}else{
		   					valores = new String[0];
		   				}*/
	    			}
	    			
	    			if(aplicaciones.size() > 0){
		    			Trabajo trabajo = new Trabajo(aplicaciones);
		    			encargado = new Encargado(trabajo);
		    			encargado.distribuirAplicaciones();
		    			aplicaciones.clear();
	    			}
	    			
	    		}
    		
   			in.close();
    	}
    }
    
}
