/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forecasting;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Hari
 */
public class Backpropagation {

    private double networkError;// The networkError from the last iteration.
    private final double learnRate;//The learning rate. This is the degree to which the deltas will affect the current network.
    private final double momentum;//The momentum, this is the degree to which the previous training cycle affects the current one.
    private final FeedforwardNetwork network;//THe network that is being trained.
    //A map between neural network layers and the  BackpropagationLayer.
    private final Map<FeedforwardLayer, BackpropagationLayer> layerMap = new HashMap<FeedforwardLayer, BackpropagationLayer>();
    private final double input[][];//Input patterns to train with.
    private final double ideal[][];//The ideal output for each of the input patterns.

    public Backpropagation(final FeedforwardNetwork network, final double input[][], final double ideal[][], final double learnRate, final double momentum) {
        this.network = network;
        this.learnRate = learnRate;
        this.momentum = momentum;
        this.input = input;
        this.ideal = ideal;

        for (final FeedforwardLayer layer : network.getLayers()) {
            final BackpropagationLayer bpl = new BackpropagationLayer(this, layer);
            this.layerMap.put(layer, bpl);
        }
    }

    //calc networkError for each layer and set accmatrix
    public void calcError(final double ideal[]) {

        if (ideal.length != this.network.getOutputLayer().getNeuronCount()) {
            throw new RuntimeException(
                    "Size mismatch: Can't calcError for ideal input size="
                    + ideal.length + " for output layer size="
                    + this.network.getOutputLayer().getNeuronCount());
        }

        // clear out all previous networkError data
        for (final FeedforwardLayer layer : this.network.getLayers()) {
            getBackpropagationLayer(layer).clearError();
        }

        for (int i = this.network.getLayers().size() - 1; i >= 0; i--) {
            final FeedforwardLayer layer = this.network.getLayers().get(i);
            if (layer.isOutput()) {

                getBackpropagationLayer(layer).calcError(ideal);
            } else {
                getBackpropagationLayer(layer).calcError();
            }
        }
    }

    //get backpropagationlayer for the given layer
    public BackpropagationLayer getBackpropagationLayer(final FeedforwardLayer layer) {
        final BackpropagationLayer result = this.layerMap.get(layer);

        if (result == null) {
            throw new RuntimeException(
                    "Layer unknown to backpropagation trainer, was a layer added after training begain?");
        }

        return result;
    }

    //RMS networkError for training set
    public double getNetworkError() {
        return this.networkError;
    }

    public FeedforwardNetwork getNetwork() {
        return this.network;
    }
    //iteration of training

    public void iteration() {

        for (int j = 0; j < this.input.length; j++) {
            this.network.computeOutputs(this.input[j]);
            calcError(this.ideal[j]);
        }
        learn();

        this.networkError = this.network.calculateError(this.input, this.ideal);
    }

    //modify matrix of weight and threshold based on calcError
    public void learn() {

        for (final FeedforwardLayer layer : this.network.getLayers()) {
            getBackpropagationLayer(layer).learn(this.learnRate, this.momentum);
        }

    }
}
