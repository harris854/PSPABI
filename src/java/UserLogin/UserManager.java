
package UserLogin;

import java.sql.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hari
 */
public class UserManager {

   
    public static final String USER_SESSION_KEY = "user";
   
    private String username;
    private String password;
    private String passwordv;
    private String fname;
    private String lname;
    private String type;

   
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordv() {
        return passwordv;
    }

    public void setPasswordv(String passwordv) {
        this.passwordv = passwordv;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    

   
    public String validateUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        String connectionURL = "jdbc:mysql://localhost:3306/bhatbhateni";
        // String query2 = "select * from  users";
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionURL, "root", "");
            Statement s = connection.createStatement();
           
            if (fUser(username)) {
                String query = "select password, type from  users where username='" + username + "';";
                ResultSet rs1 = s.executeQuery(query);
               
                rs1.next();
                
                if (!rs1.getString(1).equals(password)) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Login Failed!",
                            "The password specified is not correct.");
                    context.addMessage(null, message);
                    return null;
                }

                context.getExternalContext().getSessionMap().put(USER_SESSION_KEY, username);
                if("admin".equals(rs1.getString(2))) {
                    return "adm_welcome";
                }
                else {
                    return "welcome";
                }

            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Login Failed!",
                        "Username '"
                        + username
                        + "' does not exist.");
                context.addMessage(null, message);
                return null;
            }

       

        //  connection.close();

    }
    catch (Exception e) {
    }

return null;

       
    }

    public String createUser() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        String connectionURL = "jdbc:mysql://localhost:3306/bhatbhateni";
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionURL, "root", "");
            Statement s = connection.createStatement();

            if (!fUser(username)) {
                if (!password.equals(passwordv)) {
                    FacesMessage message = new FacesMessage("The specified passwords do not match.  Please try again");
                    context.addMessage(null, message);
                    return null;
                }
                String query = "INSERT INTO `users` "
                                + "(`fname`, `lname`, `username`, `password`, `type`) VALUES ('" 
                                + fname + "',' " + lname + "','" + username + "','" + password + "','"+type+"')";
                s.executeUpdate(query);
                
                 connection.close();
                return "adm_welcome";
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Username '"
                        + username
                        + "' already exists!  ",
                        "Please choose a different username.");
                context.addMessage(null, message);
                return null;
            }
           
        } catch (Exception e) {
        }


       
        return null;
    }

   
    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "login";

    }

   

    private boolean fUser(String uname) {

        String connectionURL = "jdbc:mysql://localhost:3306/bhatbhateni";
        String query2 = "select username from  users";
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionURL, "root", "");
            Statement s = connection.createStatement();
            ResultSet rs1 = s.executeQuery(query2);
            rs1.beforeFirst();
            while (rs1.next()) {
                if (rs1.getString(1).equals(uname)) {
                    return true;
                } 
            }
            connection.close();

        } catch (Exception e) {
        }

        return false;
    }
}
