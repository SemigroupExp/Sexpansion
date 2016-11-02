package examples;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import sexpansion.Semigroup;
import sexpansion.SetS;
public class II_findresonances_ex {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
	// Define a list of 2-dimensional arrays for each semigroup 
	SetS[][] rS_E2;
	SetS[][] rS_K3;
	SetS[][] rS_N1;
	SetS[][] rS_N2;
	SetS[][] rS_N3;
	SetS[][] rS_S3;
	SetS[][] rS_S2;
	SetS[][] rS_M3;
	SetS[][] rS_M4;
	SetS S0;
	SetS S1;
	int j, nResonances = 0;
	// Manually enter the semigroups table
	int[][] mS_E2 = {{1,2,3,4},{2,3,4,4},{3,4,4,4},{4,4,4,4}};
	int[][] mS_K3 = {{4,4,1,4},{4,2,2,4},{1,2,3,4},{4,4,4,4}};
	int[][] mS_N1 = {{4,4,1,4},{4,2,4,4},{1,4,3,4},{4,4,4,4}};
	int[][] mS_N2 = {{2,3,4,4},{3,4,4,4},{4,4,4,4},{4,4,4,4}};
	int[][] mS_N3 = {{4,1,4,4},{1,2,3,4},{4,3,4,4},{4,4,4,4}};
	int[][] mS_S3 = {{3,4,1,4},{4,2,4,4},{1,4,3,4},{4,4,4,4}};
	int[][] mS_S2 = {{1,2,3},{2,3,2},{3,2,3}};
	int[][] mS_M3 = {{1,2,3,4},{2,3,4,1},{3,4,1,2},{4,1,2,3}};
	int[][] mS_M4 = {{1,2,3,4,5},{2,3,4,5,2},{3,4,5,2,3},{4,5,2,3,4},{5,2,3,4,5}};

	
	Semigroup S_E2 = new Semigroup( mS_E2 );
	Semigroup S_K3 = new Semigroup( mS_K3 );
	Semigroup S_N1 = new Semigroup( mS_N1 );
	Semigroup S_N2 = new Semigroup( mS_N2 );
	Semigroup S_N3 = new Semigroup( mS_N3 );
	Semigroup S_S3 = new Semigroup( mS_S3 );
	Semigroup S_S2 = new Semigroup( mS_S2 );
	Semigroup S_M3 = new Semigroup( mS_M3 );
	Semigroup S_M4 = new Semigroup( mS_M4 );
	rS_E2 = S_E2.findAllResonances();
	rS_K3 = S_K3.findAllResonances();
	rS_N1 = S_N1.findAllResonances();
	rS_N2 = S_N2.findAllResonances();
	rS_N3 = S_N3.findAllResonances();
	rS_S3 = S_S3.findAllResonances();
	rS_S2 = S_S2.findAllResonances();
	rS_M3 = S_M3.findAllResonances();
	rS_M4 = S_M4.findAllResonances();
		
	File fout = new File("./Output_examples/Output_II_findresonances_ex.txt");
	PrintWriter bw = new PrintWriter(fout, "UTF-8");
	
	if (rS_E2 != null) {
	bw.println("The semigroup S_E2 has "+rS_E2.length+ " resonances:");
	for ( j = 0 ; j < rS_E2.length ; ++j ) {
		nResonances = nResonances + 1;
		S0 = rS_E2[j][0] ;
		S1 = rS_E2[j][1] ;
		bw.println("Resonance #" +nResonances);
		bw.println("S0: " +S0.toElegantReport());
		bw.println("S1: " +S1.toElegantReport());
		}
		nResonances = 0;
		bw.println("************************************");
	}

	if (rS_K3 != null) {
	bw.println("The semigroup S_K3 has "+rS_K3.length+ " resonances:");
	for ( j = 0 ; j < rS_K3.length ; ++j ) {
		nResonances = nResonances + 1;
		S0 = rS_K3[j][0] ;
		S1 = rS_K3[j][1] ;
		bw.println("Resonance #" +nResonances);
		bw.println("S0: " +S0.toElegantReport());
		bw.println("S1: " +S1.toElegantReport());
		}
		nResonances = 0;
		bw.println("************************************");
	}
	
