<%-- 
    Document   : day_month_combine
    Created on : Jul 7, 2012, 7:55:44 PM
    Author     : Sagun
--%>


<%@ include file="navigation.html" %>

<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
        <title>Day Month Combined</title>
        <link type="text/css" href="resouces/css/Other.css" rel="stylesheet" />
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/json2.js"></script>
        <script src="js/highcharts.js"></script>
        <script src="js/ajax.js"></script>
        <script src="js/exporting.js"></script>
        <script type="text/javascript" src="js/daymonthcombined.js"></script>
    </head>
    <body id="te">

        <div id="container" style="width: 600px;left:375px;"></div>

        <!--        <div id="container" style="width: 800px; height: 600px; margin: 0px; float:left"></div>-->
        <!--  <div id="container1" style="width: 600px; height: 600px; margin: 0px; float:right"></div>-->
        <div id="container1" style="width: 600px; right:0; top:128px; height: 600px; position: absolute; z-index: 1"></div>

        <%@ page import="java.sql.*" %> 
        <%@ page import="java.io.*" %> 
        <%@ page import="java.lang.*"%>
        <%@ page import="java.util.Date"%>
        <div id="left-container">
            <table bgcolor="#D8D8D8">
                <tr>
                <select id="product" onchange="showState(this.value)">  
                    <option value="none">Select sID</option>  
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
                        </tr>
                        </table><div >
                            <button id="load">Add series</button>    <button id="button">Change type 1</button>
                            <button id="button1">Change type 2</button></div>
                        </div>  
                        <div id="foot">    <%@include file="footer.xhtml" %></div>
                        </body>
                        </html>
