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
