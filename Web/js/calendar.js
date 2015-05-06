var calendarMoveable = true;
var calendarFrame = null;
var calendarDoc = null;
var calendarOutObject = null;
var calendarOutDate = ""; // 存放对象的日期
var calendarShowTime = false; // 是否使用时间
// 定义阳历中每个月的最大天数
var MonHead =[31,28,31,30,31,30,31,31,30,31,30,31];
// 定义年的变量的初始值
var meizzTheYear = new Date().getFullYear();
// 定义月的变量的初始值
var meizzTheMonth = new Date().getMonth() + 1;
// 定义日的变量的初始值
var meizzTheDate = new Date().getDate();
// 定义小时变量的初始值
var meizzTheHour = new Date().getHours();
// 定义分钟变量的初始值
var meizzTheMinute = new Date().getMinutes();
// 定义秒变量的初始值
var meizzTheSecond = new Date().getSeconds();
// 定义写日期的数组
var meizzWDay = []; 

function buildCalendar(event) {
	if (calendarFrame != null) return;
	calendarFrame = document.createElement("IFRAME");
	calendarFrame.id = "Calendar$Frame";
	calendarFrame.marginWidth = 0;
	calendarFrame.marginHeight = 0;
	calendarFrame.frameBorder = 0;
	calendarFrame.width = "1px";
	calendarFrame.height = "1px";
	calendarFrame.style.position = "absolute";
	calendarFrame.style.zIndex = 9998;
	calendarFrame.scrolling = "no";
	calendarFrame.style.display = "none";
	document.body.appendChild(calendarFrame);

	var calendarHTML = '<?DOCTYPE HTML><html><head><style>';
	calendarHTML += 'INPUT.button{BORDER-RIGHT: #7ECEE2 1px solid;BORDER-TOP: #7ECEE2 1px solid;BORDER-LEFT: #7ECEE2 1px solid;BORDER-BOTTOM: #7ECEE2 1px solid;BACKGROUND-COLOR: #7ECEE2;font-family:宋体;}';
	calendarHTML += 'TD{FONT-SIZE: 9pt;font-family:宋体;}';
	calendarHTML += '</style>';
	calendarHTML += '<scr' + 'ipt>';
	calendarHTML += 'var datelayerx,datelayery;';
	calendarHTML += 'var bDrag;';
	calendarHTML += 'document.onmousemove = function(event) {';
	calendarHTML += '  if(event == null) event = window.event;';
	calendarHTML += '  if(bDrag && event.button==1) {';
	calendarHTML += '    var DateLayer=parent.document.getElementById("Calendar$Frame").style;';
	calendarHTML += '    DateLayer.posLeft += event.clientX-datelayerx + "px";';
	calendarHTML += '    DateLayer.posTop += event.clientY-datelayery + "px";}};';
	calendarHTML += 'function DragStart(event) {';
	calendarHTML += '  if(event == null) event = window.event;';
	calendarHTML += '  var DateLayer=parent.document.getElementById("Calendar$Frame").style;';
	calendarHTML += '  datelayerx=window.event.clientX;';
	calendarHTML += '  datelayery=window.event.clientY;';
	calendarHTML += '  bDrag=true;}';
	calendarHTML += 'function DragEnd(event){';
	calendarHTML += '  if(event == null) event = window.event;';
	calendarHTML += '  bDrag=false;}';
	calendarHTML += 'document.onkeydown = function(evt) {if(evt == null) evt = window.event;if(event.stopPropagation) {event.preventDefault();event.stopPropagation();} else {event.returnValue = false;event.cancelBubble = true;}}';
	calendarHTML += '</scr' + 'ipt></head><body>';
	calendarHTML += '<div style="z-index:9999;position: absolute; left:0px; top:0px;background-color:#7ECEE2;" onselectstart="return false">';
	calendarHTML += '<span id=tmpSelectYearLayer  style="z-index: 9999;position: absolute;top: 3px; left: 19px;display: none"></span>';
	calendarHTML += '<span id=tmpSelectMonthLayer  style="z-index: 9999;position: absolute;top: 3px; left: 78px;display: none"></span>';
	calendarHTML += '<span id=tmpSelectHourLayer  style="z-index: 9999;position: absolute;top: 188px; left: 35px;display: none"></span>';
	calendarHTML += '<span id=tmpSelectMinuteLayer style="z-index:9999;position:absolute;top: 188px; left: 77px;display: none"></span>';
	calendarHTML += '<span id=tmpSelectSecondLayer style="z-index:9999;position:absolute;top: 188px; left: 119px;display: none"></span>';
	calendarHTML += '<table border="1" cellspacing="0" cellpadding="0" bordercolor=#7ECEE2 bgcolor=#7ECEE2 >';
	calendarHTML += '	<tr><td bgcolor=#FFFFFF>';
	calendarHTML += '		<table border="0" cellspacing="1" cellpadding="0">';
	calendarHTML += '			<tr align=center >';
	calendarHTML += '				<td width="16" height="20px" align="center" bgcolor="#7ECEE2" style="font-size:12px;cursor: pointer;color: #ffffff" onclick="parent.meizzPrevM()" title="向前翻 1 月" ><b >&lt;</b></td>';
	calendarHTML += '				<td width="70" align="center" bgcolor="#7ECEE2" style="font-size:12px;cursor:pointer;" ';
	calendarHTML += '					onmouseover="style.backgroundColor=\'#95E8FD\'"';
	calendarHTML += '					onmouseout="style.backgroundColor=\'#7ECEE2\'" ';
	calendarHTML += '					onclick="var text = this.innerText || this.textContent;parent.tmpSelectYearInnerHTML(text.substring(0,4));" ';
	calendarHTML += '					title="点击这里选择年份"><span  id=meizzYearHead></span></td>';
	calendarHTML += '				<td width="60" align="center" style="font-size:12px;font-color: #ffffff;cursor:pointer;" bgcolor="#7ECEE2"';
	calendarHTML += '					onmouseover="style.backgroundColor=\'#aaccf3\'" ';
	calendarHTML += '					onmouseout="style.backgroundColor=\'#7ECEE2\'" ';
	calendarHTML += '					onclick="var text = this.innerText || this.textContent;parent.tmpSelectMonthInnerHTML(text.length==3 ? text.substring(0,1) : text.substring(0,2));"';
	calendarHTML += '					title="点击这里选择月份"><span id=meizzMonthHead ></span></td>';
	calendarHTML += '				<td width="16" bgcolor="#7ECEE2" align="center" style="font-size:12px;cursor: pointer;color: #ffffff" ';
	calendarHTML += '					onclick="parent.meizzNextM()" title="向后翻 1 月" ><b >&gt;</b></td>';
	calendarHTML += '			</tr>';
	calendarHTML += '		</table></td></tr>';
	calendarHTML += '	<tr><td>';
	calendarHTML += '		<table border="0" align="center" width="100%" style="text-align:center;" cellspacing="0" cellpadding="2" bgcolor="#7ECEE2" ' + (calendarMoveable ? 'onmousedown="DragStart(event)" onmouseup="DragEnd(event)"' : '');
	calendarHTML += '			BORDERCOLORLIGHT=#7ECEE2 BORDERCOLORDARK=#FFFFFF style="cursor:' + (calendarMoveable ? 'move' : 'default') + '">';
	calendarHTML += '			<tr><td height="16px" style="font-size:12px;color:#ffffff" width=20>日</td><td style="font-size:12px;color:#FFFFFF" >一</td><td style="font-size:12px;color:#FFFFFF" >二</td><td style="font-size:12px;color:#FFFFFF" >三</td><td style="font-size:12px;color:#FFFFFF" >四</td><td style="font-size:12px;color:#FFFFFF" >五</td><td style="font-size:12px;color:#FFFFFF" >六</td></tr></table></td></tr>';
	calendarHTML += '	<tr><td>';
	calendarHTML += '		<table border="1" cellspacing="2" cellpadding="2" BORDERCOLORLIGHT="#7ECEE2" BORDERCOLORDARK="#FFFFFF" bgcolor="#FFF8F5" >';
	calendarHTML += '		<colgroup><col width="20px" /><col width="20px" /><col width="20px" /><col width="20px" /><col width="20px" /><col width="20px" /><col width="20px" /></colgroup>';
	var n = 0;
	for (var j = 0; j < 5; j++) {
	calendarHTML += '			<tr>';
		for (var i = 0; i < 7; i++) {
	calendarHTML += '				<td height="16px;" align="center" id="meizzDay' + n + '" style="font-size:12px" onclick="var text = this.innerText || this.textContent;parent.meizzDayClick(text,0);">&nbsp;</td>';
			n++;
		}
	calendarHTML += '			</tr>';
	}
	calendarHTML += '			<tr>';
	for (var i = 35; i < 37; i++)
	calendarHTML += '				<td height="16px" align="center" id="meizzDay' + i + '" style="font-size:12px"  onclick="var text = this.innerText || this.textContent;parent.meizzDayClick(text,0)">&nbsp;</td>';
	
	calendarHTML += '				<td colspan="5" align="right" style="color:#2288AA"><span onclick="parent.setNull()" style="font-size:12px;cursor: pointer"';
	calendarHTML += '					onmouseover="style.color=\'#ff0000\'" onmouseout="style.color=\'#2288AA\'" title="将日期置空">置空</span>&nbsp;&nbsp;<span onclick="parent.meizzToday()" style="font-size:12px;cursor: pointer"';
	calendarHTML += '					onmouseover="style.color=\'#ff0000\'" onmouseout="style.color=\'#2288AA\'" title="当前日期时间">当前</span>&nbsp;&nbsp;<span style="cursor:pointer;" id=evaAllOK onmouseover="style.color=\'#ff0000\'" onmouseout="style.color=\'#2288AA\'"  onclick="parent.closeCalendar()" title="关闭日历">关闭&nbsp;</span></td></tr>';
	calendarHTML += '		</table></td></tr><tr ><td >';
	calendarHTML += '		<table id=calendar_TimeLayer border=0 cellspacing=1 cellpadding=0 width="100%" bgcolor=#FFFFFF height="18" >';
	calendarHTML += '			<tr bgcolor="#7ECEE2"><td id=calendarShowTimeLayer width=30  style="cursor:pointer;" title="点击这里启用/禁用时间"';
	calendarHTML += '					onmouseover="style.backgroundColor=\'#aaccf3\'" align=center onmouseout="style.backgroundColor=\'#7ECEE2\'"';
	calendarHTML += '					onclick="parent.UseTime(this)">';
	calendarHTML += '					<span></span></td>';
	calendarHTML += '				<td style="cursor:pointer;" onclick="var text = this.innerText || this.textContent;parent.tmpSelectHourInnerHTML(text.length==3 ? text.substring(0,1) : text.substring(0,2));"';
	calendarHTML += '					onmouseover="style.backgroundColor=\'#aaccf3\'" onmouseout="style.backgroundColor=\'#7ECEE2\'"';
	calendarHTML += '					title="点击这里选择时间" align=center width=42><span id=meizzHourHead></span></td>';
	calendarHTML += '				<td style="cursor:pointer;" onclick="var text = this.innerText || this.textContent;parent.tmpSelectMinuteInnerHTML(text.length==3 ? text.substring(0,1) : text.substring(0,2));"';
	calendarHTML += '					onmouseover="style.backgroundColor=\'#aaccf3\'" onmouseout="style.backgroundColor=\'#7ECEE2\'"';
	calendarHTML += '					title="点击这里选择时间" align=center width=42><span id=meizzMinuteHead></span></td>';
	calendarHTML += '				<td style="cursor:pointer;" onclick="var text = this.innerText || this.textContent;parent.tmpSelectSecondInnerHTML(text.length==3 ? text.substring(0,1) : text.substring(0,2));"';
	calendarHTML += '					onmouseover="style.backgroundColor=\'#aaccf3\'" onmouseout="style.backgroundColor=\'#7ECEE2\'"';
	calendarHTML += '					title="点击这里选择时间" align=center width=42><span id=meizzSecondHead></span></td>';
	calendarHTML += '			</tr></table></td></tr></table></div></body></html>';

	calendarDoc = calendarFrame.contentWindow.document;
	with (calendarDoc) {
		open();
		writeln(calendarHTML);
		close();
	}
	// 任意点击时关闭该控件
	if (document.attachEvent) {
		document.attachEvent("onclick", function() {if (window.event.srcElement != calendarOutObject) closeCalendar();});
		document.attachEvent("onkeyup", function() {if (window.event.keyCode == 27) {if (calendarOutObject) calendarOutObject.blur();closeCalendar();} else if (document.activeElement) {if (document.activeElement != calendarOutObject) closeCalendar();}});
	} else if (document.addEventListener) {
		document.addEventListener("click", function(event) {if (event.target != calendarOutObject) closeCalendar();}, false);
		document.addEventListener("keyup", function(event) {if (event.keyCode == 27) {if (calendarOutObject) calendarOutObject.blur();closeCalendar();} else if (document.activeElement) {if (document.activeElement != calendarOutObject) closeCalendar();}}, false);
	}
}

