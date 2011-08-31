package problema2;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests de Correctitud para el algoritmo de la pizza
 */
public class TestPizza {

	private final String NEW_LINE = "\n";
	
	@Test
	public void testPizzaPeorCaso(){
		String data = GeneradorDeCasos.PeorCaso(26, 27);
		Pizza pizza = new Pizza(data);
		assertEquals(Pizza.SIN_SOLUCION, pizza.resolver());
		System.out.println(GeneradorDeCasos.PeorCaso(26, 27));
	}
	@Test
	public void testPizzaPeorCaso2(){
		String data = GeneradorDeCasos.PeorCaso(26, 27);
		Pizza pizza = new Pizza(data);
		assertEquals(Pizza.SIN_SOLUCION, pizza.resolver());
		System.out.println(GeneradorDeCasos.PeorCaso(26, 400));
	}
	
	//Casos positivos
	@Test
	public void testPizzaUnoSoloUnGustoA(){
		String preferencias[] = {"+A"};
		String data = parser(1, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("A", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloUnGustoB(){
		String preferencias[] = {"+B"};
		String data = parser(2, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("B", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloUnGustoZ(){
		String preferencias[] = {"+Z"};
		String data = parser(26, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("Z", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloDosGustos(){
		String preferencias[] = {"+A+A"};
		String data = parser(1, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("A", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloDosGustosDiferentes(){
		String preferencias[] = {"+A+B"};
		String data = parser(2, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("A", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloDosGustosDiferentes2(){
		String preferencias[] = {"+B+A"};
		String data = parser(2, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("A", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloDosGustosDiferentes3(){
		String preferencias[] = {"+C+B"};
		String data = parser(3, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("C", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloTresGustosDiferentes(){
		String preferencias[] = {"+A+B+C"};
		String data = parser(3, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("A", pizza.resolver());
	}

	@Test
	public void testPizzaUnoSoloTresGustosDiferentes2(){
		String preferencias[] = {"+B+C+D"};
		String data = parser(4, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("B", pizza.resolver());
	}
	
	@Test
	public void testPizzaDosSolosUnGusto(){
		String preferencias[] = {"+A","+B"};
		String data = parser(2, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("AB", pizza.resolver());
	}
	
	@Test
	public void testPizzaDosSolosUnGusto2(){
		String preferencias[] = {"+B","+A"};
		String data = parser(2, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("AB", pizza.resolver());
	}
	
	@Test
	public void testPizzaDosSolosUnGusto3(){
		String preferencias[] = {"+Z+X","+X+Z"};
		String data = parser(26, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("X", pizza.resolver());
	}
	
	//Casos negativos
	@Test
	public void testPizzaUnoSoloUnDisGusto(){
		String preferencias[] = {"-A"};
		String data = parser(1, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloUnDisGusto2(){
		String preferencias[] = {"-B"};
		String data = parser(2, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("A", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloUnDisGusto3(){
		String preferencias[] = {"-C"};
		String data = parser(3, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("A", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloDosDisGustos(){
		String preferencias[] = {"-A-B"};
		String data = parser(2, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloDosDisGustos2(){
		String preferencias[] = {"-A-B"};
		String data = parser(3, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("C", pizza.resolver());
	}
	
	@Test
	public void testPizzaDosSolosUnDisGusto(){
		String preferencias[] = {"-A","-A"};
		String data = parser(1, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("", pizza.resolver());
	}
	
	@Test
	public void testPizzaDosSolosUnDisGusto2(){
		String preferencias[] = {"-A","-A"};
		String data = parser(2, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("B", pizza.resolver());
	}
	
	@Test
	public void testPizzaTresSolosTresDisGustos(){
		String preferencias[] = {"-A","-B","-C"};
		String data = parser(3, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("", pizza.resolver());
	}
	
	@Test
	public void testPizzaTresSolosTresDisGustos2(){
		String preferencias[] = {"-A","-B","-C"};
		String data = parser(4, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("D", pizza.resolver());
	}
	
	//Casos mixtos
	@Test
	public void testPizzaUnoSoloUnGustoUnDisgusto(){
		String preferencias[] = {"-A+B"};
		String data = parser(2, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("B", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloUnGustoUnDisgusto2(){
		String preferencias[] = {"-A+C"};
		String data = parser(3, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("C", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloUnGustoUnDisgusto3(){
		String preferencias[] = {"-A+Z"};
		String data = parser(26, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("Z", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloUnGustoUnDisgusto4(){
		String preferencias[] = {"+Z-A"};
		String data = parser(26, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("Z", pizza.resolver());
	}

	@Test
	public void testPizzaUnoSoloUnGustoUnDisgusto5(){
		String preferencias[] = {"-Z+A"};
		String data = parser(26, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("A", pizza.resolver());
	}
	
	//Caso Patologico
	@Test
	public void testPizzaDosSolosUnGustoySuDisgusto(){
		String preferencias[] = {"+A","-A"};
		String data = parser(1, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals(Pizza.SIN_SOLUCION, pizza.resolver());
	}
	
	@Test
	public void testPizzaDosSolosUnGustoySuDisgusto2(){
		String preferencias[] = {"+B","-B"};
		String data = parser(2, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals(Pizza.SIN_SOLUCION, pizza.resolver());
	}
	
	@Test
	public void testPizzaDosSolosUnGustoySuDisgusto3(){
		String preferencias[] = {"+Z","-Z"};
		String data = parser(26, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals(Pizza.SIN_SOLUCION, pizza.resolver());
	}
	
	@Test
	public void testPizzaDosSolosUnGustoySuDisgusto4(){
		String preferencias[] = {"+A+B","-C-D"};
		String data = parser(4, preferencias);
		Pizza pizza = new Pizza(data);
		assertEquals("A", pizza.resolver());
	}
	
	/**
	 * Convierte en string los datos de entrada
	 * @param total la cantidad de letras a usar
	 * @param array las preferencias de cada amigo
	 * @return Devuelve un string con los valores que serán entrada 
	 */
	public String parser(int total, String[] array){
		StringBuffer data = new StringBuffer();
		data.append(total);
		data.append(NEW_LINE);
		
		for (int i = 0; i < array.length; i++) {
			data.append(array[i]);
			data.append(";");
			data.append(NEW_LINE);
		}
		data.append(".");
		return data.toString();
	}

	
}
