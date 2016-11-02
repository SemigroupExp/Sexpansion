package examples;
import Jama.Matrix;
import sexpansion.Semigroup;
import sexpansion.StructureConstantSet;
import sexpansion.StructureConstantSetExpanded;
import sexpansion.StructureConstantSetExpandedReduced;


public class III_sl2_SExp_Red_ord2_to_6_console {

	/**
	 * @param args
	 */
	// To change the order modify the value of the int order (only for values between 2 and 6)
	private static int order=2;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Semigroup [] listSemigroups = Semigroup.loadFromFile("src/datos/") ;
		
		Matrix metric ;
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
		System.out.println("zero element and identify the ones that leads to a semisimple algebra.");
		System.out.println("**********");
		Semigroup semigroup;
		StructureConstantSetExpanded expandedAlgebra;
	    StructureConstantSetExpandedReduced RedAlgebra;
		int i , zero, nZero = 0 , nSemisimple = 0;
	    for ( i = 0 ; i < listSemigroups.length ; ++i) {
	    	semigroup = listSemigroups[i] ;
	    	if ( semigroup.order == order && semigroup.isCommutative() ) {
	    		zero = semigroup.findZero() ;
	    		if ( zero != -1 ) {
	    			nZero = nZero + 1 ;
	    			expandedAlgebra  = semigroup.getExpandedStructureConstant( sl2 );
	    			RedAlgebra = new StructureConstantSetExpandedReduced(expandedAlgebra.data, zero );
	    			metric = RedAlgebra.cartanKillingMetricPretty();
	    			if (  metric.det() !=  0 ) {
	    				nSemisimple = nSemisimple + 1 ;
	    				System.out.println("A semisimple algebra has been found, expanding with the semigroup #"+semigroup.ID);
    					semigroup.show();
    					System.out.println("whose zero element is: "+zero+"."); 
    					System.out.println("The metric of the reduced algebra is:" ) ;
    					metric.print(2, 2) ;
    					System.out.println("and its determinant");
    					System.out.println(metric.det());
    					System.out.println("**********");
	    			}
	    		}
	    	}
	    }
	    System.out.println("");
	    System.out.print("There are ");
	    System.out.print(nZero);
	    System.out.println(" semigroups of order "+order+" with zero element.");
	    System.out.print("And ");
	    System.out.print(nSemisimple);
	    System.out.println(" of them leads to a semisimple algebra.");
	}

}
