package examples;

import sexpansion.Semigroup;

public class I_associative_checking {
	// This is a test
	public static void main(String[] args) {
	// Manually enter the semigroups table
	int[][] ms_ex1 = {{1,2,3},{2,1,2},{3,2,1}};
	int[][] ms_ex2 = {{1,1,1},{1,2,3},{1,1,1}};
	int[][] ms_ex3 = {{1,1,3},{1,2,3},{3,3,1}};
	int[][] ms_ex4 = {{5,4,2,1,3},{4,3,5,2,1},{2,5,1,3,4},
			          {1,2,3,4,5},{3,1,4,5,2}};
	int[][] ms_ex5 = {{5,4,2,1,3},{4,3,5,2,1},{2,5,1,3,4},
			          {1,2,3,4,5},{3,1,4,5,2}};
	int[][] ms_ex6 = {{1,1,1,1,5,5,1,1,5,5},
			          {1,2,1,1,5,5,1,1,5,5},
			          {3,3,3,3,6,6,3,3,6,6},
			          {3,3,3,4,6,6,3,3,6,6},
			          {1,1,1,1,5,5,5,5,1,1},
			          {3,3,3,3,6,6,6,6,3,3},
			          {1,1,3,3,5,6,7,8,9,10},
			          {1,1,3,3,5,6,8,7,10,9},
			          {3,3,1,1,6,5,9,10,8,7},
			          {3,3,1,1,6,5,10,9,7,8}};
	
	Semigroup s_ex1 = new Semigroup( ms_ex1) ;
	Semigroup s_ex2 = new Semigroup( ms_ex2 );
	Semigroup s_ex3 = new Semigroup( ms_ex3 );
	Semigroup s_ex4 = new Semigroup( ms_ex4 );
	Semigroup s_ex5 = new Semigroup( ms_ex5 );
	Semigroup s_ex6 = new Semigroup( ms_ex6 );
	
	if (s_ex1.isAssociative()) {
		System.out.println("The semigroup S_ex1 is associative");
	}
	else {
		System.out.println("The semigroup S_ex1 is not associative");	
	}
	if (s_ex2.isAssociative()) {
		System.out.println("The semigroup S_ex2 is associative");
	}
	else {
		System.out.println("The semigroup S_ex2 is not associative");	
	}
	if (s_ex3.isAssociative()) {
		System.out.println("The semigroup S_ex3 is associative");
	}
	else {
		System.out.println("The semigroup S_ex3 is not associative");	
	}
	if (s_ex4.isAssociative()) {
		System.out.println("The semigroup S_ex4 is associative");
	}
	else {
		System.out.println("The semigroup S_ex4 is not associative");	
	}
	if (s_ex5.isAssociative()) {
		System.out.println("The semigroup S_ex5 is associative");
	}
	else {
		System.out.println("The semigroup S_ex5 is not associative");	
	}
	if (s_ex6.isAssociative()) {
		System.out.println("The semigroup S_ex6 is associative");
	}
	else {
		System.out.println("The semigroup S_ex6 is not associative");	
	}
}
}