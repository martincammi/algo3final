package problema2;

public class Preferencia{
	public char ingrediente;
	public boolean prefiere;
	
	public Preferencia(char ingrediente,char prefiere){
		this.ingrediente = ingrediente;
		this.prefiere = prefiere == '+';
	}

	@Override
	public boolean equals(Object o) {
		Preferencia p = (Preferencia)o;
		return this.ingrediente == p.ingrediente && this.prefiere == p.prefiere;
	}

	@Override
	public String toString() {
		return (this.prefiere?"+":"-")+this.ingrediente;
	}
	
	
}