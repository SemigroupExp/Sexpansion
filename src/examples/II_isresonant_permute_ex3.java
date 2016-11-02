package examples;
import sexpansion.Semigroup;
import sexpansion.SetS;

public class II_isresonant_permute_ex3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Semigroup[] lista = Semigroup.loadFromFile("src/data/");
		int i, k;
		int [] mS0 = {2,4};
		int [] mS1 = {1,3,4};
		SetS S0 = new SetS(mS0);
		SetS S1 = new SetS(mS1);
		
		Semigroup [] listaPermutaciones = null;
		for ( i = 0 ; i < lista.length ; ++i){
			if ( lista[i].order == 4 && ( lista[i].ID == 42 || lista[i].ID == 43 || lista[i].ID == 45 
					|| lista[i].ID == 48 || lista[i].ID == 55
					|| lista[i].ID == 56 || lista[i].ID == 58 || lista[i].ID == 82 || lista[i].ID == 85 || lista[i].ID == 86 
					|| lista[i].ID == 88 || lista[i].ID == 89 || lista[i].ID == 91 || lista[i].ID == 93 || lista[i].ID == 99 ) ) {
				System.out.print("Semigroup #");
				System.out.print(lista[i].ID);
				System.out.print(" , order ");
				System.out.println(lista[i].order);
				lista[i].show();
				listaPermutaciones = lista[i].permute() ;
				for ( k = 0 ; k < listaPermutaciones.length ; ++k) {
					if ( listaPermutaciones[k].isResonant(S0, S1)) {
						System.out.println("We have found that the semigroup is resonant with the desired decomposition");
						System.out.print("We show the permutation #");
						System.out.println(k);
						listaPermutaciones[k].show();
						
					}
				}
				System.out.println("********");
			
			}
		}
	}	
}
