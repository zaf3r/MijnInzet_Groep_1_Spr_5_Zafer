//url variables
var getrequestWeeksUrl;
var getRequestDaysUrl;
var getRequestTeacherUrl= "http://localhost:8080/api/getTeachers";
var postRequestUrl;

//Teacher Variables
var mondayMoTeach = document.querySelector('select[name="maandagOchtendDocent"]');
var mondayAfTeach = document.querySelector('select[name="maandagMiddagDocent"]');
var mondayEvTeach = document.querySelector('select[name="maandagAvondDocent"]');
var tuesdayMoTeach = document.querySelector('select[name="dinsdagOchtendDocent"]');
var tuesdayAfTeach = document.querySelector('select[name="dinsdagMiddagDocent"]');
var tuesdayEvTeach = document.querySelector('select[name="dinsdagAvondDocent"]');
var wednesdayMoTeach = document.querySelector('select[name="woensdagOchtendDocent"]');
var wednesdayAfTeach = document.querySelector('select[name="woensdagMiddagDocent"]');
var wednesdayEvTeach = document.querySelector('select[name="woensdagAvondDocent"]');
var thursdayMoTeach = document.querySelector('select[name="donderdagOchtendDocent"]');
var thursdayAfTeach = document.querySelector('select[name="donderdagMiddagDocent"]');
var thursdayEvTeach = document.querySelector('select[name="donderdagAvondDocent"]');
var fridayMoTeach = document.querySelector('select[name="vrijdagOchtendDocent"]');
var fridayAfTeach = document.querySelector('select[name="vrijdagMiddagDocent"]');
var fridayEvTeach = document.querySelector('select[name="vrijdagAvondDocent"]');

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
var teacherNames = document.createElement("select");


//Rest call event listeners
$(function() {
    $(cohortNaam).on("change", cohortNaam, function () {
        getrequestWeeksUrl = "http://localhost:8080/api/cohort/weeks/"+cohortNaam.value;
        requestCallWeeks();
    });

    $(cohortWeek).on("change", cohortWeek, function () {
        getRequestDaysUrl = "http://localhost:8080/api/cohort/days/"+cohortNaam.value+"/"+cohortWeek.value;
        console.log(getRequestDaysUrl);
        requestCallDays();
    });

    $( document ).ready(function () {
        requestCallTeachers();
    })
});

//Rest call functions
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
            loadMondayTeach(data);
            loadTuesdayTeach(data);
            loadWednesdayTeach(data);
            loadThursdayTeach(data);
            loadFridayTeach(data);
        }).fail(function () {
            console.log("Failed to make a request")
        })
    });
}

function requestCallTeachers() {
    $(function () {
        $.getJSON(getRequestTeacherUrl, function (data) {
            console.log(data);
            data.forEach(function (teacher) {
                var option = new Option(teacher.username, teacher.username);
                teacherNames.append(option);
            });
            console.log(teacherNames);
        }).fail(function () {
        })
    });
}

//below functions that manipulate the form

//Load dropdown menu of weeks
function loadDropDownWeeks(dropdownItems) {

    var firstoption = new Option("Selecteer een week", "Selecteer een week");
    $(cohortWeek).append(firstoption);
    dropdownItems.forEach(function (item) {
        var option = new Option(item, item);
        $(cohortWeek).append(option);
    })
}

//teacher functions
function loadMondayTeach(courseScheduleTeachArray) {
    courseScheduleTeachArray.forEach(function(data) {
            if(data.dayOfWeek === "MONDAY") {
                mondayMoTeach.innerHTML = teacherNames.innerHTML;
                mondayMoTeach.value = data.teacherMorning.username;
                mondayAfTeach.innerHTML = teacherNames.innerHTML;
                mondayAfTeach.value = data.teacherAfternoon.username;
                mondayEvTeach.innerHTML = teacherNames.innerHTML;
                mondayEvTeach.value = data.teacherEvening.username;
            }
        }
    )
}

function loadTuesdayTeach(courseScheduleTeachArray) {
    courseScheduleTeachArray.forEach(function(data) {
            if(data.dayOfWeek === "TUESDAY") {
                tuesdayMoTeach.innerHTML = teacherNames.innerHTML;
                tuesdayMoTeach.value = data.teacherMorning.username;
                tuesdayAfTeach.innerHTML = teacherNames.innerHTML;
                tuesdayAfTeach.value = data.teacherAfternoon.username;
                tuesdayEvTeach.innerHTML = teacherNames.innerHTML;
                tuesdayEvTeach.value = data.teacherEvening.username;
            }
        }
    )
}


function loadWednesdayTeach(courseScheduleTeachArray) {
    courseScheduleTeachArray.forEach(function(data) {
        if(data.dayOfWeek === "WEDNESDAY") {
            wednesdayMoTeach.innerHTML = teacherNames.innerHTML;
            wednesdayMoTeach.value = data.teacherMorning.username;
            wednesdayAfTeach.innerHTML = teacherNames.innerHTML;
            wednesdayAfTeach.value = data.teacherAfternoon.username;
            wednesdayEvTeach.innerHTML = teacherNames.innerHTML;
            wednesdayEvTeach.value = data.teacherEvening.username;
            }
        }
    )
}

function loadThursdayTeach(courseScheduleTeachArray) {
    courseScheduleTeachArray.forEach(function(data) {
        if(data.dayOfWeek === "THURSDAY") {
            thursdayMoTeach.innerHTML = teacherNames.innerHTML;
            thursdayMoTeach.value = data.teacherMorning.username;
            thursdayAfTeach.innerHTML = teacherNames.innerHTML;
            thursdayAfTeach.value = data.teacherAfternoon.username;
            thursdayEvTeach.innerHTML = teacherNames.innerHTML;
            thursdayEvTeach.value = data.teacherEvening.username;
            }
        }
    )
}

function loadFridayTeach(courseScheduleTeachArray) {
    courseScheduleTeachArray.forEach(function(data) {
        if(data.dayOfWeek === "FRIDAY") {
            fridayMoTeach.innerHTML = teacherNames.innerHTML;
            fridayMoTeach.value = data.teacherMorning.username;
            fridayAfTeach.innerHTML = teacherNames.innerHTML;
            fridayAfTeach.value = data.teacherAfternoon.username;
            fridayEvTeach.innerHTML = teacherNames.innerHTML;
            fridayEvTeach.value = data.teacherEvening.username;
            }
        }
    )
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
