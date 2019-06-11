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
