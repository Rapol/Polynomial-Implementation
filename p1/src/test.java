import java.util.Random;

import edu.uprm.ece.icom4035.list.ArrayList;
import edu.uprm.ece.icom4035.polynomial.PolynomialImp;
import edu.uprm.ece.icom4035.polynomial.Term;
import edu.uprm.ece.icom4035.polynomial.TermImp;


public class test {

	public static void main(String[] args) {
		// Polynomial must be in descinding order of powers
		PolynomialImp tester = new PolynomialImp("x^2+x^-1");
		PolynomialImp tester1 = new PolynomialImp("-1x^2+-1x^-2");
		System.out.println(tester1.add(tester));
		System.out.println(tester1);
		System.out.println(tester1.indefiniteIntegral().multiply(new PolynomialImp("0")));
		System.out.println(tester.derivative());
		System.out.println(tester.derivative().indefiniteIntegral());
		PolynomialImp testImp = new PolynomialImp("1x^2+2x");
		System.out.println("Original Polinomial" + testImp);
		System.out.println("Integral" + testImp.indefiniteIntegral());
		System.out.println("DefiniteIntegral: "+testImp.definiteIntegral(0, 2));
		System.out.println("WolframAlpha Value: 20/3 = " + (double)20/3);

		PolynomialImp p1 = new PolynomialImp("x^2");
		PolynomialImp p2 = new PolynomialImp("0x^2+x+1");
		PolynomialImp p3 = new PolynomialImp("x+x^3+1+3x^2+x^4+0");
		PolynomialImp p4 = new PolynomialImp("0");
		PolynomialImp p5 = new PolynomialImp("x+-8x^2+-1");
		PolynomialImp p6 = new PolynomialImp("x+-1");
		PolynomialImp p7 = new PolynomialImp("1");
		PolynomialImp p8 = new PolynomialImp("0x+0+23x^3");
		PolynomialImp P1 = new PolynomialImp("8x^2+1");
		PolynomialImp P2 = new PolynomialImp("4x^2+2");
		PolynomialImp P3 = new PolynomialImp("");
		Random rand = new Random();


		double coefficient = rand.nextDouble()*rand.nextInt();
		double exponent  = rand.nextInt(999);
		ArrayList<Term> terms = new ArrayList<Term>(2); 


		String temp = "";
		int top = 2;
		for (int i = 0; i < top; i++){
			coefficient = rand.nextDouble()*rand.nextInt();
			exponent  = rand.nextInt(rand.nextInt(9999));
			if(!(i==top-1))
				temp+= coefficient+"x^"+(int) exponent+"+";
			else {
				temp+= coefficient+"x^"+(int) exponent;
			}
			terms.add(new TermImp(coefficient, (int) exponent));
		}
		System.out.println(temp); 

		System.out.println(p4.degree());
		System.out.println(p4.derivative());
		System.out.println(P1.multiply(p6));
		System.out.println(p4.indefiniteIntegral().definiteIntegral(1, 2));
		System.out.println(p6.indefiniteIntegral());
		System.out.println(P3);
		System.out.println(P1.indefiniteIntegral());
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		System.out.println(p4);
		System.out.println(p5);
		System.out.println(p6);
		System.out.println(p7);
		System.out.println(p8);
//		System.out.println(p1.degree());
//		System.out.println(p1.evaluate(1));
//		System.out.println(p2.evaluate(2));
//		System.out.println(p2.equals(p3));
		System.out.println(p1.add(p3));
		System.out.println(p5.add(p3));
		System.out.println(p6.add(p1));
		System.out.println(p3.subtract(p2));
	}

}
