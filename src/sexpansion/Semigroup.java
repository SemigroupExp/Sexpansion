package sexpansion;

import java.io.*;
import java.util.StringTokenizer;

/**
 * A class to represent a discrete semigroup.
 * 
 * @author Felip Nadal, Carlos Inostroza
 * 
 *
 */

public class Semigroup {

	/*
	 * ------------------ Class Variables ------------------
	 */
	public int[][] data;
	public int order; // The order of the semigroup
	public int ID; // This is the number of semigroup for a given order.

	/*
	 * ----------------- Constructors -----------------
	 */

	/**
	 * Constructs a Semigroup from a two dimensional matrix of integer numbers.
	 * The convention here is that, for a semigroup of order n, the elements are
	 * labelled 1,2,3,...,n.
	 * 
	 * @param data
	 *            is a matrix of integer which represents the multiplication
	 *            table of the semigroup.
	 */

	public Semigroup(int[][] data) {
		order = data.length;
		this.data = new int[order][order];
		int i = 0;
		int j = 0;
		for (i = 0; i < order; ++i) {
			for (j = 0; j < order; ++j) {
				this.data[i][j] = data[i][j];
			}
		}
	}

	/**
	 * Constructs an empty Semigroup of the given order.
	 * 
	 * @param order
	 *            order of the semigroup
	 */
	public Semigroup(int order) {
		this.order = order;
		this.data = new int[order][order];
	}

	/**
	 * Construct a semigroup from file (Not implemented Yet)
	 * 
	 * @param filename
	 */
	public Semigroup(String filename) {
		System.out.println("Not implemented yet!");
	}

	/*
	 * ----------------- Public Methods -----------------
	 */
	/**
	 * Returns the multiplication table as String
	 * 
	 * @return
	 */
	public String toElegantReport() {
		String aString;
		aString = "";
		int column;
		int row;

		for (row = 0; row < order; row++) {
			for (column = 0; column < order; column++) {
				aString = aString + " " + this.data[row][column];
			}
			aString = aString + "\n";
		}
		aString = aString.replaceAll("(?m)^ ", "");
		return aString;
	}

	/**
	 * 
	 * @return Returns the order of the semigroup
	 */

	public int elements() {
		return order;
	}

	/**
	 * Shows the multiplication table of the semigroup as a two dimensional
	 * matrix.
	 */

	public void show() {
		int i = 0;
		int j = 0;
		for (i = 0; i < order; ++i) {
			for (j = 0; j < order; ++j) {
				System.out.print(this.data[i][j]);
				System.out.print(" ");
			}
			System.out.println(" ");
		}
	}

