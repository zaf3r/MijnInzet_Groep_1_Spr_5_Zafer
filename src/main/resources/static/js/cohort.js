// select date
$( function() {
    $( "#from" ).datepicker({
        changeMonth: true,
        changeYear: true
    });

    $( "#to" ).datepicker({
        changeMonth: true,
        changeYear: true
    });
} );

// window.onload = function(){
//     var option =
// }

function changeCohort(cohort) {
    location.href = "http://localhost:8080/manager/cohort";
}