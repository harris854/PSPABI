/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forecasting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hari
 */
public class FeedforwardNetwork implements Serializable {

    private static final long serialVersionUID = -136440631687066461L;
    protected FeedforwardLayer inputLayer;// The input layer.
    protected FeedforwardLayer outputLayer;//The output layer.
    protected List<FeedforwardLayer> layers = new ArrayList<FeedforwardLayer>();//All of the layers in the neural network.
    private int max = new Integer(1);


    public FeedforwardNetwork() {
        this.max = 0;
    }

    //sequential creation of layers, should be of order,i/p,hidden,o/p
    public void addLayer(final FeedforwardLayer layer) {
        // setup the forward and back pointer
        if (this.outputLayer != null) {
            layer.setPrevious(this.outputLayer);
            this.outputLayer.setNext(layer);
        }
     
        if (this.layers.size() == 0) {
            this.inputLayer = this.outputLayer = layer;
        }
        else {
            this.outputLayer = layer;
        }

        this.layers.add(layer);
    }

    //RMS error calculate for given data
    public double calculateError(final double input[][], final double ideal[][]) throws RuntimeException {
        final ErrorCalculation errorCalculation = new ErrorCalculation();

        for (int i = 0; i < ideal.length; i++) {
            computeOutputs(input[i]);
            errorCalculation.updateError(this.outputLayer.getFire(), ideal[i]);
        }
        return (errorCalculation.calculateRMS());
    }

    
    public double[] computeOutputs(final double input[]) {

        if (input.length != this.inputLayer.getNeuronCount()) {
            throw new RuntimeException(
                    "Size mismatch: Can't compute outputs for input size="
                    + input.length + " for input layer size="
                    + this.inputLayer.getNeuronCount());
        }

        for (final FeedforwardLayer layer : this.layers) {
            if (layer.isInput()) {
                layer.computeLayerOutputs(input);
            }
            else if (layer.isHidden()) {
                layer.computeLayerOutputs(null);
            }
        }

        return this.outputLayer.getFire();
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public FeedforwardLayer getInputLayer() {
        return this.inputLayer;
    }

    public List<FeedforwardLayer> getLayers() {
        return this.layers;
    }

    public FeedforwardLayer getOutputLayer() {
        return this.outputLayer;
    }
    //reset all layers

    public void reset() {
        for (final FeedforwardLayer layer : this.layers) {
            layer.reset();
        }
    }
//	public int calculateNeuronCount() {
//		int result = 0;
//		for (final FeedforwardLayer layer : this.layers) {
//			result += layer.getNeuronCount();
//		}
//		return result;
//	}
//	
//	public int getWeightMatrixSize() {
//		int result = 0;
//		for (final FeedforwardLayer layer : this.layers) {
//			result += layer.getMatrixSize();
//		}
//		return result;
//	}
}
