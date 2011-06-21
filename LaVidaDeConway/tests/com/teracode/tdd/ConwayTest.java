package com.teracode.tdd;

import org.junit.Test;
import static org.junit.Assert.*;


public class ConwayTest {

	@Test
	public void testInitialConway(){
		Conway conway = new Conway();
		
		for (int i = 0; i < Conway.LENGHT; i++) {
			for (int j = 0; j < Conway.LENGHT; j++) {
				assertFalse(conway.get()[i][j]);
			}	
		}
	}
	
	@Test
	public void testSetValue(){
		Conway conway = new Conway();
		assertFalse(conway.get()[1][1]);
		conway.set(1,1,true);
		assertTrue(conway.get()[1][1]);
	}
	
	@Test
	public void testKill(){
		Conway conway = new Conway();
		conway.set(1,1,true);
		assertTrue(conway.get()[1][1]);
		conway.kill(1,1);
		assertFalse(conway.get()[1][1]);
	}
	
	@Test
	public void testIsAlive(){
		Conway conway = new Conway();
		assertFalse(conway.isAlive(1,1));
	}
	
	@Test
	public void testVecinosVivos(){
		
		Conway conway = new Conway();
		conway.set(0, 0, true);
		conway.set(0, 1, true);
		conway.set(1, 1, true);
		assertEquals(2,conway.vecinosVivos(1,1));
	}
	
	@Test
	public void testCasoReglaMuertePorSoledad(){
		Conway conway = new Conway();
		inicializarEnLaDiagonal(conway);
		conway.set(9, 0, true);
		
		Conway conwayInicial = new Conway(conway.get());
		
		conway.step();
	
		for (int i = 0; i < Conway.LENGHT; i++) {
			for (int j = 0; j < Conway.LENGHT; j++) {
				if(conwayInicial.get()[i][j]){
					int vecinos = conwayInicial.vecinosVivos(i,j);
					if( vecinos == 2 || vecinos == 2 ){
						assertFalse(conway.get()[i][j]);
					}
				}
			}	
		}
		
	}
	
	private void inicializarEnLaDiagonal(Conway conway){
		//Genera vivos en las posiciones pares
		for (int i = 0; i < Conway.LENGHT; i++) {
			for (int j = 0; j < Conway.LENGHT; j++) {
				if( i==j ){
					conway.set(i, j, true);
				}
			}	
		}
		conway.set(9, 0, true);
	}
	/*@Test
	public void testCasoReglaMuerte(){
		Conway conway = new Conway();
		conway.set(0, 0, true);
		conway.set(0, 1, true);
		
		boolean tableroAnterior[][] = conway.get();
		conway.step();
		boolean tableroPosterior[][] = conway.get();
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if(conway.vecinosVivos()){
					
				}
			}
		}
	}
	*/
}
