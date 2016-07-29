/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forecasting;


/*
 * @author Hari
 */
public class MatrixMath {

    public static Matrix add(final Matrix a, final Matrix b) {
        if (a.getRows() != b.getRows()) {
            throw new RuntimeException(
                    "To add the matrices they must have the same number of rows and columns.  Matrix a has "
                    + a.getRows()
                    + " rows and matrix b has "
                    + b.getRows() + " rows.");
        }

        if (a.getCols() != b.getCols()) {
            throw new RuntimeException(
                    "To add the matrices they must have the same number of rows and columns.  Matrix a has "
                    + a.getCols()
                    + " cols and matrix b has "
                    + b.getCols() + " cols.");
        }

        final double result[][] = new double[a.getRows()][a.getCols()];

        for (int resultRow = 0; resultRow < a.getRows(); resultRow++) {
            for (int resultCol = 0; resultCol < a.getCols(); resultCol++) {
                result[resultRow][resultCol] = a.get(resultRow, resultCol)
                        + b.get(resultRow, resultCol);
            }
        }

        return new Matrix(result);
    }

    public static double dotProduct(final Matrix a, final Matrix b) {
        if (!a.isVector() || !b.isVector()) {
            throw new RuntimeException(
                    "To take the dot product, both matrices must be vectors.");
        }

        final Double aArray[] = a.toPackedArray();
        final Double bArray[] = b.toPackedArray();

        if (aArray.length != bArray.length) {
            throw new RuntimeException(
                    "To take the dot product, both matrices must be of the same length.");
        }

        double result = 0;
        final int length = aArray.length;

        for (int i = 0; i < length; i++) {
            result += aArray[i] * bArray[i];
        }

        return result;
    }

    public static Matrix multiply(final Matrix a, final double b) {
        final double result[][] = new double[a.getRows()][a.getCols()];
        for (int row = 0; row < a.getRows(); row++) {
            for (int col = 0; col < a.getCols(); col++) {
                result[row][col] = a.get(row, col) * b;
            }
        }
        return new Matrix(result);
    }

    private MatrixMath() {
    }
}
