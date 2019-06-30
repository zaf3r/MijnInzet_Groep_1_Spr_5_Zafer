
var cohortName;
var subject;
var dateLocal;
var subjectName;
var date;
var partOfDay;
var status = "";

$(document).ready(function () {
    $.ajax({
        type: "POST",
        url: "/cohortsToPlan",
        contentType: "application/json; charset-utf-8",
        dataType: "json",
        data: "{}",
        success: function (response) {
            $("#cohorts2Plan").empty();
            $("#cohorts2Plan").append('<option value="-1">Welke cohort moet gepland worden?</option>')
            $.each(response, function (i, item) {
                $("#cohorts2Plan").append('<option value="">' + response[i].cohortName + '</option>');
            });
        }
    });
});

$("#cohorts2Plan").on("change", function () {
    var selectedText = $(this).find(':selected').text();
    cohortName = selectedText;
    payload = JSON.stringify({cohortName: selectedText});
    $.ajax({
        type: 'POST',
        url: '/vakkenCohort',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: payload
    }).done(function (response) {
        var n = 1;
        $("#vakkenCohort").empty();
        $.each(response, function (i, item) {
            $("#vakkenCohort").append('<option value="">' + response[i].subjectName + '</option>');
        });
    })
});
//todo min max date op cohort begin and end
$("#vakkenCohort").on("change", function () {
    var selectedText = $(this).find(':selected').text();
    subject = selectedText;
    payload = JSON.stringify({subjectName: selectedText});
    $.ajax({
        type: 'POST',
        url: '/aantalDagen',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: payload
    }).done(function (response) {
        var days = response;
        var n = 1;
        $("#regelPerLes").empty();
        $.each(days, function (i, item) {
            n = (i + 1) * 10;
            $("#regelPerLes").append('<div class="grid-item"><input type="date" class="largerText"  name="date" id="' + n + '" onchange="checkDate(event,id)"></div>');
            n++;
            $("#regelPerLes").append('<div class="grid-item"><input type="checkbox" class="largerCheckbox" name="dayPart" style="display:none" onclick="showStore(id)" id="' + n + '"></div> ');
            n++;
            $("#regelPerLes").append('<div class="grid-item"><input type="checkbox" class="largerCheckbox" name="dayPart" style="display:none" onclick="showStore(id)" id="' + n + '"></div> ');
            n++;
            $("#regelPerLes").append('<div class="grid-item"><input type="checkbox" class="largerCheckbox" name="dayPart" style="display:none" onclick="showStore(id)" id="' + n + '"></div> ');
            n++;
            $("#regelPerLes").append('<div class="grid-item"><input type="checkbox" class="largerCheckbox" name="store" id="' + n + '" style="display:none" onclick="storeCourse(id)"></div> ');
            n++;
            o=n+1
            p=o+1;
            $("#regelPerLes").append('<div class="grid-item"><span type="text" id="' + n + '" style="display:none">opgeslagen</span>' +
                '                                            <span type="text" id="' + o + '" style="display:none">geef minimaal 1 dagdeel op</span>' +
                '                                            <span type="text" id="' + p + '" style="display:none">op deze dag zijn geen onderwijs activiteiten</span></div> ');
        });
    })
});

function storeCourse(id) {
    if ($("input[type=checkbox][" + elementContent(id, 3) + "]:checked").val() == "on") {
        partOfDay = "OCHTEND";
        storeCourseService(id)
    }
    ;
    if ($("input[type=checkbox][" + elementContent(id, 2) + "]:checked").val() == 'on') {
        partOfDay = "MIDDAG";
        storeCourseService(id)
    }
    ;
    if ($("input[type=checkbox][" + elementContent(id, 1) + "]:checked").val() == "on") {
        partOfDay = "AVOND";
        storeCourseService(id)
    }
    ;
    if (($("input[type=checkbox][" + elementContent(id, 3) + "]:checked").val() != "on")&&
        ($("input[type=checkbox][" + elementContent(id, 2) + "]:checked").val() != 'on')&&
        ($("input[type=checkbox][" + elementContent(id, 1) + "]:checked").val() != "on")){
        document.getElementById(id).checked = false;
        var teller = (parseInt(id) + 2).toString();
        document.getElementById(teller).style.display = "block";


    }
}

