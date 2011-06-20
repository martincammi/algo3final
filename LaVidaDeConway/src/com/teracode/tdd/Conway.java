package com.teracode.tdd;

public class Conway {
	
	public static int LENGHT = 10;
	boolean tablero[][] = new boolean[LENGHT][LENGHT];
	
	public Conway(){
		for (int i = 0; i < LENGHT; i++) {
			for (int j = 0; j < LENGHT; j++) {
				tablero[i][j] = false;
			}	
		}
	}
	
	public boolean[][] get(){
		return tablero; 
	}
	
	public void set(int i, int j, boolean valor){
		tablero[i][j] = valor; 
	}
	
	public void kill(int i, int j, boolean valor){
		tablero[i][j] = false; 
	}
	
	public boolean isAlive(int i, int j){
		return tablero[i][j]; 
	}
	
	public int vecinosVivos(int f, int c){
		
		int vivos = 0;
		
		if(f>0 && c>0){
			if(tablero[f-1][c-1]){
				vivos++;
			}
		}
		
		if(f>0){
			if(tablero[f-1][c]){
				vivos++;
			}
		}
		
		if(f>0){
			if(tablero[f-1][c+1]){
				vivos++;
			}
		}
		
		if(c>0){
			if(tablero[f][c-1]){
				vivos++;
			}
		}
		
		
		if(tablero[f][c+1]){
			vivos++;
		}
		
		if(c>0){
			if(tablero[f+1][c-1]){
				vivos++;
			}
		}
		
		if(tablero[f+1][c]){
			vivos++;
		}
		
		if(tablero[f+1][c+1]){
			vivos++;
		}
		
		return vivos; 
	}
	
}
