/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonCreator;

import java.sql.*;
import java.util.*;

/**
 *
 * @author Hari
 */
public class category {

    private static Map<String, Object> cat;
    private static Map<String, Object> day1;
    private static Map<String, Object> month1;
    private static Map<String, Object> year1;

    public Map<String, Object> getCat() {
        return cat;
    }

    public void setCat(Map<String, Object> cat) {
        this.cat = cat;
    }

    public Map<String, Object> getDay1() {
        return day1;
    }

    public void setDay1(Map<String, Object> day1) {
        this.day1 = day1;
    }

    public Map<String, Object> getMonth1() {
        return month1;
    }

    public void setMonth1(Map<String, Object> month1) {
        this.month1 = month1;
    }

    public Map<String, Object> getYear1() {
        return year1;
    }

    public void setYear1(Map<String, Object> year1) {
        this.year1 = year1;
    }

    public category() {

        cat = new LinkedHashMap<String, Object>();
        day1 = new LinkedHashMap<String, Object>();
        month1 = new LinkedHashMap<String, Object>();
        year1 = new LinkedHashMap<String, Object>();
        String connectionURL = "jdbc:mysql://localhost:3306/bhatbhateni";
        String query = "select * from class order by classdesc asc";
        String query1 = "select distinct day from period order by day asc";
        String query2 = "select distinct month from period order by month asc";
        String query3 = "select distinct YEAR from period order by YEAR asc";

        Connection connection = null;

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionURL, "root", "");
            Statement s = connection.createStatement();


            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                cat.put(rs.getString(2), rs.getString(1));
            }

            ResultSet rs1 = s.executeQuery(query1);
            rs1.beforeFirst();
            while (rs1.next()) {
                day1.put(rs1.getString(1), rs1.getString(1));
            }

            ResultSet rs2 = s.executeQuery(query2);
            rs2.beforeFirst();
            while (rs2.next()) {
                month1.put(rs2.getString(1), rs2.getString(1));
            }

            ResultSet rs3 = s.executeQuery(query3);
            rs3.beforeFirst();
            while (rs3.next()) {
                year1.put(rs3.getString(1), rs3.getString(1));
            }

            connection.close();

        } catch (Exception e) {
        }

    }

    public static void main(String args[]) {

        cat = new LinkedHashMap<String, Object>();
        day1 = new LinkedHashMap<String, Object>();
        month1 = new LinkedHashMap<String, Object>();
        year1 = new LinkedHashMap<String, Object>();
        String connectionURL = "jdbc:mysql://localhost:3306/bhatbhateni";
        String query = "select * from class order by classdesc asc";
        String query1 = "select distinct day from period order by day asc";
        String query2 = "select distinct month from period order by month asc";
        String query3 = "select distinct YEAR from period order by YEAR asc";

        Connection connection = null;

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionURL, "root", "");
            Statement s = connection.createStatement();


            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                cat.put(rs.getString(2), rs.getString(1));
            }

            ResultSet rs1 = s.executeQuery(query1);
            rs1.beforeFirst();
            while (rs1.next()) {
                day1.put(rs1.getString(1), rs1.getString(1));
            }

            ResultSet rs2 = s.executeQuery(query2);
            rs2.beforeFirst();
            while (rs2.next()) {
                month1.put(rs2.getString(1), rs2.getString(1));
            }

            ResultSet rs3 = s.executeQuery(query3);
            rs3.beforeFirst();
            while (rs3.next()) {
                year1.put(rs3.getString(1), rs3.getString(1));
            }

            connection.close();

            System.out.println(cat);
            System.out.println(day1);
            System.out.println(month1);
            System.out.println(year1);
        } catch (Exception e) {
        }

    }
}
