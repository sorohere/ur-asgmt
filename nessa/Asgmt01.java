package nessa;
import java.util.*;

/**
 * The Matrix class represents a 2D matrix and provides methods 
 * for performing basic operations like addition, subtraction, 
 * and multiplication. It encapsulates the matrix data and ensures 
 * proper validation of dimensions for operations.
 */
class Matrix {
    private int rows;       // Number of rows in the matrix
    private int columns;    // Number of columns in the matrix
    private int[][] data;   // 2D array to store the matrix elements

    /**
     * Initializes a matrix with dimensions and takes input from the user.
     *
     * @param rows    The number of rows in the matrix.
     * @param columns The number of columns in the matrix.
     */
    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        data = new int[rows][columns];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter elements for a " + rows + "x" + columns + " matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                data[i][j] = scanner.nextInt();
            }
        }
    }

    /**
     * Initializes a matrix with specified dimensions and optionally fills it with zeros.
     *
     * @param rows          The number of rows in the matrix.
     * @param columns       The number of columns in the matrix.
     * @param initializeZero If true, initializes all elements to zero.
     */
    public Matrix(int rows, int columns, boolean initializeZero) {
        this.rows = rows;
        this.columns = columns;
        data = new int[rows][columns];
        if (initializeZero) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    data[i][j] = 0;
                }
            }
        }
    }

    /**
     * Displays the matrix in a readable format.
     */
    public void display() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Adds the current matrix with another matrix.
     * Validates the dimensions before performing the operation.
     *
     * @param other The matrix to add to the current matrix.
     * @return A new Matrix object containing the addition result.
     * @throws IllegalArgumentException if the dimensions of the two matrices do not match.
     */
    public Matrix add(Matrix other) {
        validateDimensionsForAddition(other);
        Matrix result = new Matrix(rows, columns, true);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.data[i][j] = this.data[i][j] + other.data[i][j];
            }
        }
        return result;
    }

    /**
     * Subtracts another matrix from the current matrix.
     * Validates the dimensions before performing the operation.
     *
     * @param other The matrix to subtract from the current matrix.
     * @return A new Matrix object containing the subtraction result.
     * @throws IllegalArgumentException if the dimensions of the two matrices do not match.
     */
    public Matrix subtract(Matrix other) {
        validateDimensionsForAddition(other);
        Matrix result = new Matrix(rows, columns, true);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.data[i][j] = this.data[i][j] - other.data[i][j];
            }
        }
        return result;
    }

    /**
     * Multiplies the current matrix with another matrix.
     * Validates the dimensions before performing the operation.
     *
     * @param other The matrix to multiply with the current matrix.
     * @return A new Matrix object containing the multiplication result.
     * @throws IllegalArgumentException if the dimensions of the two matrices are incompatible.
     */
    public Matrix multiply(Matrix other) {
        validateDimensionsForMultiplication(other);
        Matrix result = new Matrix(rows, other.columns, true);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < other.columns; j++) {
                for (int k = 0; k < columns; k++) {
                    result.data[i][j] += this.data[i][k] * other.data[k][j];
                }
            }
        }
        return result;
    }

    /**
     * Validates if the dimensions of the current matrix and another matrix 
     * are compatible for addition or subtraction.
     *
     * @param other The matrix to validate against.
     * @throws IllegalArgumentException if the dimensions of the matrices do not match.
     */
    private void validateDimensionsForAddition(Matrix other) {
        if (this.rows != other.rows || this.columns != other.columns) {
            throw new IllegalArgumentException("Matrix dimensions must match for addition or subtraction.");
        }
    }

    /**
     * Validates if the dimensions of the current matrix and another matrix 
     * are compatible for multiplication.
     *
     * @param other The matrix to validate against.
     * @throws IllegalArgumentException if the dimensions of the matrices are incompatible for multiplication.
     */
    private void validateDimensionsForMultiplication(Matrix other) {
        if (this.columns != other.rows) {
            throw new IllegalArgumentException("Matrix dimensions are incompatible for multiplication.");
        }
    }
}

/**
 * Main class for demonstrating matrix operations.
 */
public class Asgmt01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input for the first matrix
        System.out.print("Enter rows and columns for the first matrix: ");
        int rows1 = scanner.nextInt();
        int columns1 = scanner.nextInt();
        Matrix matrix1 = new Matrix(rows1, columns1);

        // Input for the second matrix
        System.out.print("Enter rows and columns for the second matrix: ");
        int rows2 = scanner.nextInt();
        int columns2 = scanner.nextInt();
        Matrix matrix2 = new Matrix(rows2, columns2);
        
        // Display matrices
        System.out.println('\n');
        
        System.out.println("First Matrix:");
        matrix1.display();

        System.out.println("Second Matrix:");
        matrix2.display();
        
        System.out.println('\n');

        // Perform addition
        try {
            System.out.println("Addition Result:");
            Matrix additionResult = matrix1.add(matrix2);
            additionResult.display();
        } catch (Exception e) {
            System.out.println("Addition Error: " + e.getMessage());
        }

        // Perform subtraction
        try {
            System.out.println("Subtraction Result:");
            Matrix subtractionResult = matrix1.subtract(matrix2);
            subtractionResult.display();
        } catch (Exception e) {
            System.out.println("Subtraction Error: " + e.getMessage());
        }

        // Perform multiplication
        try {
            System.out.println("Multiplication Result:");
            Matrix multiplicationResult = matrix1.multiply(matrix2);
            multiplicationResult.display();
        } catch (Exception e) {
            System.out.println("Multiplication Error: " + e.getMessage());
        }
    }
}
