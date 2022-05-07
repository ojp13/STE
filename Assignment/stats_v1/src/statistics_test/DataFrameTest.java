package statistics_test;

import static org.junit.Assert.*;

import org.junit.Test;

import statistics.DataFrame;

public class DataFrameTest {
	static final int MAX_ROWS = 2000;
	static final int MAX_COLS = 40;
	static final int MAX_VAR = MAX_COLS - 1;
	static final int MIN_ROWS = 1;
	static final int MIN_COLS = 2;
	static final double ZERO_EQV = 1E-3;
	
	private static DataFrame filledDataFrame(int cols, int rows){
		
		DataFrame dataFrame = new DataFrame(cols,rows);
		
		for (int i = 1; i <= cols; i++){
			for (int j = MIN_ROWS; j <= rows; j++){
				dataFrame.setAt(i, j, 2 * i + 3 * j);
			}
		}
		return dataFrame;
	}
	
	private static DataFrame basicLinearProblem(int numVar){
		DataFrame dataFrame = new DataFrame(numVar + 1,numVar);
		int cols = dataFrame.getCols();
		int rows = dataFrame.getRows();
		
		//This use of tan has been manually verified to give a solvable SoE with
		//A unique solution
		for (int i = 1; i <= cols; i++){
			if (i == cols){
				dataFrame.setHeader(i, "y");
			} else {
				dataFrame.setHeader(i, "x" + i);
			}
			for (int j = MIN_ROWS; j <= rows; j++){
				dataFrame.setAt(i, j, Math.tan(i*i + j));
			}
		}
		return dataFrame;
	}
	
	private static DataFrame nonUniqueLinearProblem(int numVar){
		DataFrame dataFrame = basicLinearProblem(numVar);
		double x1;
		
		//This gives the system of solutions x2 = 2*x1
		//But no unique solution for either
		for (int i = 1; i <= dataFrame.getRows(); i++){
			x1 = dataFrame.getAt(1, i);
			dataFrame.setAt(2, i, x1 * 2);
		}
		
		return dataFrame;
	}
	
	private static DataFrame linearlyDependentProblem(int numVar){
		DataFrame dataFrame = new DataFrame(numVar + 1, numVar);
		int cols = dataFrame.getCols();
		int rows = dataFrame.getRows();
		
		for (int i = 1; i <= cols; i++){
			if (i == cols){
				dataFrame.setHeader(i, "y");
			} else {
				dataFrame.setHeader(i, "x" + i);
			}
			for (int j = MIN_ROWS; j <= rows; j++){
				dataFrame.setAt(i, j, i * j);
			}
		}
		return dataFrame;
	}
	
	private static DataFrame unsolvableProblem(int numVar){
		DataFrame dataFrame = new DataFrame(numVar + 1, numVar - 1);
		int cols = dataFrame.getCols();
		int rows = dataFrame.getRows();
		
		for (int i = 1; i <= cols; i++){
			if (i == cols){
				dataFrame.setHeader(i, "y");
			} else {
				dataFrame.setHeader(i, "x" + i);
			}
			for (int j = MIN_ROWS; j <= rows; j++){
				dataFrame.setAt(i, j, Math.tan(i*i + j));
			}
		}
		return dataFrame;
	}
	
	private static DataFrame upperTriangle(int numVar){
		DataFrame dataFrame = basicLinearProblem(numVar);
		int cols = dataFrame.getCols();
		int rows = dataFrame.getRows();
		
		for (int i = 1; i <= cols; i++){
			if (i == cols){
				dataFrame.setHeader(i, "y");
			} else {
				dataFrame.setHeader(i, "x" + i);
			}
			for (int j = MIN_ROWS; j <= rows; j++){
				if (i < j){
					dataFrame.setAt(i, j, 0);
				}
			}
		}
		return dataFrame;
	}
	
