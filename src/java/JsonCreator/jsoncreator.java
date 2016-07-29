package JsonCreator;

import Analysis.DatabaseConnect;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Hari
 */
public class jsoncreator {

    private static String viz;
    private static String category;
    private static String type;
    private static Date fdate = new Date();
    private static Date tdate = new Date();
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private static String fd = "";
    private static String td = "";

    public Date getFdate() {
        return fdate;
    }

    public void setFdate(Date fdate) {
        this.fdate = fdate;
    }

    public Date getTdate() {
        return tdate;
    }

    public void setTdate(Date tdate) {
        this.tdate = tdate;
    }

    public String getViz() {
        return viz;
    }

    public void setViz(String viz) {
        jsoncreator.viz = viz;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        jsoncreator.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static char[] getJson() {

        if (type == "date") {
            return get();
        }
        else {
            return get2();
        }
    }

    public static char[] get() {

        fd = df.format(fdate);
        td = df.format(tdate);

        String query1 = "select classdesc from class where classkey='" + category + "'";

        JSONObject json = new JSONObject();

        if (type == "date"){
            try {
                ResultSet cd = new DatabaseConnect().stmt.executeQuery(query1);
                cd.first();
                String cat = cd.getString(1);

                String query = "select date,sum(qty)  from sales where classkey='" + category + "' and date between '" + fd + "' and '" + td + "' group by date ";

                ResultSet res = new DatabaseConnect().stmt.executeQuery(query);


                res.first();
                JSONArray labels = new JSONArray();
                json.put("label", labels);
                labels.put(cat);

                JSONArray value = new JSONArray();
                json.put("values", value);


                while (res.next()) {
                    JSONObject date = new JSONObject();
                    date.put("label", res.getString(1).substring(5));

                    JSONArray vmon = new JSONArray();
                    date.put("values", vmon);
                    vmon.put(res.getInt(2));
                    value.put(date);

                }

                res.close();

            }
            catch (Exception e) {
            }
        }
        else {

            int sa[] = new int[7];
            String[] day = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

            try {


                ResultSet cd = new DatabaseConnect().stmt.executeQuery(query1);
                cd.first();
                String cat = cd.getString(1);

                String query = "select date,sum(qty)  from sales where classkey='" + category + "' and date between '" + fd + "' and '" + td + "' group by date ";
                ResultSet res = new DatabaseConnect().stmt.executeQuery(query);


                while (res.next()) {
                    Date date1 = res.getDate(1);
                    int day1 = date1.getDay();
                    sa[day1] += res.getInt(2);

                }
                res.close();

                JSONArray labels = new JSONArray();
                json.put("label", labels);
                labels.put(cat);

                JSONArray value = new JSONArray();
                json.put("values", value);
                int j = 0;
                for (int i = 0; i < 7; i++) {

                    JSONObject date = new JSONObject();
                    date.put("label", day[j]);

                    JSONArray vmon = new JSONArray();
                    date.put("values", vmon);
                    vmon.put(sa[j]);
                    value.put(date);
                    j++;

                }

            }
            catch (Exception e) {
            }

        }


        String ww = json.toString();

        char[] we = ww.toCharArray();


        for (int i = 0; i < we.length; i++) {
            if (we[i] == '"') {
                we[i] = '`';
            }
        }
        return we;
    }

    public String result() {
        return viz;
    }

    public static char[] get2() {
        fd = df.format(fdate);
        td = df.format(tdate);
        String query1 = "select classdesc from class where classkey='" + category + "'";

        JSONObject json2 = new JSONObject();

        int sa[] = new int[7];
        String[] day = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        try {


            ResultSet cd = new DatabaseConnect().stmt.executeQuery(query1);
            cd.first();
            String cat = cd.getString(1);

            String query = "select date,sum(qty)  from sales where classkey='" + category + "' and date between '" + fd + "' and '" + td + "' group by date ";
            ResultSet res = new DatabaseConnect().stmt.executeQuery(query);


            while (res.next()) {
                Date date1 = res.getDate(1);
                int day1 = date1.getDay();
                sa[day1] += res.getInt(2);

            }
            res.close();

            JSONArray labels = new JSONArray();
            json2.put("label", labels);
            labels.put(cat);

            JSONArray value = new JSONArray();
            json2.put("values", value);
            int j = 0;
            for (int i = 0; i < 7; i++) {

                JSONObject date = new JSONObject();
                date.put("label", day[j]);

                JSONArray vmon = new JSONArray();
                date.put("values", vmon);
                vmon.put(sa[j]);
                value.put(date);
                j++;

            }

        }
        catch (Exception e) {
        }


        String ww = json2.toString();

        char[] we = ww.toCharArray();


        for (int i = 0; i < we.length; i++) {
            if (we[i] == '"') {
                we[i] = '`';
            }
        }

        return we;


    }
//
//    public static char[] get3() {
//
//        String query1 = "select date from period where month='" + fmdate + "' and year='" + fydate + "' and day='" + fddate + "'";
//        String query2 = "select date from period where month='" + tmdate + "' and year='" + tydate + "' and day='" + tddate + "'";
//        String query3 = "select classdesc from class where classkey='" + category + "'";
//
//        JSONObject json2 = new JSONObject();
//        String dfrom = new String();
//        String dto = new String();
//
//        int sa[] = new int[7];
//        String[] day = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
//
//        try {
//            ResultSet fd = new DatabaseConnect().stmt.executeQuery(query1);
//            fd.first();
//            dfrom = fd.getString(1);
//            ResultSet td = new DatabaseConnect().stmt.executeQuery(query2);
//            td.first();
//            dto = td.getString(1);
//
//            ResultSet cd = new DatabaseConnect().stmt.executeQuery(query3);
//            cd.first();
//            String cat = cd.getString(1);
//
//            String query = "select date,qty,productcode  from vizdata where classkey='" + category + "' and date between '" + dfrom + "' and '" + dto + "' order by date ";
//            ResultSet res = new DatabaseConnect().stmt.executeQuery(query);
//
//
//            while (res.next()) {
//                Date date1 = res.getDate(1);
//                int day1 = date1.getDay();
//                sa[day1] += res.getInt(2);
//
//            }
//            res.close();
//
//            JSONArray labels = new JSONArray();
//            json2.put("label", labels);
//            labels.put(cat);
//
//            JSONArray value = new JSONArray();
//            json2.put("values", value);
//            int j = 0;
//            for (int i = 0; i < 7; i++) {
//
//                JSONObject date = new JSONObject();
//                date.put("label", day[j]);
//
//                JSONArray vmon = new JSONArray();
//                date.put("values", vmon);
//                vmon.put(sa[j]);
//                value.put(date);
//                j++;
//
//            }
//
//        }
//        catch (Exception e) {
//        }
//
//
//        String ww = json2.toString();
//
//        char[] we = ww.toCharArray();
//
//
//        for (int i = 0; i < we.length; i++) {
//            if (we[i] == '"') {
//                we[i] = '`';
//            }
//        }
//
//        return we;
//
//
//    }
}