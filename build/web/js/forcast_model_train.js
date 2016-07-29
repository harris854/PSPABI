/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(function () {
    var chart;
    $(document).ready(function() {
        var json = document.getElementById("json").innerHTML;
        json = JSON.parse(json);
       
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                type: 'spline'
            },
            title: {
                text: 'Forcasting'
            },
            subtitle: {
                text: 'Source: Database'
            },
            xAxis: {
                categories: []
            },
            yAxis: {
                title: {
                    text: 'Sales Unit'
                },
                labels: {
                    formatter: function() {
                        return this.value +'units'
                    }
                }
            },
            tooltip: {
                crosshairs: true,
                shared: true
            },
            plotOptions: {
                spline: {
                    marker: {
                        radius: 4,
                        lineColor: '#666666',
                        lineWidth: 1
                    }
                }
            },
            series: json
        });
    });
    $('#load').click(function(){
        var json1 = 
        json1 = JSON.parse(json1);

        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                type: 'spline'
            },
            title: {
                text: 'Forcasting'
            },
            subtitle: {
                text: 'Source: Database'
            },
            xAxis: {
                categories: []
            },
            yAxis: {
                title: {
                    text: 'Sales Unit'
                },
                labels: {
                    formatter: function() {
                        return this.value +'units'
                    }
                }
            },
            tooltip: {
                crosshairs: true,
                shared: true
            },
            plotOptions: {
                spline: {
                    marker: {
                        radius: 4,
                        lineColor: '#666666',
                        lineWidth: 1
                    }
                }
            },
            series: json1
        });
      
                  
    });
    
});