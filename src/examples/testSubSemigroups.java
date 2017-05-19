package examples;
import sexpansion.*;
public class testSubSemigroups {

	public static void main(String[] args) {
		int[][] mGrupoGrande = { { 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 2, 2 }, { 1, 1, 1, 2, 3, 3 }, { 1, 1, 2, 3, 4, 4 },
				{ 1, 2, 3, 4, 5, 5 }, { 1, 2, 3, 4, 5, 6 } };
		int[][] mgrupoDado = { { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 2 }, { 1, 1, 1, 2, 3 }, { 1, 1, 2, 3, 4 },
				{ 1, 2, 3, 4, 5 } };
		Semigroup grupoDado = new Semigroup(mgrupoDado);
		Semigroup grupoGrande = new Semigroup(mGrupoGrande);
		Semigroup[] subSemigrupos =  Semigroup.subSemigroups(grupoGrande,grupoDado);
		for (int i = 0;i<subSemigrupos.length;i++) {
			subSemigrupos[i].show();
			System.out.println("-------------------------");
		}
	}
}