	private static DataFrame rowEliminated(int numVar){
		
		DataFrame dataFrame = basicLinearProblem(numVar);
		int cols = dataFrame.getCols();
		int rows = dataFrame.getRows();
		
		for (int i = 1; i <= cols; i++){
			if (i == cols){
				dataFrame.setHeader(i, "y");
			} else {
				dataFrame.setHeader(i, "x" + i);
			}
			for (int j = MIN_ROWS; j <= rows; j++){
				if (i != j && i != cols){
					dataFrame.setAt(i, j, 0);
				}
			}
		}
		return dataFrame;
	}

	@Test 
	public void test_7() {
		//Zeros on small dataframe
		
		int cols = MIN_COLS;
		int rows = MIN_ROWS;
		double cellVal;
		boolean testBool = true;
		
		DataFrame dataFrame = filledDataFrame(cols, rows);
		dataFrame.zeros();
		
		for (int i = 1; i <= cols; i++){
			for (int j = 1; j <= rows; j++){
				cellVal = dataFrame.getAt(i, j);
				if (cellVal != 0){
					testBool = false;
				}
			}
		}
		
		assertTrue(testBool);
		
	}
	
	@Test
	public void test_8() {
		//Zeros on large dataframe
		
		int cols = MAX_COLS;
		int rows = MAX_ROWS;
		double cellVal;
		boolean testBool = true;
		
		DataFrame dataFrame = filledDataFrame(cols, rows);
		dataFrame.zeros();
		
		for (int i = 1; i <= cols; i++){
			for (int j = 1; j <= rows; j++){
				cellVal = dataFrame.getAt(i, j);
				if (cellVal != 0){
					testBool = false;
				}
			}
		}
		
		assertTrue(testBool);
		
	}
	
	@Test
	public void test_9() {
		//Identity on small, square dataframe
		
		int cols = MIN_COLS;
		int rows = MIN_COLS;
		double cellVal;
		boolean diagBool = true;
		boolean nonDiagBool = true;
		
		DataFrame dataFrame = filledDataFrame(cols, rows);
		dataFrame.identity();
		
		for (int i = 1; i <= cols; i++){
			for (int j = 1; j <= rows; j++){
				cellVal = dataFrame.getAt(i, j);
				if (i == j){
					if (cellVal != 1.0){
						diagBool = false;
					}
				} else {
					if (cellVal != 0.0){
						nonDiagBool = false;
					}
				}
			}
		}
		
		assertTrue(diagBool && nonDiagBool);
		
	}
	
	@Test
	public void test_10() {
		//Identity on large, square dataframe
		
		int cols = MAX_COLS;
		int rows = MAX_COLS;
		double cellVal;
		boolean diagBool = true;
		boolean nonDiagBool = true;
		
		DataFrame dataFrame = filledDataFrame(cols, rows);
		dataFrame.identity();
		
		for (int i = 1; i <= cols; i++){
			for (int j = 1; j <= rows; j++){
				cellVal = dataFrame.getAt(i, j);
				if (i == j){
					if (cellVal != 1.0){
						diagBool = false;
					}
				} else {
					if (cellVal != 0.0){
						nonDiagBool = false;
					}
				}
			}
		}
		
		assertTrue(diagBool && nonDiagBool);
		
	}
	
	@Test
	public void test_11() {
		//Identity on non-square dataframe
		
		int cols = MAX_COLS;
		int rows = MAX_COLS - 1;
		double cellVal;
		boolean diagBool = true;
		boolean nonDiagBool = true;
		
		DataFrame dataFrame = filledDataFrame(cols, rows);
		dataFrame.identity();
		
		for (int i = 1; i <= cols; i++){
			for (int j = 1; j <= rows; j++){
				cellVal = dataFrame.getAt(i, j);
				if (i == j){
					if (cellVal != 1.0){
						diagBool = false;
					}
				} else {
					if (cellVal != 0.0){
						nonDiagBool = false;
					}
				}
			}
		}
		
		assertTrue(diagBool);
		assertTrue(nonDiagBool);
		
	}
	
