package examples;
import Jama.Matrix;
import sexpansion.Selector;
import sexpansion.Semigroup;
import sexpansion.StructureConstantSet;

public class III_Signature_Sem_ord2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Semigroup [] listaSemigrupos = Semigroup.loadFromFile("src/data/") ;
		
		Matrix metrica ;
		System.out.println("We introduce the structure constants of sl2");
		StructureConstantSet sl2p = new StructureConstantSet(3) ;
		sl2p.setStructureConstant(0, 1, 1, 2) ;
		sl2p.setStructureConstant(0, 2, 2, -2) ;
		sl2p.setStructureConstant(1, 2, 0, 1) ;
		metrica = sl2p.cartanKillingMetric() ;
		Semigroup semigroup ;		
		int i;
		int [] quants = new int[20];
		int [][] eigens = new int[20][3];
		int nPos = 0 ;
		int pos = 0 ;
		Jama.EigenvalueDecomposition eigen = null;
		// 3 pos, 3 zero, 3 neg, 2 pos 1 zero, 2 pos 1 neg, 2 zero 1 pos, 2 zero 1 neg, 2 neg 1 pos, 2 neg 1 zero, 1 pos 1 neg 1 zero.
//		int [] classification = {0, 0, 0 , 0 ,0 , 0 , 0 , 0 , 0 , 0 };
		Matrix eigenvalueMatrix ;
	    for ( i = 0 ; i < listaSemigrupos.length ; ++i) {
	    	semigroup = listaSemigrupos[i] ;
	    	if ( semigroup.order == 2 && semigroup.isCommutative()) {
	    		System.out.print("Expanding with the semigroup #");
	    		System.out.println(semigroup.ID);
	    		
	    		Selector s = semigroup.getSelector();
	    		System.out.println("We have expanded by the semigroup:");
	    	    semigroup.show();
	    	    System.out.println("The metric is:");
	    		metrica.print(2, 2);
	    		System.out.print("and its determinant: ");
	    		System.out.println(metrica.det());
	    		System.out.println("The metric of the selectors is:");
	    		metrica = s.selectorMetric();
	    		metrica.print(2,2);
	    		System.out.println("whose determinant is:");
	    		System.out.println(metrica.det());
	    		eigen = metrica.eig();
	    		eigenvalueMatrix = eigen.getD();
	    		eigenvalueMatrix.print(2, 2);
	    		int [] r = III_Signature_Sem_ord2.classifyEigenvalues(eigenvalueMatrix);
	    		for ( int j = 0 ; j < r.length ; ++j){
	    			System.out.println(r[j]);
	    		}
	    		//If we do not have the element we add it
	    		pos = III_Signature_Sem_ord2.findInList(eigens, r, nPos);
	    		if (  pos == -1 ) {
	    			System.out.println("Adding new possibility");
	    			System.out.println("abc");
	    			eigens[nPos][0] = r[0];
	    			eigens[nPos][1] = r[1];
	    			eigens[nPos][2] = r[2];
	    			quants[nPos] = 1;
	    			nPos++;
	    		}
	    		else {
	    			quants[pos]++;
	    		}
	    		}
	    	}
	    int j = 0 ;
	    for ( j = 0 ; j < nPos ; ++j) {
	    	System.out.println("****");
	    	System.out.print("There are ");
	    	System.out.print(quants[j]);
	    	System.out.println(" with this characteristics:");
	    	System.out.print(eigens[j][0]);
	    	System.out.println(" pos");
	    	System.out.print(eigens[j][1]);
	    	System.out.println(" neg");
	    	System.out.print(eigens[j][2]);
	    	System.out.println(" zero");
	    }
	    
	}
	
	// Positive, negative, zero
	public static int [] classifyEigenvalues ( Matrix m ) {
		int[] result = {0,0,0};
		int i = 0 ;
		for ( i = 0 ; i < m.getColumnDimension() ; ++i) {
			if ( m.get(i, i) > 0 ) {
				result[0] = result[0] + 1;
			}
			else if ( m.get(i, i) < 0 ) {
				result[1]	= result[1]	 + 1 ;
				} else {
					result[2] = result[2] + 1 ;
				}
			}
		
		return result;
		
	}
	
	public static int findInList(int [][] eigens, int[] eigen, int nPos) {
		for ( int i = 0 ; i < nPos ; ++i){
			if ( eigens[i][0] == eigen[0] && eigens[i][1] == eigen[1] && eigens[i][2] == eigen[2] ) {
				return i;
			}
		}
		
		return -1;
	}

}
