/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sagun
 */

 $(function () {
                var chart;
                var chart1;
                var json = "";
                $(document).ready(function() {
                          $('#button').click(function() {
                        var series = chart.series[0],
                        newType = series.type == 'line' ? 'column' : 'line';
                        changeType(series, newType);
                    })
                      $('#button1').click(function() {
                        var series = chart1.series[0],
                        newType1 = series.type == 'line' ? 'column' : 'line';
                        changeType1(series, newType1);
                    })

                    function changeType(series, newType) {
                        var dataArray = [],
                        points = series.data;
                        series.chart.addSeries({
                            type: newType,
                            name: series.name,
                            color: series.color,
                            data: series.options.data
                        }, false);
    
                        series.remove();
                    }
                 function changeType1(series, newType1) {
                        var dataArray = [],
                        points = series.data;
                      
                        series.chart1.addSeries({
                            type: newType1,
                            name: series.name,
                            color: series.color,
                            data: series.options.data
                        }, false);
                    
                        series.remove();
                    }
//                         
                    chart = new Highcharts.Chart({
                        chart: {
                            renderTo: 'container1',
                            type: 'spline',
                            marginRight: 130,
                            marginBottom: 25
                        },
                        title: {
                            text: 'Daily Average Selling over a year',
                            x: -20 //center
                        },
                        subtitle: {
                            text: 'Source: Bhatbhatani',
                            x: -20
                        },
                        xAxis: {
                            categories: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri',
                                'Sat']
                        },
                        yAxis: {
                            title: {
                                text: 'Sales Volume by units by day of week'
                            },
                            plotLines: [{
                                    value: 0,
                                    width: 1,
                                    color: '#808080'
                                }]
                        },
                        tooltip: {
                            formatter: function() {
                                return '<b>'+ this.series.name +'</b><br/>'+
                                    this.x +': '+ this.y +'units';
                            }
                        },
                        legend: {
                            layout: 'vertical',
                            align: 'right',
                            verticalAlign: 'top',
                            x: -10,
                            y: 100,
                            borderWidth: 0
                        },
                        series: []
                    });

            
                chart1 = new Highcharts.Chart({
                        chart: {
                            renderTo: 'container',
                            type: 'spline',
                            marginRight: 130,
                            marginBottom: 25
                        },
                        title: {
                            text: 'Monthly Average Selling',
                            x: -20 //center
                        },
                        subtitle: {
                            text: 'Source: Bhatbhatani',
                            x: -20
                        },
                        xAxis: {
                            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                                'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
                        },
                        yAxis: {
                            title: {
                                text: 'Sales Volume by units'
                            },
                            plotLines: [{
                                    value: 0,
                                    width: 1,
                                    color: '#808080'
                                }]
                        },
                        tooltip: {
                            formatter: function() {
                                return '<b>'+ this.series.name +'</b><br/>'+
                                    this.x +': '+ this.y +'units';
                            }
                        },
                        legend: {
                            layout: 'vertical',
                            align: 'right',
                            verticalAlign: 'top',
                            x: -10,
                            y: 100,
                            borderWidth: 0
                        },
                        series: []
                    });
                })
             

                $('#load').click(function(){
                    product_id = document.getElementById("product").value;
                    date = document.getElementById("date").value;
                    string = getData_day(product_id, date);
                    string2=  getData_month(product_id,date);
                    json1 = JSON.parse(string);
                    json2 = JSON.parse(string2);
                  //  console.log(json[0]);
                    if(json1 instanceof Array){
                        for (i=0; i < json1.length; i++){
                            chart.addSeries(json1[i]);
                            chart1.addSeries(json2[i])
                        }
                    }
                    else{
                        chart.addSeries(json1);
                        chart1.addSeries(json2);
                    }
                });
            });