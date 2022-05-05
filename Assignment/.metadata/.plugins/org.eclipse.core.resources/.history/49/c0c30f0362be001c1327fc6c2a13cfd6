package statistics;


public class DataFrame {

	static final int MAX_ROWS = 2000;
	static final int MAX_COLS = 40;
	static final int MIN_ROWS = 1;
	static final int MIN_COLS = 1;
	static final int DEFAULT_NUM_WIDTH = 7;
	static final int DEFAUlT_NUM_PRECISION = 2;
	static final int MIN_WIDTH = 3;
	static final int MAX_WIDTH = 30;
	static final int MIN_PRECISION = 0;
	static final int MAX_PRECISION = 10;

	int cols, rows;
	double[][] theData;
	String[] headerNames;
	NumberFormat[] numberFormat;
	
	
	public DataFrame(int cols, int rows)  throws ArithmeticException {

		if ( cols > MAX_COLS ) {
			throw new ArithmeticException("Error: Attempt to create Data Frame with too many columns");
		} else if ( rows > MAX_ROWS ) {
			throw new ArithmeticException("Error: Attempt to create Data Frame with too many rows");
		} else if ( rows < MIN_COLS ) {
			throw new ArithmeticException("Error: Attempt to create Data Frame with too few columns");
		} else if ( rows < MIN_ROWS ) {
			throw new ArithmeticException("Error: Attempt to create Data Frame with too few rows");
		} else {		
			this.cols = cols;
			this.rows = rows;
			this.theData = new double[rows+1][cols+1];      // Annoying - Java has zero-based arrays
			this.headerNames = new String[cols+1];          // saves the names of each column
			this.numberFormat = new NumberFormat[ cols+1 ]; // Array of references to the formats for columns
			// Initialise the header names
			for (int item = 1; item <=cols; item++) {
				this.headerNames[item] = "<Empty>";
				this.numberFormat[item] = new NumberFormat(DEFAULT_NUM_WIDTH, DEFAUlT_NUM_PRECISION);           // Used to format the data display in the colum
				
			}
		}
	}


	public void checkCol( int theCol, String callingRoutine ) throws ArithmeticException{
		// Check that the parameter does not exceed the current data frame size
		
		if ( theCol > cols ) {
			throw new ArithmeticException("Error in " + callingRoutine + ": reference to column greater than current number of columns in data frame");
		}
		if ( theCol < MIN_ROWS ) {
			throw new ArithmeticException("Error in " + callingRoutine + ": reference to column less than 1");
		}
	}

	public void checkRow( int theRow, String callingRoutine ) throws ArithmeticException{
		// Check that the parameter does not exceed the current data frame size	
		if ( theRow > rows ) {
			throw new ArithmeticException("Error in " + callingRoutine + ": reference to row greater than curent number of rows in data frame");
		}
		if ( theRow < MIN_ROWS ) {
			throw new ArithmeticException("Error in " + callingRoutine + ": reference to row less than 1");
		}
	}

	public void checkMaxCol( int theCol, String callingRoutine ) throws ArithmeticException{
		// Check that the parameter does not exceed the maximum data frame size
		if ( theCol > MAX_COLS ) {
			throw new ArithmeticException("Error in " + callingRoutine + ": reference to column greater than maximum columns in data frame");
		}
		if ( theCol < MIN_COLS ) {
			throw new ArithmeticException("Error in " + callingRoutine + ": reference to column less than 1");
		}
	}

	public void checkMaxRow( int theRow, String callingRoutine ) throws ArithmeticException{
		// Check that the parameter does not exceed the maximum data frame size	
		if ( theRow > MAX_ROWS ) {
			throw new ArithmeticException("Error in " + callingRoutine + ": reference to row greater than maximum number of rows in data frame");
		}
		if ( theRow < MIN_ROWS ) {
			throw new ArithmeticException("Error in " + callingRoutine + ": reference to row less than 1");
		}
	}
	
