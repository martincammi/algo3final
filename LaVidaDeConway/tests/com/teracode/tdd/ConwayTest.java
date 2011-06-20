package com.teracode.tdd;

import org.junit.Test;


public class ConwayTest {

	@Test
	public void testVecinosVivos(){
		Conway conway = new Conway();
		conway.set(0, 0, true);
		conway.set(0, 1, true);
		
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
