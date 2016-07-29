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
          
    $(document).ready(function() {
        var jsondata = document.getElementById("json_data").innerHTML;
           
        jsondata = JSON.parse(jsondata);
                             
        var jsoncat = document.getElementById("json_cat").innerHTML;
        jsoncat = JSON.parse(jsoncat);
                    
        var colors = Highcharts.getOptions().colors,
        categories = jsoncat,
        name = 'brands',
        data = jsondata ;
        Highcharts.setOptions({
            global: {
                VMLRadialGradientURL: 'http://code.highcharts.com/gfx/radial-gradient.png'
            }
        });
        
        // Radialize the colors
        Highcharts.getOptions().colors = $.map(Highcharts.getOptions().colors, function(color) {
            return {
                radialGradient: {
                    cx: 0.5, 
                    cy: 0.3, 
                    r: 0.7
                },
                stops: [
                [0, color],
                [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
                ]
            };
        });
    
        // Build the data arrays
        var catData = [];
        var proData = [];
        for (var i = 0; i < data.length; i++) {
    
            // add browser data
            catData.push({
                name: categories[i],
                y: data[i].y,
                color: data[i].color
            });
    
            // add version data
            for (var j = 0; j < data[i].drilldown.data.length; j++) {
                var brightness = 0.2 - (j / data[i].drilldown.data.length) / 5 ;
                proData.push({
                    name: data[i].drilldown.categories[j],
                    y: data[i].drilldown.data[j],
                    color: Highcharts.Color(data[i].color).brighten(brightness).get()
                });
            }
        }
        var currentTime = new Date()
        var month = currentTime.getMonth() + 1
        var day = currentTime.getDate()
        var year = currentTime.getFullYear()
      
      
     
    

        // Create the chart

        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                type: 'pie'
            },
            title: {
                text: 'Catagory wise and brand popularity today Date: '+ month + "/" + day + "/" + year
            },
            yAxis: {
                title: {
                    text: 'Total percent market share'
                }
            },
            plotOptions: {
                pie: {
                    shadow: false
                }
            },
            tooltip: {
                formatter: function() {
                    return '<b>'+ this.point.name +'</b>: '+ this.y +' units';
                }
            },
            series: [{
                name: 'Browsers',
                data: catData,
                size: '60%',
                dataLabels: {
                    formatter: function() {
                        return this.y > 5 ? this.point.name : null;
                    },
                    color: 'white',
                    distance: -30
                }
            }, {
                name: 'Versions',
                data: proData,
                innerSize: '80%',
                dataLabels: {
                    formatter: function() {
                        // display only if larger than 1
                        return this.y > 1 ? '<b>'+ this.point.name +':</b> '+ this.y +'units'  : null;
                    }
                }
            }]
        });
        function setChart(name, categories, data, color) {
            chart1.xAxis[0].setCategories(categories);
            chart1.series[0].remove();
            chart1.addSeries({
                name: name,
                data: data,
                color: color || 'white'
            });
        }
        chart1 = new Highcharts.Chart({
            chart: {
                renderTo: 'container1',
                type: 'column'
            },
            title: {
                text: 'Catagory wise and brand popularity today Date: '+ month + "/" + day + "/" + year
            },
            subtitle: {
                text: 'Click the columns to view versions. Click again to view brands.'
            },
            xAxis: {
                categories: categories
            },
            yAxis: {
                title: {
                    text: 'Total percent market share'
                }
            },
            plotOptions: {
                column: {
                    cursor: 'pointer',
                    point: {
                        events: {
                            click: function() {
                                var drilldown = this.drilldown;
                                if (drilldown) { // drill down
                                    setChart(drilldown.name, drilldown.categories, drilldown.data, drilldown.color);
                                } else { // restore
                                    setChart(name, categories, data);
                                }
                            }
                        }
                    },
                    dataLabels: {
                        enabled: true,
                        color: colors[0],
                        style: {
                            fontWeight: 'bold'
                        },
                        formatter: function() {
                            return this.y +'units';
                        }
                    }
                }
            },
            tooltip: {
                formatter: function() {
                    var point = this.point,
                    s = this.x +':<b>'+ this.y +' units market share</b><br/>';
                    if (point.drilldown) {
                        s += 'Click to view '+ point.category +' versions';
                    } else {
                        s += 'Click to return to browser brands';
                    }
                    return s;
                }
            },
            series: [{
                name: name,
                data: data,
                color: 'white'
            }],
            exporting: {
                enabled: false
            }
        });
    });
    
});
