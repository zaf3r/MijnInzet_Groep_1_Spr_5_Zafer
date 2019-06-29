var requestCohortClashUrl;
var teacherSelected;
var dateSelected;
var dayPartSelected;
var clashValue;

function cohortClashMondayMo(teacher, date, dayPart) {
    teacherSelected = teacher;
    dateSelected = date;
    dayPartSelected = dayPart;
    requestCallCohortClash();
    if(clashValue) {
        if (!mondayEvTeach.parentNode.classList.contains(HardConstraint)) {
            mondayEvTeach.parentNode.classList.add(HardConstraint);
        }
    }

}

function requestCallCohortClash() {
    $(function () {
        requestCohortClashUrl = "http://localhost:8080/api/cohort/scheduled/"+
            teacherSelected.username+"/"+dayPartSelected+"/"+dateSelected;
        $.getJSON(requestCohortClashUrl, function (data) {
            clashValue = data;
        }).fail(function () {
            console.log("Failed to make a request")
        })
    });
}