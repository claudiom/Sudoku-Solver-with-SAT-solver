package sudoku;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * FileParser class
 * 
 * Open file, read content and populate a grid.
 * 
 * @author claudio
 *
 */
public class FileParser implements Parser {

	/* (non-Javadoc)
	 * @see Parser#readFile(java.lang.String)
	 */
	@Override
	public Grid readFile(String filename) {
		
		// TODO: parser populates grid directly... does it make sense?
		Grid m = new Grid();
		
		try {
			FileInputStream fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			int i = 0;
			//Read file line by line
			while ((strLine = br.readLine()) != null)   {
				//System.out.format("Line %d\n", i);
				// Read each character
				for (int j = 0; j < strLine.length(); j++) {
					
					char c = strLine.charAt(j);
					if (Character.isDigit(c)) {
						//System.out.format("Found a digit at (%d,%d): %c\n", i, j, c);
						m.addInitialValue(i, j, Character.getNumericValue(c));
					}
				}
				i++;
			}
			//Close the input stream
			in.close();
		} catch (Exception e) { //Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		
		return m;
	}


}
