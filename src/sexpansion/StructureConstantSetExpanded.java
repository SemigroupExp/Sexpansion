package sexpansion;

import Jama.Matrix;

/**
 * A class to represent the Lie algebra resulting of performing a S-expansion. When performing an S-expansion, we define the generators
 * T_A \equiv T_{a,\alpha} = X_a \lambda_\alpha . The Lie bracket then is given by
 * [ T_{a, \alpha} , T_{b,\beta} ]  = \lambda_{\alpha} \lambda_{\beta} f_{ab}^c T_c = K_{\alpha \beta}^\gamma \lambda_\gamma f_{ab}^c T_c =
 * = K_{\alpha \beta}^\gamma  f_{ab}^c T_{ c , \gamma} \equiv C_{(a, \alpha) (b , \beta)}^{(c, \gamma)} T_{c, \gamma}
 *
 */

public class StructureConstantSetExpanded {

	// n is the number of generators of the algebra, m is the order of the semigroup
	public int n,m ;
	public double [][][][][][] data ;
	
	/**
	 * Creates an empty object, able to accommodate an expansion of a n-dimensional Lie algebra by a semigroup or order m
	 * @param n the dimension of the original Lie algebra
	 * @param m the order of the semigroup
	 */
	public StructureConstantSetExpanded( int n , int m ){
		this.n = n ;
		this.m = m ;
		data = new double[n][m][n][m][n][m] ;
	}
	
	/**
	 * We use a 6-dimensional array to save the structure constants. This way, C_{(a, \alpha) (b, \beta)}^{(c,\gamma)} is represented 
	 * by data[a][\alpha][b ][\beta][c][\gamma]
	 * @param data
	 */
	
	public StructureConstantSetExpanded( double [][][][][][] data) {
		this.data = data;
		this.n = data.length ;
		this.m = data[0].length;
	}
	
	/**
	 * Returns  C_{(a , b) (c,d)}^{(e,f)} 
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 * @param f
	 */
	public double get( int a , int b , int c , int d , int e , int f ) {
		return data[a  ][b  ][c  ][d  ][e  ][f  ];
	}
	
	/**
	 * Sets the value of C_{(a , b) (c,d)}^{(e,f)} to 'value'
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 * @param f
	 * @param value
	 */
	public void set( int a , int b, int c , int d, int e, int f, double value ) {
		data[a  ][b  ][c  ][d  ][e  ][f ] = value ;
	}
	
	void exportLatex() {
		int i , a, j , b , k , lambda ;
		for ( i = 0 ; i < this.n ; ++i) {
			for ( a = 0 ; a < this.m ; ++a) {
				System.out.print( " [ X_{") ;
				System.out.print( i ) ;
				System.out.print( " , ");
				System.out.print( a ) ;
				System.out.print(" }");
				for ( j = 0 ; j < this.n ; ++j) {
					for ( b = 0 ; b < this.m ; ++b) {
						System.out.print( " , X_{") ;
						System.out.print( j ) ;
						System.out.print( " , ");
						System.out.print( b ) ;
						System.out.print(" } ] = ");
						for ( k = 0 ; k < this.n ; ++k) {
							for ( lambda = 0 ; lambda < this.m ; ++lambda) {
								if ( data[i][a][j][b][k][lambda] > 0) {
									System.out.print(" + ") ;
									System.out.print(data[i][a][j][b][k][lambda]) ;
									System.out.print( " , X_{") ;
									System.out.print( k ) ;
									System.out.print( " , ");
									System.out.print( lambda ) ;
									System.out.print(" }");
								}else if ( data[i][a][j][b][k][lambda] < 0) {
									System.out.print(data[i][a][j][b][k][lambda]) ;
									System.out.print( " , X_{") ;
									System.out.print( k ) ;
									System.out.print( " , ");
									System.out.print( lambda ) ;
									System.out.print(" }");
								}
							}
						}
						System.out.println (" \\newline ");
					}
				}
			}
		}
	}
	
	/**
	 * Shows the set of structure constants in a fancy way
	 */
	
