var week;
var year;
var requestUrl;

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