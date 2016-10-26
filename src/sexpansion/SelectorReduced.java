package sexpansion;

import Jama.Matrix;


public class SelectorReduced extends Selector {

	int zero;
	public SelectorReduced( Selector s, int zero) {
		// TODO Auto-generated constructor stub
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
Matrix metrica ;
		
		System.out.println("We introduce the structure constants of sl2.");
		StructureConstantSet sl2p = new StructureConstantSet(3) ;
		sl2p.setStructureConstant(0, 1, 1, 2) ;
		sl2p.setStructureConstant(0, 2, 2, -2) ;
		sl2p.setStructureConstant(1, 2, 0, 1) ;
		metrica = sl2p.cartanKillingMetric() ;
		System.out.println("We show its Killing-Cartan metric");
		metrica.print(2, 2) ;
		System.out.println("and its determinant:");
		System.out.println(metrica.det());
		System.out.println("We load a semigroup.");
		int [][] matriz = {{1,2,3,4},{2,3,4,4},{3,4,4,4}, {4,4,4,4}};
	    Semigroup grupo = new Semigroup(matriz) ;
	    System.out.println("We compute the selectors of the semigroup and the reduced selectors.");
	    Selector s = grupo.getSelector() ;
	    SelectorReduced rs = new SelectorReduced(s,4);
	    Matrix m = rs.selectorMetric();
	    System.out.println("The metric of the reduced selectors is:");
	    m.print(2, 2);

	}
	
}
