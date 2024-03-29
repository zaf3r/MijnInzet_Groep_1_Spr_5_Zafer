if (typeof DayPilot === 'undefined') {
    var DayPilot = {};
}
;(function () {
    if (typeof DayPilot.$ !== 'undefined') {
        return;
    }
    ;DayPilot.$ = function (id) {
        return document.getElementById(id);
    };
    DayPilot.isKhtml = (navigator && navigator.userAgent && navigator.userAgent.indexOf("KHTML") != -1);
    DayPilot.isIE = (navigator && navigator.userAgent && navigator.userAgent.indexOf("MSIE") != -1);
    DayPilot.mo2 = function ($a, ev) {
        ev = ev || window.event;
        if (typeof (ev.offsetX) !== 'undefined') {
            var $b = {x: ev.offsetX + 1, y: ev.offsetY + 1};
            if (!$a) {
                return $b;
            }
            ;var $c = ev.srcElement;
            while ($c && $c != $a) {
                if ($c.tagName != 'SPAN') {
                    $b.x += $c.offsetLeft;
                    if ($c.offsetTop > 0) {
                        $b.y += $c.offsetTop - $c.scrollTop;
                    }
                }
                ;$c = $c.offsetParent;
            }
            ;
            if ($c) {
                return $b;
            }
            ;
            return null;
        }
        ;
        if (typeof (ev.layerX) !== 'undefined') {
            var $b = {x: ev.layerX, y: ev.layerY, $d: ev.target};
            if (!$a) {
                return $b;
            }
            ;var $c = ev.target;
            while ($c && $c.style.position != 'absolute' && $c.style.position != 'relative') {
                $c = $c.parentNode;
                if (DayPilot.isKhtml) {
                    $b.y += $c.scrollTop;
                }
            }
            while ($c && $c != $a) {
                $b.x += $c.offsetLeft;
                $b.y += $c.offsetTop - $c.scrollTop;
                $c = $c.offsetParent;
            }
            ;
            if ($c) {
                return $b;
            }
            ;
            return null;
        }
        ;
        return null;
    };
    DayPilot.mo3 = function ($a, ev, $e) {
        ev = ev || window.event;
        if (typeof (ev.pageX) !== 'undefined') {
            var $f = DayPilot.abs($a, $e);
            var $b = {x: ev.pageX - $f.x, y: ev.pageY - $f.y};
            return $b;
        }
        ;
        return DayPilot.mo2($a, ev);
    };
    DayPilot.abs = function (element, $e) {
        var r = {
            x: element.offsetLeft,
            y: element.offsetTop,
            w: element.clientWidth,
            h: element.clientHeight,
            toString: function () {
                return "x:" + this.x + " y:" + this.y + " w:" + this.w + " h:" + this.h;
            }
        };
        while (element.offsetParent) {
            element = element.offsetParent;
            if ($e) {
                r.x -= element.scrollLeft;
                r.y -= element.scrollTop;
            }
            ;
            if (r.x < 0) {
                r.w += r.x;
                r.x = 0;
            }
            ;
            if (r.y < 0) {
                r.h += r.y;
                r.y = 0;
            }
            ;
            if (element.scrollLeft > 0 && r.x + r.w > element.clientWidth) {
                r.w -= r.x + r.w - element.clientWidth;
            }
            ;
            if (element.scrollTop && r.y + r.h > element.clientHeight) {
                r.h -= r.y + r.h - element.clientHeight;
            }
            ;r.x += element.offsetLeft;
            r.y += element.offsetTop;
        }
        ;var $g = document.documentElement.body ? document.documentElement.body : document.body;
        r.x += $g.scrollLeft;
        r.y += $g.scrollTop;
        return r;
    };
    DayPilot.indexOf = function ($h, $i) {
        if (!$h || !$h.length) {
            return -1;
        }
        ;
        for (var i = 0; i < $h.length; i++) {
            if ($h[i] === $i) {
                return i;
            }
        }
        ;
        return -1;
    };
    DayPilot.mc = function (ev) {
        if (ev.pageX || ev.pageY) {
            return {x: ev.pageX, y: ev.pageY};
        }
        ;
        return {
            x: ev.clientX + document.documentElement.scrollLeft,
            y: ev.clientY + document.documentElement.scrollTop
        };
    };
    DayPilot.re = function (el, ev, $j) {
        if (el.addEventListener) {
            el.addEventListener(ev, $j, false);
        } else if (el.attachEvent) {
            el.attachEvent("on" + ev, $j);
        }
    };
    DayPilot.pu = function (d) {
        var a = d.attributes, i, l, n;
        if (a) {
            l = a.length;
            for (i = 0; i < l; i += 1) {
                if (!a[i]) {
                    continue;
                }
                ;n = a[i].name;
                if (typeof d[n] === 'function') {
                    d[n] = null;
                }
            }
        }
        ;a = d.childNodes;
        if (a) {
            l = a.length;
            for (i = 0; i < l; i += 1) {
                var $k = DayPilot.pu(d.childNodes[i]);
            }
        }
    };
    DayPilot.de = function (e) {
        if (!e) {
            return;
        }
        ;
        if (!e.parentNode) {
            return;
        }
        ;e.parentNode.removeChild(e);
    };
    DayPilot.sw = function (element) {
        if (!element) {
            return 0;
        }
        ;
        return element.offsetWidth - element.clientWidth - 2;
    };
    DayPilot.Selection = function ($l, end, $m, $n) {
        this.type = 'selection';
        this.start = $l.isDayPilotDate ? $l : new DayPilot.Date($l);
        this.end = end.isDayPilotDate ? end : new DayPilot.Date(end);
        this.resource = $m;
        this.root = $n;
        this.toJSON = function ($o) {
            var $p = {};
            $p.start = this.start;
            $p.end = this.end;
            $p.resource = this.resource;
            return $p;
        };
    };
    DayPilot.request = function ($q, $r, $s, $t) {
        var $u = DayPilot.createXmlHttp();
        if (!$u) {
            return;
        }
        ;$u.open("POST", $q, true);
        $u.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        $u.onreadystatechange = function () {
            if ($u.readyState != 4) return;
            if ($u.status != 200 && $u.status != 304) {
                if ($t) {
                    $t($u);
                } else {
                    alert('HTTP error ' + $u.status);
                }
                ;
                return;
            }
            ;$r($u);
        };
        if ($u.readyState == 4) {
            return;
        }
        ;$u.send($s);
    };
    DayPilot.createXmlHttp = function () {
        var $v;
        try {
            $v = new XMLHttpRequest();
        } catch (e) {
            try {
                $v = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {
            }
        }
        ;
        return $v;
    };
    DayPilot.Date = function ($w, $x) {
        this.isDayPilotDate = true;
        if (typeof $w === 'undefined') {
            this.d = DayPilot.Date.fromLocal();
        } else if (typeof $w === "string") {
            return DayPilot.Date.fromStringSortable($w);
        } else if ($w.isDayPilotDate) {
            return $w;
        } else if (!$w.getFullYear) {
            throw "date parameter is not a Date object: " + $w;
        } else if ($x) {
            this.d = DayPilot.Date.fromLocal($w);
        } else {
            this.d = $w;
        }
        ;this.ticks = this.d.getTime();
    };
    DayPilot.Date.prototype.addDays = function ($y) {
        return new DayPilot.Date(DayPilot.Date.addDays(this.d, $y));
    };
    DayPilot.Date.prototype.addHours = function ($z) {
        return this.addTime($z * 60 * 60 * 1000);
    };
    DayPilot.Date.prototype.addMilliseconds = function ($A) {
        return this.addTime($A);
    };
    DayPilot.Date.prototype.addMinutes = function ($B) {
        return this.addTime($B * 60 * 1000);
    };
    DayPilot.Date.prototype.addMonths = function ($C) {
        return new DayPilot.Date(DayPilot.Date.addMonths(this.d, $C));
    };
    DayPilot.Date.prototype.addSeconds = function ($D) {
        return this.addTime($D * 1000);
    };
    DayPilot.Date.prototype.addTime = function ($E) {
        return new DayPilot.Date(DayPilot.Date.addTime(this.d, $E));
    };
    DayPilot.Date.prototype.addYears = function ($F) {
        var n = this.clone();
        n.d.setUTCFullYear(this.getYear() + $F);
        return n;
    };
    DayPilot.Date.prototype.clone = function () {
        return new DayPilot.Date(DayPilot.Date.clone(this.d));
    };
    DayPilot.Date.prototype.dayOfWeek = function () {
        return this.d.getUTCDay();
    };
    DayPilot.Date.prototype.daysInMonth = function () {
        return DayPilot.Date.daysInMonth(this.d);
    };
    DayPilot.Date.prototype.dayOfYear = function () {
        return Math.ceil((this.getDatePart().getTime() - this.firstDayOfYear().getTime()) / 86400000) + 1;
    };
    DayPilot.Date.prototype.equals = function ($G) {
        if ($G === null) {
            return false;
        }
        ;
        if ($G.isDayPilotDate) {
            return DayPilot.Date.equals(this.d, $G.d);
        } else {
            throw "The parameter must be a DayPilot.Date object (DayPilot.Date.equals())";
        }
    };
    DayPilot.Date.prototype.firstDayOfMonth = function () {
        var $H = DayPilot.Date.firstDayOfMonth(this.getYear(), this.getMonth() + 1);
        return new DayPilot.Date($H);
    };
    DayPilot.Date.prototype.firstDayOfYear = function () {
        var $I = this.getYear();
        var d = new Date();
        d.setUTCFullYear($I, 0, 1);
        d.setUTCHours(0);
        d.setUTCMinutes(0);
        d.setUTCSeconds(0);
        d.setUTCMilliseconds(0);
        return new DayPilot.Date(d);
    };
    DayPilot.Date.prototype.firstDayOfWeek = function ($J) {
        var $H = DayPilot.Date.firstDayOfWeek(this.d, $J);
        return new DayPilot.Date($H);
    };
    DayPilot.Date.prototype.getDay = function () {
        return this.d.getUTCDate();
    };
    DayPilot.Date.prototype.getDatePart = function () {
        return new DayPilot.Date(DayPilot.Date.getDate(this.d));
    };
    DayPilot.Date.prototype.getYear = function () {
        return this.d.getUTCFullYear();
    };
    DayPilot.Date.prototype.getHours = function () {
        return this.d.getUTCHours();
    };
    DayPilot.Date.prototype.getMilliseconds = function () {
        return this.d.getUTCMilliseconds();
    };
    DayPilot.Date.prototype.getMinutes = function () {
        return this.d.getUTCMinutes();
    };
    DayPilot.Date.prototype.getMonth = function () {
        return this.d.getUTCMonth();
    };
    DayPilot.Date.prototype.getSeconds = function () {
        return this.d.getUTCSeconds();
    };
    DayPilot.Date.prototype.getTotalTicks = function () {
        return this.getTime();
    };
    DayPilot.Date.prototype.getTime = function () {
        if (typeof this.ticks !== 'number') {
            throw "Uninitialized DayPilot.Date (internal error)";
        }
        ;
        return this.ticks;
    };
    DayPilot.Date.prototype.getTimePart = function () {
        return DayPilot.Date.getTime(this.d);
    };
    DayPilot.Date.prototype.lastDayOfMonth = function () {
        var $H = DayPilot.Date.lastDayOfMonth(this.getYear(), this.getMonth() + 1);
        return new DayPilot.Date($H);
    };
    DayPilot.Date.prototype.weekNumber = function () {
        var $K = this.firstDayOfYear();
        var $y = (this.getTime() - $K.getTime()) / 86400000;
        return Math.ceil(($y + $K.dayOfWeek() + 1) / 7);
    };
    DayPilot.Date.prototype.weekNumberISO = function () {
        var $L = false;
        var $M = this.dayOfYear();
        var $N = this.firstDayOfYear().dayOfWeek();
        var $O = this.firstDayOfYear().addYears(1).addDays(-1).dayOfWeek();
        if ($N == 0) {
            $N = 7;
        }
        ;
        if ($O == 0) {
            $O = 7;
        }
        ;var $P = 8 - ($N);
        if ($N == 4 || $O == 4) {
            $L = true;
        }
        ;var $Q = Math.ceil(($M - ($P)) / 7.0);
        var $R = $Q;
        if ($P >= 4) {
            $R = $R + 1;
        }
        ;
        if ($R > 52 && !$L) {
            $R = 1;
        }
        ;
        if ($R == 0) {
            $R = this.firstDayOfYear().addDays(-1).weekNumberISO();
        }
        ;
        return $R;
    };
    DayPilot.Date.prototype.toDateLocal = function () {
        return DayPilot.Date.toLocal(this.d);
    };
    DayPilot.Date.prototype.toJSON = function () {
        return this.toStringSortable();
    };
    DayPilot.Date.prototype.toString = function () {
        return this.toStringSortable();
    };
    DayPilot.Date.prototype.toStringSortable = function () {
        return DayPilot.Date.toStringSortable(this.d);
    };
    DayPilot.Date.fromStringSortable = function ($S) {
        var $T = /^(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2})$/;
        var $w = /^(\d{4})-(\d{2})-(\d{2})$/;
        var $U = $T.test($S);
        var $V = $w.test($S);
        var $W = $U || $V;
        if (!$W) {
            throw "Invalid string format (use '2010-01-01' or '2010-01-01T00:00:00'.";
        }
        ;var $X = $U ? $T : $w;
        var m = $X.exec($S);
        var d = new Date();
        d.setUTCFullYear(m[1], m[2] - 1, m[3]);
        d.setUTCHours(m[4] ? m[4] : 0);
        d.setUTCMinutes(m[5] ? m[5] : 0);
        d.setUTCSeconds(m[6] ? m[6] : 0);
        d.setUTCMilliseconds(0);
        return new DayPilot.Date(d);
    };
    DayPilot.Date.addDays = function ($w, $y) {
        var d = new Date();
        d.setTime($w.getTime() + $y * 24 * 60 * 60 * 1000);
        return d;
    };
    DayPilot.Date.addMinutes = function ($w, $B) {
        var d = new Date();
        d.setTime($w.getTime() + $B * 60 * 1000);
        return d;
    };
    DayPilot.Date.addMonths = function ($w, $C) {
        if ($C == 0) return $w;
        var y = $w.getUTCFullYear();
        var m = $w.getUTCMonth() + 1;
        if ($C > 0) {
            while ($C >= 12) {
                $C -= 12;
                y++;
            }
            ;
            if ($C > 12 - m) {
                y++;
                m = $C - (12 - m);
            } else {
                m += $C;
            }
        } else {
            while ($C <= -12) {
                $C += 12;
                y--;
            }
            ;
            if (m <= $C) {
                y--;
                m = 12 - ($C + m);
            } else {
                m = m + $C;
            }
        }
        ;var d = DayPilot.Date.clone($w);
        d.setUTCFullYear(y);
        d.setUTCMonth(m - 1);
        return d;
    };
    DayPilot.Date.addTime = function ($w, $Y) {
        var d = new Date();
        d.setTime($w.getTime() + $Y);
        return d;
    };
    DayPilot.Date.clone = function ($Z) {
        var d = new Date();
        return DayPilot.Date.dateFromTicks($Z.getTime());
    };
    DayPilot.Date.daysDiff = function ($K, $00) {
        if ($K.getTime() > $00.getTime()) {
            return null;
        }
        ;var i = 0;
        var $01 = DayPilot.Date.getDate($K);
        var $02 = DayPilot.Date.getDate($00);
        while ($01 < $02) {
            $01 = DayPilot.Date.addDays($01, 1);
            i++;
        }
        ;
        return i;
    };
    DayPilot.Date.daysInMonth = function ($I, $03) {
        if ($I.getUTCFullYear) {
            $03 = $I.getUTCMonth() + 1;
            $I = $I.getUTCFullYear();
        }
        ;var m = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
        if ($03 != 2) return m[$03 - 1];
        if ($I % 4 != 0) return m[1];
        if ($I % 100 == 0 && $I % 400 != 0) return m[1];
        return m[1] + 1;
    };
    DayPilot.Date.daysSpan = function ($K, $00) {
        if ($K.getTime() == $00.getTime()) {
            return 0;
        }
        ;var $04 = DayPilot.Date.daysDiff($K, $00);
        if (DayPilot.Date.equals($00, DayPilot.Date.getDate($00))) {
            $04--;
        }
        ;
        return $04;
    };
    DayPilot.Date.diff = function ($K, $00) {
        if (!($K && $00 && $K.getTime && $00.getTime)) {
            throw "Both compared objects must be Date objects (DayPilot.Date.diff).";
        }
        ;
        return $K.getTime() - $00.getTime();
    };
    DayPilot.Date.equals = function ($K, $00) {
        return $K.getTime() == $00.getTime();
    };
    DayPilot.Date.fromLocal = function ($05) {
        if (!$05) {
            $05 = new Date();
        }
        ;var d = new Date();
        d.setUTCFullYear($05.getFullYear(), $05.getMonth(), $05.getDate());
        d.setUTCHours($05.getHours());
        d.setUTCMinutes($05.getMinutes());
        d.setUTCSeconds($05.getSeconds());
        d.setUTCMilliseconds($05.getMilliseconds());
        return d;
    };
    DayPilot.Date.firstDayOfMonth = function ($I, $03) {
        var d = new Date();
        d.setUTCFullYear($I, $03 - 1, 1);
        d.setUTCHours(0);
        d.setUTCMinutes(0);
        d.setUTCSeconds(0);
        d.setUTCMilliseconds(0);
        return d;
    };
    DayPilot.Date.firstDayOfWeek = function (d, $J) {
        var $06 = d.getUTCDay();
        while ($06 != $J) {
            d = DayPilot.Date.addDays(d, -1);
            $06 = d.getUTCDay();
        }
        ;
        return d;
    };
    DayPilot.Date.dateFromTicks = function ($E) {
        var d = new Date();
        d.setTime($E);
        return d;
    };
    DayPilot.Date.getDate = function ($Z) {
        var d = DayPilot.Date.clone($Z);
        d.setUTCHours(0);
        d.setUTCMinutes(0);
        d.setUTCSeconds(0);
        d.setUTCMilliseconds(0);
        return d;
    };
    DayPilot.Date.getStart = function ($I, $03, $J) {
        var $07 = DayPilot.Date.firstDayOfMonth($I, $03);
        d = DayPilot.Date.firstDayOfWeek($07, $J);
        return d;
    };
    DayPilot.Date.getTime = function ($Z) {
        var $w = DayPilot.Date.getDate($Z);
        return DayPilot.Date.diff($Z, $w);
    };
    DayPilot.Date.hours = function ($w, $08) {
        var $09 = $w.getUTCMinutes();
        if ($09 < 10) $09 = "0" + $09;
        var $0a = $w.getUTCHours();
        if ($08) {
            var am = $0a < 12;
            var $0a = $0a % 12;
            if ($0a == 0) {
                $0a = 12;
            }
            ;var $0b = am ? "AM" : "PM";
            return $0a + ':' + $09 + ' ' + $0b;
        } else {
            return $0a + ':' + $09;
        }
    };
    DayPilot.Date.lastDayOfMonth = function ($I, $03) {
        var d = DayPilot.Date.firstDayOfMonth($I, $03);
        var length = DayPilot.Date.daysInMonth($I, $03);
        d.setUTCDate(length);
        return d;
    };
    DayPilot.Date.max = function ($K, $00) {
        if ($K.getTime() > $00.getTime()) {
            return $K;
        } else {
            return $00;
        }
    };
    DayPilot.Date.min = function ($K, $00) {
        if ($K.getTime() < $00.getTime()) {
            return $K;
        } else {
            return $00;
        }
    };
    DayPilot.Date.today = function () {
        var $0c = new Date();
        var d = new Date();
        d.setUTCFullYear($0c.getFullYear());
        d.setUTCMonth($0c.getMonth());
        d.setUTCDate($0c.getDate());
        return d;
    };
    DayPilot.Date.toLocal = function ($w) {
        if (!$w) {
            $w = new Date();
        }
        ;var d = new Date();
        d.setFullYear($w.getUTCFullYear(), $w.getUTCMonth(), $w.getUTCDate());
        d.setHours($w.getUTCHours());
        d.setMinutes($w.getUTCMinutes());
        d.setSeconds($w.getUTCSeconds());
        d.setMilliseconds($w.getUTCMilliseconds());
        return d;
    };
    DayPilot.Date.toStringSortable = function ($w) {
        if ($w.isDayPilotDate) {
            return $w.toStringSortable();
        }
        ;var d = $w;
        var $00 = d.getUTCSeconds();
        if ($00 < 10) $00 = "0" + $00;
        var $09 = d.getUTCMinutes();
        if ($09 < 10) $09 = "0" + $09;
        var $0a = d.getUTCHours();
        if ($0a < 10) $0a = "0" + $0a;
        var $06 = d.getUTCDate();
        if ($06 < 10) $06 = "0" + $06;
        var $03 = d.getUTCMonth() + 1;
        if ($03 < 10) $03 = "0" + $03;
        var $I = d.getUTCFullYear();
        if ($I <= 0) {
            throw "The minimum year supported is 1.";
        }
        ;
        if ($I < 10) {
            $I = "000" + $I;
        } else if ($I < 100) {
            $I = "00" + $I;
        } else if ($I < 1000) {
            $I = "0" + $I;
        }
        ;
        return $I + "-" + $03 + "-" + $06 + 'T' + $0a + ":" + $09 + ":" + $00;
    };
})();
DayPilot.JSON = {};
(function () {
    function f(n) {
        return n < 10 ? '0' + n : n;
    };
    if (typeof Date.prototype.toJSON2 !== 'function') {
        Date.prototype.toJSON2 = function ($o) {
            return this.getUTCFullYear() + '-' + f(this.getUTCMonth() + 1) + '-' + f(this.getUTCDate()) + 'T' + f(this.getUTCHours()) + ':' + f(this.getUTCMinutes()) + ':' + f(this.getUTCSeconds()) + '';
        };
        String.prototype.toJSON = Number.prototype.toJSON = Boolean.prototype.toJSON = function ($o) {
            return this.valueOf();
        };
    }
    ;var cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        $0d = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        $0e, $0f, $0g = {'\b': '\\b', '\t': '\\t', '\n': '\\n', '\f': '\\f', '\r': '\\r', '"': '\\"', '\\': '\\\\'},
        $0h;

    function quote($S) {
        $0d.lastIndex = 0;
        return $0d.test($S) ? '"' + $S.replace($0d, function (a) {
            var c = $0g[a];
            if (typeof c === 'string') {
                return c;
            }
            ;
            return '\\u' + ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
        }) + '"' : '"' + $S + '"';
    };

    function str($o, $0i) {
        var i, k, v, length, $0j = $0e, $0k, $0l = $0i[$o];
        if ($0l && typeof $0l === 'object' && typeof $0l.toJSON2 === 'function') {
            $0l = $0l.toJSON2($o);
        } else if ($0l && typeof $0l === 'object' && typeof $0l.toJSON === 'function' && !$0l.ignoreToJSON) {
            $0l = $0l.toJSON($o);
        }
        ;
        if (typeof $0h === 'function') {
            $0l = $0h.call($0i, $o, $0l);
        }
        ;
        switch (typeof $0l) {
            case 'string':
                return quote($0l);
            case 'number':
                return isFinite($0l) ? String($0l) : 'null';
            case 'boolean':
            case 'null':
                return String($0l);
            case 'object':
                if (!$0l) {
                    return 'null';
                }
                ;$0e += $0f;
                $0k = [];
                if (typeof $0l.length === 'number' && !$0l.propertyIsEnumerable('length')) {
                    length = $0l.length;
                    for (i = 0; i < length; i += 1) {
                        $0k[i] = str(i, $0l) || 'null';
                    }
                    ;v = $0k.length === 0 ? '[]' : $0e ? '[\n' + $0e + $0k.join(',\n' + $0e) + '\n' + $0j + ']' : '[' + $0k.join(',') + ']';
                    $0e = $0j;
                    return v;
                }
                ;
                if ($0h && typeof $0h === 'object') {
                    length = $0h.length;
                    for (i = 0; i < length; i += 1) {
                        k = $0h[i];
                        if (typeof k === 'string') {
                            v = str(k, $0l);
                            if (v) {
                                $0k.push(quote(k) + ($0e ? ': ' : ':') + v);
                            }
                        }
                    }
                } else {
                    for (k in $0l) {
                        if (Object.hasOwnProperty.call($0l, k)) {
                            v = str(k, $0l);
                            if (v) {
                                $0k.push(quote(k) + ($0e ? ': ' : ':') + v);
                            }
                        }
                    }
                }
                ;v = ($0k.length === 0) ? '{\u007D' : $0e ? '{\n' + $0e + $0k.join(',\n' + $0e) + '\n' + $0j + '\u007D' : '{' + $0k.join(',') + '\u007D';
                $0e = $0j;
                return v;
        }
    };
    if (typeof DayPilot.JSON.stringify !== 'function') {
        DayPilot.JSON.stringify = function ($0l, $0m, $0n) {
            var i;
            $0e = '';
            $0f = '';
            if (typeof $0n === 'number') {
                for (i = 0; i < $0n; i += 1) {
                    $0f += ' ';
                }
            } else if (typeof $0n === 'string') {
                $0f = $0n;
            }
            ;$0h = $0m;
            if ($0m && typeof $0m !== 'function' && (typeof $0m !== 'object' || typeof $0m.length !== 'number')) {
                throw new Error('JSON.stringify');
            }
            ;
            return str('', {'': $0l});
        };
    }
})();


