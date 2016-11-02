package examples;

import sexpansion.Semigroup;

public class II_findzero_ex {

	public static void main(String[] args) {
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
	int zero_S_E2 = S_E2.findZero();
	int zero_S_K3 = S_K3.findZero();
	int zero_S_N1 = S_N1.findZero();
	int zero_S_N2 = S_N2.findZero();
	int zero_S_N3 = S_N3.findZero();
	int zero_S_S3 = S_S3.findZero();
	int zero_S_S2 = S_S2.findZero();
	int zero_S_M3 = S_M3.findZero();
	int zero_S_M4 = S_M4.findZero();
	int nozero = -1;
	if (zero_S_E2 == nozero){
		System.out.println("The semigroup S_E2 has no zero element");}
	else {
		System.out.println("The zero element of S_E2 is " +zero_S_E2);}
	if (zero_S_K3 == nozero){
		System.out.println("The semigroup S_K3 has no zero element");}
	else {
		System.out.println("The zero element of S_K3 is " +zero_S_K3);}
	if (zero_S_N1 == nozero){
		System.out.println("The semigroup S_N1 has no zero element");}
	else {
		System.out.println("The zero element of S_N1 is " +zero_S_N1);}
	if (zero_S_N2 == nozero){
		System.out.println("The semigroup S_N2 has no zero element");}
	else {
		System.out.println("The zero element of S_N2 is " +zero_S_N2);}
	if (zero_S_N3 == nozero){
		System.out.println("The semigroup S_N3 has no zero element");}
	else {
		System.out.println("The zero element of S_N3 is " +zero_S_N3);}
	if (zero_S_S3 == nozero){
		System.out.println("The semigroup S_S3 has no zero element");}
	else {
		System.out.println("The zero element of S_S3 is " +zero_S_S3);}
	if (zero_S_S2 == nozero){
		System.out.println("The semigroup S_S2 has no zero element");}
	else {
		System.out.println("The zero element of S_S2 is " +zero_S_S2);}
	if (zero_S_M3 == nozero){
		System.out.println("The semigroup S_M3 has no zero element");}
	else {
		System.out.println("The zero element of S_M3 is " +zero_S_M3);}
	if (zero_S_M4 == nozero){
		System.out.println("The semigroup S_M4 has no zero element");}
	else {
		System.out.println("The zero element of S_M4 is " +zero_S_M4);}
	}
}