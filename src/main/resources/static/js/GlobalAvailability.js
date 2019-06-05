var deTabel = document.querySelector('#deTabel');

deTabel.addEventListener('click', function(e) {
    var td = e.target;
    if (td.tagName === 'TD') {
        e.target.classList.toggle('false');
        e.target.classList.toggle('true');

        if (e.target.classList.contains('true')) {
            td.firstChild.value = 'true';
        } else {
            td.firstChild.value = 'false';
        }
    }
    console.log(td.firstChild.value);
});