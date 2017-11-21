/*
 * Alex Beaumont Stidwill
 * 10176777
 */
package library;

public class Laptop extends Device {
	// copy constructor
	public Laptop(Laptop d) {
		super((Laptop) d);
	}

	// constructor
	public Laptop(String name, double rental, int id) {
		super(name, rental, id);
	}

	// override methods
	@Override
	public double getLateFees(int days) {
		return 5 * days + 0.2 * getRentalCost();
	}

	@Override
	public String toString() {
		return "Laptop --> " + super.toString();
	}

}