	public void checkRowOrder( int fromRow, int toRow, String callingRoutine )  throws ArithmeticException{
		// Check the order of the parameters .. 'from' row must be to the left of 'to' row
		if ( fromRow > toRow ) {
			throw new ArithmeticException("Error in " + callingRoutine + ": 'from' row greater 'to' row");
		}
	}
	
	
	public void checkColOrder( int fromCol, int toCol, String callingRoutine )  throws ArithmeticException{
		// Check the order of the parameters .. 'from' row must be to the left of 'to' row		
		if ( fromCol > toCol ) {
			throw new ArithmeticException("Error in " + callingRoutine + ": 'from' column greater 'to' column");
		}
	}
	
	public void checkVal( double theValue, String callingRoutine ) throws ArithmeticException {
		// Check that we have a non-infinite value 
	     if(theValue == Double.POSITIVE_INFINITY){
	          throw new ArithmeticException("Error in " + callingRoutine + ": 'Attempt to store a Positive Infinity value'");
	     } else if (theValue == Double.NEGATIVE_INFINITY){
	          throw new ArithmeticException("Error in " + callingRoutine + ": 'Attempt to store a Negative Infinity value'");
	     } else if (Double.isNaN(theValue)) {
	          throw new ArithmeticException("Error in " + callingRoutine + ": 'Attempt to store a NaN value'");
	     }
		
	}
	
	public int getCols() {
		return cols;
	}

	public void setCols(int cols) throws ArithmeticException{
		checkMaxCol(cols, "setCols");
		this.cols = cols;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) throws ArithmeticException {
		checkMaxRow(rows, "setRows");
		this.rows = rows;
	}

	public void setAt(int theCol, int theRow, double theValue) {
		
		checkCol(theCol, "setAt");
		checkRow(theRow, "setAt");
		checkVal(theValue, "SetAt");
		
		theData[theRow][theCol] = theValue;
	}
	
	public double getAt(int theCol, int theRow) {
		// Gets the value at a specific location
		checkCol(theCol, "setAt");
		checkRow(theRow, "setAt");
		
		return( theData[theRow][theCol] );
	}	
	
	public void setHeader(int theCol, String columnName) throws ArithmeticException {
		checkCol(theCol, "setHeader");
		headerNames[theCol] = columnName;
		numberFormat[theCol].width = DEFAULT_NUM_WIDTH;
		numberFormat[theCol].precision = DEFAUlT_NUM_PRECISION;
	}
	
	public int getColIndex( String columnName ) {
		// Returns the index of a column with a given header name.
		// If no such header name then returns zero
		
		int theIndexCol = 0;
		
		for (int col = 1; col <= cols ; col++ ) {
			if ( headerNames[col] == columnName) {
				theIndexCol = col;
			}
		}
		
		return( theIndexCol);
	}

	// Routines to set column widths and precision
	public void setColWidth(int theCol, int theWidth) throws ArithmeticException {
		checkCol(theCol, "setColWidth");
		if (theWidth > MAX_WIDTH) {
			throw new ArithmeticException("Error in setColWidth: 'Attempt to set column width too large'");
		} else if (theWidth < MIN_WIDTH) {
			throw new ArithmeticException("Error in setColWidth: 'Attempt to set column width too large'");
		} else {
			numberFormat[theCol].width = theWidth;
		}
	}

	public void setColprecision(int theCol, int thePrecision) throws ArithmeticException {
		checkCol(theCol, "setColWidth");
		if (thePrecision > MAX_PRECISION) {
			throw new ArithmeticException("Error in setColWidth: 'Attempt to set precision too high'");
		} else if (thePrecision < MIN_PRECISION) {
			throw new ArithmeticException("Error in setColWidth: 'Attempt to set precision too low'");
		} else {
			numberFormat[theCol].width = thePrecision;
		}
	}

	
	
