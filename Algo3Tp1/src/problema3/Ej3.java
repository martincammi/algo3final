package problema3;

import java.io.FileNotFoundException;
import java.io.IOException;

import utils.FileManagerProblema3;
import utils.Modelo;


public class Ej3 {
	
	public static String SIN_SOLUCION = "";
	public Modelo model = new Modelo();
	
	

	public static void main(String[] args) {
		
		try{
			FileManagerProblema3 fm = new FileManagerProblema3();
			fm.abrirArchivo();
			
			Ej3 goldBach = new Ej3();
			String solucion;
			
			int numeroPar = fm.getInstancia();
			while(numeroPar != 0){
				
				solucion = goldBach.conjeturaDeGoldbach(numeroPar);
				fm.guardarSalida(solucion);
				numeroPar = fm.getInstancia();
			}
			
			fm.escribirSalida();
			fm.cerrarArchivo();
		
		}catch (FileNotFoundException e) {
			System.out.println("No se puede abrir el archivo in");
		} catch (IOException e) {
			System.out.println("Error manipulando el archivo");
		} catch (NumberFormatException nfe) {
			System.out.println("Error en el formato del archivo");
		}	
	}
	
	/**
	 * Determina si la conjetura de Goldbach es cierta para un numero positivos dado.
	 * @param n el numero para saber si se cumple la conjetura
	 * @return devuelve los dos primos que cumplen si la conjetura es valida, "" sino. 
	 */
	public String conjeturaDeGoldbach(int n){
		
		if(n % 2 != 0 || n == 2 || n == 0 ){ //Poda3: Exluir los pares 
			return SIN_SOLUCION;
		}
		
		if(n == 4){  //Poda4: caso de 4.
			return "2 2";
		}
		
		int mitad = n/2; //Poda1: Exluir los simétricos.
		
		for(int i = 3; i <= mitad; i=i+2){ //Poda2: Exluir el caso de 1
			
			if(i == (n-i)){ //Poda6: Si ambos numeros son iguales
				if(esPrimo(i)){
					return String.valueOf(i) + " " + String.valueOf(i);
				}	
			}
			
			if(esPrimo(i) && esPrimo(n-i)){
				return String.valueOf(i) + " " + String.valueOf(n-i);
			}
		}
		
		return SIN_SOLUCION;
	}
	
	/**
	 * Indica si el numero es primo.
	 * @param n el numero a testear
	 * @return true si es primo, false sino.
	 */
	public boolean esPrimo(int n){

		//Caso 2
		if(n == 2){
			return true;
		}
		
		//Caso 1 y Pares
		if(n < 2 || n % 2 == 0){
			return false;
		}
		
		return (primerDivisor(n) == n);
	}
	
	/**
	 * Dado un numero positivo mayor a 4 devuelve el primer divisor diferente de 1.
	 * @param n el numero en cuestion
	 * @return
	 */
	public int primerDivisor(int n){
		
		int tope = (int) Math.sqrt(n); //Poda5: solo divisores menores a la raiz de n.
		
		for (int i = 3; i <= tope; i=i+2) {
			model.addUcomplex();
			if(n % i == 0){
				return i;
			}
		}
		
		return n;
	}
	
	public int getComplex(){
		return model.getUniformComplex();
	}
	
	public void resetComplex(){
		model.reset();
	}
}
