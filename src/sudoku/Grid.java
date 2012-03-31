package sudoku;

/*
 * Grid class implements a grid.
 * 
 * TODO: use iterators to go through cells. Current way is awful.
 */
public class Grid {

	private Cell g[][];
	private long stateID; // I want to be able to detect whether state changes after an operation
	private int numFinalCells;
	
	/*
	 * Constructor
	 */
	public Grid() {
		g = new Cell[Settings.gridSize][Settings.gridSize];
		for (int i = 0; i < Settings.gridSize; i++) {
			for (int j = 0; j < Settings.gridSize; j++) {
				g[i][j] = new Cell(i, j, Grid.getBlockID(i, j));
			}
		}
	}
	
	/*
	 * Computes the id of the block given the id of row and column.
	 */
	public static int getBlockID(int row, int col) {
		
		int block = 0;
		
		// Consider row.
		int div = row / Settings.blockSize;
		block += div * Settings.blockSize;
		
		// Consider column.
		div = col / Settings.blockSize;
		block += div;
		
		return block;
	}
	
	/*
	 * Add the initial value for cell at position (i,j).
	 */
	public boolean addInitialValue(int i, int j, int value) {
		return g[i][j].dom.setValue(value);
	}
	
	
	/*=================================================
	 * Start solver section
	 * ==============================================*/
	
	/*
	 * Given the content of cell (i,j), remove its value from the domain of all cells
	 * in same row, column and block.
	 */
	private boolean fixDomain(int i, int j) {
		
		// Skip if cell is not fixed.
		if (g[i][j].dom.isMutable() || g[i][j].dom.getSize() != 1) {
			return false;
		} 
		else {
			
			// Store the value that we have to remove.
			int val = g[i][j].dom.getValues().get(0);
			
			// TODO: I suppose a cell iterator would be much better here, especially for block.
			
			// Handle row.
			for (int k = 0; k < Settings.gridSize; k++) {
				if (k != j && g[i][k].dom.isMutable()) {
					g[i][k].dom.removeValue(val);
				}
			}
			
			// Handle column.
			for (int k = 0; k < Settings.gridSize; k++) {
				if (k != i && g[k][j].dom.isMutable()) {
					g[k][j].dom.removeValue(val);
				}
			}
			
			// Handle block.
			// TODO: 9*9 operations when only 9 are needed, fix this.
			int b = Grid.getBlockID(i, j);
			for (int ii = 0; ii < Settings.gridSize; ii++) {
				for (int jj = 0; jj < Settings.gridSize; jj++) {
					if ( b == g[ii][jj].getBlock() &&
					     (ii != i || jj != j) && g[ii][jj].dom.isMutable()) {
						g[ii][jj].dom.removeValue(val);
					}
				}
			}
			return true;
		}
		
	} // end method fixDomain()
	
	public void preprocess() {
		for (int i = 0; i < Settings.gridSize; i++) {
			for (int j = 0; j < Settings.gridSize; j++) {
				this.fixDomain(i, j);
			}
		}
	}
	
	/*=================================================
	 * End solver section
	 * ==============================================*/
	
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String output = "";
		
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g[i].length; j++) {
				
				// If value is not final print a dot.
				if (g[i][j].dom.getSize() > 1) {
					output += ". ";
				}
				else {
					output += g[i][j].dom.getValues().get(0) + " ";
				}
				if (j % Settings.blockSize == Settings.blockSize - 1) {
					output += "  ";
				}
			}
			output += "\n";
			if (i % Settings.blockSize == Settings.blockSize - 1) {
				output += "\n";
			}
		}
		return output;
	}
}
