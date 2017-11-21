/*
 * Alex Beaumont Stidwill
 * 10176777
 */
package library;

public class Textbook extends Book {
	// copy constructor
	public Textbook(Textbook b) {
		super(b);
	}

	// constructor
	public Textbook(String name, String authors, String publisher, int year, int id) {
		super(name, authors, publisher, year, id);
	}

	// override methods
	@Override
	public double getLateFees(int days) {
		return 1 * days;
	}

	@Override
	public String toString() {
		return "Textbook --> " + super.toString();
	}

	@Override
	protected Textbook clone() {
		return new Textbook(this);
	}
}
