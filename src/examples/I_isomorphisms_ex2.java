package examples;
import sexpansion.Semigroup;
import sexpansion.SetS;

public class I_isomorphisms_ex2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Semigroup[] lista = Semigroup.loadFromFile("src/data/");
		int i,k ;
		Semigroup [] permutations ;
		SetS [] p = (new SetS(4)).allPermutations();
		
		int [][] mSN3 = {{4,1,4,4},{1,2,3,4},{4,3,4,4},{4,4,4,4}};
		Semigroup SN3 = new Semigroup(mSN3);
		
		for ( i = 0 ; i < lista.length ; ++i){
			if ( lista[i].order == 4  ) {
				if ( lista[i].isotest(SN3)[0]) {
					System.out.println("The semigroup #" + lista[i].ID);
					lista[i].show();
					System.out.println("is isomorphic to SN3.");
					System.out.println("****");
					permutations = lista[i].permute();
					for ( k = 0 ; k < permutations.length; ++k) {
						if ( permutations[k].isEqualTo(SN3)) {
								System.out.print( "A permutation that brings #" 
						+ lista[i].ID + " to SN3 is P#");
								System.out.println(k);
								p[k].show();
								System.out.println("The inverse permutation is:");
								p[k].inversePermutation().show();
								System.out.println("----");
								
						}
					}	
				}
			}
		}
	}	
}
