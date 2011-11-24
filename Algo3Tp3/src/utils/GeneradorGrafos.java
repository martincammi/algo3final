package utils;

import java.util.ArrayList;
import java.util.List;

public class GeneradorGrafos {

	private static int CANT_NODOS = 100;   //DICE DE CUANTOS NODOS SERA (ESTA EN 100, PESO SE MODIFICA DE ESTA VARIABLE) 
	private static int RANDOM_PESO = 3000;   //ME DA VALORES DE PESO ENTRE 1 Y 3000 (SI QUIEREN CAMBIAR, CAMBIAN ESTA VARIABLE)
	
	public static void main(String[] args) {
	
		generarGrafo();
		System.out.println("finish");
	}
	
	private static void generarGrafo()
	{
		Double maxGradoNodo;		//cuantos adyacentes tendra el nodo i
		Double peso;				//peso de la arista
		int cantidadAristas;		//el random puede dar menor que uno, por lo que, en esos casos, acomodo este valor a 1. Caso contrario lo paso solo a enteros al ramdom
		List<String> aristas = new ArrayList<String>();
		int i, j;				
		
		for (i = 0; i < CANT_NODOS-1; i++)			
		{
			maxGradoNodo = Math.random() * (CANT_NODOS - (i+1));
			peso = Math.random() * RANDOM_PESO < 1 ? 1: Math.random() * RANDOM_PESO;				
			
			cantidadAristas = maxGradoNodo.intValue() < 1 ? 1: maxGradoNodo.intValue();
			for (j = 0; j < cantidadAristas; j++)
			{
				int segundaArista = i+j+1;
				aristas.add(i + " " + segundaArista + " " + peso.intValue());
			}
		}
		
		System.out.println(CANT_NODOS + " " + aristas.size() + " 0");
		for (i = 0; i < aristas.size(); i++)
		{
			System.out.println(aristas.get(i));
		}
	}
}

