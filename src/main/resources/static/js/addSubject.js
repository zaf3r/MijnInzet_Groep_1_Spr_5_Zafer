
//dead code

function confirmPlease() {
    //var myClass = Java.type("main.java.makeitwork.mijninzet.ManagerController")
    var myClass = confirm('Weet u zeker dat u het vak wilt verwijderen?')
    {
        if (myClass == true) {
            //delete
            return true
        } else {
            //do nothing
            return false
        }
    }
}


var button = document.getElementById("btn").addEventListener('click', opslaan);

function opslaan() {

    var subjectName = document.getElementById("inputWaarde").value;
    var knowledgeAreas = [ { "knowledgeAreaId": document.getElementById("menu").value}];
    var url = "saveKnowledgeandCourse";

    // AJAX!!
    var request = new XMLHttpRequest();
    request.open('POST', url, true);
    request.setRequestHeader('Content-Type', 'application/json');
    request.setRequestHeader('Accept', 'application/json');


//    request.send('type=c2f&waarde=20');
    var data = { knowledgeAreas, subjectName};

    console.log(JSON.stringify(data))

    request.send(JSON.stringify(data));
    // verstuurd

    // wachten we op antwoord

    request.onreadystatechange = function() {


        if(request.readyState === XMLHttpRequest.DONE){

            if(request.status === 200){
                // verwerk
                console.log(request.response);
                var responseObject = JSON.parse(request.response); // maak van de json string een javascript object
                var waarde = responseObject.waarde; // haal de waarde uit het object
                console.log(responseObject);
                // pas het element aan op de pagina
                document.getElementById("uitkomst").innerHTML = "Het volgende is opgeslagen: " + responseObject;
            } else {
                console.log("iets ging fout: status " + request.status + " " + request.statusText);
                alert("iets ging fout: status " + request.status + " " + request.statusText);
            }
        }
    }
}

