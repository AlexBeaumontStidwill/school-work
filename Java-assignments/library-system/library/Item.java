/*
 * Alex Beaumont Stidwill
 * 10176777
 */
package library;

public abstract class Item {
	// attributes
	private int id;
	private String name;
	private static int maxID = 0;

	// copy constructor
	public Item(Item i) {
		this.id = ++maxID;
		this.name = i.name;
	}

	// constructor
	public Item(String name, int id) {
		this.id = ++maxID;
		this.name = name;
	}

	// setters and getters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	// override methods
	@Override
	public String toString() {

		return "Item ID:" + id + ", Name: " + name;
	}

	public abstract double getLateFees(int days);

	@Override
	protected abstract Item clone();

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Item))
			return false;
		Item i = (Item) obj;
		return i.id == id;
	}

}
