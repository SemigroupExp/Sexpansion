package calculations;
import sexpansion.Semigroup;
import sexpansion.SetS;

public class hasRepeatedElement {
	
	public static boolean hasNonZeroRepeatingElement(SetS S0, SetS S1,int zero) {
		if(zero != -1) {
			for(int i=0;i<S0.nElements;i++) {
				for(int j=0;j<S1.nElements;j++){
					if(S0.elementAt(i)==S1.elementAt(j) && S0.elementAt(i)!=zero) {
						return true;
					}
				}
			}
			return false;
		}
		else {
			for(int i=0;i<S0.nElements;i++) {
				for(int j=0;j<S1.nElements;j++){
					if(S0.elementAt(i)==S1.elementAt(j)) {
						return true;
					}
				}
			}
			return false;
		}
	}

	public static void main (String[] args) {		
		Semigroup [] SemigroupL = Semigroup.loadFromFile("src/data/") ;
		SetS [][] Resonances;
		int zero;
		for (int i =0;i<SemigroupL.length;i++) {
			if (SemigroupL[i].order==3 && SemigroupL[i].isCommutative()) {
				Resonances = SemigroupL[i].findAllResonances();
				zero = SemigroupL[i].findZero();
				if(Resonances!=null){
					for(int j=0;j<Resonances.length;j++){
						if(!hasNonZeroRepeatingElement(Resonances[j][0],Resonances[j][1],zero)){
							System.out.println("Se ha encontrado una resonancia que pasa el filtro");
							System.out.println("Para el semigrupo Numero " +SemigroupL[i].ID +" con elemento zero " +zero);
							System.out.print("S0:");
							Resonances[j][0].show();
							System.out.print("S1: ");
							Resonances[j][1].show();
						}
					}
				}
			}
		}
			
	 }
		
}

