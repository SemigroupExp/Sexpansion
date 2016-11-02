package examples;
import sexpansion.Semigroup;

public class II_findzero_console_ord4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Semigroup[] list = Semigroup.loadFromFile("src/datos/");
		Semigroup s ;
		int elementoCero ;
		int i,j;
		j = 0;
		System.out.println("This is the output of the program II_findzero_console_ord4.java, which gives the list ");
		System.out.println("of semigroups of order 4 with zero element.");
		System.out.println("The number of semigroups with zero element is given at the end of the file.");
		System.out.println("************************************");
		for ( i = 0 ; i < list.length ; ++i){
			s = list[i];
			elementoCero = list[i].findZero();
				if ( s.order == 4 && elementoCero != -1 && list[i].isCommutative()) {
					++j;
					System.out.println("#" +list[i].ID);
					list[i].show();
					System.out.print("The zero element is ");
					System.out.println(elementoCero);
					System.out.println("************************************");
				}
			}
		System.out.println("Number of semigroups with zero element: " +j);
	}
}