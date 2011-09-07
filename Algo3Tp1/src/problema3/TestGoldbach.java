package problema3;
import static org.junit.Assert.*;
import org.junit.Test;


public class TestGoldbach {

	//Casos B�sicos.
	@Test
	public void goldBach1(){
		Ej3 goldBach = new Ej3();
		assertEquals("",goldBach.conjeturaDeGoldbach(1));
		showComplex(goldBach.getComplex());
	}
	
	@Test
	public void goldBach2(){
		Ej3 goldBach = new Ej3();
		assertEquals("",goldBach.conjeturaDeGoldbach(2));
		showComplex(goldBach.getComplex());
	}
	
	@Test
	public void goldBach3(){
		Ej3 goldBach = new Ej3();
		assertEquals("",goldBach.conjeturaDeGoldbach(3));
		showComplex(goldBach.getComplex());
	}
	
	@Test
	public void goldBach4(){
		Ej3 goldBach = new Ej3();
		assertEquals("2 2",goldBach.conjeturaDeGoldbach(4));
		showComplex(goldBach.getComplex());
	}
	
	@Test
	public void goldBach8(){
		Ej3 goldBach = new Ej3();
		assertEquals("3 5",goldBach.conjeturaDeGoldbach(8));
		showComplex(goldBach.getComplex());
	}
	
	@Test
	public void goldBach10(){
		Ej3 goldBach = new Ej3();
		assertEquals("3 7",goldBach.conjeturaDeGoldbach(10));
		showComplex(goldBach.getComplex());
	}
	
	@Test
	public void goldBach12(){
		Ej3 goldBach = new Ej3();
		assertEquals("5 7",goldBach.conjeturaDeGoldbach(12));
		showComplex(goldBach.getComplex());
	}
	
	@Test
	public void goldBach14(){
		Ej3 goldBach = new Ej3();
		assertEquals("3 11",goldBach.conjeturaDeGoldbach(14));
		showComplex(goldBach.getComplex());
	}
	
	@Test
	public void goldBach16(){
		Ej3 goldBach = new Ej3();
		assertEquals("3 13",goldBach.conjeturaDeGoldbach(16));
		showComplex(goldBach.getComplex());
	}
	
	@Test
	public void goldBach18(){
		Ej3 goldBach = new Ej3();
		assertEquals("5 13",goldBach.conjeturaDeGoldbach(18));
		showComplex(goldBach.getComplex());
	}

	@Test
	public void goldBach100(){
		Ej3 goldBach = new Ej3();
		assertEquals("3 97",goldBach.conjeturaDeGoldbach(100));
		showComplex(goldBach.getComplex());
	}
	
	@Test
	public void goldBach2020(){
		Ej3 goldBach = new Ej3();
		assertEquals("3 2017",goldBach.conjeturaDeGoldbach(2020));
		showComplex(goldBach.getComplex());
	}
	
	//Mediciones
	
	@Test
	public void goldBachMediciones(){
		Ej3 goldBach = new Ej3();
		int desde = 2;
		int hasta = 30;
		int gap = 32;
		
		String solucion = "";
		for (int i = desde; i < hasta; i=i+1) {
			int numeroPar = (int) Math.pow(2, i);
			solucion = goldBach.conjeturaDeGoldbach(numeroPar);
			System.out.print(numeroPar);
			System.out.print("\t");
			System.out.print(goldBach.getComplex());
			//System.out.print("\t");
			//System.out.println(solucion);
			goldBach.resetComplex();
			System.out.println("");
		}
		
	}
	
	//Casos Patológicos
	//Casos Promedio
	//Peores Casos
	
	private void showComplex(double complex) {
		System.out.println("Complejidad: " + complex);
	}
}
