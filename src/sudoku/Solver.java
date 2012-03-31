package sudoku;

/*
 * Solver class
 * 
 * Temporary implementation: it's just a main.
 */
public class Solver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// TODO: read filename from args[]
		// ...
		
		FileParser fp = new FileParser();
		Grid grid = fp.readFile("sudoku_easy_01.txt");
		//System.out.println(grid.toString());
		
		grid.preprocess();
		//System.out.println("After one step of preprocessing");
		grid.preprocess();
		//System.out.println("After two steps of preprocessing");
		grid.preprocess();
		//System.out.println("After three steps of preprocessing");
		grid.preprocess();
		grid.preprocess();
		grid.preprocess();

		System.out.println("After many steps of preprocessing");

		System.out.println(grid.toString());

	}

}
