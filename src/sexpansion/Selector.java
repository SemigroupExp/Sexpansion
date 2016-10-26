package sexpansion;

import Jama.Matrix;

/**
 * A Java Class to represent a selector. A selector is defined with the equality \lambda_\alpha \lambda_\beta = K_{\alpha \beta}^\gamma \lambda_\gamma.
 * Its use is very convenient to describe S-expansions. The components of a selector can only take the values 0 or 1.
 * @author fnadal
 *
 */

public class Selector {
	
	//Number of elements of the semigroup
	int order ;
	//Array to save the selectors
	int [][][] data ;
	
	/**
	 * Creates a selector object for a semigroup of order n.
	 * 
	 * @param n the order of the semigroup 
	 */
	public Selector ( int n ) {
		order = n ;
		data = new int[n][n][n];
	}
	/**
	 * Sets the value of the K_{ab}^c component of the selector to valor
	 * @param a
	 * @param b
	 * @param c
	 * @param valor
	 */
	public void set( int a , int b , int c , int valor ) {
		data[a ][b][c] = valor ;
	}
	
	/**
	 * Returns the value K_{ab}^c
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public int get( int a, int b, int c) {
		return data[a  ][b  ][c ];
	}
	
	/** Computes the "metric" which results on the contraction of the selectors */
	public Matrix selectorMetric() {
		int a , b , c , d ;
		double sum = 0 ;
		double [][] metric = new double[order][order] ;
		for ( a = 0 ; a < order ; ++a) {
			for ( b = 0 ; b < order ; ++b ) {
				sum = 0 ;
				for ( c = 0 ; c < order ; ++c ) {
					for ( d = 0 ; d < order ; ++d ) {
						sum = sum + this.data[a][ c] [d ] * this.data[b][d][ c] ;
					}
				}
				metric[a][b] = sum ;
			}
		}
		return new Matrix( metric );
	}

	/**
	 * Shows the selector in a fancy way. It shows n boxes of dimension nxn, so the component K_{ab}^c is found in box a, row b, column c.
	 */
	public void show(){
		int a, b, c;
		System.out.println("For the considered semigroup of order m, here we print the m matrices K_{a,b}^{c}=M_{b,c}");
		System.out.println("(with a=1,...,m) which gives the adjoint representation for the elements of the semigroup.");
		for ( a = 0 ; a < this.order ; a = a + 1){
			System.out.println("*********") ;
			System.out.println("Adj [lambda_{"+(a+1)+"}] = ( K_{"+(a+1)+",b}^{c} ) =");
			for ( b = 0 ; b < this.order ; b = b + 1 ) {
				for ( c = 0 ; c < this.order ; c = c + 1 ){
					System.out.print(" ");
					System.out.print(data[a][b][c]);
				}
				System.out.println("");
			}
			
			System.out.println("*********");
		}
	}
	
	/**
	 * Computes the metric of an S-expanded Lie algebra, given the metric of the original algebra.
	 * @param metrica
	 * @return
	 */
	
	public Matrix expandedMetric( Matrix metrica ) {
		int i,a,j, l , g;
		double sum = 0 ;
		double [][][][] data = new double[metrica.getColumnDimension()][this.order][metrica.getColumnDimension()][ this.order];
		for ( i = 0 ; i < metrica.getRowDimension() ; i = i + 1 ) {
			for ( a = 0 ; a < this.order ; a = a + 1 ) {
				for ( j = 0 ; j <  metrica.getRowDimension() ; j = j + 1  ) {
					int b = 0;
					for ( b = 0  ; b < this.order ; ++b ) {
						sum = 0 ;
						for ( l = 0 ; l < this.order ; l = l + 1) {
							for ( g = 0 ; g < this.order ; g = g + 1 ) {
								sum = sum + this.data[a][g][l] * this.data[b][l][g] * metrica.get(i, j);
								}
							}
						data[i][a][j][b] = sum ;
					}
				}
			}
		}
		
		double [][] matrix = new double[this.order * metrica.getColumnDimension()] [this.order * metrica.getColumnDimension()] ;
		int row = 0 , column = 0 ;
		for ( i = 0 ; i < metrica.getColumnDimension() ; ++i ) {
			for ( a = 0 ; a < this.order ; ++a ){
				column = 0 ;
				for ( j = 0 ; j < metrica.getColumnDimension() ; ++j) {
					int b ;
					for ( b = 0 ; b < this.order ; ++ b) {
						matrix[row][column] = data[i][a][j][b] ;
						column = column + 1 ;
					}
				}
				row = row + 1 ;
			}
		}
		return new Matrix(matrix);
	}
}

