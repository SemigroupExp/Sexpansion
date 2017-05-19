package examples;

import java.util.*;
import sexpansion.Semigroup;
import sexpansion.SetS;

public class III_c_Solutions_with_Subsem_SE3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Semigroup[] lista = Semigroup.loadFromFile("src/data/");
		int orden = 6, idSemigrupo, nTotal_Sem_with_subs = 0;
		Set<Integer> nSem_with_subs = new HashSet<Integer>();
		Integer[] IDList = { 7916, 9915, 14651 };
		Set<Integer> SolutionsID = new HashSet<Integer>(Arrays.asList(IDList));
		for (idSemigrupo = 0; idSemigrupo < lista.length; ++idSemigrupo) {
			// La variable IdSemigrupo corre sobre todos los semigrupos de orden
			// aaaa6
			if (SolutionsID.contains(lista[idSemigrupo].ID)) {
				// Solo trabajamos sobre los semigrupos cuyo ID se incluyó en la
				// lista. 7916, 9915, 14651
				SetS gens = new SetS(orden);
				// gens es un Set con elementos 1,2,...n, en este caso n = 6
				// {1,2,3,4,5,6}
				int ordenSubsemigrupos = 5;
				// Una lista de Sets con todos los subconjuntos posibles de 5
				// elementos del set de 6 elementos gens
				SetS[] subconjuntos = gens.subSets(ordenSubsemigrupos);
				int i = 0;
				// In the following 'if', the commutative condition is also
				// included in order to discard non commutative solutions.
				// En este if, es redudante verificar el orden del semigrupo de
				// entrada ya que en este punto solo están los que pertenecen a
				// la lista (ID)
				// Además no me parece necesario verificar la conmutatividad ya que solo consideramos los de la lista de entrada...
				if (lista[idSemigrupo].order == orden && lista[idSemigrupo].isCommutative()) {
					// Exporta los datos de la tabla de multiplicación del
					// semigrupo de orden mayor a un arreglo bidimensional de
					// enteros.
					int[][] matriu = lista[idSemigrupo].data;
					Semigroup group = new Semigroup(ordenSubsemigrupos);
					int fila, columna, j, nSemigrupo, l;
					fila = 0;
					columna = 0;
					int nGeneradores = 0;
					// Se construye un semigrupo vacío del orden de los
					// semigrupos menores
					Semigroup group2 = new Semigroup(ordenSubsemigrupos);
					for (nSemigrupo = 0; nSemigrupo < subconjuntos.length; ++nSemigrupo) {
						nGeneradores = 0;
						// int[] aux = (int []){{1}};
						SetS generadores = new SetS(0);
						int[][] matriz = group.data;
						int[][] matriz2 = new Semigroup(ordenSubsemigrupos).data;
						// subconjuntos[nSemigrupo].show();
						// System.out.println("Printing group");
						// group.show();
						boolean candidatoSemigrupo = true;
						// First, we study if the result that has been return
						// 1. has at most the number of elements equal to the
						// order
						// 2. has some element of the matrix which is not equal
						for (i = 0; i < ordenSubsemigrupos; ++i) {
							for (j = 0; j < ordenSubsemigrupos; ++j) {
								fila = subconjuntos[nSemigrupo].elementAt(i);
								columna = subconjuntos[nSemigrupo].elementAt(j);
								// for (l = 0; l < ordenSubsemigrupos; ++l) {
								// lista[idSemigrupo].show();
								// System.out.println("**************");
								// subconjuntos[nSemigrupo].show();
								// System.out.println(matriz[i][j]);
								// System.out.println(matriu[fila-1][columna-1]);
								// System.out.println("fila - 1, j");
								// System.out.println(fila - 1 +", " +i);
								// System.out.println("columna - 1, j");
								// System.out.println(columna - 1 +", " +j);
								matriz[i][j] = matriu[fila - 1][columna - 1];
								// }
							}
						}
						// we check the conditions
						generadores = new SetS(0);
						generadores.addElement(matriz[0][0]);
						for (i = 0; i < ordenSubsemigrupos; ++i) {
							for (j = 0; j < ordenSubsemigrupos; ++j) {
								// fila = subconjuntos[nSemigrupo].elementAt(i);
								// columna =
								// subconjuntos[nSemigrupo].elementAt(j);
								// for (l = 0; l < ordenSubsemigrupos; ++l) {
								// System.out.println(matriz[i][j]);
								if (generadores.find(matriz[i][j]) == false) {
									int elemento = matriz[i][j];
									generadores = generadores.addElement(elemento);
									// System.out.println("Adding generator");
									// generadores.show();
									// }
								}
							}
						}
						nGeneradores = generadores.nElements;
						for (int z = 0; z < nGeneradores; ++z) {
							// System.out.println("Checking closure under
							// multiplication");
							if (subconjuntos[nSemigrupo].find(generadores.elementAt(z)) == false) {
								// System.out.println("It is not a semigroup");
								candidatoSemigrupo = false;
							}
						}

						// System.out.println("Semigroups with ");
						// System.out.println(nGeneradores);
						// System.out.println( " elements");
						for (i = 0; i < ordenSubsemigrupos; ++i) {
							for (j = 0; j < ordenSubsemigrupos; ++j) {
								fila = subconjuntos[nSemigrupo].elementAt(i);
								columna = subconjuntos[nSemigrupo].elementAt(j);
								matriz[i][j] = matriu[fila - 1][columna - 1];
								for (l = 0; l < ordenSubsemigrupos; ++l) {
									if (matriz[i][j] == subconjuntos[nSemigrupo].elementAt(l)) {
										matriz2[i][j] = l + 1;
										break;
									}
								}
							}
						}
						if (nGeneradores <= ordenSubsemigrupos && candidatoSemigrupo) {
							group = new Semigroup(matriz);
							group2 = new Semigroup(matriz2);
							// System.out.println("Semigroup:");
							// group.show();
							// System.out.println(group.Elements());
							if (group2.isAssociative() && group2.isCommutative()) {
								// System.out.println("Is associative");
								// group.show();
								// group2.show();

								int[][] mgrupoDado = { { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 2 }, { 1, 1, 1, 2, 3 },
										{ 1, 1, 2, 3, 4 }, { 1, 2, 3, 4, 5 } };
								Semigroup grupoDado = new Semigroup(mgrupoDado);
								// System.out.println("Given semigroup");
								// grupoDado.show();
								boolean isomorfo = group2.isotest(grupoDado)[0];
								// System.out.println(isomorphic);
								if (isomorfo) {
									System.out.println(
											"A subsemigroup which is isomorphic to the given one has been found");
									System.out.println("The semigroup which contains it as a subsemigroup is S#"
											+ lista[idSemigrupo].ID);
									lista[idSemigrupo].show();
									System.out.println("------------------");
									grupoDado.show();
									System.out.println("*****************");
									System.out.println("The elements that has been used are:");
									subconjuntos[nSemigrupo].show();
									System.out.println("Orignal subsemigroup:");
									group.show();
									System.out.println("Subsemigroup with renamed elements:");
									group2.show();
									System.out.println("********************");
									nTotal_Sem_with_subs = nTotal_Sem_with_subs + 1;
									nSem_with_subs.add(lista[idSemigrupo].ID);
								}
							}
						}
					}
				}
			} // Del if que selecciona solo las soluciones
		} // Del For principal IDList
		System.out.println(
				"From the set of " + IDList.length + " solutions of the input, the number of semigroups containing");
		System.out.println("the given semigroup as subsemigroup is: " + nSem_with_subs.size());
		System.out.println("The list with their ID is given by: ");
		System.out.println(nSem_with_subs.toString());
		System.out.println("");
		System.out.println("Total number of times that the given semigroup is subsemigroup:" + nTotal_Sem_with_subs);
	}
}
