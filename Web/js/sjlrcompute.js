//异常定位
		function exceptionPosition(code,mode){
			var form;
		  if(mode=="S"){
			form=document.forms["JcForm"];
		  }
		  if(mode=="C"){
			form=document.forms["JzForm"];
		  }
		    if(code==-1){
				form.elements["stat1"].focus();
			}
		   if(code==-2){
				form.elements["sczm"].focus();
			}
			if(code==-3){
				form.elements["scfm"].focus();
			} 
			if(code==-4){
					form.elements["stat2"].focus();
				}
			if(code==-5){
					form.elements["jhou"].focus();
				}
			if(code==-6){
				  if(mode=="S"){
				     form.elements["jbcn"].focus();
				  }
				  if(mode=="C"){
					 form.elements["sjzl"].focus();
				  }
			}
			if(code==-9){
				form.elements["losq"].focus();
			}
			if(code==-11){
				form.elements["chan"].focus();
			}
			if(code==-12){
				form.elements["deng"].focus();
			}
			if(code==-13){
				form.elements["czdm"].focus();
			}
			if(code==-14){
				form.elements["zpzl"].focus();
			}
			if(code==-15){
				form.elements["jdyn"].focus();
			}
			if(code==-16){
				form.elements["jsyn"].focus();
			}
			if(code==-17){
				form.elements["ddno"].focus();
			}
			if(code==-20){
				form.elements["rjet"].focus();
			}
			if(code==-21){
				form.elements["qqdm"].focus();
			}
			if(code==-22){
				form.elements["jsno"].focus();
			}
			if(code==-23){
				form.elements["ban"].focus();
			}
		}
		
		// 清空Form
		function doQk(type) {
			 var form=null;
			 var dataForm=document.forms["DataForm"];
			 var mode=dataForm.elements["mode"].value;
			 if(mode=='S'){
				 form=document.forms["JcForm"];
			 }
			 if(mode=='C'){
				 form=document.forms["JzForm"];
			 }
			var jbno=form.elements["jbno"].value;
			var fuzm=form.elements["fuzm"].value;
			var fufm=form.elements["fufm"].value; 
			var houu=form.elements["houu"].value ;
			var kuan=form.elements["kuan"].value;
			var stat1=form.elements["stat1"].value ;
			var stat2=form.elements["stat2"].value ;
			var jhou=form.elements["jhou"].value;
			var sczm=form.elements["sczm"].value;
			var scfm=form.elements["scfm"].value;
			var zrjb=form.elements["zrjb"].value;
			var zsno=form.elements["zsno"].value;
			var dhno=form.elements["dhno"].value;
			var sjzl=form.elements["sjzl"].value;
			var mode=form.elements["mode"].value;
			var elin=form.elements["elin"].value;
			var ban=form.elements["ban"].value;
			var zu=form.elements["zu"].value;
			var isfp=form.elements["isfp"].value;
			form.reset();
			form.elements["jbno"].value =jbno;
			form.elements["fuzm"].value =fuzm;
			form.elements["fufm"].value =fufm;
			form.elements["houu"].value =houu;
			form.elements["kuan"].value =kuan;
			if(type!='all'){
				form.elements["stat1"].value=stat1;
				form.elements["stat2"].value=stat2;
				form.elements["jhou"].value=jhou;
				form.elements["sczm"].value=sczm;
				form.elements["scfm"].value=scfm;
			}
			form.elements["zrjb"].value=zrjb;
			form.elements["dhno"].value=dhno;
		    form.elements["mode"].value=mode;
		    form.elements["elin"].value=elin;
		    form.elements["zsno"].value=zsno;
		    form.elements["ban"].value=ban;
			form.elements["zu"].value=zu;
			form.elements["isfp"].value=isfp;
			form.elements["stat2"].disabled = false;
			form.elements["jhou"].disabled = false;	
			form.elements["stat1"].disabled = false;
			form.elements["sczm"].disabled = false;
			form.elements["scfm"].disabled = false;	
		}
		
		// 查看指示：附页、业务、生产备注
		function doViewZs() {
			//指示书号
			 var dataForm=document.forms["DataForm"];
			 var jbno=dataForm.elements["jbno"].value;
			if(jbno==""){
				alet("卷板NO不能为空");
				return ;
			}
			var ajax = new Cocoajax();
			ajax.dataDiv = document.getElementById("ViewArea");
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				coco.showPage('View',{center:true,top:50,width:"80%"});
			};
			ajax.post("viewzs!"+jbno+".do");
		}
		// 查看原材：附页、业务、生产备注
		function doViewKNYL() {
			//指示书号
			 var dataForm=document.forms["DataForm"];
			 var jbno=dataForm.elements["jbno"].value;
			if(jbno==""){
				alet("卷板NO不能为空");
				return ;
			}
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					//coco.alert(this.msg);
					return;
				}
			
				doViewZs();
				
			};
			ajax.post("getYLKNS!"+jbno+".do");
		}
		//阅读附件
		function view(ylno) {
			var height = screen.availHeight;
		  	var width = screen.availWidth;
			window.open(path + "/sino/cmn/inform/viewAttach.do?name="+ylno, "查看文件","left=0,top=0,height="+height+",width="+width+",status=0,toolbar=no,menubar=no,location=no");
		}
		//检查附着量
		function checkFu(){
			
			 var dataForm=document.forms["DataForm"];
		
			 var form=null;
			 var mode=document.getElementById("mode").value;
			 if(mode=='S'){
				 form=document.forms["JcForm"];
			 }
			 if(mode=='C'){
				 form=document.forms["JzForm"];
			 }
			 var sczm=form.elements["sczm"].value;
			 var scfm=form.elements["scfm"].value;
			 var dhno=form.elements["dhno"].value;
			 var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						 var msg= new Array(); 
						  msg=this.msg.split('&');
						  alert(msg[0]);
						  exceptionPosition(this.code,mode);	
						return;
					}
					
				};
				var key=coco.parseParams([{name : "dhno",value: dhno},
				                          {name : "sczm",value: sczm},
				                          {name : "scfm",value: scfm}
				]);
				ajax.post("checkFu.do", key, "POST");
			 
		}
		//显示出货重量
		function showCHZL(){
			 var form=null;
			 var dataForm=document.forms["DataForm"];
			 var mode=document.getElementById("mode").value;
			 if(mode=='S'){
				 form=document.forms["JcForm"];
			 }
			 if(mode=='C'){
				 form=document.forms["JzForm"];
			 }
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				var values=this.result.split(',');
				form.elements["zpzl"].value =values[0];
				form.elements["jbcn"].value =values[1];
			};
			var zsno, jbcn, cutc, loss, losc2, sjzl, chan;
			zsno = form.elements["zsno"].value;
			jbcn = form.elements["jbcn"].value;
			jbcn = jbcn != null && jbcn.length > 0 ? jbcn : 0;
			cutc = form.elements["cutc"].value;
			cutc = cutc != null && cutc.length > 0 ? cutc : 0;
			loss = form.elements["losc"].value;
			loss = loss != null && loss.length > 0 ? loss : 0;
			loss2 = form.elements["losc2"].value;
			loss2 = loss2 != null && loss2.length > 0 ? loss2 : 0;
			chan = form.elements["chan"].value;
		    sjzl = form.elements["sjzl"].value;
			var postContent = coco.parseParams([{name : "zsno",value: zsno},{name : "sjzl",value: sjzl},
			                           {name : "jbcn",value: jbcn},{name : "cutc",value: cutc},
			                           {name : "loss",value: loss},{name : "loss2",value: loss2},{name : "chan",value: chan},{name : "formTye",value: mode}]);
			ajax.post("getZpzl.do", postContent, "POST");
		}