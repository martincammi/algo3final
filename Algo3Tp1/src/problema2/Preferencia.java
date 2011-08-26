package problema2;

public class Preferencia implements Comparable<Preferencia> {
	public char ingrediente;
	public boolean prefiere;
	
	public Preferencia(char ingrediente,char prefiere){
		this.ingrediente = ingrediente;
		this.prefiere = prefiere == '+';
	}
	
	//@Override
	public int compareTo(Preferencia t) {
		if(this.ingrediente == t.ingrediente){
			return 0;
		}else if(this.ingrediente < t.ingrediente){
			return -1;
		}else{
			return 1;
		}
	}

	@Override
	public String toString() {
		return ""+(this.prefiere?'+':'-')+this.ingrediente;
	}
}