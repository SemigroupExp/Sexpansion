package examples;
import sexpansion.Semigroup;
import sexpansion.SetS;

public class II_findAllResonances_console_ord4 {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		Semigroup [] list = Semigroup.loadFromFile("src/data/") ;
		SetS [][] Resonances;
		SetS S0, S1;
		int i, j, nResonances = 0, nSemigroupWithResonance = 0, TotalResonances = 0;
		System.out.println("This is the output of the program II_findAllResonances_console_ord4.java, which "
				+ "gives the list of semigroups of order 4 with resonant decompositions.");
		System.out.println("The number of semigroups with at least one resonance and the total number "
				+ "of different resonances is given at the end of the file.");
		System.out.println("************************************");
		for (i=0; i < list.length ; i++) {
			if (list[i].order == 4 && list[i].isCommutative()) {
				Resonances = list[i].findAllResonances();
				if (Resonances != null) {
					nSemigroupWithResonance = nSemigroupWithResonance +1;
					TotalResonances = TotalResonances + Resonances.length;
					System.out.println("The semigroup #"+list[i].ID+" has "+Resonances.length+" resonances");
					list[i].show();
					//System.out.println("has #" +Resonances.length+ " resonances:");
					for (j = 0; j < Resonances.length ; j++) {
						nResonances = nResonances + 1;
						S0 = Resonances[j][0];
						S1 = Resonances[j][1];
						System.out.println("Resonance #" +nResonances);
						System.out.print("S0: "); 
						S0.show();
						System.out.print("S1: ");	
						S1.show();
					}
					System.out.println("************************************");
					nResonances = 0;
				}
			}
		}
		System.out.println("There are " +nSemigroupWithResonance +" semigroups with at least one resonance "
				+ "and there are in total " +TotalResonances +" different resonances.");
	}
}
