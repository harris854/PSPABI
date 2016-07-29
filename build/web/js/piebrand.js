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
        var json = document.getElementById("json").innerHTML;
        json = JSON.parse(json);
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
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: 'Fast moving items'
            },
            tooltip: {
                formatter: function() {
                    return '<b>'+ this.point.name +'</b>: '+ Math.round(this.percentage*100.0)/100.0 +' %';
                }
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        color: '#000000',
                        connectorColor: '#000000',
                        formatter: function() {
                            return '<b>'+ this.point.name +'</b>: '+ Math.round(this.percentage*100.0)/100.0 +' %';
                        }
                    }
                }
            },
            series: json             
        });
    });
});