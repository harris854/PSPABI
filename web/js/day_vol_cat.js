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
                $(document).ready(function() {
                    var json = document.getElementById("j1").innerHTML;
                    json = JSON.parse(json);
                    var json1= document.getElementById("j2").innerHTML;
                    json1= JSON.parse(json1);
                    console.log(json);
                    console.log(json1);
                    chart = new Highcharts.Chart({
                        chart: {
                            renderTo: 'container',
                            type: 'spline',
                            marginRight: 130,
                            marginBottom: 25
                        },
                        title: {
                            text: 'Sales Volume QTY by Date Range',
                            x: -20 //center
                        },
                        subtitle: {
                            text: 'Source: Database',
                            x: -20
                        },
                        xAxis: {
                            categories: json1
                        },
                        yAxis: {
                            title: {
                                text: 'Sales volume by day'
                            },
                            plotLines: [{
                                    value: 0,
                                    width: 1,
                                    color: '#808080'
                                }]
                        },
                        tooltip: {
                            formatter: function() {
                                return '<b>'+ this.series.desc +'</b><br/>'+
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
                        series: json
                    });
  
                });

            });

