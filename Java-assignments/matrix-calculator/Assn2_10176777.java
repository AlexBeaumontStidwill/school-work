//Alex Beaumont-Stidwill
//10176777
package cmpe_212;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

class Matrix {
	// Attributes
	private int m; // rows
	private int n; // columns
	private double[][] matrix;

	// Constructors
	public Matrix(int m, int n) {
		// constructs all zero matrix
		this.m = m; // rows
		this.n = n; // columns
		this.matrix = new double[m][n]; // 2D matrix
	}

	public Matrix(int m, int n, int x) {
		// the extra variable is only used to differentiate it from the above
		// constructor
		this.m = m; // rows
		this.n = n; // columns
		this.matrix = new double[m][n]; // 2D matrix
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int row = i + 1; // so the message will say actual row instead
									// of one less
				int column = j + 1; // so the message will say actual column
									// instead of on less
				String num = JOptionPane.showInputDialog(null, "Enter value for row " + row + ", column " + column);
				double value = Double.parseDouble(num);
				this.matrix[i][j] = value; // fills array with user's data
			}
		}
	}

	public Matrix(String filename) {
		Scanner sc;
		try {
			// creates new file
			sc = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			// file doesn't exist
			System.out.println("File not found");
			return;
		}
		String[] size = sc.nextLine().split(",");
		// split seperates the values with commas
		m = Integer.parseInt(size[0]); // first value is row
		n = Integer.parseInt(size[1]); // next value is column
		matrix = new double[m][n];

		while (sc.hasNextLine()) { // reads the file
			for (int i = 0; i < m; i++) {
				String line[] = sc.nextLine().split(",");
				for (int j = 0; j < n; j++) {
					matrix[i][j] = Double.parseDouble(line[j]);
					// fills up matrix
				}
			}
		}
		sc.close();
		// closes the file
	}

	// Getters
	public int getN() {
		return n;
	}

	public int getM() {
		return m;
	}

	public double get(int i, int j) {
		return matrix[i][j];
	}

	// Opertations
	public Matrix add(Matrix m) {
		// add this matrix to matrix m and return the result
		Matrix addedMatrix = new Matrix(this.m, this.n);
		if (this.m == m.m) {
			if (this.n == m.n) {
				// matrices must be of same size
				for (int i = 0; i < this.m; i++) {
					for (int j = 0; j < this.n; j++) {
						addedMatrix.matrix[i][j] = this.matrix[i][j] + m.matrix[i][j];
					}
				}
			}
		} else {
			System.out.println("Error, matrices must be of same size\nAddition not defined");
		}
		return addedMatrix;
	}

	public Matrix subtract(Matrix m) {
		// subtract matrix m from this matrix and return the result
		Matrix subMatrix = new Matrix(this.m, this.n);
		if (this.m == m.m && this.n == m.n) {
			// matrices must be of same size
			for (int i = 0; i < this.m; i++) {
				for (int j = 0; j < this.n; j++) {
					subMatrix.matrix[i][j] = this.matrix[i][j] - m.matrix[i][j];
				}
			}
		} else {
			System.out.println("Error, matrices must be of same size\nSubtraction not defined");
		}
		return subMatrix;
	}

	public Matrix multiply(Matrix m) {
		// matrix multiplication between this matrix and matrix m. return the
		// result
		Matrix multMatrix = new Matrix(this.m, m.n);
		if (this.n == m.m) {
			// 1st matrix needs as many rows as 2nd matrix has columns
			for (int i = 0; i < this.m; i++) {
				for (int j = 0; j < m.n; j++) {
					for (int k = 0; k < m.m; k++) {
						multMatrix.matrix[i][j] += this.matrix[i][k] * m.matrix[k][j];
					}
				}
			}
		} else {
			System.out.println("Error, matrices cannot be multiplied due to mismatched sizes");
		}
		return multMatrix;
	}

	public Matrix multiply(double x) {
		// multiply scalar x by this matrix and return the result
		Matrix multMatrix = new Matrix(this.m, this.n);
		for (int i = 0; i < this.m; i++) {
			for (int j = 0; j < this.n; j++) {
				// multiplies each value by given scalar
				multMatrix.matrix[i][j] = this.matrix[i][j] * x;
			}
		}
		return multMatrix;
	}

	public Matrix divide(Matrix m) {
		// matrix division of this matrix on matrix m. return the result
		Matrix divMatrix = new Matrix(this.m, this.n);
		// division is the same as multiplying by inverse of matrix
		divMatrix = this.multiply(m.inverse());
		return divMatrix;
	}

	public double determinant() {
		// return the determinant of the current matrix
		// matrix has to be square and of size 1x1, 2x2, or 3x3
		double det = 0;
		if (isSquare() == true && this.m < 4 && this.n < 4) {
			if (this.m == 1) { // 1x1 matrix
				det = this.matrix[0][0];
			} else if (this.m == 2) { // 2x2 matrix
				det = (this.matrix[0][0] * this.matrix[1][1]) - (this.matrix[1][0] * this.matrix[0][1]);
			} else { // 3x3 matrix
				double x = this.matrix[0][0]
						* ((this.matrix[1][1] * this.matrix[2][2]) - (this.matrix[2][1] * this.matrix[1][2]));
				double y = this.matrix[0][1]
						* ((this.matrix[1][0] * this.matrix[2][2]) - (this.matrix[2][0] * this.matrix[1][2]));
				double z = this.matrix[0][2]
						* ((this.matrix[1][0] * this.matrix[2][1]) - (this.matrix[1][1] * this.matrix[2][0]));
				det = x - y + z;
				// formula used to find determinant of 3x3 matrix
			}
		} else { // matrix is not square or is too big
			System.out.println("Error, invalid matrix\nCan't compute determinant");
		}
		return det;
	}

	public Matrix inverse() {
		// return the inverse of the current matrix
		// matrix has to be square and of size 1x1, 2x2, or 3x3
		Matrix invMatrix = new Matrix(this.m, this.n);
		if (isSquare() == false) { // check that matrix is square
			System.out.println("Error, matrix must be square");
			return invMatrix;
		} else {
			if (isSquare() == true && this.m < 4 && this.n < 4) {
				if (this.m == 1) { // 1x1 matrix
					invMatrix = invMatrix.multiply(1 / (determinant()));
				} else if (this.m == 2) { // 2x2 matrix
					invMatrix.matrix[0][0] = this.matrix[1][1];
					invMatrix.matrix[0][1] = -1 * this.matrix[1][0];
					invMatrix.matrix[1][0] = -1 * this.matrix[0][1];
					invMatrix.matrix[1][1] = this.matrix[0][0];
					invMatrix = invMatrix.multiply(1 / (this.determinant()));
				} else { // 3x3 matrix
					invMatrix.matrix[0][0] = (this.matrix[1][1] * this.matrix[2][2])
							- (this.matrix[1][2] * this.matrix[2][1]);
					invMatrix.matrix[0][1] = -1
							* ((this.matrix[1][0] * this.matrix[2][2]) - (this.matrix[2][0] * this.matrix[1][2]));
					invMatrix.matrix[0][2] = (this.matrix[1][0] * this.matrix[2][1])
							- (this.matrix[2][0] * this.matrix[1][1]);
					invMatrix.matrix[1][0] = -1
							* ((this.matrix[0][1] * this.matrix[2][2]) - (this.matrix[2][1] * this.matrix[0][2]));
					invMatrix.matrix[1][1] = (this.matrix[0][0] * this.matrix[2][2])
							- (this.matrix[2][0] * this.matrix[0][2]);
					invMatrix.matrix[1][2] = -1
							* ((this.matrix[0][0] * this.matrix[2][1]) - (this.matrix[2][0] * this.matrix[0][1]));
					invMatrix.matrix[2][0] = (this.matrix[0][1] * this.matrix[1][2])
							- (this.matrix[1][1] * this.matrix[0][2]);
					invMatrix.matrix[2][1] = -1
							* ((this.matrix[0][0] * this.matrix[1][2]) - (this.matrix[1][0] * this.matrix[0][2]));
					invMatrix.matrix[2][2] = (this.matrix[0][0] * this.matrix[1][1])
							- (this.matrix[1][0] * this.matrix[0][1]);
					invMatrix = invMatrix.transpose();
					invMatrix = invMatrix.multiply(1 / (this.determinant()));
					return invMatrix;
				}
			}
		}
		return invMatrix; // returns all zero if too big or not square
	}

	public boolean isSquare() {
		// return true if the matrix is square
		if (this.m == this.n) { // is square
			return true;
		} else { // is not square
			return false;
		}
	}

	public Matrix transpose() {
		// return the transpose of the current matrix
		Matrix transMatrix = new Matrix(this.n, this.m);
		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.m; j++) {
				// flips matrix along the diagonal
				transMatrix.matrix[i][j] = this.matrix[j][i];
			}
		}
		return transMatrix;
	}

	// Other
	public String toString() {
		// to return a string representation of the matrix
		String print = "";
		for (int i = 0; i < this.getM(); i++) {
			print += "\n"; // new line each time it moves to next row
			for (int j = 0; j < this.getN(); j++) { // prints values
				print += " " + this.get(i, j);
			}
		}
		return print;
	}

	public void print(String filename) throws FileNotFoundException {
		// create a file and write the matrix in it
		PrintWriter pw = new PrintWriter(new File(filename + ".csv"));
		// creates csv file using user's filename
		String res = "";
		res += m + "," + n + ",";
		for (int i = 0; i < this.getM(); i++) {
			res += "\n";
			// new line each time it moves to next row
			for (int j = 0; j < this.getN(); j++) { // print values
				res += " " + this.get(i, j) + ",";
			}
		}
		pw.println(res);
		pw.close();
	}

	public Matrix identity(int size) {
		// static method to create an identity square matrix of size size
		Matrix idMatrix = new Matrix(size, size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == j) {
					idMatrix.matrix[i][j] = 1;
				} else {
					idMatrix.matrix[i][j] = 0;
				}
			}
		}
		return idMatrix;
	}

}
