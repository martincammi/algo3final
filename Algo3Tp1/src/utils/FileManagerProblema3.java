package utils;

import java.io.IOException;

public class FileManagerProblema3 extends FileManager {

	public static String FILE_IN = "Ej3.in";
	public static String FILE_OUT = "Ej3.out";
	private String salida = ""; 

	public FileManagerProblema3(){
		super(FILE_IN);
		setCutCharacter("0");
	}
	
	public FileManagerProblema3(String filename) {
		super(filename);
		setCutCharacter("0");
	}
	
	public int getInstancia() throws IOException {
		String instancia = super.hayMasInstancias();
		return Integer.parseInt(instancia);
	}

	public void guardarSalida(String data) throws IOException{
		salida = salida + data + "\n";	
	}

	public void escribirSalida() throws IOException{
		super.escribirArchivo(salida, FILE_OUT);
		salida = "";
	}
	
}