function loadCalendar(oInput, hiddenTime) {
	buildCalendar();
	if (typeof (oInput) == "string") oInput = document.getElementById(oInput);
	if (oInput == null) {
		alert("对不起，没有指定日历输入控件");
		return;
	}
	var dads = document.getElementById("Calendar$Frame").style;
	var rect = oInput.getBoundingClientRect();
	var tleft = rect.left + Math.max(document.documentElement.scrollLeft, document.body.scrollLeft);
	var ttop = rect.top + Math.max(document.documentElement.scrollTop, document.body.scrollTop);
	var thei = oInput.clientHeight;
	var oOffsetParent = oInput;
	dads.top = ttop + thei + 2 + "px";
	dads.left = tleft + "px";
	calendarOutObject = oInput;

	// 根据当前输入框的日期显示日历的年月
	var val = calendarOutObject.value;
	if (val == "") {
		if (oInput.style.year != null && oInput.style.year > 0) val = oInput.style.year + "-01-01";
	}
	var reg = null;
	if (val.length <= 10) reg = /^(\d+)-(\d{1,2})-(\d{1,2})/; // 不含时间
	else reg = /^(\d+)-(\d{1,2})-(\d{1,2})\s+(\d{1,2}):(\d{1,2}):(\d{1,2})/; // 含时间
	var r = val.match(reg);
	if (r != null) {
		r[2] = r[2] - 1;
		var d = new Date(r[1], r[2], r[3]);
		if (d.getFullYear() == r[1] && d.getMonth() == r[2] && d.getDate() == r[3]) {
			calendarOutDate = d;
			parent.meizzTheYear = r[1];
			parent.meizzTheMonth = r[2];
			parent.meizzTheDate = r[3];
		} 
		else calendarOutDate = "";
		if (r.length > 4) meizzTheHour = parent.meizzTheHour = r[4];
		if (r.length > 5) meizzTheMinute = parent.meizzTheMinute = r[5];
		if (r.length > 6) meizzTheSecond = parent.meizzTheSecond = r[6];
		meizzSetDay(r[1], r[2] + 1);
	} 
	else {
		calendarOutDate = "";
		meizzSetDay(new Date().getFullYear(), new Date().getMonth() + 1);
	}
	dads.display = '';
	if (hiddenTime || calendarOutObject.style.calendar == "date") {
		calendarDoc.getElementById("calendar_TimeLayer").style.display = "none";
		calendarShowTime = false;
	} else {
		calendarDoc.getElementById("calendar_TimeLayer").style.display = "block";
		calendarShowTime = true;
	}
	calendarDoc.getElementById("calendarShowTimeLayer").innerHTML = bImgSwitch();
	meizzWriteHead(meizzTheYear, meizzTheMonth);

	try {
		event.cancelBubble = true;
		event.returnValue = false;
	} catch (e) {
	} // 此处排除错误，错误原因暂未找到。
	
	var oDiv = calendarDoc.body.childNodes[0];
	calendarFrame.width = "170px";
	calendarFrame.height = oDiv.offsetHeight + 4 + "px";
}


