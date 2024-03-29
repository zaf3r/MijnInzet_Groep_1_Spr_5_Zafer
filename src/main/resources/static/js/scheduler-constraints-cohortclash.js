var requestCohortClashUrl;
var teacherSelected;
var dateSelected;
var dayPartSelected;
var clashValue;
var dayPartCellSelected;

function cohortClash(teacher, date, dayPart, dayPartCell) {
    teacherSelected = teacher;
    dateSelected = date;
    dayPartSelected = dayPart;
    dayPartCellSelected = dayPartCell;
    requestCallCohortClash();
}

function requestCallCohortClash() {
    $(function () {
        requestCohortClashUrl = "http://localhost:8080/api/cohort/scheduled/"+
            teacherSelected.username+"/"+dayPartSelected+"/"+dateSelected;
        $.getJSON(requestCohortClashUrl, function (data) {
            clashValue = data;
        }).fail(function () {
            console.log("Failed to make a request");
        })
            .done(function () {
                cohortClashTest();
            })
    })
}

function cohortClashTest() {
    if(clashValue) {
        console.log(clashValue);
        alert("Docent " + teacherSelected.username + " Is al gepland op "+ dateSelected + " voor het bettrefende dagdeel." +
            " Docenten zijn ondanks de verwachting die gesteld worden, ook maar mensen en kunnen niet op twee verschillende plekken tegelijk zijn.");
        addHardConstraintClass(dayPartCellSelected);
    }
}