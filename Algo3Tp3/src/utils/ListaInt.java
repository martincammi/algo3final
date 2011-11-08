package utils;

import java.util.ArrayList;

public class ListaInt extends LinkedList<Integer>{

	@Override
	public String toString() {
		String ret="";
		for(Integer i: this){
			ret+=i+" ";
		}
		return ret;
	}
	
}