<%-- 
    Document   : clusterviz
    Created on : Jul 17, 2012, 8:25:02 PM
    Author     : Jwala
--%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Cluster</title>


        <link type="text/css" href="css/base_1.css" rel="stylesheet" />
        <link type="text/css" href="css/Hypertree.css" rel="stylesheet" />

        <!--[if IE]><script language="javascript" type="text/javascript" src="../../Extras/excanvas.js"></script><![endif]-->

        <script language="javascript" type="text/javascript" src="js/jit.js"></script>
        <script language="javascript" type="text/javascript" src="js/hypertree.js"></script>

        <div id="json1" style="display: none" >
            <jsp:useBean id="mybean" class="Clustering.Cluster_Clope" />
            <%=mybean.tututu()%></div>
     
    </head>

    <body id="te" onload="init();">
        <%@include file="header.xhtml" %>

       
        
        <div id="container1">

            <div id="left-container">
                <div class="text">
                    
                </div>
                <ul>  <a id="home" href="welcome.xhtml" class="theme button white">Home</a>
                </ul>
<!--                <div id="id-list"></div>-->

            </div>

            <div id="center-container">
                <div id="infovis"></div>    
            </div>

            <div id="left-container">
                
                <div id="inner-details"></div>

            </div>

            <div id="log"></div>
        </div>
        
        
        
        
        <div id="foot">    <%@include file="footer.xhtml" %></div>
    </body>
</html>