/**
 *  往 head 中写入当前的年与月
 */
function meizzWriteHead(yy, mm, ss) {
	if(document.attachEvent) {
		calendarDoc.getElementById("meizzYearHead").innerText = yy + " 年";
		calendarDoc.getElementById("meizzMonthHead").innerText = calendarFormat(mm) + " 月";
		// 插入当前小时、分
		calendarDoc.getElementById("meizzHourHead").innerText = calendarShowTime ? (meizzTheHour + " 时") : "";
		calendarDoc.getElementById("meizzMinuteHead").innerText = calendarShowTime ? (meizzTheMinute + " 分") : "";
		calendarDoc.getElementById("meizzSecondHead").innerText = calendarShowTime ? (meizzTheSecond + " 秒") : "";
	}
	else {
		calendarDoc.getElementById("meizzYearHead").textContent = yy + " 年";
		calendarDoc.getElementById("meizzMonthHead").textContent = calendarFormat(mm) + " 月";
		// 插入当前小时、分
		calendarDoc.getElementById("meizzHourHead").textContent = calendarShowTime ? (meizzTheHour + " 时") : "";
		calendarDoc.getElementById("meizzMinuteHead").textContent = calendarShowTime ? (meizzTheMinute + " 分") : "";
		calendarDoc.getElementById("meizzSecondHead").textContent = calendarShowTime ? (meizzTheSecond + " 秒") : "";
	}
}

