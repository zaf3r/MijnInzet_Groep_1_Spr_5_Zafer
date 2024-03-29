if (typeof DayPilot === 'undefined') {
    var DayPilot = {};
}
;(function () {
    var $a = function () {
    };
    if (typeof DayPilot.Calendar !== 'undefined') {
        return;
    }
    ;var DayPilotCalendar = {};
    DayPilotCalendar.selectedCells = null;
    DayPilotCalendar.topSelectedCell = null;
    DayPilotCalendar.bottomSelectedCell = null;
    DayPilotCalendar.selecting = false;
    DayPilotCalendar.column = null;
    DayPilotCalendar.firstSelected = null;
    DayPilotCalendar.firstMousePos = null;
    DayPilotCalendar.originalMouse = null;
    DayPilotCalendar.originalHeight = null;
    DayPilotCalendar.originalTop = null;
    DayPilotCalendar.resizing = null;
    DayPilotCalendar.globalHandlers = false;
    DayPilotCalendar.moving = null;
    DayPilotCalendar.register = function ($b) {
        if (!DayPilotCalendar.registered) {
            DayPilotCalendar.registered = [];
        }
        ;var r = DayPilotCalendar.registered;
        for (var i = 0; i < r.length; i++) {
            if (r[i] === $b) {
                return;
            }
        }
        ;r.push($b);
    };
    DayPilotCalendar.unregister = function ($b) {
        var a = DayPilotCalendar.registered;
        if (!a) {
            return;
        }
        ;var i = DayPilot.indexOf(a, $b);
        if (i === -1) {
            return;
        }
        ;a.splice(i, 1);
    };
    DayPilotCalendar.getCellsAbove = function ($c) {
        var $d = [];
        var c = DayPilotCalendar.getColumn($c);
        var tr = $c.parentNode;
        var $e = null;
        while (tr && $e !== DayPilotCalendar.firstSelected) {
            $e = tr.getElementsByTagName("td")[c];
            $d.push($e);
            tr = tr.previousSibling;
            while (tr && tr.tagName !== "TR") {
                tr = tr.previousSibling;
            }
        }
        ;
        return $d;
    };
    DayPilotCalendar.getCellsBelow = function ($c) {
        var $d = [];
        var c = DayPilotCalendar.getColumn($c);
        var tr = $c.parentNode;
        var $e = null;
        while (tr && $e !== DayPilotCalendar.firstSelected) {
            $e = tr.getElementsByTagName("td")[c];
            $d.push($e);
            tr = tr.nextSibling;
            while (tr && tr.tagName !== "TR") {
                tr = tr.nextSibling;
            }
        }
        ;
        return $d;
    };
    DayPilotCalendar.getColumn = function ($c) {
        var i = 0;
        while ($c.previousSibling) {
            $c = $c.previousSibling;
            if ($c.tagName === "TD") {
                i++;
            }
        }
        ;
        return i;
    };
    DayPilotCalendar.gUnload = function (ev) {
        if (!DayPilotCalendar.registered) {
            return;
        }
        ;var r = DayPilotCalendar.registered;
        for (var i = 0; i < r.length; i++) {
            var c = r[i];
            c.dispose();
            DayPilotCalendar.unregister(c);
        }
    };
    DayPilotCalendar.gMouseUp = function (e) {
        if (DayPilotCalendar.resizing) {
            if (!DayPilotCalendar.resizingShadow) {
                DayPilotCalendar.resizing.style.cursor = 'default';
                document.body.style.cursor = 'default';
                DayPilotCalendar.resizing = null;
                return;
            }
            ;var $f = DayPilotCalendar.resizing.event;
            var $g = DayPilotCalendar.resizingShadow.clientHeight + 4;
            var top = DayPilotCalendar.resizingShadow.offsetTop;
            var $h = DayPilotCalendar.resizing.dpBorder;
            DayPilotCalendar.deleteShadow(DayPilotCalendar.resizingShadow);
            DayPilotCalendar.resizingShadow = null;
            DayPilotCalendar.resizing.style.cursor = 'default';
            document.body.style.cursor = 'default';
            DayPilotCalendar.resizing = null;
            $f.root.eventResizeDispatch($f, $g, top, $h);
        } else if (DayPilotCalendar.moving) {
            if (!DayPilotCalendar.movingShadow) {
                DayPilotCalendar.moving = null;
                document.body.style.cursor = 'default';
                return;
            }
            ;var $i = DayPilotCalendar.moving.helper.oldColumn;
            var top = DayPilotCalendar.movingShadow.offsetTop;
            DayPilotCalendar.deleteShadow(DayPilotCalendar.movingShadow);
            var $f = DayPilotCalendar.moving.event;
            var $j = DayPilotCalendar.movingShadow.column;
            DayPilotCalendar.moving = null;
            DayPilotCalendar.movingShadow = null;
            document.body.style.cursor = 'default';
            var ev = e || window.event;
            $f.root.eventMoveDispatch($f, $j, top, ev);
        }
    };
    DayPilotCalendar.deleteShadow = function ($k) {
        if (!$k) {
            return;
        }
        ;
        if (!$k.parentNode) {
            return;
        }
        ;$k.parentNode.removeChild($k);
    };
    DayPilotCalendar.createShadow = function ($l, $m) {
        var $n = $l.parentNode;
        while ($n && $n.tagName !== "TD") {
            $n = $n.parentNode;
        }
        ;var $k = document.createElement('div');
        $k.setAttribute('unselectable', 'on');
        $k.style.position = 'absolute';
        $k.style.width = ($l.offsetWidth - 4) + 'px';
        $k.style.height = ($l.offsetHeight - 4) + 'px';
        $k.style.left = ($l.offsetLeft) + 'px';
        $k.style.top = ($l.offsetTop) + 'px';
        $k.style.border = '2px dotted #666666';
        $k.style.zIndex = 101;
        $k.style.backgroundColor = "#aaaaaa";
        $k.style.opacity = 0.5;
        $k.style.filter = "alpha(opacity=50)";
        $k.style.border = '2px solid #aaaaaa';
        if ($m) {
            $k.style.overflow = 'hidden';
            $k.style.fontSize = $l.style.fontSize;
            $k.style.fontFamily = $l.style.fontFamily;
            $k.style.color = $l.style.color;
            $k.innerHTML = $l.data.InnerHTML;
        }
        ;$k.style.MozBorderRadius = "5px";
        $k.style.webkitBorderRadius = "5px";
        $k.style.borderRadius = "5px";
        $n.firstChild.appendChild($k);
        return $k;
    };
    DayPilotCalendar.moveShadow = function ($o) {
        var $k = DayPilotCalendar.movingShadow;
        var parent = $k.parentNode;
        parent.style.display = 'none';
        $k.parentNode.removeChild($k);
        $o.firstChild.appendChild($k);
        $k.style.left = '0px';
        parent.style.display = '';
        $k.style.width = (DayPilotCalendar.movingShadow.parentNode.offsetWidth + 1) + 'px';
    };
    DayPilotCalendar.Calendar = function (id) {
        var $p = false;
        if (this instanceof DayPilotCalendar.Calendar && !this.$1r) {
            $p = true;
            this.$1r = true;
        }
        ;
        if (!$p) {
            throw "DayPilot.Calendar() is a constructor and must be called as 'var c = new DayPilot.Calendar(id);'";
        }
        ;var $b = this;
        this.uniqueID = null;
        this.id = id;
        this.clientName = id;
        this.cache = {};
        this.cache.pixels = {};
        this.elements = {};
        this.elements.events = [];
        this.nav = {};
        this.afterRender = function () {
        };
        this.fasterDispose = true;
        this.borderColor = "#CED2CE";
        this.businessBeginsHour = 9;
        this.businessEndsHour = 18;
        this.cellBackColor = "#ffffff";
        this.cellBorderColor = "#DEDFDE";
        this.cellHeight = 20;
        this.columnMarginRight = 5;
        this.cornerBackColor = "#F3F3F9";
        this.days = 1;
        this.eventBackColor = '#638EDE';
        this.eventBorderColor = "#2951A5";
        this.eventFontFamily = 'Tahoma, Arial, Helvetica, sans-serif';
        this.eventFontSize = '8pt';
        this.eventFontColor = "#ffffff";
        this.eventHeaderFontSize = '8pt';
        this.eventHeaderFontFamily = 'Tahoma, Arial, Helvetica, sans-serif';
        this.eventHeaderFontColor = "#ffffff";
        this.eventHeaderHeight = 14;
        this.eventHeaderVisible = true;
        this.headerFontSize = '10pt';
        this.headerFontFamily = 'Tahoma, Arial, Helvetica, sans-serif';
        this.headerFontColor = "#42658C";
        this.headerHeight = 21;
        this.height = 300;
        this.heightSpec = 'BusinessHours';
        this.hourHalfBorderColor = "#EBEDEB";
        this.hourBorderColor = "#DEDFDE";
        this.hourFontColor = "#42658C";
        this.hourFontFamily = "Tahoma, Arial, Helvetica, sans-serif";
        this.hourFontSize = "16pt";
        this.hourNameBackColor = "#F3F3F9";
        this.hourNameBorderColor = "#DEDFDE";
        this.hourWidth = 45;
        this.initScrollPos = 0;
        this.loadingLabelText = "Loading...";
        this.loadingLabelVisible = true;
        this.loadingLabelBackColor = "ff0000";
        this.loadingLabelFontColor = "#ffffff";
        this.loadingLabelFontFamily = "Tahoma, Arial, Helvetica, sans-serif";
        this.loadingLabelFontSize = "10pt";
        this.selectedColor = "#316AC5";
        this.showToolTip = true;
        this.startDate = new DayPilot.Date().getDatePart();
        this.timeFormat = 'Clock12Hours';
        this.timeRangeSelectedHandling = 'Disabled';
        this.eventClickHandling = 'Disabled';
        this.eventResizeHandling = 'Disabled';
        this.eventMoveHandling = 'Disabled';
        this.onTimeRangeSelected = function ($q, end) {
            alert($q + '\n' + end);
        };
        this.onEventClick = function (e) {
            alert(e);
        };
        this.onEventResize = function (e) {
            alert(e);
        };
        this.onEventMove = function (e) {
            alert(e);
        };
        this.clearSelection = function () {
            for (var j = 0; j < DayPilotCalendar.selectedCells.length; j++) {
                var $c = DayPilotCalendar.selectedCells[j];
                if ($c) {
                    if ($c.selected) {
                        $c.removeChild($c.selected);
                        $c.firstChild.style.display = '';
                        $c.selected = null;
                    }
                }
            }
        };
        this.ie = (navigator && navigator.userAgent && navigator.userAgent.indexOf("MSIE") !== -1);
        this.ff = (navigator && navigator.userAgent && navigator.userAgent.indexOf("Firefox") !== -1);
        this.opera105 = (function () {
            if (/Opera[\/\s](\d+\.\d+)/.test(navigator.userAgent)) {
                var v = new Number(RegExp.$1);
                return v >= 10.5;
            }
            ;
            return false;
        })();
        this.webkit522 = (function () {
            if (/AppleWebKit[\/\s](\d+\.\d+)/.test(navigator.userAgent)) {
                var v = new Number(RegExp.$1);
                return v >= 522;
            }
            ;
            return false;
        })();
        this.ie9 = (navigator && navigator.userAgent && navigator.userAgent.indexOf("MSIE 9") !== -1);
        this.cleanSelection = this.clearSelection;
        this.callBack2 = function ($r, $s, $t) {
            if (this.callbackTimeout) {
                window.clearTimeout(this.callbackTimeout);
            }
            ;this.callbackTimeout = window.setTimeout(function () {
                $b.loadingStart();
            }, 100);
            var $u = {};
            $u.action = $r;
            $u.parameters = $t;
            $u.data = $s;
            $u.header = this.getCallBackHeader();
            var $v = "JSON" + DayPilot.JSON.stringify($u);
            if (this.backendUrl) {
                DayPilot.request(this.backendUrl, this.callBackResponse, $v, this.ajaxError);
            }
        };
        this.dispose = function () {
            var c = $b;
            c.deleteEvents();
            c.nav.zoom.onmousemove = null;
            c.nav.scroll.root = null;
            DayPilot.pu(c.nav.loading);
            c.disposeMain();
            c.disposeHeader();
            c.nav.select = null;
            c.nav.cornerRight = null;
            c.nav.scrollable = null;
            c.nav.zoom = null;
            c.nav.loading = null;
            c.nav.header = null;
            c.nav.hourTable = null;
            c.nav.scrolltop = null;
            c.nav.scroll = null;
            c.nav.main = null;
            c.nav.message = null;
            c.nav.messageClose = null;
            c.nav.top = null;
            DayPilotCalendar.unregister(c);
        };
        this.registerDispose = function () {
            var $w = document.getElementById(id);
            $w.dispose = this.dispose;
        };
        this.callBackResponse = function ($x) {
            $b.updateView($x.responseText);
        };
        this.getCallBackHeader = function () {
            var h = {};
            h.control = "dpc";
            h.id = this.id;
            h.days = $b.days;
            h.startDate = $b.startDate;
            h.heightSpec = $b.heightSpec;
            h.businessBeginsHour = $b.businessBeginsHour;
            h.businessEndsHour = $b.businessEndsHour;
            h.backColor = $b.cellBackColor;
            h.timeFormat = $b.timeFormat;
            h.viewType = $b.viewType;
            h.locale = $b.locale;
            return h;
        };
        this.updateView = function ($y, $z) {
            var $y = eval("(" + $y + ")");
            if ($y.UpdateType === "None") {
                $b.loadingStop();
                return;
            }
            ;$b.deleteEvents();
            if ($y.UpdateType === "Full") {
                $b.columns = $y.Columns;
                $b.days = $y.Days;
                $b.startDate = new DayPilot.Date($y.StartDate);
                $b.heightSpec = $y.HeightSpec ? $y.HeightSpec : $b.heightSpec;
                $b.businessBeginsHour = $y.BusinessBeginsHour ? $y.BusinessBeginsHour : $b.businessBeginsHour;
                $b.businessEndsHour = $y.BusinessEndsHour ? $y.BusinessEndsHour : $b.businessEndsHour;
                $b.viewType = $y.ViewType;
                $b.backColor = $y.BackColor ? $y.BackColor : $b.backColor;
                $b.eventHeaderVisible = $y.EventHeaderVisible ? $y.EventHeaderVisible : $b.eventHeaderVisible;
                $b.timeFormat = $y.TimeFormat ? $y.TimeFormat : $b.timeFormat;
                $b.locale = $y.Locale ? $y.Locale : $b.locale;
                $b.prepareColumns();
            }
            ;$b.loadEvents($y.Events);
            $b.updateHeaderHeight();
            if ($y.UpdateType === "Full") {
                $b.drawHeader();
                $b.drawMain();
                $b.drawHourTable();
                $b.updateHeight();
            }
            ;$b.drawEvents();
            $b.clearSelection();
            $b.afterRender($y.CallBackData, true);
            $b.loadingStop();
        };
        this.$ = function ($A) {
            return document.getElementById(id + "_" + $A);
        };
        this.durationHours = function () {
            return 24;
        };
        this.businessHoursSpan = function () {
            if (this.businessBeginsHour > this.businessEndsHour) {
                return 24 - this.businessBeginsHour + this.businessEndsHour;
            } else {
                return this.businessEndsHour - this.businessBeginsHour;
            }
        };
        this.rowCount = function () {
            return 48;
        };
        this.eventClickCallBack = function (e, $s) {
            this.callBack2('EventClick', $s, e);
        };
        this.eventClickDispatch = function (e) {
            var $B = this;
            var e = $B.event;
            switch ($b.eventClickHandling) {
                case 'CallBack':
                    $b.eventClickCallBack(e);
                    break;
                case 'JavaScript':
                    $b.onEventClick(e);
                    break;
            }
        };
        this.eventResizeCallBack = function (e, $C, $D, $s) {
            if (!$C) throw 'newStart is null';
            if (!$D) throw 'newEnd is null';
            var $E = {};
            $E.e = e;
            $E.newStart = $C;
            $E.newEnd = $D;
            this.callBack2('EventResize', $s, $E);
        };
        this.eventResizeDispatch = function (e, $F, $G, $h) {
            var $H = 1;
            var $C = new Date();
            var $D = new Date();
            var $q = e.start();
            var end = e.end();
            var $I = new Date();
            var $J = $b.columns[e.div.data.DayIndex].Start;
            $I = end.getDatePart();
            var $K = Math.floor(($G + $F - $H) / $b.cellHeight);
            var $L = $K * 30;
            var ts = $L * 60 * 1000;
            $C = $q;
            $D = $J.addTime(ts);
            switch ($b.eventResizeHandling) {
                case 'CallBack':
                    $b.eventResizeCallBack(e, $C, $D);
                    break;
                case 'JavaScript':
                    $b.onEventResize(e, $C, $D);
                    break;
            }
        };
        this.eventMoveCallBack = function (e, $C, $D, $M, $s) {
            if (!$C) throw 'newStart is null';
            if (!$D) throw 'newEnd is null';
            var $E = {};
            $E.e = e;
            $E.newStart = $C;
            $E.newEnd = $D;
            $E.newResource = $M;
            this.callBack2('EventMove', $s, $E);
        };
        this.eventMoveDispatch = function (e, $j, $G, ev) {
            var $H = 1;
            var $K = Math.floor(($G - $H) / $b.cellHeight);
            var $N = $K * 30 * 60 * 1000;
            var $q = e.start();
            var end = e.end();
            var $I = new Date();
            if ($q.isDayPilotDate) {
                $q = $q.d;
            }
            ;$I.setTime(Date.UTC($q.getUTCFullYear(), $q.getUTCMonth(), $q.getUTCDate()));
            var $O = $q.getTime() - ($I.getTime() + $q.getUTCHours() * 3600 * 1000 + Math.floor($q.getUTCMinutes() / 30) * 30 * 60 * 1000);
            var length = end.getTime() - $q.getTime();
            var $P = this.columns[$j];
            var $Q = $P.Start.getTime();
            var $R = new Date();
            $R.setTime($Q + $N + $O);
            var $C = new DayPilot.Date($R);
            var $D = $C.addTime(length);
            switch ($b.eventMoveHandling) {
                case 'CallBack':
                    $b.eventMoveCallBack(e, $C, $D, $P.Value);
                    break;
                case 'JavaScript':
                    $b.onEventMove(e, $C, $D, $P.Value, false);
                    break;
            }
        };
        this.timeRangeSelectedCallBack = function ($q, end, $S, $s) {
            var $T = {};
            $T.start = $q;
            $T.end = end;
            $T.resource = $S;
            this.callBack2('TimeRangeSelected', $s, $T);
        };
        this.timeRangeSelectedDispatch = function ($q, end, $o) {
            if (!$q.isDayPilotDate) {
                $q = new DayPilot.Date($q);
            }
            ;
            if (!end.isDayPilotDate) {
                end = new DayPilot.Date(end);
            }
            ;
            switch ($b.timeRangeSelectedHandling) {
                case 'CallBack':
                    $b.timeRangeSelectedCallBack($q, end, $o);
                    break;
                case 'JavaScript':
                    $b.onTimeRangeSelected($q, end, $o);
                    break;
            }
        };
        this.onCellMousedown = function (ev) {
            if (DayPilotCalendar.selecting) {
                return;
            }
            ;
            if ($b.timeRangeSelectedHandling === "Disabled") {
                return;
            }
            ;var $U = (window.event) ? window.event.button : ev.which;
            if ($U !== 1 && $U !== 0) {
                return;
            }
            ;DayPilotCalendar.firstMousePos = DayPilot.mc(ev || window.event);
            DayPilotCalendar.selecting = true;
            if (DayPilotCalendar.selectedCells) {
                $b.clearSelection();
                DayPilotCalendar.selectedCells = [];
            }
            ;DayPilotCalendar.column = DayPilotCalendar.getColumn(this);
            DayPilotCalendar.selectedCells.push(this);
            DayPilotCalendar.firstSelected = this;
            DayPilotCalendar.topSelectedCell = this;
            DayPilotCalendar.bottomSelectedCell = this;
            $b.activateSelection();
        };
        this.activateSelection = function () {
            var $V = this.getSelection();
            for (var j = 0; j < DayPilotCalendar.selectedCells.length; j++) {
                var $c = DayPilotCalendar.selectedCells[j];
                if ($c && !$c.selected) {
                    var $W = document.createElement("div");
                    $W.style.height = ($b.cellHeight - 1) + "px";
                    $W.style.backgroundColor = $b.selectedColor;
                    $c.firstChild.style.display = "none";
                    $c.insertBefore($W, $c.firstChild);
                    $c.selected = $W;
                }
            }
        };
        this.mousemove = function (ev) {
            if (typeof (DayPilotCalendar) === 'undefined') {
                return;
            }
            ;
            if (!DayPilotCalendar.selecting) {
                return;
            }
            ;var $X = DayPilot.mc(ev || window.event);
            var $Y = DayPilotCalendar.getColumn(this);
            if ($Y !== DayPilotCalendar.column) {
                return;
            }
            ;$b.clearSelection();
            if ($X.y < DayPilotCalendar.firstMousePos.y) {
                DayPilotCalendar.selectedCells = DayPilotCalendar.getCellsBelow(this);
                DayPilotCalendar.topSelectedCell = DayPilotCalendar.selectedCells[0];
                DayPilotCalendar.bottomSelectedCell = DayPilotCalendar.firstSelected;
            } else {
                DayPilotCalendar.selectedCells = DayPilotCalendar.getCellsAbove(this);
                DayPilotCalendar.topSelectedCell = DayPilotCalendar.firstSelected;
                DayPilotCalendar.bottomSelectedCell = DayPilotCalendar.selectedCells[0];
            }
            ;$b.activateSelection();
        };
        this.getSelection = function () {
            var $q = DayPilotCalendar.topSelectedCell.start;
            var end = DayPilotCalendar.bottomSelectedCell.end;
            var $Z = DayPilotCalendar.topSelectedCell.resource;
            return new DayPilot.Selection($q, end, $Z, $b);
        };
        this.mouseup = function (ev) {
            if (DayPilotCalendar.selecting && DayPilotCalendar.topSelectedCell !== null) {
                DayPilotCalendar.selecting = false;
                var $00 = $b.getSelection();
                $b.timeRangeSelectedDispatch($00.start, $00.end, $00.resource);
                if ($b.timeRangeSelectedHandling !== "Hold" && $b.timeRangeSelectedHandling !== "HoldForever") {
                    $a();
                }
            } else {
                DayPilotCalendar.selecting = false;
            }
        };
        this.prepareColumns = function () {
            if (!this.columns) {
                this.createDaysViewColumns();
            }
            ;
            for (var i = 0; i < this.columns.length; i++) {
                this.activateColumn(this.columns[i]);
            }
        };
        this.activateColumn = function ($o) {
            $o.Start = new DayPilot.Date($o.Start);
            $o.putIntoBlock = function (ep) {
                for (var i = 0; i < this.blocks.length; i++) {
                    var $01 = this.blocks[i];
                    if ($01.overlapsWith(ep.Top, ep.Height)) {
                        $01.events.push(ep);
                        $01.min = Math.min($01.min, ep.Top);
                        $01.max = Math.max($01.max, ep.Top + ep.Height);
                        return i;
                    }
                }
                ;var $01 = [];
                $01.lines = [];
                $01.events = [];
                $01.overlapsWith = function ($q, $02) {
                    var end = $q + $02 - 1;
                    if (!(end < this.min || $q > this.max - 1)) {
                        return true;
                    }
                    ;
                    return false;
                };
                $01.putIntoLine = function (ep) {
                    var $03 = this;
                    for (var i = 0; i < this.lines.length; i++) {
                        var $04 = this.lines[i];
                        if ($04.isFree(ep.Top, ep.Height)) {
                            $04.push(ep);
                            return i;
                        }
                    }
                    ;var $04 = [];
                    $04.isFree = function ($q, $02) {
                        var end = $q + $02 - 1;
                        var $05 = this.length;
                        for (var i = 0; i < $05; i++) {
                            var e = this[i];
                            if (!(end < e.Top || $q > e.Top + e.Height - 1)) {
                                return false;
                            }
                        }
                        ;
                        return true;
                    };
                    $04.push(ep);
                    this.lines.push($04);
                    return this.lines.length - 1;
                };
                $01.events.push(ep);
                $01.min = ep.Top;
                $01.max = ep.Top + ep.Height;
                this.blocks.push($01);
                return this.blocks.length - 1;
            };
            $o.putIntoLine = function (ep) {
                var $03 = this;
                for (var i = 0; i < this.lines.length; i++) {
                    var $04 = this.lines[i];
                    if ($04.isFree(ep.Top, ep.Height)) {
                        $04.push(ep);
                        return i;
                    }
                }
                ;var $04 = [];
                $04.isFree = function ($q, $02) {
                    var end = $q + $02 - 1;
                    var $05 = this.length;
                    for (var i = 0; i < $05; i++) {
                        var e = this[i];
                        if (!(end < e.Top || $q > e.Top + e.Height - 1)) {
                            return false;
                        }
                    }
                    ;
                    return true;
                };
                $04.push(ep);
                this.lines.push($04);
                return this.lines.length - 1;
            };
        };
        this.createDaysViewColumns = function () {
            this.columns = [];
            var $q = this.startDate.getDatePart();
            for (var i = 0; i < this.days; i++) {
                var $o = {};
                $o.Start = $q.addDays(i);
                $o.Name = $o.Start.toString();
                $o.InnerHTML = DayPilot.Date.toLocal($o.Start.d).toLocaleString();
                this.columns.push($o);
            }
        };
        this.deleteEvents = function () {
            if (this.elements.events) {
                for (var i = 0; i < this.elements.events.length; i++) {
                    var $W = this.elements.events[i];
                    var $l = $W.event;
                    if ($l) {
                        $l.div = null;
                        $l.root = null;
                    }
                    ;$W.onclick = null;
                    $W.onclickSave = null;
                    $W.onmouseover = null;
                    $W.onmouseout = null;
                    $W.onmousemove = null;
                    $W.onmousedown = null;
                    if ($W.firstChild && $W.firstChild.firstChild && $W.firstChild.firstChild.tagName && $W.firstChild.firstChild.tagName.toUpperCase() === 'IMG') {
                        var $06 = $W.firstChild.firstChild;
                        $06.onmousedown = null;
                        $06.onmousemove = null;
                        $06.onclick = null;
                    }
                    ;$W.helper = null;
                    $W.data = null;
                    $W.event = null;
                    DayPilot.de($W);
                }
            }
            ;this.elements.events = [];
        };
        this.drawEvent = function ($s) {
            var $07 = this.nav.main;
            var $08 = true;
            var $09 = true;
            var $0a = false;
            var $0b = this.eventBorderColor;
            var $W = document.createElement("div");
            $W.data = $s;
            $W.unselectable = 'on';
            $W.style.MozUserSelect = 'none';
            $W.style.KhtmlUserSelect = 'none';
            $W.style.position = 'absolute';
            $W.style.fontFamily = this.eventFontFamily;
            $W.style.fontSize = this.eventFontSize;
            $W.style.color = this.eventFontColor;
            $W.style.left = $s.Left + '%';
            $W.style.top = ($s.Top) + 'px';
            $W.style.width = $s.Width + '%';
            $W.style.height = Math.max($s.Height, 2) + 'px';
            if (!$08) {
                $W.style.backgroundColor = $0b;
            }
            ;$W.style.overflow = 'hidden';
            $W.isFirst = $s.PartStart.getTime() === $s.Start.getTime();
            $W.isLast = $s.PartEnd.getTime() === $s.End.getTime();
            $W.onclick = this.eventClickDispatch;
            var $0c = [];
            $0c.push("<div");
            if (this.showToolTip) {
                $0c.push(" title='");
                $0c.push($s.ToolTip.replace(/'/g, "&apos;"));
                $0c.push("'");
            }
            ;var $g = Math.max($s.Height - 2, 0);
            $0c.push(" class='");
            $0c.push("'");
            if ($0a) {
                $0c.push(" style='margin-top:1px;height:");
                $0c.push($g - 2);
            } else {
                $0c.push(" style='margin-top:0px;height:");
                $0c.push($g);
            }
            ;$0c.push("px;background-color:");
            $0c.push(this.eventBackColor);
            if ($09) {
                $0c.push(";border:1px solid ");
                $0c.push($0b);
                $0c.push(";-moz-border-radius:5px;");
                $0c.push(";-webkit-border-radius:5px;");
                $0c.push(";border-radius:5px;");
            } else {
                $0c.push(";border-left:1px solid ");
                $0c.push($0b);
                $0c.push(";border-right:1px solid ");
                $0c.push($0b);
            }
            ;$0c.push(";");
            $0c.push("' unselectable='on'>");
            var $0d = this.eventHeaderVisible ? this.eventHeaderHeight : 0;
            if (this.eventHeaderVisible) {
                var $0e = "";
                var $0f = $s.Start.getHours();
                var am = $0f < 12;
                var $0g = $s.Start.getMinutes();
                if (this.timeFormat === "Clock12Hours") {
                    $0f = $0f % 12;
                    if ($0f === 0) {
                        $0f = 12;
                    }
                    ;$0e = am ? "am" : "pm";
                }
                ;
                if ($0g < 10) {
                    $0g = "0" + $0g;
                }
                ;var $0h = $0f + ":" + $0g + $0e;
                $0c.push("<div unselectable='on' style='overflow:hidden;height:");
                $0c.push(this.eventHeaderHeight);
                $0c.push("px; background-color:");
                $0c.push($0b);
                $0c.push(";font-size:");
                $0c.push(this.eventHeaderFontSize);
                $0c.push(";color:");
                $0c.push(this.eventHeaderFontColor);
                $0c.push("'>");
                $0c.push($0h);
                $0c.push("</div>");
            }
            ;$0c.push("<div unselectable='on' style='overflow:hidden;padding-left:2px;height:");
            $0c.push($g - $0d - 1);
            $0c.push("px;'>");
            $0c.push($s.InnerHTML);
            $0c.push("</div></div>");
            $W.innerHTML = $0c.join('');
            if ($07.rows[0].cells[$s.DayIndex]) {
                var $0i = $07.rows[0].cells[$s.DayIndex].firstChild;
                $0i.appendChild($W);
                $b.makeChildrenUnselectable($W);
                var e = new DayPilotCalendar.Event($W, $b);
            }
            ;$b.elements.events.push($W);
        };
        this.makeChildrenUnselectable = function (el) {
            var c = (el && el.childNodes) ? el.childNodes.length : 0;
            for (var i = 0; i < c; i++) {
                try {
                    var $0j = el.childNodes[i];
                    if ($0j.nodeType === 1) {
                        $0j.unselectable = 'on';
                        this.makeChildrenUnselectable($0j);
                    }
                } catch (e) {
                }
            }
        };
        this.drawEvents = function () {
            var $q = new Date();
            for (var i = 0; i < this.columns.length; i++) {
                var $0k = this.columns[i];
                for (var m = 0; m < $0k.blocks.length; m++) {
                    var $01 = $0k.blocks[m];
                    for (var j = 0; j < $01.lines.length; j++) {
                        var $04 = $01.lines[j];
                        for (var k = 0; k < $04.length; k++) {
                            var e = $04[k];
                            e.Width = 100 / $01.lines.length;
                            e.Left = e.Width * j;
                            var $0l = (j === $01.lines.length - 1);
                            if (!$0l) {
                                e.Width = e.Width * 1.5;
                            }
                            ;this.drawEvent(e);
                        }
                    }
                }
            }
            ;var end = new Date();
            var $0m = end.getTime() - $q.getTime();
        };
        this.drawTop = function () {
            this.nav.top = document.getElementById(this.id);
            this.nav.top.innerHTML = '';
            this.nav.top.style.MozUserSelect = 'none';
            this.nav.top.style.KhtmlUserSelect = 'none';
            this.nav.top.style.position = 'relative';
            this.nav.top.style.width = this.width ? this.width : '100%';
            this.nav.top.style.lineHeight = "1.2";
            this.nav.top.style.textAlign = "left";
            if (this.heightSpec === "Parent100Pct") {
                this.nav.top.style.height = "100%";
            }
            ;this.nav.scroll = document.createElement("div");
            this.nav.scroll.style.height = this.getScrollableHeight() + "px";
            if (this.heightSpec === 'BusinessHours') {
                this.nav.scroll.style.overflow = "auto";
            } else {
                this.nav.scroll.style.overflow = "hidden";
            }
            ;this.nav.scroll.style.position = "relative";
            this.nav.scroll.style.border = "1px solid " + this.borderColor;
            this.nav.scroll.style.backgroundColor = this.hourNameBackColor;
            var $0n = this.drawTopHeaderDiv();
            this.nav.top.appendChild($0n);
            this.nav.scroll.style.zoom = 1;
            var $0o = this.drawScrollable();
            this.nav.scrollable = $0o.firstChild;
            this.nav.scroll.appendChild($0o);
            this.nav.top.appendChild(this.nav.scroll);
            this.nav.scrollLayer = document.createElement("div");
            this.nav.scrollLayer.style.position = 'absolute';
            this.nav.scrollLayer.style.top = '0px';
            this.nav.scrollLayer.style.left = '0px';
            this.nav.top.appendChild(this.nav.scrollLayer);
            this.nav.loading = document.createElement("div");
            this.nav.loading.style.position = 'absolute';
            this.nav.loading.style.top = '0px';
            this.nav.loading.style.left = (this.hourWidth + 5) + "px";
            this.nav.loading.style.backgroundColor = this.loadingLabelBackColor;
            this.nav.loading.style.fontSize = this.loadingLabelFontSize;
            this.nav.loading.style.fontFamily = this.loadingLabelFontFamily;
            this.nav.loading.style.color = this.loadingLabelFontColor;
            this.nav.loading.style.padding = '2px';
            this.nav.loading.innerHTML = this.loadingLabelText;
            this.nav.loading.style.display = 'none';
            this.nav.top.appendChild(this.nav.loading);
        };
        this.drawHourTable = function () {
            if (!this.fasterDispose) DayPilot.pu(this.nav.hourTable);
            this.nav.scrollable.rows[0].cells[0].innerHTML = '';
            this.nav.hourTable = this.createHourTable();
            this.nav.scrollable.rows[0].cells[0].appendChild(this.nav.hourTable);
        };
        this.drawScrollable = function () {
            var $0p = document.createElement("div");
            $0p.style.zoom = 1;
            $0p.style.position = 'relative';
            var $0q = document.createElement("table");
            $0q.cellSpacing = "0";
            $0q.cellPadding = "0";
            $0q.border = "0";
            $0q.style.border = "0px none";
            $0q.style.width = "100%";
            $0q.style.position = 'absolute';
            var r = $0q.insertRow(-1);
            var c;
            c = r.insertCell(-1);
            c.valign = "top";
            c.style.padding = '0px';
            c.style.border = '0px none';
            this.nav.hourTable = this.createHourTable();
            c.appendChild(this.nav.hourTable);
            c = r.insertCell(-1);
            c.valign = "top";
            c.width = "100%";
            c.style.padding = '0px';
            c.style.border = '0px none';
            c.appendChild(this.createEventsAndCells());
            $0p.appendChild($0q);
            this.nav.zoom = $0p;
            return $0p;
        };
        this.createEventsAndCells = function () {
            var $0q = document.createElement("table");
            $0q.cellPadding = "0";
            $0q.cellSpacing = "0";
            $0q.border = "0";
            $0q.style.width = "100%";
            $0q.style.border = "0px none";
            $0q.style.borderLeft = "1px solid " + this.borderColor;
            this.nav.main = $0q;
            return $0q;
        };
        this.createHourTable = function () {
            var $0q = document.createElement("table");
            $0q.cellSpacing = "0";
            $0q.cellPadding = "0";
            $0q.border = "0";
            $0q.style.border = '0px none';
            $0q.style.width = this.hourWidth + "px";
            $0q.oncontextmenu = function () {
                return false;
            };
            var r = $0q.insertRow(-1);
            r.style.height = "1px";
            r.style.backgroundColor = "white";
            var c = r.insertCell(-1);
            c.style.padding = '0px';
            c.style.border = '0px none';
            var $0r = 24;
            for (var i = 0; i < $0r; i++) {
                this.createHourRow($0q, i);
            }
            ;
            return $0q;
        };
        this.createHourRow = function ($0q, i) {
            var $g = (this.cellHeight * 2);
            var r = $0q.insertRow(-1);
            r.style.height = $g + "px";
            var c = r.insertCell(-1);
            c.valign = "bottom";
            c.unselectable = "on";
            c.style.backgroundColor = this.hourNameBackColor;
            c.style.cursor = "default";
            c.style.padding = '0px';
            c.style.border = '0px none';
            var $0s = document.createElement("div");
            $0s.style.width = this.hourWidth + "px";
            $0s.style.height = ($g) + "px";
            $0s.style.overflow = 'hidden';
            $0s.unselectable = 'on';
            var $01 = document.createElement("div");
            $01.style.display = "block";
            $01.style.borderBottom = "1px solid " + this.hourNameBorderColor;
            $01.style.height = ($g - 1) + "px";
            $01.style.textAlign = "right";
            $01.unselectable = "on";
            var $0h = document.createElement("div");
            $0h.style.padding = "2px";
            $0h.style.fontFamily = this.hourFontFamily;
            $0h.style.fontSize = this.hourFontSize;
            $0h.style.color = this.hourFontColor;
            $0h.unselectable = "on";
            var $q = this.startDate.addHours(i);
            var $0f = $q.getHours();
            var am = $0f < 12;
            if (this.timeFormat === "Clock12Hours") {
                $0f = $0f % 12;
                if ($0f === 0) {
                    $0f = 12;
                }
            }
            ;$0h.innerHTML = $0f;
            var $0t = document.createElement("span");
            $0t.style.fontSize = "10px";
            $0t.style.verticalAlign = "super";
            $0t.unselectable = "on";
            var $0u;
            if (this.timeFormat === "Clock12Hours") {
                if (am) {
                    $0u = "AM";
                } else {
                    $0u = "PM";
                }
            } else {
                $0u = "00";
            }
            ;$0t.innerHTML = "&nbsp;" + $0u;
            $0h.appendChild($0t);
            $01.appendChild($0h);
            $0s.appendChild($01);
            c.appendChild($0s);
        };
        this.getScrollableHeight = function () {
            switch (this.heightSpec) {
                case "Full":
                    return (24 * 2 * this.cellHeight);
                case "BusinessHours":
                    var $0v = this.businessHoursSpan();
                    return $0v * this.cellHeight * 2;
                default:
                    throw "DayPilot.Calendar: Unexpected 'heightSpec' value.";
            }
        };
        this.drawTopHeaderDiv = function () {
            var $0n = document.createElement("div");
            $0n.style.borderLeft = "1px solid " + this.borderColor;
            $0n.style.borderRight = "1px solid " + this.borderColor;
            $0n.style.overflow = "auto";
            var $0q = document.createElement("table");
            $0q.cellPadding = "0";
            $0q.cellSpacing = "0";
            $0q.border = "0";
            $0q.style.width = "100%";
            $0q.style.borderCollapse = 'separate';
            $0q.style.border = "0px none";
            var r = $0q.insertRow(-1);
            var c = r.insertCell(-1);
            c.style.padding = '0px';
            c.style.border = '0px none';
            var $0w = this.drawCorner();
            c.appendChild($0w);
            this.nav.corner = $0w;
            c = r.insertCell(-1);
            c.style.width = "100%";
            c.style.backgroundColor = this.hourNameBackColor;
            c.valign = "top";
            c.style.position = 'relative';
            c.style.padding = '0px';
            c.style.border = '0px none';
            this.nav.header = document.createElement("table");
            this.nav.header.cellPadding = "0";
            this.nav.header.cellSpacing = "0";
            this.nav.header.border = "0";
            this.nav.header.width = "100%";
            this.nav.header.style.borderBottom = "0px none #000000";
            this.nav.header.style.borderRight = "0px none #000000";
            this.nav.header.style.borderLeft = "1px solid " + this.borderColor;
            this.nav.header.style.borderTop = "1px solid " + this.borderColor;
            this.nav.header.oncontextmenu = function () {
                return false;
            };
            var $0x = this.nav.scroll.style.overflow !== 'hidden';
            if ($0x) {
                this.nav.header.style.borderRight = "1px solid " + this.borderColor;
            }
            ;c.appendChild(this.nav.header);
            if ($0x) {
                c = r.insertCell(-1);
                c.style.backgroundColor = this.hourNameBackColor;
                c.style.borderTop = "1px solid " + this.borderColor;
                c.style.borderBottom = "0px none";
                c.style.borderLeft = "0px none";
                c.style.borderRight = "0px none";
                c.style.padding = '0px';
                c.style.verticalAlign = 'top';
                c.unselectable = "on";
                c.innerHTML = "&nbsp;";
                var $0c = document.createElement("div");
                $0c.unselectable = "on";
                $0c.style.width = "16px";
                $0c.style.height = "1px";
                $0c.style.lineHeight = "1px";
                $0c.style.fontSize = "1px";
                c.appendChild($0c);
                this.nav.cornerRight = $0c;
            }
            ;$0n.appendChild($0q);
            return $0n;
        };
        this.drawCorner = function () {
            var $0o = document.createElement("div");
            $0o.style.position = 'relative';
            $0o.style.backgroundColor = this.hourNameBackColor;
            $0o.style.fontFamily = this.headerFontFamily;
            $0o.style.fontSize = this.headerFontSize;
            $0o.style.color = this.headerFontColor;
            $0o.style.width = this.hourWidth + "px";
            $0o.style.height = this.headerHeight + "px";
            $0o.style.borderTop = "1px solid " + this.borderColor;
            $0o.oncontextmenu = function () {
                return false;
            };
            var $0w = document.createElement("div");
            $0w.unselectable = "on";
            $0o.appendChild($0w);
            return $0o;
        };
        this.disposeMain = function () {
            var $0q = this.nav.main;
            $0q.root = null;
            $0q.onmouseup = null;
            for (var y = 0; y < $0q.rows.length; y++) {
                var r = $0q.rows[y];
                for (var x = 0; x < r.cells.length; x++) {
                    var c = r.cells[x];
                    c.root = null;
                    c.onmousedown = null;
                    c.onmousemove = null;
                    c.onmouseout = null;
                    c.onmouseup = null;
                }
            }
            ;
            if (!this.fasterDispose) DayPilot.pu($0q);
        };
        this.drawMain = function () {
            DayPilotCalendar.selectedCells = [];
            var $0y = [];
            var $0z = [];
            var $0q = this.nav.main;
            var $K = 30 * 60 * 1000;
            var $0A = this.rowCount();
            var $0B = $b.columns;
            var $0C = !this.tableCreated || $0B.length !== $0q.rows[0].cells.length || $0A !== $0q.rows.length;
            if ($0q) {
                this.disposeMain();
            }
            while ($0q && $0q.rows && $0q.rows.length > 0 && $0C) {
                if (!this.fasterDispose) DayPilot.pu($0q.rows[0]);
                $0q.deleteRow(0);
            }
            ;this.tableCreated = true;
            var r = ($0C) ? $0q.insertRow(-1) : $0q.rows[0];
            if ($0C) {
                r.style.backgroundColor = 'white';
            }
            ;var cl = $0B.length;
            for (var j = 0; j < cl; j++) {
                var c = ($0C) ? r.insertCell(-1) : r.cells[j];
                if ($0C) {
                    c.style.padding = '0px';
                    c.style.border = '0px none';
                    c.style.height = '1px';
                    c.style.overflow = 'visible';
                    if (!$b.rtl) {
                        c.style.textAlign = 'left';
                    }
                    ;c.style.width = (100.0 / $0B.length) + "%";
                    var $W = document.createElement("div");
                    $W.style.display = 'block';
                    $W.style.marginRight = $b.columnMarginRight + "px";
                    $W.style.position = 'relative';
                    $W.style.height = '1px';
                    $W.style.fontSize = '1px';
                    $W.style.lineHeight = '1.2';
                    $W.style.marginTop = '-1px';
                    c.appendChild($W);
                }
            }
            ;
            for (var i = 0; i < $0A; i++) {
                var r = ($0C) ? $0q.insertRow(-1) : $0q.rows[i + 1];
                if ($0C) {
                    r.style.MozUserSelect = 'none';
                    r.style.KhtmlUserSelect = 'none';
                }
                ;
                for (var j = 0; j < cl; j++) {
                    var $0k = this.columns[j];
                    var c = ($0C) ? r.insertCell(-1) : r.cells[j];
                    c.start = $0k.Start.addTime(i * $K);
                    c.end = c.start.addTime($K);
                    c.resource = $0k.Value;
                    if ($0C) {
                        c.root = this;
                        c.style.padding = '0px';
                        c.style.border = '0px none';
                        c.style.verticalAlign = 'top';
                        c.onmousedown = this.onCellMousedown;
                        c.onmousemove = this.mousemove;
                        c.onmouseup = function () {
                            return false;
                        };
                        c.onclick = function () {
                            return false;
                        };
                        if ((!$b.rtl && j !== cl - 1) || $b.rtl) {
                            c.style.borderRight = '1px solid ' + $b.cellBorderColor;
                        }
                        ;c.style.height = $b.cellHeight + 'px';
                        c.style.overflow = 'hidden';
                        c.unselectable = 'on';
                        var $W = document.createElement("div");
                        $W.unselectable = 'on';
                        $W.style.fontSize = '1px';
                        $W.style.height = '0px';
                        var $0D = (c.end.getMinutes() + c.end.getSeconds() + c.end.getMilliseconds()) > 0;
                        if ($0D) {
                            if ($b.hourHalfBorderColor !== '') {
                                $W.style.borderBottom = '1px solid ' + $b.hourHalfBorderColor;
                            }
                        } else {
                            if ($b.hourBorderColor !== '') {
                                $W.style.borderBottom = '1px solid ' + $b.hourBorderColor;
                            }
                        }
                        ;var $0E = document.createElement("div");
                        $0E.style.height = ($b.cellHeight - 1) + "px";
                        $0E.style.overflow = 'hidden';
                        $0E.unselectable = 'on';
                        c.appendChild($0E);
                        c.appendChild($W);
                    }
                    ;c.style.backgroundColor = $b.cellBackColor;
                    $0E = c.firstChild;
                }
            }
            ;$0q.onmouseup = this.mouseup;
            $0q.root = this;
            var scroll = $b.nav.scroll;
            $b.nav.scrollable.onmousemove = function (ev) {
                ev = ev || window.event;
                var $0F = $b.nav.scrollable;
                $b.coords = DayPilot.mo3($0F, ev);
                var $X = DayPilot.mc(ev);
                if (DayPilotCalendar.resizing) {
                    if (!DayPilotCalendar.resizingShadow) {
                        DayPilotCalendar.resizingShadow = DayPilotCalendar.createShadow(DayPilotCalendar.resizing, false, $b.shadow);
                    }
                    ;var $0G = DayPilotCalendar.resizing.event.root.cellHeight;
                    var $H = 1;
                    var $0H = ($X.y - DayPilotCalendar.originalMouse.y);
                    if (DayPilotCalendar.resizing.dpBorder === 'bottom') {
                        var $0I = Math.floor(((DayPilotCalendar.originalHeight + DayPilotCalendar.originalTop + $0H) + $0G / 2) / $0G) * $0G - DayPilotCalendar.originalTop + $H;
                        if ($0I < $0G) $0I = $0G;
                        var $05 = DayPilotCalendar.resizing.event.root.nav.main.clientHeight;
                        if (DayPilotCalendar.originalTop + $0I > $05) $0I = $05 - DayPilotCalendar.originalTop;
                        DayPilotCalendar.resizingShadow.style.height = ($0I - 4) + 'px';
                    }
                } else if (DayPilotCalendar.moving) {
                    if (!DayPilotCalendar.movingShadow) {
                        DayPilotCalendar.movingShadow = DayPilotCalendar.createShadow(DayPilotCalendar.moving, !$b.ie, $b.shadow);
                        DayPilotCalendar.movingShadow.style.width = (DayPilotCalendar.movingShadow.parentNode.offsetWidth + 1) + 'px';
                    }
                    ;
                    if (!$b.coords) {
                        return;
                    }
                    ;var $0G = $b.cellHeight;
                    var $H = 1;
                    var $0J = DayPilotCalendar.moveOffsetY;
                    if (!$0J) {
                        $0J = $0G / 2;
                    }
                    ;var $0K = Math.floor((($b.coords.y - $0J - $H) + $0G / 2) / $0G) * $0G + $H;
                    if ($0K < $H) {
                        $0K = $H;
                    }
                    ;var $07 = $b.nav.main;
                    var $05 = $07.clientHeight;
                    var $g = parseInt(DayPilotCalendar.movingShadow.style.height);
                    if ($0K + $g > $05) {
                        $0K = $05 - $g;
                    }
                    ;DayPilotCalendar.movingShadow.parentNode.style.display = 'none';
                    DayPilotCalendar.movingShadow.style.top = $0K + 'px';
                    DayPilotCalendar.movingShadow.parentNode.style.display = '';
                    var $0L = $07.clientWidth / $07.rows[0].cells.length;
                    var $o = Math.floor(($b.coords.x - 45) / $0L);
                    if ($o < 0) {
                        $o = 0;
                    }
                    ;
                    if ($o < $07.rows[0].cells.length && $o >= 0 && DayPilotCalendar.movingShadow.column !== $o) {
                        DayPilotCalendar.movingShadow.column = $o;
                        DayPilotCalendar.moveShadow($07.rows[0].cells[$o]);
                    }
                }
            };
            $b.nav.scrollable.style.display = '';
        };
        this.disposeHeader = function () {
            var $0q = this.nav.header;
            if ($0q && $0q.rows) {
                for (var y = 0; y < $0q.rows.length; y++) {
                    var r = $0q.rows[y];
                    for (var x = 0; x < r.cells.length; x++) {
                        var c = r.cells[x];
                        c.onclick = null;
                        c.onmousemove = null;
                        c.onmouseout = null;
                    }
                }
            }
            ;
            if (!this.fasterDispose) DayPilot.pu($0q);
        };
        this.drawHeaderRow = function ($0C) {
            var r = ($0C) ? this.nav.header.insertRow(-1) : this.nav.header.rows[0];
            var $0B = this.columns;
            var $0M = $0B.length;
            for (var i = 0; i < $0M; i++) {
                var $s = $0B[i];
                var $c = ($0C) ? r.insertCell(-1) : r.cells[i];
                $c.data = $s;
                $c.style.width = (100.0 / $0B.length) + "%";
                $c.style.overflow = 'hidden';
                $c.style.padding = '0px';
                $c.style.border = '0px none';
                $c.style.height = (this.headerHeight) + "px";
                var $W = ($0C) ? document.createElement("div") : $c.firstChild;
                if ($0C) {
                    $W.unselectable = 'on';
                    $W.style.MozUserSelect = 'none';
                    $W.style.backgroundColor = $s.BackColor;
                    $W.style.cursor = 'default';
                    $W.style.position = 'relative';
                    $W.style.fontFamily = this.headerFontFamily;
                    $W.style.fontSize = this.headerFontSize;
                    $W.style.color = this.headerFontColor;
                    if ($b.rtl) {
                        if (i === $0M - 1) {
                            $W.style.borderLeft = "1px solid " + $s.BackColor;
                        } else {
                            $W.style.borderLeft = "1px solid " + this.borderColor;
                        }
                    } else {
                        if (i === $0M - 1) {
                            $a();
                        } else {
                            $W.style.borderRight = "1px solid " + this.borderColor;
                        }
                    }
                    ;$W.style.height = this.headerHeight + "px";
                    var $0h = document.createElement("div");
                    $0h.style.position = 'absolute';
                    $0h.style.left = '0px';
                    $0h.style.width = '100%';
                    $0h.style.padding = "2px";
                    $W.style.textAlign = 'center';
                    $0h.unselectable = 'on';
                    $W.appendChild($0h);
                    $c.appendChild($W);
                }
                ;var $0h = $W.firstChild;
                $0h.innerHTML = $s.InnerHTML;
            }
        };
        this.widthUnit = function () {
            if (this.width && this.width.indexOf("px") != -1) {
                return "Pixel";
            }
            ;
            return "Percentage";
        };
        this.drawHeader = function () {
            var $0n = this.nav.header;
            var $0C = true;
            var $0B = this.columns;
            var $0M = $0B.length;
            if (this.headerCreated && $0n && $0n.rows && $0n.rows.length > 0) {
                $0C = $0n.rows[$0n.rows.length - 1].cells.length !== $0M;
            }
            while (this.headerCreated && $0n && $0n.rows && $0n.rows.length > 0 && $0C) {
                if (!this.fasterDispose) DayPilot.pu($0n.rows[0]);
                $0n.deleteRow(0);
            }
            ;this.headerCreated = true;
            if (!$0C) {
                var $0w = $b.nav.corner;
                $0w.style.backgroundColor = $b.cornerBackColor;
                if (!this.fasterDispose) DayPilot.pu($0w.firstChild);
            }
            ;this.drawHeaderRow($0C);
        };
        this.loadingStart = function () {
            if (this.loadingLabelVisible) {
                this.nav.loading.innerHTML = this.loadingLabelText;
                this.nav.loading.style.top = (this.headerHeight + 5) + "px";
                this.nav.loading.style.display = '';
            }
        };
        this.commandCallBack = function ($0N, $s) {
            var $E = {};
            $E.command = $0N;
            this.callBack2('Command', $s, $E);
        };
        this.loadingStop = function ($0O) {
            if (this.callbackTimeout) {
                window.clearTimeout(this.callbackTimeout);
            }
            ;this.nav.loading.style.display = 'none';
        };
        this.enableScrolling = function () {
            var $0P = this.nav.scroll;
            if (!this.initScrollPos) return;
            $0P.root = this;
            if ($0P.scrollTop === 0) {
                $0P.scrollTop = this.initScrollPos;
            }
        };
        this.callbackError = function ($y, $z) {
            alert("Error!\r\nResult: " + $y + "\r\nContext:" + $z);
        };
        this.fixScrollHeader = function () {
            var w = DayPilot.sw(this.nav.scroll);
            var d = this.nav.cornerRight;
            if (d && w > 0) {
                d.style.width = (w - 1) + 'px';
            }
        };
        this.registerGlobalHandlers = function () {
            if (!DayPilotCalendar.globalHandlers) {
                DayPilotCalendar.globalHandlers = true;
                DayPilot.re(document, 'mouseup', DayPilotCalendar.gMouseUp);
                DayPilot.re(window, 'unload', DayPilotCalendar.gUnload);
            }
        };
        this.loadEvents = function ($0Q) {
            if (!$0Q) {
                $0Q = this.events;
            }
            ;var length = $0Q.length;
            var $0R = 24 * 60 * 60 * 1000;
            this.cache.pixels = {};
            var $0S = [];
            this.scrollLabels = [];
            this.minStart = 10000;
            this.maxEnd = 0;
            if (!$0Q) {
                return;
            }
            ;
            for (var i = 0; i < length; i++) {
                var e = $0Q[i];
                e.Start = new DayPilot.Date(e.Start);
                e.End = new DayPilot.Date(e.End);
            }
            ;var $0T = this.startDate;
            var $0U = this.startDate.addDays(this.days);
            for (var i = 0; i < this.columns.length; i++) {
                var scroll = {};
                scroll.minEnd = 1000000;
                scroll.maxStart = -1;
                this.scrollLabels.push(scroll);
                var $0k = this.columns[i];
                $0k.events = [];
                $0k.lines = [];
                $0k.blocks = [];
                var $J = new DayPilot.Date($0k.Start);
                var $0V = $J.getTime();
                var $0W = $J.addTime($0R);
                var $0X = $0W.getTime();
                for (var j = 0; j < length; j++) {
                    if ($0S[j]) {
                        continue;
                    }
                    ;var e = $0Q[j];
                    var $q = e.Start;
                    var end = e.End;
                    var $0Y = $q.getTime();
                    var $0Z = end.getTime();
                    if ($0Z < $0Y) {
                        continue;
                    }
                    ;var $10 = !($0Z <= $0V || $0Y >= $0X);
                    if ($10) {
                        var ep = {};
                        ep.Text = e.Text;
                        ep.Value = e.Value;
                        ep.ToolTip = e.ToolTip ? e.ToolTip : e.Text;
                        ep.Start = $q;
                        ep.End = end;
                        ep.DayIndex = i;
                        ep.PartStart = $0V < $0Y ? ep.Start : $J;
                        ep.PartEnd = $0X > $0Z ? ep.End : $0W;
                        ep.InnerHTML = e.InnerHTML ? e.InnerHTML : e.Text;
                        var $11 = this.getPixels(ep.PartStart, $0k.Start);
                        var $12 = this.getPixels(ep.PartEnd, $0k.Start);
                        var top = $11.top;
                        var $13 = $12.top;
                        if (top === $13 && ($11.cut || $12.cut)) {
                            continue;
                        }
                        ;var $14 = $12.boxBottom;
                        ep.Top = Math.floor(top / this.cellHeight) * this.cellHeight + 1;
                        ep.Height = Math.max(Math.ceil($14 / this.cellHeight) * this.cellHeight - ep.Top, this.cellHeight - 1) + 1;
                        var $q = ep.Top;
                        var end = ep.Top + ep.Height;
                        if ($q > scroll.maxStart) {
                            scroll.maxStart = $q;
                        }
                        ;
                        if (end < scroll.minEnd) {
                            scroll.minEnd = end;
                        }
                        ;
                        if ($q < this.minStart) {
                            this.minStart = $q;
                        }
                        ;
                        if (end > this.maxEnd) {
                            this.maxEnd = end;
                        }
                        ;$0k.events.push(ep);
                        if (ep.PartStart.getTime() === $0Y && ep.PartEnd.getTime() === $0Z) {
                            $0S[j] = true;
                        }
                    }
                }
            }
            ;
            for (var i = 0; i < this.columns.length; i++) {
                var $0k = this.columns[i];
                $0k.events.sort(this.eventComparer);
                for (var j = 0; j < $0k.events.length; j++) {
                    var e = $0k.events[j];
                    $0k.putIntoBlock(e);
                }
                ;
                for (var j = 0; j < $0k.blocks.length; j++) {
                    var $01 = $0k.blocks[j];
                    $01.events.sort(this.eventComparer);
                    for (var k = 0; k < $01.events.length; k++) {
                        var e = $01.events[k];
                        $01.putIntoLine(e);
                    }
                }
            }
        };
        this.eventComparer = function (a, b) {
            if (!a || !b || !a.Start || !b.Start) {
                return 0;
            }
            ;var $15 = a.Start.getTime() - b.Start.getTime();
            if ($15 !== 0) {
                return $15;
            }
            ;var $16 = b.End.getTime() - a.End.getTime();
            return $16;
        };
        this.debug = function ($0O, $17) {
            if (!this.debuggingEnabled) {
                return;
            }
            ;
            if (!$b.debugMessages) {
                $b.debugMessages = [];
            }
            ;$b.debugMessages.push($0O);
            if (typeof console !== 'undefined') {
                console.log($0O);
            }
        };
        this.getPixels = function ($Q, $q) {
            if (!$q) $q = this.startDate;
            var $0Y = $q.getTime();
            var $18 = $Q.getTime();
            var $19 = this.cache.pixels[$18 + "_" + $0Y];
            if ($19) {
                return $19;
            }
            ;$0Y = $q.getTime();
            var $1a = 30 * 60 * 1000;
            var $1b = $18 - $0Y;
            var $1c = $1b % $1a;
            var $1d = $1b - $1c;
            var $1e = $1d + $1a;
            if ($1c === 0) {
                $1e = $1d;
            }
            ;var $y = {};
            $y.cut = false;
            $y.top = this.ticksToPixels($1b);
            $y.boxTop = this.ticksToPixels($1d);
            $y.boxBottom = this.ticksToPixels($1e);
            this.cache.pixels[$18 + "_" + $0Y] = $y;
            return $y;
        };
        this.ticksToPixels = function ($18) {
            return Math.floor((this.cellHeight * $18) / (1000 * 60 * 30));
        };
        this.prepareVariables = function () {
            this.startDate = new DayPilot.Date(this.startDate);
        };
        this.updateHeaderHeight = function () {
            if (this.nav.corner) {
                this.nav.corner.style.height = this.headerHeight + "px";
            }
        };
        this.updateHeight = function () {
            var sh = this.getScrollableHeight();
            if (this.nav.scroll && sh > 0) {
                this.nav.scroll.style.height = sh + "px";
            }
        };
        this.loadFromServer = function () {
            return (typeof this.events === 'undefined') || !this.events;
        };
        this.initShort = function () {
            this.prepareVariables();
            this.prepareColumns();
            this.drawTop();
            this.drawHeader();
            this.drawMain();
            this.fixScrollHeader();
            this.enableScrolling();
            this.registerGlobalHandlers();
            DayPilotCalendar.register(this);
            this.callBack2('Init');
        };
        this.Init = function () {
            var $1f = this.loadFromServer();
            if ($1f) {
                this.initShort();
                return;
            }
            ;this.prepareVariables();
            this.prepareColumns();
            if (this.events) {
                this.loadEvents();
            }
            ;this.drawTop();
            this.drawHeader();
            this.drawMain();
            this.fixScrollHeader();
            this.enableScrolling();
            this.registerGlobalHandlers();
            DayPilotCalendar.register(this);
            if (this.events) {
                this.updateHeaderHeight();
                this.drawEvents();
            }
            ;this.afterRender(null, false);
        };
    };
    DayPilotCalendar.Cell = function ($q, end, $o) {
        this.start = $q;
        this.end = end;
        this.column = function () {
        };
    };
    DayPilotCalendar.Column = function ($1g, name, $Q) {
        this.value = $1g;
        this.name = name;
        this.date = new DayPilot.Date($Q);
    };
    DayPilotCalendar.Event = function ($l, $b) {
        $l.event = this;
        var ev = this;
        this.div = $l;
        this.root = $b;
        this.calendar = $b;
        this.value = function () {
            return $l.data.Value;
        };
        this.id = function () {
            return $l.data.Value;
        };
        this.text = function () {
            return $l.data.Text;
        };
        this.start = function () {
            return $l.data.Start;
        };
        this.end = function () {
            return $l.data.End;
        };
        this.partStart = function () {
            return $l.data.PartStart;
        };
        this.partEnd = function () {
            return $l.data.PartEnd;
        };
        this.innerHTML = function () {
            var c = $l.getElementsByTagName("DIV");
            return c[c.length - 1].innerHTML;
        };
        this.toJSON = function ($1h) {
            var $1i = {};
            $1i.value = this.value();
            $1i.text = this.text();
            $1i.start = this.start();
            $1i.end = this.end();
            return $1i;
        };
        $l.onmousemove = function (ev) {
            var $1j = 5;
            var $1k = $b.eventHeaderVisible ? ($b.eventHeaderHeight) : 10;
            var w = 5;
            if (typeof (DayPilotCalendar) === 'undefined') {
                return;
            }
            ;var $0J = DayPilot.mo3($l, ev, true);
            if (!$0J) {
                return;
            }
            ;
            if (DayPilotCalendar.resizing || DayPilotCalendar.moving) {
                return;
            }
            ;var $1l = this.isFirst;
            var $1m = this.isLast;
            if ($0J.y <= $1k && $b.eventMoveHandling !== 'Disabled') {
                this.style.cursor = "move";
            } else if (this.offsetHeight - $0J.y <= $1j && $b.eventResizeHandling !== 'Disabled') {
                if ($1m) {
                    this.style.cursor = "s-resize";
                    this.dpBorder = 'bottom';
                } else {
                    this.style.cursor = 'not-allowed';
                }
            } else if (!DayPilotCalendar.resizing && !DayPilotCalendar.moving) {
                if ($b.eventClickHandling !== 'Disabled') {
                    this.style.cursor = 'pointer';
                } else {
                    this.style.cursor = 'default';
                }
            }
        };
        $l.onmousedown = function (ev) {
            ev = ev || window.event;
            var $U = ev.which || ev.button;
            if ((this.style.cursor === 'n-resize' || this.style.cursor === 's-resize') && $U === 1) {
                DayPilotCalendar.resizing = this;
                DayPilotCalendar.originalMouse = DayPilot.mc(ev);
                DayPilotCalendar.originalHeight = this.offsetHeight;
                DayPilotCalendar.originalTop = this.offsetTop;
            } else if (this.style.cursor === 'move' && $U === 1) {
                DayPilotCalendar.moving = this;
                DayPilotCalendar.moving.event = this.event;
                var $1n = DayPilotCalendar.moving.helper = {};
                $1n.oldColumn = $b.columns[this.data.DayIndex].Value;
                DayPilotCalendar.originalMouse = DayPilot.mc(ev);
                DayPilotCalendar.originalTop = this.offsetTop;
                var $0J = DayPilot.mo3(this, ev);
                if ($0J) {
                    DayPilotCalendar.moveOffsetY = $0J.y;
                } else {
                    DayPilotCalendar.moveOffsetY = 0;
                }
                ;document.body.style.cursor = 'move';
            }
            ;
            return false;
        };
    };
    DayPilot.Calendar = DayPilotCalendar.Calendar;
    if (typeof jQuery === 'undefined') {
        return;
    }
    ;(function ($) {
        $.fn.daypilotCalendar = function ($1o) {
            var $1p = null;
            var j = this.each(function () {
                if (this.daypilot) {
                    return;
                }
                ;var $1q = new DayPilot.Calendar(this.id);
                this.daypilot = $1q;
                for (name in $1o) {
                    $1q[name] = $1o[name];
                }
                ;$1q.Init();
                if (!$1p) {
                    $1p = $1q;
                }
            });
            if (this.length === 1) {
                return $1p;
            } else {
                return j;
            }
        };
    })(jQuery);
})();