	@Test
	public void test_12() {
		//Transpose on small dataframe
		
		int cols = MIN_COLS;
		int rows = MIN_ROWS;
		
		boolean rowShapeBool = true;
		boolean colShapeBool = true;
		boolean transBool = true;
		
		DataFrame dataFrame = filledDataFrame(cols, rows);
		DataFrame dataFrameTransposed = filledDataFrame(cols, rows);
		
		dataFrameTransposed.transpose();

		//Check if the shape of the transposed matrix is as expected
		if (dataFrame.getRows() != dataFrameTransposed.getCols()){
			rowShapeBool = false;
		}
		
		if (dataFrame.getCols() != dataFrameTransposed.getRows()){
			colShapeBool = false;
		}
		
		//Check if the values of the transposed matrix are as expected
		for (int i = 1; i <= dataFrame.getCols(); i++){
			for (int j = 1; j <= dataFrame.getRows(); j++){
				if (dataFrame.getAt(i,j) != dataFrameTransposed.getAt(j,i)) {
					transBool = false;
				}
			}
		}
		
		assertTrue(rowShapeBool);
		assertTrue(colShapeBool);
		assertTrue(transBool);
		
	}
	
	@Test
	public void test_13() {
		//Transpose on large dataframe
		
		int cols = MAX_COLS;
		int rows = MAX_VAR;
		
		boolean rowShapeBool = true;
		boolean colShapeBool = true;
		boolean transBool = true;
		
		DataFrame dataFrame = filledDataFrame(cols, rows);
		DataFrame dataFrameTransposed = filledDataFrame(cols, rows);
		
		dataFrameTransposed.transpose();

		//Check if the shape of the transposed matrix is as expected
		if (dataFrame.getRows() != dataFrameTransposed.getCols()){
			rowShapeBool = false;
		}
		
		if (dataFrame.getCols() != dataFrameTransposed.getRows()){
			colShapeBool = false;
		}
		
		//Check if the values of the transposed matrix are as expected
		for (int i = 1; i <= dataFrame.getCols(); i++){
			for (int j = 1; j <= dataFrame.getRows(); j++){
				//System.out.println("(" + i + ", " + j + ")");
				try {
					if (dataFrame.getAt(i,j) != dataFrameTransposed.getAt(j,i)) {
						//System.out.println("(" + i + ", " + j + ")");
						transBool = false;
					}
				}
				catch(Exception e) {
					transBool = false;
				}
			}
		}
		
		assertTrue(rowShapeBool);
		assertTrue(colShapeBool);
		assertTrue(transBool);
		
	}
	
	@Test
	public void test_14() {
		//Upper triangular on small, unique, non-linearly dependent and solvable SoE
		
		int numVar = 2;
		double newCellVal;
		boolean lowerTriBool = true;
		
		//Create a linear problem
		DataFrame dataFrame = basicLinearProblem(numVar);
		dataFrame.upperTriangular();

		//Check if lower triangular
		//Iterate over rows first, then cols in inner loop
		for (int j = 1; j <= numVar; j++){
			for (int i = 1; i < numVar + 1; i++){
				newCellVal = dataFrame.getAt(i,j);
				if (j > i){
					if (Math.abs(newCellVal) > ZERO_EQV){
						lowerTriBool = false;
					}
				}
			}
		}
		
		assertTrue(lowerTriBool);
		
	}
	
	@Test
	public void test_15() {
		//Upper triangular on large, unique, non-linearly dependent and solvable SoE
		
		int numVar = MAX_VAR;
		double newCellVal;
		boolean lowerTriBool = true;
		
		//Create a linear problem
		DataFrame dataFrame = basicLinearProblem(numVar);
		dataFrame.upperTriangular();

		//Check if lower triangular
		//Iterate over rows first, then cols in inner loop
		for (int j = 1; j <= numVar; j++){
			for (int i = 1; i < numVar + 1; i++){
				newCellVal = dataFrame.getAt(i,j);
				if (j > i){
					if (Math.abs(newCellVal) > ZERO_EQV){
						lowerTriBool = false;
					}
				}
			}
		}
		
		assertTrue(lowerTriBool);
		
	}
	
