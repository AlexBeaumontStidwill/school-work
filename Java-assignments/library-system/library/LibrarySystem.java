/*
 * Alex Beaumont Stidwill
 * 10176777
 */

package library;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class LibrarySystem {
	// attributes
	public static int day = 0;
	static Scanner in = new Scanner(System.in);
	// creates the collections for the items, rentals, and customers
	TreeMap<Integer, Item> itemLibrary;
	TreeMap<Integer, Rental> rentalLibrary;
	TreeMap<Integer, Customer> customerLibrary;
	ArrayList<Rental> transactions;

	// creates the library
	public LibrarySystem() {
		transactions = new ArrayList<Rental>();
		itemLibrary = new TreeMap<Integer, Item>();
		rentalLibrary = new TreeMap<Integer, Rental>();
		customerLibrary = new TreeMap<Integer, Customer>();
	}

	// library constructor
	public LibrarySystem(LibrarySystem l) {
		transactions = new ArrayList<Rental>();
		itemLibrary = new TreeMap<Integer, Item>();
		rentalLibrary = new TreeMap<Integer, Rental>();
		customerLibrary = new TreeMap<Integer, Customer>();
		for (Rental r : l.transactions) {
			// checks if the attributes are valid
			if (isValidItem(itemLibrary, r.getItemID())) {
				itemLibrary.put(r.getItemID(), r.getItem());
				if (isValidCustomer(customerLibrary, r.getCID())) {
					customerLibrary.put(r.getCID(), r.getCustomer());
					if (isValidTransaction(rentalLibrary, Rental.getTransaction())) {
						rentalLibrary.put(Rental.getTransaction(), r);
						transactions.add(r.clone());
					}
				}
			}
		}
	}

	// function to add a rental transaction
	void addTransactions(Rental r) {
		if (r == null)
			return;
		if (isValidItem(itemLibrary, r.getItemID())) {
			// checks that the input is valid
			itemLibrary.put(r.getItemID(), r.getItem());
			if (isValidCustomer(customerLibrary, r.getCID())) {
				customerLibrary.put(r.getCID(), r.getCustomer());
				if (isValidTransaction(rentalLibrary, Rental.getTransaction())) {
					rentalLibrary.put(Rental.getTransaction(), r);
					transactions.add(r);
				}
			}
		}
	}

	// exception functions
	private boolean isValidTransaction(TreeMap<Integer, Rental> a, int transaction) {
		try {
			if (a.containsKey(transaction))
				throw new DuplicateTransactionID("Please enter a valid transaction ID");

		} catch (DuplicateTransactionID e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	private boolean isValidCustomer(TreeMap<Integer, Customer> a, int cid) {
		try {
			if (a.containsKey(cid))
				throw new DuplicateCustomerID("Please enter a valid customer ID");

		} catch (DuplicateCustomerID e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	private boolean isValidItem(TreeMap<Integer, Item> a, int itemID) {
		try {
			if (a.containsKey(itemID))
				throw new DuplicateItemID("Please enter a valid item ID");
			return true;
		} catch (DuplicateItemID e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	// gets the late fees in the entire library system
	double getTotalLateFees() {
		int sum = 0;
		for (Rental r : transactions) {

			sum += r.getItem().getLateFees(r.getLateDays());
		}
		return sum;
	}

	// gets the total rental cost in the entire library
	double getTotalRentalCosts() {
		int sum = 0;
		for (Rental r : transactions) {
			if (r.getItem() instanceof Device) {
				Device d = (Device) r.getItem();
				sum += d.getRentalCost();
			}
		}
		return sum;
	}

	// override methods
	@Override
	public String toString() {
		String s = "";
		for (Rental r : transactions) {
			s += "\n" + r + "\n------\n";
		}
		s += "Total Late Fees= " + getTotalLateFees();
		s += "\nTotal Rental Costs= " + getTotalRentalCosts();
		return s;
	}

	@Override
	protected LibrarySystem clone() {

		return new LibrarySystem(this);
	}

	public LibrarySystem cloneAll() {
		LibrarySystem l = new LibrarySystem(this);
		for (Rental r : transactions) {
			Rental r2 = r.clone();
			r2.setItem(r2.getItem().clone());
			l.addTransactions(r2);
		}
		return l;
	}

	// tests for the functions
	public static void main(String[] args) {
		LibrarySystem l = new LibrarySystem();
		l.addTransactions(new Rental(1, 10, 2, new Customer(1, "Alex", "engineering", "Students"),
				new Device("Projector", 100, 1)));
		l.addTransactions(
				new Rental(2, 1, 1, new Customer(2, "Jerry", "Science", "Employees"), new Laptop("HP Elite", 50, 2)));
		l.addTransactions(
				new Rental(3, 3, 0, new Customer(2, "Jerry", "Science", "Employees"), new Adaptor("HDMI", 3, 3)));
		l.addTransactions(new Rental(4, 6, 6, new Customer(2, "Jerry", "Science", "Students"),
				new Book("Absolute Java", "Walter", "Pearson", 2016, 4)));
		l.addTransactions(new Rental(5, 6, 5, new Customer(2, "Jerry", "Science", "Employees"),
				new Textbook("Absolute Java", "Walter", "Pearson", 2012, 5)));
		l.addTransactions(new Rental(6, 6, 4, new Customer(2, "Jerry", "Science", "Employees"),
				new Magazine("IEEE CommSoc", "N/A", "IEEE", 2017, 6)));
		System.out.println(l);
		System.out.println("-----------------New copy:---------------");
		System.out.println(l.clone());

		System.out.println("-----------------Adding same Rentals:---------------");
		System.out.println(l.cloneAll());

	}

}
