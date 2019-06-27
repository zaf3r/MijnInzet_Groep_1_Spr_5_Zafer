//diverse variables
var getrequestWeeksUrl;
var getRequestDaysUrl;
var postRequestUrl;

//diverse variables
var noTeacher = "geen docent";
var noSubject = "vacant";

//Teacher Variables
var mondayMoTeach = document.querySelector('option[name="maandagOchtendDocent"]');
var mondayAfTeach = document.querySelector('option[name="maandagMiddagDocent"]');
var mondayEvTeach = document.querySelector('option[name="maandagAvondDocent"]');
var tuesdayMoTeach = document.querySelector('option[name="dinsdagOchtendDocent"]');
var tuesdayAfTeach = document.querySelector('option[name="dinsdagMiddagDocent"]');
var tuesdayEvTeach = document.querySelector('option[name="dinsdagAvondDocent"]');
var wednesdayMoTeach = document.querySelector('option[name="woensdagOchtendDocent"]');
var wednesdayAfTeach = document.querySelector('option[name="woensdagMiddagDocent"]');
var wednesdayEvTeach = document.querySelector('option[name="woensdagAvondDocent"]');
var thursdayMoTeach = document.querySelector('option[name="donderdagOchtendDocent"]');
var thursdayAfTeach = document.querySelector('option[name="donderdagMiddagDocent"]');
var thursdayEvTeach = document.querySelector('option[name="donderdagAvondDocent"]');
var fridayMoTeach = document.querySelector('option[name="vrijdagOchtendDocent"]');
var fridayAfTeach = document.querySelector('option[name="vrijdagMiddagDocent"]');
var fridayEvTeach = document.querySelector('option[name="vrijdagAvondDocent"]');

//Subject variables
var mondayMoSub = document.querySelector('div[name="maandagOchtendVak"]');
var mondayAfSub  = document.querySelector('div[name="maandagMiddagVak"]');
var mondayEvSub  = document.querySelector('div[name="maandagAvondVak"]');
var tuesdayMoSub  = document.querySelector('div[name="dinsdagOchtendVak"]');
var tuesdayAfSub  = document.querySelector('div[name="dinsdagMiddagVak"]');
var tuesdayEvSub  = document.querySelector('div[name="dinsdagAvondVak"]');
var wednesdayMoSub  = document.querySelector('div[name="woensdagOchtendVak"]');
var wednesdayAfSub  = document.querySelector('div[name="woensdagMiddagVak"]');
var wednesdayEvSub  = document.querySelector('div[name="woensdagAvondVak"]');
var thursdayMoSub  = document.querySelector('div[name="donderdagOchtendVak"]');
var thursdayAfSub  = document.querySelector('div[name="donderdagMiddagVak"]');
var thursdayEvSub  = document.querySelector('div[name="donderdagAvondVak"]');
var fridayMoSub  = document.querySelector('div[name="vrijdagOchtendVak"]');
var fridayAfSub  = document.querySelector('div[name="vrijdagMiddagVak"]');
var fridayEvSub  = document.querySelector('div[name="vrijdagAvondVak"]');

//Other variables
var cohortNaam = document.querySelector('select[name="cohortName"]');
var cohortWeek = document.querySelector('select[name="weekNumber"]');


$(function() {
    $(cohortNaam).on("change", cohortNaam, function () {
        getrequestWeeksUrl = "http://localhost:8080/api/cohort/weeks/"+cohortNaam.value;
        requestCallWeeks();
    });
});

$(function() {
    $(cohortNaam).on("change", cohortWeek, function () {
        getrequestWeeksUrl = "http://localhost:8080/api/cohort/days/"+cohortNaam.value+"/"+cohortWeek.value;
        requestCallDays();
    });

});

function requestCallWeeks() {
    $(function () {
        $.getJSON(getrequestWeeksUrl, function (data) {
            console.log(data);
            loadDropDownWeeks(data)
        }).fail(function () {
            console.log("Failed to make a request")
        })
    });
}

function requestCallDays() {
    $(function () {
        $.getJSON(getRequestDaysUrl, function (data) {
            console.log(data);
        }).fail(function () {
            console.log("Failed to make a request")
        })
    });
}


//Load dropdown menu of weeks
function loadDropDownWeeks(dropdownItems) {
    dropdownItems.forEach(function (item) {
        var option = document.createElement("option");
        option.text(item);
        console.log(item);
        cohortWeek.push(option);
    })

}

