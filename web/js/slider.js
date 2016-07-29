/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sagun
 */ 
$(function() {
    $(document).ready(function() {   
       $('#load').click(function(){                             
            product_id = document.getElementById("classkey").value;
            var data = getData3(product_id);
           // alert (data);                      
            data=JSON.parse(data);
            window.chart = new Highcharts.StockChart({
                chart : {
                    renderTo : 'container'
                },

                rangeSelector : {
                    selected : 1
                },

                title : {
                    text : 'Sales over the entire range'
                },
 series : [{
                name : product_id,
                data : data,
                type : 'area',
                threshold : null,
                tooltip : {
                    valueDecimals : 2
                },
                fillColor : {
                    linearGradient : {
                        x1: 0, 
                        y1: 0, 
                        x2: 0, 
                        y2: 1
                    },
                    stops : [[0, Highcharts.getOptions().colors[0]], [1, 'rgba(0,0,0,0)']]
                    }
                }]
            });
                        

        });
    });
});