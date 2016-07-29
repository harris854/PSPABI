<%-- 
    Document   : index
    Created on : Jul 1, 2012, 7:53:04 AM
    Author     : Sagun
--%>
<%@ include file="navigation.html" %>


<!DOCTYPE HTML>
<html>
    <head><link type="text/css" href="resources/css/Other.css" rel="stylesheet" />

        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/json2.js"></script>
        <script type="text/javascript" src="js/piebrand.js"></script>
    </head>
    <body id="te">

        <jsp:useBean id="mybean" class="Visualization.brand_pie" />
        <%  String product_id = request.getParameter("id");
            String date = request.getParameter("year");
        %>

        <div id="json" style="display: none" >
            <%=mybean.retrieve(product_id, date)%></div>
        <script src="js/highcharts.js"></script>
        <script src="js/modules/exporting.js"></script>
        <div id="container" style="min-width: 400px; height: 600px; margin: 0 auto"></div>

        <%@ page import="java.sql.*" %> 
        <%@ page import="java.io.*" %> 
        <%@ page import="java.lang.*"%>
        <%@ page import="java.util.Date"%>
        <FORM NAME="form1" ACTION="#" METHOD="POST">
            <div id="left-container"><table bgcolor="#D8D8D8">
                <td>
                <select name='id' onchange="showState(this.value)">  
                    <option value="none">Select ID</option>  
                    <%
                        Class.forName(
                                "com.mysql.jdbc.Driver").newInstance();
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhatbhateni", "root", "");
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("Select * from class order by classdesc ASC");


                        while (rs.next()) {
                    %>
                    <option value="<%=rs.getInt(1)%>"><%=rs.getString(2)%></option>  
                    <%
                        }
                    %>

                </select></td>
                    <td>
                    <select name='year' onchange="showState(this.value)">  
                        <option value="2011">Select Year</option>  
                        <%                    //Class.forName("com.mysql.jdbc.Driver").newInstance();  
                            // Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdatabase","root","");  
                            Statement stmt1 = con.createStatement();
                            ResultSet rs2 = stmt1.executeQuery("SELECT DISTINCT (YEAR) FROM period ORDER BY YEAR ASC ");


                            while (rs2.next()) {
                        %>
                        <option value="<%=rs2.getInt(1)%>"><%=rs2.getInt(1)%></option>  
                        <%
                            }
                        %>

                    </select></td>
                        <td><INPUT TYPE="submit" VALUE="show"></td></table></div>

                        </FORM>
                        <div id="foot">    <%@include file="footer.xhtml" %></div>
                        </body>
                        </html>
