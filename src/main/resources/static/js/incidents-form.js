var week;
var year;
var requestUrl;
var postUrl;
var jsonPost = new Object();
var weekpicker;

var mondayMo = document.querySelector('input[name="mondayMo"]');
var mondayAf = document.querySelector('input[name="mondayAf"]');
var mondayEv = document.querySelector('input[name="mondayEv"]');
var tuesdayMo = document.querySelector('input[name="tuesdayMo"]');
var tuesdayAf = document.querySelector('input[name="tuesdayAf"]');
var tuesdayEv = document.querySelector('input[name="tuesdayEv"]');
var wednesdayMo = document.querySelector('input[name="wednesdayMo"]');
var wednesdayAf = document.querySelector('input[name="wednesdayAf"]');
var wednesdayEv = document.querySelector('input[name="wednesdayEv"]');
var thursdayMo = document.querySelector('input[name="thursdayMo"]');
var thursdayAf = document.querySelector('input[name="thursdayAf"]');
var thursdayEv = document.querySelector('input[name="thursdayEv"]');
var fridayMo = document.querySelector('input[name="fridayMo"]');
var fridayAf = document.querySelector('input[name="fridayAf"]');
var fridayEv = document.querySelector('input[name="fridayEv"]');

$(function() {
    weekpicker = $("#weekpicker1").weekpicker();

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
    });

    var btn = $("#button").on("click", function () {
        postCall();
    });

});


function postCall() {
        jsonPost.week = weekpicker.getWeek();
        jsonPost.year = weekpicker.getYear();

        jsonPost.mondayMo = mondayMo.value;
        jsonPost.mondayAf = mondayAf.value;
        jsonPost.mondayEv = mondayEv.value;

        jsonPost.tuesdayMo = tuesdayMo.value;
        jsonPost.tuesdayAf = tuesdayAf.value;
        jsonPost.tuesdayEv = tuesdayEv.value;

        jsonPost.wednesdayMo = wednesdayMo.value;
        jsonPost.wednesdayAf = wednesdayAf.value;
        jsonPost.wednesdayEv = wednesdayEv.value;

        jsonPost.thursdayMo = thursdayMo.value;
        jsonPost.thursdayAf = thursdayAf.value;
        jsonPost.thursdayEv = thursdayEv.value;

        jsonPost.fridayMo = fridayMo.value;
        jsonPost.fridayAf = fridayAf.value;
        jsonPost.fridayEv = fridayEv.value;

        var jsonObject = JSON.stringify(jsonPost);

        postUrl = "http://localhost:8080/api/saveIncidents/";
        console.log(jsonPost);

    $.ajax({
        type: "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        url: postUrl,
        data:jsonObject,
        }).done(alert("Uw incidenten voor het jaar " + jsonPost.year + ", week " + jsonPost.week + " zijn opgeslagen"));
}
 function requestCall() {

     $(function () {
         $.getJSON(requestUrl, function (data) {
             console.log(data);

             loadMonday(data);
             loadTuesday(data);
             loadWednesday(data);
             loadThursday(data);
             loadFriday(data);

             if(data.length === 0){
                 console.log("I'm empty, but you should still update");
             }
         }).fail(function () {
             console.log("Failed to make a request")
         })
     });
 }

 function loadMonday(incidentArray) {
     incidentArray.forEach(function(day) {
         if(day.weekday === "MONDAY") {
             mondayMo.value = day.morning;
             mondayMo.parentNode.classList = day.morning;
             mondayAf.value = day.afternoon;
             mondayAf.parentNode.classList = day.evening;
             mondayEv.value = day.afternoon;
             mondayEv.parentNode.classList = day.evening;
            }
        }
     )
 }

function loadTuesday(incidentArray) {
    incidentArray.forEach(function(day) {
            if(day.weekday === "TUESDAY") {
                tuesdayMo.value = day.morning;
                tuesdayMo.parentNode.classList = day.morning;
                tuesdayAf.value = day.afternoon;
                tuesdayAf.parentNode.classList = day.afternoon;
                tuesdayEv.value = day.evening;
                tuesdayEv.parentNode.classList = day.evening;
            }
        }
    )
}

function loadWednesday(incidentArray) {
    incidentArray.forEach(function(day) {
            if(day.weekday === "WEDNESDAY") {
                wednesdayMo.value = day.morning;
                wednesdayMo.parentNode.classList = day.morning;
                wednesdayAf.value = day.afternoon;
                wednesdayAf.parentNode.classList = day.afternoon;
                wednesdayEv.value = day.evening;
                wednesdayEv.parentNode.classList = day.evening;
            }
        }
    )
}

function loadThursday(incidentArray) {
    incidentArray.forEach(function(day) {
            if(day.weekday === "THURSDAY") {
                thursdayMo.value = day.morning;
                thursdayMo.parentNode.classList = day.morning;
                thursdayAf.value = day.afternoon;
                thursdayAf.parentNode.classList = day.afternoon;
                thursdayEv.value = day.evening;
                thursdayEv.parentNode.classList = day.evening;
            }
        }
    )
}

function loadFriday(incidentArray) {
    incidentArray.forEach(function(day) {
            if(day.weekday === "FRIDAY") {
                fridayMo.value = day.morning;
                fridayMo.parentNode.classList = day.morning;
                fridayAf.value = day.afternoon;
                fridayAf.parentNode.classList = day.afternoon;
                fridayEv.value = day.evening;
                fridayEv.parentNode.classList = day.evening;
            }
        }
    )
}