

<%-- 
    
    Created on : Jun 28, 2012, 6:13:23 PM
    Author     : Sagun
--%>

<%@ include file="navigation.html" %>

<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Day_wise</title>
        <link type="text/css" href="resources/css/Other.css" rel="stylesheet" />
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/json2.js"></script>
        <script src="js/highcharts.js"></script>
        <script src="js/ajax.js"></script>
        <script src="js/exporting.js"></script>
        <script type="text/javascript" src="js/day_wise.js"></script>
    </head>
    <body id="te">

        <div id="container" style="min-width: 400px; height: 600px; margin: 0 auto"></div>

        <%@ page import="java.sql.*" %> 
        <%@ page import="java.io.*" %> 
        <%@ page import="java.lang.*"%>
        <%@ page import="java.util.Date"%>

        <div id="left-container"><table bgcolor="#D8D8D8">
                <tr>
                <select id="product" onchange="showState(this.value)">  
                    <option value="none">Select Category</option>  
                    <%
                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhatbhateni", "root", "");
                        Statement stmt = con.createStatement();
                        ResultSet rs3 = stmt.executeQuery("Select * from class order by classdesc ASC");
                        while (rs3.next()) {
                    %>
                    <option value="<%=rs3.getInt(1)%>"><%=rs3.getString(2)%></option>  
                    <%
                        }
                    %>
                </select>
                </tr>
                <tr>
                <select id="date" onchange="showState(this.value)">  
                    <option value="2011">Select Year</option>  
                    <%
                        //Class.forName("com.mysql.jdbc.Driver").newInstance();  
                        // Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdatabase","root","");  
                        Statement stmt1 = con.createStatement();
                        ResultSet rs2 = stmt1.executeQuery("SELECT DISTINCT (YEAR) FROM period ORDER BY YEAR ASC ");
                        while (rs2.next()) {
                    %>
                    <option value="<%=rs2.getInt(1)%>"><%=rs2.getInt(1)%></option>  
                    <%
                        }
                    %>
                </select></tr></table>
            <button id="load">Add series</button>    <button id="button">Change type</button></div>
        <div id="foot">    <%@include file="footer.xhtml" %></div>
    </body>
</html>