function storeCourseService(id) {
    date = dateLocal;
    subjectName = subject;
    var course = {cohortName, subjectName, date, partOfDay, status};
    var payload = JSON.stringify(course);
    console.log(payload);
    $.ajax({
        type: 'POST',
        url: '/storeCourse',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: payload
    }).done(function (response) {
        if (response["exists"]) {
            var teller = (parseInt(id) + 1).toString();
            document.getElementById(teller).style.display = "block";
            //    todo aantal workshops
        }
    });
}

function show(id, view) {
    var see = view ? 'block' : 'none';
    var teller = (parseInt(id) + 1).toString();
    document.getElementById(teller).style.display = see;//morgen
    var teller = (parseInt(id) + 2).toString();
    document.getElementById(teller).style.display = see;//middag
    var teller = (parseInt(id) + 3).toString();
    document.getElementById(teller).style.display = see;//avond
    var teller = (parseInt(id) + 4).toString();
    document.getElementById(teller).style.display = see;//opslaan
}

function showStore(id){
    // var pointer=(id-(id%11));
    // var teller = (parseInt(pointer) + 5).toString();
    // document.getElementById(teller).style.display = "none";
    //todo van boeken tot geboekt weergeven
    //deze functie moet checkbox opslaan tonen of verbergen
    //de box mag alleen getoond worden als 1 van de dagdelen is checked
    // var oneOfThree=false;
    // if (id%11!=0){id=(id%11)};
    // var teller=  (parseInt(id)).toString();
    // if ($("input[type=checkbox]["+elementContent(id,0)+"]:checked").val()=="on"){oneOfThree=true};
    // if ($("input[type=checkbox]["+elementContent(id,1)+"]:checked").val()=='on'){oneOfThree=true};
    // if ($("input[type=checkbox]["+elementContent(id,2)+"]:checked").val()=="on"){oneOfThree=true};
    // var teller=  (parseInt(id+3)).toString();
    // if(oneOfThree){document.getElementById(teller).style.display = "block";
    // } else {document.getElementById(teller).style.display = "none"}
}

function checkDate(event,id) {
    dateLocal = event.target.value;
    var view;
    if (dateLocal != '') {
        view = true
    }
    ;
    if (dateLocal == '') {
        view = false
    }
    ;
    var date = dateLocal;
    var desc = 'vakantie';
    show(id, view);
    payload = JSON.stringify({date, desc});
    if (dateLocal != '') {
        $.ajax({
            type: 'POST',
            url: '/checkDate',
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: payload
        }).done(function (response) {
            if (response["exists"]) {
                view = !response["exists"];
                show(id, view);
                var teller = (parseInt(id) + 7).toString();
                document.getElementById(teller).style.display = "block";
            } else {
                view = true;
                show(id, view);
                var teller = (parseInt(id) + 7).toString();
                document.getElementById(teller).style.display = "none";
                $.ajax({
                    type: 'POST',
                    url: '/plannedWorkshop',
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    data: payload
                }).done(function (response) {
                    if (response["exists"]) {
                        console.log(response);
                        console.log("heeft planning");
                        var teller = (parseInt(id) + 1).toString();
                        {if (response["morgen"]) {document.getElementById(teller).style.display = "none";}}
                        var teller = (parseInt(id) + 2).toString();
                        {if (response["middag"]){document.getElementById(teller).style.display = "none";}}
                        var teller = (parseInt(id) + 3).toString();
                        {if (response["avond"]){document.getElementById(teller).style.display = "none";}}
                        var teller = (parseInt(id) + 4).toString();
                        if (response["morgen"]&&response["middag"]&&response["avond"]){
                            document.getElementById(teller).style.display = "none"}
                    } else {
                        console.log(response);
                        console.log("heeft geen planning")
                        view=true;
                        show(id,view);
                    }
                })
            }
        });
    }
}

function elementContent(id,i){
    //de id van element relatief tov de column "opslaan"
    var elementId=parseInt(id)-i;
    var elementIdString=elementId.toString();
    return "id='"+elementIdString+"'";
}
