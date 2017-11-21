// Alex Beaumont-Stidwill
// 10176777
package cmpe_212;

import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Assn2_10176777_Test {
	public static Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		JOptionPane.showMessageDialog(null, "Pressing 'Cancel' will exit the program");
		int a = 0; // condition to stay in loop
		while (a == 0) {
			String[] options = new String[] { "Try specific operations", "Cancel" };
			int choice = JOptionPane.showOptionDialog(null, "What would you like to do?", "The Matrix",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			if (choice == 0) {
				specificOperations();
			} else { // if cancel is chosen, program is terminated
				System.exit(0);
			}
		}
	}

	private static void specificOperations() throws FileNotFoundException {
		String[] options = new String[] { "One", "Two", "Cancel" };
		int answer = JOptionPane.showOptionDialog(null, "How many matrices would you like to create?", "Matrix Tester",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if (answer == 0) { // create one matrix
			Matrix mtrx = new Matrix(0, 0);
			mtrx = matrixCreator();
			oneMatrix(mtrx);
		} else if (answer == 1) { // create two matrices
			Matrix mtrx = new Matrix(0, 0);
			Matrix mtrx2 = new Matrix(0, 0);
			System.out.println("First Matrix");
			mtrx = matrixCreator();
			System.out.println("Second Matrix");
			mtrx2 = matrixCreator();
			twoMatrix(mtrx, mtrx2);
		} else { // cancel is chosen
			System.exit(0);
		}
	}

	private static void twoMatrix(Matrix mtrx, Matrix mtrx2) throws FileNotFoundException {
		System.out.println("The first matrix is:\n" + mtrx + "\n");
		System.out.println("The second matrix is:\n" + mtrx2 + "\n");
		int dec = 0;
		String[] options = { "first + second", "first - second", "first x second", "first / second", "second - first",
				"second x first", "second / first", "Cancel" };
		int choice = JOptionPane.showOptionDialog(null, "What operation woud you like to perform?",
				"Only one operation can be performed", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				options, options[0]);
		Matrix rslt = new Matrix(0, 0); // initializes matrix to be used
		// each choice gives user option to manipulate their resulting matrix by
		// doing single matrix operations
		if (choice == 0) {
			// first + second
			rslt = mtrx.add(mtrx2);
			System.out.println("The resulting matrix is:\n" + rslt + "\n");
			printer(rslt);
			dec = JOptionPane.showConfirmDialog(null, "Do you want to perform single matrix operations on new matrix?",
					"", JOptionPane.YES_NO_OPTION);
			if (dec == 0) {
				oneMatrix(rslt);
			}
		} else if (choice == 1) {
			// first - second
			rslt = mtrx.subtract(mtrx2);
			System.out.println("The resulting matrix is:\n" + rslt + "\n");
			printer(rslt);
			dec = JOptionPane.showConfirmDialog(null, "Do you want to perform single matrix operations on new matrix?",
					"", JOptionPane.YES_NO_OPTION);
			if (dec == 0) {
				oneMatrix(rslt);
			}
		} else if (choice == 2) {
			// first x second
			rslt = mtrx.multiply(mtrx2);
			System.out.println("The resulting matrix is:\n" + rslt + "\n");
			printer(rslt);
			dec = JOptionPane.showConfirmDialog(null, "Do you want to perform single matrix operations on new matrix?",
					"", JOptionPane.YES_NO_OPTION);
			if (dec == 0) {
				oneMatrix(rslt);
			}
		} else if (choice == 3) {
			// first / second
			rslt = mtrx.divide(mtrx2);
			System.out.println("The resulting matrix is:\n" + rslt + "\n");
			printer(rslt);
			dec = JOptionPane.showConfirmDialog(null, "Do you want to perform single matrix operations on new matrix?",
					"", JOptionPane.YES_NO_OPTION);
			if (dec == 0) {
				oneMatrix(rslt);
			}
		} else if (choice == 4) {
			// second - first
			rslt = mtrx2.subtract(mtrx);
			System.out.println("The resulting matrix is:\n" + rslt + "\n");
			printer(rslt);
			dec = JOptionPane.showConfirmDialog(null, "Do you want to perform single matrix operations on new matrix?",
					"", JOptionPane.YES_NO_OPTION);
			if (dec == 0) {
				oneMatrix(rslt);
			}
		} else if (choice == 5) {
			// second x first
			rslt = mtrx2.multiply(mtrx);
			System.out.println("The resulting matrix is:\n" + rslt + "\n");
			printer(rslt);
			dec = JOptionPane.showConfirmDialog(null, "Do you want to perform single matrix operations on new matrix?",
					"", JOptionPane.YES_NO_OPTION);
			if (dec == 0) {
				oneMatrix(rslt);
			}
		} else if (choice == 6) {
			// second / first
			rslt = mtrx2.divide(mtrx);
			System.out.println("The resulting matrix is:\n" + rslt + "\n");
			printer(rslt);
			dec = JOptionPane.showConfirmDialog(null, "Do you want to perform single matrix operations on new matrix?",
					"", JOptionPane.YES_NO_OPTION);
			if (dec == 0) {
				oneMatrix(rslt);
			}
		} else { // terminate program
			System.exit(0);
		}
	}

	private static void oneMatrix(Matrix mtrx) throws FileNotFoundException {
		int dec = 0; // variable to activate loop
		while (dec == 0) {
			String[] options = { "multiply by scalar", "find determinant", "find the inverse", "determine if square",
					"transpose", "Cancel" };
			int quest = JOptionPane.showOptionDialog(null, "What operation would you like to do?", "One Matrix",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

			switch (quest) {

			case 0:
				// multiply by scalar
				System.out.println("The matrix is:\n" + mtrx);
				System.out.println("choose a scalar to multiply the matrix: ");
				double x = in.nextDouble();
				mtrx = mtrx.multiply(x);
				System.out.println("Multiplied matrix:\n" + mtrx);
				break;

			case 1:
				// find determinant
				System.out.println("The matrix is:\n" + mtrx);
				double det = mtrx.determinant();
				System.out.println("The determinant is: " + det);
				break;

			case 2:
				// find inverse
				System.out.println("The matrix is:\n" + mtrx);
				mtrx = mtrx.inverse();
				System.out.println("The inverted matrix is:\n" + mtrx);
				break;

			case 3:
				// determine if square
				System.out.println("The matrix is:\n" + mtrx);
				boolean check = mtrx.isSquare();
				if (check == true) {
					System.out.println("Matrix is square!");
				} else {
					System.out.println("Matrix is not square!");
				}
				break;

			case 4:
				// transpose
				System.out.println("The matrix is:\n" + mtrx);
				mtrx = mtrx.transpose();
				System.out.println("Transposed matrix:\n" + mtrx);
				break;

			case 5:
				// terminate program
				System.exit(0);
			}
			dec = JOptionPane.showConfirmDialog(null, "Do you want to perform more operations on single matrix?",
					"decision", JOptionPane.YES_NO_OPTION);
		}
		// allows user to save final matrix in csv file
		printer(mtrx);
	}

	private static void printer(Matrix mtrx) throws FileNotFoundException {
		// function to call print
		int file = JOptionPane.showConfirmDialog(null, "Do you want to print the matrix to a csv file?", "CSV",
				JOptionPane.YES_NO_OPTION);
		if (file == 0) { // wants to print in csv
			String name = JOptionPane.showInputDialog(null, "File name:");
			mtrx.print(name);
		} else { // if not, return to main program
			return;
		}
	}

	private static Matrix matrixCreator() throws FileNotFoundException {
		int m = 0;
		int n = 0;
		int size = 0;
		String filename;
		Matrix custMatrix = new Matrix(m, n); // initializes custom matrix
		String[] options = new String[] { "Custom Matrix", "Matrix from a CSV file", "All zero matrix",
				"Identity matrix", "Cancel" };
		int answer = JOptionPane.showOptionDialog(null, "What kind of matrix do you want to create?", "Matrix Creator",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		switch (answer) {

		case 0:
			// custom matrix
			System.out.println("number of rows: ");
			m = in.nextInt(); // assigns value to rows
			System.out.println("number of columns: ");
			n = in.nextInt(); // assigns value to columns
			custMatrix = createMatrix(m, n);
			return custMatrix;

		case 1:
			// CSV Matrix
			System.out.println("what is the name of the CSV file? ");
			filename = in.nextLine(); // asks user for name of file
			custMatrix = matrixCSV(filename);
			return custMatrix;

		case 2:
			// All zero matrix
			System.out.println("number of rows: ");
			m = in.nextInt(); // assigns value to rows
			System.out.println("number of columns: ");
			n = in.nextInt(); // assigns value to columns
			Matrix zeroMatrix = new Matrix(m, n);
			// creates all zero matrix
			return zeroMatrix;

		case 3:
			// identity matrix
			System.out.println("size of matrix: ");
			size = in.nextInt(); // size of matrix
			custMatrix = identityMatrix(size);
			return custMatrix;

		case 4:
			// terminates the program
			System.exit(0);
		}
		return custMatrix;
	}

	private static Matrix identityMatrix(int size) {
		// create the identity matrix
		Matrix a = new Matrix(size, size);
		a = a.identity(size);
		return a;
	}

	private static Matrix matrixCSV(String filename) throws FileNotFoundException {
		// create the matrix from the csv file
		Matrix a = new Matrix(filename);
		return a;
	}

	private static Matrix createMatrix(int m, int n) {
		int x = 0; // needed to create matrix that won't be all zero
		Matrix a = new Matrix(m, n, x);
		return a;
	}
}
