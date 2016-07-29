/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forecasting;

/**
 *
 * @author Hari
 */
public class ErrorCalculation {

    private double globalError;
    private int setSize;

    //give RMS error for whole network
    public double calculateRMS() {
        final double err = Math.sqrt(this.globalError / (this.setSize));
        return err;

    }

    public void reset() {
        this.globalError = 0;
        this.setSize = 0;
    }

    public void updateError(final double actual[], final double ideal[]) {
        for (int i = 0; i < actual.length; i++) {
            final double delta = ideal[i] - actual[i];
            this.globalError += delta * delta;
        }
        this.setSize += ideal.length;
    }
}
