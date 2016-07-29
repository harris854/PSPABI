
<%-- 
    
    Created on : Jun 28, 2012, 6:13:23 PM
    Author     : Sagun
--%>



<%
 String ck = request.getParameter("product_id");
%>

            <jsp:useBean id="beans" class="Visualization.dataslide" />
            <%= beans.retrieve(ck)%>
