package utils;

public class Modelo {

	private double logComplex;
	private int uniformComplex;
	
	public Modelo(){
		reset();
	}
	
	public void addUcomplex(int value){
		uniformComplex += value;
	}
	
	public void addUcomplex(){
		uniformComplex++;
	}
	
	public void addSumComplex(int a, int b){
		//logComplex += Math.log(a) + Math.log(b);
		logComplex += Math.max(Math.log(a),Math.log(b));
	}
	
	public void addMultComplex(int a, int b){
		//logComplex += Math.log(a) * Math.log(b);
		double logACuad = Math.pow(Math.log(a), Math.log(a));
		double logBCuad = Math.pow(Math.log(b), Math.log(b));
		logComplex += Math.max(logACuad,logBCuad);
	}
	
	public void addVectorIndexityComplex(int i){
		logComplex += Math.log(i);
	}
	
	public void addIncComplex(int i){
		logComplex += Math.log(i);
	}
	
	public void addForComplex(int desde, int hasta){
		int n = hasta - desde; 
		logComplex += n * Math.log(n);
	}
	
	public void resetLogaritmic(){
		logComplex = 0;
	}
	
	public void resetUniform(){
		uniformComplex = 0;
	}
	
	public void reset(){
		logComplex = 0;
		uniformComplex = 0;
	}

	public double getLogComplex() {
		return logComplex;
	}

	public int getUniformComplex() {
		return uniformComplex;
	}
}
