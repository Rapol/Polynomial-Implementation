package edu.uprm.ece.icom4035.polynomial;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import edu.uprm.ece.icom4035.list.ArrayList;

public class PolynomialImp implements Polynomial{

	private ArrayList<Term> terms;

	/**
	 * Constructor that receive a string as input
	 * @param input string form Polynomial
	 */
	public PolynomialImp(String string) {
		terms = new ArrayList<Term>(3);
		fromString(string);
	}

	/**
	 * Addition method
	 * @param P2 polynomial to be added
	 * @return new polynomial after addition
	 */
	public Polynomial add(Polynomial P2) {
		PolynomialImp result=new PolynomialImp("");
		for(Term t1:this.terms){
			boolean found=false;
			for(Term t2:P2){
				//sum if the second term is equal to the term in the first one, break
				if(t1.getExponent()==t2.getExponent()){
					found=true;
					TermImp newTerm = new TermImp(t1.getCoefficient()+t2.getCoefficient(),t1.getExponent());
					result.addTerm(newTerm);
					break;
				}
			}
			//if there is not a term with the same exponent add at the end of the polynomial
			if(!found){
				TermImp newTerm = new TermImp(t1.getCoefficient(),t1.getExponent());
				result.addTerm(newTerm);
			}
		}
		//takes care of the terms not added in the second polynomial
		for(Term t1:P2){
			boolean notFound=true;
			for(Term t2:this.terms){
				//checks if it was already added
				if(t1.getExponent()==t2.getExponent()){
					notFound=false;
				}
			}
			//add if exponent not found
			if(notFound){
				TermImp newTerm = new TermImp(t1.getCoefficient(),t1.getExponent());
				result.addTerm(newTerm);
			}
		}
		return result;
	}


	/**Subtract method
	 * @param P2 polinomial to be substracted
	 * @return new polynomial after substraction
	 */
	public Polynomial subtract(Polynomial P2) {
		return this.add(P2.multiply(-1));
	}

	/**Multiply two polynomials
	 * @param P2 Polynomial to be multiply
	 * @return resulting polynomial after multiplication using the distributive rule
	 */
	public Polynomial multiply(Polynomial P2) {
		//arraylist for saving each polynomial that results of the multiplication for each term
		ArrayList<PolynomialImp> allPols=new ArrayList<PolynomialImp>(3);
		for(Term t1:this.terms){
			PolynomialImp result = new PolynomialImp("");
			for(Term t2:P2){
				TermImp newTerm = new TermImp(t1.getCoefficient()*t2.getCoefficient(),t1.getExponent()+t2.getExponent());
				result.addTerm(newTerm);
			}
			//adds the polynomial to the arraylist.
			allPols.add(result);
		}
		//sum all the polynomial resulting from the multiplication
		for(int i=0;i<allPols.size()-1;i++){
			//the add will take care of reorganizing the terms in their descending order
			allPols.set(0,(PolynomialImp) allPols.get(0).add(allPols.get(i+1)));
		}
		//the resultant is located in the position 0
		return allPols.get(0);
	}

	/**Multiply by a scalar value
	 * @param c value to be multiply and return
	 * @return new polynomial after scalar product
	 */
	public Polynomial multiply(double c) {
		PolynomialImp result=new PolynomialImp("");
		//if c=0 return a polynomial=0
		if(c==0){
			//this will return a polynomial equal to P=0
			result.addTerm(new TermImp(0,0));
			return result;
		}
		for(Term t:this.terms){
			TermImp newTerm = new TermImp(t.getCoefficient()*c,t.getExponent());
			result.addTerm(newTerm);
		}
		return result;
	}

	/**
	 * @return the derivative of the polynomial as a new polynomial
	 */
	public Polynomial derivative() {
		PolynomialImp result = new PolynomialImp("");
		for(Term t:this.terms){
			if(t.getExponent()!=0){
				TermImp newTerm = new TermImp(t.getCoefficient()*t.getExponent(),t.getExponent()-1);
				result.addTerm(newTerm);
			}
		}
		return result;
	}

	/**
	 * @return new polynomial as the indefinite integral of current polynomial
	 */
	public Polynomial indefiniteIntegral() {
		PolynomialImp result= new PolynomialImp("");
		for(Term t:this.terms){
			TermImp newTerm = new TermImp((t.getCoefficient())/(t.getExponent()+1),t.getExponent()+1);
			result.addTerm(newTerm);
		}
		//add the constant=1 to polynomial
		result.addTerm(new TermImp(1,0));
		return result;
	}

	/** Evaluate the integral of the polynomail
	 * @param a = lower bound of the integral
	 * @param b = upper bound of the integral
	 * @return the result of evaluating the integral
	 */
	public double definiteIntegral(double a, double b) {
		return  this.indefiniteIntegral().evaluate(b)-this.indefiniteIntegral().evaluate(a);
	}

	/** 
	 * @return the degree of the polynomial
	 */
	public int degree() {
		return this.terms.first().getExponent();
	}

	/**
	 * @param x value to be evaluated on each term
	 * @return double value of the evaluating result
	 */
	public double evaluate(double x) {
		double result=0;
		for(Term t:this.terms){
			result+=t.evaluate(x);
		}
		return result;
	}

	/**
	 * @return true if the polynomials are equal
	 */
	@Override
	public boolean equals(Polynomial P) {
		return this.toString().equals(P.toString());
	}
	/**
	 * Convert a string polynomial to a Term based polynomial
	 * @param str polynomial string form
	 */
	private void fromString(String str) {
		StringTokenizer strTok = new StringTokenizer(str, "+");
		String nextStr = null;
		Term nextTerm = null;
		this.terms.clear();
		while (strTok.hasMoreElements()){
			nextStr = (String) strTok.nextElement();
			nextTerm = TermImp.fromString(nextStr);
			// private method to store a new term into a polynomial
			this.addTerm(nextTerm);
		}
	}

	/**
	 *Add new terms to the current polynomial and reorganize the terms in the corresponding order
	 * @param nextTerm
	 */
	private void addTerm(Term nextTerm) {
		//this takes care of reorganizing each term in the polynomial in the correct descending order. 
		//This will not add terms with coefficient equal to zero and will add zero if the polynomial is empty
		if(nextTerm.getCoefficient()!=0){
			if(this.terms.isEmpty()){
				this.terms.add(nextTerm);
			}
			else{
				boolean notAdded=true;
				for(int i=0;i<this.terms.size();i++){
					if(nextTerm.getExponent()>this.terms.get(i).getExponent())
					{
						this.terms.add(i, nextTerm);
						notAdded=false;
						break;
					}
				}
				if(notAdded){
					this.terms.add(nextTerm);
				}
			}
		}
		else{
			if(this.terms.isEmpty() && nextTerm.getExponent()==0){
				this.terms.add(nextTerm);
			}
		}
	}
	
	/**
	 * Convert a Polynomial to a String Form
	 */
	@Override
	public String toString() {
		String result ="";
		if(this.terms.isEmpty()){
			return "0.00";
		}
		for(Term t:this.terms){
			result+=t+"+";
		}
		return result.substring(0, result.length()-1);
	}

	@Override
	public Iterator<Term> iterator() {
		return this.terms.iterator();
	}
}

