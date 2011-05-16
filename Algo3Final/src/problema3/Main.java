package problema3;

import java.util.Iterator;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Trabajo trabajo1 = new Trabajo();
		trabajo1.mostrar();
	}

	public static class Encargado{

		int MAX_PCS = 10;
		Trabajo trabajo;
		boolean existeSolucion;
		String pcs[] = new String[MAX_PCS];
		int appActual;
		int solActual;
		
		public Encargado(Trabajo trabajo){
			setNuevoTrabajo(trabajo);
		}
		
		public void setNuevoTrabajo(Trabajo trabajo){
			this.trabajo = trabajo;
			
			for (int i = 0; i < pcs.length; i++) {
				pcs[i] = "";
			}
		}
		
		public void distribuirAplicaciones(){
			
			if(validarAplicaciones()){
				buscarSolucion();
			}
		}
		
		public boolean buscarSolucion(){
			for (int appId = 0; appId < trabajo.aplicaciones.length; appId++) {
				asignarAplicacion(appId);
			}
		}
		
		public void asignarAplicacion(int appId, int appIdAnt){
			
				String nombreApp = trabajo.aplicaciones[appId];
				String nombreAppAnt = trabajo.aplicaciones[appIdAnt];
				int cantApp = trabajo.cantidad[appId];
				int[] listaComp = trabajo.computadoras[appId];
				
				
				for (int comp = 0; comp < listaComp.length; comp++) {
					if(cantApp > 0){
						int pcId = listaComp[comp];
						if( !estaOcupadaLaPc(pcId) && noEsPc(nombreAppAnt,pcId) ){
							pcs[pcId] = nombreApp;
							trabajo.cantidad[appId] =- 1;
							cantApp = trabajo.cantidad[appId];
						}
					}else{
						break;
					}
				}
			
			
			if(cantApp > 0){
				for (int comp = 0; comp < listaComp.length; comp++) {
					if(cantApp > 0){
						int pcId = listaComp[comp];
						if( !estaOcupadaLaPc(pcId) ){
							pcs[pcId] = nombreApp;
							trabajo.cantidad[appId] =- 1;
							cantApp = trabajo.cantidad[appId];
						}
					}else{
						break;
					}
				}
			}
			
		}
		
		public boolean estaOcupadaLaPc(int pcId){
			return pcs[pcId] != "";
		}

		public boolean noEsPc(String nombreApp, int pcId){
			return !pcs[pcId].equals(nombreApp);
		}
		
		public boolean quedaParaAsignar(){
			return true;
		}
		

		
		/**
		 * Verifica que para la cantidad de aplicaciones de un tipo existan
		 * al menos esa misma cantidad de computadoras para ejecutarlos. 
		 * @param trabajo
		 * @return true si es posible ejecutar las aplicaciones, false sino.
		 */
		private boolean validarAplicaciones(){
			boolean sePuedeResolver = true;
			int cantAplicaciones = trabajo.aplicaciones.length;
			
			for (int i = 0; i < cantAplicaciones && sePuedeResolver; i++) {
				sePuedeResolver = trabajo.cantidad[i] > trabajo.computadoras[i].length;
			}
			return sePuedeResolver;
		}
	}
	
	public static class Trabajo{
		
		String aplicaciones[]; //Array con los nombres de las aplicaciones
		int cantidad[];
		int computadoras[][];
		
		//public Trabajo(String aplicaciones[], String computadoras[]){
		public Trabajo(){
			
			aplicaciones = new String[3];
			aplicaciones[0] = "A"; 
			aplicaciones[1] = "Q";
			aplicaciones[2] = "P";
			
			cantidad = new int[3];
			cantidad[0] = 4;
			cantidad[1] = 1;
			cantidad[2] = 4;
			
			computadoras = new int[3][];
			computadoras[0] = new int[5];
			for (int i = 0; i < 5; i++) {
				this.computadoras[0][i] = i;
			}
			
			this.computadoras[1] = new int[1];
			this.computadoras[1][0] = 5;
			
			this.computadoras[2] = new int[5];
			for (int i = 0; i < 5; i++) {
				this.computadoras[2][i] = i+5;
			}
		}
		
		public void mostrar(){
			for (int i = 0; i < aplicaciones.length; i++) {
				System.out.print(aplicaciones[i]);
				System.out.print(cantidad[i] + " ");
				for (int j = 0; j < computadoras[i].length; j++) {
					System.out.print(computadoras[i][j]);
				}
				System.out.println(";");
			}
		}
	}
}
