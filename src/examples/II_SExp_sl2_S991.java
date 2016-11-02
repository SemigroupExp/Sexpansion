package examples;
import Jama.Matrix;
import sexpansion.Semigroup;
import sexpansion.SetS;
import sexpansion.StructureConstantSet;
import sexpansion.StructureConstantSetExpanded;
import sexpansion.StructureConstantSetExpandedReduced;
import sexpansion.StructureConstantSetExpandedResonant;
import sexpansion.StructureConstantSetExpandedResonantReduced;


public class II_SExp_sl2_S991 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("PRELIMINAR OBSERVATIONS:");
		System.out.println("");
		System.out.println("This is the output of the example program II_SExp_sl2_S991.java, ");
		System.out.println("which performs expansions of the sl(2) algebra with the ");
		System.out.println("semigroup S_{5}^{991}, whose multiplication table is given by:");
		System.out.println("1 1 1 1 1");
		System.out.println("1 2 3 4 5");
		System.out.println("1 3 2 5 4");
		System.out.println("1 4 5 3 2");
		System.out.println("1 5 4 2 3");
		System.out.println("The resonance is:");
		System.out.println("S0 = {1,2,3}, S1 = {1,4,5} and zero element is 1.");
		System.out.println("");
		System.out.println("It gives the commutation relations of:");
		System.out.println("1) Expanded algebra");
		System.out.println("2) Resonant subalgebra");
		System.out.println("3) Reduced algebra");
		System.out.println("4) Reduction of the resonant subalgebra");
		System.out.println("");
		System.out.println("NOTATION:");
		System.out.println("");
		System.out.println("The original algebra and the expanded one, GxS, are given by");
		System.out.println("[ X_{i} , X_{j} ] = C_{ij}^{k} X_{k} ,    (1)");
		System.out.println("[ X_{i,a} , X_{j,b} ] = C_{(i,a)(j,b)}^{(k,c)} X_{k,c)} ,    (2)");
		System.out.println("where i,j,k=1,...,n and a,b,c=1,...,m.");
		System.out.println("");
		System.out.println("As the first possition of an array [][] is usually [0][0], the method ");
		System.out.println("'setStructureConstant()' reads the non-vanishing structure constants C_{ij}^{k} ");
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
		System.out.println("value V, then we introduce it as:");
		System.out.println("name.setStructureConstant( i-1 , j-1 , k-1 , V )");
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
		System.out.println("");
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
	    //We calculate the structure constants of

	    // 1) the expanded algebra
	    StructureConstantSetExpanded Gexp = S991.getExpandedStructureConstant( sl2 );
	    System.out.println("METHOD: showCommut()");
	    Gexp.showCommut();
		System.out.println("");
		System.out.println("METHOD: showSC()");
		Gexp.showSC();
		System.out.println("");
		System.out.println("METHOD: cartanKillingMetric()");
		System.out.println("The Killing-Cartan Metric of the Expanded algebra is:");
		Matrix metricaGexp = Gexp.cartanKillingMetric();
		metricaGexp.print(0,0);
		double detKC_Gexp = metricaGexp.det();
		System.out.println("The determinant of the Killing-Cartan Metric of the Expanded algebra is:");
		System.out.println(+detKC_Gexp);
		System.out.println("-------------------");
		System.out.println("");
	    
		// 2) the resonant subalgebra
	    StructureConstantSetExpandedResonant ResGexp = new StructureConstantSetExpandedResonant( Gexp.data , S0 , S1 , V0 , V1 ) ;
	    System.out.println("METHOD: showCommutRes()");
	    ResGexp.showCommutRes();
		System.out.println("");
		System.out.println("METHOD: showSCRes()");
		ResGexp.showSCRes();
		System.out.println("");
		System.out.println("METHOD: cartanKillingMetricPretty()");
		System.out.println("The Killing-Cartan Metric of the Resonant subalgebra is:");
		Matrix metricaResGexp = ResGexp.cartanKillingMetricPretty();
		metricaResGexp.print(0,0);
		double detKC_ResGexp = metricaResGexp.det();
		System.out.println("The determinant of the Killing-Cartan Metric of the Resonant subalgebra is:");
		System.out.println(+detKC_ResGexp);
		System.out.println("-------------------");
		System.out.println("");
	    
	    // 3) the reduced algebra showCommutRel 
	    StructureConstantSetExpandedReduced RedGexp = new StructureConstantSetExpandedReduced( S991.getExpandedStructureConstant(sl2).data , 1);
	    System.out.println("METHOD: showCommutRed()");
	    RedGexp.showCommutRed();
	    System.out.println("");
	    System.out.println("METHOD: showSCRed()");
	    RedGexp.showSCRed();
	    System.out.println("");
		System.out.println("METHOD: cartanKillingMetricPretty()");
		System.out.println("The Killing-Cartan Metric of the Reduced algebra is:");
		Matrix metricaRedGexp = RedGexp.cartanKillingMetricPretty();
		metricaRedGexp.print(0,0);
		double detKC_RedGexp = metricaRedGexp.det();
		System.out.println("The determinant of the Killing-Cartan Metric of the Reduced algebra is:");
		System.out.println(+detKC_RedGexp);
		System.out.println("-------------------");
		System.out.println("");
	    
	    // 4) the reduction of the resonant subalgebra  
	    StructureConstantSetExpandedResonantReduced RedResGexp = new StructureConstantSetExpandedResonantReduced( ResGexp , 1 ) ;
	    System.out.println("METHOD: showCommutResRed()");
	    RedResGexp.showCommutResRed();
	    System.out.println("");
	    System.out.println("METHOD: showSCResRed()");
	    RedResGexp.showSCResRed();
	    System.out.println("");
		System.out.println("METHOD: cartanKillingMetricPretty()");
		System.out.println("The Killing-Cartan Metric of the Reduced algebra is:");
		Matrix metricaRedResGexp = RedResGexp.cartanKillingMetricPretty();
		metricaRedResGexp.print(0,0);
		double detKC_RedResGexp = metricaRedResGexp.det();
		System.out.println("The determinant of the Killing-Cartan Metric of the Reduced algebra is:");
		System.out.println(+detKC_RedResGexp);
	    System.out.println("-------------------");
	}
}
