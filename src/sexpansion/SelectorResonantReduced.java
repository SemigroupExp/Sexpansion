package sexpansion;

import Jama.Matrix;

/**
 * A java class to represent the selector of a Resonant s-expanded algebra.
 * Its a child class of Selector
 * @author fnadal
 *
 */

public class SelectorResonantReduced extends SelectorResonant {
	/* ------------------
	 * Class Variables
	 * ------------------*/
	int zero;
	/* -----------------
	 * Constructors
	 * ----------------- */
	public SelectorResonantReduced(Selector s, SetS V0, SetS V1 , int zero) {
		// TODO Auto-generated constructor stub
		super(s,V0,V1);
		this.zero = zero;
		int i,j, k;
		for ( i = 0 ; i < this.order ; ++i) {
			for ( j = 0 ; j < this.order ; ++j) {
				for ( k = 0 ; k < this.order ; ++k ) {
					if ( i == zero - 1 || j == zero -1 || k == zero -1 ){
						data[i][j][k] = 0;
					}
				}
			}
		}
	}
	
	/* -----------------
	 * Public Methods 
	 * ----------------- */
	
	public Matrix selectorMetric (){
		Matrix bigMatrix = super.selectorMetric();
		int n = S0.nElements + S1.nElements;
		int n1 = S0.nElements;
		if (S0.find(zero)) {
			n = n -1;
			n1 = n1 - 1 ;
		}
		if ( S1.find(zero)) {
			n = n - 1;
		}
		
		System.out.print("n=");
		System.out.println(n);
		double [][] result = new double[n][n];
		
		int i,j;
		int l= 0,m= 0;
		for ( i = 0 ; i < S0.nElements ; ++i) {
			m = 0 ;
			if ( S0.elementAt(i) != zero) {
				for( j = 0 ; j < S0.nElements ; ++j ){
					if ( S0.elementAt(j) != zero ) {
					result[l][m] = bigMatrix.get(i, j);
					m = m + 1;
					}
				}
				l = l +1;
			}
		}
		l = n1 ;
		m = n1 ;
		for ( i = 0 ; i < S1.nElements ; ++i) {
			m = n1;
			if ( S1.elementAt(i) != zero ) {
				for( j = 0 ; j < S1.nElements ; ++j ){
					if ( S1.elementAt(j) != zero ) {
					result[l][m] = bigMatrix.get(i + S0.nElements, j + S0.nElements);
					m = m + 1;
					}
				}
				l = l +1;
			}
		}
		
		return new Matrix(result);
	}
	
	void NMatrix () {
		System.out.println("This is the resonant decomposition:");
		System.out.print("S0: ");
		S0.show();
		System.out.print("S1: ");
		S1.show();
		int n0 = S0.nElements;
		int n1 = S1.nElements;
		Matrix m0 = new Matrix(n0,n0);
		Matrix m1 = new Matrix(n1,n1);
		//First we compute m0
		int a0,b0,r0,g0,r1,g1, i,j,k,l,a1,b1;
		float suma = 0;
		for ( i =  0 ; i < n0 ; ++i) {
			for ( j = 0 ; j < n0; ++j) {
				a0 = S0.elementAt(i)-1;
				b0 = S0.elementAt(j)-1;
				suma = 0 ;
				for ( k = 0 ; k < n0 ; ++k) {
					r0 = S0.elementAt(k)-1;
					for ( l = 0 ; l < n0 ; ++l) {
						g0 = S0.elementAt(l)-1;
						suma = suma + data[a0][g0][r0] * data[b0][r0][g0] ;

					}
				}
				for ( k = 0 ; k < n1 ; ++k) {
					r1 = S1.elementAt(k)-1;
					for ( l =  0 ; l < n1 ; ++l){
						g1 = S1.elementAt(l)-1;
						suma = suma + data[a0][g1][r1] * data[b0][r1][g1] ;
					}
				}
				m0.set(i, j, suma);
			}
		}
		for ( i =  0 ; i < n1 ; ++i) {
			for ( j = 0 ; j < n1; ++j) {
				a1 = S1.elementAt(i)-1;
				b1 = S1.elementAt(j)-1;
				suma = 0 ;
				for ( k = 0 ; k < n1 ; ++k) {
					r1 = S1.elementAt(k)-1;
					for ( l = 0 ; l < n0 ; ++l) {
						g0 = S0.elementAt(l)-1;
						suma = suma + data[a1][g0][r1] * data[b1][r1][g0] ;

					}
				}
				for ( k = 0 ; k < n0 ; ++k) {
					r0 = S0.elementAt(k)-1;
					for ( l =  0 ; l < n1 ; ++l){
						g1 = S1.elementAt(l)-1;
						suma = suma + data[a1][g1][r0] * data[b1][r0][g1] ;
					}
				}
				m1.set(i, j, suma);
			}
		}
		System.out.println("g(S0):");
		m0.print(2, 2);
		System.out.println("g(S1):");
		m1.print(2, 2);
		
	}
	
	/**
	 * @param args
	 */

}