	// Routines to sum blocks 
	public double sum(int fromCol, int toCol, int fromRow, int toRow){
	    // Returns the sum of a complete block
		checkCol(fromCol, "Sum");
		checkCol(toCol, "Sum");
		checkColOrder(fromCol, toCol, "Sum");
		
		checkRow(fromRow, "Sum");
		checkRow(toRow, "Sum");
		checkRowOrder(fromRow, toRow, "Sum");
		
		double total = 0;
		
		for (int row = fromRow; row <= toRow; row++) {
			for (int col = fromCol; col <= toCol; col++) {
				total = total + theData[row][col];
			}
		}	
		
		return ( total );
	
	}
	
	public double sumCol(int theCol, int fromRow, int toRow) {
		// Return the sum of a column between two given rows
		checkCol(theCol, "sumCol");

		checkRow(fromRow, "sumCol");
		checkRow(toRow, "sumCol");
		checkRowOrder(fromRow, toRow, "sumCol");
		
		return (sum(theCol, theCol, fromRow, toRow));
	}

	public double sumCol(int col) {
		// Return the sum of a column (all rows)
		checkCol(col, "sumCol");
		return (sum(col, col, 1, rows));
	}
	
	public double sumCol( String columnName ) throws ArithmeticException {
		// Return the sum of a column (all rows)

		double theColSum = 0.0;
		
		int theIndexCol = this.getColIndex( columnName);
		if ( theIndexCol > 0 ) {
			theColSum = sum(theIndexCol, theIndexCol, 1, rows);
		}
		else {
			throw new ArithmeticException("Error in sumCol : Column name does not exist");
		}
			
		return (theColSum);
	}

	public double sumCol( String columnName, int fromRow, int toRow ) throws ArithmeticException {
		// Return the sum of a column (all rows)

		double theColSum = 0.0;
		
		checkRow(fromRow, "sumCol");
		checkRow(toRow, "sumCol");
		checkRowOrder(fromRow, toRow, "sumCol");
		
		int theIndexCol = this.getColIndex( columnName);
		if ( theIndexCol > 0 ) {
			theColSum = sum(theIndexCol, theIndexCol, fromRow, toRow); 
		}
		else {
			throw new ArithmeticException("Error in sumCol : Column name does not exist");
		}
			
		return (theColSum);
	}	

	public double sumRow(int fromCol, int toCol, int theRow) {
		// Return the sum of a row between two given rows
		checkCol(fromCol, "sumRow");
		checkCol(toCol, "sumRow");
		checkRow(theRow, "sumRow");
		checkColOrder(fromCol, toCol, "sumRow");
		
		return (sum(fromCol, toCol, theRow, theRow));
	}
	
	public double sumRow(int row) {
		// Return the sum of a row (all columns)
		checkRow(row, "sumRow");
		return (sum(1, cols, row, row));
	}
	

	
	// Routines to set areas of the data-frame with Random numbers
	public void setBlockRandRange(int fromCol, int toCol, int fromRow, int toRow, int lower, int upper) throws ArithmeticException {
		
		// error check the row and column limits
		checkCol(fromCol, "setBlockRandRange");
		checkCol(toCol, "setBlockRandRange");
		checkRow(fromRow, "setBlockRandRange");
		checkRow(toRow, "setBlockRandRange");
		
		checkColOrder(fromCol, toCol, "setBlockRandRange");
		checkRowOrder(fromRow, toRow, "setBlockRandRange");		
		
		// Set that block with Random values	
		for (int row = fromRow; row <= toRow; row++) {
			for (int col = fromCol; col <= toCol; col++) {
				theData[row][col] = lower + (Math.random() * (upper - lower));
			}
		}	
		
	}	
	

	public void setBlockRand(int fromCol, int toCol, int fromRow, int toRow) throws ArithmeticException {
		setBlockRandRange( fromCol, toCol, fromRow, toRow, 0, 1);
	}
	
