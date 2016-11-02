package examples;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import sexpansion.Semigroup;
public class II_findzero_ord2_to_6 {

	/**
	 * @param args
	 */
	// To change the order modify the value of the int order (only for values between 2 and 6)
	private static int order=2;
	private static String Filename = String.format("./Output_examples/Output_II_findzero_ord"+order +".txt") ;
	public static void main(String[] args) throws IOException {
		Semigroup[] list = Semigroup.loadFromFile("src/data/");
		Semigroup s ;
		int elementoCero ;
		int i,j;
		j = 0;
		File fout = new File(Filename);
		PrintWriter bw = new PrintWriter(fout, "UTF-8");
		bw.println("This is the output of the program II_findzero_ord2_to_6.java, which gives the list of semigroups of order "+order+" with zero element.");
		bw.println("The number of semigroups with zero element is given at the end of the file.");
		bw.println("************************************");
		for ( i = 0 ; i < list.length ; ++i){
			s = list[i];
			elementoCero = list[i].findZero();
				if ( s.order == order && elementoCero != -1 && list[i].isCommutative()) {
					++j;
					bw.println("#" +list[i].ID);
					bw.print(list[i].toElegantReport());
					bw.println("The zero element is " +elementoCero);
					bw.println("************************************");
				}
			}
		bw.println("Number of semigroups with zero element: " +j);
		System.out.println("Results printed to file" +fout);
		System.out.println("For a better visualization open the file using Notepad++");
		bw.close();
	}
}