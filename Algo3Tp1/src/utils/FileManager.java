package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Reads form and write to file.
 *
 */
public class FileManager {

	private static FileReader fr;
	private static BufferedReader br;
	private String fileNameIn;
	private String cutCharacter = ".";
	
	public FileManager(String filename){
		this.fileNameIn = filename;
	}
	
	public void abrirArchivo()	throws FileNotFoundException {
		fr = new FileReader(this.fileNameIn);
		br = new BufferedReader(fr);
	}
	
	private void borrarArchivo(String filename){
		File f = new File(filename);
		f.delete();
	}

	public void escribirArchivo(String data, String fileName) throws IOException {
		borrarArchivo(fileName);
		FileWriter fw = new FileWriter(fileName, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(data + "\n");
		bw.close();
	}
	
	public void cerrarArchivo() throws IOException {
		fr.close();
	}
	
	public void setCutCharacter(String cutCharacter){
		this.cutCharacter = cutCharacter;
	}
	
	public String hayMasInstancias() throws IOException
	{
		String line = br.readLine();

		if (line == null || line.equals(this.cutCharacter)){
			return this.cutCharacter;
		}
		
		return line;
	}
	
	
}