	if (rS_N1 != null) {
	bw.println("The semigroup S_N1 has "+rS_N1.length+ " resonances:");
	for ( j = 0 ; j < rS_N1.length ; ++j ) {
		nResonances = nResonances + 1;
		S0 = rS_N1[j][0] ;
		S1 = rS_N1[j][1] ;
		bw.println("Resonance #" +nResonances);
		bw.println("S0: " +S0.toElegantReport());
		bw.println("S1: " +S1.toElegantReport());
		}
		nResonances = 0;
		bw.println("************************************");
	}
	
	if (rS_N2 != null) {
	bw.println("The semigroup S_N2 has "+rS_N2.length+ " resonances:");
	for ( j = 0 ; j < rS_N2.length ; ++j ) {
		nResonances = nResonances + 1;
		S0 = rS_N2[j][0] ;
		S1 = rS_N2[j][1] ;
		bw.println("Resonance #" +nResonances);
		bw.println("S0: " +S0.toElegantReport());
		bw.println("S1: " +S1.toElegantReport());
		}
		nResonances = 0;
		bw.println("************************************");
	}
	
	if (rS_N3 != null) {
	bw.println("The semigroup S_N3 has "+rS_N3.length+ " resonances:");
	for ( j = 0 ; j < rS_N3.length ; ++j ) {
		nResonances = nResonances + 1;
		S0 = rS_N3[j][0] ;
		S1 = rS_N3[j][1] ;
		bw.println("Resonance #" +nResonances);
		bw.println("S0: " +S0.toElegantReport());
		bw.println("S1: " +S1.toElegantReport());
		}
		nResonances = 0;
		bw.println("************************************");
	}
	
	if (rS_S3 != null) {
	bw.println("The semigroup S_S3 has "+rS_S3.length+ " resonances:");
	for ( j = 0 ; j < rS_S3.length ; ++j ) {
		nResonances = nResonances + 1;
		S0 = rS_S3[j][0] ;
		S1 = rS_S3[j][1] ;
		bw.println("Resonance #" +nResonances);
		bw.println("S0: " +S0.toElegantReport());
		bw.println("S1: " +S1.toElegantReport());
		}
		nResonances = 0;
		bw.println("************************************");
	}
	
	if (rS_S2 != null) {
	bw.println("The semigroup S_S2 has "+rS_S2.length+ " resonances:");
	for ( j = 0 ; j < rS_S2.length ; ++j ) {
		nResonances = nResonances + 1;
		S0 = rS_S2[j][0] ;
		S1 = rS_S2[j][1] ;
		bw.println("Resonance #" +nResonances);
		bw.println("S0: " +S0.toElegantReport());
		bw.println("S1: " +S1.toElegantReport());
		}
		nResonances = 0;
		bw.println("************************************");
	}
	
	if (rS_M3 != null) {
	bw.println("The semigroup S_M3 has "+rS_M3.length+ " resonances:");
	for ( j = 0 ; j < rS_M3.length ; ++j ) {
		nResonances = nResonances + 1;
		S0 = rS_M3[j][0] ;
		S1 = rS_M3[j][1] ;
		bw.println("Resonance #" +nResonances);
		bw.println("S0: " +S0.toElegantReport());
		bw.println("S1: " +S1.toElegantReport());
		}
		nResonances = 0;
		bw.println("************************************");
	}

	if (rS_M4 != null) {
	bw.println("The semigroup S_M4 has "+rS_M4.length+ " resonances:");
	for ( j = 0 ; j < rS_M4.length ; ++j ) {
		nResonances = nResonances + 1;
		S0 = rS_M4[j][0] ;
		S1 = rS_M4[j][1] ;
		bw.println("Resonance #" +nResonances);
		bw.println("S0: " +S0.toElegantReport());
		bw.println("S1: " +S1.toElegantReport());
		}
		nResonances = 0;
		bw.println("************************************");
	}

System.out.println("Results printed to file" +fout);
System.out.println("For a better visualization open the file using Notepad++");
bw.close();
}
}