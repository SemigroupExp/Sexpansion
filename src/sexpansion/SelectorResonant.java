package sexpansion;

import Jama.Matrix;

public class SelectorResonant extends Selector {

	SetS S0, S1 ;
	
	SelectorResonant ( Selector s, SetS V0, SetS V1) {
		super(s.order);
		this.data = s.data ;
		this.S0 = V0;
		this.S1 = V1;
		
	}
	
	public Matrix selectorMetric (){
		int dim = S0.nElements + S1.nElements;
		Matrix result = new Matrix(dim,dim);
		int h = 0 ; //fila
		int l = 0 ;//columna
		double[][] resultat = new double[dim][dim];
		for ( h = 0 ; h < dim ; ++h ) {
			for ( l = 0 ; l < dim ; ++l) {
				if ( h < S0.nElements && l < S0.nElements) { //Caixa en S0
					double sum = 0 ;
					int k = 0 ;
					int m = 0 ;
					for ( k = 0; k < S0.nElements ; ++k) {
						for ( m = 0 ; m < S0.nElements ;++m){
							sum = sum + this.data[S0.elementAt(h)-1][S0.elementAt(k)-1][S0.elementAt(m)-1] * this.data[S0.elementAt(l)-1][S0.elementAt(m)-1][S0.elementAt(k)-1] ;
						}
					}
					for ( k = 0; k < S1.nElements ; ++k) {
						for ( m = 0 ; m < S1.nElements ;++m){
							sum = sum + this.data[S0.elementAt(h)-1][S1.elementAt(k)-1][S1.elementAt(m)-1] * this.data[S0.elementAt(l)-1][S1.elementAt(m)-1][S1.elementAt(k)-1] ;
						}
					}
					//System.out.println(h);
					//System.out.println(l);
					//System.out.println(sum);
					result.set(h,l,sum);
					resultat[h][l] = sum;
				}else if ( h >= S0.nElements && l >= S0.nElements) {
					double sum = 0 ;
					int k = 0 ;
					int m = 0 ;
					for ( k = 0; k < S0.nElements ; ++k) {
						for ( m = 0 ; m < S1.nElements ;++m){
							sum = sum + this.data[S1.elementAt(h-S0.nElements)-1][S0.elementAt(k)-1][S1.elementAt(m)-1] * this.data[S1.elementAt(l-S0.nElements)-1][S1.elementAt(m)-1][S0.elementAt(k)-1] ;
							sum = sum + this.data[S1.elementAt(h-S0.nElements)-1][S1.elementAt(m)-1][S0.elementAt(k)-1] * this.data[S1.elementAt(l-S0.nElements)-1][S0.elementAt(k)-1][S1.elementAt(m)-1] ;

						}
					}
				
					//System.out.println(h);
					//System.out.println(l);
					//System.out.println(sum);
					result.set(h,l,sum);
					//System.out.println(result.get(h, l));
					//result.print(2, 2);
					//System.out.println(result.get(h, l));
					resultat[h][l] = sum;

				}
			}
			
		}
		//result.print(1, 1);
		return result;
	//	return new Matrix(resultat);
		
	}
	
	void NMatrix () {
		System.out.println("Here is the resonant decomposition");
		System.out.print("S0:");
		S0.show();
		System.out.print("S1:");
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
