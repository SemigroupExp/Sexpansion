package examples;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import sexpansion.Semigroup;
import sexpansion.SetS;
public class II_findzero_and_AllResonances_ord2_to_6 {

	/**
	 * @param args
	 */
	// To change the order modify the value of the int order (only for values between 2 and 6)
	private static int order=2;
	private static String Filename = String.format("./Output_II_findzero_and_AllResonances_ord"+order +".txt") ;
	public static void main(String[] args) throws IOException {
		Semigroup [] SemigroupL = Semigroup.loadFromFile("src/data/") ;
		Semigroup s ;
		SetS [][] Resonances;
		SetS S0, S1;
		int zeroelement ;
		int i, j, nResonances = 0, nSemigroupWithResonance = 0, TotalResonances = 0;
		File fout = new File(Filename);
		PrintWriter bw = new PrintWriter(fout, "UTF-8");
		bw.println("This is the output of the program II_findzero_and_AllResonances_ord2_to_6.java, which "
				+ "gives the list of semigroups of order "+order+" with zero element and resonant decompositions.");
		bw.println("The number of semigroups with zero elements and at least one resonance, as well as the total number "
				+ "of different resonances is given at the end of the file.");
		bw.println("************************************");
		for (i=0; i < SemigroupL.length ; i++) {
			s = SemigroupL[i];
			if (SemigroupL[i].order == order && SemigroupL[i].isCommutative() && s.findZero() != -1 ) {
				Resonances = SemigroupL[i].findAllResonances();
				if (Resonances != null) {
					nSemigroupWithResonance = nSemigroupWithResonance +1;
					TotalResonances = TotalResonances + Resonances.length;
					zeroelement = SemigroupL[i].findZero();
					bw.println("The semigroup #" +SemigroupL[i].ID);
					bw.print(SemigroupL[i].toElegantReport());
					bw.println("has #" +Resonances.length+ " resonances and " +zeroelement+ " is the zero element");
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
		bw.println("There are " +nSemigroupWithResonance +" semigroups with zero element and at least one resonance. In total, they have " +TotalResonances +" different resonances.");
		System.out.println("Results printed to file" +fout);
		System.out.println("For a better visualization open the file using Notepad++");
		bw.close();
	}
}