	@Test
	public void test_16() {
		//Upper Triangular on linearly dependent SoE
		
		int numVar = 10;
		double newCellVal;
		boolean lowerTriBool = true;
		
		//Create a linear problem
		DataFrame dataFrame = linearlyDependentProblem(numVar);
		dataFrame.upperTriangular();

		//Check if lower triangular
		//Iterate over rows first, then cols in inner loop
		for (int j = 1; j <= numVar; j++){
			for (int i = 1; i < numVar + 1; i++){
				newCellVal = dataFrame.getAt(i,j);
				if (j > i){
					if (Math.abs(newCellVal) > ZERO_EQV){
						lowerTriBool = false;
					}
				}
			}
		}
		
		assertTrue(lowerTriBool);
		
	}
	
	@Test
	public void test_17() {
		//Upper Triangular on non-unique SoE
		
		int numVar = 10;
		double newCellVal;
		boolean lowerTriBool = true;
		
		//Create a linear problem
		DataFrame dataFrame = nonUniqueLinearProblem(numVar);
		dataFrame.upperTriangular();

		//Check if lower triangular
		//Iterate over rows first, then cols in inner loop
		for (int j = 1; j <= numVar; j++){
			for (int i = 1; i < numVar + 1; i++){
				newCellVal = dataFrame.getAt(i,j);
				if (j > i){
					if (Math.abs(newCellVal) > ZERO_EQV){
						lowerTriBool = false;
					}
				}
			}
		}
		
		assertTrue(lowerTriBool);
		
	}
	
	@Test(expected = ArithmeticException.class)
	public void test_18() {
		//Upper Triangular on unsolvable SoE
		
		int numVar = 10;
		double newCellVal;
		boolean lowerTriBool = true;
		
		//Create a linear problem
		DataFrame dataFrame = unsolvableProblem(numVar);
		dataFrame.upperTriangular();

		//Check if lower triangular
		//Iterate over rows first, then cols in inner loop
		for (int j = 1; j <= dataFrame.getRows(); j++){
			for (int i = 1; i < dataFrame.getCols() + 1; i++){
				newCellVal = dataFrame.getAt(i,j);
				if (j > i){
					if (Math.abs(newCellVal) > ZERO_EQV){
						lowerTriBool = false;
					}
				}
			}
		}
		
		assertTrue(lowerTriBool);
		
	}

	@Test
	public void test_19() {
		//Row elimination on small, unique, non-linearly dependent and solvable, 
		//upper triangular SoE
		
		int numVar = 2;
		double newCellVal;
		boolean diagBool = true;
		boolean changedYVal = false;
		double yOld;
		double yNew;
		
		//Create a linear problem
		DataFrame dataFrame = upperTriangle(numVar);
		DataFrame rowElimDF = upperTriangle(numVar);
		rowElimDF.rowElimination();

		//Check if lower triangular
		//Check if the 'y' value has changed
		//Iterate over rows first, then cols in inner loop
		for (int j = 1; j <= numVar; j++){
			for (int i = 1; i < numVar + 1; i++){
				newCellVal = dataFrame.getAt(i,j);
				if (j > i || (i > j && i != numVar)){
					if (Math.abs(newCellVal) >= ZERO_EQV){
						diagBool = false;
					}
				}
			}
			//If none of the y value have changed, we want to flag this
			yOld = dataFrame.getAt(numVar + 1, j);
			yNew = rowElimDF.getAt(numVar + 1, j);
			if (yOld != yNew){
				changedYVal = true;
			}
		}
		
		assertTrue(diagBool);
		assertTrue(changedYVal);
		
	}
	
	@Test
	public void test_20() {
		//Row elimination on large, unique, non-linearly dependent and solvable, 
		//upper triangular SoE
		
		int numVar = MAX_VAR;
		double newCellVal;
		boolean diagBool = true;
		boolean changedYVal = false;
		double yOld;
		double yNew;
		
		//Create a linear problem
		DataFrame dataFrame = upperTriangle(numVar);
		DataFrame rowElimDF = upperTriangle(numVar);
		rowElimDF.rowElimination();

		//Check if lower triangular
		//Check if the 'y' value has changed
		//Iterate over rows first, then cols in inner loop
		for (int j = 1; j <= numVar; j++){
			for (int i = 1; i < numVar + 1; i++){
				newCellVal = rowElimDF.getAt(i,j);
				if (j > i || (i > j && i != numVar)){
					if (Math.abs(newCellVal) > ZERO_EQV){
						diagBool = false;
					}
				}
			}
			//If none of the y value have changed, we want to flag this
			yOld = dataFrame.getAt(numVar + 1, j);
			yNew = rowElimDF.getAt(numVar + 1, j);
			if (yOld != yNew){
				changedYVal = true;
			}
		}
		
		assertTrue(diagBool);
		assertTrue(changedYVal);
		
	}
	
