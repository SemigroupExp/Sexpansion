package examples;
import sexpansion.Semigroup;
import sexpansion.SetS;

public class I_isomorphisms_ex3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Semigroup[] lista = Semigroup.loadFromFile("src/data/");
		int i,k ;
		
		int [][] mSM3 = {{1,2,3,4},{2,3,4,1},{3,4,1,2},{4,1,2,3}};

		
		Semigroup SM3 = new Semigroup(mSM3);

		
		Semigroup [] permutations ;
		SetS [] p = (new SetS(4)).allPermutations();
		
		
		for ( i = 0 ; i < lista.length ; ++i){
			if ( lista[i].order == 4  ) {
				if ( lista[i].isotest(SM3)[0]) {
					System.out.println("The semigroup #" + lista[i].ID);
					lista[i].show();
					System.out.println("is isomorphic to SM3.");
					System.out.println("****");
					permutations = lista[i].permute();
					for ( k = 0 ; k < permutations.length; ++k) {
						if ( permutations[k].isEqualTo(SM3)) {
								System.out.print( "A permutation that brings #" + lista[i].ID + " to SM3 is P#");
								System.out.println(k);
								p[k].show();
								System.out.println("and the permuted semigroup is ");
								permutations[k].show();
								System.out.println("which is exactly SM3.");
								System.out.println("----");
								
						}
					}	
				}
			}
		}
	}	
}
