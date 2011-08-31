package problema2;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Stack;

public final class Pizza {

	public static String SIN_SOLUCION = "No hay Solucion";
	public long complex;
	

	public class ListaComenzal extends ArrayList<Comenzal> {
	}

	public class PilaInt extends Stack<Integer> {
	}

	public int ingredientelimite;
	public boolean terminoSinSolucion;

	public ListaComenzal comenzalesInsatisfechos;
	public ListaComenzal[][] comenzalesSatisfechos;// estas podrian tratarse
													// como una cola

	public char[] ingredientes;
	public boolean[] importan;// en caso de que ningun Comenzal tenga una
								// preferencia sobre el ingrediente i // se
								// llena de false y se van seteando los true a
								// medida que se lee la entrada y se arma la
								// preferencia
	// public int ingredienteActual;

	public PilaInt solucion;

	public Pizza(String data) {
		this.complex = 1;
		int nIngredientes = Integer.parseInt(
				data.substring(0, data.indexOf(10)), 10);
		this.ingredientelimite = nIngredientes;
		this.ingredientes = new char[nIngredientes];
		this.importan = new boolean[nIngredientes];
		this.terminoSinSolucion = false;
		this.solucion = new PilaInt();
		this.comenzalesInsatisfechos = new ListaComenzal();
		this.comenzalesSatisfechos = new ListaComenzal[2][nIngredientes];
		for (int i = 0; i < nIngredientes; i++) {
			this.complex++;
			this.ingredientes[i] = (char) (65 + i);
			this.importan[i] = false;
			// los comenzales satisfechos los voy guardando en dos listas
			// distintas para no preguntar dos veces por un mismo ingrediente si
			// tuve una poda
			this.comenzalesSatisfechos[0][i] = new ListaComenzal();
			this.comenzalesSatisfechos[1][i] = new ListaComenzal();
		}
		int i = data.indexOf(10) + 1;
		int j = data.indexOf(59, i);// ';'
		while (data.charAt(i) != '.') {
			this.addComenzal(data.substring(i, j));
			i = j + 2;
			j = data.indexOf(59, i);
		}

	}

	public void addComenzal(String data) {
		char[] dat = data.toCharArray();

		if (data.length() > 1) {// hay mas caracteres que solo ';'// con esto
								// evito crear comenzales sin preferencias y
								// asumo que se cumplen
			boolean participa = true;
			Preferencia[] prefs = new Preferencia[this.ingredientelimite];
			int maxPref = -1;
			for (int i = 0; i < data.length() - 1 && participa; i += 2) {
				this.complex++;
				Preferencia p = new Preferencia(dat[i + 1], dat[i]);
				this.importan[(int) dat[i + 1] - 65] = true;
				if (prefs[(int) dat[i + 1] - 65] == null) {
					prefs[(int) dat[i + 1] - 65] = p;
					if ((int) dat[i + 1] - 65 > maxPref)
						maxPref = (int) dat[i + 1] - 65;
				} else {
					// preguntar si hay que evaluar el caso que aparezca mas de
					// una vez la misma preferencia
					participa = false;
				}
			}
			if (participa)
				this.comenzalesInsatisfechos.add(new Comenzal(prefs, maxPref));// O(1)
		}
	}

	public String resolver() {
		String resultado = "";

		if (this.backtrack(0, true) && !this.terminoSinSolucion) {
			resultado = this.convierteSolucion();
		} else if (this.backtrack(0, false) && !this.terminoSinSolucion) {
			resultado = this.convierteSolucion();
		} else {
			resultado = SIN_SOLUCION;
		}
		return resultado;
	}

	public String convierteSolucion() {
		String ret = "";
		ListIterator<Integer> li = this.solucion.listIterator();
		while (li.hasNext()){
			ret += this.ingredientes[li.next()];
		}
		return ret;
	}

