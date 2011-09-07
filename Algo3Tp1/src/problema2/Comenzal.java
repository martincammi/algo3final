package problema2;

public class Comenzal {
	Preferencia[] preferencias;
	char maxPref;
	
	public Comenzal(Preferencia[] prefs,char maxPref){
		this.preferencias = prefs;
		this.maxPref = maxPref;
	}
	
	private int charToInt(char letra) {
		return (int)letra - 65;
	}

	public boolean puedeSerSatisfecho(char ingredienteActual) {//O(1)
		return ingredienteActual <= this.maxPref;
	}

	public boolean prefiere(Preferencia p) {//O(1)
		int ingrediente = charToInt(p.ingrediente);
		boolean ret = this.preferencias[ingrediente]!=null;
		if(ret){
			ret = this.preferencias[ingrediente].equals(p);
		}
		return ret;
	}

	@Override
	public String toString() {
		String s = "";
		for(Preferencia p: this.preferencias)s+=p+",";
		return "["+s+"]";
	}
}