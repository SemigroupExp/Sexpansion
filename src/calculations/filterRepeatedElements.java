package calculations;
import sexpansion.SetS;
import sexpansion.Semigroup;

public class filterRepeatedElements {

		public static void main (String[] args) {
			SetS resonancias[][];
			Semigroup [] SemigroupL = Semigroup.loadFromFile("src/data/") ;
			int zero=-1;
			for(int i = 0;i<SemigroupL.length;i++){
				if(SemigroupL[i].order==4 && SemigroupL[i].isAssociative()) {
					resonancias = SemigroupL[i].filterResonances();
					zero = SemigroupL[i].findZero();
					if(resonancias != null) {
						for (int k=0;k<resonancias.length;k++) {
							System.out.println("Se ha encontrado una resonancia que pasa el filtro");
							System.out.println("Para el semigrupo Numero " +SemigroupL[i].ID +" con elemento zero " +zero);
							System.out.print("S0:");
							resonancias[k][0].show();
							System.out.print("S1: ");
							resonancias[k][1].show();
						}
					}
				}
			}
		}
}
