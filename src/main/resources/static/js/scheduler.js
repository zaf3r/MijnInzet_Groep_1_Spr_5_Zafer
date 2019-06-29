//url variables
var getrequestWeeksUrl;
var getRequestDaysUrl;
var getRequestTeacherUrl = "http://localhost:8080/api/getTeachers";
var postRequestUrl =  "http://localhost:8080/api/saveTeacherSchedule";

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
var mondayAfSub = document.querySelector('div[name="maandagMiddagVak"]');
var mondayEvSub = document.querySelector('div[name="maandagAvondVak"]');
var tuesdayMoSub = document.querySelector('div[name="dinsdagOchtendVak"]');
var tuesdayAfSub = document.querySelector('div[name="dinsdagMiddagVak"]');
var tuesdayEvSub = document.querySelector('div[name="dinsdagAvondVak"]');
var wednesdayMoSub = document.querySelector('div[name="woensdagOchtendVak"]');
var wednesdayAfSub = document.querySelector('div[name="woensdagMiddagVak"]');
var wednesdayEvSub = document.querySelector('div[name="woensdagAvondVak"]');
var thursdayMoSub = document.querySelector('div[name="donderdagOchtendVak"]');
var thursdayAfSub = document.querySelector('div[name="donderdagMiddagVak"]');
var thursdayEvSub = document.querySelector('div[name="donderdagAvondVak"]');
var fridayMoSub = document.querySelector('div[name="vrijdagOchtendVak"]');
var fridayAfSub = document.querySelector('div[name="vrijdagMiddagVak"]');
var fridayEvSub = document.querySelector('div[name="vrijdagAvondVak"]');

//Other variables
var cohortNaam = document.querySelector('select[name="cohortName"]');
var cohortWeek = document.querySelector('select[name="weekNumber"]');
var teacherNames = document.createElement("select");
var noSubject = "Geen vak";
var weekDayObject = new Object();
var button = document.querySelector("#button");
var teacherObject = new Object();

//Rest call event listeners
$(function () {
    $(cohortNaam).on("change", cohortNaam, function () {
        getrequestWeeksUrl = "http://localhost:8080/api/cohort/weeks/" + cohortNaam.value;
        requestCallWeeks();
        if(!button.disabled) {
            $(button).attr("disabled", true);
        }
    });

    $(cohortWeek).on("change", cohortWeek, function () {
        getRequestDaysUrl = "http://localhost:8080/api/cohort/days/" + cohortNaam.value + "/" + cohortWeek.value;
        requestCallDays();
        if(button.disabled) {
            $(button).removeAttr('disabled');
        }
    });

    $(document).ready(function () {
        requestCallTeachers();
    });

    $(button).on("click", function () {
        postCallWeekObject();
    });
});

//Rest call functions
function requestCallWeeks() {
    $(function () {
        $.getJSON(getrequestWeeksUrl, function (data) {
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
            teacherObject = data;
            data.forEach(function (teacher) {
                var option = new Option(teacher.username, teacher.username);
                teacherNames.append(option);
            });
        }).fail(function () {
        })
    });
}

function postCallWeekObject() {

    // 15 variables here
    weekDayObject.cohort = cohortNaam.value;
    weekDayObject.weekNumber = cohortWeek.value;

    weekDayObject.mondayMorningTeacher = mondayMoTeach.value;
    weekDayObject.mondayAfternoonTeacher =  mondayAfTeach.value;
    weekDayObject.mondayEveningTeacher = mondayEvTeach.value;

    weekDayObject.tuesdayMorningTeacher = tuesdayMoTeach.value;
    weekDayObject.tuesdayAfternoonTeacher = tuesdayAfTeach.value;
    weekDayObject.tuesdayEveningTeacher = tuesdayEvTeach.value;

    weekDayObject.wednesdayMorningTeacher = wednesdayMoTeach.value;
    weekDayObject.wednesdayAfternoonTeacher = wednesdayAfTeach.value;
    weekDayObject.wednesdayEveningTeacher = wednesdayEvTeach.value;

    weekDayObject.thursdayMorningTeacher = thursdayMoTeach.value;
    weekDayObject.thursdayAfternoonTeacher = thursdayAfTeach.value;
    weekDayObject.thursdayEveningTeacher = thursdayEvTeach.value;

    weekDayObject.fridayMorningTeacher = fridayMoTeach.value;
    weekDayObject.fridayAfternoonTeacher = fridayAfTeach.value;
    weekDayObject.fridayEveningTeacher = fridayEvTeach.value;

    var WeekDayJson = JSON.stringify(weekDayObject);

    $.ajax({
        type: "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        url: postRequestUrl,
        data:WeekDayJson,
    }).done(alert("De inzet van docenten voor "+ cohortNaam.value +" week " + cohortWeek.value + " is succesvol opgeslagen!"));
}

//below functions that manipulate the form

//Load dropdown menu of weeks
function loadDropDownWeeks(dropdownItems) {
    var firstoption = new Option("Selecteer een week", "Selecteer een week");
    $(cohortWeek).empty();
    $(cohortWeek).append(firstoption);
    dropdownItems.forEach(function (item) {
        var option = new Option(item, item);
        $(cohortWeek).append(option);
    });
    $(cohortWeek).find("option").eq(0).attr("disabled",true);
    $(cohortWeek).find("option").eq(0).attr("selected",true);
    $(cohortWeek).find("option").eq(0).attr("hidden",true);
}

