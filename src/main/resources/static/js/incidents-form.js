var week;
var year;
var requestUrl;

var mondayMo = document.querySelector("#mondayMo");
var mondayAf = document.querySelector("#mondayAf");
var mondayEv = document.querySelector("#mondayEv");
var tuesdayMo = document.querySelector("#tuesdayMo");
var tuesdayAf = document.querySelector("#tuesdayAf");
var tuesdayEv = document.querySelector("#tuesdayEv");
var wednesdayMo = document.querySelector("#wednesdayMo");
var wednesdayAf = document.querySelector("#wednesdayAf");
var wednesdayEv = document.querySelector("#wednesdayEv");
var thursdayMo = document.querySelector("#thursdayMo");
var thursdayAf = document.querySelector("#thursdayAf");
var thursdayEv = document.querySelector("#thursdEv");
var fridayMo = document.querySelector("#fridayMo");
var fridayAf = document.querySelector("#fridayAf");
var fridayEv = document.querySelector("#fridayEv");


$(function() {
    var weekpicker = $("#weekpicker1").weekpicker();

    week = weekpicker.getWeek();
    year = weekpicker.getYear();

    console.log(weekpicker.getWeek());
    console.log(weekpicker.getYear());

    var inputField = weekpicker.find("input");
    inputField.datetimepicker().on("dp.change", function() {
        week = weekpicker.getWeek();
        year = weekpicker.getYear();
        requestUrl = "http://localhost:8080/api/findincident/"+year+"/"+week;

        requestCall();
    })

});
 function requestCall() {

     $(function () {
         $.getJSON(requestUrl, function (data) {
             console.log(data);
             if(data.length === 0){
                 console.log("I'm empty, but you should still update");
             }
         }).fail(function () {
             console.log("Failed to make a request")
         })
     });
 }