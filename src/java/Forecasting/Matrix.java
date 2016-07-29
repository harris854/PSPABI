/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forecasting;

import java.io.Serializable;


/*.
 * @author Hari
 */
public class Matrix implements Cloneable, Serializable {

    private static final long serialVersionUID = -7977897210426471675L;
    double matrix[][];

    public Matrix(final double sourceMatrix[][]) {
        this.matrix = new double[sourceMatrix.length][sourceMatrix[0].length];
        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getCols(); c++) {
                this.set(r, c, sourceMatrix[r][c]);
            }
        }
    }

    public Matrix(final int rows, final int cols) {
        this.matrix = new double[rows][cols];
    }

    public void add(final int row, final int col, final double value) {
        validate(row, col);
        final double newValue = get(row, col) + value;
        set(row, col, newValue);
    }

    public void clear() {
        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getCols(); c++) {
                set(r, c, 0);
            }
        }
    }

    public double get(final int row, final int col) {
        validate(row, col);
        return this.matrix[row][col];
    }

    public Matrix getCol(final int col) {
        if (col > getCols()) {
            throw new RuntimeException("Can't get column #" + col
                    + " because it does not exist.");
        }

        final double newMatrix[][] = new double[getRows()][1];

        for (int row = 0; row < getRows(); row++) {
            newMatrix[row][0] = this.matrix[row][col];
        }

        return new Matrix(newMatrix);
    }

    public int getCols() {
        return this.matrix[0].length;
    }

    public int getRows() {
        return this.matrix.length;
    }

    public boolean isVector() {
        if (getRows() == 1) {
            return true;
        } else {
            return getCols() == 1;
        }
    }

    public void ramdomize(final double min, final double max) {
        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getCols(); c++) {
                this.matrix[r][c] = (Math.random() * (max - min)) + min;
            }
        }
    }

    public void set(final int row, final int col, final double value) {
        validate(row, col);
        if (Double.isInfinite(value) || Double.isNaN(value)) {
            throw new RuntimeException("Trying to assign invalud number to matrix: "
                    + value);
        }
        this.matrix[row][col] = value;
    }

    public int size() {
        return this.matrix[0].length * this.matrix.length;
    }

    public Double[] toPackedArray() {
        final Double result[] = new Double[getRows() * getCols()];

        int index = 0;
        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getCols(); c++) {
                result[index++] = this.matrix[r][c];
            }
        }

        return result;
    }

    private void validate(final int row, final int col) {
        if ((row >= getRows()) || (row < 0)) {
            throw new RuntimeException("The row:" + row + " is out of range:"
                    + getRows());
        }

        if ((col >= getCols()) || (col < 0)) {
            throw new RuntimeException("The col:" + col + " is out of range:"
                    + getCols());
        }
    }
}
