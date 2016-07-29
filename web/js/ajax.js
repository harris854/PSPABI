

/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sagun
 */

function ajax(){
    if(window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest();
    }
    else{
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xmlhttp;
}
function getData_month(product_id, date){//month wise
    var retString;
    xmlhttp = ajax();
    xmlhttp.onreadystatechange = function(){
        if(xmlhttp.readyState == 4 && xmlhttp.status==200){
            retString = xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET", "data_monthwise.jsp?product_id="+product_id+"&date="+date, false);
    xmlhttp.send();
    return retString;
}
function getData_day(product_id, date){//daywise
    var retString;
    xmlhttp = ajax();
 
    xmlhttp.onreadystatechange = function(){
        if(xmlhttp.readyState == 4 && xmlhttp.status==200){
            retString = xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET", "data_daywise.jsp?product_id="+product_id+"&date="+date, false);
    xmlhttp.send();
    return retString;
}
function getData3(product_id){
    var retString;
    xmlhttp = ajax();
 
    xmlhttp.onreadystatechange = function(){
        if(xmlhttp.readyState == 4 && xmlhttp.status==200){
            retString = xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET", "dataslide.jsp?product_id="+product_id, false);
    xmlhttp.send();
    return retString;
}

