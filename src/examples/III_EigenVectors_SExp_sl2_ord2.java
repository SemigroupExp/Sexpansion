package examples;
import Jama.Matrix;
import sexpansion.Semigroup;
import sexpansion.StructureConstantSet;
import sexpansion.StructureConstantSetExpanded;

public class III_EigenVectors_SExp_sl2_ord2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Matrix metric ;
				
				Semigroup [] listOfSemigroups = Semigroup.loadFromFile("src/data/") ;
				
				System.out.println("We introduce the structure constants of sl2");
				StructureConstantSet sl2 = new StructureConstantSet(3) ;
				sl2.setStructureConstant(0, 1, 1, 2) ;
				sl2.setStructureConstant(0, 2, 2, -2) ;
				sl2.setStructureConstant(1, 2, 0, 1) ;
				metric = sl2.cartanKillingMetric() ;
				System.out.println("We show its Killing-Cartan metric");
				metric.print(2, 2) ;
				System.out.println("and its determinant:");
				System.out.println(metric.det());
				StructureConstantSetExpanded expandedAlgebra ;
			    int i ;
			    Semigroup semigroup;
			    for ( i = 0 ; i < listOfSemigroups.length ; ++i) {
			    	semigroup = listOfSemigroups[i] ;
			    	if ( semigroup.order == 2 && semigroup.isCommutative()) {
			    		//System.out.print("Expanding with the semigroup #");
			    		//System.out.println(semigroup.ID);
			    		expandedAlgebra  = semigroup.getExpandedStructureConstant( sl2 );
			    		metric = expandedAlgebra.cartanKillingMetric() ;
			    		if (  metric.det() !=  0 ) {
			    			System.out.println("*******************************");
			    			System.out.println("A semisimple algebra has been found, expanding with the semigroup #"+semigroup.ID);
			    			semigroup.show();
			    			System.out.println("The metric is:");
			    			metric.print(2, 2);
			    			System.out.print("And its determinant: ");
			    			System.out.println(metric.det());
			    			double []valores = expandedAlgebra.eigenValues();
			    			int v;
			    			System.out.println("The eigenvalues are:");
			    			for ( v = 0 ; v < valores.length ; ++v) {
			    				System.out.println(valores[v]);
			    			}
			    			System.out.println("The matriz of eigenvectors is:");
			    			expandedAlgebra.eigenVectors().print(2, 2);
			    		}
			    	}
			    }
	}

}