//teacher functions
function loadMondayTeach(courseScheduleTeachArray) {
    courseScheduleTeachArray.forEach(function (data) {
            if (data.dayOfWeek === "MONDAY") {
                mondayMoTeach.innerHTML = teacherNames.innerHTML;
                mondayMoTeach.value = data.teacherMorning.username;
                if (data.subjectMorning) {
                    mondayMoSub.innerHTML = data.subjectMorning
                } else {
                    mondayMoSub.innerHTML = noSubject;
                }

                mondayAfTeach.innerHTML = teacherNames.innerHTML;
                mondayAfTeach.value = data.teacherAfternoon.username;
                if (data.subjectAfteroon) {
                    mondayAfSub.innerHTML = data.subjectMorning
                } else {
                    mondayAfSub.innerHTML = noSubject;
                }

                mondayEvTeach.innerHTML = teacherNames.innerHTML;
                mondayEvTeach.value = data.teacherEvening.username;
                if (data.subjectEvening) {
                    mondayEvSub.innerHTML = data.subjectMorning
                } else {
                    mondayEvSub.innerHTML = noSubject;
                }
            }
        }
    )
}

function loadTuesdayTeach(courseScheduleTeachArray) {
    courseScheduleTeachArray.forEach(function (data) {
            if (data.dayOfWeek === "TUESDAY") {
                tuesdayMoTeach.innerHTML = teacherNames.innerHTML;
                tuesdayMoTeach.value = data.teacherMorning.username;
                if (data.subjectMorning) {
                    tuesdayMoSub.innerHTML = data.subjectMorning
                } else {
                    tuesdayMoSub.innerHTML = noSubject;
                }

                tuesdayAfTeach.innerHTML = teacherNames.innerHTML;
                tuesdayAfTeach.value = data.teacherAfternoon.username;
                if (data.subjectAfteroon) {
                    tuesdayAfSub.innerHTML = data.subjectMorning
                } else {
                    tuesdayAfSub.innerHTML = noSubject;
                }

                tuesdayEvTeach.innerHTML = teacherNames.innerHTML;
                tuesdayEvTeach.value = data.teacherEvening.username;
                if (data.subjectEvening) {
                    tuesdayEvSub.innerHTML = data.subjectMorning
                } else {
                    tuesdayEvSub.innerHTML = noSubject;
                }
            }
        }
    )
}


function loadWednesdayTeach(courseScheduleTeachArray) {
    courseScheduleTeachArray.forEach(function (data) {
            if (data.dayOfWeek === "WEDNESDAY") {
                wednesdayMoTeach.innerHTML = teacherNames.innerHTML;
                wednesdayMoTeach.value = data.teacherMorning.username;
                if (data.subjectMorning) {
                    wednesdayMoSub.innerHTML = data.subjectMorning
                } else {
                    wednesdayMoSub.innerHTML = noSubject;
                }

                wednesdayAfTeach.innerHTML = teacherNames.innerHTML;
                wednesdayAfTeach.value = data.teacherAfternoon.username;
                if (data.subjectAfteroon) {
                    wednesdayAfSub.innerHTML = data.subjectMorning
                } else {
                    wednesdayAfSub.innerHTML = noSubject;
                }

                wednesdayEvTeach.innerHTML = teacherNames.innerHTML;
                wednesdayEvTeach.value = data.teacherEvening.username;
                if (data.subjectEvening) {
                    wednesdayEvSub.innerHTML = data.subjectMorning
                } else {
                    wednesdayEvSub.innerHTML = noSubject;
                }
            }
        }
    )
}

function loadThursdayTeach(courseScheduleTeachArray) {
    courseScheduleTeachArray.forEach(function (data) {
            if (data.dayOfWeek === "THURSDAY") {
                thursdayMoTeach.innerHTML = teacherNames.innerHTML;
                thursdayMoTeach.value = data.teacherMorning.username;
                if (data.subjectMorning) {
                    thursdayMoSub.innerHTML = data.subjectMorning
                } else {
                    thursdayMoSub.innerHTML = noSubject;
                }

                thursdayAfTeach.innerHTML = teacherNames.innerHTML;
                thursdayAfTeach.value = data.teacherAfternoon.username;
                if (data.subjectAfteroon) {
                    thursdayAfSub.innerHTML = data.subjectMorning
                } else {
                    thursdayAfSub.innerHTML = noSubject;
                }

                thursdayEvTeach.innerHTML = teacherNames.innerHTML;
                thursdayEvTeach.value = data.teacherEvening.username;
                if (data.subjectEvening) {
                    thursdayEvSub.innerHTML = data.subjectMorning
                } else {
                    thursdayEvSub.innerHTML = noSubject;
                }
            }
        }
    )
}

function loadFridayTeach(courseScheduleTeachArray) {
    courseScheduleTeachArray.forEach(function (data) {
            if (data.dayOfWeek === "FRIDAY") {
                fridayMoTeach.innerHTML = teacherNames.innerHTML;
                fridayMoTeach.value = data.teacherMorning.username;
                if (data.subjectMorning) {
                    fridayMoSub.innerHTML = data.subjectMorning
                } else {
                    fridayMoSub.innerHTML = noSubject;
                }

                fridayAfTeach.innerHTML = teacherNames.innerHTML;
                fridayAfTeach.value = data.teacherAfternoon.username;
                if (data.subjectAfteroon) {
                    fridayAfSub.innerHTML = data.subjectMorning
                } else {
                    fridayAfSub.innerHTML = noSubject;
                }

                fridayEvTeach.innerHTML = teacherNames.innerHTML;
                fridayEvTeach.value = data.teacherEvening.username;
                if (data.subjectEvening) {
                    fridayEvSub.innerHTML = data.subjectMorning
                } else {
                    fridayEvSub.innerHTML = noSubject;
                }
            }
        }
    )
}
