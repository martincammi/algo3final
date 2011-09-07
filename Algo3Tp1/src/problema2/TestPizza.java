package problema2;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests de Correctitud para el algoritmo de la pizza
 */
public class TestPizza {

	private final String NEW_LINE = "\n";
	private final String SIN_SOLUCION = "No hay Solución";
	
	//Casos positivos
	@Test
	public void testPizzaUnoSoloUnGustoA(){
		String preferencias[] = {"+A"};
		Pizza pizza = new Pizza(1);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("A", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloUnGustoB(){
		String preferencias[] = {"+B"};
		Pizza pizza = new Pizza(2);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("B", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloUnGustoZ(){
		String preferencias[] = {"+Z"};
		Pizza pizza = new Pizza(26);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("Z", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloDosGustos(){
		String preferencias[] = {"+A+A"};
		Pizza pizza = new Pizza(1);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("A", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloDosGustosDiferentes(){
		String preferencias[] = {"+A+B"};
		Pizza pizza = new Pizza(2);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("A", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloDosGustosDiferentes2(){
		String preferencias[] = {"+B+A"};
		Pizza pizza = new Pizza(2);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("A", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloDosGustosDiferentes3(){
		String preferencias[] = {"+C+B"};
		Pizza pizza = new Pizza(3);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("B", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloDosGustosDiferentes4(){
		String preferencias[] = {"+B+C"};
		Pizza pizza = new Pizza(3);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("B", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloTresGustosDiferentes(){
		String preferencias[] = {"+A+B+C"};
		Pizza pizza = new Pizza(3);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("A", pizza.resolver());
	}

	@Test
	public void testPizzaUnoSoloTresGustosDiferentes2(){
		String preferencias[] = {"+B+C+D"};
		Pizza pizza = new Pizza(4);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("B", pizza.resolver());
	}
	
	@Test
	public void testPizzaDosSolosUnGusto(){
		String preferencias[] = {"+A","+B"};
		Pizza pizza = new Pizza(2);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("AB", pizza.resolver());
	}
	
	@Test
	public void testPizzaDosSolosUnGusto2(){
		String preferencias[] = {"+B","+A"};
		Pizza pizza = new Pizza(2);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("AB", pizza.resolver());
	}
	
	@Test
	public void testPizzaDosSolosUnGusto3(){
		String preferencias[] = {"+Z+X","+X+Z"};
		Pizza pizza = new Pizza(26);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("X", pizza.resolver());
	}
	
	//Casos negativos
	@Test
	public void testPizzaUnoSoloUnDisGusto(){
		String preferencias[] = {"-A"};
		Pizza pizza = new Pizza(1);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloUnDisGusto2(){
		String preferencias[] = {"-B"};
		Pizza pizza = new Pizza(2);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloUnDisGusto3(){
		String preferencias[] = {"-C"};
		Pizza pizza = new Pizza(3);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloDosDisGustos(){
		String preferencias[] = {"-A-B"};
		Pizza pizza = new Pizza(2);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloDosDisGustos2(){
		String preferencias[] = {"-A-B"};
		Pizza pizza = new Pizza(3);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloUnDisGusto4(){
		String preferencias[] = {"-C-B-D"};
		Pizza pizza = new Pizza(4);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("", pizza.resolver());
	}
	
	@Test
	public void testPizzaDosSolosUnDisGusto(){
		String preferencias[] = {"-A","-A"};
		Pizza pizza = new Pizza(1);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("", pizza.resolver());
	}
	
	@Test
	public void testPizzaDosSolosUnDisGusto2(){
		String preferencias[] = {"-A","-A"};
		Pizza pizza = new Pizza(2);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("", pizza.resolver());
	}
	
	@Test
	public void testPizzaTresSolosTresDisGustos(){
		String preferencias[] = {"-A","-B","-C"};
		Pizza pizza = new Pizza(3);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("", pizza.resolver());
	}
	
	@Test
	public void testPizzaTresSolosTresDisGustos2(){
		String preferencias[] = {"-A","-B","-C"};
		Pizza pizza = new Pizza(4);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("", pizza.resolver());
	}
	
	@Test
	public void testPizzaTresSolosTresDisGustos3(){
		String preferencias[] = {"-A","-B","-C","-D"};
		Pizza pizza = new Pizza(4);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("", pizza.resolver());
	}
	
	//Casos mixtos
	@Test
	public void testPizzaUnoSoloUnGustoUnDisgusto(){
		String preferencias[] = {"-A+B"};
		Pizza pizza = new Pizza(2);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloUnGustoUnDisgusto2(){
		String preferencias[] = {"-A+C"};
		Pizza pizza = new Pizza(3);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloUnGustoUnDisgusto3(){
		String preferencias[] = {"-A+Z"};
		Pizza pizza = new Pizza(26);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("", pizza.resolver());
	}
	
	@Test
	public void testPizzaUnoSoloUnGustoUnDisgusto4(){
		String preferencias[] = {"+Z-A"};
		Pizza pizza = new Pizza(26);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("", pizza.resolver());
	}

	@Test
	public void testPizzaUnoSoloUnGustoUnDisgusto5(){
		String preferencias[] = {"-Z+A"};
		Pizza pizza = new Pizza(26);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("A", pizza.resolver());
	}
	
	//Caso Patologico
	@Test
	public void testPizzaDosSolosUnGustoySuDisgusto(){
		String preferencias[] = {"+A","-A"};
		Pizza pizza = new Pizza(1);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals(SIN_SOLUCION, pizza.resolver());
	}
	
	@Test
	public void testPizzaDosSolosUnGustoySuDisgusto2(){
		String preferencias[] = {"+B","-B"};
		Pizza pizza = new Pizza(2);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals(SIN_SOLUCION, pizza.resolver());
	}
	
	@Test
	public void testPizzaDosSolosUnGustoySuDisgusto3(){
		String preferencias[] = {"+Z","-Z"};
		Pizza pizza = new Pizza(26);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals(SIN_SOLUCION, pizza.resolver());
	}
	
	@Test
	public void testPizzaDosSolosUnGustoySuDisgusto4(){
		String preferencias[] = {"+A+B","-C-D"};
		Pizza pizza = new Pizza(4);
		for (int i = 0; i < preferencias.length; i++) {
			pizza.addComenzal(preferencias[i]);
		}
		assertEquals("A", pizza.resolver());
	}
	
	/**
	 * Convierte en string los datos de entrada
	 * @param total la cantidad de letras a usar
	 * @param array las preferencias de cada amigo
	 * @return Devuelve un string con los valores que ser�n entrada 
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
