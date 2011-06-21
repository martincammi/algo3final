package com.teracode.tdd;

public class Conway {
	
	public static int LENGHT = 10;
	boolean tablero[][] = new boolean[LENGHT][LENGHT];
	
	public Conway(){}
	
	public Conway(boolean[][] tableroNuevo){
		copiaTablero(tableroNuevo);
	}
	
	public boolean[][] get(){
		return tablero; 
	}
	
	public void set(int i, int j, boolean valor){
		tablero[i][j] = valor; 
	}
	
	public void kill(int i, int j){
		tablero[i][j] = false;
	}
	
	public boolean isAlive(int i, int j){
		return tablero[i][j];
	}
	
	public int vecinosVivos(int i, int j){
		
		int vivos = 0;
		
		if(i>0 && j>0){
			if(tablero[i-1][j-1]){
				vivos++;
			}
		}
		
		if(i>0){
			if(tablero[i-1][j]){
				vivos++;
			}
		}
		
		if(i>0){
			if(tablero[i-1][j+1]){
				vivos++;
			}
		}
		
		if(j>0){
			if(tablero[i][j-1]){
				vivos++;
			}
		}
		
		
		if(tablero[i][j+1]){
			vivos++;
		}
		
		if(j>0){
			if(tablero[i+1][j-1]){
				vivos++;
			}
		}
		
		if(tablero[i+1][j]){
			vivos++;
		}
		
		if(tablero[i+1][j+1]){
			vivos++;
		}
		
		return vivos; 
	}
	
	public void step(){
		Conway conwayInicial = new Conway(tablero);
		
		for (int i = 0; i < Conway.LENGHT; i++) {
			for (int j = 0; j < Conway.LENGHT; j++) {
				if(conwayInicial.isAlive(i,j)){
					int vecinos = conwayInicial.vecinosVivos(i,j);
					if(vecinos == 0 || vecinos == 2){
						kill(i,j);
					}
				}
			}	
		}
	}
	
	private void copiaTablero(boolean[][] tableroNuevo){
		for (int i = 0; i < Conway.LENGHT; i++) {
			for (int j = 0; j < Conway.LENGHT; j++) {
				tablero[i][j] = tableroNuevo[i][j];
			}	
		}
	}
	
	
}
