

<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%-- 
    Document   : drill_down_top_cat
    Created on : Jul 16, 2012, 2:53:13 PM
    Author     : Sagun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="navigation.html" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Drill down</title>
        <link type="text/css" href="resources/css/Other.css" rel="stylesheet" />
        <script type="text/javascript" src="js/json2.js"></script>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/drill_down_top_cat.js"></script>


    </head>
    <body id="te">

        <script src="js/highcharts.js"></script>
        <script src="js/modules/exporting.js"></script>

        <div id="container" style="min-width: 900px; height: 300px; "></div>
        <div id="container1" style="width: 99.9%; height: 300px;  top: 428px;position:absolute;"></div>

        <jsp:useBean id="mybean" class="Visualization.drill_down_top_cat" />
        <%
            String json1 = "";
            String json2 = "";
            HashMap<String, String> mp = mybean.retrieve_json();
            Set s = mp.entrySet();
            Iterator it = s.iterator();
            while (it.hasNext()) {
                Map.Entry m = (Map.Entry) it.next();
                json1 = (String) m.getKey();
                json2 = (String) m.getValue();
            }
        %>
        <div id="json_data" style="display: none" >
            <%= json1%></div>
        <div id="json_cat" style="display: none" >
            <%= json2%></div>
        <div id="foot">    <%@include file="footer.xhtml" %></div>
    </body>
</html>