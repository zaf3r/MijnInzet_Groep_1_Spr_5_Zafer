var getrequestCohorts = "http://localhost:8080/api/findCohorts";
var postRequestUrl = "http://localhost:8080/api/saveCohortSubject";
var postAddUrl = "http://localhost:8080/api/addSubjectToCohort";
var postRemoveUrl = "http://localhost:8080/api/removeSubjectToCohort";
var getrequestSelectedSubjects;
var getrequestPossibleSubjects;
var cohortNaam = document.querySelector('select[name="cohortNameList"]');
var cohortList = document.querySelector('#cohortList');
var selectedSubjects = document.querySelector('select[name="selectedSubjects"]');
var possibleSubjects = document.querySelector('select[name="possibleSubjects"]');
$(document).ready(function () {
    console.log("Here!");
    $(function () {
        $.getJSON(getrequestCohorts, function (data) {
            console.log(data);
            $(cohortList).empty();
            $(cohortList).append('<option value="-1">Selecteer een cohort</option>');
            $.each(data, function (i, item) {
                $(cohortList).append('<option value="'+ data[i] +'">' + data[i] + '</option>');
            });
        }).fail(function () {
            console.log("Failed to make a request")
        })
    });
});

$(cohortNaam).on("change", cohortNaam, function () {
    getrequestSelectedSubjects = "http://localhost:8080/api/findSubjectsOfCohorts/"+cohortNaam.value;
    getrequestPossibleSubjects = "http://localhost:8080/api/findPossibleSubjects/"+cohortNaam.value;
    console.log(getrequestSelectedSubjects);
    console.log(cohortNaam.value);
    getSelectedSubjects();
    getPossibleSubjects();
});

function getSelectedSubjects() {
    $.getJSON(getrequestSelectedSubjects, function (data) {
        console.log(data);
        $(selectedSubjects).empty();
        data.forEach(function (item) {
            var option = new Option(item.subjectName, item.subjectName);
            $(selectedSubjects).append(option);
        });
    }).fail(function () {
        console.log("Failed to make a request")
    })
}

function getPossibleSubjects() {
    $.getJSON(getrequestPossibleSubjects, function (data) {
        console.log(data);
        $(possibleSubjects).empty();
        data.forEach(function (item) {
            var option = new Option(item.subjectName, item.subjectName);
            $(possibleSubjects).append(option);
        });
    }).fail(function () {
        console.log("Failed to make a request")
    })
}

$(document).ready(function (){
    $("#addSubjectButton").click(function (){
        $("#possibleSubjects option:selected").prependTo("#selectedSubjects");
    });

    $("#removeSubjectButton").click(function (){
        $("#selectedSubjects option:selected").prependTo("#possibleSubjects");
    });
});

function add(){
    console.log("function add()");
    var subjectName = possibleSubjects.value;
    console.log(subjectName);

    $.ajax({
        type: "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        url: postAddUrl,
        data:subjectName,
    }).fail(function () {
        console.log("Failed to make a request")
    })
}


function remove(){
    console.log("function remove()");
    var subjectName = selectedSubjects.value;
    console.log(subjectName);

    $.ajax({
        type: "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        url: postRemoveUrl,
        data:subjectName,
    }).fail(function () {
        console.log("Failed to make a request")
    })
}