package problema1;

public class Tennis {

	
	public static void main(String[] args) {
		
		//Si es par debe resolver en n-1 dias
		//Si es impar debe resolver en n dias
		
		int jugadores = 5; //Debe resolver en 5 días.
		Tennis tennis = new Tennis();
		Matriz fixture = tennis.armarFixture(jugadores);
		fixture.mostrar();
	}
	
	private class Matriz{
		
		private int[][] matriz;
		private int alto;
		private int ancho;
		
		public Matriz(int alto, int ancho){
			matriz = new int[alto][ancho];
			this.alto = alto;
			this.ancho = ancho;
		}
		
		public void asignar(int i, int j, int value){
			matriz[i][j] = value;
		}
		
		public void mostrar(){
			for (int i = 0; i < alto; i++) {
				for (int j = 0; j < ancho; j++) {
					System.out.print(matriz[i][j] + " ");
				}
				System.out.println(" ");
			}
		}
	}
	
	public Matriz armarFixture(int jugadores){

		Matriz fixture;
		int dias;
		
		if (jugadores % 2 != 0){
			dias = jugadores; 
		}else{
			dias = jugadores - 1; 
		}		
		
		fixture = new Matriz(jugadores,dias);
		
	    return armarFixtureAux(fixture, 0, jugadores-1, dias);
	}
	
	private Matriz armarFixtureAux(Matriz fixture, int d, int h, int dia) {
	    if(h > d) {
	        armarFixtureAux(fixture, d, (d+h)/2, dia/2);
	        armarFixtureAux(fixture, (d+h)/2 + 1, h, dia/2);
	        
	        int i = d;
	        int s = 0;
	        while(i <= (d+h)/2){
	            int j = (d+h)/2 + 1;
	            int c = s;
	            while(j <= h){
	                fixture.asignar(i, j, dia - Math.abs(c) - 1);
	                fixture.asignar(j, i, dia - Math.abs(c) - 1);
	                j++;
	                c++;
	            }
	            i++;
	            s--;
	        }
	    }
	    else{
	        fixture.asignar(d, d, 0); //la diagonal se rellena de ceros
	    }
	    return fixture;
	}
	
}
