package examples;

import sexpansion.Semigroup;

public class I_isomorphisms_ex1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Manually enter the semigroups table

		int[][] mSE_5 = {{1,2,3,4,5,6,7},{2,3,4,5,6,7,7},{3,4,5,6,7,7,7},{4,5,6,7,7,7,7},{5,6,7,7,7,7,7},{6,7,7,7,7,7,7},{7,7,7,7,7,7,7}};
		int[][] mSM_6 = {{1,2,3,4,5,6,7},{2,3,4,5,6,7,2},{3,4,5,6,7,2,3},{4,5,6,7,2,3,4},{5,6,7,2,3,4,5},{6,7,2,3,4,5,6},{7,2,3,4,5,6,7}};
		
		Semigroup SE_5 = new Semigroup( mSE_5) ;
		Semigroup SM_6 = new Semigroup( mSM_6);

		if ( SE_5.isotest(SM_6)[0]) {
			System.out.println("SE_5 is isomorphic to SM_6");
		}
		else {
			System.out.println("SE_5 is not isomorphic to SM_6");
		}
 	}
}
