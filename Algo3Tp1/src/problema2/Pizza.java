package problema2;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

public final class Pizza {
	/*simulacion de typedefs de C++*/
	public class ListaComenzal extends LinkedList<Comensal>{}
	public class PilaChar extends Stack<Character>{}
	public class ListaIngrediente extends LinkedList<Character>{}
	/*fin typedefs*/
	
	public long complex;
	public int mComenzales;
	
	private static final char INGREDIENTE_CENTINELA = (char)0;
	private boolean hayPosibleSolucion;
	private ListaComenzal comenzalesInsatisfechos;
	private ListaComenzal[] comenzalesSatisfechos;//estas podrian tratarse como una cola o una pila
	private ListaIngrediente ingredientesQueImportan;
	private PilaChar solucion;

	// En caso de que ningun Comenzal tenga una preferencia sobre el ingrediente i no me interesa evaluarlo para ver si va o no en la pizza
	// se llena de false y se van seteando los true a medida que se lee la entrada y se arma la preferencia 
	private boolean[] importan;
	
	public Pizza(int nIngredientes){
		this.importan = new boolean[nIngredientes];
		this.hayPosibleSolucion = true;
		this.solucion = new PilaChar();
		this.comenzalesInsatisfechos = new ListaComenzal();
		this.comenzalesSatisfechos = new ListaComenzal[nIngredientes];
		this.complex=1;
		this.mComenzales=0;
		for(int i = 0;i< nIngredientes;i++){
			this.importan[i] = false;
			//esta lista se va a llenar con los comenzales que en la solucion parcial actual hayan sido satisfechos con el ingrediente i
			this.comenzalesSatisfechos[i] = new ListaComenzal();
			this.complex++;
		}
	}
	
	public boolean hayPosibleSolucion(){
		return this.hayPosibleSolucion;
	}
	
	private int charToInt(char letra) {
		return ((int)letra) - 65;
	}

	private char intToChar(int i) {
		return (char)(i + 65);
	}
	
	public void addComenzal(String preferenciaCompletaComenzal){
		int cantidadIngredientes = this.importan.length;
		char[] prefComenzalAux = preferenciaCompletaComenzal.toCharArray();
		boolean participa = false;
		if(preferenciaCompletaComenzal.length() > 1){
			//hay mas caracteres que solo ';'
			//con esto evito crear comenzales sin preferencias y asumo que no se cumplen ya que como no existen preferencia => no existe preferencia que le haya satisfecho
			participa = true;
			Preferencia[] prefs = new Preferencia[cantidadIngredientes];
			char maxPref = INGREDIENTE_CENTINELA;
			int i = 0;
			while( (i < prefComenzalAux.length -1) && (participa) ){
				Preferencia p = new Preferencia(prefComenzalAux[i+1], prefComenzalAux[i]);
				char letra = prefComenzalAux[i+1];
				int nLetra = charToInt(letra);
				this.importan[nLetra] = true;
				if(prefs[nLetra] == null){
					prefs[nLetra] = p;
					if(letra > maxPref){
						maxPref = letra;
					}
				}else if(!prefs[nLetra].equals(p)){
				//si la preferencia por el ingrediente es inversa asumo que poniendo o no el ingrediente, el comenzal va a estar satisfecho
				//asi que ni me gasto en hacerlo participar
					participa = false;
				}
				i+=2;
				this.complex++;
			}
			if(participa){
				this.mComenzales++;
				this.comenzalesInsatisfechos.add(new Comensal(prefs,maxPref));
			}
		}else{
			this.hayPosibleSolucion=false;
		}
	}
	
	private void terminoCargaComenzales(){
		this.ingredientesQueImportan = new ListaIngrediente();
		int cantIngredientes = this.importan.length;
		for(int i = 0;i<cantIngredientes;i++){
			if(this.importan[i] == true){
				this.ingredientesQueImportan.add(intToChar(i));
			}
			this.complex++;
		}
	}
	