/**
 * 年份的下拉框
 */
function tmpSelectYearInnerHTML(strYear) {
	if (strYear.match(/\D/) != null) {
		alert("年份输入参数不是数字！");
		return;
	}
	var m = (strYear) ? strYear : new Date().getFullYear();
	if (m < 1000 || m > 9999) {
		alert("年份值不在 1000 到 9999 之间！");
		return;
	}
	var n = m - 50;
	if (n < 1000) n = 1000;
	if (n + 101 > 9999) n = 9974;
	var s = '&nbsp;<select id="tmpSelectYear" name="tmpSelectYear" style="font-size: 12px" onblur="document.getElementById(\'tmpSelectYearLayer\').style.display=\'none\';" onchange="document.getElementById(\'tmpSelectYearLayer\').style.display=\'none\';parent.meizzTheYear = this.value; parent.meizzSetDay(parent.meizzTheYear,parent.meizzTheMonth);">';
	for ( var i = n; i < n + 101; i++) {
		if (i == m) s += '<option value="' + i + '" selected>' + i + '年</option>\r\n';
		else s += '<option value="' + i + '">' + i + '年</option>\r\n';
	}
	s += '</select>';
	var obj = calendarDoc.getElementById("tmpSelectYearLayer");
	obj.style.display = "";
	obj.innerHTML = s;
	calendarDoc.getElementById("tmpSelectYear").focus();
}

/**
 * 月份的下拉框
 */
function tmpSelectMonthInnerHTML(strMonth) {
	if (strMonth.match(/\D/) != null) {
		alert("月份输入参数不是数字！");
		return;
	}
	var m = (strMonth) ? strMonth : new Date().getMonth() + 1;
	var s = '&nbsp;&nbsp;&nbsp;<select id="tmpSelectMonth" name="tmpSelectMonth" style="font-size: 12px" onblur="document.getElementById(\'tmpSelectMonthLayer\').style.display=\'none\';" onchange="document.getElementById(\'tmpSelectMonthLayer\').style.display=\'none\'; parent.meizzTheMonth = this.value; parent.meizzSetDay(parent.meizzTheYear,parent.meizzTheMonth);">';
	for ( var i = 1; i < 13; i++) {
		if (i == m) s += '<option value="' + i + '" selected>' + i + '月</option>\r\n';
		else s += '<option value="' + i + '" >' + i + '月</option>\r\n';
	}
	s += '</select>';
	var obj = calendarDoc.getElementById("tmpSelectMonthLayer");
	obj.style.display = "";
	obj.innerHTML = s;
	calendarDoc.getElementById("tmpSelectMonth").focus();
}

