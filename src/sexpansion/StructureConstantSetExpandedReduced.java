package sexpansion;

import Jama.Matrix;

/**
 * Class to save the result of applying a S-expansion to a Lie algebra, getting the resonant subalgebra and then perform a reduction by zero
 * @author fnadal
 *
 */
public class StructureConstantSetExpandedReduced extends
		StructureConstantSetExpanded {
		int zero ;
		/**
		 * Creates an object able to get the result of this type of expansion followed by a reduction.
		 * @param n the dimension of the original Lie algebra
		 * @param m the order of the semigroup
		 * @param zero the zero element
		 */
		public StructureConstantSetExpandedReduced( int n, int m,  int zero ) {
			super( n , m ) ;
			this.zero = zero ;
		}
		
		/**
		 * To be filled
		 * @param data
		 * @param zero
		 */
		public StructureConstantSetExpandedReduced( double [][][][][][] data, int zero) {
			super(data);
			this.zero = zero;
			int i,a,j,b,k,lambda ;
			for ( i = 0 ; i < n ; ++i) {
				for ( a = 0 ; a < m ; ++a ) {
					for ( j = 0 ; j < n ; ++j) {
						for ( b = 0 ; b < m ; ++b) {
							for ( k = 0 ; k < n ; ++k) {
								for ( lambda = 0 ; lambda < m ; ++lambda) {
									if ( a == zero -1 || b == zero - 1 || lambda == zero - 1 ) {
										this.data[i][a][j][b][k][lambda] = 0 ;
									}
								}
							}
						}
					}
				}
			}
		}
		
		/**
		 * Calculates the Cartan-Killing metric of the Reduced algebra
		 * @return
		 */

		public Matrix cartanKillingMetricPretty(){
			
			//System.out.println("L'‡lgebra tŽ generadors: ");
			//System.out.println(n);
			//System.out.println("Y el semigroup: ");
			//System.out.println(m);
			int i , a , lambda , g , k , l, j , b ;
			double sum = 0 ;
			int row = 0 , column = 0;
			double [][] metric = new double[ n*m][n*m] ;
			for ( i = 0 ; i < n ; ++i){
				for ( a = 0 ; a < m ; a = a + 1 ) {
					column = 0;
					for ( j = 0 ; j < n ; j = j + 1 ) {
						for ( b = 0 ; b < m ; b = b + 1 ) {
							sum = 0 ;
							for ( lambda = 0 ; lambda < m ; ++lambda) {
								for ( g = 0 ; g < m ; ++g) {
									for ( k = 0 ; k < n ; ++k){
										for ( l = 0 ; l < n ; ++l) {
											sum = sum + this.data[i][a][l][g][k][lambda] * this.data[j][b][k][lambda][l][g] ;
										}
									}
								}
							}
							metric[row ][column] = sum ;
							column = column + 1 ;
						}
					}
					row = row + 1 ;
				}
			}
			
			int nGenerators = this.n * this.m - this.n ;
			//System.out.println("La nueva mŽtrica tiene dimensiones");
			//System.out.println(nGeneradores);
			double[][] finalMetric = new double[nGenerators][nGenerators];
			row = 0 ;
			column = 0 ;
			int originalRow, originalColumn ;
			for ( i = 0 ; i < n  ; ++i ) {
				for ( a = 0 ; a < m ; ++ a) {
					if ( a != zero -1  ) {
						column = 0 ;
						for ( j = 0 ; j < n ; ++j) {
							for ( b = 0 ; b < m ; ++b ) {
								if (  b != zero - 1  ) {
									originalRow = m * i + a ;
									originalColumn = m * j + b ;
									//System.out.print("Igualo elemento ") ;
									//System.out.print( fila ) ;
									//System.out.print(" , " ) ;
									//System.out.print( columna) ;
									//System.out.print(" al elemento ") ;
									//System.out.print(filaOriginal );
									//System.out.print(" , ") ;
									//System.out.println( columnaOriginal) ;
									
									finalMetric[row][column] = metric[originalRow][originalColumn];
									column = column + 1 ;
									}
								}
							}
						row = row + 1 ;
					}
				}
			}
		return new Matrix( finalMetric );
		}

public void showPretty() {
	int i , a , lambda  , k ,  j , b ;
	int l = 0 ;
	System.out.println( "NOTATION for the Reduced Algebra:") ;
	System.out.println("") ;
	System.out.println("n = "+n+" , Dimension of the original Lie algebra.") ;
	System.out.println("m = "+m+" , Order of the semigroup.");
	System.out.println( "") ;
	System.out.println( "To print the structure constants notice that for (i,a) fixed,") ;
	System.out.println( "the quantities C_{(i,a)(j,b)}^{(k,c)}=M_{A,B} are elements") ;
	System.out.println( "of a matrix M whose indices have the following values:") ;
	System.out.print( "A,B = ") ;
	for ( i = 0 ; i < this.n ; ++i ) {
		for ( j = 0 ; j < this.m ; ++j ){
		  if ( j != this.zero - 1 ) {
			  System.out.print( +(l+1)+", ") ;
		  }
			l = l + 1 ;
		}
	}
	System.out.println("") ;
	System.out.println("Or equivalently, ") ;
	System.out.print( "A,B = ") ;
	for ( i = 0 ; i < this.n ; ++i ) {
		for ( j = 0 ; j < this.m ; ++j ){
		  if ( j != this.zero - 1 ) {
			  System.out.print("("+(i+1)+","+(j+1)+"), ") ;
		  }
			l = l + 1 ;
		}
	}
	System.out.println("") ;
	System.out.println("") ;
	for ( i = 0 ; i < n ; ++i){
		System.out.println("Here we print the matrices C_{("+(i+1)+",a) (j,b)}^{(k,c)}, with the double indices having the values described above.");
		for ( a = 0 ; a < m ; a = a + 1 ) {
		  if ( a != this.zero -1 ) {
			System.out.println("******");
			System.out.println("C_{("+(i+1)+","+(a+1)+") (j,b)}^{(k,c)}");
			for ( j = 0 ; j < n ; j = j + 1 ) {
				for ( b = 0 ; b < m ; b = b + 1 ) {
				  if (  b != this.zero -1 ) {	
					for ( k = 0 ; k < n ; ++k) {
					  	for ( lambda = 0 ; lambda < m ; ++lambda){
					  		if ( lambda != this.zero -1 ) {
									System.out.print(" ");
									System.out.print(this.data[i][a][j][b][k][lambda]);
									System.out.print(" ");
					  		}
						}
					}
					System.out.println("");
					}
				}
			}
			//System.out.println("******");
			}
		}
		System.out.println("*****");
		}
	}


/**
 * Shows the non vanishing commutators of the Reduced algebra
 */
public void showCommutRed () {
	System.out.println("Non vanishing commutators of the 'Reduced algebra'") ;
	System.out.println("") ;
	System.out.println("n = "+n+" , Dimension of the original Lie algebra.") ;
	System.out.println("m = "+m+" , Order of the semigroup.");
	System.out.println("") ;
	
	int i , a , c  , k ,  j , b ;
	int l = 0 ;
	System.out.println("With the notation: X_{i,a}= X_{i} lambda_{a}, the generators of the 'Reduced algebra' are given by:");
	for ( i = 0 ; i < this.n ; ++i ) {
		for ( j = 0 ; j < this.m ; ++j ){
		  if ( j != this.zero - 1 ) {
			  System.out.println( " Y_{"+(l+1)+"} =  X_{"+(i+1)+","+(j+1)+"}") ;
		  }
			l = l + 1 ;
		}
	}
	System.out.println("") ;
	System.out.println("The non vanishing commutators of the 'Reduced algebra' are given by:");	
	for ( i = 0 ; i < n ; ++i){
		for ( a = 0 ; a < m ; a = a + 1 ) {
		  if ( a != this.zero -1 ) {	
			for ( j = 0 ; j < n ; j = j + 1 ) {
				for ( b = 0 ; b < m ; b = b + 1 ) {
				  if (  b != this.zero -1 ) {	
					for ( k = 0 ; k < n ; ++k) {
					  	for ( c = 0 ; c < m ; ++c){
					  		if ( c != this.zero -1 ) {
					  			if ( data[i][a][j][b][k][c] > 0) {
									if ( i < j ) {
										System.out.print( " [ X_{"+(i+1)+","+(a+1)+"}");
										
										System.out.print( " , X_{"+(j+1)+","+(b+1)+"} ] = ") ;
									
										System.out.print(+data[i][a][j][b][k][c]+" X_{"+(k+1)+","+(c+1)+"}") ;
										System.out.println("");
									}
								}
								else if ( data[i][a][j][b][k][c] < 0) {
									if ( i < j ) {
										System.out.print( " [ X_{"+(i+1)+","+(a+1)+"}");
										
										System.out.print( " , X_{"+(j+1)+","+(b+1)+"} ] = ") ;
															
										System.out.print(data[i][a][j][b][k][c]+" X_{"+(k+1)+","+(c+1)+"}") ;
										System.out.println("");
									}
								}
					  									  			
					  		}
						}
					}
					}
				}
			}
			}
		}
		}
	}
		

/**
 * Shows the non vanishing structure constants of the Reduced algebra
 */
public void showSCRed () {

	int i , a , c  , k ,  j , b ;
	System.out.println("Non vanishing structure constants of the 'Reduced algebra' are given by:");	
	for ( i = 0 ; i < n ; ++i){
		for ( a = 0 ; a < m ; a = a + 1 ) {
		  if ( a != this.zero -1 ) {	
			for ( j = 0 ; j < n ; j = j + 1 ) {
				for ( b = 0 ; b < m ; b = b + 1 ) {
				  if (  b != this.zero -1 ) {	
					for ( k = 0 ; k < n ; ++k) {
					  	for ( c = 0 ; c < m ; ++c){
					  		if ( c != this.zero -1 ) {
					  			if ( data[i][a][j][b][k][c] > 0) {
									if ( i < j ) {
										System.out.print(" C_{("+(i+1)+","+(a+1)+")("+(j+1)+","+(b+1)+")}^{("+(k+1)+","+(c+1)+")} = "+data[i][a][j][b][k][c]) ;
										System.out.println("");
									}
								}
								else if ( data[i][a][j][b][k][c] < 0) {
									if ( i < j ) {
										System.out.print(" C_{("+(i+1)+","+(a+1)+")("+(j+1)+","+(b+1)+")}^{("+(k+1)+","+(c+1)+")} = "+data[i][a][j][b][k][c]) ;
										System.out.println("");
									}
								}
					  		}
						}
					}
					}
				}
			}
			}
		}
		}
	}
}
