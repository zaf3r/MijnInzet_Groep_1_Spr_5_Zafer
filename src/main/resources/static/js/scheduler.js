//url variables
var getrequestWeeksUrl;
var getRequestDaysUrl;
var getRequestCohortScheduleUrl;
var getRequestTeacherUrl = "http://localhost:8080/api/getTeachers";
var postRequestUrl = "http://localhost:8080/api/saveTeacherSchedule";

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

//Rest call results
var weekDayObject = new Object();
var teacherObject = new Object();
var subjectScheduleObject = new Object();

//Specific HTML fields
var cohortNaam = document.querySelector('select[name="cohortName"]');
var cohortWeek = document.querySelector('select[name="weekNumber"]');
var button = document.querySelector("#button");

// Dates of days
var dateMonday;
var dateTuesday;
var dateWednesday;
var dateThursday;
var dateFriday;

var morning = "morning";
var afternoon = "afternoon";
var evening = "evening";

var morningDutch = "ochtend";
var afternoonDutch = "middag";
var eveningDutch = "avond";

//Other variables
var teacherNames = document.createElement("select");
var noSubject = "Geen vak";
var allTableCells = document.querySelectorAll("td");
var dateCourseScheduleSelected;


//Rest call event listeners
$(function () {
    $(cohortNaam).on("change", cohortNaam, function () {
        getrequestWeeksUrl = "http://localhost:8080/api/cohort/weeks/" + cohortNaam.value;
        requestCallWeeks();
        if (!button.disabled) {
            $(button).attr("disabled", true);
        }
        resetClassColours();
    });

    $(cohortWeek).on("change", cohortWeek, function () {
        getRequestDaysUrl = "http://localhost:8080/api/cohort/days/" + cohortNaam.value + "/" + cohortWeek.value;
        resetClassColours();
        requestCallDays();
        if (button.disabled) {
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
            loadDates(data);
        }).fail(function () {
            console.log("Failed to make a request")
        })
    });
}

