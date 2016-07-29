/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forecasting;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hari
 */
public final class PredictData {
    //  public final static int ACTUAL_SIZE = 150;

    private static int trainSetsize = 135;
    public final static int inputSize = 7;
    public final static int outputSize = 1;
    public final static int hiddenSize1 = 9;
    //  public final static int hiddenSize2 = 0;
    private static double learnRate = 0.07;
    private static double momentum = 0.5;
    private PrepareData prepare;
    private FeedforwardNetwork network;
    private double input[][];
    private double ideal[][];
    private static int classKey = 58;
    private static ArrayList<String> preData = new ArrayList<String>();
    public static String json = "";
    private static String actualData = "";
    private static String predictData = "";
    private static ArrayList<Integer> dat1 = new ArrayList<Integer>();
    private static String resultdata = "";

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getActualData() {
        return actualData;
    }

    public void setActualData(String actualData) {
        this.actualData = actualData;
    }

    public String getPredictData() {
        return predictData;
    }

    public void setPredictData(String predictData) {
        this.predictData = predictData;
    }

    public String getResultdata() {
        return resultdata;
    }

    public void setResultdata(String resultdata) {
        this.resultdata = resultdata;
    }

    public double getLearnRate() {
        return learnRate;
    }

    public void setLearnRate(double learnRate) {
        this.learnRate = learnRate;
    }

    public double getMomentum() {
        return momentum;
    }

    public void setMomentum(double momentum) {
        this.momentum = momentum;
    }

    public int getClassKey() {
        return classKey;
    }

    public void setClassKey(int classKey) {
        this.classKey = classKey;
    }

    public ArrayList<String> getPreData() {
        return preData;
    }

    public void setPreData(ArrayList<String> preData) {
        this.preData = preData;
    }


    public static void main(final String args[]) throws ClassNotFoundException, IOException {
        final PredictData wave = new PredictData();
        wave.operate();
    }
    public PredictData() {

        //operate();
    }

    public void operate()  {
            generateData();
            createNetwork();
            generateTrainingSets();
            trainNetworkBackprop();
            saveNeuralNetwork();
            display();
       
    }

     public void operate2()  {
         
         loadNeuralNetwork();
            display2();  
     }
    private void generateData() {
        this.prepare = new PrepareData(PredictData.inputSize, PredictData.outputSize, PredictData.classKey);
    }

    private void generateTrainingSets() {
        this.input = new double[trainSetsize][inputSize];
        this.ideal = new double[trainSetsize][outputSize];

        for (int i = 0; i < trainSetsize; i++) {
            this.prepare.getInputData(i, this.input[i]);
            this.prepare.getOutputData(i, this.ideal[i]);
        }
    }

    public void createNetwork() {
        final ActivationSigmoid threshold = new ActivationSigmoid();
        this.network = new FeedforwardNetwork();
        this.network.addLayer(new FeedforwardLayer(threshold, inputSize));
        this.network.addLayer(new FeedforwardLayer(threshold, PredictData.hiddenSize1));
//        if (PredictData.hiddenSize2 > 0) {
//            this.network.addLayer(new FeedforwardLayer(threshold, PredictData.hiddenSize2));
//        }
        this.network.addLayer(new FeedforwardLayer(threshold, outputSize));

        this.network.reset();
    }

    private void trainNetworkBackprop() {
        final Backpropagation train = new Backpropagation(this.network, this.input, this.ideal, learnRate, momentum);
        int epoch = 1;
        do {
            train.iteration();
            //System.out.println("Iteration #" + epoch + " Error:" + train.getNetworkError());
            epoch++;
        } while ((epoch < 50) && (train.getNetworkError() > 0.01));
        //System.out.println(train.getNetworkError());
        this.network.setMax(prepare.getNmax());

    }

