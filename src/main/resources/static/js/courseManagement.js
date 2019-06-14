

///Get drop-down list object

oListbox = document.getElementById("selectAge");
var ListUtil = new Object();
var selectbtn = document.getElementById("selectbtn");
function getSelAge (){
    //Access option
    alert(oListbox.options[1].firstChild.nodeValue); //Displayed content
    alert(oListbox.options[1].getAttribute("value"));//Corresponding value
    alert("Get its position in the collection== " + oListbox.options[2].index); //Get its position in the collection
    alert("Get the number of elements in the collection== " + oListbox.options.length); //Get the number of elements in the collection

}


/*******************************************************************/
//Input parameter, drop-down box object
ListUtil.getSelectIndexes = function (oListbox){

    var arrIndexes =  new Array();
    for(var i=0 ; i<oListbox.options.length;i++){
        //If the item is selected, add the corresponding index to the array.
        if(oListbox.options[i].selected){
            arrIndexes.push(i);
        }
    }
    return  arrIndexes; //Returns the selected option index
}


/*******Mobile option***************************************************************************************/


    //Get drop down box

var move1Listbox = document.getElementById("move1"); //left drop down box
var move2Listbox = document.getElementById("move2"); //right drop down box

//Move one or more selected options
ListUtil.move = function(oListboxFrom ,oListboxTo){
    //var idx1 = oListboxFrom.selectedIndex;
    var arrIndexes = ListUtil.getSelectIndexes(oListboxFrom);
    var oOption ;

    if(arrIndexes.length == 0 ){
        alert("Please select at least one option!");
        return ;
    }else{
        for(var i=oListboxFrom.options.length-1;i>=0;i--){
            oOption = oListboxFrom.options[i];
            if(oOption.selected && oOption != null ){
                oListboxTo.appendChild(oOption);
            }
        }
    }
}


//single select to right
function rightSingle(){
    ListUtil.move(move1Listbox,move2Listbox);
};

//single select to left
function leftSingle(){
    ListUtil.move(move2Listbox,move1Listbox);
}

/****************************************/

function confirmPlease() {
    var r = confirm('Weet u zeker dat u het vak wilt verwijderen?')
    if (r==true){
        return true;
    }
    else {
        //do not execute submit
        window.location.href="http://localhost:8080/manager/vak";
        return false;
    }
}