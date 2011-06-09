package com.teracode.tdd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RomanConverter {

	private Map<Integer, String> romanBasicNumbers = new HashMap<Integer, String>();
	private List<Integer> descendingKeys = new ArrayList<Integer>();
	
	public RomanConverter(){
		romanBasicNumbers.put(1, "I");
		romanBasicNumbers.put(5, "V");
		romanBasicNumbers.put(10, "X");
		romanBasicNumbers.put(50, "D");
		
		for (Integer romanBasicNumber : romanBasicNumbers.keySet()) {
			descendingKeys.add(romanBasicNumber); 
		}
		Collections.sort(descendingKeys);
		Collections.reverse(descendingKeys);
	}
	
	public String convert(int number) {
		
		String resultado = "";
		for (Integer romanIndex : descendingKeys) {
			
			if(number == 0){
				break;
			}
			
			if(number == romanIndex - 1){
				resultado = resultado + romanBasicNumbers.get(1) + romanBasicNumbers.get(romanIndex);
				number = number - (romanIndex-1);
			}
			
			while(number >= romanIndex){
				resultado = resultado + romanBasicNumbers.get(romanIndex);
				number = number - romanIndex;
			}
		}
		
		return resultado;
	}

}
