package problema3;
import static org.junit.Assert.*;
import org.junit.Test;


public class TestGoldbach {

	//Casos Básicos.
	@Test
	public void goldBach1(){
		Goldbach goldBach = new Goldbach();
		assertEquals("",goldBach.conjeturaDeGoldbach(1));
		showComplex(goldBach.getComplex());
	}
	
	@Test
	public void goldBach2(){
		Goldbach goldBach = new Goldbach();
		assertEquals("",goldBach.conjeturaDeGoldbach(2));
		showComplex(goldBach.getComplex());
	}
	
	@Test
	public void goldBach3(){
		Goldbach goldBach = new Goldbach();
		assertEquals("",goldBach.conjeturaDeGoldbach(3));
		showComplex(goldBach.getComplex());
	}
	
	@Test
	public void goldBach4(){
		Goldbach goldBach = new Goldbach();
		assertEquals("2 2",goldBach.conjeturaDeGoldbach(4));
		showComplex(goldBach.getComplex());
	}
	
	@Test
	public void goldBach8(){
		Goldbach goldBach = new Goldbach();
		assertEquals("3 5",goldBach.conjeturaDeGoldbach(8));
		showComplex(goldBach.getComplex());
	}
	
	@Test
	public void goldBach10(){
		Goldbach goldBach = new Goldbach();
		assertEquals("3 7",goldBach.conjeturaDeGoldbach(10));
		showComplex(goldBach.getComplex());
	}
	
	@Test
	public void goldBach12(){
		Goldbach goldBach = new Goldbach();
		assertEquals("5 7",goldBach.conjeturaDeGoldbach(12));
		showComplex(goldBach.getComplex());
	}
	
	@Test
	public void goldBach14(){
		Goldbach goldBach = new Goldbach();
		assertEquals("3 11",goldBach.conjeturaDeGoldbach(14));
		showComplex(goldBach.getComplex());
	}
	
	@Test
	public void goldBach16(){
		Goldbach goldBach = new Goldbach();
		assertEquals("3 13",goldBach.conjeturaDeGoldbach(16));
		showComplex(goldBach.getComplex());
	}
	
	@Test
	public void goldBach18(){
		Goldbach goldBach = new Goldbach();
		assertEquals("5 13",goldBach.conjeturaDeGoldbach(18));
		showComplex(goldBach.getComplex());
	}

	@Test
	public void goldBach100(){
		Goldbach goldBach = new Goldbach();
		assertEquals("3 97",goldBach.conjeturaDeGoldbach(100));
		showComplex(goldBach.getComplex());
	}
	
	@Test
	public void goldBach2020(){
		Goldbach goldBach = new Goldbach();
		assertEquals("3 2017",goldBach.conjeturaDeGoldbach(2020));
		showComplex(goldBach.getComplex());
	}
	
	//Mediciones
	
	@Test
	public void goldBachMediciones(){
		Goldbach goldBach = new Goldbach();
		int tope = 1000;
		for (int i = 4; i < tope; i=i+2) {
			goldBach.conjeturaDeGoldbach(i);
			System.out.print(i);
			System.out.print(",");
			System.out.println(goldBach.getComplex());
			goldBach.resetComplex();
		}
		
	}
	
	//Casos PatolÃ³gicos
	//Casos Promedio
	//Peores Casos
	
	private void showComplex(double complex) {
		System.out.println("Complejidad: " + complex);
	}
}
