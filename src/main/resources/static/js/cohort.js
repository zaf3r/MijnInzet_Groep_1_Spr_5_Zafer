// select date
$( function() {
    $( "#from" ).datepicker({
        changeMonth: true,
        changeYear: true,
        minDate: new Date()
    });

    $( "#to" ).datepicker({
        changeMonth: true,
        changeYear: true,
        minDate: new Date()
    });
} );

