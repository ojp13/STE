package statistics_test;

import static org.junit.Assert.*;

import org.junit.Test;

import statistics.DataFrame;

public class TestBasicTest {
	static final int MAX_ROWS = 2000;
	static final int MAX_COLS = 40;
	static final int MAX_VAR = MAX_COLS - 1;
	static final int MIN_ROWS = 1;
	static final int MIN_COLS = 1;
	
	private static DataFrame filledDataFrame(int cols, int rows, double value){
		
		DataFrame dataFrame = new DataFrame(cols,rows);
		
		for (int i = MIN_COLS; i <= cols; i++){
			for (int j = MIN_ROWS; j <= rows; j++){
				dataFrame.setAt(i, j, value);
			}
		}
		return dataFrame;
	}
	
	private static DataFrame basicLinearProblem(int numVar){
		DataFrame dataFrame = new DataFrame(numVar + 1,numVar);
		int cols = dataFrame.getCols();
		int rows = dataFrame.getRows();
		
		for (int i = MIN_COLS; i <= cols; i++){
			if (i == cols){
				dataFrame.setHeader(i, "y");
			} else {
				dataFrame.setHeader(i, "x" + i);
			}
			for (int j = MIN_ROWS; j <= rows; j++){
				dataFrame.setAt(i, j, i * j + i);
			}
		}
		return dataFrame;
	}
	
	private static DataFrame upperTriangle(int numVar){
		DataFrame dataFrame = new DataFrame(numVar + 1, numVar);
		int cols = dataFrame.getCols();
		int rows = dataFrame.getRows();
		
		for (int i = MIN_COLS; i <= cols; i++){
			if (i == cols){
				dataFrame.setHeader(i, "y");
			} else {
				dataFrame.setHeader(i, "x" + i);
			}
			for (int j = MIN_ROWS; j <= rows; j++){
				if (i >= j){
					dataFrame.setAt(i, j, i * j + i);
				} else {
					dataFrame.setAt(i, j, 0);
				}
			}
		}
		return dataFrame;
	}
	
	private static DataFrame rowElimination(int numVar){
		DataFrame dataFrame = new DataFrame(numVar + 1,numVar);
		int cols = dataFrame.getCols();
		int rows = dataFrame.getRows();
		
		for (int i = MIN_COLS; i <= cols; i++){
			if (i == cols){
				dataFrame.setHeader(i, "y");
			} else {
				dataFrame.setHeader(i, "x" + i);
			}
			for (int j = MIN_ROWS; j <= rows; j++){
				if (i == j || i == cols){
					dataFrame.setAt(i, j, i * j + i);
				} else {
					dataFrame.setAt(i, j, 0);
				}
			}
		}
		return dataFrame;
	}
	
