
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%-- 
    Document   : day_vol_cat
    Created on : Jul 7, 2012, 7:13:30 PM
    Author     : Sagun
--%>

<%@ include file="navigation.html" %>
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<%@ page import="java.lang.*"%>
<%@ page import="java.util.*"%>

<html>
    <head>
        <title>Visualization</title>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/json2.js"></script>
        <script type="text/javascript" src="js/day_vol_cat.js"></script>
        <link type="text/css" href="resources/css/Other.css" rel="stylesheet" />
    </head>
    <body id="te">

        <script type="text/javascript" src="js/highcharts.js"></script>
        <script type="text/javascript" src="js/exporting.js"></script>

        <div id="container" style="min-width: 400px; height: 600px; margin: 0 auto"></div>
        <!--        <button id="button">Add series</button>-->


        <%
            String json = "";
            String json1 = "";
            String fyr = request.getParameter("fyear");
            String tyr = request.getParameter("tyear");
            String fmth = request.getParameter("fmonth");
            String tmth = request.getParameter("tmonth");
            String fdy = request.getParameter("fday");
            String tdy = request.getParameter("tday");
            String class_key = request.getParameter("classkey");


        %>
        <jsp:useBean id="mybean" class="Visualization.day_vol_cat" />
        <%
            HashMap<String, String> mp = mybean.retrieve_json(fyr, fmth, fdy, tyr, tmth, tdy, class_key);
            Set s = mp.entrySet();
            Iterator it = s.iterator();
            while (it.hasNext()) {
                Map.Entry m = (Map.Entry) it.next();
                json = (String) m.getKey();
                json1 = (String) m.getValue();
            }

        %>
        <div id="j1" style="display: none" >
            <%= json%></div>
        <div id="j2" style="display: none" >
            <%= json1%></div>
        <div id="left-container">
            <FORM NAME="form1" ACTION="#" METHOD="POST">

                <table bgcolor="#D8D8D8">            
                    <tr>  <td>   FROM &nbsp;&nbsp;&nbsp;
                            <select name ='fyear' onchange="showState(this.value)">  
                                <option value="2011">Select Year</option>  
                                <%
                                    //Class.forName("com.mysql.jdbc.Driver").newInstance();  
                                    // Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdatabase","root","");  
                                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhatbhateni", "root", "");

                                    Statement stmt1 = con.createStatement();
                                    ResultSet rs2 = stmt1.executeQuery("SELECT DISTINCT (year) FROM period ORDER BY YEAR ASC ");
                                    while (rs2.next()) {
                                %>
                                <option value="<%=rs2.getInt(1)%>"><%=rs2.getInt(1)%></option>


                                <%
                                    }
                                %>
                            </select></td>
                        <td>
                            <select name ='fmonth' onchange="showState(this.value)">  
                                <option value="1">Select Month</option>  
                                <%
                                    //Class.forName("com.mysql.jdbc.Driver").newInstance();  
                                    // Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdatabase","root","");  
                                    Statement stmt2 = con.createStatement();
                                    ResultSet rs8 = stmt2.executeQuery("SELECT DISTINCT (Month) FROM period ORDER BY month ASC ");
                                    while (rs8.next()) {
                                %>
                                <option value="<%=rs8.getInt(1)%>"><%=rs8.getInt(1)%></option>  
                                <%
                                    }
                                %>
                            </select>  </td>

                        <td>
                            <select name ='fday' onchange="showState(this.value)">  
                                <option value="1">Select Day</option>  
                                <%
                                    //Class.forName("com.mysql.jdbc.Driver").newInstance();  
                                    // Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdatabase","root","");  
                                    Statement stmt8 = con.createStatement();
                                    ResultSet rs12 = stmt8.executeQuery("SELECT DISTINCT (Day) FROM period ORDER BY Day ASC ");
                                    while (rs12.next()) {
                                %>
                                <option value="<%=rs12.getInt(1)%>"><%=rs12.getInt(1)%></option>  
                                <%
                                    }
                                %>
                            </select> </td>
                    </tr><tr><td>
                            TO &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <select name ='tyear' onchange="showState(this.value)">  
                                <option value="2011">Select Year</option>  
                                <%
                                    //Class.forName("com.mysql.jdbc.Driver").newInstance();  
                                    // Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdatabase","root","");  
                                    rs2.beforeFirst();
                                    while (rs2.next()) {
                                %>
                                <option value="<%=rs2.getInt(1)%>"><%=rs2.getInt(1)%></option>


                                <%
                                    }
                                %>
                            </select>  </td><td>
                            <select name ='tmonth' onchange="showState(this.value)">  
                                <option value="1">Select Month</option>  
                                <%
                                    rs8.beforeFirst();
                                    while (rs8.next()) {
                                %>
                                <option value="<%=rs8.getInt(1)%>"><%=rs8.getInt(1)%></option>  
                                <%
                                    }
                                %>
                            </select>    </td>
                        <td>
                            <select name ='tday' onchange="showState(this.value)">  
                                <option value="1">Select Day</option>  
                                <%
                                    //Class.forName("com.mysql.jdbc.Driver").newInstance();  
                                    // Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdatabase","root","");  
                                    rs12.beforeFirst();
                                    while (rs12.next()) {
                                %>
                                <option value="<%=rs12.getInt(1)%>"><%=rs12.getInt(1)%></option>  
                                <%
                                    }
                                %>
                            </select>     </td></tr>
                    <tr><td>
                            <select name ='classkey' onchange="showState(this.value)">  
                                <option value="1">Select ID</option>  
                                <%
                                    //Class.forName("com.mysql.jdbc.Driver").newInstance();  
                                    Statement stmt15 = con.createStatement();
                                    ResultSet rs15 = stmt15.executeQuery("Select * from class order by classdesc ASC");
                                    while (rs15.next()) // Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdatabase","root","");  
                                    {
                                %>
                                <option value="<%=rs15.getInt(1)%>"><%=rs15.getString(2)%></option>  
                                <%
                                    }

                                %>
                            </select>     </td></tr>
                </table>
                <td><INPUT TYPE="submit" VALUE="show"></td>
            </FORM></div>
        <div id="foot">    <%@include file="footer.xhtml" %></div>
    </body>
</html>






























