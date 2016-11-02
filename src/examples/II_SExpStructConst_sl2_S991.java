package examples;
import Jama.Matrix;
import sexpansion.Semigroup;
import sexpansion.SetS;
import sexpansion.StructureConstantSet;
import sexpansion.StructureConstantSetExpanded;
import sexpansion.StructureConstantSetExpandedReduced;
import sexpansion.StructureConstantSetExpandedResonant;
import sexpansion.StructureConstantSetExpandedResonantReduced;


public class II_SExpStructConst_sl2_S991 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("PRELIMINAR OBSERVATIONS:");
		System.out.println("");
		System.out.println("This is the output of the example program II_SExpStructConst_sl2_S991.java. ");
		System.out.println("It performs expansions of the sl(2) algebra,");
		System.out.println("[X_{1},X_{2}]= -2 X_{3} ,");
		System.out.println("[X_{1},X_{3}]=  2 X_{2} ,");
		System.out.println("[X_{2},X_{3}]=  2 X_{1} ,");
		System.out.println("with the semigroup S_{5}^{991}, whose multiplication table is given by:");
		System.out.println("1 1 1 1 1");
		System.out.println("1 2 3 4 5");
		System.out.println("1 3 2 5 4");
		System.out.println("1 4 5 3 2");
		System.out.println("1 5 4 2 3");
		System.out.println("The resonance that will be considered is:");
		System.out.println("S0 = {1,2,3}, S1 = {1,4,5},");
		System.out.println("and the zero element is: 1.");
		System.out.println("");
		System.out.println("It gives the structure constants C_{(i,a)(j,b)}^{(k,c)}} of:");
		System.out.println("1) Expanded algebra");
		System.out.println("2) Resonant subalgebra");
		System.out.println("3) Reduced algebra");
		System.out.println("4) Reduction of the resonant subalgebra");
		System.out.println("");
		System.out.println("NOTATION:");
		System.out.println("");
		System.out.println("Using i,j,k=1,...,n and a,b,c=1,...,m, the structure constants of the expanded algebra will be given as follows:");
		System.out.println("We first give m matrices C_{(1,a)(j,b)}^{(k,c)}");
		System.out.println("C_{(1,1)(j,b)}^{(k,c)}, C_{(1,2)(j,b)}^{(k,c)}, ..., C_{(1,m)(j,b)}^{(k,c)}}");
		System.out.println("Then the m matrices C_{(2,a)(j,b)}^{(k,c)}}");
		System.out.println("C_{(2,1)(j,b)}^{(k,c)}, C_{(2,2)(j,b)}^{(k,c)}, ..., C_{(2,m)(j,b)}^{(k,c)}}");
		System.out.println("and thus we continue until giving the m boxes C_{(n,a)(j,b)}^{(k,c)}}");
		System.out.println("C_{(n,1)(j,b)}^{(k,c)}, C_{(n,2)(j,b)}^{(k,c)}, ..., C_{(n,m)(j,b)}^{(k,c)}");
		System.out.println("");
		System.out.println("In a similar way we give the selectors of the reduced algebra, resonant subalgebra and reduction of");
		System.out.println("the resonant subalgebra, omitting the rows and comumns (i,a) that are not in the corresponding algebra.");
		System.out.println("The range where the indices (i,a) are running is indicated for each case 1-4.");
		System.out.println("");
		System.out.println("Finally, we remind that the method 'setStructureConstant()' reads the non-vanishing structure constants C_{ij}^{k} ");
		System.out.println("in such a way that i,j,k=0,1...,n-1. They are introduced as follows:");
		System.out.println("");
		System.out.println("name.setStructureConstant( i , j , k , C_{ij}^{k})");
		System.out.println("");
		System.out.println("Similarly a,b,c=0,1,...,m-1 in the functions C_{(i,a)(j,b)}^{(k,c)}.");
		System.out.println("However, the outputs will be given in such a way that i,j,k=1,...,n and a,b,c=1,...,m.");
		System.out.println("");
		System.out.println("-------------------");
		System.out.println("");
		
		
		Matrix metric ;		
		System.out.println("We introduce the structure constants of sl2.");
		System.out.println("Remind that if a non vanishing structure constant C_{ij}^{k} has the ");
		System.out.println("value V, then we introduce it as: name.setStructureConstant( i-1 , j-1 , k-1 , V )");
		StructureConstantSet sl2 = new StructureConstantSet(3) ;
		sl2.setStructureConstant(0, 1, 2, -2) ;
		sl2.setStructureConstant(0, 2, 1, 2) ;
		sl2.setStructureConstant(1, 2, 0, 2) ;
		metric = sl2.cartanKillingMetric() ;
		System.out.println("");
		System.out.println("Show its Killing-Cartan metric");
		metric.print(2, 2) ;
		System.out.println("whose determinant is:");
		System.out.println(metric.det());
		// Then, we load the semigroup S_{5}^{991} and its resonant decomposition
		int [][] mS991 = {{1,1,1,1,1},{1,2,3,4,5},{1,3,2,5,4},{1,4,5,3,2},{1,5,4,2,3}};
	    Semigroup S991 = new Semigroup(mS991) ;
	    int[] mS0 = {1,2,3} ;
	    SetS S0 = new SetS(mS0) ;
	    int[] mS1 = {1,4,5} ;
	    SetS S1 = new SetS(mS1) ;
	    int[] mV0 = {1};
	    SetS V0 = new SetS(mV0);
	    int[] mV1 = {2,3};
	    SetS V1 = new SetS(mV1);
	    System.out.println("");
		System.out.println("-------------------");
			    
		//Now we will calculate the structure constants of

	    // 1) the expanded algebra
	    StructureConstantSetExpanded Gexp = S991.getExpandedStructureConstant( sl2 );
	    Gexp.showPretty();
		System.out.println("");
		System.out.println("-------------------");
		System.out.println("");
	    
		// 2) the resonant subalgebra
	    StructureConstantSetExpandedResonant ResGexp = new StructureConstantSetExpandedResonant( Gexp.data , S0 , S1 , V0 , V1 ) ;
	    ResGexp.showPretty();
		System.out.println("");
		System.out.println("-------------------");
		System.out.println("");
	    
	    // 3) the reduced algebra showCommutRel 
	    StructureConstantSetExpandedReduced RedGexp = new StructureConstantSetExpandedReduced( S991.getExpandedStructureConstant(sl2).data , 1);
	    RedGexp.showPretty();
	    System.out.println("");
	    System.out.println("-------------------");
		System.out.println("");
	    
	    // 4) the reduction of the resonant subalgebra  
	    StructureConstantSetExpandedResonantReduced RedResGexp = new StructureConstantSetExpandedResonantReduced( ResGexp , 1 ) ;
	    RedResGexp.showPretty();
	    System.out.println("");
	    System.out.println("-------------------");
	}
}