	public String resolver(){
		String ret ="";
		this.terminoCargaComenzales();
		ListIterator<Character> lc = this.ingredientesQueImportan.listIterator();
		if(lc.hasNext() && this.hayPosibleSolucion()){
			if(this.backtrack(lc, true)){
				ret = this.convierteSolucion();
			}else if(this.backtrack(lc, false)){
				ret = this.convierteSolucion();
			}else{
				ret = "No hay Solución";
			}
		}else{
			ret = "No hay Solución";
		}
		return ret;
	}
	
	public String convierteSolucion(){
		String ret = "";
		ListIterator<Character> li = this.solucion.listIterator();
		while(li.hasNext()){
			ret += li.next();
			this.complex++;
		}
		return ret;
	}
		
	public boolean backtrack(ListIterator<Character> ingredienteActualIterator,boolean vaEnLaPizza){
		char ingredienteActual = ingredienteActualIterator.next();
		Preferencia p = new Preferencia(ingredienteActual, vaEnLaPizza?'+':'-');
		int ingAct = charToInt(ingredienteActual);
		boolean termino = false;
		boolean poda = false;
		if(vaEnLaPizza){//si estoy considerando el caso de que el ingrediente actual vaya en la pizza lo agrego a la solucion
			this.solucion.push(ingredienteActual);
		}
		ListIterator<Comensal> insatisfechosIterator = this.comenzalesInsatisfechos.listIterator();
		//ciclo sobre todos los comenzales que todavia no fueron satisfechos
		while(insatisfechosIterator.hasNext() && !poda){
			Comensal i = insatisfechosIterator.next();
			if(!i.puedeSerSatisfecho(ingredienteActual)){//esto tarda O(1)
				poda = true;
			}else{
				if(i.prefiere(p)){//esto tarda O(1)
					this.comenzalesSatisfechos[ingAct].add(i);
					insatisfechosIterator.remove();
				}
			}
			this.complex++;
		}
		if(this.comenzalesSatisfechos[ingAct].isEmpty() && vaEnLaPizza){
			//si no saque a nadie de insatisfechos y estoy en el caso vaEnLaPizza == true
			//podo porque si hay solución a partir de este punto va a haber solucion en el llamado false tambien
			//entonces me evito de hacer la recursion sobre la rama true
			poda = true;
		}
		if(!poda){
			//si this.comenzalesInsatisfechos esta vacia entonces termino=true
			if(this.comenzalesInsatisfechos.isEmpty()){
				termino = true;
			}else{//chequeo que no se acaben los ingredientes y backtrakeo
				if(ingredienteActualIterator.hasNext()){
					termino = this.backtrack(ingredienteActualIterator,true);
					if(!termino){
						termino = this.backtrack(ingredienteActualIterator,false);
					}
				}
			}
		}
		if(!termino){//si ya termino no me preocupo en restaurar nada y corto
			while(!this.comenzalesSatisfechos[ingAct].isEmpty()){
				this.comenzalesInsatisfechos.add(this.comenzalesSatisfechos[ingAct].get(0));
				this.comenzalesSatisfechos[ingAct].remove(0);
				this.complex++;
			}
			if(vaEnLaPizza){
			//si vaEnLaPizza == true entonces agregue el actual al principio de la funcion y si no termino entonces no hubo solucion poniendo este ingrediente y hay que probar sin el
				this.solucion.pop();
			}
			//se que a la funcion backtrack la llamo si se que hay un siguiente y avanzo el iterador asi que se que hay un elemento anterior
			ingredienteActualIterator.previous();
		}
		return termino;
	}

	@Override
	public String toString() {
		int cantIngredientes = this.importan.length;
		String ret = "";
		ret +=""+cantIngredientes+"\n";
		ret +="Insatisfechos: \n";
		for(Comensal ig:this.comenzalesInsatisfechos)ret+=ig+"\n";
		for(int j=0; j< cantIngredientes;j++){
			ret+="Satisfechos con "+intToChar(j)+":\n";
			for(Comensal integ:this.comenzalesSatisfechos[j])ret+="\t"+integ+"\n";
		}
		ret+="\ningredientes: ";
		for(char a = 'A'; a < intToChar(cantIngredientes);a++){
			ret+=a;
		}
		return ret+"\ncomplex: "+this.complex+"\n";
	}
}