	@Test(expected = ArithmeticException.class)
	public void test_21() {
		//Row elimination on non-upper triangular SoE
		
		int numVar = 10;
		double newCellVal;
		boolean diagBool = true;
		boolean changedYVal = false;
		double yOld;
		double yNew;
		
		//Create a non upper triangular problem
		DataFrame dataFrame = basicLinearProblem(numVar);
		DataFrame rowElimDF = basicLinearProblem(numVar);
		rowElimDF.rowElimination();

		//Check if lower triangular
		//Check if the 'y' value has changed
		//Iterate over rows first, then cols in inner loop
		for (int j = 1; j <= numVar; j++){
			for (int i = 1; i < numVar + 1; i++){
				newCellVal = rowElimDF.getAt(i,j);
				if (j > i || (i > j && i != numVar)){
					if (Math.abs(newCellVal) > ZERO_EQV){
						diagBool = false;
					}
				}
			}
			//If none of the y value have changed, we want to flag this
			yOld = dataFrame.getAt(numVar + 1, j);
			yNew = rowElimDF.getAt(numVar + 1, j);
			if (yOld != yNew){
				changedYVal = true;
			}
		}
		
		assertTrue(diagBool);
		assertTrue(changedYVal);
		
	}
	
	@Test(expected = ArithmeticException.class)
	public void test_22() {
		//Row elimination on non-unique, upper triangular augmented matrix
		
		int numVar = 10;
		double newCellVal;
		boolean diagBool = true;
		boolean changedYVal = false;
		double yOld;
		double yNew;
		
		//Create a non unique problem
		DataFrame dataFrame = nonUniqueLinearProblem(numVar);
		DataFrame rowElimDF = nonUniqueLinearProblem(numVar);
		
		for (int i = 1; i <= dataFrame.getCols() + 1; i++){
			for (int j = MIN_ROWS; j <= dataFrame.getRows(); j++){
				if (j > i){
					dataFrame.setAt(i, j, 0.0);
					rowElimDF.setAt(i, j, 0.0);
				}
			}
		}
		
		rowElimDF.rowElimination();

		//Check if lower triangular
		//Check if the 'y' value has changed
		//Iterate over rows first, then cols in inner loop
		for (int j = 1; j <= numVar; j++){
			for (int i = 1; i < numVar + 1; i++){
				newCellVal = rowElimDF.getAt(i,j);
				if (j > i || (i > j && i != numVar)){
					if (Math.abs(newCellVal) > ZERO_EQV){
						diagBool = false;
					}
				}
			}
			//If none of the y value have changed, we want to flag this
			yOld = dataFrame.getAt(numVar + 1, j);
			yNew = rowElimDF.getAt(numVar + 1, j);
			if (yOld != yNew){
				changedYVal = true;
			}
		}
		
		assertTrue(diagBool);
		assertTrue(changedYVal);
		
	}
	
