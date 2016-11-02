package examples;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import sexpansion.SetS;
public class I_Permutations_and_inverses_Order_2_to_7 {

	/**
	 * @param args
	 */
	// To change the order modify the value of the int order (only for values between 2 and 7)
	private static int order = 2;
	private static String Filename = String.format("./Output_examples/Output_I_Permutations_and_inverses_n"+order +".txt") ;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int i, j ;
		SetS [] allpermut ;
		SetS n_elements ;
		SetS inv ;
		n_elements = new SetS(order);
		allpermut = n_elements.allPermutations() ;
		File fout = new File(Filename);
		PrintWriter bw = new PrintWriter(fout, "UTF-8");
		bw.println("This is the output of the program I_Permutations_and_inverses_Order_2_to_7.java, which "
				+ "gives the list of all permutations of "+order+" elements and their inverses.");
		bw.println("NOTATION: The n! permutations of a set of n elements are labeled here with P#X, with X=0,...,(n!-1)");
		bw.println("************************************");
		for ( i = 0 ; i < allpermut.length; ++i ) {
			bw.println("Permutation P#" +i);
			bw.println(allpermut[i].toElegantReport());
			inv=allpermut[i].inversePermutation();
			for ( j = 0 ; j < allpermut.length; ++j ) {
				if ( inv.equalTo(allpermut[j]) ) {
					bw.println("The inverse permutation (P#"+i+")^(-1) = P#"+j);
					bw.println(allpermut[j].toElegantReport());
					bw.println("************");
					
				}
			}
		}
		System.out.println("Results printed to file" +fout);
		System.out.println("For a better visualization open the file using Notepad++");
		bw.close();
	}
}