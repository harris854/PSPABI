<%--
    Document   : heatmap
    Created on : Jul 7, 2012, 6:01:53 PM
    Author     : Sagun
--%>
<%@ include file="navigation.html" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Heatmap</title>


        <link type="text/css" href="css/base.css" rel="stylesheet" />
        <link type="text/css" href="css/Treemap.css" rel="stylesheet" />

        <!--[if IE]><script language="javascript" type="text/javascript" src="../../Extras/excanvas.js"></script><![endif]-->


        <script language="javascript" type="text/javascript" src="js/jit.js"></script>


        </div>
        <div id="json1" style="display: none" >
            <jsp:useBean id="mybean" class="Visualization.heatmap" />
            <%=mybean.retrieve()%></div>
        <script language="javascript" type="text/javascript" src="js/heatmap.js"></script>

    </head>
    <body id="te" onload="init()">

        <div id="container1">
            <div id="left-container">
                <div class="text">
                    <h4>
                        Heatmap
                    </h4>
                </div>

                <div id="id-list">
                    <table>
                        <tr>
                            <td>
                                <label for="r-sq">Squarified </label>
                            </td>
                            <td>
                                <input type="radio" id="r-sq" name="layout" checked="checked" value="left" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="r-st">Strip </label>
                            </td>
                            <td>
                                <input type="radio" id="r-st" name="layout" value="top" />
                            </td>
                            <tr>
                                <td>
                                    <label for="r-sd">SliceAndDice </label>
                                </td>
                                <td>
                                    <input type="radio" id="r-sd" name="layout" value="bottom" />
                                </td>
                            </tr>
                    </table>
                </div>
                <a id="back" href="#" class="theme button white">Go to Parent</a>
            </div>
            <div id="center-container">
                <div id="infovis"></div>
            </div>

        </div>
        <div id="foot">    <%@include file="footer.xhtml" %></div>
    </body>
</html>
