/*
 * Alex Beaumont Stidwill
 * 10176777
 */
package library;

public class DuplicateTransactionID extends Exception {
	// returns the message to the terminal because of the transaction ID already
	// having been used
	DuplicateTransactionID(String message) {
		super(message);
	}
}
