package examples;

import sexpansion.Semigroup;
import sexpansion.SetS;

public class I_PermuteWith_ex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int [][] mSN3 = {{4,1,4,4},{1,2,3,4},{4,3,4,4},{4,4,4,4}};
		Semigroup SN3 = new Semigroup(mSN3);
		int [][] mS42 = {{1,1,1,1},{1,1,1,2},{1,1,1,3},{1,2,3,4}};
		Semigroup S42 = new Semigroup(mS42);
		SetS n_elements = new SetS(4);
		SetS[] permutations = n_elements.allPermutations() ;
		SetS inv ;
		int j ;
		
		System.out.println("The semigroup S42");
		S42.show();
		System.out.println("permutated with P#19");
		permutations[19].show();
		System.out.println("gives ");
		Semigroup permutated1 = S42.permuteWith(permutations[19]);
		permutated1.show();
		System.out.println("************");
		
		System.out.println("The semigroup SN3");
		SN3.show();
		inv= permutations[19].inversePermutation();
		for ( j = 0 ; j < permutations.length; ++j ) {
			if ( inv.equalTo(permutations[j]) ) {
				System.out.println("permutated with (P#19)^(-1) = P#"+j);
				permutations[j].show();
				System.out.println("gives ");
				Semigroup permutated2 = SN3.permuteWith(permutations[j]);
				permutated2.show();
				System.out.println("************");
			}
		}
		
		
		System.out.println("The semigroup S42");
		S42.show();
		System.out.println("permutated with P#22");
		permutations[22].show();
		System.out.println("gives ");
		Semigroup permutated3 = S42.permuteWith(permutations[22]);
		permutated3.show();
		System.out.println("************");
		
		System.out.println("The semigroup SN3");
		SN3.show();
		inv= permutations[22].inversePermutation();
		for ( j = 0 ; j < permutations.length; ++j ) {
			if ( inv.equalTo(permutations[j]) ) {
				System.out.println("permutated with (P#22)^(-1) = P#"+j);
				permutations[j].show();
				System.out.println("gives ");
				Semigroup permutated4 = SN3.permuteWith(permutations[j]);
				permutated4.show();
				System.out.println("************");
			}
		}
	}
}