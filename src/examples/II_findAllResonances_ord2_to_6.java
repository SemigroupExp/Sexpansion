package examples;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import sexpansion.Semigroup;
import sexpansion.SetS;
public class II_findAllResonances_ord2_to_6 {

	/**
	 * @param args
	 */
	// To change the order modify the value of the int order (only for values between 2 and 6)
	private static int order=2;
	private static String Filename = String.format("./Output_examples/Output_II_findAllResonances_ord"+order +".txt") ;

	public static void main(String[] args) throws IOException {
		Semigroup [] SemigroupL = Semigroup.loadFromFile("src/datos/") ;
		SetS [][] Resonances;
		SetS S0, S1;
		int i, j, nResonances = 0, nSemigroupWithResonance = 0, TotalResonances = 0;
		File fout = new File(Filename);
		PrintWriter bw = new PrintWriter(fout, "UTF-8");
		bw.println("This is the output of the program II_findAllResonances_ord2_to_6.java, which "
				+ "gives the list of semigroups of order" +order +"with resonant decompositions.");
		bw.println("The number of semigroups with at least one resonance and the total number "
				+ "of different resonances is given at the end of the file.");
		bw.println("************************************");
		for (i=0; i < SemigroupL.length ; i++) {
			if (SemigroupL[i].order == order && SemigroupL[i].isCommutative()) {
				Resonances = SemigroupL[i].findAllResonances();
				if (Resonances != null) {
					nSemigroupWithResonance = nSemigroupWithResonance +1;
					TotalResonances = TotalResonances + Resonances.length;
					bw.println("The semigroup #" +SemigroupL[i].ID);
					bw.print(SemigroupL[i].toElegantReport());
					bw.println("has #" +Resonances.length+ " resonances.");
					for (j = 0; j < Resonances.length ; j++) {
						nResonances = nResonances + 1;
						S0 = Resonances[j][0];
						S1 = Resonances[j][1];
						bw.println("Resonance #" +nResonances);
						bw.println("S0: " +S0.toElegantReport());
						bw.println("S1: " +S1.toElegantReport());
					}
					bw.println("************************************");
					nResonances = 0;
				}
			}
		}
		bw.println("There are " +nSemigroupWithResonance +" semigroups with at least one resonance and there are in total " +TotalResonances +" different resonances.");
		System.out.println("Results printed to file" +fout);
		System.out.println("For a better visualization open the file using Notepad++");
		bw.close();
	}
}
