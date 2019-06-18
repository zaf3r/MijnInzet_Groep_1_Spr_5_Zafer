function confirmPlease() {
    var r = confirm('Weet u zeker dat u het vak wilt verwijderen?')
        if (r==true){
            return true;
        }
        else {
            //do not execute submit
            window.location.href="http://localhost:8080/manager/vak";
            return false;
        }
}


var button = document.getElementById("btn").addEventListener('click', opslaan);
// haal data van server op een toon dit op pagina
// bijvoorbeeld van conversie server
// https://www.remideboer.com/convert/temperature_post.php
// type:
// CORS: Introducing CORS: Cross-Origin Resource Sharing - https://www.youtube.com/watch?v=JVZIhCVFJ9c
// https://chrome.google.com/webstore/detail/allow-control-allow-origi/nlfbmbojpeacfghkpbjhddihlkkiljbi/related?hl=en
function opslaan() {

    var inputwaarde = document.getElementById("inputWaarde").value;
    // nu nog de waarde van de select box, selecteer waar selected voorkomt
    var type = document.getElementById("menu").value;

    var url = "saveKnowledgeandCourse";

    // AJAX!!
    var request = new XMLHttpRequest();
    request.open('POST', url, true);
    request.setRequestHeader('Content-Type', 'application/json'); // x-www-urlencoded
    request.setRequestHeader('Accept', 'application/json');


//    request.send('type=c2f&waarde=20');
    var data = { type:type, waarde: inputwaarde};
    request.send(JSON.stringify(data)); // JSON.stringify
    // verstuurd

    // wachten we op antwoord

    request.onreadystatechange = function() {
        // check eerst op done

        if(request.readyState === XMLHttpRequest.DONE){
            // dan op status 200 ok
            if(request.status === 200){
                // verwerk
                console.log(request.response);
                var responseObject = JSON.parse(request.response); // maak van de json string een javascript object
                var waarde = responseObject.waarde; // haal de waarde uit het object
                console.log(waarde);
                // pas het element aan op de pagina
                document.getElementById("uitkomst").innerHTML = "Het volgende is opgeslagen: " + waarde;
            } else {
                console.log("iets ging fout: status " + request.status + " " + request.statusText);
                alert("iets ging fout: status " + request.status + " " + request.statusText);
            }
        }
    }
}

