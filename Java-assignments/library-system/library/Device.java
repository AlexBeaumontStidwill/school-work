/*
 * Alex Beaumont Stidwill
 * 10176777
 */
package library;

public class Device extends Item {
	// attributes
	private double rentalCost;

	// function to get the rental cost
	public double getRentalCost() {
		// not sure if this is how I was supposed to implement the check to see
		// if the customer is a student
		if (Customer.customers.Students != null) {
			// if the customer is a student, apply discount
			return 0.75 * rentalCost;
		}
		return rentalCost;
	}

	// setter
	public void setRentalCost(double rentalCost) {
		this.rentalCost = rentalCost;
	}

	// copy constructor
	public Device(Device d) {
		super((Item) d);
		rentalCost = d.rentalCost;
	}

	// constructor
	public Device(String name, double rental, int id) {
		super(name, id);
		rentalCost = rental;
	}

	// override methods
	@Override
	public double getLateFees(int days) {
		return 2 * days + 0.1 * rentalCost;
	}

	@Override
	public String toString() {
		return "Device --> " + super.toString() + ", Cost= " + rentalCost;
	}

	@Override
	protected Device clone() {
		return new Device(this);
	}
}
