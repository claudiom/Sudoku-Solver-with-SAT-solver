package sudoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Domain class
 * 
 * It represents a set of possible integers.
 * 
 * @author claudio
 *
 */
public class Domain {

	private boolean mutable;
	private HashSet<Integer> items;
	
	/*
	 * Constructor
	 * 
	 * Initialize the domain with all possible values
	 */
	public Domain() {
		this.mutable = true;
		this.items = new HashSet<Integer>(Settings.maxValue);
		for (int i = Settings.minValue; i <= Settings.maxValue; i++) {
			this.items.add(i);
		}
	}
	
	/*
	 * Return true if cell has one possible value left in its domain.
	 * It is thus fixed.
	 */
	public boolean isMutable() {
		return this.mutable;
	}
	
	/*
	 * Return the list of values in the domain.
	 */
	public List<Integer> getValues() {
		ArrayList<Integer> l = new ArrayList<Integer>();
		for (int i: items) {
			l.add(i);
		}
		return l;
	}
	
	/*
	 * Return the number of values present in the domain.
	 */
	public int getSize() {
		return this.items.size();
	}
	
	/*
	 * Set a single value for the domain.
	 * Domain size goes down to 1.
	 */
	public boolean setValue(int n) {
		
		// Check if operation is allowed.
		if (!mutable) {
			return false;
		}
		
		// Proceed whatever the values we currently have.
		this.items.clear();
		this.items.add(n);
		this.mutable = false;
		return true;
	}
	
	/*
	 * Remove a possible value from the domain of this cell
	 */
	public boolean removeValue(int n) {

		// Check if operation is allowed.
		if (!mutable) {
			return false;
		}

		// If n is not in the domain do nothing and return false.
		if (this.items.contains(n)) {
			this.items.remove(n);
		} else {
			return false;
		}
		
		if (this.items.size() == 1) {
			this.mutable = false;
		} else if (this.items.size() == 0) {
			// TODO: raise exception!
		}

		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		ArrayList<Integer> l = new ArrayList<Integer>();
		for (int i: items) {
			l.add(i);
		}
		return l.toString();
	}
	
}
