package sexpansion;

import java.io.*;
import java.util.StringTokenizer;

/**
 * A class to represent a discrete semigroup.
 * 
 * @author Felip Nadal
 * 
 *
 */

public class Semigroup {

	int[][] data;
	public int order;  //The order of the semigroup
	public int ID; // This is the number of semigroup for a given order.
	
	/**
	 * 
	 * @param data is a matrix of integer which represents the multiplication table of the semigroup. The convention here is that,
	 * for a semigroup of order n, the elements are labelled 1,2,3,...,n.
	 */
	public Semigroup(int [][]data) {
		order = data.length ;
		this.data = new int[order][order];
		int i = 0 ;
		int j = 0 ;
		for ( i = 0 ; i < order ; ++ i ) {
			for ( j = 0 ; j < order ; ++j ) {
				this.data[i][j] = data[i][j] ;
			}
		}
	}
	
	public Semigroup ( int order) {
		this.order = order;
		this.data = new int[order][order];
	}
	public Semigroup( String filename) {
		System.out.println("Not implemented yet!");
	}
	/**
	 * Returns the multiplication table as String
	 * @return
	 */
	public String toElegantReport() {
	    String aString;     
	    aString = "";
	    int column;
	    int row;

	    for (row = 0; row < order; row++) {
	        for (column = 0; column < order; column++ ) {
	        aString = aString + " " + this.data[row][column];
	        }
	    aString = aString + "\n";
	    }
	    aString = aString.replaceAll("(?m)^ ", "");
	    return aString;
	}
	/**
	 * 
	 * @return Returns the order of the semigroup
	 */
	public int elements(){
		return order ;
	}
	
