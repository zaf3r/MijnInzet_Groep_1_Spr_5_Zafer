var requestUserIncidentUrl;
var incidentCheckBoolean;


function incidentClash(dayPartCell) {
    dayPartCellSelected = dayPartCell;
    requestUserIncident();

}

function requestUserIncident() {
    $(function () {
        requestUserIncidentUrl = "http://localhost:8080/api/teacher/incident/"+teacherSelected.username+"/"+dayPartSelected+"/"+dateSelected;
        console.log(requestUserIncidentUrl);
        $.getJSON(requestUserIncidentUrl, function (data) {
            incidentCheckBoolean = data;
            console.log(data);
        }).fail(function () {
            console.log("Failed to make a request");
        })
            .done(function () {
                incidentClashTest()
            })
    })
}

function incidentClashTest() {
    console.log(incidentCheckBoolean);
    if (!incidentCheckBoolean) {
        alert("Docent " + teacherSelected.username + " Is heeft een incident (afwezigheid) gemeld op "+ dateSelected + " voor het bettrefende dagdeel." +
            " Indien u geen incident wilt met " + teacherSelected.username  + ", graag niet zonder");
        addHardConstraintClass(dayPartCellSelected);
    } else {
        removeHardConstraintClass(dayPartCellSelected);
    }
}