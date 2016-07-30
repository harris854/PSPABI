/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Analysis;

import java.sql.*;

/**
 *
 * @author Hari
 */
public class DatabaseConnect {
    String dbName="bhatbhateni";
    String dbUrl = "jdbc:mysql://localhost:3306/"+dbName;
    Connection con;
    public Statement stmt;

    public DatabaseConnect() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, "root", "");

            stmt = con.createStatement();

        }
        catch (Exception e) {
        }


    }

    public void close() {
        try {
            con.close();
        }
        catch (Exception e) {
        }
    }
}
