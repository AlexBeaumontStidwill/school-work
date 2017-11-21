package library;

public class libraryTest {
	public static void main(String[] args) {
		Library l = new Library();
		l.addTransactions(new Rental(1, 10, 2, new Device("Projector", 100)));
		l.addTransactions(new Rental(2, 1, 1, new Laptop("HP Elite", 50)));
		l.addTransactions(new Rental(3, 3, 0, new Adaptor("HDMI", 3)));
		l.addTransactions(new Rental(4, 6, 6, new Book("Absolute Java", "Walter", "Pearson", 2016)));
		l.addTransactions(new Rental(5, 6, 6, new Textbook("Absolute Java", "Walter", "Pearson", 2012)));
		l.addTransactions(new Rental(6, 6, 6, new Magazine("IEEE CommSoc", "N/A", "IEEE", 2017)));

		System.out.println(l);
		System.out.println("-----------------New copy:---------------");
		System.out.println(l.clone());
		
		System.out.println("-----------------Adding same Rentals:---------------");
		System.out.println(l.cloneAll());
		
		

	}
}