	public void setBlockZeroes(int fromCol, int toCol, int fromRow, int toRow) throws ArithmeticException {
		
		// error check the row and column limits
		checkCol(fromCol, "setBlockZeroes");
		checkCol(toCol, "setBlockZeroes");
		checkRow(fromRow, "setBlockZeroes");
		checkRow(toRow, "setBlockZeroes");
		
		checkColOrder(fromCol, toCol, "setBlockZeroes");
		checkRowOrder(fromRow, toRow, "setBlockZeroes");		
		
		// Set that block with Random values	
		for (int row = fromRow; row <= toRow; row++) {
			for (int col = fromCol; col <= toCol; col++) {
				theData[row][col] = 0;
			}
		}	
		
	}	

	
	
	// Routines to display data-frames
	
	public void showBlock(int fromCol, int toCol, int fromRow, int toRow) throws ArithmeticException {
		// Show a given number of columns and a given number of rows from a data-frame

		
		// error check the row and column limits
		checkCol(fromCol, "setBlockRandRange");
		checkCol(toCol, "setBlockRandRange");
		checkRow(fromRow, "setBlockRandRange");
		checkRow(toRow, "setBlockRandRange");
		
		checkColOrder(fromCol, toCol, "setBlockRandRange");
		checkRowOrder(fromRow, toRow, "setBlockRandRange");			
		
		
		int colWidth;
		int precision;

		// print the headers
		for (int col = fromCol; col <= toCol; col++) {
			System.out.print(headerNames[col]);
			System.out.print(", ");			
		}
		System.out.println();

		// print the data
		for (int row = fromRow; row <= toRow; row++) {
			for (int col = fromCol; col <= toCol; col++) {
				colWidth = numberFormat[col].width;
				precision = numberFormat[col].precision;
				String format = "%" + colWidth + "." + precision + "f";
				System.out.printf(format,theData[row][col]);
				System.out.print(", ");		
			}
			System.out.println();
		}
	}
	
	
	
	public void showCols(int fromCol, int toCol) throws ArithmeticException {
		// Show only a given set of columns
		showBlock(fromCol, toCol,1, rows);
	}
	
	public void showRows(int fromRow, int toRow) throws ArithmeticException {		
		// Show only a given set of rows
		showBlock(1, cols, fromRow, toRow);
	}
	
	public void show() {
		// Show all rows and columns of a data-frame
		showBlock(1, cols, 1, rows);
	}
	
	
	public String toString(int fromCol, int toCol, int fromRow, int toRow, boolean includeHeaders) throws ArithmeticException {
		// Produce a string from a given number of columns and a given number of rows from a data-frame

		
		// error check the row and column limits
		checkCol(fromCol, "toString");
		checkCol(toCol, "toString");
		checkRow(fromRow, "toString");
		checkRow(toRow, "toString");
		
		checkColOrder(fromCol, toCol, "toString");
		checkRowOrder(fromRow, toRow, "toString");			
		
		
		int colWidth;
		int precision;

		String resultStr = "";
		
		if (includeHeaders) {
			// resultStr will need to include the headers
			for (int col = fromCol; col <= toCol; col++ ) {
				resultStr = resultStr + headerNames[ col ] + ",";
			}
		}

		for (int row = fromRow; row <= toRow; row++) {
			resultStr = resultStr + "[";
			for (int col = fromCol; col <= toCol; col++) {
				colWidth = numberFormat[col].width;
				precision = numberFormat[col].precision;
				String format = "%" + colWidth + "." + precision + "f";
				resultStr = resultStr + String.format(format, theData[row][col]);
				if (col<toCol){
					resultStr = resultStr + ",";
				}
			}
			resultStr = resultStr + "]";
		}
	return(resultStr);
	}
	
	
	public void deleteCol(int theCol) {
		
		checkCol(theCol, "deleteCol");

			
		for (int col = theCol; col<cols; col++) {
			headerNames[col] = headerNames[col+1];
			for (int row = 1; row <= rows; row++) {
				theData[row][col] = theData[row][col+1];
			}
		}
		cols = cols - 1;
	}	
	
	
	public void insertCol(int theCol) {
		
		checkCol(theCol, "insertCol");
		checkMaxCol(cols+1,"insertCol");

		if (cols < MAX_COLS) {
			cols = cols + 1;
		}
		
		
		// Copy the data from the upper colums one to the left
		for (int col = cols-1; col>theCol; col--) {
			headerNames[col] = headerNames[col-1];
			for (int row = 1; row <= rows; row++) {
				theData[row][col] = theData[row][col-1];
			}
		}
		// Put a header in
		headerNames[theCol] = "<Empty>";
		// Put zeros into that newly created column
		for (int row = 1; row <= rows; row++){
			theData[row][theCol] = 0.0;
		}
		
	}

