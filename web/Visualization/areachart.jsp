
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>AreaChart</title>
        <link type="text/css" href="../css/base.css" rel="stylesheet" />
        <link type="text/css" href="../css/BarChart.css" rel="stylesheet" />
        <!--[if IE]><script language="javascript" type="text/javascript" src="../../Extras/excanvas.js"></script><![endif]-->
        <script language="javascript" type="text/javascript" src="../js/jit.js"></script>
        <script language="javascript" type="text/javascript" src="areachart.js"></script>
        <link href="/PSPABI/faces/javax.faces.resource/Other.css?ln=css" rel="stylesheet" type="text/css"/>
        <%@page import="JsonCreator.jsoncreator" %>
        <% jsoncreator queryDataProvider = new jsoncreator();
            char[] queryDataJson = queryDataProvider.get();%>
    </head>

    <body  onload="init('<%out.print(queryDataJson);%>');">
        <%@include file="../header.xhtml" %>
        
        <div class="row">
        <div class="loader">
                    <a4j:status>
                        <f:facet name="start">
                            <h:graphicImage value="/images/ai.gif" alt="ai" />
                        </f:facet>
                    </a4j:status>
                </div>
         <h:form  class="logout">
            <a id="home" href="../faces/welcome.xhtml" class="button home">Home</a>
            </h:form>
        </div>
        <div class="row">
             <div  class="left-container">
                <ul id="id-list"></ul>
            </div>
             <div class="center-container">
                <div id="infovis"></div>   
            </div>
        </div>
         <div  class="row footer">
            <%@include file="../footer.xhtml" %>
        </div>
    </body>
</html>
