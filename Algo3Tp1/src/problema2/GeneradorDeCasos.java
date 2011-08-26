package problema2;

public class GeneradorDeCasos {
	public static String PeorCaso(int nIngredientes,int mComenzales){
		String ret= nIngredientes+"\n";
		while(mComenzales > nIngredientes){
			for(int i= 0;i < nIngredientes;i++){
				ret+= "+"+(char)(65+i);
			}
			ret+=";\n";
			mComenzales--;
		}
		nIngredientes = mComenzales;
		while(nIngredientes > 0){
			for(int i= 0;i < nIngredientes-1;i++){
				ret+= "+"+(char)(65+i);
			}
			ret+= "-"+(char)(65+nIngredientes-1)+";\n";
			nIngredientes--;
		}
		return ret+".\n";
	}
}