/**
 * 小时的下拉框
 */
function tmpSelectHourInnerHTML(strHour) {
	if (!calendarShowTime) return;
	var m = (strHour) ? strHour : new Date().getHours();
	var s = '<select id="tmpSelectHour" name="tmpSelectHour" style="font-size: 12px" onblur="document.getElementById(\'tmpSelectHourLayer\').style.display=\'none\';" onchange="document.getElementById(\'tmpSelectHourLayer\').style.display=\'none\';parent.meizzTheHour = this.value; parent.evaSetTime(parent.meizzTheHour,parent.meizzTheMinute);">';
	for ( var i = 0; i < 24; i++) {
		if (i == m) s += '<option value="' + i + '" selected>' + i + '</option>\r\n';
		else s += '<option value="' + i + '" >' + i + '</option>\r\n';
	}
	s += "</select>";
	var obj = calendarDoc.getElementById("tmpSelectHourLayer");
	obj.style.display = "";
	obj.innerHTML = s;
	calendarDoc.getElementById("tmpSelectHour").focus();
}

/**
 *  分钟的下拉框
 */
function tmpSelectMinuteInnerHTML(strMinute) {
	if (!calendarShowTime) return;
	var m = (strMinute) ? strMinute : new Date().getMinutes();
	var s = '<select id="tmpSelectMinute" name="tmpSelectMinute" style="font-size: 12px" onblur="document.getElementById(\'tmpSelectMinuteLayer\').style.display=\'none\';" onchange="document.getElementById(\'tmpSelectMinuteLayer\').style.display=\'none\';parent.meizzTheMinute = this.value; parent.evaSetTime(parent.meizzTheHour,parent.meizzTheMinute);">';
	for ( var i = 0; i < 60; i++) {
		if (i == m) s += '<option value="' + i + '" selected>' + i + '</option>\r\n';
		else s += '<option value="' + i + '" >' + i + '</option>\r\n';
	}
	s += "</select>";
	var obj = calendarDoc.getElementById("tmpSelectMinuteLayer");
	obj.style.display = "";
	obj.innerHTML = s;
	calendarDoc.getElementById("tmpSelectMinute").focus();
}

/**
 * 秒的下拉框
 */
function tmpSelectSecondInnerHTML(strSecond) {
	if (!calendarShowTime) return;
	var m = (strSecond) ? strSecond : new Date().getMinutes();
	var s = '<select id="tmpSelectSecond" name="tmpSelectSecond" style="font-size: 12px" onblur="document.getElementById(\'tmpSelectSecondLayer\').style.display=\'none\';" onchange="document.getElementById(\'tmpSelectSecondLayer\').style.display=\'none\';parent.meizzTheSecond = this.value; parent.evaSetTime(parent.meizzTheHour,parent.meizzTheMinute,parent.meizzTheSecond);">';
	for ( var i = 0; i < 60; i++) {
		if (i == m) s += '<option value="' + i + '" selected>' + i + '</option>\r\n';
		else s += '<option value="' + i + '" >' + i + '</option>\r\n';
	}
	s += "</select>";
	var obj = calendarDoc.getElementById("tmpSelectSecondLayer");
	obj.style.display = "";
	obj.innerHTML = s;
	calendarDoc.getElementById("tmpSelectSecond").focus();
}

/**
 * 关闭日历
 */
function closeCalendar() {
	if (calendarFrame != null) calendarFrame.style.display = "none";
	if(calendarOutObject != null) {
		calendarOutObject = null;
		if(typeof(cocoform.verify) == "function") cocoform.verify(calendarOutObject);
	}
}

/**
 * 显示日历
 */
function showCalendar() {
	if (calendarFrame != null) calendarFrame.style.display = "";
}

/**
 * 判断是否闰平年
 */
function IsPinYear(year) {
	return (0 == year % 4 && ((year % 100 != 0) || (year % 400 == 0)));
}

/**
 * 闰年二月为29天
 */
function GetMonthCount(year, month) {
	var c = MonHead[month - 1];
	if ((month == 2) && IsPinYear(year)) c++;
	return c;
}

/**
 * 求某天的星期几
 */
function GetDOW(day, month, year) {
	return new Date(year, month - 1, day).getDay() / 7;
}

/**
 * 往前翻 Year
 */