	/**
	 * Shows the multiplication table of the semigroup in a convenient manner.
	 */
	public void show() {
		int i = 0 ;
		int j = 0 ;
		for ( i = 0 ; i < order ; ++i ) {
			for( j = 0 ; j < order ; ++j ) {
				System.out.print( this.data[i][j]) ;
				System.out.print(" ");
			}
			System.out.println(" ");
		}
	}
	/**
	 * 
	 * @return Returns true if the multiplication table is associative i.e. if the object is really a semigroup
	 */
	public boolean isAssociative() {
		int i , j , k ;
		for ( i = 0 ; i < order ; ++ i) {
			for ( j = 0 ; j < order ; ++j) {
				for ( k = 0 ; k < order ; ++ k) {
					if( ! (data[i][data[j][k]-1] == data[data[i][j]-1][k])) {
						return false ;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @return Returns true if the semigroup is commutative
	 */
	public boolean isCommutative(){
		int i, j;
		for ( i = 0 ; i < order ; ++i) {
			for( j = 0 ; j < order ; ++j ) {
				if( ! (data[i][j] == data[j][i])) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Checks if the semigroup given has a zero element
	 * 
	 * @return Returns -1 if the semigroup doesn't have a zero element, and the zero element otherwise.
	 */
	public int findZero() {
		//Returns -1 is can't find a zero element, and the zero element otherwise.
		int i, j ;
		boolean isZero = false ;
		for ( i = 0 ; i < order ; ++ i ) {
			if ( isZero == true ) {
				return i ;
			}
			j = 0 ;
			isZero = true;
			while ( isZero && (j < order )) {
				if ( data[j][i] != i+1) {
					isZero = false ;
				}
				++j;
			}	
		}
		if ( isZero == true ) {
			return order ;
		}
		return -1 ;
	}
	
	/**
	 * Loads all the semigroups which are saved in the data files
	 * @return A list with all the semigroups which have been loaded
	 */
	
	
	public static Semigroup readSemigroupFromFile( String arxiu) {
		Semigroup group = null;
		try {
			FileReader reader = new FileReader(arxiu);
			BufferedReader theReader ;
			theReader = new BufferedReader(reader);
			// La primera l�nia que llig �s l'ordre del semigrup
			String str = theReader.readLine();
			StringTokenizer st = new StringTokenizer(str);
			String strNumber = st.nextToken();
			int order = Integer.parseInt(strNumber);
			int[][] matrix = new int[order][order];
			int number;
			//System.out.println(order);
			int i  , j  ;
			for ( i = 0 ; i < order ; ++ i ){ 
				for ( j = 0 ; j < order ; ++j ) {
					str = theReader.readLine();
					st = new StringTokenizer(str);
					strNumber = st.nextToken();
					number = Integer.parseInt(strNumber);
					//System.out.println(number) ;
					matrix[i][j] = number;
				}
			}
			group = new Semigroup(matrix);
			group.order = order;
			theReader.close();
		} catch (IOException e) {
			System.out.println("Error");
		}
		return group;
	}
	
	private static Semigroup[] loadFile (int order, String Fname ) {
//		System.out.println("loading semigroups of order:");
//		System.out.println(order);
		int N = 0 ;
		int NSemigroups ;
		int number,id;
		BufferedReader theReader ;
		String str ;
		String strNumber;
		StringTokenizer st ;
		int [][] matrix ;
		Semigroup [] result;
		String fileName = null;
		int elements = 0 ;
		switch( order ) {
		case 2 :
			fileName = Fname + "sem.2" ;
			elements = 4;
			break;
		case 3 :
			fileName = Fname + "sem.3" ;
			elements= 18;
			break;
		case 4 :
			fileName = Fname + "sem.4" ;
			elements= 126;
			break;
		case 5 :
			fileName = Fname + "sem.5" ;
			elements= 1160;
			break;
		case 6 :
			fileName = Fname + "sem.6" ;
			elements= 15973;
			break;
		}
		result = new Semigroup[elements];

		//System.out.println('Anem a tractar de carregar un arxiu');
		File arxiu = new File( fileName );
		try {
			NSemigroups = elements ;
			FileReader reader = new FileReader(arxiu);
			theReader = new BufferedReader(reader);
			for ( N = 0 ; N < NSemigroups ; ++N ){
				matrix = new int[order][order];
				// La primera l�nia que llig �s el n�mero de semigrup i l'ordre
				str = theReader.readLine();
				st = new StringTokenizer(str);
				strNumber = st.nextToken();
				number = Integer.parseInt(strNumber);
				id = number ;
				//System.out.println(numero);
				strNumber = st.nextToken();
				number = Integer.parseInt(strNumber);
				//System.out.println(numero);
				int i  , j  ;
				for ( i = 0 ; i < order ; ++ i ){ 
					for ( j = 0 ; j < order ; ++j ) {
						str = theReader.readLine();
						st = new StringTokenizer(str);
						strNumber = st.nextToken();
						number = Integer.parseInt(strNumber);
						//System.out.println(numero) ;
						matrix[i][j] = number;
					}
				}
				result[N] = new Semigroup(matrix) ;
				result[N].ID = id ;
			}
//			System.out.println("Fi de l'arxiu");
			reader.close();
			} catch( IOException excepcio  ) {
				System.out.println("Error");
			}
		return result;
	}
	/**
	 * 
	 * @param Fname Specify the route to the sem.x files relative to your program
	 * @return loads all semigroups of orders 2 to 6
	 */
	
	public static Semigroup[] loadFromFile( String Fname ) {
		
		Semigroup [] result;
		Semigroup [] aux ;
		int i = 0 , j = 0 ;
		int alreadySaved = 0 ;
		result = new Semigroup[4+18+126+1160+15973];
		int[] elements = {4,18,126,1160,15973};
	
		for ( i = 2 ; i < 7 ; ++i) {
			aux = loadFile(i, Fname) ;
			for ( j = 0 ; j < elements[i - 2] ; ++j) {
				result[alreadySaved + j] = aux[j];
			}
			alreadySaved += elements[i-2];
		}
		return result;
	}	
	
	/**
	 * Load the lists of semigroup up to order 7, returning an array of Semigroup 
	 * objects. The only list which is not complete is the one for the order 7, which 
	 * contains 835,927 of the 836,021 semigroups existing on that order.  
	 */
	
	
	private static Semigroup[] loadAllFile (int order ) {
		System.out.println("loading semigroups of order:");
		System.out.println(order);
		int N = 0 ;
		int NSemigroups ;
		int number,id;
		BufferedReader theReader ;
		String str ;
		String strNumber;
		StringTokenizer st ;
		int [][] matrix ;
		Semigroup [] result;
		String fileName = null;
		int elements = 0 ;
		switch( order ) {
		case 2 :
			fileName = "src/semigroups/data/sem.2" ;
			elements = 4;
			break;
		case 3 :
			fileName = "src/semigroups/data/sem.3" ;
			elements= 18;
			break;
		case 4 :
			fileName = "src/semigroups/data/sem.4" ;
			elements= 126;
			break;
		case 5 :
			fileName = "src/semigroups/data/sem.5" ;
			elements= 1160;
			break;
		case 6 :
			fileName = "src/semigroups/data/sem.6" ;
			elements= 15973;
			break;
		case 7 :
			fileName = "src/semigroups/data/sem.7" ;
			elements= 835927;
			break;
		}
		result = new Semigroup[elements];

		//System.out.println('Anem a tractar de carregar un arxiu');
		File arxiu = new File( fileName );
		try {
			NSemigroups = elements ;
			FileReader reader = new FileReader(arxiu);
			theReader = new BufferedReader(reader);
			for ( N = 0 ; N < NSemigroups ; ++N ){
//				System.out.println(N);
				matrix = new int[order][order];
				// La primera l�nia que llig �s el n�mero de semigrup i l'ordre
				str = theReader.readLine();
				st = new StringTokenizer(str);
				strNumber = st.nextToken();
				number = Integer.parseInt(strNumber);
				id = number ;
				//System.out.println(numero);
				strNumber = st.nextToken();
				number = Integer.parseInt(strNumber);
				//System.out.println(numero);
				int i  , j  ;
				for ( i = 0 ; i < order ; ++ i ){ 
					for ( j = 0 ; j < order ; ++j ) {
						str = theReader.readLine();
						st = new StringTokenizer(str);
						strNumber = st.nextToken();
						number = Integer.parseInt(strNumber);
						//System.out.println(numero) ;
						matrix[i][j] = number;
					}
				}
				result[N] = new Semigroup(matrix) ;
				result[N].ID = id ;
			}
			System.out.println("End of File");
			reader.close();
			} catch( IOException excepcio  ) {
				System.out.println("Error");
			}
		return result;
	}
	
	/**
	 * Load all the avaliable semigroups from order 2 to 7 using the method "loadAllFile". 
	 */
	
	public static Semigroup[] loadAllFromFile( ) {
		
		Semigroup [] result;
		Semigroup [] aux ;
		int i = 0 , j = 0 ;
		int alreadySaved = 0 ;
		result = new Semigroup[4+18+126+1160+15973+835927];
		int[] elements = {4,18,126,1160,15973,835927};
	
		for ( i = 2 ; i <= 7 ; ++i) {
			aux = loadAllFile(i) ;
			for ( j = 0 ; j < elements[i - 2] ; ++j) {
				result[alreadySaved + j] = aux[j];
			}
			alreadySaved += elements[i-2];
		}
		return result;
	}
	
	
	
	
	/**
	 * Checks if a given decomposition of a semigroup is a resonant one
	 * @param s0
	 * @param s1
	 * @param orden the order of the semigroup
	 * @return Returns true if the given decomposition is resonant, false otherwise
	 */
	
	public boolean isResonant( SetS s0 , SetS s1) {
    	int i,j, n0 = s0.nElements, n1 = s1.nElements ;
    	if ( SetS.fillTheSpace(s0, s1, order)  ){
    		for ( i = 0 ; i < n0 ; ++i ) {
    			for ( j = 0 ; j < n0 ; ++j ) {
    				if ( ! s0.find(this.data[s0.elementAt(i) -1][s0.elementAt(j)-1] ) ) {
    					return false ;
    				}
    			}
    		}
    		for ( i = 0 ; i < n0 ; ++i ){
    			for ( j = 0 ; j < n1 ; ++j) {
    				if ( ! s1.find( this.data[s0.elementAt(i)-1][s1.elementAt(j)-1]))  {
    					return false;
    				}
    			}
    		}
    		for ( i = 0 ; i < n1 ; ++i) {
    			for ( j = 0 ; j < n1 ; ++j) {
    				if (! s0.find( this.data[s1.elementAt(i)-1][s1.elementAt(j)-1])) {
    					return false;
    				}
    			}
    		}
    	}else {
    		return false;
    	}
    	
    	return true;
    }
	
	/**
	 * Looks for all the resonant decomposition of a possible semigroup with n1 elements in S0 and n2 in S1
	 * @param n1 Number of elements of S0
	 * @param n2 Number of elements of S1
	 * @return Returns a list of 2-dimensional arrays of SetS with format  { SetS S0 , SetS S1 }. It means that for each element of
	 * the array (let's call it list), list[i][0] plays the role of S0 in the resonant decomposition and list[i][1] plays the role of S1.
	 */
	
	public SetS [][] findResonances( int n1, int n2 ) {
		SetS total = new SetS(this.order) ;
		SetS [] list1 = total.subSets(n1) ;
		SetS [] list2 = total.subSets(n2) ;
		SetS [][] result = null;
		SetS [][] auxiliar = null ;
		int foundResonances = 0 ;
		int i , j , k = 0;
		for ( i = 0 ; i < list1.length ; ++ i ) {
			for ( j = 0 ; j < list2.length ; ++j ) {
				if ( this.isResonant( list1[i], list2[j]) && SetS.fillTheSpace( list1[i], list2[j], this.order))
				{
					//System.out.println("FindResonances(a,b): resonancia encontrada");
					foundResonances = foundResonances + 1 ;
					auxiliar = result ;
					result = new SetS[foundResonances ] [2] ;
					for ( k = 0 ; k < foundResonances -1 ; ++k) {
						result[k][0] = auxiliar[k][0];
						result[k][1] = auxiliar[k][1];
					}
					result[ foundResonances - 1 ] [0] = list1[i];
					result[ foundResonances - 1] [1] = list2[j];
				}
			}
		}
		return result;
	}
	
	/**
	 * Looks for all possible resonant decomposition of the semigroup
	 * @return Returns a list of 2-dimensional arrays of SetS with format  { SetS S0 , SetS S1 }. It means that for each element of
	 * the array (let's call it list), list[i][0] plays the role of S0 in the resonant decomposition and list[i][1] plays the role of S1.
	 */
	public SetS [][] findAllResonances() {
		int i , j , k;
		SetS [][] result = null;
		SetS [][] auxiliar ;
		SetS [][] intermediateResult;
		int N = 0;
		for ( i = 1 ; i < this.order ; ++i) {
			for ( j = 1 ; j < this.order ; ++j) {
				intermediateResult = this.findResonances( i, j) ;
				if ( intermediateResult != null ) {
				//	System.out.println("FindAllResonances: he encontrado una resonancia");
					auxiliar = result ;
					N = N + intermediateResult.length ;
					result = new SetS[ N][2];
					for ( k = 0 ; k < N - intermediateResult.length ; ++k ) {
						result[k][0] = auxiliar[k][0];
						result[k][1] = auxiliar[k][1];
					}
					for ( k = 0 ; k < intermediateResult.length ; ++k) {
						result[ N - intermediateResult.length + k][0 ] = intermediateResult[k][0];
						result[ N - intermediateResult.length + k][ 1 ] = intermediateResult[k][1];
					}
				}
			}
		}
		return result;
	}

	/**
	 * Applies an isomorphism to the semigroup
	 * @param s is the SetS which identifies the permutation to apply
	 * @return Returns a new Semigroup which is the result of applying the isomorphism
	 */
	
	public Semigroup permuteWith( SetS s ) {
		int i,j;
		int [][] matrix = new int[this.order][ this.order];
		SetS inverse  = s.inversePermutation() ;
			//Abans que res copie la matriu 
		//for ( i = 0 ; i < this.NElements ; ++i ){
		//	for ( j = 0 ; j < this.NElements ; ++j) {
		//			matriu[i][j] = this.data[i][j];
		//		}
		//}
		for ( i = 0 ; i < this.order ; ++i) {
			//Intercanviem files
			for ( j = 0 ; j < this.order ; ++j) {
					matrix[i][j] = this.data[ inverse.elementAt(i) - 1] [ inverse.elementAt(j) -1] ;
					//matriu[l][ i] = this.data[ s.elementAt(l)-1 ][ i] ;
		}
		}
			//Ara reanomenem les entrades
		//System.out.println("Semigroup abans de reanomenar");
		//(new Semigroup(matriu)).show();
			for ( i = 0 ; i < this.order ; ++i) {
				for ( j = 0 ; j < this.order ; ++j) {
					if ( matrix[i][j] != -1 ) {
						matrix[i][j] = s.elementAt( matrix[i][j] - 1 );	
					}
				}
						
			}
		return new Semigroup(matrix);
	}
	/**
	 * Gives all the semigroups isomorphic to a given one
	 * @return A list containing all the isomorphic semigroups
	 */
	
	public Semigroup [] permute() {
		SetS identity = new SetS( this.order );
		SetS [] permutations = identity.allPermutations() ;
		int k;
		Semigroup [] result = new Semigroup [permutations.length];
		for ( k = 0 ; k < permutations.length ; ++k) {
			
				//System.out.println("***");
				//permutacions[k].show();
				//System.out.println("******");
				result[k ] = this.permuteWith(permutations[k]);	
				//resultat[k].show();
		}
		return result;
	}
	
	/**
	 * Applies all possible antiisomorphisms to the semigroup
	 * @return A list containing all the antiisomorphic semigroups
	 */
	
	public Semigroup [] antiPermute () {
		return (this.transpose()).permute( );
	}
	
	/**
	 * This methods tests for the existence of an isomorphism or antiisomorphism between the current object and the semigroup B.
	 * @param B is a semigroup which is believed to be isomorphic to the current one
	 * @return Return value mean: (false , false ) -> isomorphism or antiisomorphism don't exist.(true, true ) -> isomorphism exists. (true, false ) -> antiisomorphism exists
	 */
	public boolean [] isotest(  Semigroup B) {
		boolean [] resultNothing = {false , false} ;
		boolean [] resultIso = {true , true };
		boolean [] resultAnti = {true , false };
		Semigroup [] permutations = this.permute() ;
		Semigroup [] permutationsB = B.permute() ;
		int i,j;
		for ( i = 0 ; i < permutations.length; ++i) {
			for ( j = 0; j < permutationsB.length ; ++j) {
				if ( permutations[i].isEqualTo(permutationsB[j])) {
					return resultIso ;
				}
			}
		}
		
		Semigroup [] antiPermutations = this.antiPermute() ;
		Semigroup [] antiPermutationsB = B.antiPermute() ;
		for ( i = 0 ; i < permutations.length; ++i) {
			for ( j = 0; j < permutationsB.length ; ++j) {
				if ( antiPermutations[i].isEqualTo(antiPermutationsB[j])) {
					return resultAnti ;
				}
			}
		}
		return resultNothing;
	}
	
	
	/**
	 * Checks if the 2 semigroups are the same
	 * @param B the semigroup to check if is identic to the present one
	 * @return returns 1 if the semigroups are equal, 0 othercase
	 */
	
	int compare ( Semigroup B ) {
		int i,j;
	//	System.out.println("Compare:");
	//	this.show();
	//	B.show();
		for ( i = 0 ; i < this.order ; ++i ) {
			for ( j = 0 ; j < B.order ; ++j) {
				if ( this.data[i][j] > B.data[i][j]){
			//		System.out.println("Compare torna 0");
					return 0;
				}
			}
		}
		System.out.println("Compare torna 1");
		return 1;
	}
	/**
	 * Checks if the 2 semigroups are the same
	 * @param B is a semigroup to check if its the same that the current one
	 * @return true if are equal, false othercase
	 */
	public boolean isEqualTo ( Semigroup B ) {
		int i,j;
	//	System.out.println("Compare:");
	//	this.show();
	//	B.show();
		if ( this.order != B.order ) {
			return false;
		}
		for ( i = 0 ; i < this.order ; ++i ) {
			for ( j = 0 ; j < B.order ; ++j) {
				if ( this.data[i][j] != B.data[i][j]){
			//		System.out.println("Compare torna 0");
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Returns a semigroup with a multiplication table which is transposed to the original one
	 */
	public Semigroup transpose( ) {
		int [][] matrix  = new int [this.order][this.order];
		int i,j;
		for ( i = 0 ; i < this.order ;++i){
			for ( j = 0 ; j < this.order ; ++j) {
				matrix[i][j] = this.data[j][i];
			}
		}
		return new Semigroup(matrix);
	}
		
	/**
	 * Builds a selector for the semigroup
	 * @return a Selector object
	 */
	
	public Selector getSelector() {
		Selector result = new Selector( this.order);
		int i ,j ;
		for ( i = 0 ; i < this.order ; i = i + 1 ) {
			for ( j = 0 ; j < this.order ; j = j + 1 ) {
				result.set(i, j, this.data[i][j] - 1 , 1) ;
			}
		}
		return result;
	}
	
	/**
	 * Performs the S-expansion of a Lie algebra, represented by a StructureConstantSet object, by the current semigroup
	 * @param s represents the Lie algebra, 
	 * @return a StructureConstantSetExpanded object, with the result of the expansion
	 */
	public StructureConstantSetExpanded getExpandedStructureConstant( StructureConstantSet s ) {
		Selector selectors = this.getSelector() ;
		//Number of generators for the Algebra
		int n = s.N ;
		//Number of generators for the Semigroup
		int m = this.order ;
		double [][][][][][] matrix = new double[n][m ][n][m][n][m];
		int a,b,g, k , i , j ;
		for ( i = 0 ; i < n ; ++i) {
			for ( a = 0 ; a < m ; ++a) {
				for (j = 0 ; j < n ; ++j ) {
					for ( b = 0 ; b < m ; ++b) {
						for ( k = 0 ; k < n ; ++k) {
							for ( g = 0 ; g < m ; ++g){
								matrix[i][a][j][b][k][g] =  selectors.get(a  , b  , g ) * s.structureConstant(i  , j  , k );
							}
						}
					}
				}
			}
		}
		return new StructureConstantSetExpanded( matrix);
	}
}
	
