/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forecasting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Hari
 */
public class PrepareData {

    private final double actual[];
    private final int inputSize;
    private final int outputSize;
    public static ArrayList<Double> normalizedData = new ArrayList<Double>();
    public static int nmax;
    public static double totalDataSize;
    private static int classKey = 1;

    public PrepareData(final int inputSize, final int outputSize, final int classkey) {
        this.classKey = classkey;
        getdata();
        this.actual = new double[normalizedData.size()];
        this.inputSize = inputSize;
        this.outputSize = outputSize;

        for (int i = 0; i < normalizedData.size(); i++) {
            this.actual[i] = normalizedData.get(i);
        }
    }

    public void getInputData(final int offset, final double target[]) {
        for (int i = 0; i < this.inputSize; i++) {
            target[i] = this.actual[offset + i];
        }
    }

    public void getOutputData(final int offset, final double target[]) {
        for (int i = 0; i < this.outputSize; i++) {
            target[i] = this.actual[offset + this.inputSize + i];
        }
    }

    public static void getdata() {
        String dbUrl = "jdbc:mysql://localhost:3306/bhatbhateni";
        Connection con;
        Statement stmt;
        ArrayList<Integer> dat = new ArrayList<Integer>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, "root", "");
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select period.date, (select IFNULL(sum(qty),0) from sales where classkey='" + classKey + "' and  SALES.DATE=PERIOD.DATE)as qty from sales, period where  sales.classkey='" + classKey + "' group by period.date");
            int sum = 0;
            while (rs.next()) {
                dat.add(rs.getInt(2));
                sum += rs.getInt(2);
            }
            int max;
            max = Collections.max(dat);
            totalDataSize = dat.size();
            int avg = (int) (sum / totalDataSize);
            nmax = max + avg;
            for (int j = 0; j < totalDataSize; j++) {
                normalizedData.add((double) dat.get(j) / nmax);
            }
            con.close();
        } catch (Exception e) {
        }
    }

    public int getNmax() {
        return nmax;

    }

    public double getTotalDataSize() {
        return totalDataSize;

    }
}