	/**
	 * 
	 * @return Returns true if the multiplication table is associative.
	 */
	public boolean isAssociative() {
		int i, j, k;
		for (i = 0; i < order; ++i) {
			for (j = 0; j < order; ++j) {
				for (k = 0; k < order; ++k) {
					if (!(data[i][data[j][k] - 1] == data[data[i][j] - 1][k])) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * 
	 * @return Returns true if the semigroup is commutative
	 */
	public boolean isCommutative() {
		int i, j;
		for (i = 0; i < order; ++i) {
			for (j = 0; j < order; ++j) {
				if (!(data[i][j] == data[j][i])) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Checks if the semigroup given has a zero element
	 * 
	 * @return Returns -1 if the semigroup doesn't have a zero element, and the
	 *         zero element otherwise.
	 */
	public int findZero() {
		// Returns -1 is can't find a zero element, and the zero element
		// otherwise.
		int i, j;
		boolean isZero = false;
		for (i = 0; i < order; ++i) {
			if (isZero == true) {
				return i;
			}
			j = 0;
			isZero = true;
			while (isZero && (j < order)) {
				if (data[j][i] != i + 1) {
					isZero = false;
				}
				++j;
			}
		}
		if (isZero == true) {
			return order;
		}
		return -1;
	}

	/**
	 * Loads all the semigroups which are saved in the data files
	 * 
	 * @return A list with all the semigroups which have been loaded
	 */

	public static Semigroup readSemigroupFromFile(String arxiu) {
		Semigroup group = null;
		try {
			FileReader reader = new FileReader(arxiu);
			BufferedReader theReader;
			theReader = new BufferedReader(reader);
			// La primera l’nia que llig Žs l'ordre del semigrup
			String str = theReader.readLine();
			StringTokenizer st = new StringTokenizer(str);
			String strNumber = st.nextToken();
			int order = Integer.parseInt(strNumber);
			int[][] matrix = new int[order][order];
			int number;
			// System.out.println(order);
			int i, j;
			for (i = 0; i < order; ++i) {
				for (j = 0; j < order; ++j) {
					str = theReader.readLine();
					st = new StringTokenizer(str);
					strNumber = st.nextToken();
					number = Integer.parseInt(strNumber);
					// System.out.println(number) ;
					matrix[i][j] = number;
				}
			}
			group = new Semigroup(matrix);
			group.order = order;
			theReader.close();
		} catch (IOException e) {
			System.out.println("Error");
		}
		return group;
	}

	private static Semigroup[] loadFile(int order, String Fname) {
		// System.out.println("loading semigroups of order:");
		// System.out.println(order);
		int N = 0;
		int NSemigroups;
		int number, id;
		BufferedReader theReader;
		String str;
		String strNumber;
		StringTokenizer st;
		int[][] matrix;
		Semigroup[] result;
		String fileName = null;
		int elements = 0;
		switch (order) {
		case 2:
			fileName = Fname + "sem.2";
			elements = 4;
			break;
		case 3:
			fileName = Fname + "sem.3";
			elements = 18;
			break;
		case 4:
			fileName = Fname + "sem.4";
			elements = 126;
			break;
		case 5:
			fileName = Fname + "sem.5";
			elements = 1160;
			break;
		case 6:
			fileName = Fname + "sem.6";
			elements = 15973;
			break;
		}
		result = new Semigroup[elements];

		File arxiu = new File(fileName);
		try {
			NSemigroups = elements;
			FileReader reader = new FileReader(arxiu);
			theReader = new BufferedReader(reader);
			for (N = 0; N < NSemigroups; ++N) {
				matrix = new int[order][order];
				str = theReader.readLine();
				st = new StringTokenizer(str);
				strNumber = st.nextToken();
				number = Integer.parseInt(strNumber);
				id = number;
				strNumber = st.nextToken();
				number = Integer.parseInt(strNumber);
				int i, j;
				for (i = 0; i < order; ++i) {
					for (j = 0; j < order; ++j) {
						str = theReader.readLine();
						st = new StringTokenizer(str);
						strNumber = st.nextToken();
						number = Integer.parseInt(strNumber);
						matrix[i][j] = number;
					}
				}
				result[N] = new Semigroup(matrix);
				result[N].ID = id;
			}
			reader.close();
		} catch (IOException excepcio) {
			System.out.println("Error");
		}
		return result;
	}

	/**
	 * 
	 * @param Fname
	 *            Specify the route to the sem.x files relative to your program
	 * @return loads all semigroups of orders 2 to 6
	 */

	public static Semigroup[] loadFromFile(String Fname) {

		Semigroup[] result;
		Semigroup[] aux;
		int i = 0, j = 0;
		int alreadySaved = 0;
		result = new Semigroup[4 + 18 + 126 + 1160 + 15973];
		int[] elements = { 4, 18, 126, 1160, 15973 };

		for (i = 2; i < 7; ++i) {
			aux = loadFile(i, Fname);
			for (j = 0; j < elements[i - 2]; ++j) {
				result[alreadySaved + j] = aux[j];
			}
			alreadySaved += elements[i - 2];
		}
		return result;
	}

	/**
	 * Checks if a given decomposition of a semigroup is a resonant one
	 * 
	 * @param s0
	 * @param s1
	 * @param orden
	 *            the order of the semigroup
	 * @return Returns true if the given decomposition is resonant, false
	 *         otherwise
	 */

	public boolean isResonant(SetS s0, SetS s1) {
		int i, j, n0 = s0.nElements, n1 = s1.nElements;
		if (SetS.fillTheSpace(s0, s1, order)) {
			for (i = 0; i < n0; ++i) {
				for (j = 0; j < n0; ++j) {
					if (!s0.find(this.data[s0.elementAt(i) - 1][s0.elementAt(j) - 1])) {
						return false;
					}
				}
			}
			for (i = 0; i < n0; ++i) {
				for (j = 0; j < n1; ++j) {
					if (!s1.find(this.data[s0.elementAt(i) - 1][s1.elementAt(j) - 1])) {
						return false;
					}
				}
			}
			for (i = 0; i < n1; ++i) {
				for (j = 0; j < n1; ++j) {
					if (!s0.find(this.data[s1.elementAt(i) - 1][s1.elementAt(j) - 1])) {
						return false;
					}
				}
			}
		} else {
			return false;
		}

		return true;
	}

	public boolean isResonatF(SetS S0, SetS S1) {
		int zero = this.findZero();
		if (!hasNonZeroRepeatingElement(S0, S1, zero) && this.isResonant(S0, S1)) {
			return true;
		} else {
			return false;
		}
	}

	public SetS[][] findResonancesF(int n1, int n2) {
		SetS total = new SetS(this.order);
		SetS[] list1 = total.subSets(n1);
		SetS[] list2 = total.subSets(n2);
		SetS[][] result = null;
		SetS[][] auxiliar = null;
		int foundResonances = 0;
		int i, j, k = 0;
		for (i = 0; i < list1.length; ++i) {
			for (j = 0; j < list2.length; ++j) {
				if (this.isResonatF(list1[i], list2[j]) && SetS.fillTheSpace(list1[i], list2[j], this.order)) {
					// System.out.println("FindResonances(a,b): resonancia
					// encontrada");
					foundResonances = foundResonances + 1;
					auxiliar = result;
					result = new SetS[foundResonances][2];
					for (k = 0; k < foundResonances - 1; ++k) {
						result[k][0] = auxiliar[k][0];
						result[k][1] = auxiliar[k][1];
					}
					result[foundResonances - 1][0] = list1[i];
					result[foundResonances - 1][1] = list2[j];
				}
			}
		}
		return result;
	}

	/**
	 * Looks for all the resonant decomposition of a possible semigroup with n1
	 * elements in S0 and n2 in S1
	 * 
	 * @param n1
	 *            Number of elements of S0
	 * @param n2
	 *            Number of elements of S1
	 * @return Returns a list of 2-dimensional arrays of SetS with format { SetS
	 *         S0 , SetS S1 }. It means that for each element of the array
	 *         (let's call it list), list[i][0] plays the role of S0 in the
	 *         resonant decomposition and list[i][1] plays the role of S1.
	 */

	public SetS[][] findResonances(int n1, int n2) {
		SetS total = new SetS(this.order);
		SetS[] list1 = total.subSets(n1);
		SetS[] list2 = total.subSets(n2);
		SetS[][] result = null;
		SetS[][] auxiliar = null;
		int foundResonances = 0;
		int i, j, k = 0;
		for (i = 0; i < list1.length; ++i) {
			for (j = 0; j < list2.length; ++j) {
				if (this.isResonant(list1[i], list2[j]) && SetS.fillTheSpace(list1[i], list2[j], this.order)) {
					// System.out.println("FindResonances(a,b): resonancia
					// encontrada");
					foundResonances = foundResonances + 1;
					auxiliar = result;
					result = new SetS[foundResonances][2];
					for (k = 0; k < foundResonances - 1; ++k) {
						result[k][0] = auxiliar[k][0];
						result[k][1] = auxiliar[k][1];
					}
					result[foundResonances - 1][0] = list1[i];
					result[foundResonances - 1][1] = list2[j];
				}
			}
		}
		return result;
	}

	/**
	 * Looks for all possible resonant decomposition of the semigroup
	 * 
	 * @return Returns a list of 2-dimensional arrays of SetS with format { SetS
	 *         S0 , SetS S1 }. It means that for each element of the array
	 *         (let's call it list), list[i][0] plays the role of S0 in the
	 *         resonant decomposition and list[i][1] plays the role of S1.
	 */
	public SetS[][] findAllResonances() {
		int i, j, k;
		SetS[][] result = null;
		SetS[][] auxiliar;
		SetS[][] intermediateResult;
		int N = 0;
		for (i = 1; i < this.order; ++i) {
			for (j = 1; j < this.order; ++j) {
				intermediateResult = this.findResonances(i, j);
				if (intermediateResult != null) {
					// System.out.println("FindAllResonances: he encontrado una
					// resonancia");
					auxiliar = result;
					N = N + intermediateResult.length;
					result = new SetS[N][2];
					for (k = 0; k < N - intermediateResult.length; ++k) {
						result[k][0] = auxiliar[k][0];
						result[k][1] = auxiliar[k][1];
					}
					for (k = 0; k < intermediateResult.length; ++k) {
						result[N - intermediateResult.length + k][0] = intermediateResult[k][0];
						result[N - intermediateResult.length + k][1] = intermediateResult[k][1];
					}
				}
			}
		}
		return result;
	}

	public SetS[][] findAllResonancesF() {
		int i, j, k;
		SetS[][] result = null;
		SetS[][] auxiliar;
		SetS[][] intermediateResult;
		int N = 0;
		for (i = 1; i < this.order; ++i) {
			for (j = 1; j < this.order; ++j) {
				intermediateResult = this.findResonancesF(i, j);
				if (intermediateResult != null) {
					// System.out.println("FindAllResonances: he encontrado una
					// resonancia");
					auxiliar = result;
					N = N + intermediateResult.length;
					result = new SetS[N][2];
					for (k = 0; k < N - intermediateResult.length; ++k) {
						result[k][0] = auxiliar[k][0];
						result[k][1] = auxiliar[k][1];
					}
					for (k = 0; k < intermediateResult.length; ++k) {
						result[N - intermediateResult.length + k][0] = intermediateResult[k][0];
						result[N - intermediateResult.length + k][1] = intermediateResult[k][1];
					}
				}
			}
		}
		return result;
	}

	public static boolean hasNonZeroRepeatingElement(SetS S0, SetS S1, int zero) {
		if (zero != -1) {
			for (int i = 0; i < S0.nElements; i++) {
				for (int j = 0; j < S1.nElements; j++) {
					if (S0.elementAt(i) == S1.elementAt(j) && S0.elementAt(i) != zero) {
						return true;
					}
				}
			}
			return false;
		} else {
			for (int i = 0; i < S0.nElements; i++) {
				for (int j = 0; j < S1.nElements; j++) {
					if (S0.elementAt(i) == S1.elementAt(j)) {
						return true;
					}
				}
			}
			return false;
		}
	}

	/**
	 * Filters all the resonances of the given semigroup so that the only
	 * repeating element in and s1 is the zero element if it exists and returns
	 * it as a SetS array where result[i][0] is S0 and result[i][1] is S1
	 * 
	 * @return SetS[][]
	 */
	public SetS[][] findAllResonancesF2() {
		SetS resonances[][];
		SetS result[][] = null;
		SetS auxiliar[][];
		int zero = this.findZero();
		int j = 0;
		resonances = this.findAllResonances();
		if (resonances != null) {
			for (int i = 0; i < resonances.length; i++) {
				if (!hasNonZeroRepeatingElement(resonances[i][0], resonances[i][1], zero)) {
					auxiliar = result;
					result = new SetS[j + 1][2];
					for (int k = 0; k < j; k++) {
						result[k][0] = auxiliar[k][0];
						result[k][1] = auxiliar[k][1];
					}
					result[j][0] = resonances[i][0];
					result[j][1] = resonances[i][1];
					j++;
				}
			}
			return result;
		} else {
			return null;
		}
	}

	/**
	 * Applies an isomorphism to the semigroup
	 * 
	 * @param s
	 *            is the SetS which identifies the permutation to apply
	 * @return Returns a new Semigroup which is the result of applying the
	 *         isomorphism
	 */

	public Semigroup permuteWith(SetS s) {
		int i, j;
		int[][] matrix = new int[this.order][this.order];
		SetS inverse = s.inversePermutation();
		// Abans que res copie la matriu
		// for ( i = 0 ; i < this.NElements ; ++i ){
		// for ( j = 0 ; j < this.NElements ; ++j) {
		// matriu[i][j] = this.data[i][j];
		// }
		// }
		for (i = 0; i < this.order; ++i) {
			// Intercanviem files
			for (j = 0; j < this.order; ++j) {
				matrix[i][j] = this.data[inverse.elementAt(i) - 1][inverse.elementAt(j) - 1];
				// matriu[l][ i] = this.data[ s.elementAt(l)-1 ][ i] ;
			}
		}
		// Ara reanomenem les entrades
		// System.out.println("Semigroup abans de reanomenar");
		// (new Semigroup(matriu)).show();
		for (i = 0; i < this.order; ++i) {
			for (j = 0; j < this.order; ++j) {
				if (matrix[i][j] != -1) {
					matrix[i][j] = s.elementAt(matrix[i][j] - 1);
				}
			}

		}
		return new Semigroup(matrix);
	}

	/**
	 * Gives all the semigroups isomorphic to a given one
	 * 
	 * @return A list containing all the isomorphic semigroups
	 */

	public Semigroup[] permute() {
		SetS identity = new SetS(this.order);
		SetS[] permutations = identity.allPermutations();
		int k;
		Semigroup[] result = new Semigroup[permutations.length];
		for (k = 0; k < permutations.length; ++k) {

			// System.out.println("***");
			// permutacions[k].show();
			// System.out.println("******");
			result[k] = this.permuteWith(permutations[k]);
			// resultat[k].show();
		}
		return result;
	}

	/**
	 * Applies all possible antiisomorphisms to the semigroup
	 * 
	 * @return A list containing all the antiisomorphic semigroups
	 */

	public Semigroup[] antiPermute() {
		return (this.transpose()).permute();
	}

	/**
	 * This methods tests for the existence of an isomorphism or antiisomorphism
	 * between the current object and the semigroup B.
	 * 
	 * @param B
	 *            is a semigroup which is believed to be isomorphic to the
	 *            current one
	 * @return Return value mean: (false , false ) -> isomorphism or
	 *         antiisomorphism don't exist.(true, true ) -> isomorphism exists.
	 *         (true, false ) -> antiisomorphism exists
	 */
	public boolean[] isotest(Semigroup B) {
		boolean[] resultNothing = { false, false };
		boolean[] resultIso = { true, true };
		boolean[] resultAnti = { true, false };
		Semigroup[] permutations = this.permute();
		Semigroup[] permutationsB = B.permute();
		int i, j;
		for (i = 0; i < permutations.length; ++i) {
			for (j = 0; j < permutationsB.length; ++j) {
				if (permutations[i].isEqualTo(permutationsB[j])) {
					return resultIso;
				}
			}
		}

		Semigroup[] antiPermutations = this.antiPermute();
		Semigroup[] antiPermutationsB = B.antiPermute();
		for (i = 0; i < permutations.length; ++i) {
			for (j = 0; j < permutationsB.length; ++j) {
				if (antiPermutations[i].isEqualTo(antiPermutationsB[j])) {
					return resultAnti;
				}
			}
		}
		return resultNothing;
	}

	/**
	 * Checks if the 2 semigroups are the same
	 * 
	 * @param B
	 *            the semigroup to check if is identic to the present one
	 * @return returns 1 if the semigroups are equal, 0 othercase
	 */

	int compare(Semigroup B) {
		int i, j;
		// System.out.println("Compare:");
		// this.show();
		// B.show();
		for (i = 0; i < this.order; ++i) {
			for (j = 0; j < B.order; ++j) {
				if (this.data[i][j] > B.data[i][j]) {
					// System.out.println("Compare torna 0");
					return 0;
				}
			}
		}
		System.out.println("Compare torna 1");
		return 1;
	}

	/**
	 * Checks if the 2 semigroups are the same
	 * 
	 * @param B
	 *            is a semigroup to check if its the same that the current one
	 * @return true if are equal, false othercase
	 */
	public boolean isEqualTo(Semigroup B) {
		int i, j;
		// System.out.println("Compare:");
		// this.show();
		// B.show();
		if (this.order != B.order) {
			return false;
		}
		for (i = 0; i < this.order; ++i) {
			for (j = 0; j < B.order; ++j) {
				if (this.data[i][j] != B.data[i][j]) {
					// System.out.println("Compare torna 0");
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Returns a semigroup with a multiplication table which is transposed to
	 * the original one
	 */
	public Semigroup transpose() {
		int[][] matrix = new int[this.order][this.order];
		int i, j;
		for (i = 0; i < this.order; ++i) {
			for (j = 0; j < this.order; ++j) {
				matrix[i][j] = this.data[j][i];
			}
		}
		return new Semigroup(matrix);
	}

	/**
	 * Builds a selector for the semigroup
	 * 
	 * @return a Selector object
	 */

	public Selector getSelector() {
		Selector result = new Selector(this.order);
		int i, j;
		for (i = 0; i < this.order; i = i + 1) {
			for (j = 0; j < this.order; j = j + 1) {
				result.set(i, j, this.data[i][j] - 1, 1);
			}
		}
		return result;
	}

	/**
	 * Performs the S-expansion of a Lie algebra, represented by a
	 * StructureConstantSet object, by the current semigroup
	 * 
	 * @param s
	 *            represents the Lie algebra,
	 * @return a StructureConstantSetExpanded object, with the result of the
	 *         expansion
	 */
	public StructureConstantSetExpanded getExpandedStructureConstant(StructureConstantSet s) {
		Selector selectors = this.getSelector();
		// Number of generators for the Algebra
		int n = s.N;
		// Number of generators for the Semigroup
		int m = this.order;
		double[][][][][][] matrix = new double[n][m][n][m][n][m];
		int a, b, g, k, i, j;
		for (i = 0; i < n; ++i) {
			for (a = 0; a < m; ++a) {
				for (j = 0; j < n; ++j) {
					for (b = 0; b < m; ++b) {
						for (k = 0; k < n; ++k) {
							for (g = 0; g < m; ++g) {
								matrix[i][a][j][b][k][g] = selectors.get(a, b, g) * s.structureConstant(i, j, k);
							}
						}
					}
				}
			}
		}
		return new StructureConstantSetExpanded(matrix);
	}

	/**
	 * Performs the multiplication of two elements of the semigroup using its
	 * multiplication table, returns -1 if you enter an element that does not
	 * belong to the semigroup.
	 * 
	 * @param i
	 * @param j
	 * @return returns i * j according to the semigroup's multiplication table
	 */
	public int multiply(int i, int j) {
		if (i > this.order || j > this.order) {
			System.out.println("You entered an element that does not belong to the semigroup");
			return -1;
		} else {
			return this.data[i - 1][j - 1];
		}
	}

	/**
	 * Checks if the Smaller Semigroup is contained in the Bigger one and
	 * returns a list of the times it is contained
	 * 
	 * @param sgroupBig
	 * @param sgroupSmall
	 * @return
	 */
	public static Semigroup[] subSemigroups(Semigroup sgroupBig, Semigroup sgroupSmall) {
		if (sgroupBig.isCommutative() && sgroupBig.isAssociative() && sgroupSmall.isCommutative()
				&& sgroupSmall.isAssociative()) {
			int order, orderSubSemigroups, fila, columna, j, nSemigrupo, l, i = 0;
			int cantResults = 0;
			order = sgroupBig.order;
			orderSubSemigroups = sgroupSmall.order;
			SetS gens = new SetS(order);
			SetS[] subconjuntos = gens.subSets(orderSubSemigroups);
			int[][] matriu = sgroupBig.data;
			Semigroup[] result = null, aux;
			Semigroup group = new Semigroup(orderSubSemigroups);
			fila = 0;
			columna = 0;
			int nGeneradores = 0;
			Semigroup group2 = new Semigroup(orderSubSemigroups);
			for (nSemigrupo = 0; nSemigrupo < subconjuntos.length; ++nSemigrupo) {
				nGeneradores = 0;
				SetS generadores = new SetS(0);
				int[][] matriz = group.data;
				int[][] matriz2 = new Semigroup(orderSubSemigroups).data;
				boolean candidatoSemigrupo = true;
				for (i = 0; i < orderSubSemigroups; ++i) {
					for (j = 0; j < orderSubSemigroups; ++j) {
						fila = subconjuntos[nSemigrupo].elementAt(i);
						columna = subconjuntos[nSemigrupo].elementAt(j);
						matriz[i][j] = matriu[fila - 1][columna - 1];
					}
				}
				generadores = new SetS(0);
				generadores.addElement(matriz[0][0]);
				for (i = 0; i < orderSubSemigroups; ++i) {
					for (j = 0; j < orderSubSemigroups; ++j) {
						if (generadores.find(matriz[i][j]) == false) {
							int elemento = matriz[i][j];
							generadores = generadores.addElement(elemento);
						}
					}
				}
				
				nGeneradores = generadores.nElements;
				for (int z = 0; z < nGeneradores; ++z) {
					if (subconjuntos[nSemigrupo].find(generadores.elementAt(z)) == false) {
						candidatoSemigrupo = false;
					}
				}
				
				for (i = 0; i < orderSubSemigroups; ++i) {
					for (j = 0; j < orderSubSemigroups; ++j) {
						fila = subconjuntos[nSemigrupo].elementAt(i);
						columna = subconjuntos[nSemigrupo].elementAt(j);
						matriz[i][j] = matriu[fila - 1][columna - 1];
						for (l = 0; l < orderSubSemigroups; ++l) {
							if (matriz[i][j] == subconjuntos[nSemigrupo].elementAt(l)) {
								matriz2[i][j] = l + 1;
								break;
							}
						}
					}
				}

				if (nGeneradores <= orderSubSemigroups && candidatoSemigrupo) {
					group = new Semigroup(matriz);
					group2 = new Semigroup(matriz2);
					if (group2.isAssociative() && group2.isCommutative()) {
						boolean isomorfo = group2.isotest(sgroupSmall)[0];
						if (isomorfo) {
							aux = result;
							result = new Semigroup[cantResults + 1];
							for (i = 0; i < cantResults; i++) {
								result[i] = aux[i];
							}
							result[cantResults] = new Semigroup(group.data);
							cantResults += 1;
						}
					}
				}
			}
			return result;
		} else {
			return null;
		}
	}
}
