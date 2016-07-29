/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forecasting;

/*
 * @author Hari
 */
public class BackpropagationLayer {

    private final double error[];
    private final double errorDelta[];
    private Matrix correctionMatrix;
    private int biasRow;
    private Matrix previousCorectionMatrix;
    private final Backpropagation backpropagation;
    private final FeedforwardLayer layer;

    public BackpropagationLayer(final Backpropagation backpropagation, final FeedforwardLayer layer) {
        this.backpropagation = backpropagation;
        this.layer = layer;

        final int neuronCount = layer.getNeuronCount();

        this.error = new double[neuronCount];
        this.errorDelta = new double[neuronCount];

        if (layer.getNext() != null) {
            this.correctionMatrix = new Matrix(layer.getNeuronCount() + 1, layer.getNext().getNeuronCount());
            this.previousCorectionMatrix = new Matrix(layer.getNeuronCount() + 1, layer.getNext().getNeuronCount());
            this.biasRow = layer.getNeuronCount();
        }
    }

    public void correctionMatrixDelta(final int i1, final int i2, final double value) {
        this.correctionMatrix.add(i1, i2, value);
    }

    public void correctionThresholdDelta(final int index, final double value) {
        this.correctionMatrix.add(this.biasRow, index, value);
    }

    public void calcError() {

        final BackpropagationLayer next = this.backpropagation.getBackpropagationLayer(this.layer.getNext());

        for (int i = 0; i < this.layer.getNext().getNeuronCount(); i++) {
            for (int j = 0; j < this.layer.getNeuronCount(); j++) {
                correctionMatrixDelta(j, i, next.getErrorDelta(i) * this.layer.getFire(j));
                setError(j, getError(j) + this.layer.getMatrix().get(j, i) * next.getErrorDelta(i));
            }
            correctionThresholdDelta(i, next.getErrorDelta(i));
        }

        if (this.layer.isHidden()) {

            for (int i = 0; i < this.layer.getNeuronCount(); i++) {
                setErrorDelta(i, calculateDelta(i));
            }
        }

    }

    public void calcError(final double ideal[]) {


        for (int i = 0; i < this.layer.getNeuronCount(); i++) {
            setError(i, ideal[i] - this.layer.getFire(i));
            setErrorDelta(i, calculateDelta(i));
        }
    }

    private double calculateDelta(final int i) {
        return getError(i) * this.layer.getActivationFunction().derivativeFunction(this.layer.getFire(i));
    }

    public void clearError() {
        for (int i = 0; i < this.layer.getNeuronCount(); i++) {
            this.error[i] = 0;
        }
    }

    public double getError(final int index) {
        return this.error[index];
    }

    public double getErrorDelta(final int index) {
        return this.errorDelta[index];
    }

    public void learn(final double learnRate, final double momentum) {

        if (this.layer.hasMatrix()) {

            final Matrix m1 = MatrixMath.multiply(this.correctionMatrix, learnRate);
            final Matrix m2 = MatrixMath.multiply(this.previousCorectionMatrix, momentum);
            this.previousCorectionMatrix = MatrixMath.add(m1, m2);
            this.layer.setMatrix(MatrixMath.add(this.layer.getMatrix(), this.previousCorectionMatrix));
            this.correctionMatrix.clear();
        }
    }

    public void setError(final int index, final double e) {
        this.error[index] = e;
    }

    public void setErrorDelta(final int index, final double d) {
        this.errorDelta[index] = d;
    }
}
