package sexpansion;

import Jama.Matrix;

/**
 * A class to represent the result of getting the resonant subalgebra of a S-expanded Lie algebra.
 * 
 * A resonant decomposition for a discrete semigroup is given by two sets S_0 and S_1, such that the union of S_0 and S_1 contains 
 * all the generators of the semigroup and that
 * S_0 \times S_0 \in S_0
 * S_0 \times S_1 \in S_1
 * S_1 \times S_1 \in S_0
 * 
 * We need a graded Lie algebra given by G = V_0 + V_1 , where
 * [V_0 , V_0] \in V_0
 * [V_0 , V_1 ]Ê\in V_1 
 * [ V_1 , V_1 ]Ê\in V_0
 * 
 *  The resonant subalgebra of the S-expanded Lie algebra is given by the elements
 *  G_R = ( S_0 \otimes V_0 ) + ( S_1 \otimes V_1 )
 * @author fnadal
 *
 */

public class StructureConstantSetExpandedResonant extends
		StructureConstantSetExpanded {
		SetS S0, S1 , V0 , V1 ;
		
		/**
		 * To be filled
		 * @param data
		 * @param S0
		 * @param S1
		 * @param S0
		 * @param S1
		 */
		
		
		/**
		 * Creates a StructureConstantSetExpandedResonant, given the structure constants of the expanded algebra, the resonant decomposition
		 * of the semigroup and the graded decomposition of the algebra
		 * @param data
		 * @param S0
		 * @param S1
		 * @param S0
		 * @param S1
		 */
		public StructureConstantSetExpandedResonant ( double [][][][][][] data , SetS S0 , SetS S1 , SetS V0 , SetS V1 ) {
			super(data);
			this.S0 = S0 ;
			this.S1 = S1 ;
			this.V0 = V0 ;
			this.V1 = V1 ;
			int i,a, j , b , k , lambda ;
			for ( i = 0 ; i < this.n ; ++i) {
				for ( a = 0 ; a < this.m ; ++a) {
					for ( j = 0 ; j < this.n ; ++j) {
						for ( b = 0 ; b < this.m ; ++b) {
							for ( k = 0 ; k < this.n ; ++k) {
								for ( lambda = 0 ; lambda < this.m ; ++lambda) {
									if( ( (S0.find(a+1) && V0.find(i+1)) || (S1.find(a+1) && V1.find(i+1))) &&  ( (S0.find(b+1) && V0.find(j+1)) || (S1.find(b+1) && V1.find(j+1))) &&  ( (S0.find(lambda+1) && V0.find(k+1)) || (S1.find(lambda+1) && V1.find(k+1)))) {
										;
									}
									else {
										data[i][a][j][b][k][lambda] = 0 ;
									}
								}
							}
						}
					}
				}
			}
		}
		
/**
 * Shows the structure constants in a convenient way, erasing the ones belonging to the expanded algebra but not in the
 * resonant subalgebra
 */
public void showPretty(){
	int i , a , lambda  , k ,  j , b ;
	int l = 0 ;
	System.out.println( "NOTATION for the Resonant Subalgebra:") ;
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
		  if ( (V0.find(i+1) && S0.find(j+1)) || (V1.find(i+1) && S1.find(j+1))) {
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
		  if ( (V0.find(i+1) && S0.find(j+1)) || (V1.find(i+1) && S1.find(j+1))) {
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
			if ( (S0.find(a+1) && V0.find(i+1)) || (S1.find(a+1) && V1.find(i+1))) {
			System.out.println("******");
			System.out.println("C_{("+(i+1)+","+(a+1)+") (j,b)}^{(k,c)}");
			for ( j = 0 ; j < n ; j = j + 1 ) {
				for ( b = 0 ; b < m ; b = b + 1 ) {
					if ((S0.find(b+1) && V0.find(j+1)) || (S1.find(b+1) && V1.find(j+1))){
					for ( k = 0 ; k < n ; ++k) {
							for ( lambda = 0 ; lambda < m ; ++lambda){
								if ( (S0.find(lambda+1) && V0.find(k+1)) || (S1.find(lambda+1) && V1.find(k+1))){
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
 * Shows the non vanishing commutators of the Resonant subalgebra
 */
public void showCommutRes(){
	System.out.println("Non vanishing commutators of the 'Resonant Subalgebra'") ;
	System.out.println("") ;
	System.out.println("n = "+n+" , Dimension of the original Lie algebra.") ;
	System.out.println("m = "+m+" , Order of the semigroup.");
	System.out.println("") ;
	int i , a , c  , k ,  j , b ;
	int l = 0 ;
	System.out.println("With the notation: X_{i,a}= X_{i} lambda_{a}, the generators of the 'Resonant Subalgebra' are given by:");
	for ( i = 0 ; i < this.n ; ++i ) {
		for ( j = 0 ; j < this.m ; ++j ){
		  if ( (V0.find(i+1) && S0.find(j+1)) || (V1.find(i+1) && S1.find(j+1))) {
			  System.out.println( " Y_{"+(l+1)+"} =  X_{"+(i+1)+","+(j+1)+"}") ;
		  }
		l = l + 1 ;
	}
	}
	System.out.println("") ;
	System.out.println("The non vanishing commutators of the 'Resonant Subalgebra' are given by:");
	for ( i = 0 ; i < n ; ++i){
		for ( a = 0 ; a < m ; a = a + 1 ) {
			if ( (S0.find(a+1) && V0.find(i+1)) || (S1.find(a+1) && V1.find(i+1))) { 
			for ( j = 0 ; j < n ; j = j + 1 ) {
				for ( b = 0 ; b < m ; b = b + 1 ) {
					if ((S0.find(b+1) && V0.find(j+1)) || (S1.find(b+1) && V1.find(j+1))){
					
					for ( k = 0 ; k < n ; ++k) {
							for ( c = 0 ; c < m ; ++c){
								if ( (S0.find(c+1) && V0.find(k+1)) || (S1.find(c+1) && V1.find(k+1)))
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

/**
 * Shows the non vanishing structure constants of the Resonant subalgebra
 */
public void showSCRes(){
	
	int i , a , c  , k ,  j , b ;
	System.out.println("Non vanishing structure constants of the 'Resonant Subalgebra' are given by:");
	for ( i = 0 ; i < n ; ++i){
		for ( a = 0 ; a < m ; a = a + 1 ) {
			if ( (S0.find(a+1) && V0.find(i+1)) || (S1.find(a+1) && V1.find(i+1))) { 
			for ( j = 0 ; j < n ; j = j + 1 ) {
				for ( b = 0 ; b < m ; b = b + 1 ) {
					if ((S0.find(b+1) && V0.find(j+1)) || (S1.find(b+1) && V1.find(j+1))){
					
					for ( k = 0 ; k < n ; ++k) {
						for ( c = 0 ; c < m ; ++c){
							if ( (S0.find(c+1) && V0.find(k+1)) || (S1.find(c+1) && V1.find(k+1)))
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

		
		/**
		 * Returns a matrix with the Cartan-Killing metric of the Lie algebra. This method erases the rows and columns related to the erased generators
		 * @return
		 */
		public Matrix cartanKillingMetricPretty(){
			//System.out.print(" n = ") ;
			//System.out.println(n);
			//System.out.print(" m = ") ;
			//System.out.println( m ) ;
			//System.out.println( this.data.length);
			//System.out.println( this.data[0].length);
			//System.out.println( this.data[0][0].length);
			//System.out.println( this.data[0][0][0].length);
			//System.out.println( this.data[0][0][0][0].length);
			//System.out.println( this.data[0][0][0][0][0].length);

			int i , a , lambda , g , k , l, j , b ;
			double suma = 0 ;
			int fila = 0 , columna = 0;
			double [][] metrica = new double[ n*m][n*m] ;
			for ( i = 0 ; i < n ; ++i){
				for ( a = 0 ; a < m ; a = a + 1 ) {
					columna = 0;
					for ( j = 0 ; j < n ; j = j + 1 ) {
						for ( b = 0 ; b < m ; b = b + 1 ) {
							suma = 0 ;
							for ( lambda = 0 ; lambda < m ; ++lambda) {
								for ( g = 0 ; g < m ; ++g) {
									for ( k = 0 ; k < n ; ++k){
										for ( l = 0 ; l < n ; ++l) {
											suma = suma + this.data[i][a][l][g][k][lambda] * this.data[j][b][k][lambda][l][g] ;
										}
									}
								}
							}
							metrica[fila ][columna] = suma ;
							columna = columna + 1 ;
						}
					}
					fila = fila + 1 ;
				}
			}
			
			int nGeneradores = V0.nElements * S0.nElements + V1.nElements * S1.nElements ;
			//System.out.println("La nueva mŽtrica tiene dimensiones");
			//System.out.println(nGeneradores);
			double[][] metricaFinal = new double[nGeneradores][nGeneradores];
			fila = 0 ;
			columna = 0 ;
			int filaOriginal, columnaOriginal ;
			for ( i = 0 ; i < n  ; ++i ) {
				for ( a = 0 ; a < m ; ++ a) {
					if  ( (S0.find(a + 1)  && V0.find(i +1  )) || ( S1.find(a+1) && V1.find( i +1) ) ) {
						columna = 0 ;
						for ( j = 0 ; j < n ; ++j) {
							for ( b = 0 ; b < m ; ++b ) {
								if  ( (S0.find(b+1)  && V0.find(j +1 )) || ( S1.find(b+1) && V1.find( j +1) ) )  {
									filaOriginal = m * i + a ;
									columnaOriginal = m * j + b ;
								//	System.out.print("Igualo elemento ") ;
								//	System.out.print( fila ) ;
								//	System.out.print(" , " ) ;
								//	System.out.print( columna) ;
								//	System.out.print(" al elemento ") ;
								//	System.out.print(filaOriginal );
								//	System.out.print(" , ") ;
								//	System.out.println( columnaOriginal) ;
									
									metricaFinal[fila][columna] = metrica[filaOriginal][columnaOriginal];
									columna = columna + 1 ;
								}
							}
						}
						fila = fila + 1 ;
					}
				}
												
			}
		return new Matrix( metricaFinal ) ;
		}
}
