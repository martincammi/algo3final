package problema1;

public class Prisma {
	public static class Posicion{
		public int i;
		public int j;
		
		public Posicion(int i, int j){
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "("+i+","+j+")";
		}
		
		
	}
	
	public int altura;
	public int rango;
	public long destruccionAcumulada;
	public Posicion desdeDondeVino;
	public Posicion pos;
	public static final Posicion INICIO		= new Posicion( 0, 0);
	public static final Posicion ARRIBA		= new Posicion(-1, 0);
	public static final Posicion ABAJO		= new Posicion( 1, 0);
	public static final Posicion IZQUIERDA	= new Posicion( 0,-1);
	public static final Posicion DERECHA	= new Posicion( 0, 1);
	public static final int DESTRUCCION_CENTINELA = -1;

	public Prisma(int h, int r,int i, int j){
		this.altura = h;
		this.rango = r;
		this.destruccionAcumulada = DESTRUCCION_CENTINELA;
		this.desdeDondeVino = INICIO;
		this.pos = new Posicion(i, j);
	}
	
	public Prisma(){
		this.altura = 0;
		this.rango = 0;
		this.destruccionAcumulada = DESTRUCCION_CENTINELA;
		this.desdeDondeVino = INICIO;
		this.pos = new Posicion(-1, -1);
	}
	
	/**
	 * Devuelve el prisma con más destrucción acumulada
	 */
	public static Prisma prismaConMasDestruccion(Prisma p1, Prisma p2, Prisma p3, Prisma p4){
		Prisma prismaAux1;
		Prisma prismaAux2;
		Prisma prismaAux3;
		
		prismaAux1 = (p1.destruccionAcumulada >= p2.destruccionAcumulada) ? p1 : p2;
		prismaAux2 = (p3.destruccionAcumulada >= p4.destruccionAcumulada) ? p3 : p4;
		prismaAux3 = (prismaAux1.destruccionAcumulada >= prismaAux2.destruccionAcumulada) ? prismaAux1 : prismaAux2;
		
		return prismaAux3;
	}

	@Override
	public String toString() {
		return "["+this.altura+","+this.rango+"]{"+this.destruccionAcumulada+"}"+this.pos+""+this.desdeDondeVino;
	}
	
}
