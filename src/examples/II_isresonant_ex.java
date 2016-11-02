package examples;

import sexpansion.Semigroup;
import sexpansion.SetS;

public class II_isresonant_ex {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Let us run the program!");
		// TODO Auto-generated method stub
		//Cree un array
		//int[][] matriu = new int[3][3] ;
		int[][] matriu = {{4,4,1,4},{4,2,4,4},{1,4,3,4}, {4,4,4,4}}; 
		//System.out.println(matriu[1][0]) ;
		//Semigroup [] resultat;
		Semigroup s = new Semigroup(matriu);
		s.show();
		System.out.println("Is associative?");
		System.out.println(s.isAssociative());
		System.out.println("Is commutative?");
		System.out.println(s.isCommutative());
		System.out.println("Does it have a zero element?");
		System.out.println(s.findZero());
		//resultat = Semigroup.loadFromFile();
		/*int i;
		System.out.println("Semigroups loaded from file:");
		for ( i = 0 ; i < resultat.length ; ++i) {
		System.out.print("Group #");
		System.out.println(resultat[i].ID);
		resultat[i].show();
		System.out.println("The semigroup is associative?");
		System.out.println(resultat[i].isAssociative());
		System.out.println("The semigroup is commutative?");
		System.out.println(resultat[i].isCommutative());
		}*/
		System.out.println("Let us check now the code that checks resonances");
		int[][] grupet = {{4,1,4,4},{1,2,3,4},{4,3,4,4},{4,4,4,4}};
		int[] s0i = {2,4};
		int[] s1i = {1,3,4};
		Semigroup grup = new Semigroup(grupet) ;
		SetS s0 = new SetS(s0i);
		SetS s1 = new SetS(s1i);
		s0.show();
		System.out.println(" and ") ;
		s1.show();
		System.out.println("is resonant?");
		System.out.println( grup.isResonant(s0, s1));
		System.out.println("Let us check now the code that checks resonances");
		int[][] grupet2 = {{2,3,4,4},{3,4,4,4},{4,3,4,4},{4,4,4,4}};
		int[] s0i2 = {2,4};
		int[] s1i2 = {1,3,4};
		Semigroup grup2 = new Semigroup(grupet2) ;
		SetS s02 = new SetS(s0i2);
		SetS s12 = new SetS(s1i2);
		s02.show();
		System.out.println(" and ") ;
		s12.show();
		System.out.println("is resonant?");
		System.out.println( grup2.isResonant(s02, s12));
		grup2.findResonances( 2, 3) ;
		System.out.println("Now, let us look for all the resonances of the semigroup");
		SetS [][] resonancies = grup2.findAllResonances();
		int i;
		for ( i = 0 ; i < resonancies.length ; ++i) {
			System.out.println("S0:");
			resonancies[i][0].show();
			System.out.println("S1");
			resonancies[i][1].show();
		}
		
	}

}
