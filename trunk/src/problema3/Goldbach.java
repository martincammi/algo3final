package problema3;

import java.util.ArrayList;
import java.util.List;

public class Goldbach {
	
	public static int VACIO = 0;
	public static String SIN_SOLUCION = "";
	public int complex = 0; //Contador para la complejidad
	
	/**
	 * Determina si la conjetura de Goldbach es cierta para un numero positivos dado.
	 * @param n el número para saber si se cumple la conjetura
	 * @return devuelve los dos primeros primos que cumplen si la conjetura es válida, 0 sino. 
	 */
	public String conjeturaDeGoldbach(int n){
		
		if(n % 2 != 0 || n == 2 || n == 0 ){ //Poda3: Exluir los pares 
			return SIN_SOLUCION;
		}
		
		if(n == 4){  //Poda4: caso de 4.
			return "2 2";
		}
		
		int tope = n/2; //Poda1: Exluir los simétricos.
		
		for(int i = 3; i <= tope; i++){ //Poda2: Exluir el caso de 1
			if(esPrimo(i) && esPrimo(n-i)){
				return String.valueOf(i) + " " + String.valueOf(n-i);
			}
		}
		
		return SIN_SOLUCION;
	}
	
	
	public List<Integer> conjeturaDeGoldbachList(int n){
		
		List<Integer> list = new ArrayList();
		
		if(n % 2 != 0 || n == 2 || n == 0 ){ //Poda3: Exluir los pares 
			return list;
		}
		
		if(n == 4){  //Poda4: caso de 4.
			list.add(2);
			list.add(2);
			return list;
		}
		
		int tope = n/2; //Poda1: Exluir los simétricos.
		
		for(int i = 3; i <= tope; i++){ //Poda2: Exluir el caso de 1
			if(esPrimo(i) && esPrimo(n-i)){
				list.add(i);
				list.add(n-i);
			}
		}
		
		return list;
	}
	
	/**
	 * Determina si la conjetura de Goldbach es cierta para un numero dado implementando BackTracking
	 * @param n el numero para saber si se cumple la conjetura
	 * @return devuelve los dos primeros primos que cumplen si la conjetura es valida, 0 sino. 
	 */
	public String conjeturaDeGoldbachBack2(int n){
		
		if(n % 2 != 0 || n == 2 || n == 0 ){
			return SIN_SOLUCION;
		}
		
		if(n == 4){
			return "2 2";
		}
		
		MyInteger a = new MyInteger(3);
		MyInteger b = new MyInteger(n-3);
		
		if(conjeturaDeGoldbachBackTrackings(n,a,b)){
			return String.valueOf(a.value) + " " + String.valueOf(b.value);
		}
		
		return SIN_SOLUCION;
	}
	
	
	private boolean conjeturaDeGoldbachBackTrackings(int n, MyInteger a, MyInteger b){ 
		
		if(esPrimo(a.value) && esPrimo(b.value)){
			return true;
		}else{
			if(a.value+2 < n){
				a.mas(2);
				b.menos(2);
				return conjeturaDeGoldbachBackTrackings(n,a,b);
			}else{
				return false;
			}
		}
	}
	
	private class MyInteger{
		
		public int value = 0;
		
		public MyInteger(int n){
			this.value = n;
		}
		
		public void mas(int n) {
			this.value += n;
		}
		
		public void menos(int n) {
			this.value -= n;
		}
	}
	
	public boolean esPrimo(int n){

		if(n == 2){
			return true;
		}
		
		if(n < 2 || n % 2 == 0){
			return false;
		}
		
		return (primerDivisor(n) == n);
	}
	
	/**
	 * Dado un número positivo mayor a 1 devuelve el primer divisor diferente de 1.
	 * Si el número es menor o igual a 1 devuelve 0.
	 * @return
	 */
	public int primerDivisor(int n){
		
		int tope = (int) Math.sqrt(n); //Poda5: solo divisores menores a la raiz de n.
		
		if(n < 2){
			return 0;
		}
		
		if(n < 4){ //Caso 2 y 3
			return n;
		}
		
		for (int i = 2; i <= tope; i++) {
			complex++;
			if(n % i == 0){
				return i;
			}
		}
		
		return n;
	}
}
