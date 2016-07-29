/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forecasting;
import java.io.Serializable;


/**
 *
 * @author Hari
 */

public class FeedforwardLayer implements Serializable {

	private static final long serialVersionUID = -3698708039331150031L;

	private double fire[];
	private Matrix matrix;
	private FeedforwardLayer next;
	private FeedforwardLayer previous;
	private final ActivationSigmoid activationFunction;

	public FeedforwardLayer(final ActivationSigmoid thresholdFunction,final int neuronCount) {
		this.fire = new double[neuronCount];
		this.activationFunction = thresholdFunction;
	}


	public FeedforwardLayer(final int neuronCount) {
		this(new ActivationSigmoid(), neuronCount);
	}


	public double[] computeLayerOutputs(final double pattern[]) {
		int i;
                
		if (pattern != null) {
			for (i = 0; i < getNeuronCount(); i++) {
				setFire(i, pattern[i]);
			}
		}
               
		final Matrix inputMatrix = createInputMatrix(this.fire);

		for (i = 0; i < this.next.getNeuronCount(); i++) {
			final Matrix col = this.matrix.getCol(i);
			final double sum = MatrixMath.dotProduct(col, inputMatrix);

			this.next.setFire(i, this.activationFunction.activationFunction(sum));
		}

		return this.fire;
	}


        private Matrix createInputMatrix(final double pattern[]) {
		final Matrix result = new Matrix(1, pattern.length + 1);
		for (int i = 0; i < pattern.length; i++) {
			result.set(0, i, pattern[i]);
		}

		result.set(0, pattern.length, 1);

		return result;
	}

	public double[] getFire() {
		return this.fire;
	}

	public double getFire(final int index) {
		return this.fire[index];
	}

	public Matrix getMatrix() {
		return this.matrix;
	}

	public int getMatrixSize() {
		if (this.matrix == null) {
			return 0;
		} else {
			return this.matrix.size();
		}
	}

	public int getNeuronCount() {
		return this.fire.length;
	}

	public FeedforwardLayer getNext() {
		return this.next;
	}

	public FeedforwardLayer getPrevious() {
		return this.previous;
	}

	public boolean hasMatrix() {
		return this.matrix != null;
	}

	public boolean isHidden() {
		return ((this.next != null) && (this.previous != null));
	}

	public boolean isInput() {
		return (this.previous == null);
	}

	public boolean isOutput() {
		return (this.next == null);
	}
        
        
	public void reset() {
		if (this.matrix != null) {
			this.matrix.ramdomize(0, 1);
		}
	}

	
	public void setFire(final int index, final double f) {
		this.fire[index] = f;
	}

	
	public void setMatrix(final Matrix matrix) {
		if (matrix.getRows() < 2) {
			throw new RuntimeException(
					"Weight matrix includes threshold values, and must have at least 2 rows.");
		}
		if (matrix != null) {
			this.fire = new double[matrix.getRows() - 1];
		}
		this.matrix = matrix;

	}

	
	public void setNext(final FeedforwardLayer next) {
		this.next = next;
		
		this.matrix = new Matrix(this.getNeuronCount() + 1, next.getNeuronCount());
	}


	public void setPrevious(final FeedforwardLayer previous) {
		this.previous = previous;
	}

	public ActivationSigmoid getActivationFunction() {
		return this.activationFunction;
	}

}
