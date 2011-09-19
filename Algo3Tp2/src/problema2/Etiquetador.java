package problema2;

/*
 * Clase etiquetador, comienza etiquetando desde 1
 */
public class Etiquetador {

	public static int ETIQUETA_NULA = 0;
	private static int counter = ETIQUETA_NULA;
	
	public static int getEtiqueta(){
		counter++;
		return counter;
	}
	
	public static void reset(){
		counter = ETIQUETA_NULA;
	}
	

}
