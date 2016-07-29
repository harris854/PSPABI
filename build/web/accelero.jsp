
<!DOCTYPE HTML>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Accelero</title>
        <link type="text/css" href="/resources/css/Other.css" rel="stylesheet" />
        <%@ page import="java.sql.*" %> 
        <%@ page import="java.io.*" %> 
        <%@ page import="java.lang.*"%>
        <%@ page import="java.text.DateFormatSymbols"%>
        <%@ include file="navigation.html" %> 

        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript">
            $(function () {
                $(document).ready(function() {
                    var json = document.getElementById("json_plotbands").innerHTML;
                    var json_data = document.getElementById("json_data").innerHTML;
                    // alert(json_max);
                    json_data =JSON.parse(json_data);
                    json=JSON.parse(json);
                    var chart = new Highcharts.Chart({
 
                        chart: {
                            renderTo: 'container',
                            type: 'gauge',
                            plotBackgroundColor: null,
                            plotBackgroundImage: null,
                            plotBorderWidth: 0,
                            plotShadow: false
                        },
        
                        title: {
                            text: 'Avg sales'
                        },
        
                        pane: {
                            startAngle: -150,
                            endAngle: 150,
                            background: [{
                                    backgroundColor: {
                                        linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
                                        stops: [
                                            [0, '#FFF'],
                                            [1, '#333']
                                        ]
                                    },
                                    borderWidth: 0,
                                    outerRadius: '109%'
                                }, {
                                    backgroundColor: {
                                        linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
                                        stops: [
                                            [0, '#333'],
                                            [1, '#FFF']
                                        ]
                                    },
                                    borderWidth: 1,
                                    outerRadius: '107%'
                                }, {
                                    // default background
                                }, {
                                    backgroundColor: '#DDD',
                                    borderWidth: 0,
                                    outerRadius: '105%',
                                    innerRadius: '103%'
                                }]
                        },
           
                        // the value axis
                        yAxis: {
                            min: 0,
                            max: 344058,
            
                            minorTickInterval: 'auto',
                            minorTickWidth: 1,
                            minorTickLength: 10,
                            minorTickPosition: 'inside',
                            minorTickColor: '#666',
    
                            tickPixelInterval: 30,
                            tickWidth: 2,
                            tickPosition: 'inside',
                            tickLength: 10,
                            tickColor: '#666',
                            labels: {
                                step: 2,
                                rotation: 'auto'
                            },
                            title: {
                                text: 'Sales'
                            },
                            plotBands: json    
                        },
    
                        series: [{
                                name: 'Sales',
                                data: json_data,
                                tooltip: {
                                    valueSuffix: 'units'
                                }
                            }]
    
                    }, 
                    // Add some life
                    function (chart) {
                        setInterval(function () {
                            var point = chart.series[0].points[0],
                            newVal,
                            inc = Math.round((Math.random() - 0.5) * 2);
            
                            newVal = point.y + inc;
                            if (newVal < 0 || newVal > 200) {
                                newVal = point.y - inc;
                            }
            
                            point.update(newVal);
            
                        }, 3000);
                    });
                })
            });
        </script>
    </head>
    <body id="te">

        <script src="js/highcharts.js"></script>
        <script src="js/modules/exporting.js"></script>
        <script src="js/highcharts-more.js"></script>

        <div id="container" style="width: 1200px;"></div>
        <%

            String year = request.getParameter("year");
            if (year == null) {
                year = "2011";
            }

            String month = request.getParameter("month");
            if (month == null) {
                month = "9";
            }

            String json_plot = "";
            String json_data = "";
            int sum = 0;
            int noi = 1;
            int max = 0;
            int min = 0;

            try {
                String connectionURL = "jdbc:mysql://localhost:3306/bhatbhateni";
                Connection connection = null;
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                connection = DriverManager.getConnection(connectionURL, "root", "");

                Statement s = (Statement) connection.createStatement();

                String query2 = "select sum(qty) from vizdata where year(date) = " + year + " group by month(date)";
                ResultSet rs = s.executeQuery(query2);
                int init = 0;
                while (rs.next()) {
                    noi++;
                    int a = rs.getInt(1);
                    if (init == 0) {
                        max = a;
                        min = a;
                        init++;
                    }


                    if (a > max) {
                        max = a;
                    }
                    if (a < min) {
                        min = a;
                    }
                    sum += a;
                }
                int avg = sum / noi;
                json_plot = "[{\"from\": 0,\"to\": " + min + ",\"color\": \"#55BF3B\"}, { \"from\":" + min + ",\"to\": " + avg + ",\"color\": \"#DDDF0D\"}, {\"from\": " + avg + ",\"to\": " + max + ", \"color\": \"#DF5353\"  }]";

                Statement s1 = (Statement) connection.createStatement();

                String query1 = "select sum(qty) from vizdata where year(date) = " + year + " and  month(date)= " + month;
                ResultSet rs1 = s.executeQuery(query1);

                rs1.next();
                int thismonth = rs1.getInt(1);
                json_data = "[" + thismonth + "]";




            } catch (Exception e) {
                out.println(e.getMessage());
            }


        %>
        <div id="json_plotbands" style="display:none">
            <%= json_plot%>
        </div>
        <div id="json_data" style="display:none">
            <%= json_data%>
        </div>

        <FORM NAME="form" ACTION="#" METHOD="POST">
            <table bgcolor="#D8D8D8">
                <tr>
                <select name='month' onchange="showState(this.value)">  
                    <option value="7">Select ID</option>  
                    <%
                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhatbhateni", "root", "");
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("Select distinct (Month) from Period order by Month asc");


                        while (rs.next()) {
                    %>
                    <option value="<%=rs.getInt(1)%>"><%=(new DateFormatSymbols().getMonths()[rs.getInt(1) - 1])%></option>  
                    <%
                        }
                    %>

                </select>
              </tr>
                    <tr>
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

                    </select>  </tr>
                        </table>
                        <td><INPUT TYPE="submit" VALUE="show"></td>
                        </FORM>
                        <div id="foot">    <%@include file="footer.xhtml" %></div>
                        </body>


                        </html>