	public void deleteRow(int theRow) {
		
		checkRow(theRow, "deleteRow");

			
		for (int row = theRow; row<rows; row++) {
			for (int col = 1; col <= cols; col++) {
				theData[row][col] = theData[row+1][col];
			}
		}
		rows = rows - 1;
	}	

	public void insertRow(int theRow) {
		
		checkRow(theRow, "insertRow");
		checkMaxRow(theRow+1,"insertRow");
		
		if (rows < MAX_ROWS) {
			rows = rows + 1;
		}
		
		// Copy the data from the upper rows one row upwards
		for (int row = rows-1; row>theRow; row--) {
			for (int col = 1; col <= cols; col++) {
				theData[row][col] = theData[row-1][col];
			}
		}
		// Put zeros into that newly created row
		for (int col = 1; col <= cols; col++){
			theData[theRow][col] = 0.0;
		}
		
	}
	
	// Some useful arithmetic operations
	public void add(int fromCol, int toCol, int fromRow, int toRow, double aNumber) {
		// Adds a number to every cell within a defined range
		
		// error check the row and column limits
		checkCol(fromCol, "toString");
		checkCol(toCol, "toString");
		checkRow(fromRow, "toString");
		checkRow(toRow, "toString");
		
		checkColOrder(fromCol, toCol, "toString");
		checkRowOrder(fromRow, toRow, "toString");	
		
		for (int row = fromRow; row <= toRow; row++) {
			for (int col = fromCol; col <= toCol; col++) {
				theData[row][col] = theData[row][col] + aNumber;
				}
			}
	}
	
	public void add(double aNumber) {
		// Adds a number to every cell in a dataframe
		add(MIN_ROWS, rows, MIN_COLS, cols, aNumber);
	}	
	
	public void subtract(int fromCol, int toCol, int fromRow, int toRow, double aNumber) {
		// Adds a number to every cell within a defined range
		
		// error check the row and column limits
		checkCol(fromCol, "toString");
		checkCol(toCol, "toString");
		checkRow(fromRow, "toString");
		checkRow(toRow, "toString");
		
		checkColOrder(fromCol, toCol, "toString");
		checkRowOrder(fromRow, toRow, "toString");	
		
		for (int row = fromRow; row <= toRow; row++) {
			for (int col = fromCol; col <= toCol; col++) {
				theData[row][col] = theData[row][col] + aNumber;
				}
			}
	}
	
	public void subtract(double aNumber) {
		// Adds a number to every cell in a dataframe
		add(MIN_ROWS, rows, MIN_COLS, cols, -aNumber);
	}	

	
	public void mult(int fromCol, int toCol, int fromRow, int toRow, double aNumber) {
		// Multiplies a number to every cell within a defined range
		
		// error check the row and column limits
		checkCol(fromCol, "toString");
		checkCol(toCol, "toString");
		checkRow(fromRow, "toString");
		checkRow(toRow, "toString");
		
		checkColOrder(fromCol, toCol, "toString");
		checkRowOrder(fromRow, toRow, "toString");	
		
		for (int row = fromRow; row <= toRow; row++) {
			for (int col = fromCol; col <= toCol; col++) {
				theData[row][col] = theData[row][col] * aNumber;
				}
			}
	}
	