	@Test(expected = ArithmeticException.class)
	public void test_23() {
		//Row elimination on linearly dependent SoE
		
		int numVar = 10;
		double newCellVal;
		boolean diagBool = true;
		boolean changedYVal = false;
		double yOld;
		double yNew;
		
		//Create a non unique problem
		DataFrame dataFrame = linearlyDependentProblem(numVar);
		DataFrame rowElimDF = linearlyDependentProblem(numVar);
		
		for (int i = 1; i <= dataFrame.getCols() + 1; i++){
			for (int j = MIN_ROWS; j <= dataFrame.getRows(); j++){
				if (j > i){
					dataFrame.setAt(i, j, 0.0);
					rowElimDF.setAt(i, j, 0.0);
				}
			}
		}
		
		rowElimDF.rowElimination();

		//Check if lower triangular
		//Check if the 'y' value has changed
		//Iterate over rows first, then cols in inner loop
		for (int j = 1; j <= numVar; j++){
			for (int i = 1; i < numVar + 1; i++){
				newCellVal = rowElimDF.getAt(i,j);
				if (j > i || (i > j && i != numVar)){
					if (Math.abs(newCellVal) > ZERO_EQV){
						diagBool = false;
					}
				}
			}
			//If none of the y value have changed, we want to flag this
			yOld = dataFrame.getAt(numVar + 1, j);
			yNew = rowElimDF.getAt(numVar + 1, j);
			if (yOld != yNew){
				changedYVal = true;
			}
		}
		
		assertTrue(diagBool);
		assertTrue(changedYVal);
		
	}
	
	@Test(expected = ArithmeticException.class)
	public void test_24() {

		//Row elimination on non-solvable SoE
		
		int numVar = 10;
		double newCellVal;
		boolean diagBool = true;
		boolean changedYVal = false;
		double yOld;
		double yNew;
		
		//Create a non unique problem
		DataFrame dataFrame = unsolvableProblem(numVar);
		DataFrame rowElimDF = unsolvableProblem(numVar);
		
		for (int i = 1; i <= dataFrame.getCols() + 1; i++){
			for (int j = MIN_ROWS; j <= dataFrame.getRows(); j++){
				if (j > i){
					dataFrame.setAt(i, j, 0.0);
					rowElimDF.setAt(i, j, 0.0);
				}
			}
		}
		
		rowElimDF.rowElimination();

		//Check if lower triangular
		//Check if the 'y' value has changed
		//Iterate over rows first, then cols in inner loop
		for (int j = 1; j <= numVar; j++){
			for (int i = 1; i < numVar + 1; i++){
				newCellVal = rowElimDF.getAt(i,j);
				if (j > i || (i > j && i != numVar)){
					if (Math.abs(newCellVal) > ZERO_EQV){
						diagBool = false;
					}
				}
			}
			//If none of the y value have changed, we want to flag this
			yOld = dataFrame.getAt(numVar + 1, j);
			yNew = rowElimDF.getAt(numVar + 1, j);
			if (yOld != yNew){
				changedYVal = true;
			}
		}
		
		assertTrue(diagBool);
		assertTrue(changedYVal);
		
	}

	@Test
	public void test_25() {
		//Ones reduce on small, row eliminated form, solvable SoE
		
		int numVar = 2;
		double newCellVal;
		boolean onesReduceFormatBool = true;
		boolean correctSolutionBool = true;
		
		//Create a ones reduced format matrix
		DataFrame dataFrame = rowEliminated(numVar);
		DataFrame reducedDataFrame = rowEliminated(numVar);
		reducedDataFrame.onesReduce();

		//Check if correct form
		//Iterate over rows first, then cols in inner loop
		for (int j = 1; j <= numVar; j++){
			for (int i = 1; i < numVar + 1; i++){
				newCellVal = reducedDataFrame.getAt(i,j);
				if (j == i){
					if (Math.abs(newCellVal) < 1 - ZERO_EQV 
							|| Math.abs(newCellVal) > 1 + ZERO_EQV ){
						onesReduceFormatBool = false;
					}
				} else if (Math.abs(newCellVal) > ZERO_EQV){
					onesReduceFormatBool = false;
				}
			}
			if (reducedDataFrame.getAt(numVar + 1, j)
					!= dataFrame.getAt(numVar + 1, j) / dataFrame.getAt(j, j)){
				correctSolutionBool = false;
			}
			
		}
		
		assertTrue(onesReduceFormatBool);
		assertTrue(correctSolutionBool);
		
	}
	
