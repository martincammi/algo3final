package utils;

public class MutableBoolean{
	
	boolean flag;
	
	public MutableBoolean(){
		flag = true;
	}

	public void True(){
		flag = true;
	}
	
	public void False(){
		flag = false;
	}
	
	public boolean getValue(){
		return flag;
	}
}