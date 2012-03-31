package sudoku;


/**
 * Cell class
 * 
 * A cell is basically a domain with coordinates.
 * It can hold a guessed value used in the backtracking algorithm.
 * 
 * @author claudio
 *
 */
public class Cell {
	
	// Coordinates.
	private int rowID;
	private int columnID;
	private int blockID;
	
	public Domain dom;
	public boolean guessed;
	public int guess;
	
	/*
	 * Constructor
	 */
	public Cell(int row, int col, int block) {
		this.dom = new Domain();
		this.guessed = false;
		this.rowID = row;
		this.columnID = col;
		this.blockID = block;
	}
	
	/*
	 * Return row id.
	 */
	public int getRow() {
		return this.rowID;
	}

	/*
	 * Return column id.
	 */
	public int getColumn() {
		return this.columnID;
	}

	/*
	 * Return block id.
	 */
	public int getBlock() {
		return this.blockID;
	}

	/*
	 * Return currently guessed value.
	 */
	public int getGuess() {
		
		// Check if operation is possible.
		if ( !this.guessed ) {
			// TODO: throw exception?
			return 0;
		}
		else {
			return this.guess;
		}
	}
	
	/*
	 * Set a guess for this cell.
	 */
	public boolean setGuess(int n) {
		
		// Check if operation is possible.
		if ( !this.dom.isMutable() ) {
			// TODO: throw exception?
			return false;
		}
		else {
			this.guess = n;
			this.guessed = true;
			return true;
		}
	}
	
	/*
	 * Remove the guessed that was made on this cell.
	 */
	public boolean removeGuess() {
		
		// Check if operation is possible.
		if ( !this.guessed ) {
			// TODO: throw exception?
			return false;
		}
		else {
			this.guessed = false;
			return true;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String res = "Cell (";
		res += this.rowID;
		res += ",";
		res += this.columnID;
		res += "). Possible values: ";
		res += dom.toString();
		return res;
	}
}
