var m;

function searchNodes(oRoot, sTagName, fCallBack) {
    if (!oRoot.hasChildNodes()) return;
    var cns = oRoot.childNodes;
    for (var i = 0; i < cns.length; ++i) {
        if (cns[i].nodeType == 1 &&
            cns[i].nodeName == sTagName) fCallBack(cns[i]);
        searchNodes(cns[i], sTagName, fCallBack);
    }
};

function dealclick() {

    var r, c;

    outer: for (var y = 0; y < m.length; ++y) {
        for (var x = 0; x < m[y].length; ++x) {
            if (m[y][x] == this) {
                m[y][x].value = this.checked ? true : false;
                r = y;
                c = x;
                break outer;
            }
        }
    }
    if (r == 0 && c == 0) {
        for (var y = 0; y < m.length; ++y) {
            for (var x = 0; x < m[y].length; ++x) {
                m[y][x].checked = this.checked;
                m[y][x].value = this.checked ? true : false;
                m[y][x].parentNode.parentNode.style.backgroundColor = this.checked ? "#00b359" : "#ff6347";
                m[0][x].parentNode.parentNode.style.backgroundColor = this.checked ? "#ffffff" : "#ffffff";
                m[y][0].parentNode.parentNode.style.backgroundColor = this.checked ? "#ffffff" : "#ffffff";
            }
        }

    } else if (r == 0) {
        for (var y = 1; y < m.length; ++y) {
            m[y][c].checked = this.checked;
            m[y][c].value = this.checked ? true : false;
            m[y][c].parentNode.parentNode.style.backgroundColor = this.checked ? "#00b359" : "#ff6347";
        }

    } else if (c == 0) {
        for (var x = 1; x < m[0].length; ++x) {
            m[r][x].checked = this.checked;
            m[r][x].value = this.checked ? true : false;
            m[r][x].parentNode.parentNode.style.backgroundColor = this.checked ? "#00b359" : "#ff6347";
        }
    } else {
        this.parentNode.parentNode.style.backgroundColor = this.checked ? "#00b359" : "#ff6347";
    }
}

window.onload = function() {
    m = new Array();
    var sec = document.getElementById("availability");

    searchNodes(sec, "TR",
        function(tr) {
            var cbs = new Array();
            var tds = tr.childNodes;
            for (var i = 0; i < tds.length; ++i) {
                if (tds[i].nodeType != 1) continue;
                var txt = document.createTextNode(tds[i].innerHTML);
                var chk = document.createElement("input");
                chk.setAttribute("type", "checkbox");
                chk.setAttribute("value", false);
                chk.onclick = dealclick;
                var lbl = document.createElement("label");
                lbl.appendChild(txt);
                lbl.appendChild(chk);
                cbs.push(chk);
                tds[i].replaceChild(lbl, tds[i].firstChild);
            }
            m.push(cbs);
        }
    );
};