	public void mult(double aNumber) {
		// Multiplies a number to every cell in a dataframe
		mult(MIN_ROWS, rows, MIN_COLS, cols, aNumber);
	}	
	
	public void divide(int fromCol, int toCol, int fromRow, int toRow, double aNumber) {
		// divides a number to every cell within a defined range
		
		// error check the row and column limits
		checkCol(fromCol, "toString");
		checkCol(toCol, "toString");
		checkRow(fromRow, "toString");
		checkRow(toRow, "toString");
		
		checkColOrder(fromCol, toCol, "toString");
		checkRowOrder(fromRow, toRow, "toString");	
		
		for (int row = fromRow; row <= toRow; row++) {
			for (int col = fromCol; col <= toCol; col++) {
				theData[row][col] = theData[row][col] / aNumber;
				}
			}
	}
	
	public void divide(double aNumber) {
		// Adds a number to every cell in a dataframe
		
		// error check the row and column limits

		divide(MIN_ROWS, rows, MIN_COLS, cols, 1/aNumber);
	}	
	
	
	// Matrix arithmetic operations
	public void add(DataFrame otherDataFrame) {
		// Adds one dataframe to another
		
		// Check if one dataframe is smaller than the other .. we only add up the cells that exist in both dataframes
		int numCols = Math.min(cols, otherDataFrame.cols);
		int numRows = Math.min(rows, otherDataFrame.rows);
		
		for (int row = MIN_ROWS; row <= numRows; row++) {
			for (int col = MIN_COLS; col <= numCols; col++) {
				theData[row][col] = theData[row][col] + otherDataFrame.theData[row][col];
				}
			}
	}
	
	public void mult(DataFrame otherDataFrame) {
		// Item-by-item multiply of corresponding cells in a dataframe
		
		// Check if one dataframe is smaller than the other .. we only add up the cells that exist in both dataframes
		int numCols = Math.min(cols, otherDataFrame.cols);
		int numRows = Math.min(rows, otherDataFrame.rows);
		
		for (int row = MIN_ROWS; row <= numRows; row++) {
			for (int col = MIN_COLS; col <= numCols; col++) {
				theData[row][col] = theData[row][col] * otherDataFrame.theData[row][col];
				}
			}
	}
	
	public void mmult(DataFrame otherDataFrame) throws ArithmeticException {
		// 'Traditional matrix multiplication'
		
	     if(cols != otherDataFrame.rows){
	          throw new ArithmeticException("Error in mmult: 'Attempt to multiple two matrices where colums in matrix 1 <> rows in matrix 2");
	     } else {
	    	 // Create a new dataframe to store the result
	 		DataFrame result = new DataFrame(rows, otherDataFrame.cols);
	    	Double innerSum = 0.0; 
	    	 
	    	// First compute the result
	         for(int i = 1; i <= rows; i++) {
	             for (int j = 1; j <= otherDataFrame.cols; j++) {
	            	 innerSum = 0.0;
	                 for (int k = 1; k <= cols; k++) {
	                     innerSum += theData[i][k] * otherDataFrame.theData[k][j];
	                     result.setAt(i,j, innerSum);
	                 }
	             }
	         }   
	         // then copy the result back to the original dataframe 
	         for(int i = 1; i <= rows; i++) {
	             for (int j = 1; j <= cols; j++) {
	                  theData[i][j] =result.getAt(i, j);
	                 }
	             }
	         }   
	}

	
	// The following are trigonometric functions
	public void sin(){
		// Efficient and fast algorithm to compute sin for every element of the dataframe
		
		for (int row = MIN_ROWS; row <= rows; row++) {
			for (int col = MIN_COLS; col <= cols; col++) {
				double argument = theData[row][col];
				double fastSin = (16 * argument)*(Math.PI-argument)/((5 * Math.PI * Math.PI)-(4 * argument * (Math.PI - argument)));
				theData[row][col] =  fastSin;
				}
			}
	}
	
