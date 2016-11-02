package examples;

public class II_Example_diagonalization {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double [][] m = {{1,0,0},{0,2,0},{0,0,3}};
		Jama.Matrix matriu = new Jama.Matrix(m) ;
		Jama.EigenvalueDecomposition dec = new Jama.EigenvalueDecomposition(matriu);
		System.out.println(dec.getRealEigenvalues()[0]);
		System.out.println(dec.getRealEigenvalues()[1]);
		System.out.println(dec.getRealEigenvalues()[2]);
		matriu = dec.getV();
		matriu.print(0, 0);
	}

}
