var labelType, useGradients, nativeTextSupport, animate;

(function() {
    var ua = navigator.userAgent,
    iStuff = ua.match(/iPhone/i) || ua.match(/iPad/i),
    typeOfCanvas = typeof HTMLCanvasElement,
    nativeCanvasSupport = (typeOfCanvas == 'object' || typeOfCanvas == 'function'),
    textSupport = nativeCanvasSupport 
    && (typeof document.createElement('canvas').getContext('2d').fillText == 'function');
    //I'm setting this based on the fact that ExCanvas provides text support for IE
    //and that as of today iPhone/iPad current text support is lame
    labelType = (!nativeCanvasSupport || (textSupport && !iStuff))? 'Native' : 'HTML';
    nativeTextSupport = labelType == 'Native';
    useGradients = nativeCanvasSupport;
    animate = !(iStuff || !nativeCanvasSupport);
})();

var Log = {
    elem: false,
    write: function(text){
        if (!this.elem) 
            this.elem = document.getElementById('log');
        this.elem.innerHTML = text;
        this.elem.style.left = (500 - this.elem.offsetWidth / 2) + 'px';
    }
};


function init(viz){
    
    var  json1 =viz.replace(/`/g,'"');
  
 
    var jet=eval('('+json1+')');
    //end
    var infovis = document.getElementById('infovis');
    //init AreaChart
    var areaChart = new $jit.AreaChart({
        //id of the visualization container
        injectInto: 'infovis',
        //add animations
        animate: true,
        //separation offsets
        Margin: {
            top: 5,
            left: 5,
            right: 5,
            bottom: 5
        },
        labelOffset: 15,
        //whether to display sums
        showAggregates: true,
        //whether to display labels at all
        showLabels: true,
        //could also be 'stacked'
        type: useGradients? 'stacked:gradient' : 'stacked',
        //label styling
        Label: {
            type: labelType, //can be 'Native' or 'HTML'
            size: 15,
            family: 'Arial',
            color: 'black'
        },
        //enable tips
        Tips: {
            enable: true,
            onShow: function(tip, elem, node) {
                tip.innerHTML = "<b>" + node.name + "</b> <br><b>" + elem.name + "</b>: " + elem.value;
            }
                                    
        },
        //add left and right click handlers
        filterOnClick: true,
        restoreOnRightClick:true
    });
    //load JSON data.
    areaChart.loadJSON(jet);
    //end
    var list = $jit.id('id-list'), button = $jit.id('update'),restoreButton = $jit.id('restore');
    //update json on click
//    $jit.util.addEvent(button, 'click', function() {
//        var util = $jit.util;
//        if(util.hasClass(button, 'gray')) return;
//        util.removeClass(button, 'white');
//        util.addClass(button, 'gray');
//        areaChart.updateJSON(jet);
//    });
    //restore graph on click
//    $jit.util.addEvent(restoreButton, 'click', function() {
//        areaChart.restore();
//    });
    //dynamically add legend to list
    var legend = areaChart.getLegend(),
    listItems = [];
    for(var name in legend) {
        listItems.push('<div class=\'query-color\' style=\'background-color:'
            + legend[name] +'\'>&nbsp;</div>' + name);
    }
    list.innerHTML = '<li>' + listItems.join('</li><li>') + '</li>';
}
