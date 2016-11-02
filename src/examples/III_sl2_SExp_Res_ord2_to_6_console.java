package examples;
import Jama.Matrix;
import sexpansion.Semigroup;
import sexpansion.SetS;
import sexpansion.StructureConstantSet;
import sexpansion.StructureConstantSetExpanded;
import sexpansion.StructureConstantSetExpandedResonant;


public class III_sl2_SExp_Res_ord2_to_6_console {

	/**
	 * @param args
	 */
	// To change the order modify the value of the int order (only for values between 2 and 6)
	private static int order=2;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Matrix metric ;
		
		Semigroup [] listSemigroups = Semigroup.loadFromFile("src/datos/") ;
		
		System.out.println("We introduce the structure constants of sl2");
		StructureConstantSet sl2 = new StructureConstantSet(3) ;
		sl2.setStructureConstant(0, 1, 2, -2) ;
		sl2.setStructureConstant(0, 2, 1, 2) ;
		sl2.setStructureConstant(1, 2, 0, 2) ;
		metric = sl2.cartanKillingMetric() ;
		System.out.println("We show the Killing-Cartan metric");
		metric.print(2, 2) ;
		System.out.println("and its determinant");
		System.out.println(metric.det());
		System.out.println(" ");
		System.out.println("In what follows we expand sl2 with all the abelian semigroups of order "+order+" having ");
		System.out.println("a resonance; and identify the ones that leads to a semisimple algebra.");
		System.out.println("**********");
		System.out.println("");
		Semigroup semigroup;
	    SetS [][] resonances ;
	    SetS S0 , S1 ;
	    int[] mV0 = {1};
	    SetS V0 = new SetS(mV0);
	    int[] mV1 = {2,3};
	    SetS V1 = new SetS(mV1);
	    StructureConstantSetExpanded expandedAlgebra;
	    StructureConstantSetExpandedResonant ResAlgebra;
	    int nResonant = 0 , nSemisimple = 0;
	    int nTotalResonances = 0 ;
	    boolean AllSemisimple = true ;
	    int nAllSemisimple = 0 ;
	    int i , j;
	    for ( i = 0 ; i < listSemigroups.length ; ++i) {
	    	semigroup = listSemigroups[i] ;
	    	if ( semigroup.order == order && semigroup.isCommutative()) {
	    		expandedAlgebra  = semigroup.getExpandedStructureConstant( sl2 );
	    		resonances = semigroup.findAllResonances() ;
	    		if ( resonances != null ) {
	    			nResonant = nResonant + 1 ;
	    			AllSemisimple = true ;
	    			for ( j = 0 ; j < resonances.length ; ++j ) {
	    				nTotalResonances = nTotalResonances + 1 ;
	    				S0 = resonances[j][0] ;
	    				S1 = resonances[j][1] ;
	    				ResAlgebra = new StructureConstantSetExpandedResonant(expandedAlgebra.data, S0, S1, V0, V1);
	    				metric = ResAlgebra.cartanKillingMetric() ;
	    				metric = ResAlgebra.cartanKillingMetricPretty() ;
	    				if ( metric.det() != 0.0 ) {
	    					nSemisimple = nSemisimple + 1;
	    					System.out.println("A semisimple algebra has been found, expanding with the semigroup #"+semigroup.ID);
	    					semigroup.show();
	    					System.out.println("whith the resonance:");
	    					System.out.print("S0: ");
			    			S0.show();
			    			System.out.print("S1: " ) ;
			    			S1.show();
			    			System.out.println("The metric of the reduction of the resonant subalgebra is:" ) ;
	    					metric.print(2, 2) ;
	    					System.out.println("and its determinant");
	    					System.out.println(metric.det());
	    					System.out.println("**********");
	    				}
	    				else {
	    					AllSemisimple = false;
	    				}
	    			}
	    			if ( AllSemisimple && resonances.length > 1 ) {
	    				nAllSemisimple = nAllSemisimple +1 ;
	    				System.out.print("The semigroup #");
	    				System.out.print( semigroup.ID);
	    				System.out.println(" gives semisimple algebras with all their resonant expansions that are: ");
	    				System.out.println(resonances.length);
	    			}
	    		}
	    	}
	    }
	    System.out.println("");
	    System.out.print("There are ");
	    System.out.print(nResonant);
	    System.out.println(" semigroups of order "+order+" at least one resonant decomposition.");
	    System.out.print("In total, there are ");
	    System.out.print( nTotalResonances );
	    System.out.println(" different resonant decompositions.");
	    System.out.print("And ");
	    System.out.print(nSemisimple);
	    System.out.println(" of them leads to a semisimple algebra.");
	    System.out.print("There are ");
	    System.out.print( nAllSemisimple ) ;
	    System.out.println( " semigroups having more than one resonance and");
	    System.out.println( "leading to semisimple algebras with all those resonances.");
	}
}