//subject functions
function loadMondaySubs(courseScheduleSubArray) {
    courseScheduleSubArray.forEach(function(sub) {
            if(sub.weekday === "MONDAY") {
                mondayMoSub.value = sub.morning;
                mondayMoSub.parentNode.classList = sub.morning;
                mondayAfSub.value = sub.afternoon;
                mondayAfSub.parentNode.classList = sub.afternoon;
                mondayEvSub.value = sub.evening;
                mondayEvSub.parentNode.classList = sub.evening;
            }
        }
    )
}

function loadTuesdaySubs(courseScheduleSubArray) {
    courseScheduleSubArray.forEach(function(sub) {
            if(sub.weekday === "TUESDAY") {
                tuesdayMoSub.value = sub.morning;
                tuesdayMoSub.parentNode.classList = sub.morning;
                tuesdayAfSub.value = sub.afternoon;
                tuesdayAfSub.parentNode.classList = sub.afternoon;
                tuesdayEvSub.value = sub.evening;
                tuesdayEvSub.parentNode.classList = sub.evening;
            }
        }
    )
}

function loadWednesdaySubs(courseScheduleSubArray) {
    courseScheduleSubArray.forEach(function(sub) {
            if(sub.weekday === "WEDNESDAY") {
                wednesdayMoSub.value = sub.morning;
                wednesdayMoSub.parentNode.classList = sub.morning;
                wednesdayAfSub.value = sub.afternoon;
                wednesdayAfSub.parentNode.classList = sub.afternoon;
                wednesdayEvSub.value = sub.evening;
                wednesdayEvSub.parentNode.classList = sub.evening;
            }
        }
    )
}


function loadThursdaySubs(courseScheduleSubArray) {
    courseScheduleSubArray.forEach(function(sub) {
            if(sub.weekday === "THURSDAY") {
                thursdayMoSub.value = sub.morning;
                thursdayMoSub.parentNode.classList = sub.morning;
                thursdayAfSub.value = sub.afternoon;
                thursdayAfSub.parentNode.classList = sub.afternoon;
                thursdayEvSub.value = sub.evening;
                thursdayEvSub.parentNode.classList = sub.evening;
            }
        }
    )
}


function loadFridaySubs(courseScheduleSubArray) {
    courseScheduleSubArray.forEach(function(sub) {
            if(sub.weekday === "FRIDAY") {
                if (fridayMoSub.value === null) {
                    sub.morning = noSubject;
                }
                else
                    fridayMoSub.value = sub.morning;
                fridayMoSub.parentNode.classList = sub.morning;
                if (fridayAfSub.value === null) {
                    sub.morning = noSubject;
                }
                else
                    fridayAfSub.value = sub.afternoon;
                fridayAfSub.parentNode.classList = sub.afternoon;
                if (fridayEvSub.value === null) {
                    sub.morning = noSubject;
                }
                else
                    fridayEvSub.value = sub.evening;
                fridayEvSub.parentNode.classList = sub.evening;
            }
        }
    )
}


//teacher functions
function loadMondayTeach(courseScheduleTeachArray) {
    courseScheduleTeachArray.forEach(function(teach) {
            if(teach.weekday === "MONDAY") {
                mondayMoTeach.value = teach.morning;
                mondayAfTeach.value = teach.afternoon;
                mondayEvTeach.value = teach.evening;
            }
        }
    )
}

function loadTuesdayTeach(courseScheduleTeachArray) {
    courseScheduleTeachArray.forEach(function(teach) {
            if(teach.weekday === "TUESDAY") {
                tuesdayMoTeach.value = teach.morning;
                tuesdayAfTeach.value = teach.afternoon;
                tuesdayEvTeach.value = teach.evening;
            }
        }
    )
}

function loadWednesdayTeach(courseScheduleTeachArray) {
    courseScheduleTeachArray.forEach(function(teach) {
            if(teach.weekday === "WEDNESDAY") {
                wednesdayMoTeach.value = teach.morning;
                wednesdayAfTeach.value = teach.afternoon;
                wednesdayEvTeach.value = teach.evening;
            }
        }
    )
}

function loadThursdayTeach(courseScheduleTeachArray) {
    courseScheduleTeachArray.forEach(function(teach) {
            if(teach.weekday === "THURSDAY") {
                thursdayMoTeach.value = teach.morning;
                thursdayAfTeach.value = teach.afternoon;
                thursdayEvTeach.value = teach.evening;
            }
        }
    )
}


function loadFridayTeach(courseScheduleTeachArray) {
    courseScheduleTeachArray.forEach(function(teach) {
            if(teach.weekday === "FRIDAY") {
                fridayMoTeach.value = teach.morning;
                fridayAfTeach.value = teach.afternoon;
                fridayEvTeach.value = teach.evening;
            }
        }
    )
}



