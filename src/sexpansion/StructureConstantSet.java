package sexpansion;

import Jama.* ;

/**
 * Class to represent a Lie algebra, by mean of its structure constants.
 * @author fnadal
 *
 */
public class StructureConstantSet {

	//Array to save the structure constants of the orignal Lie algebra
	double [][][] constants ;
	//Number of generators of the Lie algebra.
	int N ;
	
	/** 
	 * Creates a StructureConstantSet for a Lie algebra of dimension n
	 * @param n the dimension of the Lie algebra
	 */
	
	public StructureConstantSet( int n ){
		N = n ;
		constants = new double[N][N][N];
	}
	
	public boolean generatorsCommute( int i , int j ) {
		int k = 0 ;
		for ( k = 0 ; k < this.N ; ++k) {
			if ( Math.abs(this.constants[i][j][k] ) > 1e-12  ) {
				return false ;
			}
		}
		return true;
	}
	
	/**
	 * Sets the value of f_{ab}^c to a given value. It sets f_{ab}^{c} and also f_{ba}^c. 
	 * @param a
	 * @param b
	 * @param c
	 * @param fabc the value to set
	 */
	public void setStructureConstant( int a , int b , int c, double fabc ) {
		constants[a ][b  ][c  ] = fabc ;
		constants[b  ][a  ][c ] = - fabc;
	}
	
	
	/**
	 * Returns the value of f_{ab}^c
	 */
	public double structureConstant( int a, int b , int c ) {
		return constants[a  ][b  ][c ] ; 
	}
	
	/**
	 * Shows the structure constants. It shows n boxes, so f_{ij}^k is showed in box i, row j, column k.
	 */
	public void show() {
		int i ,  j  , k ;
		System.out.println("For the considered Lie algebra of dimension n, we print the n matrices C_{ij}^{k}=M_{jk}");
		System.out.println("(with i=1,...,n) which gives the adjoint representation for the elements of the algebra.");
		for ( i = 0 ; i < this.N ; ++i) {
			System.out.println("*********") ;
			System.out.println("Adj [ X_{"+(i+1)+"} ] = ( C_{"+(i+1)+",j}^{k} ) =");
			for ( j = 0 ; j < this.N ; ++j) {
				for ( k = 0 ; k < this.N ; ++k) {
					System.out.print(" ") ;
					System.out.print( this.constants[i][j][k]);
				}
				System.out.println("");
			}
			System.out.println("*****");
		}
	}
	
	/**
	 * Calculates the Cartan-Killing metric of the Lie algebra
	 */
	public Matrix cartanKillingMetric(){
		int a , b , c , d ;
		double sum = 0 ;
		double [][] metric = new double[N][N] ;
		for ( a = 0 ; a < N ; ++a) {
			for ( b = 0 ; b < N ; ++b ) {
				sum = 0 ;
				for ( c = 0 ; c < N ; ++c ) {
					for ( d = 0 ; d < N ; ++d ) {
						sum = sum + this.structureConstant(a  , c , d  ) * this.structureConstant(b  , d , c ) ;
					}
				}
				metric[a][b] = sum ;
			}
		}
		return new Matrix( metric );
	}
	
	public Matrix [] adjointGenerators () {
		Matrix [] generators = new Matrix[this.N];
		int i,j,k ;
		double [][] auxMatrix = new double [this.N][this.N];
		for ( i = 0 ; i < this.N ; ++i) {
			auxMatrix = new double [this.N][this.N];
			for ( j = 0 ; j < this.N ; ++j) {
				for ( k = 0 ; k < this.N ; ++k ) {
					auxMatrix[j][k] = this.constants[i][k][j] ;
				}
			}
			generators[i] = new Matrix(auxMatrix) ;
			System.out.println("Generator");
			generators[i].print(2, 2);
		}
		
		return generators;
	}
	
