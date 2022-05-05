package statistics;

public class StatisticsMain {

	public static void main(String [] args) {
		
		System.out.println("Start");
		
		DataFrame df = new DataFrame(10,7);
		
		System.out.println("Demonstration of Legacy (pre-2022) Statistics Code");
		System.out.println("==================================================");		

		df.setBlockRandRange(2, 5, 3, 6, 10,20);
		System.out.println("Setting column names and using them");
		System.out.println("===================================");
		
		
		System.out.println("Setting the name for column 5 = 'Data'"); 		
		df.setHeader(5, "Data");
		System.out.println();
		
		System.out.print("The index for the column called 'Data' is : "); 
		System.out.println(df.getColIndex("Data"));
		
		df.show();
		
		System.out.println();
		System.out.println("Display columns 2 to 5"); 		
		df.showCols(2, 5);
		
		
		System.out.println();
		System.out.println("Show the elements in columns 1 to 5 and rows 3 to 5 inclusive"); 		
		df.showBlock(1,5, 3, 5);

		System.out.println();
		System.out.println("Methods to compute sums of rows, columns etc.");
		System.out.println("=============================================");		
		
		double sumAll;
		sumAll = df.sum(1, 10, 1, 7);
		System.out.println();
		System.out.print("Sum of all cells = ");
		System.out.println(sumAll);
		
		double sumACol;
		sumACol = df.sumCol(2, 1, 4);
		System.out.print("Sum of column 2, between rows 1 and 4 = ");
		System.out.println(sumACol);
		
		df.setHeader(2, "Col 2");
		sumACol = df.sumCol("Col 2", 1, 4);
		System.out.print("Sum of 'Col 2' (column 2), between rows 1 and 4 = ");
		System.out.println(sumACol);	
		
		sumACol = df.sumCol(5);
		System.out.print("Sum of column 5 = ");
		System.out.println(sumACol);

		sumACol = df.sumCol("Data");
		System.out.print("Sum of column 'Data' = ");
		System.out.println(sumACol);
	

		Double sumARow;
		sumARow = df.sumRow(2, 3, 5);
		System.out.print("Sum of row 5, between columns 2 and 3 = ");
		System.out.println(sumARow);		
		
		sumARow = df.sumRow(3);
		System.out.print("Sum of row 3 = ");
		System.out.println(sumARow);		
		
		System.out.println();
		System.out.println("Converting the dataframe to a simple string as an aid to testing");
		System.out.println("================================================================");
		
		System.out.println("Convert the dataframe to a string - including headers");
		System.out.println(df.toString(2,5,3,6, true));
		System.out.println();
		System.out.println("...and without headers");
		System.out.println(df.toString(2,5,3,6, false));

		
		System.out.println();
		System.out.println("Setting values at specific locations in the dataframe");
		System.out.println("=====================================================");		
		System.out.println();
		System.out.println("The complete table:");
		df.show();

		System.out.println();
		System.out.println("Set the value at (2,3) to 99.0");
		df.setAt(2, 3, 99.0);
		df.show();		

		System.out.println();
		System.out.println("Inserting and deleting columns and rows");
		System.out.println("=======================================");
		
		System.out.println();
		System.out.println("Delete column 2:");
		df.deleteCol(2);
		df.show();
		

		System.out.println();
		System.out.println("Insert a column at position 4");
		df.insertCol(4);
		df.show();
		
		

		System.out.println();
		System.out.println("Delete Row 4");
		df.deleteRow(4);
		df.show();
		

		System.out.println();
		System.out.println("Insert a row at position 5");
		df.insertRow(5);
		df.show();
		

		System.out.println();
		System.out.println("Reset the whole table back to zeroes");
		df.setBlockZeroes(1,10,1,7);
		df.show();	
		
		System.out.println("Create an empty 3x3 dataframe");
		DataFrame df2 = new DataFrame(3,3);
		df2.show();
		
		System.out.println();
		System.out.println("Add 5 to every cell in the dataframe");
		df2.add(5.0);
		df2.show();

		System.out.println();
		System.out.println("Add 9 to cells in the top-left (2x2) section of the dataframe");
		df2.add(1,2,1,2,9);
		df2.show();
		
		System.out.println();
		System.out.println("Add 7 to cells in the bottom two rows (3x2) section of the dataframe");
		df2.add(1,3,2,3,7);
		df2.show();
		
		System.out.println();
		System.out.println("Subtract 5 from every cell of the dataframe");
		df2.subtract(5.0);
		df2.show();
		
		System.out.println();
		System.out.println("Add one dataframe to another (both dataframes the same size):");
		System.out.println("   (1) Create a new dataframe with 20 in every cell");
		DataFrame df3 = new DataFrame(3,3);
		df3.add(20);
		df3.show();
		System.out.println("   (2) Add that new dataframe to our existing dataframe");
		df2.add(df3);
		df2.show();	
		
		System.out.println();
		System.out.println("Multiply every cell of the dataframe by 4");
		df2.mult(4.0);
		df2.show();
		

		System.out.println();
		System.out.println("Multiply one dataframe by another:");
		System.out.println("   (1) Create a first dataframe with 10 in every cell");
		DataFrame df4 = new DataFrame(3,3);
		df4.add(10);
		df4.show();
		
		System.out.println("   (2) Create a second dataframe with [2,4,5] down the diagonal");
		DataFrame df5 = new DataFrame(3,3);
		df5.setAt(1, 1, 2);
		df5.setAt(2, 2, 4);
		df5.setAt(3, 3, 5);		
		df5.show();		

		System.out.println("   (3) Multiply the first dataframe by the second (expecting [[20,40,50], [20,40,50],[20,40,50]]");
		df4.mmult(df5);
		df4.show();
		
		System.out.println();
		System.out.println("Test the 'Fast Efficient Trigonometric' (FET) Functions (arguments in radians):");
		System.out.println("===============================================================================");
		System.out.println("First sin");
		
		System.out.println("   (1) Create a dataFrame with radian aruments in cells");
		DataFrame df_for_sin = new DataFrame(3,3);

		df_for_sin.setAt(1, 1, 0.1);  	// sin(0.1) = 0.10
		df_for_sin.setAt(1, 2, 0.15); 	// sin(0.15) = 0.15
		df_for_sin.setAt(1, 3, 0.2); 	// sin(0.2) = 0.20
		df_for_sin.setAt(2, 1, 1.2); 	// sin(1.2) = 0.93 
		df_for_sin.setAt(2, 2, 1.4); 	// sin(1.4) = 0.99 
		df_for_sin.setAt(2, 3, 1.6); 	// sin(1.6) = 1.00 		
		
		df_for_sin.show();		

		System.out.println();
		System.out.println("   (2) Create a dataFrame with sin of argument in each cell");
		System.out.println("       Expecting [[0.10, 0.93, 0.00], [0.15,0.99,0.00], [0.20, 1.00, 0.00]");
		df_for_sin.sin();
		df_for_sin.show();		
		
		System.out.println();
		System.out.println("Then cos");
		System.out.println("   (1) Create a dataFrame with radian aruments in cells");
		DataFrame df_for_cos = new DataFrame(3,3);

		df_for_cos.setAt(1, 1, 0.2);  	// cos(0.2) = 0.98
		df_for_cos.setAt(1, 2, 0.4); 	// cos(0.4) = 0.92
		df_for_cos.setAt(1, 3, 0.6); 	// cos(0.6) = 0.83
		df_for_cos.setAt(2, 1, 1.2); 	// cos(1.2) = 0.36
		df_for_cos.setAt(2, 2, 1.4); 	// cos(1.4) = 0.17
		df_for_cos.setAt(2, 3, 1.6); 	// cos(1.6) = -0.30
		
		df_for_cos.show();		

		System.out.println();
		System.out.println("   (2) Create a dataFrame with cos of argument in each cell");
		System.out.println("       Expecting [[0.98, 0.36, 1.00], [0.92,0.17,1.00], [0.83, -0.03, 1.00]");
		df_for_cos.cos();
		df_for_cos.show();	
		
		System.out.println();
		System.out.println("Finally tan");
		System.out.println("   (1) Create a dataFrame with radian aruments in cells");
		DataFrame df_for_tan = new DataFrame(3,3);

		df_for_tan.setAt(1, 1, 0.02);  	// tan(0.2) = 0.98
		df_for_tan.setAt(1, 2, 0.04); 	// tan(0.4) = 0.92
		df_for_tan.setAt(1, 3, 0.06); 	// tan(0.6) = 0.83
		df_for_tan.setAt(2, 1, 0.2); 	// tan(1.2) = 0.36
		df_for_tan.setAt(2, 2, 0.4); 	// tan(1.4) = 0.17
		df_for_tan.setAt(2, 3, 0.6); 	// tan(1.6) = -0.30
		
		df_for_tan.show();		

		System.out.println();
		System.out.println("   (2) Create a dataFrame with tan of argument in each cell");
		System.out.println("       Expecting [[0.02, 0.20, 0.00], [0.04, 0.42, 0.00], [0.06, 0.68, 0.00]");
		df_for_tan.tan();
		df_for_tan.show();	
		
		System.out.println("Demonstration of New 2022 Code to be tested");
		System.out.println("===========================================");		
		
		DataFrame df_linAlg = new DataFrame(4,4);
		
		System.out.println("Zero matrix");
		df_linAlg.zeros();
		df_linAlg.show();
		
		System.out.println("");	
		System.out.println("Identity");		
		df_linAlg.identity();
		df_linAlg.show();
		
		System.out.println("");			
		System.out.println("Transpose - Original");
		df_linAlg.setAt(1,3,13);
		df_linAlg.setAt(2,3,23);
		df_linAlg.setAt(3,1,31);
		df_linAlg.show();

		System.out.println("Transposed");
		df_linAlg.transpose();
		df_linAlg.show();		

		System.out.println("");	
		System.out.println("Upper trangular form");
		
		
		DataFrame df_upperTriangle = new DataFrame(11,10);
		// Assumes that we are dealing with an 'augmented' matrix .. 
		// The right-most column (i.e. theData[...][cols]) represents the 'Y' variable
		// The other columns represent the 'x' variables
		
		// Set the columns to a larger width to improve display format later
		for (int i=1;i<11;i++) {
			df_upperTriangle.setColWidth(i, 10);
		}
		df_upperTriangle.setBlockRandRange(1, 11, 1, 10, 1, 10000);
		System.out.println("Given the matrix:");
		df_upperTriangle.show();
		
		System.out.println("");	
		System.out.println("It's Upper trangular form is ");
		df_upperTriangle.upperTriangular();
		df_upperTriangle.show();		
		
		System.out.println("");	
		System.out.println("Eliminate to leave only values on the diagnonal");

		df_upperTriangle.rowElimination();
		df_upperTriangle.show();
		
		System.out.println("");	
		System.out.println("Now let's complete the Gaussian Ellimination by reducing the diagonal to '1's:");
		df_upperTriangle.onesReduce();
		df_upperTriangle.show();		
		

		
	}
}