	public void cos(){
		// Efficient and fast algorithm to compute cos for every element of the dataframe
		
		for (int row = MIN_ROWS; row <= rows; row++) {
			for (int col = MIN_COLS; col <= cols; col++) {
				double argument = theData[row][col];
				double fastCos = 1 - (Math.pow(argument, 2.0)/2)+(Math.pow(argument, 4))/24-(Math.pow(argument,6)/720);
				theData[row][col] =  fastCos;
				}
			}
	}

	public void tan(){
		// Efficient and fast algorithm to compute tan for every element of the dataframe
		
		for (int row = MIN_ROWS; row <= rows; row++) {
			for (int col = MIN_COLS; col <= cols; col++) {
				double argument = theData[row][col];
				double fastTan = argument + 2*Math.pow(argument,3)/factorial(3) + 16*Math.pow(argument,5)/factorial(5);
				theData[row][col] =  fastTan;
				}
			}
	}
	
	private static double factorial(double number) {
		if (number <= 1)
			return 1;
		else
			return number * factorial(number - 1);
	}
	
	
	// New code for March 2022 test programme below this point

	// New Linear algebra methods
	
	public void zeros() {
		for(int i=0;i<5;i++){    
			for(int j=0;j<5;j++){    
				theData[i][j]=0;  
			}    
		}   		
	}
	
	public void identity() {
		for(int i=0;i<5;i++){    
			for(int j=0;j<5;j++){    
				theData[i][j]=0;  
				if (i==j) {
					theData[i][j] = 1;
				}
			}    
		} 
		
	}
	
	
	public void transpose() {
		
		DataFrame theTranspose = new DataFrame(this.rows, this.cols);
		
		for(int i=0;i<4;i++){    
			for(int j=0;j<4;j++){   
				// System.out.print(i);
				// System.out.print(j);	
				// System.out.println("---");
				theTranspose.setAt(i+1, j+1, theData[j][i]);  
			}    
		} 
		
		// Copy the transposed data back
		for(int i=0;i<4;i++){    
			for(int j=0;j<4;j++){    
				theData[i][j] = theTranspose.getAt(i+1,j+1);  
			}    
		} 		
	}

	public void upperTriangular() {
		// Assumes that we are dealing with an 'augmented' matrix .. 
		// The right-most column (i.e. theData[...][cols]) represents the 'Y' variable
		// The other columns represent the 'x' variables
		
		double rowDivisor = 0.0;
		
		for (int k=1; k<rows; k++){
			for(int i=k+1;i<=rows;i++){    
				rowDivisor = theData[i][k]/theData[k][k];				
				for(int j=1; j<=cols;j++) {
			    	theData[i][j] = theData[i][j] - (rowDivisor*theData[k][j]); 
			    }
			}
		}
	}
	
	public void rowElimination() {
		// Assumes that matrix is augmented and already in upper-triangular form
		
		double rowDivisor = 0.0;
		
		for (int i=rows; i>=2; i--){
			for (int j=i-1; j>=1; j--) {
				rowDivisor = theData[j][i]/theData[i][i];	
				for (int k=1; k<cols; k++) {
					theData[j][k] -= ( theData[i][k] * rowDivisor );
				}
			}
		}
	}
	
	public void onesReduce() {
		// Final step in Gaussian Elimination
		// Reduce diagonal to ones
		// Assumes that this is a an augmented matrix with data along the diagonal
		
		double toOneDivisor = 0.0;
		
		for (int i=1; i<=rows; i++) {
			toOneDivisor = theData[i][i];
			for (int j = 1; j<= cols; j++) {
				theData[i][j] = theData[i][j] / toOneDivisor;
			}
		}
	}
}
