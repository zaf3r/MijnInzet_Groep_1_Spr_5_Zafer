var username = {username : "theteacher"};
var url = "http://localhost:8080/api/findUser/"+username.username;
var week = weekpicker.getWeek();
var year = weekpicker.getYear();

$(function() {
    var weekpicker = $("#weekpicker1").weekpicker();

    console.log(weekpicker.getWeek());
    console.log(weekpicker.getYear());

    var inputField = weekpicker.find("input");
    inputField.datetimepicker().on("dp.change", function() {
        console.log(weekpicker.getWeek());
        console.log(weekpicker.getYear());
    })

});
$(function () {
    $.getJSON(url,username, function (data) {
        console.log(data);
        })
});