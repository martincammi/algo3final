package com.teracode.tdd;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Converts from integer numbers to roman numbers
 * @author martin.cammi
 *
 */
public class RomanConverterTest {

	protected RomanConverter romanConverter = new RomanConverter();
	
	@Test
	public void testConvertToOne(){
		assertEquals("I", romanConverter.convert(1));
	}
	
	@Test
	public void testConvertToTwo(){
		assertEquals("II", romanConverter.convert(2));
	}
	
	@Test
	public void testConvertToThree(){
		assertEquals("III", romanConverter.convert(3));
	}
	
	@Test
	public void testConvertToFour(){
		assertEquals("IV", romanConverter.convert(4));
	}
	
	@Test
	public void testConvertToFive(){
		assertEquals("V", romanConverter.convert(5));
	}
	
	@Test
	public void testConvertToSix(){
		assertEquals("VI", romanConverter.convert(6));
	}
	
	@Test
	public void testConvertToSeven(){
		assertEquals("VII", romanConverter.convert(7));
	}
	
	@Test
	public void testConvertToEight(){
		assertEquals("VIII", romanConverter.convert(8));
	}
	
	@Test
	public void testConvertToNine(){
		assertEquals("IX", romanConverter.convert(9));
	}
	
	@Test
	public void testConvertToCero(){
		assertEquals("", romanConverter.convert(0));
	}
	
	@Test
	public void testConvertToTen(){
		assertEquals("X", romanConverter.convert(10));
	}
	
	@Test
	public void testConvertToEleven(){
		assertEquals("XI", romanConverter.convert(11));
	}
	
	@Test
	public void testConvertToTwelve(){
		assertEquals("XII", romanConverter.convert(12));
	}
	
	@Test
	public void testConvertToFourteen(){
		assertEquals("XIV", romanConverter.convert(14));
	}
	
	@Test
	public void testConvertToTwenty(){
		assertEquals("XX", romanConverter.convert(20));
	}
	
	@Test
	public void testConvertToFourtyNine(){
		assertEquals("ID", romanConverter.convert(49));
	}
	
	@Test
	public void testConvertToFifty(){
		assertEquals("D", romanConverter.convert(50));
	}
	
	@Test
	public void testConvertToSeventyNine(){
		assertEquals("DXXIX", romanConverter.convert(79));
	}
}
