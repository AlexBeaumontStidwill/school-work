/*
 * Alex Beaumont Stidwill
 * 10176777
 * NOTE: I'm not sure if this is how to properly use the dates, 
 * I wasn't sure how to input the dates therefore I got stuck at that, 
 * but still tried my best to make the functions and implement them
 */
package library;

import java.util.Date;

public class Rental {
	// attributes
	private int CID, rentalDays, lateDays, itemID;
	private static int transaction = 0;
	private Item item;
	private Customer customer;
	private Date dateRented, dateReturned, estimatedReturn;
	private status status;

	// status enum
	public enum status {
		Active, Late, Closed
	};

	// setters and getters
	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public static int getTransaction() {
		return transaction++;
	}

	public static void setTransaction(int transaction) {
		Rental.transaction = transaction++;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getDateRented() {
		return dateRented;
	}

	public void setDateRented(Date dateRented) {
		this.dateRented = dateRented;
	}

	public Date getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(Date dateReturned) {
		this.dateReturned = dateReturned;
	}

	public Date getEstimatedReturn() {
		return estimatedReturn;
	}

	public void setEstimatedReturn(Date estimatedReturn) {
		this.estimatedReturn = estimatedReturn;
	}

	public status getStatus() {
		return status;
	}

	public void setStatus(status status) {
		this.status = status;
	}

	public Item getItem() {
		return item;
	}

	public int getCID() {
		return CID;
	}

	public void setCID(int cID) {
		CID = cID;
	}

	public int getRentalDays() {
		return rentalDays;
	}

	public void setRentalDays(int rentalDays) {
		this.rentalDays = rentalDays;
	}

	public int getLateDays() {
		return lateDays;
	}

	public void setLateDays(int lateDays) {
		this.lateDays = lateDays;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	// rental constructor
	public Rental(int cID, int rentalDays, int itemID, Customer customer, Item item) {
		CID = cID;
		this.rentalDays = rentalDays;
		this.itemID = itemID;
		this.item = item;
		this.customer = customer;
		Rental.transaction = transaction++;
		this.status = status.Active;
	}

	// rental copy constructor
	public Rental(Rental r) {
		this.CID = r.CID;
		this.rentalDays = r.rentalDays;
		this.itemID = r.itemID;
		this.item = r.item;
		this.customer = r.customer;
		Rental.transaction = transaction++;
		this.status = status.Active;
	}

	// function to get the late fee based on the calendar date
	public double getLateFee() {
		double total = 0;
		if (isLate()) {
			Date date1 = new Date();
			// below will calculate the difference between the current date and
			// the
			// rented date
			// the division is used to convert milliseconds to days, and cast it
			// as
			// an integer
			int daysLate = (int) (date1.getTime() - this.dateRented.getTime()) / (1000 * 60 * 60 * 24);
			// below will calculate the total late fee based on the late days
			total = this.item.getLateFees(daysLate);
			return total;
		}
		return total;
	}

	// function to determine if the item is late
	public boolean isLate() {
		Date date1 = new Date();
		// if the current date is after the estimated return date, it is late
		if (date1.after(this.estimatedReturn)) {
			// sets the status as late
			this.status = status.Late;
			return true;
		} else
			return false;
	}

	// function to set the status of the item to returned
	public void itemReturned() {
		this.status = status.Closed;
		// sets the return date as the current date
		this.dateReturned = new Date();
	}

	// function to determine the rental cost of the item
	public double getRentalCost() {
		// only devices have rental costs
		if (item instanceof Device) {
			return ((Device) item).getRentalCost() * rentalDays;
		} else
			return 0;
	}

	// NOTE : i didn't implement the get total to be paid function because it is
	// done in the main library class

	// override methods
	@Override
	public String toString() {
		String s = "Customer ID: " + CID;
		s += "\nItem: " + getItem() + "\n";
		s += "Rental days=" + rentalDays + ", late Days=" + lateDays;
		return s;
	}

	@Override
	protected Rental clone() {
		return new Rental(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (!(obj.getClass() == getClass()))
			return false;
		Rental r = (Rental) obj;
		return r.item.equals(this.item) && CID == r.CID;
	}
}