    private void display() {


        final NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMinimumFractionDigits(2);

        final double input1[] = new double[PredictData.inputSize];
        final double output[] = new double[PredictData.outputSize];
        double mac = this.network.getMax();
        final StringBuilder str1 = new StringBuilder();
        final StringBuilder str2 = new StringBuilder();
        int flag = 0;
        //System.out.println(trains);
        preData.clear();
        for (int i = PredictData.inputSize; i < prepare.totalDataSize - 7; i++) {

            if (flag != 0) {
                str1.append(",");
                str2.append(",");
            }
            flag++;


            this.prepare.getInputData(i - PredictData.inputSize, input1);
            this.prepare.getOutputData(i - PredictData.inputSize, output);

            final double predict[] = this.network.computeOutputs(input1);

            final StringBuilder str = new StringBuilder();
            str.append(i);
            str.append(":Actual=");
            for (int j = 0; j < output.length; j++) {
                if (j > 0) {
                    str.append(',');
                }
                str.append(Math.round(output[j] * mac));
                str1.append(Math.round(output[j] * mac));

            }


            str.append(":Predicted=");
            for (int j = 0; j < output.length; j++) {
                if (j > 0) {
                    str.append(',');
                }
               // System.out.println(predict[j]*mac);
                str.append(Math.round(predict[j] * mac));
                str2.append(Math.round(predict[j] * mac));
            }

            str.append(":Difference=");


            final ErrorCalculation error = new ErrorCalculation();
            error.updateError(predict, output);

            str.append(percentFormat.format(error.calculateRMS()));


            preData.add(str.toString());

        }

        setActualData(str1.toString());
        setPredictData(str2.toString());
        setJson(0);
        // System.out.println(preData+"train");




    }

    private void display2() {

        final NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMinimumFractionDigits(2);

        final double input1[] = new double[PredictData.inputSize];
        final double output[] = new double[PredictData.outputSize];
        double mac = this.network.getMax();
        final StringBuilder str1 = new StringBuilder();
        final StringBuilder str2 = new StringBuilder();
        int flag = 0;
        preData.clear();
        getdata();

        final StringBuilder str11 = new StringBuilder();

        int flag1 = 0;

        final StringBuilder str = new StringBuilder();
        str.append("Input data:[");

        for (int i = 0; i < dat1.size(); i++) {
            if (flag1 != 0) {
                str11.append(",");
            }
            flag1++;
            if (i > 0) {
                str.append(',');
            }

            input1[i] = dat1.get(i) / mac;
            str.append(Math.round(input1[i] * mac));
            str11.append(Math.round(input1[i] * mac));
        }
        str.append("]");

        final double predict[] = this.network.computeOutputs(input1);


        str.append("\n Predicted=[");
        for (int j = 0; j < output.length; j++) {
            if (j > 0) {
                str.append(',');
            }
            str.append(Math.round(predict[j] * mac));
            str11.append(",");
            str11.append(Math.round(predict[j] * mac));
        }
        str.append("]");

        preData.add(str.toString());

        setResultdata(str11.toString());
        setJson(1);
        System.out.println(preData + "predict");

    }

    public void saveNeuralNetwork() {
        try {
            SerializeObject.save("predict_" + classKey + ".net", this.network);
        } catch (IOException ex) {
            Logger.getLogger(PredictData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadNeuralNetwork() {
        try {
            this.network = (FeedforwardNetwork) SerializeObject.load("predict_" + classKey + ".net");
        } catch (IOException ex) {
            Logger.getLogger(PredictData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PredictData.class.getName()).log(Level.SEVERE, null, ex);
        }
      
            
        
    }

    public void setJson(int i) {
        if (i == 0) {
            json = "";
            json += "[{";
            String desc1 = "Actual";
            String desc2 = "Predicted";
            json += "\"name\":\"" + desc1 + "\",";
            json += "\"data\":[";
            json += getActualData();
            json += "]},";
            json += "{";
            json += "\"name\":\"" + desc2 + "\",";
            json += "\"data\":[";
            json += getPredictData();
            json += "]}]";


        } else if (i == 1) {
            json = "";
            json += "[{";
            String output = "Predict";
            json += "\"name\":\"" + output + "\",";
            json += "\"data\":[";
            json += getResultdata();
            json += "]}]";

        }
    }

    private static void getdata() {
        String dbUrl = "jdbc:mysql://localhost:3306/bhatbhateni";
        Connection con;
        Statement stmt;


        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, "root", "");
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select period.date, (select IFNULL(sum(qty),0) from sales where classkey='" + classKey + "' and  SALES.DATE=PERIOD.DATE)as qty from sales, period where  sales.classkey='" + classKey + "' group by period.date order by date desc limit 7");
            dat1.clear();
            while (rs.next()) {
                dat1.add(rs.getInt(2));
            }

            con.close();
        } catch (Exception e) {
        }
    }

   
}
