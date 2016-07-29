/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Hari
 */
public class Users {

    private ArrayList<pack> cList = new ArrayList<pack>();

    public ArrayList<pack> getcList() {
        return cList;
    }

    public void setcList(ArrayList<pack> cList) {
        this.cList = cList;
    }

    public Users() {
        cList.clear();
        String connectionURL = "jdbc:mysql://localhost:3306/bhatbhateni";
        // String query2 = "select * from  users";
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionURL, "root", "");
            Statement s = connection.createStatement();

            String query = "select username, type from  users;";
            ResultSet rs1 = s.executeQuery(query);
rs1.beforeFirst();
            while (rs1.next()) {

                cList.add(new pack(rs1.getString("username"), rs1.getString("type")));
            }

        }
        catch (Exception e) {
        }




    }
}