function requestCallSubjectSchedule() {
    $(function () {
        getRequestCohortScheduleUrl = "http://localhost:8080/api/getCourseSchedule/" + dateCourseScheduleSelected + "/" + cohortNaam.value;
        $.getJSON(getRequestDaysUrl, function (data) {
            subjectScheduleObject = data;
        }).fail(function () {
            console.log("Failed to make a request")
        })
            .done(function () {
                console.log("Request " + getRequestCohortScheduleUrl + " was succesfull")
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
    weekDayObject.mondayAfternoonTeacher = mondayAfTeach.value;
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
    console.log(WeekDayJson);

    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        url: postRequestUrl,
        data: WeekDayJson,
    }).done(alert("De inzet van docenten voor " + cohortNaam.value + " week " + cohortWeek.value + " is succesvol opgeslagen!"));
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
    $(cohortWeek).find("option").eq(0).attr("disabled", true);
    $(cohortWeek).find("option").eq(0).attr("selected", true);
    $(cohortWeek).find("option").eq(0).attr("hidden", true);
}

//teacher functions
function loadMondayTeach(courseScheduleTeachArray) {
    courseScheduleTeachArray.forEach(function (data) {
            if (data.dayOfWeek === "MONDAY") {
                dateMonday = data.date;
                dateCourseScheduleSelected = dateMonday;
                requestCallSubjectSchedule();

                mondayMoTeach.innerHTML = teacherNames.innerHTML;
                mondayMoTeach.value = data.teacherMorning.username;

                mondayAfTeach.innerHTML = teacherNames.innerHTML;
                mondayAfTeach.value = data.teacherAfternoon.username;

                mondayEvTeach.innerHTML = teacherNames.innerHTML;
                mondayEvTeach.value = data.teacherEvening.username;

                mondayMoSub.innerHTML = noSubject;
                mondayAfSub.innerHTML = noSubject;
                mondayEvSub.innerHTML = noSubject;

                if(!subjectScheduleObject) {
                    subjectScheduleObject.forEach(function (courseSchedule) {
                        if (courseSchedule.partOfDay === morningDutch) {
                            mondayMoSub.innerHTML = courseSchedule.subject.subjectName;
                        } else if (courseSchedule.partOfDay === afternoonDutch) {
                            mondayAfSub.innerHTML = courseSchedule.subject.subjectName;
                        } else {
                            mondayEvSub.innerHTML = courseSchedule.subject.subjectName;
                        }
                    })
                }
            }
        }
    )
}

function loadTuesdayTeach(courseScheduleTeachArray) {
    courseScheduleTeachArray.forEach(function (data) {
            if (data.dayOfWeek === "TUESDAY") {
                dateTuesday = data.date;
                dateCourseScheduleSelected = dateTuesday;
                requestCallSubjectSchedule();

                tuesdayMoTeach.innerHTML = teacherNames.innerHTML;
                tuesdayMoTeach.value = data.teacherMorning.username;

                tuesdayAfTeach.innerHTML = teacherNames.innerHTML;
                tuesdayAfTeach.value = data.teacherAfternoon.username;

                tuesdayEvTeach.innerHTML = teacherNames.innerHTML;
                tuesdayEvTeach.value = data.teacherEvening.username;

                tuesdayMoSub.innerHTML = noSubject;
                tuesdayAfSub.innerHTML = noSubject;
                tuesdayEvSub.innerHTML = noSubject;

                if(!subjectScheduleObject) {
                    subjectScheduleObject.forEach(function (courseSchedule) {
                        if (courseSchedule.partOfDay === morningDutch) {
                            tuesdayMoSub.innerHTML = courseSchedule.subject.subjectName;
                        } else if (courseSchedule.partOfDay === afternoonDutch) {
                            tuesdayAfSub.innerHTML = courseSchedule.subject.subjectName;
                        } else {
                            tuesdayEvSub.innerHTML = courseSchedule.subject.subjectName;
                        }
                    })
                }
            }
        }
    )
}


function loadWednesdayTeach(courseScheduleTeachArray) {
    courseScheduleTeachArray.forEach(function (data) {
            if (data.dayOfWeek === "WEDNESDAY") {
                dateWednesday = data.date;
                dateCourseScheduleSelected = dateWednesday;
                requestCallSubjectSchedule();

                wednesdayMoTeach.innerHTML = teacherNames.innerHTML;
                wednesdayMoTeach.value = data.teacherMorning.username;

                wednesdayAfTeach.innerHTML = teacherNames.innerHTML;
                wednesdayAfTeach.value = data.teacherAfternoon.username;

                wednesdayEvTeach.innerHTML = teacherNames.innerHTML;
                wednesdayEvTeach.value = data.teacherEvening.username;

                wednesdayMoSub.innerHTML = noSubject;
                wednesdayAfSub.innerHTML = noSubject;
                wednesdayEvSub.innerHTML = noSubject;

                if(!subjectScheduleObject) {
                    subjectScheduleObject.forEach(function (courseSchedule) {
                        if (courseSchedule.partOfDay === morningDutch) {
                            wednesdayMoSub.innerHTML = courseSchedule.subject.subjectName;
                        } else if (courseSchedule.partOfDay === afternoonDutch) {
                            wednesdayAfSub.innerHTML = courseSchedule.subject.subjectName;
                        } else {
                            wednesdayEvSub.innerHTML = courseSchedule.subject.subjectName;
                        }
                    })
                }
            }
        }
    )
}

function loadThursdayTeach(courseScheduleTeachArray) {
    courseScheduleTeachArray.forEach(function (data) {
            if (data.dayOfWeek === "THURSDAY") {
                dateThursday = data.date;
                dateCourseScheduleSelected = dateThursday;
                requestCallSubjectSchedule();

                thursdayMoTeach.innerHTML = teacherNames.innerHTML;
                thursdayMoTeach.value = data.teacherMorning.username;

                thursdayAfTeach.innerHTML = teacherNames.innerHTML;
                thursdayAfTeach.value = data.teacherAfternoon.username;

                thursdayEvTeach.innerHTML = teacherNames.innerHTML;
                thursdayEvTeach.value = data.teacherEvening.username;

                thursdayMoSub.innerHTML = noSubject;
                thursdayAfSub.innerHTML = noSubject;
                thursdayEvSub.innerHTML = noSubject;

                if(!subjectScheduleObject) {
                    subjectScheduleObject.forEach(function (courseSchedule) {
                        if (courseSchedule.partOfDay === morningDutch) {
                            thursdayMoSub.innerHTML = courseSchedule.subject.subjectName;
                        } else if (courseSchedule.partOfDay === afternoonDutch) {
                            thursdayAfSub.innerHTML = courseSchedule.subject.subjectName;
                        } else {
                            thursdayEvSub.innerHTML = courseSchedule.subject.subjectName;
                        }
                    })
                }
            }
        }
    )
}

function loadFridayTeach(courseScheduleTeachArray) {
    courseScheduleTeachArray.forEach(function (data) {
            if (data.dayOfWeek === "FRIDAY") {
                dateFriday = data.date;
                dateCourseScheduleSelected = dateFriday;
                requestCallSubjectSchedule();

                fridayMoTeach.innerHTML = teacherNames.innerHTML;
                fridayMoTeach.value = data.teacherMorning.username;

                fridayAfTeach.innerHTML = teacherNames.innerHTML;
                fridayAfTeach.value = data.teacherAfternoon.username;

                fridayEvTeach.innerHTML = teacherNames.innerHTML;
                fridayEvTeach.value = data.teacherEvening.username;

                fridayMoSub.innerHTML = noSubject;
                fridayAfSub.innerHTML = noSubject;
                fridayEvSub.innerHTML = noSubject;

                if(!subjectScheduleObject) {
                    subjectScheduleObject.forEach(function (courseSchedule) {
                        if (courseSchedule.partOfDay === morningDutch) {
                            fridayMoSub.innerHTML = courseSchedule.subject.subjectName;
                        } else if (courseSchedule.partOfDay === afternoonDutch) {
                            fridayAfSub.innerHTML = courseSchedule.subject.subjectName;
                        } else {
                            fridayEvSub.innerHTML = courseSchedule.subject.subjectName;
                        }
                    })
                }
            }
        }
    )
}

function resetClassColours() {
    $.each(allTableCells, function () {
        if ($(this).hasClass(HardConstraint)) {
            $(this).removeClass(HardConstraint);
        }
    })
}

function loadDates(dataCohortWeek) {
    dataCohortWeek.forEach(function (day) {
        if (day.dayOfWeek === monday) {
            dateMonday = day.date;
        } else if (day.dayOfWeek === tuesday) {
            dateTuesday = day.date;
        } else if (day.dayOfWeek === wednesday) {
            dateWednesday = day.date;
        } else if (day.dayOfWeek === thursday) {
            dateThursday = day.date;
        } else {
            dateFriday = day.date;
        }
    })

}