package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeneradorGrafos {

	private static int CANT_NODOS = 100;   //DICE DE CUANTOS NODOS SERA (ESTA EN 100, PESO SE MODIFICA DE ESTA VARIABLE) 
	private static int RANDOM_PESO = 3000;   //ME DA VALORES DE PESO ENTRE 1 Y 3000 (SI QUIEREN CAMBIAR, CAMBIAN ESTA VARIABLE)
	
	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("ej.in", true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		//generarGrafo();
		//System.out.println();
		//generarGrafoMaloParaMatching();
		//generaParametrosGrasp(200);
		for(int cantNodos = 100;cantNodos <= 2000; cantNodos+=100){
			System.out.println(cantNodos);
			generarGrafoArcosYAristas(cantNodos,bw);
		}
		bw.close();
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
			
			cantidadAristas = maxGradoNodo.intValue() < 1 ? 1: maxGradoNodo.intValue();
			for (j = 0; j < cantidadAristas; j++)
			{
				int segundaNodoArista = i+j+1;
				//peso = Math.random() * RANDOM_PESO < 1 ? 1: Math.random() * RANDOM_PESO;
				peso = Math.random() * RANDOM_PESO;
				if(peso < 1){
					peso = 1.0;
				}
				aristas.add(i + " " + segundaNodoArista + " " + peso.intValue());
			}
		}
		
		System.out.println(CANT_NODOS + " " + aristas.size() + " 0");
		for (i = 0; i < aristas.size(); i++)
		{
			System.out.println(aristas.get(i));
		}
	}
	
	private static void generarGrafoArcosYAristas(int cantNodos,BufferedWriter bw) throws IOException
	{
		Double maxGradoNodo;		//cuantos adyacentes tendra el nodo i
		Double peso;				//peso de la arista
		int cantidadAristas;		//el random puede dar menor que uno, por lo que, en esos casos, acomodo este valor a 1. Caso contrario lo paso solo a enteros al ramdom
		List<String> aristas = new ArrayList<String>();
		List<String> arcos = new ArrayList<String>();
		int i, j;				
		
		for (i = 0; i < cantNodos-1; i++)			
		{
			maxGradoNodo = Math.random() * (cantNodos - (i+1));
			
			cantidadAristas = maxGradoNodo.intValue() < 1 ? 1: maxGradoNodo.intValue();
			
			//mando la primera a arista para asegurarme fuertementeConexo
			peso = Math.random() * RANDOM_PESO;
			if(peso < 1){
				peso = 1.0;
			}
			aristas.add(i + " " + (i+1) + " " + peso.intValue());
			for (j = 1; j < cantidadAristas; j++)
			{
				int segundaNodoArista = i+j+1;
				//peso = Math.random() * RANDOM_PESO < 1 ? 1: Math.random() * RANDOM_PESO;
				peso = Math.random() * RANDOM_PESO;
				if(peso < 1){
					peso = 1.0;
				}
				if(((Double)(Math.random() * 100)).intValue()%2==0){//arista o arco
					aristas.add(i + " " + segundaNodoArista + " " + peso.intValue());
				}else{
					if(((Double)(Math.random() * 100)).intValue()%2==0){//ida o vuelta
						arcos.add(i + " " + segundaNodoArista + " " + peso.intValue());
					}else{
						arcos.add(segundaNodoArista + " " + i + " " + peso.intValue());
					}
					
				}
				
			}
		}
		//System.out.println(cantNodos + " " + aristas.size() + " " + arcos.size());
		bw.write(cantNodos + " " + aristas.size() + " " + arcos.size() + "\n");
		for (i = 0; i < aristas.size(); i++)
		{
			//System.out.println(aristas.get(i));
			bw.write(aristas.get(i)+"\n");
		}
		for (i = 0; i < arcos.size(); i++)
		{
			//System.out.println(arcos.get(i));
			bw.write(aristas.get(i)+"\n");
		}
	}
	
	private static void generarGrafoMaloParaMatching(){
		Double peso;
		int nodoFin = CANT_NODOS-1;
		int x=0,y=0,cantAristas=0;
		if(CANT_NODOS%2==0){
			y=x=CANT_NODOS/2 -1;
		}else{
			x= (CANT_NODOS-1)/2;
			y= (CANT_NODOS-3)/2;
		}
		cantAristas = x + y + x*y +1;
		System.out.println(CANT_NODOS+" "+"0"+" "+cantAristas);
		peso = Math.random() * RANDOM_PESO;
		if(peso < 1) peso = 1.0;
		System.out.println(nodoFin+" "+0+" "+peso.intValue());
		for(int i = 1; i <= x;++i){
			peso = Math.random() * RANDOM_PESO;
			if(peso < 1) peso = 1.0;
			System.out.println(0+" "+i+" "+peso.intValue());
			for(int j= x+1;j <= x+y;++j){
				peso = Math.random() * RANDOM_PESO;
				if(peso < 1) peso = 1.0;
				System.out.println(i+" "+j+" "+peso.intValue());
			}
		}
		for(int i = x+1; i <= x+y;++i){
			peso = Math.random() * RANDOM_PESO;
			if(peso < 1) peso = 1.0;
			System.out.println(i+" "+nodoFin+" "+peso.intValue());
		}
	}
	
	private static void generaParametrosGrasp(int n){
		int parametroGRASP;
		for(int i =1; i< n;++i){
			parametroGRASP = ((Double)(Math.random() * 1000000)).intValue();
			System.out.print(parametroGRASP+", ");
		}
		parametroGRASP = ((Double)(Math.random() * 1000000)).intValue();
		System.out.print(parametroGRASP);
	}
}
