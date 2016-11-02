package examples;
import Jama.Matrix;
import sexpansion.Selector;
import sexpansion.Semigroup;
import sexpansion.StructureConstantSet;
import sexpansion.StructureConstantSetExpanded;


public class II_SExp_CheckingByHand {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Matrix metrica ;
			
		System.out.println("We introduce the structure constants of sl2");
		StructureConstantSet sl2p = new StructureConstantSet(3) ;
		sl2p.setStructureConstant(0, 1, 1, 2) ;
		sl2p.setStructureConstant(0, 2, 2, -2) ;
		sl2p.setStructureConstant(1, 2, 0, 1) ;
		metrica = sl2p.cartanKillingMetric() ;
		System.out.println("We show its Killing-Cartan metric");
		metrica.print(2, 2) ;
		System.out.println("And its determinant:");
		System.out.println(metrica.det());
		System.out.println("We load a semigroup");
		int [][] matriz = {{1,2,3,4},{2,3,4,4},{3,4,4,4}, {4,4,4,4}};
	    Semigroup grupo = new Semigroup(matriz) ;
	    System.out.println("We calculate the selectors of the semigroup");
	    Selector s = grupo.getSelector() ;
	    s.show();
	    System.out.println("W with the KC metric of sl2 and the selectors using the method expandedMetric()");
	    metrica = s.expandedMetric(metrica);
	    System.out.println("We show the metric that has been computed:");
	    metrica.print(0, 0);
		    
		    
		    
	    System.out.println("We compute the expanded structure constants in terms of the selectors and the structure constants of sl2");
	    StructureConstantSetExpanded c = grupo.getExpandedStructureConstant( sl2p );
	    System.out.println("We show on the screen the expanded structure constants:");
	    c.show();
		    
	    //We show here some particular structure constants:
	    System.out.println(c.get(0, 0, 0, 1, 0, 1));
	    System.out.println(s.get(0, 0, 0));
	    System.out.println( sl2p.structureConstant(1, 2, 2));
	    System.out.println("We calculate the expanded KC metric with the expanded structure constants");
	    metrica = c.cartanKillingMetric();
	    System.out.println("We show it");
	    metrica.print(0, 0 );
		    
	    System.out.println("Now we introduce by hands the structure constants of the expansion of sl2");
		StructureConstantSet expandedsl2 = new StructureConstantSet(12);
		    
	    expandedsl2.setStructureConstant(0, 4, 4, 2) ;
	    expandedsl2.setStructureConstant(0,5,5,2);
	    expandedsl2.setStructureConstant(0,6,6,2);
	    expandedsl2.setStructureConstant(0,7,7,2);
	    expandedsl2.setStructureConstant(0,8,8,-2);
	    expandedsl2.setStructureConstant(0,9,9,-2);
	    expandedsl2.setStructureConstant(0,10,10,-2);
	    expandedsl2.setStructureConstant(0,11,11,-2);
	    
	    expandedsl2.setStructureConstant(1,4,5,2);
	    expandedsl2.setStructureConstant(1,5,6,2);
	    expandedsl2.setStructureConstant(1,6,7,2);
	    expandedsl2.setStructureConstant(1,7,7,2);
	    expandedsl2.setStructureConstant(1,8,9,-2);
	    expandedsl2.setStructureConstant(1,9,10,-2);
	    expandedsl2.setStructureConstant(1,10,11,-2);
	    expandedsl2.setStructureConstant(1,11,11,-2);
	    
	    expandedsl2.setStructureConstant(2,4,6,2);
	    expandedsl2.setStructureConstant(2,5,7,2);
	    expandedsl2.setStructureConstant(2,6,7,2);
	    expandedsl2.setStructureConstant(2,7,7,2);
	    expandedsl2.setStructureConstant(2,8,10,-2);
	    expandedsl2.setStructureConstant(2,9,11,-2);
	    expandedsl2.setStructureConstant(2,10,11,-2);
	    expandedsl2.setStructureConstant(2,11,11,-2);
	    
	    expandedsl2.setStructureConstant(3,4,7,2);
	    expandedsl2.setStructureConstant(3,5,7,2);
	    expandedsl2.setStructureConstant(3,6,7,2);
	    expandedsl2.setStructureConstant(3,7,7,2);
	    expandedsl2.setStructureConstant(3,8,11,-2);
	    expandedsl2.setStructureConstant(3,9,11,-2);
        expandedsl2.setStructureConstant(3,10,11,-2);
	    expandedsl2.setStructureConstant(3,11,11,-2);
	    
	    expandedsl2.setStructureConstant(4,8,0,1);
	    expandedsl2.setStructureConstant(4,9,1,1);
	    expandedsl2.setStructureConstant(4,10,2,1);
	    expandedsl2.setStructureConstant(4,11,3,1);
	    
	    expandedsl2.setStructureConstant(5,8,1,1);
	    expandedsl2.setStructureConstant(5,9,2,1);
	    expandedsl2.setStructureConstant(5,10,3,1);
	    expandedsl2.setStructureConstant(5,11,3,1);
	    
	    expandedsl2.setStructureConstant(6,8,2,1);
	    expandedsl2.setStructureConstant(6,9,3,1);
	    expandedsl2.setStructureConstant(6,10,3,1);
	    expandedsl2.setStructureConstant(6,11,3,1);
	    
	    expandedsl2.setStructureConstant(7,8,3,1);
	    expandedsl2.setStructureConstant(7,9,3,1);
	    expandedsl2.setStructureConstant(7,10,3,1);
	    expandedsl2.setStructureConstant(7,11,3,1);
	    
	    System.out.println("We compute the KC metric of corresponding to the expanded algebra calculated by hands");
	    metrica = expandedsl2.cartanKillingMetric() ;
	    System.out.println("We show it");
	    metrica.print(0, 0);
	    Matrix metriqueta = s.selectorMetric();
	    metriqueta.print(2, 2);
	}
}
