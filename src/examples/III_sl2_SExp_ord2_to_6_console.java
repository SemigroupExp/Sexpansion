package examples;
import Jama.Matrix;
import sexpansion.Semigroup;
import sexpansion.StructureConstantSet;
import sexpansion.StructureConstantSetExpanded;


public class III_sl2_SExp_ord2_to_6_console {

	/**
	 * @param args
	 */
	// To change the order modify the value of the int order (only for values between 2 and 6)
	private static int order=2;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Matrix metric ;
		
		Semigroup [] listSemigroups = Semigroup.loadFromFile("src/data/") ;
		
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
		System.out.println("In what follows we expand sl2 with all the abelian semigroups of order "+order);
		System.out.println("and identify the ones that leads to a semisimple algebra");
		System.out.println("**********");
		Semigroup semigroup ;
	    StructureConstantSetExpanded expandedAlgebra ;
	    int nCommutativos = 0 ;
	    int nSemisimples = 0 ;
	    int i ;
	    for ( i = 0 ; i < listSemigroups.length ; ++i) {
	    	semigroup = listSemigroups[i] ;
	    	if ( semigroup.order == order  && semigroup.isCommutative()) {
	    		nCommutativos = nCommutativos + 1 ;
	    		expandedAlgebra  = semigroup.getExpandedStructureConstant( sl2 );
	    		metric = expandedAlgebra.cartanKillingMetric() ;
	    		if (  metric.det() !=  0 ) {
	    			nSemisimples = nSemisimples + 1 ;
	    			System.out.println("A semisimple algebra has been found, expanding with the semigroup #"+semigroup.ID);
	    			semigroup.show();
	    			//expandedAlgebra.show();
	    			System.out.println("The metric of the expanded algebra is:");
	    			metric.print(2, 2);
	    			System.out.print("whose determinant is: ");
	    			System.out.println(metric.det());
	    			System.out.println("**********");
	    		}
	    	}
	    }
	    System.out.print("There are ");
	    System.out.print(nCommutativos);
	    System.out.println(" commutative semigroups of order "+order);
	    System.out.print("And ");
	    System.out.print(nSemisimples);
	    System.out.println(" expansions that give a semisimple algebra");
	}

}
