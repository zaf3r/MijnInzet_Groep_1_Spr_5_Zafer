var requestUserIncidentUrl;
var incidentCheckBoolean;


function incidentClash() {
    requestUserIncident();

}

function requestUserIncident() {
    $(function () {
        console.log("I am in the function");
        requestUserIncidentUrl = "http://localhost:8080/api/teacher/incident/"+teacherSelected.username+"/"+dayPartSelected+"/"+dateSelected;
        console.log(requestUserIncidentUrl);
        $.getJSON(requestUserIncidentUrl, function (data) {
            incidentCheckBoolean = data;
        }).fail(function () {
            console.log("Failed to make a request");
        })
            .done(function () {
                console.log("Succes!");
                incidentClashTest()
            })
    })
}

function incidentClashTest() {
    if (!incidentCheckBoolean) {
        addHardConstraintClass(dayPartCellSelected);
        alert("Docent " + teacherSelected.username + " Is heeft een incident (afwezigheid) gemeld op "+ dateSelected + " voor het bettrefende dagdeel." +
            " Indien u geen incident wilt met " + teacherSelected.username  + ", graag niet zonder");

    } else {
        removeHardConstraintClass(dayPartCellSelected);
    }
}