package problema2;

public class AlgoIIITp1Problema2 {

	public static void main(String[] args) {
		String data = GeneradorDeCasos.PeorCaso(26, 27);
		System.out.println(data);
//		System.out.println(GeneradorDeCasos.PeorCaso(26, 400));
		Pizza test1 = new Pizza(data);
//		System.out.println(test1);
		System.out.println("solucion: \""+test1.resolver()+"\"");
//		System.out.println(test1);
		String masData = "26\n+A+B+C-D+E+F+G+H-I+J+K+L+M+N-O+P+Q+R-S+T+U+V+W+X+Y+Z;\n+A+B+C+D+E+F+G+H+I+J+K-L+M+N+O+P+Q+R+S+T-U+V+W+X+Y-Z;\n+A+B+C+D+E+F+G+H+I+J+K+L+M+N+O+P+Q+R+S+T+U+V+W+X-Y;\n+A+B+C+D+E+F+G+H-I+J+K+L+M+N+O+P+Q+R+S+T+U+V+W-X;\n+A+B+C+D+E+F+G+H+I+J+K+L+M+N+O+P+Q+R+S+T+U+V-W;\n+A+B+C+D+E+F+G+H+I+J+K+L+M+N+O+P+Q+R+S+T+U-V;\n+A+B+C+D+E+F+G+H+I+J+K-L+M+N+O+P+Q+R+S+T-U;\n+A+B+C+D+E+F+G+H+I+J+K+L+M+N+O+P+Q+R+S-T;\n+A+B+C+D+E+F+G+H+I+J+K+L+M-N+O+P+Q+R-S;\n+A+B+C+D+E+F+G+H+I+J+K+L+M+N+O+P+Q-R;\n+A+B+C+D-E+F+G+H+I+J+K+L+M+N+O+P-Q;\n+A+B+C+D+E+F+G+H+I+J+K+L+M+N+O-P;\n+A+B+C+D+E+F+G+H-I+J+K+L+M+N-O;\n+A+B+C+D+E+F+G+H+I+J+K+L+M-N;\n+A+B+C+D+E+F+G+H+I+J+K+L-M;\n+A+B+C-D+E+F+G+H+I+J+K-L;\n+A+B+C+D+E+F+G+H+I+J-K;\n+A+B+C+D+E-F+G+H+I-J;\n+A+B+C+D+E+F+G+H-I;\n+A+B+C+D+E+F+G-H;\n+A+B+C+D+E+F-G;\n+A+B-C+D+E-F;\n+A+B+C+D-E;\n+A+B+C-D;\n+A+B-C;\n+A-B;\n-A;\n.";
		Pizza test2 = new Pizza(masData);
//		System.out.println(test2);
		System.out.println(test2.resolver());

//		String ret = "";
//		for(int t= 0;t < 256;t++){
//			ret+= ""+t+" "+(char)(t)+"\n";
//		}
//		System.out.println(ret);

//		int nIngredientes = 1;
//		while(nIngredientes <= 26){
//			int mComenzales = 1;
//			while(mComenzales < 400){
//				Pizza p = new Pizza(GeneradorDeCasos.PeorCaso(nIngredientes, mComenzales));
//				p.resolver();
//				System.out.println("n: "+nIngredientes+" m: "+mComenzales+" complejidad: "+p.complex);
//				mComenzales++;
//			}
//			nIngredientes++;
//		}
	}
}