	public Matrix [] semisimpleAdjointGenerators () {
		Matrix [] generators = this.adjointGenerators() ;
		Matrix [] result = null;
		Matrix [] aux ;
		int i,j , nSemisimple = 0 ;;
		for ( i = 0 ; i < generators.length ; ++i) {
			System.out.println( "Generator?");
			System.out.println((generators[i].eig().getV()).det());
			if ( Math.abs((generators[i].eig().getV()).det()) > 1e-10 ) {
				System.out.println("Uno");
				generators[i].print(2, 2);
				System.out.println((generators[i].eig().getV()).det());
				if ( nSemisimple == 0 ) {
					result = new Matrix[1] ;
					result[0] = generators[i] ;
				} else {
					aux = new Matrix[result.length  ] ;
					for ( j = 0 ; j < result.length ; ++j) {
						aux[j] = result[j];
					}
					result = new Matrix[ nSemisimple + 1 ] ;
					for ( j = 0 ; j < nSemisimple ; ++ j ) {
						result[j] = aux[j] ;
					}
					result[nSemisimple ] = generators[i];
					nSemisimple = nSemisimple + 1 ;
				}
				
				
			}
		}
		
		return result;
	}
	
	/**
	 * Returns a list with the number of semisimple generators. I.e, generators { 1 , 4 and 6 }
	 * @return
	 */
	
	public int [] semisimpleAdjointGeneratorsReferences () {
		Matrix [] generators = this.adjointGenerators() ;
		int [] result = null;
		int [] aux ;
		int i,j , nSemisimple = 0 ;;
		for ( i = 0 ; i < generators.length ; ++i) {
			System.out.println( "Generator?");
			System.out.println((generators[i].eig().getV()).det());
			if ( Math.abs((generators[i].eig().getV()).det()) > 1e-10 ) {
				System.out.println("Uno");
				generators[i].print(2, 2);
				System.out.println((generators[i].eig().getV()).det());
				if ( nSemisimple == 0 ) {
					result = new int[1] ;
					result[0] = i ;
				} else {
					aux = new int [result.length] ;
					for ( j = 0 ; j < result.length ; ++j) {
						aux[j] = result[j];
					}
					result = new int[ nSemisimple + 1 ] ;
					for ( j = 0 ; j < nSemisimple ; ++ j ) {
						result[j] = aux[j] ;
					}
					result[nSemisimple ] = i;
					nSemisimple = nSemisimple + 1 ;
				}
				
				
			}
		}
		
		return result;
	}
	
	
	
	
	public Matrix Casimir() {
		Matrix CK = this.cartanKillingMetric() ;
		Matrix inverseCK = CK.inverse() ;
		Matrix M = new Matrix(N, N );
		int j1 , j2 , k , i , l ;
		double suma = 0 ;
		for ( i = 0 ; i < this.N ; ++i ) {
			for ( l = 0 ; l < this.N ; ++l  ) {
				suma = 0 ;
				for ( j1 = 0 ; j1 < this.N ; ++j1 ) {
					for ( j2 = 0 ; j2 < this.N ; ++j2 ) {
						for ( k = 0 ; k < this.N ; ++k) {
							suma =  suma + this.constants[j1][i][k] * this.constants[j2][k][l] * inverseCK.get(j1, j2);
						}
					}
				}
				M.set(i, l, suma);
			}
		}
		return M;
	}
	
	public double [] CasimirEigenvalues () {
		return (this.Casimir().eig()).getRealEigenvalues();
	}
	
	//Matrix [] commutingAndSemisimplices () {
		
	//	int i, int j, int k ;
		
	//	}
		
	//	return null;
	//}
	
	public boolean generatorsSelfCommute (SetS generators ) {
		int i , j ;
		int nGeneradors = generators.nElements ;
		for ( i = 0 ; i < nGeneradors - 1 ; ++i) {
			for ( j = i + 1 ; j < nGeneradors ; ++j ) {
				if ( this.generatorsCommute( generators.elementAt(i) -1, generators.elementAt(j) -1 )  ) {
					;
				}
				else {
					return false ;
				}
			}
		}
		return true;
	}
	
	/*
	 * Returns a set object containing the maximal abelian subalgebra
	 */
	
	public SetS maximalAbelianSubalgebra () {
		SetS max = new SetS(this.N) ;
		int i = max.nElements , j ;
		SetS[] subSets = null;
		for ( i = max.nElements - 1 ; i > 0 ; ++i ){
			subSets =  max.subSets(i) ;
			for ( j = 0 ; j < subSets.length ; ++j ) {
				if ( this.generatorsSelfCommute( subSets[j]) ) {
					return subSets[i] ;
				}
			}
			
		}
		
		return new SetS(0);
		
	}	
}
