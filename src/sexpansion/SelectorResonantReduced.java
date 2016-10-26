package sexpansion;

import Jama.Matrix;


public class SelectorResonantReduced extends SelectorResonant {

	int zero;
	
	public SelectorResonantReduced(Selector s, SetS V0, SetS V1 , int zero) {
		// TODO Auto-generated constructor stub
		super(s,V0,V1);
		this.zero = zero;
		int i,j, k;
		for ( i = 0 ; i < this.order ; ++i) {
			for ( j = 0 ; j < this.order ; ++j) {
				for ( k = 0 ; k < this.order ; ++k ) {
					if ( i == zero - 1 || j == zero -1 || k == zero -1 ){
						data[i][j][k] = 0;
					}
				}
			}
		}
	}
	
	
	public Matrix selectorMetric (){
		Matrix bigMatrix = super.selectorMetric();
		int n = S0.nElements + S1.nElements;
		int n1 = S0.nElements;
		if (S0.find(zero)) {
			n = n -1;
			n1 = n1 - 1 ;
		}
		if ( S1.find(zero)) {
			n = n - 1;
		}
		
		System.out.print("n=");
		System.out.println(n);
		double [][] result = new double[n][n];
		
		int i,j;
		int l= 0,m= 0;
		for ( i = 0 ; i < S0.nElements ; ++i) {
			m = 0 ;
			if ( S0.elementAt(i) != zero) {
				for( j = 0 ; j < S0.nElements ; ++j ){
					if ( S0.elementAt(j) != zero ) {
					result[l][m] = bigMatrix.get(i, j);
					m = m + 1;
					}
				}
				l = l +1;
			}
		}
		l = n1 ;
		m = n1 ;
		for ( i = 0 ; i < S1.nElements ; ++i) {
			m = n1;
			if ( S1.elementAt(i) != zero ) {
				for( j = 0 ; j < S1.nElements ; ++j ){
					if ( S1.elementAt(j) != zero ) {
					result[l][m] = bigMatrix.get(i + S0.nElements, j + S0.nElements);
					m = m + 1;
					}
				}
				l = l +1;
			}
		}
		
		return new Matrix(result);
	}
	
