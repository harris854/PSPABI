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
                var json = "";
                $(document).ready(function() {
                    $('#button').click(function() {
                        var series = chart.series[0],
                        newType = series.type == 'line' ? 'column' : 'line';
                        changeType(series, newType);
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
                    chart = new Highcharts.Chart({
                        chart: {
                            renderTo: 'container',
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

                });


                $('#load').click(function(){
                    product_id = document.getElementById("product").value;
                    date = document.getElementById("date").value;
                    string = getData_day(product_id, date);
                    json = JSON.parse(string);
                  //  console.log(json[0]);
                    if(json instanceof Array){
                        for (i=0; i < json.length; i++){
                            chart.addSeries(json[i]);
                        }
                    }
                    else{
                        chart.addSeries(json);
                    }
                });

            });


