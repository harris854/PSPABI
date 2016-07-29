
<%-- 
    Document   : data_monthwise
    Created on : Jun 28, 2012, 6:13:23 PM
    Author     : Sagun
--%>

<%
String name = request.getParameter("product_id");
String yr = request.getParameter("date");
%>

            <jsp:useBean id="beans" class="Visualization.month_wise_json" />
            <%= beans.retrieve(name,yr)%>

