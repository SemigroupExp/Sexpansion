package examples;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import sexpansion.Semigroup;

public class I_commutative_ord2_to_6 {
	// To change the order modify the value of the int order (only for values between 2 and 6)
	private static int order=2;
	private static String Filename = String.format("./Output_examples/Output_I_commutative_ord"+order +".txt") ;
	public static void main(String[] args) throws IOException {
		Semigroup[] list = Semigroup.loadFromFile("src/data/");
		Semigroup s ;
		int i,j;
		j = 0;
		File fout = new File(Filename);
		PrintWriter bw = new PrintWriter(fout, "UTF-8");
		bw.println("This is the output of the program I_commutative_ord2_to_6.java, which "
				+ "gives the list of abelian semigroups of order "+order+"." );
		bw.println("The total number of abelian semigroups in this order is is given at the end of the file.");
		bw.println("************************************");
		for ( i = 0 ; i < list.length ; ++i){
			s = list[i];
			if ( s.order == order && list[i].isCommutative()) {
				++j;
				bw.println("#" + list[i].ID);
				bw.print(list[i].toElegantReport());
				bw.println("************************************");
			}
		}
	bw.println("Number of commutative semigroups of order 2: ");
	bw.println(+j);
	System.out.println("Results printed to file" +fout);
	System.out.println("For a better visualization open the file using Notepad++");
	bw.close();
	}

}
