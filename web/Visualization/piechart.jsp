<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>PieChart</title>

        <link type="text/css" href="../css/base.css" rel="stylesheet" />
        <link type="text/css" href="../css/BarChart.css" rel="stylesheet" />

        <!--[if IE]><script language="javascript" type="text/javascript" src="../../Extras/excanvas.js"></script><![endif]-->

        <script language="javascript" type="text/javascript" src="../js/jit.js"></script>
        <script language="javascript" type="text/javascript" src="piechart.js"></script>
        <%@page import="JsonCreator.jsoncreator" %>
        <% jsoncreator qt1 = new jsoncreator();
            char[] str1 = qt1.get();%>

    </head>

    <body id="te" onload="init('<%out.print(str1);%>');">
        <%@include file="../header.xhtml" %>
        <div id="container1">

            <div id="left-container">
             
                <ul id="id-list"></ul>
                <ul>  <a id="home" href="../welcome.xhtml" class="theme button white">Home</a>
                </ul>
                

            </div>

            <div id="center-container">
                <div id="infovis"></div>    
            </div>

        </div>
            <div id="foot"><%@include file="../footer.xhtml" %></div>
    </body>
</html>
