package problema2;

public class Comenzal {
	private static int ids = 1;
	public int id;
	Preferencia[] preferencias;
	int maxPref;
	
	public Comenzal(Preferencia[] prefs,int maxPref){
		this.id = ids;
		ids++;
		this.preferencias = prefs;
		this.maxPref = maxPref;
	}
	
	@Override
	public String toString() {
		String ret = "{id:"+id+" maxPref: "+this.maxPref+" preferencias[";
			for(Preferencia p : this.preferencias)ret+=p+",";
		return ret+"]}";		
	}
	
		
}