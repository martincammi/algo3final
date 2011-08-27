package problema3;

import java.util.ArrayList;
import java.util.List;


public class GoldBachHelper {
	
	public static void main(String[] args) {
		System.out.println("BEGIN");
		//generarSumandos(3,1000);
		//generarSumandosMayores100(1000000,10000000);
		conjeturaDeGarbi(3,1000);
		System.out.println("END");
	}
	
	/**
	 * Dado un n devuelve la lista de todos los sumandos primos
	 * @param n
	 * @return
	 */
	public static List<Integer> conjeturaDeGoldbachList(int n){
		Goldbach goldbach  = new Goldbach();
		List<Integer> list = new ArrayList<Integer>();
		
		if(n % 2 != 0 || n == 2 || n == 0 ){ //Poda3: Exluir los pares 
			return list;
		}
		
		if(n == 4){  //Poda4: caso de 4.
			list.add(2);
			list.add(2);
			return list;
		}
		
		int tope = n/2; //Poda1: Exluir los simÃ©tricos.
		
		for(int i = 3; i <= tope; i++){ //Poda2: Exluir el caso de 1
			if(goldbach.esPrimo(i) && goldbach.esPrimo(n-i)){
				list.add(i);
				list.add(n-i);
			}
		}
		
		return list;
	}
	
	public static void generarSumandos(int desde, int hasta){
		
		if(desde % 2 != 0){
			desde++;
		}
		
		for (int i = desde; i <= hasta; i=i+2) {
			List<Integer> list = conjeturaDeGoldbachList(i);
			
			if(list.size() > 1){
				System.out.print(i);
				for (Integer prime : list) {
					System.out.print("," + prime);
				}
			}
			System.out.println("");
		}
	}
	
	public static void generarSumandosMayores100(int desde, int hasta){
		
		if(desde % 2 != 0){
			desde++;
		}
		
		for (int i = desde; i <= hasta; i=i+2) {
			List<Integer> list = conjeturaDeGoldbachList(i);
			int MAX = 100;
			
			
			if(minimo(list) > MAX){
				System.out.print(i);
				for (Integer prime2 : list) {
					System.out.print("," + prime2);
				}
				System.out.println("");
				break;
			}
		}
	}
	
	private static int minimo(List<Integer> list){
		
		int minimo = 0;
		
		if(list.size() > 0){
			minimo = list.get(0); 
		}
			
		for (Integer number : list) {
			if(number < minimo){
				minimo = number;
			}
		}
		
		return minimo;
	}
	
	public static void conjeturaDeGarbi(int desde, int hasta){
		
		boolean cumplio = false;
		Goldbach goldbach = new Goldbach();
		
		if(desde % 2 != 0){
			desde++;
		}
		
		for (int i = desde; i <= hasta; i=i+2) {
			
			if((i/2)%2 == 0){
				List<Integer> list = conjeturaDeGoldbachList(i/2);
				
				for (Integer primo : list) {
					if(goldbach.esPrimo(i-primo)){
						cumplio = true;
						break;//Al menos uno cumplió ser sumando primo de n
					}	
				}
				
				if(!cumplio){
					
					List<Integer> listN = conjeturaDeGoldbachList(i);
					System.out.print(i);
					for (Integer prime : listN) {
						System.out.print("," + prime);
					}
					System.out.println("");		
					
					System.out.print(i/2);
					for (Integer prime : list) {
						System.out.print("," + prime);
					}
					System.out.println("");
					System.out.println("-------");
					
				}
			}
			cumplio = false;
		}
	}
	
}