function meizzPrevY() {
	if (meizzTheYear > 999 && meizzTheYear < 10000) meizzTheYear--;
	else alert("年份超出范围（1000-9999）！");
	meizzSetDay(meizzTheYear, meizzTheMonth);
}

/**
 * 往后翻 Year
 */
function meizzNextY() {
	if (meizzTheYear > 999 && meizzTheYear < 10000) meizzTheYear++;
	else alert("年份超出范围（1000-9999）！");
	meizzSetDay(meizzTheYear, meizzTheMonth);
}

function setNull() {
	calendarOutObject.value = '';
	closeCalendar();
}

/**
 * Today Button
 */
function meizzToday() {
	parent.meizzTheYear = new Date().getFullYear();
	parent.meizzTheMonth = new Date().getMonth() + 1;
	parent.meizzTheDate = new Date().getDate();
	parent.meizzTheHour = new Date().getHours();
	parent.meizzTheMinute = new Date().getMinutes();
	parent.meizzTheSecond = new Date().getSeconds();
	var meizzTheSecond = new Date().getSeconds();
	
	//格式化成两位数字
	if (meizzTheMonth < 10 && meizzTheMonth.length < 2) parent.meizzTheMonth = "0" + parent.meizzTheMonth;
	//格式化成两位数字
	if (parent.meizzTheDate < 10 && parent.meizzTheDate.length < 2) parent.meizzTheDate = "0" + parent.meizzTheDate;
	// meizzSetDay(meizzTheYear,meizzTheMonth);
	var val = calendarOutObject.value;
	if (calendarOutObject) {
		if (calendarShowTime) {
			calendarOutObject.value = parent.meizzTheYear + "-"
					+ calendarFormat(parent.meizzTheMonth) + "-"
					+ calendarFormat(parent.meizzTheDate) + " "
					+ calendarFormat(parent.meizzTheHour) + ":"
					+ calendarFormat(parent.meizzTheMinute) + ":"
					+ calendarFormat(parent.meizzTheSecond);
			// 注：在这里你可以输出改成你想要的格式
		} else {
			calendarOutObject.value = parent.meizzTheYear + "-"
					+ calendarFormat(parent.meizzTheMonth) + "-"
					+ calendarFormat(parent.meizzTheDate); // 注：在这里你可以输出改成你想要的格式
		}
		var len = calendarOutObject.maxLength;
		if (!isNaN(len)) {
			calendarOutObject.value = calendarOutObject.value.substr(0, Math.min(19, len));
		}
	}
	closeCalendar();
	if(val != calendarOutObject.value) {
		try {
			if(calendarOutObject.fireEvent) calendarOutObject.fireEvent("onchange"); 
			else {
				var evt = document.createEvent('HTMLEvents');
				evt.initEvent('change',true,true);
				t.dispatchEvent(evt);  
			}
		} catch (e) { }
	}
}

/**
 * 往前翻月份
 */
function meizzPrevM() {
	if (meizzTheMonth > 1) meizzTheMonth--;
	else {
		meizzTheYear--;
		meizzTheMonth = 12;
	}
	meizzSetDay(meizzTheYear, meizzTheMonth);
}

/**
 * 往后翻月份
 */
function meizzNextM() {
	if (meizzTheMonth == 12) {
		meizzTheYear++;
		meizzTheMonth = 1;
	} 
	else meizzTheMonth++;
	meizzSetDay(meizzTheYear, meizzTheMonth);
}

