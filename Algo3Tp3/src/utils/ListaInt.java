package utils;

import java.util.ArrayList;

public class ListaInt extends ArrayList<Integer>{

	@Override
	public String toString() {
		String ret="";
		for(Integer i: this){
			ret+=i+" ";
		}
		return ret;
	}
	
}