	private static DataFrame linearlyDependentProblem(int numVar){
		DataFrame dataFrame = new DataFrame(numVar + 1, numVar);
		int cols = dataFrame.getCols();
		int rows = dataFrame.getRows();
		
		for (int i = MIN_COLS; i <= cols; i++){
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
		
		for (int i = MIN_COLS; i <= cols; i++){
			if (i == cols){
				dataFrame.setHeader(i, "y");
			} else {
				dataFrame.setHeader(i, "x" + i);
			}
			for (int j = MIN_ROWS; j <= rows; j++){
				dataFrame.setAt(i, j, i * j + i % j);
			}
		}
		return dataFrame;
	}
	
	public static void main(String[] args){
		
		DataFrame smallDataFrame = filledDataFrame(MIN_COLS, MIN_ROWS, 100.0);
		DataFrame medDataFrame = filledDataFrame(5, 5, 100);
		DataFrame bigDataFrame = filledDataFrame(MAX_COLS, MAX_ROWS, 100.0);
		DataFrame smallLinearProblem = basicLinearProblem(1);
		DataFrame medLinearProblem = basicLinearProblem(5);
		DataFrame bigLinearProblem = basicLinearProblem(MAX_VAR);
		DataFrame smallUpperTriangle = upperTriangle(1);
		DataFrame medUpperTriangle = upperTriangle(5);
		DataFrame bigUpperTriangle = upperTriangle(MAX_VAR);
		DataFrame smallRowElimination = rowElimination(1);
		DataFrame medRowElimination = rowElimination(5);
		DataFrame bigRowElimination = rowElimination(MAX_VAR);
		DataFrame linearlyDependentProblem = linearlyDependentProblem(10);
		DataFrame unsolvableProblem = unsolvableProblem(10);
		
		
		unsolvableProblem.show();
		medRowElimination.show();
	}
	
	

	@Test
	public void test() {
		DataFrame dataFrame = new DataFrame(4,3);
		
		// 1st equation
		dataFrame.setAt(1, 1, 1.0);
		dataFrame.setAt(2, 1, 2.0);
		dataFrame.setAt(3, 1, 3.0);
		dataFrame.setAt(4, 1, -7.0);

		// 2nd equation
		dataFrame.setAt(1, 2, 2.0);
		dataFrame.setAt(2, 2, -3.0);
		dataFrame.setAt(3, 2, -5.0);
		dataFrame.setAt(4, 2, 9.0);
		
		// 3rd Equation
		dataFrame.setAt(1, 3, -6.0);
		dataFrame.setAt(2, 3, -8.0);
		dataFrame.setAt(3, 3, 1.0);
		dataFrame.setAt(4, 3, -22.0);
		
		dataFrame.setHeader(1, "Column 1");
		dataFrame.setHeader(2, "Column 2");
		dataFrame.setHeader(3, "Column 3");
		dataFrame.setHeader(4, "Values");
		
		System.out.println("Original Matrix");
		dataFrame.show();
		
		System.out.println("After upper triangulation");
		dataFrame.upperTriangular();
		dataFrame.show();	
		
		System.out.println("After row elimination");
		dataFrame.rowElimination();
		dataFrame.show();
		
		System.out.println("After reduction to ones");
		dataFrame.onesReduce();
		dataFrame.show();
		
		double result1 = dataFrame.getAt(4, 1);
		double result2 = dataFrame.getAt(4, 2);
		double result3 = dataFrame.getAt(4, 3);
		
		double expectedAnswer1 = -1.00;
		double expectedAnswer2 = 3.00;
		double expectedAnswer3 = -4.00;
		
		double error = 0.0001;
		
		assertTrue(expectedAnswer1 - error < result1 && result1 < expectedAnswer1 + error);
		assertTrue(result1 == expectedAnswer1);
	}
	
	@Test
	public void test2() {
		DataFrame dataFrame = new DataFrame(4,3);
		
		// 1st equation
		dataFrame.setAt(1, 1, 1.0);
		dataFrame.setAt(2, 1, 2.0);
		dataFrame.setAt(3, 1, 3.0);
		dataFrame.setAt(4, 1, -7.0);

		// 2nd equation
		dataFrame.setAt(1, 2, 2.0);
		dataFrame.setAt(2, 2, -3.0);
		dataFrame.setAt(3, 2, -5.0);
		dataFrame.setAt(4, 2, 9.0);
		
		// 3rd Equation
		dataFrame.setAt(1, 3, -6.0);
		dataFrame.setAt(2, 3, -8.0);
		dataFrame.setAt(3, 3, 1.0);
		dataFrame.setAt(4, 3, -22.0);
		
		dataFrame.setHeader(1, "Column 1");
		dataFrame.setHeader(2, "Column 2");
		dataFrame.setHeader(3, "Column 3");
		dataFrame.setHeader(4, "Values");
		
		System.out.println("Original Matrix");
		dataFrame.show();
		
		System.out.println("After upper triangulation");
		dataFrame.upperTriangular();
		dataFrame.show();	
		
		System.out.println("After row elimination");
		dataFrame.rowElimination();
		dataFrame.show();
		
		System.out.println("After reduction to ones");
		dataFrame.onesReduce();
		dataFrame.show();
		
		double result1 = dataFrame.getAt(4, 1);
		double result2 = dataFrame.getAt(4, 2);
		double result3 = dataFrame.getAt(4, 3);
		
		double expectedAnswer1 = -1.00;
		double expectedAnswer2 = 3.00;
		double expectedAnswer3 = -4.00;
		
		double error = 0.0001;
		
		assertTrue(result1 == expectedAnswer1);
	}
	

}
