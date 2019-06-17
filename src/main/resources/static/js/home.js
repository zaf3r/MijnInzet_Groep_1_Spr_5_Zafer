var url = 'https://ron-swanson-quotes.herokuapp.com/v2/quotes'
var xhrbtn = document.querySelector("#xhr");
var display = document.querySelector("#quote");

xhrbtn.addEventListener("click", function(){
    var XHR = new XMLHttpRequest();
    XHR.onreadystatechange = function(){
        if(XHR.readyState == 4 && XHR.status == 200){
            var quote = JSON.parse(XHR.responseText)[0];
            display.innerText = quote;
        }
    }
    XHR.open("GET", url);
    XHR.send();
});