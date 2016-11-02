package examples;

import sexpansion.Selector;
import sexpansion.Semigroup;
import sexpansion.StructureConstantSet;

public class II_S_and_G_AdjointRep_ex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
					
		System.out.println("We load a semigroup");
		int [][] mS = {{1,2,3,4},{2,3,4,4},{3,4,4,4}, {4,4,4,4}};
	    Semigroup S = new Semigroup(mS) ;
	    System.out.println("We calculate the selectors of the semigroup");
	    Selector AdjS = S.getSelector() ;
	    AdjS.show();
	    
	    System.out.println("We load a Lie algebra (sl(2) in this case)");
	    StructureConstantSet sl2 = new StructureConstantSet(3) ;
		sl2.setStructureConstant(0, 1, 2, -2);
		sl2.setStructureConstant(0, 2, 1, 2);
		sl2.setStructureConstant(1, 2, 0, 2);
		sl2.show();
		
		
	}
}
