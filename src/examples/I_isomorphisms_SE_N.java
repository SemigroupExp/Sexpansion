package examples;
import sexpansion.Semigroup;
import sexpansion.SetS;

public class I_isomorphisms_SE_N {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Semigroup[] lista = Semigroup.loadFromFile("src/data/");
		int i,k ;
		
		int [][] mSE0 = {{1,2},{2,2}};
		int [][] mSE1 = {{1,2,3},{2,3,3},{3,3,3}};
		int [][] mSE2 = {{1,2,3,4},{2,3,4,4},{3,4,4,4},{4,4,4,4}};
		int [][] mSE3 = {{1,2,3,4,5},{2,3,4,5,5},{3,4,5,5,5},{4,5,5,5,5},{5,5,5,5,5}};
		int [][] mSE4 = {{1,2,3,4,5,6},{2,3,4,5,6,6},{3,4,5,6,6,6},{4,5,6,6,6,6},{5,6,6,6,6,6},{6,6,6,6,6,6}};
		
		
		Semigroup SE0 = new Semigroup(mSE0);
		Semigroup SE1 = new Semigroup(mSE1);
		Semigroup SE2 = new Semigroup(mSE2);
		Semigroup SE3 = new Semigroup(mSE3);
		Semigroup SE4 = new Semigroup(mSE4);

		
		Semigroup [] permutations_SE0 ;
		SetS [] p_SE0 = (new SetS(2)).allPermutations();
				
		for ( i = 0 ; i < lista.length ; ++i){
			if ( lista[i].order == 2  ) {
				if ( lista[i].isotest(SE0)[0]) {
					System.out.println("The semigroup #" + lista[i].ID);
					lista[i].show();
					System.out.println("is isomorphic to SE0.");
					System.out.println("****");
					permutations_SE0 = lista[i].permute();
					for ( k = 0 ; k < permutations_SE0.length; ++k) {
						if ( permutations_SE0[k].isEqualTo(SE0)) {
								System.out.print( "A permutation that brings #" + lista[i].ID + " to SE0 is P#");
								System.out.println(k);
								p_SE0[k].show();
								System.out.println("and the permuted semigroup is ");
								permutations_SE0[k].show();
								System.out.println("which is exactly SE0.");
								System.out.println("----");
								
						}
					}	
				}
			}
		}
		
		Semigroup [] permutations_SE1 ;
		SetS [] p_SE1 = (new SetS(3)).allPermutations();
				
		for ( i = 0 ; i < lista.length ; ++i){
			if ( lista[i].order == 3  ) {
				if ( lista[i].isotest(SE1)[0]) {
					System.out.println("The semigroup #" + lista[i].ID);
					lista[i].show();
					System.out.println("is isomorphic to SE1.");
					System.out.println("****");
					permutations_SE1 = lista[i].permute();
					for ( k = 0 ; k < permutations_SE1.length; ++k) {
						if ( permutations_SE1[k].isEqualTo(SE1)) {
								System.out.print( "A permutation that brings #" + lista[i].ID + " to SE1 is P#");
								System.out.println(k);
								p_SE1[k].show();
								System.out.println("and the permuted semigroup is ");
								permutations_SE1[k].show();
								System.out.println("which is exactly SE1.");
								System.out.println("----");
								
						}
					}	
				}
			}
		}

		Semigroup [] permutations_SE2 ;
		SetS [] p_SE2 = (new SetS(4)).allPermutations();
				
		for ( i = 0 ; i < lista.length ; ++i){
			if ( lista[i].order == 4  ) {
				if ( lista[i].isotest(SE2)[0]) {
					System.out.println("The semigroup #" + lista[i].ID);
					lista[i].show();
					System.out.println("is isomorphic to SE2.");
					System.out.println("****");
					permutations_SE2 = lista[i].permute();
					for ( k = 0 ; k < permutations_SE2.length; ++k) {
						if ( permutations_SE2[k].isEqualTo(SE2)) {
								System.out.print( "A permutation that brings #" + lista[i].ID + " to SE2 is P#");
								System.out.println(k);
								p_SE2[k].show();
								System.out.println("and the permuted semigroup is ");
								permutations_SE2[k].show();
								System.out.println("which is exactly SE2.");
								System.out.println("----");
								
						}
					}	
				}
			}
		}
		
		Semigroup [] permutations_SE3 ;
		SetS [] p_SE3 = (new SetS(5)).allPermutations();
				
		for ( i = 0 ; i < lista.length ; ++i){
			if ( lista[i].order == 5  ) {
				if ( lista[i].isotest(SE3)[0]) {
					System.out.println("The semigroup #" + lista[i].ID);
					lista[i].show();
					System.out.println("is isomorphic to SE3.");
					System.out.println("****");
					permutations_SE3 = lista[i].permute();
					for ( k = 0 ; k < permutations_SE3.length; ++k) {
						if ( permutations_SE3[k].isEqualTo(SE3)) {
								System.out.print( "A permutation that brings #" + lista[i].ID + " to SE3 is P#");
								System.out.println(k);
								p_SE3[k].show();
								System.out.println("and the permuted semigroup is ");
								permutations_SE3[k].show();
								System.out.println("which is exactly SE3.");
								System.out.println("----");
								
						}
					}	
				}
			}
		}
		
		Semigroup [] permutations_SE4 ;
		SetS [] p_SE4 = (new SetS(6)).allPermutations();
				
		for ( i = 0 ; i < lista.length ; ++i){
			if ( lista[i].order == 6  ) {
				if ( lista[i].isotest(SE4)[0]) {
					System.out.println("The semigroup #" + lista[i].ID);
					lista[i].show();
					System.out.println("is isomorphic to SE4.");
					System.out.println("****");
					permutations_SE4 = lista[i].permute();
					for ( k = 0 ; k < permutations_SE4.length; ++k) {
						if ( permutations_SE4[k].isEqualTo(SE4)) {
								System.out.print( "A permutation that brings #" + lista[i].ID + " to SE4 is P#");
								System.out.println(k);
								p_SE4[k].show();
								System.out.println("and the permuted semigroup is ");
								permutations_SE4[k].show();
								System.out.println("which is exactly SE4.");
								System.out.println("----");
								
						}
					}	
				}
			}
		}
		
	}	
}
