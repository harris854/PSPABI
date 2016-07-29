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
    //init data
   var  json1 =viz.replace(/`/g,'"');
  
 
  var jet=eval('('+json1+')');
    //init PieChart
    var pieChart = new $jit.PieChart({
      //id of the visualization container
      injectInto: 'infovis',
      //whether to add animations
      animate: true,
      //offsets
      offset:40,
      sliceOffset: 0,
      labelOffset: 30,
      //slice style
      type: useGradients? 'stacked:gradient' : 'stacked',
      //whether to show the labels for the slices
      showLabels:true,
      //resize labels according to
      //pie slices values set 7px as
      //min label size
      resizeLabels: 10,
      //if value given set value as height not angular for mono valued
      updateHeights:0,
      //label styling
      Label: {
        type: labelType, //Native or HTML
        size: 10,
        family: 'Arial',
        color: 'black'
      },
      //enable tips
                              Tips: {
            enable: true,
            onShow: function(tip, elem, node) {
                tip.innerHTML = "<b>" + node.name + "</b> <br><b>" + elem.name + "</b>: " + elem.value;
            }
        }
    });
    //load JSON data.
    pieChart.loadJSON(jet);
    //end
    var list = $jit.id('id-list'),button = $jit.id('update');
    //update json on click
//    $jit.util.addEvent(button, 'click', function() {
//      var util = $jit.util;
//      if(util.hasClass(button, 'gray')) return;
//      util.removeClass(button, 'white');
//      util.addClass(button, 'gray');
//      pieChart.updateJSON(jet);
//    });
    //dynamically add legend to list
    var legend = pieChart.getLegend(),
        listItems = [];
    for(var name in legend) {
      listItems.push('<div class=\'query-color\' style=\'background-color:'
          + legend[name] +'\'>&nbsp;</div>' + name);
    }
    list.innerHTML = '<li>' + listItems.join('</li><li>') + '</li>';
}
