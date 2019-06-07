var m;
function dealclick() {
    var r, c;
    var check = false;

    outer: for (var y = 0; y < m.length; ++y) {
        for (var x = 0; x < m[y].length; ++x) {
            if (m[y][x] == this) {
                r = y;
                c = x;
                f(m[r][c]);
                break outer;
            }
        }
    }
    if (r == 0 && c == 0) {

        for (var y = 1; y < m.length; ++y) {
            for (var x = 1; x < m[y].length; ++x) {
                if (m[y][x].classList.contains('false')) {
                    f(m[y][x]);
                    check = true;
                } else {}
            }
        }
        if (!check) {
            for (var y = 0; y < m.length; ++y) {
                for (var x = 0; x < m[y].length; ++x) {
                    f(m[y][x]);
                }
            }
        }
    } else if (r == 0) {
        for (var y = 1; y < m.length; ++y) {
            if (m[y][c].classList.contains('false')) {
                f(m[y][c]);
                check = true;
            } else {}
        }
        if (!check) {
            for (var y = 1; y < m.length; ++y) {
                f(m[y][c]);
            }
        }
    } else if (c == 0) {
        for (var x = 1; x < m[0].length; ++x) {
            if (m[r][x].classList.contains('false')) {
                f(m[r][x]);
                check = true;
            } else {}
        }
        if (!check) {
            for (var x = 1; x < m[0].length; ++x) {
                f(m[r][x]);
            }
        }
    } else {}
}

window.onload = function tabCell(){
    m = new Array();
    var tab=document.getElementById('deTabel');
    var rows=tab.rows;
    var rlen=rows.length;

    for (var i = 0; i <rlen; i++) {
        var cells=rows[i].cells;
        var cbs = new Array();
        for (var j = 0; j < cells.length; j++) {
            cells[j].onclick=dealclick;
            cbs.push(cells[j]);
        }
        m.push(cbs);
    }
}

function f(e) {
    e.classList.toggle("false");
    e.classList.toggle("true");
    if (e.classList.contains('true')) {
        e.firstChild.value = 'true';
    } else {
        e.firstChild.value = 'false';
    }
}