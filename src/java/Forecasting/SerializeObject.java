/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forecasting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * @author Hari
 */
public class SerializeObject {

    public static Serializable load(final String filename) throws IOException, ClassNotFoundException {
        Serializable object;
        FileInputStream fis = null;
        ObjectInputStream in = null;
        fis = new FileInputStream(filename);
        in = new ObjectInputStream(fis);
        object = (Serializable) in.readObject();
        in.close();
        return object;
    }

    public static void save(final String filename, final Serializable object) throws IOException {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;

        fos = new FileOutputStream(filename);
        out = new ObjectOutputStream(fos);
        out.writeObject(object);
        out.close();
    }
}
