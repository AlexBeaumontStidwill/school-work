/*
 * Alex Beaumont Stidwill
 * 10176777
 */
package library;

public class Book extends Item {
	// attributes
	private String authors, publisher;
	private int year;

	// copy constructor
	public Book(Book b) {
		super(b);
		authors = b.authors;
		publisher = b.publisher;
		year = b.year;
	}

	// constructor
	public Book(String name, String authors, String publisher, int year, int id) {
		super(name, id);
		this.authors = authors;
		this.publisher = publisher;
		this.year = year;
	}

	// setters and getters
	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	// override method
	@Override
	public double getLateFees(int days) {
		return 0.5 * days;
	}

	@Override
	public String toString() {
		return "Book --> " + super.toString() + ", authors: " + authors + ", year:" + year + ", Publisher: "
				+ publisher;
	}

	@Override
	protected Book clone() {
		return new Book(this);
	}
}