	public boolean backtrack(int ingredienteActual, boolean vaEnLaPizza) {
		boolean termino = false;
		boolean poda = false;
		if (vaEnLaPizza)
			this.solucion.push(ingredienteActual);// si estoy considerando el
													// caso de que el
													// ingrediente actual vaya
													// en la pizza lo agrego a
													// la solucion
		ListIterator<Comenzal> insatisfechosIterator = this.comenzalesInsatisfechos
				.listIterator();
		// ciclo sobre todos los comenzales que todavia no fueron satisfechos
		while (insatisfechosIterator.hasNext() && !poda) {
			Comenzal i = insatisfechosIterator.next();
			// se que el iterador está parado en el último ingrediente
			// revisado y este es menor del actual asi que hay tres opciones:
			if (ingredienteActual > i.maxPref) {
				// no tiene mas preferencias => esto significa que no se
				// pudieron cumplir ninguna de las preferencias del Comenzal
				// actual con esta solución parcial asi que se corta y se
				// vuelve a la instancia anterior
				poda = true;
				this.complex++;
			} else {
				this.complex++;
				if (i.preferencias[ingredienteActual] != null) {
					if (i.preferencias[ingredienteActual].prefiere == vaEnLaPizza) {
						this.comenzalesSatisfechos[vaEnLaPizza ? 1 : 0][ingredienteActual]
								.add(i);
						insatisfechosIterator.remove();
						this.complex++;
					}
				}
			}
		}

		// si estoy en la llamada a backtrack con vaEnLaPizza false es porque
		// con true falló pero pudo haber marcado algunos como satisfechos
		// y en esta llamada ya se sabia que no se iban a marcar asi que me
		// ahorro el chequeo y los agrego como insatisfechos para la llamada
		// backtrack siguiente
		// lo unico que mejora esto es no preguntar por la misma letra dos veces
		// cuando hice PODA y llame a la letra actual con false
		while (!this.comenzalesSatisfechos[vaEnLaPizza ? 0 : 1][ingredienteActual]
				.isEmpty()) {
			this.comenzalesInsatisfechos
					.add(this.comenzalesSatisfechos[vaEnLaPizza ? 0 : 1][ingredienteActual]
							.get(0));
			this.comenzalesSatisfechos[vaEnLaPizza ? 0 : 1][ingredienteActual]
					.remove(0);
			this.complex++;
		}

		if (!poda) {
			// si this.comenzalesInsatisfechos esta vacia entonces termino=true
			if (this.comenzalesInsatisfechos.isEmpty()) {
				termino = true;
			} else {// chequeo que no se acaben los ingredientes y backtrakeo
				int siguienteIngrediente = ingredienteActual + 1;
				while (siguienteIngrediente < this.ingredientelimite
						&& !this.importan[siguienteIngrediente])
					siguienteIngrediente++;// esto evita los ingredientes donde
											// nadie tiene preferencia de por si
											// o por no y le quita altura al
											// arbol de backtracking al no
											// evaluarlo
				if (siguienteIngrediente != this.ingredientelimite) {
					termino = this.backtrack(siguienteIngrediente, true);
					if (!termino)
						termino = this.backtrack(siguienteIngrediente, false);
				} else if (!vaEnLaPizza) {
					this.terminoSinSolucion = true;
					termino = true;
				}
			}
		}
		if (!termino) {// si ya termino no me preocupo en restaurar nada y corto
		// while(!this.comenzalesSatisfechos[ingredienteActual].isEmpty()){
		// this.comenzalesInsatisfechos.add(this.comenzalesSatisfechos[ingredienteActual].get(0));
		// this.comenzalesSatisfechos[ingredienteActual].remove(0);
		// this.complex++;
		// }
			if (vaEnLaPizza)
				this.solucion.pop();// si vaEnLaPizza == true entonces agregue
									// el actual al principio de la funcion y si
									// no termino entonces no hubo solucion
									// poniendo este ingrediente y hay que
									// probar sin el
		}
		return termino;
	}

	@Override
	public String toString() {
		String ret = "";
		ret += "" + this.ingredientelimite + "\n";
		ret += "Insatisfechos: \n";
		for (Comenzal ig : this.comenzalesInsatisfechos)
			ret += ig + "\n";
		for (int j = 0; j < this.ingredientelimite; j++) {
			ret += "Satisfechos con " + this.ingredientes[j] + ":\n";
			for (int i = 0; i < 2; i++)
				for (Comenzal integ : this.comenzalesSatisfechos[i][j])
					ret += "\t" + integ + "\n";
		}
		ret += "\ningredientes: ";
		for (char i : this.ingredientes)
			ret += i;
		return ret + "\ncomplex: " + this.complex + "\n";
	}
}