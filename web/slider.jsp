<%-- 
    Document   : slider
    Created on : Jul 6, 2012, 7:02:58 PM
    Author     : Sagun
--%>
<%@ include file="navigation.html" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Slider Vizualization</title>
        <link type="text/css" href="resources/css/Other.css" rel="stylesheet" />
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/json2.js"></script>
        <script src="js/highcharts.js"></script>
        <script src="js/ajax.js"></script>
        <script src="js/exporting.js"></script>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/slider.js"></script>

    </head>
    <body id="te">

        <script src="js/highstock.js"></script>
        <script src="js/modules/exporting.js"></script>

        <div id="container" style="left:300px;width:1200px;"></div>
        <%@ page import="java.sql.*" %> 
        <%@ page import="java.io.*" %> 
        <%@ page import="java.lang.*"%>
        <%@ page import="java.util.Date"%>      
        <div id="left-container"><table>                
                <tr>  <select id = "classkey" onchange="showState(this.value)">  
                    <option value="58">Select ID</option>  
                    <%
                        //Class.forName("com.mysql.jdbc.Driver").newInstance();  
                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhatbhateni", "root", "");
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("Select * from class order by classdesc ASC");
                        while (rs.next()) // Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdatabase","root","");  
                        {
                    %>
                    <option value="<%=rs.getInt(1)%>"><%=rs.getString(2)%></option>  
                    <%
                        }

                    %>
                </select>
                </tr></table>     

            <button id="load">Add series</button>  </div>

        <div id="foot">    <%@include file="footer.xhtml" %></div>
    </body>
</html>
