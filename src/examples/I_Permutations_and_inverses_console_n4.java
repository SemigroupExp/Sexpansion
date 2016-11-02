package examples;
import sexpansion.SetS;

public class I_Permutations_and_inverses_console_n4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 4, i;
		SetS [] allpermut ;
		SetS n_elements ;
		n_elements = new SetS(n);
		System.out.println("Permutations of 4 elements and their inverses:");
		allpermut = n_elements.allPermutations() ;
		for ( i = 0 ; i < allpermut.length; ++i ) {
			System.out.println("Permutation #" +i);
			allpermut[i].show();
			System.out.println("The inverse permutation is:");
			allpermut[i].inversePermutation().show();
			System.out.println("************");
		}
	}
}