package sexpansion;

import Jama.Matrix;


public class SelectorReduced extends Selector {

	int zero;
	public SelectorReduced( Selector s, int zero) {
		super(s.order);
		this.data = s.data ;
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
	
	public Matrix selectorMetric() {
		Matrix bigMatrix = super.selectorMetric();
		int i,j;
		int l = 0 ,m = 0;
		double[][] dat = new double[order -1] [order - 1];
		for ( i = 0 ; i < this.order; ++i ){
			if ( i != zero - 1) {
				m = 0 ;
				for ( j = 0 ; j < this.order ; ++j ) {
					if ( j != zero -1 ) {
								dat[l][m] = bigMatrix.get(i, j);
								m = m + 1 ;
						}
					}
				l = l + 1;
				}
			}
		return new Matrix(dat);
	}
	
	
	/**
	 * @param args
	 */
	
}
