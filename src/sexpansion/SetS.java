package sexpansion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.io.IOException;

/**
 * A class to represent a set of integers, where none of them is repeated.
 * @author fnadal
 *
 */

public class SetS {

	int [] list ;
	public int nElements ;
	
	/**
	 * Builds a SetS object out of a array of integers
	 */
	public SetS( int [] elements ) {
		list = elements ;
		nElements = elements.length;
	}
	
	public SetS() {
		nElements = 0 ;
	}
	
	/**
	 * Builds a SetS object with n elements: the integers 1, 2, ..., n. 
	 * @param n The number of elements in the SetS
	 */
	
	public SetS( int n ){
		nElements = n;
		list = new int[n];
		int i ;
		for ( i = 0 ; i < n ; ++i) {
			list[i] = i +1 ;
		}
	}
	/**
	 * Prints a SetS to string in a fancy way
	 */
	public String toElegantReport() {
	    String aString;     
	    aString = "";
	    int column;
	    for (column = 0; column < nElements; column++ ) {
	     aString = aString + " " + this.list[column];
	    }
	    aString = aString.replaceAll("(?m)^ ", "");
	    return aString;
	}
	/**
	 * Shows a SetS object in a fancy way
	 */
    public void show() {
    	int i;
    	for ( i = 0 ; i < nElements ; ++i ) {
    		System.out.print( list[i]);
    		System.out.print( " ") ;
    	}
    	System.out.println("");
    }
	
    /**
     * Erases an element from the set
     * @param el the element to be erased (not the order of the element at the set, but the element itself)
     * @return a new set which doesn't contain the erased element
     */
    public SetS eraseElement( int el) {
    	int [] newList = new int[ nElements -1 ] ;
    	int i;
    	for ( i = 0 ; i < el ; ++i) {
    		newList[i] = this.list[i];
    	}
    	for ( i = el + 1 ; i < nElements ; ++i) {
    		newList[i-1] = this.list[i];
    	}
    	return new SetS(newList) ;
    }
    
    
    /**
     * Adds a new element to a given SetS 
     * @param el the element to be added
     * @return a SetS object where the element has been added
     */
    public SetS addElement( int el ) {
    	int[] newList = new int[ nElements +1 ] ;
    	int i;
    	for ( i = 0 ; i < nElements ; ++i ) {
    		newList[i] = this.list[i];
    	}
    	newList[nElements] = el ;
    	return new SetS( newList) ;
    }
    
    /**
     * Returns the ith element at the set. By convention, the elements in the set are ordered and the 1st element is at position 0
     * @param i the position of the element to be searched
     * @return the element at the given position
     */
    public int elementAt( int i ) {
    	return this.list[i] ;
    }
    
    public static SetS readFromFile( String arxiu) {
    	try {
    		FileReader reader = new FileReader(arxiu);
    		BufferedReader theReader ;
    		theReader = new BufferedReader(reader);
    		// La primera l�nia que llig �s la dimensi� del SetS
    		String str = theReader.readLine();
    		StringTokenizer st = new StringTokenizer(str);
    		String strNumber = st.nextToken();
    		int Nelements = Integer.parseInt(strNumber);
    		int[] array = new int[Nelements];
    		int i;
    		for ( i = 0 ; i < Nelements; i = i + 1 ) {
    			str = theReader.readLine();
        		st = new StringTokenizer(str);
        		strNumber = st.nextToken();
        		array[i] = Integer.parseInt(strNumber);
    		}
    		theReader.close();
        	return new SetS(array);
    	} catch ( IOException e) {
    		System.out.println("Error");
    	}
    	return null;
    }
    
    public SetS[] auxSubset( SetS original , SetS result, int n) {
    	int i;
    	SetS [] list = null;
    	SetS [] totalList = null;
    	//System.out.println("AuxSubset");
    	//System.out.println("original ");
    	//System.out.print( original.nElements);
    	//System.out.println(" elements");
    	//System.out.println("resultat ");
    	//System.out.print( resultat.nElements);
    	//System.out.println(" elements");
    	if ( n == 0 ) {
    		totalList = new SetS[1];
    		totalList[0] = result;
    		return totalList ;
    	}
    	SetS aux1;
    	SetS aux2;
    	for ( i = 0 ; i < original.nElements ; ++i ) {
    		//System.out.println("Volta ");
    		//System.out.println(i);
    		aux1= original.eraseElement(i);
    		aux2= result.addElement( original.elementAt(i));
        	//System.out.println("aux1 ");
        	//System.out.print( aux1.nElements);
        	//System.out.println(" elements");
        	//System.out.println("aux2 ");
        	//System.out.print( aux2.nElements);
        	//System.out.println(" elements");
    		list = auxSubset(aux1 , aux2 , n-1);
    		//System.out.println("A sumar resultats");
    		totalList = SetS.add(list , totalList );
    		//System.out.println("Llista total es ");
    		//System.out.println(llistaTotal);
    		if ( totalList != null ) {
    			//System.out.println("LlistaTotal t� ") ;
    			//System.out.println(llistaTotal.length);
    		}
    	}
    	return totalList ;
    }
    
    
    /**
     * Gives a value to the ith element of the set
     * @param i the position of the element to be changed
     * @param value its new value
     */
    public void setElement( int i , int value) {
    	this.list[i] = value;
    }
    
