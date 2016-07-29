/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forecasting;

import java.io.Serializable;

/*
 *
 * @author Hari
 */
public class ActivationSigmoid implements Serializable{

   
    private static final long serialVersionUID = 5622349801036468572L;

 
    public double activationFunction(final double d) {
        return 1.0 / (1 + Math.exp(-1.0 * d));
    }

  //for back propagation
    public double derivativeFunction(double d) {
        return d * (1.0 - d);
    }
}