	void NMatrix () {
		System.out.println("This is the resonant decomposition:");
		System.out.print("S0: ");
		S0.show();
		System.out.print("S1: ");
		S1.show();
		int n0 = S0.nElements;
		int n1 = S1.nElements;
		Matrix m0 = new Matrix(n0,n0);
		Matrix m1 = new Matrix(n1,n1);
		//First we compute m0
		int a0,b0,r0,g0,r1,g1, i,j,k,l,a1,b1;
		float suma = 0;
		for ( i =  0 ; i < n0 ; ++i) {
			for ( j = 0 ; j < n0; ++j) {
				a0 = S0.elementAt(i)-1;
				b0 = S0.elementAt(j)-1;
				suma = 0 ;
				for ( k = 0 ; k < n0 ; ++k) {
					r0 = S0.elementAt(k)-1;
					for ( l = 0 ; l < n0 ; ++l) {
						g0 = S0.elementAt(l)-1;
						suma = suma + data[a0][g0][r0] * data[b0][r0][g0] ;

					}
				}
				for ( k = 0 ; k < n1 ; ++k) {
					r1 = S1.elementAt(k)-1;
					for ( l =  0 ; l < n1 ; ++l){
						g1 = S1.elementAt(l)-1;
						suma = suma + data[a0][g1][r1] * data[b0][r1][g1] ;
					}
				}
				m0.set(i, j, suma);
			}
		}
		for ( i =  0 ; i < n1 ; ++i) {
			for ( j = 0 ; j < n1; ++j) {
				a1 = S1.elementAt(i)-1;
				b1 = S1.elementAt(j)-1;
				suma = 0 ;
				for ( k = 0 ; k < n1 ; ++k) {
					r1 = S1.elementAt(k)-1;
					for ( l = 0 ; l < n0 ; ++l) {
						g0 = S0.elementAt(l)-1;
						suma = suma + data[a1][g0][r1] * data[b1][r1][g0] ;

					}
				}
				for ( k = 0 ; k < n0 ; ++k) {
					r0 = S0.elementAt(k)-1;
					for ( l =  0 ; l < n1 ; ++l){
						g1 = S1.elementAt(l)-1;
						suma = suma + data[a1][g1][r0] * data[b1][r0][g1] ;
					}
				}
				m1.set(i, j, suma);
			}
		}
		System.out.println("g(S0):");
		m0.print(2, 2);
		System.out.println("g(S1):");
		m1.print(2, 2);
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Matrix metric ;
		//SelectorResonant rs;	
		//	Semigroup [] listOfSemigroups = Semigroup.loadFromFile() ;
			
		System.out.println("We introduce the structure constants of sl2.");
			StructureConstantSet sl2 = new StructureConstantSet(3) ;
			sl2.setStructureConstant(0, 1, 1, 2) ;
			sl2.setStructureConstant(0, 2, 2, -2) ;
			sl2.setStructureConstant(1, 2, 0, 1) ;
			metric = sl2.cartanKillingMetric() ;
			System.out.println("We show its Killing-Cartan metric");
			metric.print(2, 2) ;
			System.out.println("and its determinant:");
			System.out.println(metric.det());
			System.out.println("***********");
			System.out.println("Semigroup #12:");
			int [][] s12 = {{1,1,1},{1,2,3},{1,3,2}};
			Semigroup group = new Semigroup(s12) ;
			int []mS0_12 = {1,2};
		    int []mS1_12 = {1,3};
		    SetS S0_12 = new SetS(mS0_12);
		    SetS S1_12 = new SetS(mS1_12);
		    SelectorResonantReduced rs = new SelectorResonantReduced( group.getSelector(), S0_12, S1_12, 1 );
		    //rs.show();
		    Matrix resultat = rs.selectorMetric();
		    System.out.println("The metric of the resonant reduced selector is:");
		    resultat.print(2, 2);
		    System.out.println("whose determinant is given by");
		    System.out.println(resultat.det());
		    rs.NMatrix();
		    System.out.println("***********");
		    System.out.println("Semigroup #88");
			int [][] s88 = {{1,1,1,1},{1,2,2,2},{1,2,3,4},{1,2,4,3}};
			group = new Semigroup(s88);
			int [] mS0_88 = {1,2,3};
		    int [] mS1_88 = {1,2,4};
		    SetS S0_88 = new SetS(mS0_88);
		    SetS S1_88 = new SetS(mS1_88);
		    rs = new SelectorResonantReduced( group.getSelector(), S0_88, S1_88, 1 );
		    //rs.show();
		    resultat = rs.selectorMetric();
		    System.out.println("The metric of the resonant reduced selector is:");
		    resultat.print(2, 2);
		    System.out.println("whose determinant is given by");
		    System.out.println(resultat.det());
		    rs.NMatrix();
		    System.out.println("***********");
			System.out.println("Semigroup #770");
			int [][] matrix = {{1,1,1,1,1},{1,2,1,1,5},{1,1,3,4,1}, {1,1,4,3,1},{1,5,1,1,2}};
		    group = new Semigroup(matrix) ;
		    int []mS0_770 = {1,2,3};
		    int []mS1_770 = {1,4,5};
		    SetS S0_770 = new SetS(mS0_770);
		    SetS S1_770 = new SetS(mS1_770);
		    rs = new SelectorResonantReduced( group.getSelector(), S0_770, S1_770, 1 );
		    //rs.show();
		    resultat = rs.selectorMetric();
		    System.out.println("The metric of the resonant reduced selector is:");
		    resultat.print(2, 2);
		    System.out.println("whose determinant is given by");
		    System.out.println(resultat.det());
		    rs.NMatrix();
		    System.out.println("***********");
		    System.out.println("Semigroup #779");
		    int [][]matrix2 = {{1,1,1,1,1},{1,2,1,2,2},{1,1,3,3,3}, {1,2,3,4,5},{1,2,3,4,5}};
		    group = new Semigroup(matrix2);
		    int[] mS0_779 = {1,2,3,4};
		    int[] mS1_779 = {1,2,3,5};
		    SetS S0_779 = new SetS(mS0_779);
		    SetS S1_779 = new SetS(mS1_779);
		    rs = new SelectorResonantReduced( group.getSelector(), S0_779, S1_779, 1);
		    rs.NMatrix();
		    System.out.println("***********");
		    System.out.println("Semigroup #922");
		    int [][]matrix3 = {{1,1,1,1,1},{1,2,2,2,2},{1,2,3,3,3}, {1,2,3,4,5},{1,2,3,4,5}};
		    group = new Semigroup(matrix3);
		    int[] mS0_922 = {1,2,3,4};
		    int[] mS1_922 = {1,2,3,5};
		    SetS S0_922 = new SetS(mS0_922);
		    SetS S1_922 = new SetS(mS1_922);
		    rs = new SelectorResonantReduced( group.getSelector(), S0_922, S1_922, 1);
		    rs.NMatrix();
		    System.out.println("***********");
		    System.out.println("Semigroup #968");
		    int [][]matrix4 = {{1,1,1,1,1},{1,2,2,4,4},{1,2,3,4,5}, {1,4,4,2,2},{1,4,5,2,3}};
		    group = new Semigroup(matrix4);
		    int[] mS0_968 = {1,2,3};
		    int[] mS1_968 = {1,4,5};
		    SetS S0_968 = new SetS(mS0_968);
		    SetS S1_968 = new SetS(mS1_968);
		    rs = new SelectorResonantReduced( group.getSelector(), S0_968, S1_968,1 );
		    rs.NMatrix();
		    System.out.println("***********");
		    System.out.println("Semigroup #990");
		    int [][]matrix5 = {{1,1,1,1,1},{1,2,3,4,5},{1,3,2,5,4}, {1,4,5,2,3},{1,5,4,3,2}};
		    group = new Semigroup(matrix5);
		    int[] mS0_990 = {1,2,5};
		    int[] mS1_990 = {1,3,4};
		    SetS S0_990 = new SetS(mS0_990);
		    SetS S1_990 = new SetS(mS1_990);
		    rs = new SelectorResonantReduced( group.getSelector(), S0_990, S1_990, 1);
		    rs.NMatrix();
		    System.out.println("***********");
		    System.out.println("Semigroup #991");
		    int [][]matrix6 = {{1,1,1,1,1},{1,2,3,4,5},{1,3,2,5,4}, {1,4,5,3,2},{1,5,4,2,3}};
		    group = new Semigroup(matrix6);
		    int[] mS0_991 = {1,2,3};
		    int[] mS1_991 = {1,4,5};
		    SetS S0_991 = new SetS(mS0_991);
		    SetS S1_991 = new SetS(mS1_991);
		    rs = new SelectorResonantReduced( group.getSelector(), S0_991, S1_991,1);
		    rs.NMatrix();
	}

}
