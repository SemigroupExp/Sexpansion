package examples;
import Jama.Matrix;
import sexpansion.SetS;
import sexpansion.StructureConstantSet;


public class II_AdjointRep_Casimirs_ex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		StructureConstantSet su2 = new StructureConstantSet(3) ;
		StructureConstantSet sl2 = new StructureConstantSet(3) ;
		StructureConstantSet e2 = new StructureConstantSet(3) ;
		Matrix metrica ;
		Matrix [] generators ;
		int i;
		
		su2.setStructureConstant(1, 2, 0, -1) ;
		su2.setStructureConstant(2, 0, 1, -1) ;
		su2.setStructureConstant(0, 1, 2, -1 ) ;
		metrica = su2.cartanKillingMetric() ;
		metrica.print( 2, 2);
		System.out.println(metrica.det()  ) ;
		
		sl2.setStructureConstant(1, 2, 0, 1) ;
		sl2.setStructureConstant(2, 0, 1, -1) ;
		sl2.setStructureConstant(0, 1, 2, 1 ) ;
		metrica = sl2.cartanKillingMetric() ;
		metrica.print(2, 2) ;
		System.out.println(metrica.det()  ) ;

		e2.setStructureConstant(1, 2, 0, -1) ;
		e2.setStructureConstant(2, 0, 1, -1) ;
		metrica = e2.cartanKillingMetric() ;
		metrica.print(2, 2) ;
		System.out.println(metrica.det()  ) ;

		StructureConstantSet sl2p = new StructureConstantSet(3) ;
		sl2p.setStructureConstant(0, 1, 1, 2) ;
		sl2p.setStructureConstant(0, 2, 2, -2) ;
		sl2p.setStructureConstant(1, 2, 0, 1) ;
		metrica = sl2p.cartanKillingMetric() ;
		metrica.print(2, 2) ;
		System.out.println(metrica.det());
		
		
		System.out.println("We look for the adjoint generators of sl2");
		generators = sl2p.adjointGenerators();
		System.out.print("There are ");
		System.out.print(generators.length);
		System.out.println(" generators, that are:");
		for ( i = 0 ; i < generators.length ; ++i ) {
			generators[i].print(2, 2);
		}
		generators = sl2p.semisimpleAdjointGenerators();
		System.out.print("There are " ) ;
		System.out.print( generators.length ) ;
		System.out.println(" semisimple generators");
		generators[0].print(2, 2);
		generators[0].eig().getV().print(2,2);
		System.out.println(generators[0].eig().getRealEigenvalues().length);
		Matrix M = sl2p.casimir();
		System.out.println("The Casimir;");
		M.print(2, 2);
		double [] autoValores = sl2p.casimirEigenvalues();
		System.out.println("If all the following eigenvalues are equal, the el algebra is simple");
		for ( i = 0 ; i < autoValores.length ; ++i)
		{
			System.out.println(autoValores[i]);
		}
		System.out.println(sl2p.generatorsCommute(0, 1));
		System.out.println(sl2p.generatorsCommute(0, 2));
		System.out.println(sl2p.generatorsCommute(1, 2));
		int [] semi = sl2p.semisimpleAdjointGeneratorsReferences() ;
		System.out.println("Semisimple generators");
		for ( i = 0 ; i < semi.length ; ++i) {
			System.out.println(i);
		}
		
		StructureConstantSet algebraDeProva = new StructureConstantSet(4 ) ;
		algebraDeProva.setStructureConstant(0, 2, 0, 1) ; //Let X_1 not commute with x_2 and neither with X_3
		algebraDeProva.setStructureConstant(0, 3, 0, 1) ; 
		int[] mgen = {2,3,4};
		SetS gens = new SetS(mgen);
		System.out.println("Generators commute?");
		System.out.println(algebraDeProva.generatorsSelfCommute(gens));
		int[] mgen2 = {1,2,3} ;
		gens = new SetS(mgen2);
		System.out.println("Generators commute?");
		System.out.println(algebraDeProva.generatorsSelfCommute(gens));
		SetS maxAbelian = algebraDeProva.maximalAbelianSubalgebra();
		System.out.println("The maximal abelian subalgebra is ");
		if ( maxAbelian.nElements > 0 ) {
			maxAbelian.show() ;
		}
		else {
			System.out.println("It doesn't exist");
		}
	}	
}
