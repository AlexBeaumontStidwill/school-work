/*
 * Alex Beaumont Stidwill
 * 10176777
 */
package library;

public class Adaptor extends Device {
	// copy constructor
	public Adaptor(Adaptor d) {
		super((Adaptor) d);
	}
	
	// constructor
	public Adaptor(String name, double rental, int id) {
		super(name, rental, id);
	}

	// override methods
	@Override
	public double getLateFees(int days) {
		return 2.5 * days + 0.15 * getRentalCost();
	}
	@Override
	public String toString() {
		return "Adaptor --> " + super.toString();
	}
	@Override
	protected Adaptor clone()  {
		return new Adaptor(this);
	}
}
