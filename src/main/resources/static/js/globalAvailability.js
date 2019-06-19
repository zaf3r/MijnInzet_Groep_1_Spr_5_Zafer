var cell;
function dealclick() {
    var row, column;
    var check = false;

    outer: for (var y = 0; y < cell.length; ++y) {
        for (var x = 0; x < cell[y].length; ++x) {
            if (cell[y][x] == this) {
                row = y;
                column = x;
                f(cell[row][column]);
                break outer;
            }
        }
    }
    if (row == 0 && column == 0) {

        for (var y = 1; y < cell.length; ++y) {
            for (var x = 1; x < cell[y].length; ++x) {
                if (cell[y][x].classList.contains('false')) {
                    f(cell[y][x]);
                    check = true;
                } else {}
            }
        }
        if (!check) {
            for (var y = 0; y < cell.length; ++y) {
                for (var x = 0; x < cell[y].length; ++x) {
                    f(cell[y][x]);
                }
            }
        }
    } else if (row == 0) {
        for (var y = 1; y < cell.length; ++y) {
            if (cell[y][column].classList.contains('false')) {
                f(cell[y][column]);
                check = true;
            } else {}
        }
        if (!check) {
            for (var y = 1; y < cell.length; ++y) {
                f(cell[y][column]);
            }
        }
    } else if (column == 0) {
        for (var x = 1; x < cell[0].length; ++x) {
            if (cell[row][x].classList.contains('false')) {
                f(cell[row][x]);
                check = true;
            } else {}
        }
        if (!check) {
            for (var x = 1; x < cell[0].length; ++x) {
                f(cell[row][x]);
            }
        }
    } else {}
}

window.onload = function tableCell(){
    cell = new Array();
    var table=document.getElementById('deTabel');
    var rows=table.rows;
    var rowLength=rows.length;

    for (var i = 0; i <rowLength; i++) {
        var cells=rows[i].cells;
        var cellsArray = new Array();
        for (var j = 0; j < cells.length; j++) {
            cells[j].onclick=dealclick;
            cellsArray.push(cells[j]);
        }
        cell.push(cellsArray);
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