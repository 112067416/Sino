       //打印制品标签
		function printZpbq(jbno) {
			if(jbno == null || jbno.length == 0) {
				alert("请指定一个制品No.");
				return false;
			}
			var index = 0;
			var ajax = new Cocoajax();
			var LODOP = document.getElementById("lodop");
			ajax.callback = function() {
				if(this.code < 0) coco.alert(this.msg);
				toPrintZpbq(LODOP, this.result);
			};
			ajax.post(path + "/sino/dy/zpbq!print.do", coco.parseParam("jbno", jbno));
		}
		
		function toPrintZpbq(LODOP, result) {
//			LODOP.PRINT_INIT("ZPBQ");
//			LODOP.SET_PRINTER_INDEXA("ZPBQ");
			LODOP.PRINT_INIT("ZPBQ");
			LODOP.SET_PRINTER_INDEXA("ZPBQ");
			LODOP.SET_PRINT_PAGESIZE(2,"0","0","A4");
			LODOP.ADD_PRINT_HTM(2, -13, 1100, 760, result);
			//ADD_PRINT_BARCODE(Top, Left,Width, Height, CodeType, CodeValue)
			var start = result.indexOf('<table no="');
			var end = result.indexOf('" ');
			var no1 = null, no2 = null;
			if(start != -1 && end != -1) {
				var nos = result.substring(start + 11, end).split(";");
				if(nos[0] != "") no1 = nos[0];
				if(nos[1] != "") no2 = nos[1];
			}
			if(no1 != null) {
				LODOP.ADD_PRINT_BARCODE("230px", "300px", "200px", "60px", "code39", no1);
				LODOP.ADD_PRINT_BARCODE("245px", "800px", "200px", "45px", "code39", no1);
				LODOP.ADD_PRINT_BARCODE("625px", "300px", "200px", "60px", "code39", no1);
				LODOP.ADD_PRINT_BARCODE("635px", "800px", "200px", "45px", "code39", no1);
			}
			if(no2 != null) {
				LODOP.ADD_PRINT_BARCODE("320px", "800px", "200px", "45px", "code39", no2);
				LODOP.ADD_PRINT_BARCODE("710px", "800px", "200px", "45px", "code39", no2);
			}
			//打印预览
			//LODOP.PREVIEW();
			//打印
			LODOP.PRINT();	
		}
		
		//打印制品卡
		function printZpk(jbno,pzno) {
			if(jbno == null || jbno.length == 0) {
				alert("请指定一个制品No.");
				return false;
			}
			var postContent = coco.parseParam("jbnos", jbno);
			//调用打印控件进行套打
			var LODOP = document.getElementById("lodop");
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code < 0) {
					coco.alert(this.msg);
					return;
				}
				//返回js对象
				var obj = null;
				eval("obj = "+this.result);
				if(obj == null || obj.datas == null) {
					alert("返回错误信息");
					return;
				}
				if(obj.datas.length == 0) {
					alert("没有数据");
					return;
				}
				toPrintZpk(LODOP, obj.datas[0]);
				if(pzno == '2') {
					printZpbq(jbno);
				}
			};
			ajax.post(path + "/sino/dy/zpk!print.do", postContent);
		}

		function toPrintZpk(LODOP, data) {
			LODOP.PRINT_INIT("ZPK");
			LODOP.SET_PRINTER_INDEXA("DPK510");
			LODOP.SET_PRINT_PAGESIZE(1,2000,1397,"A4");
			LODOP.SET_PRINT_STYLE("FontSize",11);
			LODOP.SET_PRINT_STYLE("Bold",0);
			//制品号
			var zpnos = data.zpno.split("-");
			LODOP.ADD_PRINT_TEXT(40, 80, 40, 15, zpnos[0].length == 1 ? " " + zpnos[0] : zpnos[0]);
			LODOP.ADD_PRINT_TEXT(40, 125, 120, 15, zpnos[1]);
			if(data.date != null)LODOP.ADD_PRINT_TEXT(40, 275, 100, 10, data.date);
			if(data.packageNo != null) LODOP.ADD_PRINT_TEXT(72, 125, 150, 10, data.packageNo);
			if(data.zsno != null) LODOP.ADD_PRINT_TEXT(76, 455, 100, 10, data.zsno);
			if(data.bzxs != null) LODOP.ADD_PRINT_TEXT(76, 558, 80, 10, data.bzxs);
			if(data.w != null) LODOP.ADD_PRINT_TEXT(76, 620, 80, 10, data.w);
			if(data.dhno != null) LODOP.ADD_PRINT_TEXT(105, 80, 120, 10, data.dhno);
			if(data.dhline != null) {
				LODOP.ADD_PRINT_TEXT(105, 135, 40, 10, " - ");
				LODOP.ADD_PRINT_TEXT(105, 155, 40, 10, data.dhline);
			}
			if(data.yhlc != null) LODOP.ADD_PRINT_TEXT(110, 440, 120, 10, data.yhlc);
			if(data.yhdm != null) LODOP.ADD_PRINT_TEXT(110, 547, 60, 10, data.yhdm);
			if(data.clm != null) LODOP.ADD_PRINT_TEXT(110, 605, 40, 10, data.clm);
			if(data.dj != null) LODOP.ADD_PRINT_TEXT(110, 630, 40, 10, data.dj);
			if(data.zqx != null) LODOP.ADD_PRINT_TEXT(110, 665, 40, 10, data.zqx);
			if(data.piler != null) LODOP.ADD_PRINT_TEXT(110, 690, 30, 10, data.piler);
			if(data.face != null) LODOP.ADD_PRINT_TEXT(170, 55, 150, 10, data.faceK + " (" + data.face + ")");
			if(data.djl != null) LODOP.ADD_PRINT_TEXT(170, 230, 150, 10, data.djl);
			if(data.pzdj != null) LODOP.ADD_PRINT_TEXT(170, 375, 150, 10, data.pzdj);
			if(data.temper != null) LODOP.ADD_PRINT_TEXT(170, 500, 150, 10, data.temper);
			if(data.size != null) LODOP.ADD_PRINT_TEXT(215, 90, 300, 10, data.xpho + " ×" + data.xpkn + "w ×" + data.xpcn);
			if(data.packageNoBz != null) LODOP.ADD_PRINT_TEXT(215, 490, 300, 10, data.packageNoBz);
			if(data.pinhole != null) LODOP.ADD_PRINT_TEXT(265, 320, 30, 10, data.pinhole);
			if(data.dl != null) LODOP.ADD_PRINT_TEXT(265, 360, 100, 10, data.dl);
			if(data.bzzs != null) LODOP.ADD_PRINT_TEXT(265, 510, 100, 10, data.bzzs);
			if(data.zl != null) LODOP.ADD_PRINT_TEXT(265, 655, 100, 10, data.zl);
			if(data.jdy != null) LODOP.ADD_PRINT_TEXT(315, 540, 50, 10, data.jdy);
			if(data.jsy != null) LODOP.ADD_PRINT_TEXT(315, 655, 50, 10, data.jsy);
			LODOP.ADD_PRINT_BARCODE("320px", "70px", "300px", "60px", "Code93", zpnos[1]);
			if(data.duic != null && data.duic == 'E') LODOP.ADD_PRINT_BARCODE("400px", "70px", "300px", "60px", "Code93", zpnos[1]);
			//打印预览
			//LODOP.PREVIEW();
			//打印
			LODOP.PRINT();	
		}
		// 打印不良扣除联络单
		function doDyLossc(mode) {
			var jbno, losc, losc2;
			if(mode == "S") {
				form = document.forms["JcForm"];
			}
			if(mode == "C") {
				form = document.forms["JzForm"];
			}
		   jbno = form.elements["jbno"].value;
		   losc = form.elements["losc"].value;
		   losc2 = form.elements["losc2"].value;
		   if((losc != null && losc.length > 0) || (losc2 != null && losc2.length > 0)) {
			var postContent = coco.parseParams([{name:"jbno",value:jbno}]);
			var ajax = new Cocoajax();
			ajax.dataDiv = LosscArea;
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				coco.showPage('LosscView',{center:true,top:50,width:"80%"});
			};
			ajax.post(path + "/sino/dy/lossc.do", postContent);
		 }
	  }
		
	//打印不良扣除联络单
	function printLoss(jbno, total) {
		var LODOP = document.getElementById("lodop");
		var el1 = document.getElementById("lxx");
		var lxx = el1.checked ? "Y" : "N";
		var el2 = document.getElementById("jdx");
		var jdx = el2.checked ? "Y" : "N";
		var el3 = document.getElementById("lx");
		var lx = el3.checked ? "Y" : "N";
		var index = 1;
		var postContent = coco.parseParams([{name:"jbno",value:jbno},{name:"lxx",value:lxx},{name:"jdx",value:jdx},{name:"lx",value:lx},{name:"index",value:1}]);
		var ajax = new Cocoajax();
		ajax.callback = function() {
			if(this.code <= 0){
				coco.alert(this.msg);
				return;
			}
			var obj = null;
			eval("obj = "+this.result + ";");
			doPrintLoss(obj, LODOP);
			if(total == 2) {
				total = 1;
				el1 = document.getElementById("lxx2");
				lxx = el1.checked ? "Y" : "N";
				el2 = document.getElementById("jdx2");
				jdx = el2.checked ? "Y" : "N";
				el3 = document.getElementById("lx2");
				lx = el3.checked ? "Y" : "N";
				postContent = coco.parseParams([{name:"jbno",value:jbno},{name:"lxx",value:lxx},{name:"jdx",value:jdx},{name:"lx",value:lx},{name:"index",value:2}]);
				ajax.post(path + "/sino/dy/lossc!print.do", postContent);
			}
			coco.hidePage('LosscView');
			cocopage.refresh();
		};
		ajax.post(path + "/sino/dy/lossc!print.do", postContent);
	}
	//打印不良扣除联络单
	function doPrintLoss(data, LODOP) {
		LODOP.PRINT_INIT("LOSQ");
		//LODOP.SET_PRINTER_INDEXA("lossdan");
		LODOP.SET_PRINTER_INDEXA("lossdan");
		LODOP.SET_PRINT_PAGESIZE(1,2000,1397,"A4");
		LODOP.SET_PRINT_STYLE("FontSize",12);
		LODOP.SET_PRINT_STYLE("Bold",0);
		
		if(data.upda != null)LODOP.ADD_PRINT_TEXT(100, 105, 200, 10, data.upda);
		if(data.jbno != null) LODOP.ADD_PRINT_TEXT(100, 530, 350, 10, data.zzsd + '-' + data.jbno);
		if(data.name != null) LODOP.ADD_PRINT_TEXT(128, 85, 600, 10, data.name);
		if(data.losq != null) LODOP.ADD_PRINT_TEXT(155, 130, 300, 10, data.losq);
		if(data.losc != null) LODOP.ADD_PRINT_TEXT(155, 575, 120, 10, data.losc);
		if(data.lxx != null) LODOP.ADD_PRINT_TEXT(180, 184, 60, 10, data.lxx);
		if(data.jdx != null) LODOP.ADD_PRINT_TEXT(180, 385, 60, 10, data.jdx);
		if(data.lx != null) LODOP.ADD_PRINT_TEXT(180, 606, 60, 10, data.lx);
		LODOP.ADD_PRINT_TEXT(257, 25, 60, 10, 0);
		if(data.jbcn != null) LODOP.ADD_PRINT_TEXT(257, 655, 100, 10, data.jbcn);
		//打印预览
		//LODOP.PREVIEW();
		//打印
		LODOP.PRINT();	
	}