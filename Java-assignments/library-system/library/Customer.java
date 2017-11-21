/*
 * Alex Beaumont Stidwill
 * 10176777
 */
package library;

public class Customer {
	// attributes
	private int customerID;
	private String name, department;

	enum customers {
		Students, Employees
	};

	private customers customerType;

	// setters and getters
	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public customers getCustomerType() {
		return customerType;
	}

	public void setCustomerType(customers customerType) {
		this.customerType = customerType;
	}

	// customer constructor
	public Customer(int customerID, String name, String department, String customerType) {
		this.customerID = customerID;
		this.name = name;
		this.department = department;
		this.customerType = customers.valueOf(customerType);
	}
}