	@Test
	public void test_26() {
		//Ones reduce on large, row eliminated form, solvable SoE
		
		int numVar = MAX_VAR;
		double newCellVal;
		boolean onesReduceFormatBool = true;
		boolean correctSolutionBool = true;
		
		//Create a ones reduced format matrix
		DataFrame dataFrame = rowEliminated(numVar);
		DataFrame reducedDataFrame = rowEliminated(numVar);
		reducedDataFrame.onesReduce();

		//Check if lower triangular
		//Iterate over rows first, then cols in inner loop
		for (int j = 1; j <= numVar; j++){
			for (int i = 1; i < numVar + 1; i++){
				newCellVal = reducedDataFrame.getAt(i,j);
				if (j == i){
					if (Math.abs(newCellVal) < 1 - ZERO_EQV 
							|| Math.abs(newCellVal) > 1 + ZERO_EQV ){
						onesReduceFormatBool = false;
					}
				} else if (Math.abs(newCellVal) > ZERO_EQV){
					onesReduceFormatBool = false;
				}
			}
			if (reducedDataFrame.getAt(numVar + 1, j)
					!= dataFrame.getAt(numVar + 1, j) / dataFrame.getAt(j, j)){
				correctSolutionBool = false;
			}
			
		}
		
		assertTrue(onesReduceFormatBool);
		assertTrue(correctSolutionBool);
				
	}
	
	@Test(expected = ArithmeticException.class)
	public void test_27() {
		//Ones reduce on non-solvable SoE
		
		int numVar = 10;
		double newCellVal;
		boolean onesReduceFormatBool = true;
		boolean correctSolutionBool = true;
		
		//Create a ones reduced format matrix
		DataFrame dataFrame = rowEliminated(numVar);
		DataFrame reducedDataFrame = rowEliminated(numVar);
		
		//Set a coefficient to 0 to make SoE non-Solvable
		reducedDataFrame.setAt(2, 2, 0.0);
		
		reducedDataFrame.onesReduce();

		//Check if correct form
		//Iterate over rows first, then cols in inner loop
		for (int j = 1; j <= numVar; j++){
			for (int i = 1; i < numVar + 1; i++){
				newCellVal = reducedDataFrame.getAt(i,j);
				if (j == i){
					if (Math.abs(newCellVal) < 1 - ZERO_EQV 
							|| Math.abs(newCellVal) > 1 + ZERO_EQV ){
						onesReduceFormatBool = false;
					}
				} else if (Math.abs(newCellVal) > ZERO_EQV){
					onesReduceFormatBool = false;
				}
			}
			if (reducedDataFrame.getAt(numVar + 1, j)
					!= dataFrame.getAt(numVar + 1, j) / dataFrame.getAt(j, j)){
				correctSolutionBool = false;
			}
			
		}
		
		assertTrue(onesReduceFormatBool);
		assertTrue(correctSolutionBool);
					
	}
	
	@Test(expected = ArithmeticException.class)
	public void test_28() {
		//Ones reduce on non row eliminated form matrix
		
		int numVar = 10;
		double newCellVal;
		boolean onesReduceFormatBool = true;
		boolean correctSolutionBool = true;
		
		//Create a ones reduced format matrix
		DataFrame dataFrame = basicLinearProblem(numVar);
		DataFrame reducedDataFrame = basicLinearProblem(numVar);
		reducedDataFrame.onesReduce();

		//Check if correct form
		//Iterate over rows first, then cols in inner loop
		for (int j = 1; j <= numVar; j++){
			for (int i = 1; i < numVar + 1; i++){
				newCellVal = reducedDataFrame.getAt(i,j);
				if (j == i){
					if (Math.abs(newCellVal) < 1 - ZERO_EQV 
							|| Math.abs(newCellVal) > 1 + ZERO_EQV ){
						onesReduceFormatBool = false;
					}
				} else if (Math.abs(newCellVal) > ZERO_EQV){
					onesReduceFormatBool = false;
				}
			}
			if (reducedDataFrame.getAt(numVar + 1, j)
					!= dataFrame.getAt(numVar + 1, j) / dataFrame.getAt(j, j)){
				correctSolutionBool = false;
			}
			
		}
		
		assertTrue(onesReduceFormatBool);
		assertTrue(correctSolutionBool);
					
	}
	
}
