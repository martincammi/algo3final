package problema1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class Ej1 {

	private static FileReader fr;
	private static BufferedReader br;
	/*Datos de entrada*/
	private static int l;
	private static int a;
	/*Fin de datos de entrada*/
	private static int QtyOps; //guardo la cantidad de operaciones
	public static Prisma[][] grilla;
	public static Prisma maximo;
	
	//abre el archivo
	private static void abrirInstancias() throws FileNotFoundException {
		fr = new FileReader("Ej1.in");
		br = new BufferedReader(fr);
	}

//	//cierra el archivo
	private static void cerrarInstancias() throws IOException {
		fr.close();
	}
	
	//escribe el resultado en el archivo out. Primero la cantidad de instancias, y luego los pares de soluciones
	public static void escribirEnLog(String texto) throws IOException {
		FileWriter fw = new FileWriter("Ej1.log", true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(texto+"\n");
		for(int i=0;i< Ej1.l;i++){
			for(int j=0;j< Ej1.a;j++){
				bw.write(grilla[i][j]+"|");
			}
			bw.write("\n");
		}
		bw.write("\n");
		bw.close();
	}
	
	public static void escribirSalida() throws IOException {
		FileWriter fw = new FileWriter("Ej1.out", true);
		BufferedWriter bw = new BufferedWriter(fw);
		int n = 1;
		Stack<Prisma.Posicion> sol = new Stack<Prisma.Posicion>();
		while(!maximo.desdeDondeVino.equals(Prisma.INICIO)){
			n++;
			sol.push(maximo.pos);
			maximo = grilla[maximo.pos.i+maximo.desdeDondeVino.i][maximo.pos.j+maximo.desdeDondeVino.j];
			QtyOps++;
		}
		sol.push(maximo.pos);
		QtyOps++;
		bw.write(n+" ");
		while(!sol.isEmpty()){
			bw.write(sol.pop()+" ");
			//QtyOps++;
		}
		bw.write("\n");
		bw.close();
	}
	
	public static void limpiarSalida() throws IOException {
		File f = new File("Ej1.out");
		f.delete();
	}
	
	public static void limpiarLog() throws IOException {
		File f = new File("Ej1.log");
		f.delete();
	}
	
	private static boolean hayMasInstancias() throws IOException {
		
		String line = br.readLine();
		if (line == null){
			return false;
		}
		
		if ("".equals(line)){
			line = br.readLine();
		}
		
		String tams[] = line.split(" ");
		Ej1.l = Integer.parseInt(tams[0]);
		Ej1.a = Integer.parseInt(tams[1]);
		grilla = new Prisma[l][a];
		maximo = new Prisma();
		for(int i=0;i< Ej1.l;i++){
			for(int j=0;j< Ej1.a;j++){
				line = br.readLine();
				String par[] = line.split(" ");
				int h = Integer.parseInt(par[0]);
				int r = Integer.parseInt(par[1]);
				Prisma pris = new Prisma(h, r, i, j);
				grilla[i][j] = pris;
				//QtyOps++;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		try { 
			abrirInstancias();
			limpiarSalida();
			limpiarLog();
			QtyOps = 0;
			while(hayMasInstancias()){
				escribirEnLog("Instancia");
				for(int i=0;i< Ej1.l;i++){
					for(int j=0;j< Ej1.a;j++){
					 	 calcularPrisma(i, j);
						 //QtyOps++;
					}
				}
				escribirSalida();
				escribirEnLog("Complejidad="+QtyOps);
				QtyOps=0;
			}
			cerrarInstancias();
		}
		catch (FileNotFoundException e) {
			System.out.println("No se puede abrir el archivo in");
		} catch (IOException e) {
			System.out.println("Error manipulando el archivo");
		} catch (NumberFormatException nfe) {
			System.out.println("Error en el formato del archivo");
		}
	}

	/**
	 * Devuelve para el prisma en la posición i,j la máxima cantidad de destrucción que podría ocasionar
	 */
	private static Prisma calcularPrisma(int i, int j) {
		Prisma prismaRet = new Prisma();
		Prisma prismaActual = grilla[i][j];
		
		QtyOps++;
		if(prismaActual.destruccionAcumulada > Prisma.DESTRUCCION_CENTINELA){ //Si ya fue calculada la destrucción de este prisma devolverla
			prismaRet = prismaActual;
		}else{ //Sino fue calculada, preguntar a todos sus adyacentes
			Prisma prismaArriba, prismaAbajo, prismaIzquierda, prismaDerecha;
			
			prismaArriba	= obtenerPrisma(prismaActual, Prisma.ARRIBA);
			prismaAbajo		= obtenerPrisma(prismaActual, Prisma.ABAJO 	);
			prismaIzquierda	= obtenerPrisma(prismaActual, Prisma.IZQUIERDA);
			prismaDerecha	= obtenerPrisma(prismaActual, Prisma.DERECHA);
			
			prismaRet = Prisma.prismaConMasDestruccion(prismaArriba, prismaDerecha, prismaAbajo, prismaIzquierda);
			
			if(prismaActual.destruccionAcumulada >= prismaRet.destruccionAcumulada){ // Caso en que el por el prisma actual haya hecho mas daño  
				prismaActual.destruccionAcumulada = prismaActual.rango;
				prismaActual.desdeDondeVino = Prisma.INICIO;
			}else{
				prismaActual.destruccionAcumulada = prismaActual.rango + prismaRet.destruccionAcumulada;
				prismaActual.desdeDondeVino = new Prisma.Posicion(prismaRet.pos.i-i, prismaRet.pos.j-j);
			}
			prismaRet = prismaActual;
		}
		
		if(prismaRet.destruccionAcumulada > maximo.destruccionAcumulada){
			maximo = prismaRet;
		}
		
		return prismaRet;
	}

	/**
	 * Indica si la posición de donde pudo haber venido es valida o no.
	 */
	private static boolean pudoHaberVenidoDe(Prisma prisma, Prisma.Posicion p) {
		int i = prisma.pos.i + p.i;
		int j = prisma.pos.j + p.j;
		if(i >= 0 && i < Ej1.l && j>= 0 && j < Ej1.a){
			return grilla[i][j].altura > prisma.altura;
		}else{
			return false;
		}
	}

	/**
	 * Devuelve el prisma de la posición indicada por parámetro, si el prisma no existe devuelve uno default.   
	 */
	private static Prisma obtenerPrisma(Prisma prisma, Prisma.Posicion p) {
		Prisma prismaRet;
		int i = prisma.pos.i + p.i;
		int j = prisma.pos.j + p.j;
		
		if (pudoHaberVenidoDe(prisma, p)){
			prismaRet = calcularPrisma( i , j);
		}else{
			prismaRet = new Prisma();
		}
		return prismaRet;
	}
}