function meizzSetDay(yy, mm) {
	meizzWriteHead(yy, mm);
	// 设置当前年月的公共变量为传入值
	meizzTheYear = yy;
	meizzTheMonth = mm;
	for ( var i = 0; i < 37; i++) {
		meizzWDay[i] = "";
	}
	// 将显示框的内容全部清空
	var day1 = 1, day2 = 1, firstday = new Date(yy, mm - 1, 1).getDay(); // 某月第一天的星期几

	for (i = 0; i < firstday; i++) {
		 // 上个月的最后几天
		meizzWDay[i] = GetMonthCount(mm == 1 ? yy - 1 : yy, mm == 1 ? 12 : mm - 1) - firstday + i + 1;
	}
	var mc = GetMonthCount(yy, mm) + 1;
	for (i = firstday; day1 < mc; i++) {
		meizzWDay[i] = day1;
		day1++;
	}
	for (i = firstday + GetMonthCount(yy, mm); i < 37; i++) {
		meizzWDay[i] = day2;
		day2++;
	}
	for (i = 0; i < 37; i++) {
		var da = calendarDoc.getElementById('meizzDay' + i); // 书写新的一个月的日期星期排列
		if (meizzWDay[i] != "") {
			// 初始化边框
			da.borderColorLight = "#7ECEE2";
			da.borderColorDark = "#7ECEE2";
			da.style.color = "#2288AA";
			// 上个月的部分
			if (i < firstday) {
				da.innerHTML = "<b><font color=#BCBABC>" + meizzWDay[i] + "</font></b>";
				da.title = (mm == 1 ? 12 : mm - 1) + "月" + meizzWDay[i] + "日";
				da.onclick = function(event) {
					var text = event ? event.target.textContent : calendarFrame.contentWindow.event.srcElement.innerText;
					meizzDayClick(text,-1);
				};

				if (!calendarOutDate) da.style.backgroundColor = ((mm == 1 ? yy - 1 : yy) == new Date().getFullYear()
																&& (mm == 1 ? 12 : mm - 1) == new Date().getMonth() + 1 
																&& meizzWDay[i] == new Date().getDate()) ? "#5CEFA0" : "#F5F5F5";
				else {
					da.style.backgroundColor = ((mm == 1 ? yy - 1 : yy) == calendarOutDate.getFullYear()
												&& (mm == 1 ? 12 : mm - 1) == calendarOutDate.getMonth() + 1 
												&& meizzWDay[i] == calendarOutDate.getDate()) ? "#84C1FF" 
													: (((mm == 1 ? yy - 1 : yy) == new Date().getFullYear()
														&& (mm == 1 ? 12 : mm - 1) == new Date().getMonth() + 1 
														&& meizzWDay[i] == new Date().getDate()) ? "#5CEFA0" : "#F5F5F5");
					// 将选中的日期显示为凹下去
					if ((mm == 1 ? yy - 1 : yy) == calendarOutDate.getFullYear()
						&& (mm == 1 ? 12 : mm - 1) == calendarOutDate.getMonth() + 1
						&& meizzWDay[i] == calendarOutDate.getDate()) {
						da.borderColorLight = "#FFFFFF";
						da.borderColorDark = "#7ECEE2";
					}
				}
			} 
			// 下个月的部分
			else if (i >= firstday + GetMonthCount(yy, mm)) {
				da.innerHTML = "<b><font color=#BCBABC>" + meizzWDay[i] + "</font></b>";
				da.title = (mm == 12 ? 1 : mm + 1) + "月" + meizzWDay[i] + "日";
				da.onclick = function(event) {
					var text = event ? event.target.textContent : calendarFrame.contentWindow.event.srcElement.innerText;
					meizzDayClick(text,1);
				};
				if (!calendarOutDate)
					da.style.backgroundColor = ((mm == 12 ? yy + 1 : yy) == new Date()
							.getFullYear()
							&& (mm == 12 ? 1 : mm + 1) == new Date().getMonth() + 1 && meizzWDay[i] == new Date()
							.getDate()) ? "#5CEFA0" : "#F5F5F5";
				else {
					da.style.backgroundColor = ((mm == 12 ? yy + 1 : yy) == calendarOutDate
							.getFullYear()
							&& (mm == 12 ? 1 : mm + 1) == calendarOutDate
									.getMonth() + 1 && meizzWDay[i] == calendarOutDate
							.getDate()) ? "#84C1FF"
							: (((mm == 12 ? yy + 1 : yy) == new Date()
									.getFullYear()
									&& (mm == 12 ? 1 : mm + 1) == new Date()
											.getMonth() + 1 && meizzWDay[i] == new Date()
									.getDate()) ? "#5CEFA0" : "#F5F5F5");
					// 将选中的日期显示为凹下去

					if ((mm == 12 ? yy + 1 : yy) == calendarOutDate
							.getFullYear()
							&& (mm == 12 ? 1 : mm + 1) == calendarOutDate
									.getMonth() + 1
							&& meizzWDay[i] == calendarOutDate.getDate()) {
						da.borderColorLight = "#FFFFFF";
						da.borderColorDark = "#7ECEE2";
					}
				}
			} else {
				da.innerHTML = "<b>" + meizzWDay[i] + "</b>";
				da.title = mm + "月" + meizzWDay[i] + "日";
				// 给td赋予onclick事件的处理
				da.onclick = function(event) {
					var text = event ? event.target.textContent : calendarFrame.contentWindow.event.srcElement.innerText;
					meizzDayClick(text,0);
				};

				// 如果是当前选择的日期，则显示亮蓝色的背景；如果是当前日期，则显示暗黄色背景
				if (!calendarOutDate)
					da.style.backgroundColor = (yy == new Date().getFullYear()
							&& mm == new Date().getMonth() + 1 && meizzWDay[i] == new Date()
							.getDate()) ? "#5CEFA0" : "#F5F5F5";
				else {
					da.style.backgroundColor = (yy == calendarOutDate
							.getFullYear()
							&& mm == calendarOutDate.getMonth() + 1 && meizzWDay[i] == calendarOutDate
							.getDate()) ? "#84C1FF"
							: ((yy == new Date().getFullYear()
									&& mm == new Date().getMonth() + 1 && meizzWDay[i] == new Date()
									.getDate()) ? "#5CEFA0" : "#F5F5F5");
					// 将选中的日期显示为凹下去

					if (yy == calendarOutDate.getFullYear()
							&& mm == calendarOutDate.getMonth() + 1
							&& meizzWDay[i] == calendarOutDate.getDate()) {
						da.borderColorLight = "#FFFFFF";
						da.borderColorDark = "#7ECEE2";
					}
				}
			}
			da.style.cursor = "pointer";
		} 
		else {
			da.innerHTML = "";
			da.style.backgroundColor = "";
			da.style.cursor = "default";
		}
	}
}
/**
 * 点击显示框选取日期
 */