	public void show(){

		int i , a , lambda  , k ,  j , b ;
		for ( i = 0 ; i < n ; ++i){
			System.out.println("Here we print the m tables C_{("+(i+1)+",a) (j,b)}^{(k,c)}, with a=1,...,m.");
			System.out.println("******");
			for ( a = 0 ; a < m ; a = a + 1 ) {
				System.out.println("C_{("+(i+1)+","+(a+1)+") (j,b)}^{(k,c)}");
				for ( j = 0 ; j < n ; j = j + 1 ) {
					for ( b = 0 ; b < m ; b = b + 1 ) {
						for ( k = 0 ; k < n ; ++k) {
								for ( lambda = 0 ; lambda < m ; ++lambda){
										System.out.print(" ");
										System.out.print(this.data[i][a][j][b][k][lambda]);
										System.out.print(" ");
								}
						}
						System.out.println("");
					}
				}
				System.out.println("******");
			}
			System.out.println("*****");
		}
	}
	
	
	public void showPretty () {
		int i,j ;
		int l = 0 ;
		System.out.println( "NOTATION for the Expanded algebra:") ;
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
				System.out.print( +(l+1)+", ") ;
				l = l + 1 ;
			}
		}
		System.out.println("") ;
		System.out.println("Or equivalently, ") ;
		System.out.print( "A,B = ") ;
		for ( i = 0 ; i < this.n ; ++i ) {
			for ( j = 0 ; j < this.m ; ++j ){
				System.out.print("("+(i+1)+","+(j+1)+"), ") ;
				l = l + 1 ;
			}
		}
		System.out.println("") ;
		System.out.println("") ;
		show();
	}
	

/**
 * Shows the non vanishing commutators of the Expanded algebra
 */
public void showCommut(){
	System.out.println("Non vanishing commutators of the 'Expanded algebra'") ;
	System.out.println("") ;
	System.out.println("n = "+n+" , Dimension of the original Lie algebra.") ;
	System.out.println("m = "+m+" , Order of the semigroup.");
	System.out.println("") ;
	int i , a , c  , k ,  j , b ;
	int l = 0 ;
	System.out.println("With the notation: X_{i,a}= X_{i} lambda_{a}, the generators of the 'Expanded algebra' are given by:");
	for ( i = 0 ; i < this.n ; ++i ) {
		for ( j = 0 ; j < this.m ; ++j ){
			System.out.println( " Y_{"+(l+1)+"} =  X_{"+(i+1)+","+(j+1)+"}") ;
		l = l + 1 ;
		}
	}
	System.out.println("") ;
	System.out.println("The non vanishing commutators of the 'Expanded algebra' are given by:");
	for ( i = 0 ; i < n ; ++i){
		for ( a = 0 ; a < m ; a = a + 1 ) {
			for ( j = 0 ; j < n ; j = j + 1 ) {
				for ( b = 0 ; b < m ; b = b + 1 ) {
					for ( k = 0 ; k < n ; ++k) {
						for ( c = 0 ; c < m ; ++c){
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


/**
 * Shows the non vanishing structure constants of the Expanded algebra
 */
public void showSC(){
	int i , a , c  , k ,  j , b ;
	System.out.println("Non vanishing structure constants of the Expanded algebra:");
	for ( i = 0 ; i < n ; ++i){
		for ( a = 0 ; a < m ; a = a + 1 ) {
			for ( j = 0 ; j < n ; j = j + 1 ) {
				for ( b = 0 ; b < m ; b = b + 1 ) {
					for ( k = 0 ; k < n ; ++k) {
						for ( c = 0 ; c < m ; ++c){
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
	
	/**
	 * Calculates the Cartan-Killing metric of this Lie algebra
	 * @return
	 */
	public Matrix cartanKillingMetric(){
		
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
		return new Matrix( metric );
	}
	/*
	 * Returns the eigenValues of the Cartan-Killing matrix
	 */
	public double[] detKC() {
		Jama.Matrix metric = this.cartanKillingMetric();
		Jama.EigenvalueDecomposition dec = new Jama.EigenvalueDecomposition(metric);
		return dec.getRealEigenvalues();
	}
	
	/*
	 * Returns the eigenValues of the Cartan-Killing matrix
	 */
	public double[] eigenValues() {
		Jama.Matrix metric = this.cartanKillingMetric();
		Jama.EigenvalueDecomposition dec = new Jama.EigenvalueDecomposition(metric);
		return dec.getRealEigenvalues();
	}
	
	/**
	 * Returns a Jama.Matrix object containing the eigenVectors of the Cartan-Killing metric
	 * @return
	 */
	public Matrix eigenVectors() {
		Jama.Matrix metric = this.cartanKillingMetric();
		Jama.EigenvalueDecomposition dec = new Jama.EigenvalueDecomposition(metric);
		return dec.getV();
	}
}
