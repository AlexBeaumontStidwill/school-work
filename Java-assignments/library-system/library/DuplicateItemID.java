/*
 * Alex Beaumont Stidwill
 * 10176777
 */
package library;

public class DuplicateItemID extends Exception {
	// returns the message to the terminal because of the item being duplicated
	DuplicateItemID(String message) {
		super(message);
	}
}
