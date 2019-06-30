var requestUserIncidentUrl;
var incidentCheckBoolean;

function incidentClash() {
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
            console.log(requestUserIncidentUrl);
        })
            .done(function () {
                incidentClashTest()
            })
    })
}

function incidentClashTest() {
    if (!incidentCheckBoolean) {
        alert("You have a clash!");
        addHardConstraintClass(dayPartCellSelected);
    } else {
        removeHardConstraintClass(dayPartCellSelected);
    }
}