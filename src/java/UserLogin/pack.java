/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserLogin;

/**
 *
 * @author Hari
 */
public class pack {

    private static String uname;
    private static String utype;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public pack() {
    }

    public pack(String uname, String utype) {
        this.uname = uname;
        this.utype = utype;
    }
}
