/*
 * Alex Beaumont Stidwill
 * 10176777
 */
package library;

public class DuplicateCustomerID extends Exception {
	// returns the message to the terminal due to another customer having the
	// same ID
	DuplicateCustomerID(String message) {
		super(message);
	}
}
