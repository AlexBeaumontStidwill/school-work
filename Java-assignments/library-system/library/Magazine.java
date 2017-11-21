/*
 * Alex Beaumont Stidwill
 * 10176777
 */
package library;

public class Magazine extends Book {
	// copy constructor
	public Magazine(Magazine b) {
		super(b);
	}

	// constructor
	public Magazine(String name, String authors, String publisher, int year, int id) {
		super(name, authors, publisher, year, id);
	}

	// override methods
	@Override
	public double getLateFees(int days) {
		return 0.75 * days;
	}

	@Override
	public String toString() {
		return "Magazine --> " + super.toString();
	}
}