function meizzDayClick(n, ex) {
	parent.meizzTheDate = n;
	var yy = meizzTheYear;
	var mm = parseInt(meizzTheMonth) + ex; // ex表示偏移量，用于选择上个月份和下个月份的日期
	var hh = parseInt(meizzTheHour);
	if (isNaN(hh)) hh = 0;
	var mi = parseInt(meizzTheMinute);
	if (isNaN(mi)) mi = 0;
	var se = parseInt(meizzTheSecond);
	if (isNaN(se)) se = 0;
	// 判断月份，并进行对应的处理
	if (mm < 1) {
		yy--;
		mm = 12 + mm;
	} else if (mm > 12) {
		yy++;
		mm = mm - 12;
	}

	if (mm < 10) mm = "0" + mm;
	// 时
	if (hh < 10) hh = "0" + hh;
	// 分
	if (mi < 10) mi = "0" + mi;
	// 秒
	if (se < 10) se = "0" + se;
	if (calendarOutObject) {
		if (!n)return;
		if (n < 10) n = "0" + n;
		WriteDateTo(yy, mm, n, hh, mi, se);
		closeCalendar();
	}
}

// 格式化数字为两位字符表示
function calendarFormat(n) {
	// n = parseInt(n);
	n = new Number(n);
	if (isNaN(n)) n = 0;
	if (n < 10) return "0" + n;
	else return n;
}

/**
 * 设置用户选择的小时、分钟
 */
function evaSetTime() {
	calendarDoc.getElementById("meizzHourHead").innerText = meizzTheHour + " 时";
	calendarDoc.getElementById("meizzMinuteHead").innerText = meizzTheMinute + " 分";
	calendarDoc.getElementById("meizzSecondHead").innerText = meizzTheSecond + " 秒";
	WriteDateTo(meizzTheYear, meizzTheMonth, meizzTheDate, meizzTheHour, meizzTheMinute, meizzTheSecond);
}

/**
 * 设置时间控件为空
 */
function evaSetTimeNothing() {
	calendarDoc.getElementById("meizzHourHead").innerText = "";
	calendarDoc.getElementById("meizzMinuteHead").innerText = "";
	calendarDoc.getElementById("meizzSecondHead").innerText = "";
	WriteDateTo(meizzTheYear, meizzTheMonth, meizzTheDate, meizzTheHour, meizzTheMinute, meizzTheSecond);
}

/**
 * 设置时间控件为当前时间
 */
function evaSetTimeNow() {
	calendarDoc.getElementById("meizzHourHead").innerText = new Date().getHours() + " 时";
	calendarDoc.getElementById("meizzMinuteHead").innerText = new Date().getMinutes() + " 分";
	calendarDoc.getElementById("meizzSecondHead").innerText = new Date().getSeconds() + " 秒";
	meizzTheHour = new Date().getHours();
	meizzTheMinute = new Date().getMinutes();
	meizzTheSecond = new Date().getSeconds();
	WriteDateTo(meizzTheYear, meizzTheMonth, meizzTheDate, meizzTheHour, meizzTheMinute, meizzTheSecond);
}

function UseTime(ctl) {
	calendarShowTime = !calendarShowTime;
	if (calendarShowTime) {
		ctl.innerHTML = bImgSwitch();
		evaSetTime(); // 显示时间，用户原来选择的时间
		// evaSetTimeNow(); //显示当前时间
	} else {
		ctl.innerHTML = bImgSwitch();
		evaSetTimeNothing();
	}
}

function WriteDateTo(yy, mm, n, hh, mi, se) {
	if (calendarShowTime) {
		calendarOutObject.value = yy + "-" + calendarFormat(mm) + "-" + calendarFormat(n) + " "
				+ calendarFormat(hh) + ":" + calendarFormat(mi) + ":" + calendarFormat(se); // 注：在这里你可以输出改成你想要的格式
	} else {
		calendarOutObject.value = yy + "-" + calendarFormat(mm) + "-" + calendarFormat(n); // 注：在这里你可以输出改成你想要的格式
	}
	var len = calendarOutObject.maxLength;
	if (!isNaN(len) && len > 0) {
		calendarOutObject.value = calendarOutObject.value.substr(0, Math.min(19, len));
		if(typeof(cocoform.verify) == "function") cocoform.verify(calendarOutObject);
	}
	try {
		calendarOutObject.fireEvent("onchange");
	} catch (e) {
	}
}

function bImgSwitch() {
	if (calendarShowTime) return "开启";
	else return "关闭";
}
if(window.attachEvent) window.attachEvent("onload", buildCalendar);
else window.addEventListener("load", buildCalendar, true);
