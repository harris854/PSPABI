

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Final Year Project</title>
    </head>
    <body bgcolor="Green">

        <center>
            <h1>Bhatbhateni Analysis</h1>
            <br/>

            <%--redirect to login if session not set else welcome page--%>
            <jsp:forward page="./faces/welcome.xhtml" > 
                <jsp:param name="forwardedFrom" value="this.jsp" />
            </jsp:forward>
        </center>


    </body>
</html>