    /**
     * Returns true if 2 SetS objects are equal
     */
    public boolean equalTo( SetS s ) {
    	int i = 0 ;
    	if ( nElements != s.nElements) {
    		return false ;
    	}
    	else {
    		for ( i = 0 ; i < nElements ; ++ i) {
    			if ( list[i] != s.elementAt(i) ) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    /**
     * Looks for a given element in the set
     * @param n the element to be searched ( not its order!)
     * @return true if the element is in the set
     */
    public boolean find( int n ) {
    	int i;
    	for ( i = 0 ; i < nElements ; ++i) {
    		if ( list[i] == n ) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Checks if, combining the elements in s1 and s2, we get all the elements from 1 up to orden
     */
    
    public static boolean fillTheSpace( SetS s1 , SetS s2 , int orden) {
    	int i ;
    	for ( i = 0 ; i < orden ; ++i) {
    		if ( ! s1.find( i+1) && ! s2.find(i +1) ){
    			return false;
    		}
    	}
    	return true;
    }
    
    /**
     * Given a list of SetS objects, this method removes the duplicated sets.
     * @param lst a list of SetS objects
     * @return A new list of SetS objects without duplicated
     */
    public static SetS[] cleanDuplicates( SetS [] lst) {
    	int i, j ;
    	int n = lst.length ;
    	int elements = n;
    	SetS [] newList ;
    	for ( i = 0 ; i < n ; ++i) {
    		lst[i] = SetS.sort(lst[i]);
    	}
    	for ( i = 0 ; i < n ; ++i) {
    		for ( j = i +1 ; j < n ; ++j){
    			if (lst[i] != null && lst[j] != null && lst[i].equalTo(lst[j]) ) {
    				lst[j] = null ;
    				elements = elements - 1 ;	
    			}
    		}
    	}
    	newList = new SetS[elements] ;
    	j = 0 ;
    	for ( i = 0 ;  i < n ; ++i) {
    		if ( lst[i] != null ) {
    			newList[j] = lst[i];
    			++j;
    		}
    	}
    	
    	return newList ;
    }
    
    
    /**
     * A SetS object is expected to have its elements ordered. This method orders it, in case it wasn't already ordered.
     * @param s a unordered set object
     * @return a new SetS object, ordered
     */
    public static SetS sort(SetS s) {
    	int nelements = s.nElements ;
    	int i ;
    	int j;
    	int aux = 0;
    	for ( i = 0 ; i < nelements ; ++i) {
    		for ( j =  0 ; j < nelements - 1 ; ++j){
    			if ( s.elementAt(j) > s.elementAt(j+1) ) {
    				aux = s.elementAt(j);
    				s.setElement( j, s.elementAt(j+1));
    				s.setElement(j+1, aux);
    			}
    		}
    	}
    	return s;
    }
    
    static SetS [] add ( SetS [] s1 , SetS [] s2 ) {
    	
    	if ( s1 == null && s2 == null) {
    		return null;
    	}
    	else if ( s1 == null  && s2 != null ) {
    		return s2 ;
    	}
    	else if ( s1 != null && s2 == null ) {
    		return s1;
    	}
    	int l1 = s1.length , l2 = s2.length ;
    	int elements = l1 + l2 ;
    	int i;
    	SetS [] llista = new SetS[elements] ;
    	for ( i = 0 ; i < l1 ; ++i ){
    		llista[i] = s1[i];
    	}
    	for ( i = 0 ; i < l2 ; ++i) {
    		llista[i + l1 ] = s2[i];
    	}
    	return llista ;
    }
    
    
    /**
     * Returns all the subsets of n elements of a given SetS
     * @param n the number of elements in the subsets
     * @return a list of SetS objects with all the possible subsets
     */
    public SetS [] subSets( int n ) {
    	SetS result = new SetS();
    	return SetS.cleanDuplicates(auxSubset( this , result, n ));
    }
    
	public SetS [] allPermutations( ) {
		SetS result = new SetS(0) ;
		 return this.permutationsAux( this , result);
	}
	
	public SetS [] permutationsAux( SetS original, SetS result ) {
		int i , j;
		SetS original2 , result2 ;
		SetS [] list = null;
		SetS [] totalList = null;
		SetS [] previousList = null;
		int N = 0 ;
		if ( original.nElements == 0 ) {
			list = new SetS[1];
			list[0] = result ;
			return list ;
		}
		for ( i = 0 ; i < original.nElements ; i++ ){
			result2 = result.addElement( original.elementAt(i) ) ;
			original2 = original.eraseElement(i) ;
			list = permutationsAux( original2 , result2) ;
			N = N + list.length ;
			previousList = totalList ;
			totalList = new SetS[N];
			for ( j = 0 ; j < N - list.length ; ++j) {
				totalList[j] = previousList[j];
			}
			for ( j = 0 ; j < list.length ; ++j) {
				totalList[ j + N - list.length ] = list[j];
			}
		}
		
		return totalList;
	}
	
	// Returns the inverse of the set, seen as a permutation
	public SetS inversePermutation() {
		SetS [] permutations = this.allPermutations() ;
		int i, j ;
		boolean inverse = true ;
		for ( i = 0 ; i < permutations.length ; ++i) {
			inverse = true ;
			for ( j = 0 ; j < this.nElements ; ++j) {
				if ( permutations[i].elementAt( this.elementAt(j) - 1) -1 != j){
					inverse = false;
				}
			}
			if ( inverse == true ){
				return permutations[i];
			}
		}
		return null;
	}
    	
}
