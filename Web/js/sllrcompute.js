//异常定位
		function exceptionPosition(code){
			
			var form;	
			form=document.forms["DataForm"];
		    if(code==-1){
				form.elements["zl"].focus();
			}
		   if(code==-2){
				form.elements["zz"].focus();
			}
			if(code==-3){
				form.elements["jh"].focus();
			} 
			if(code==-4){
					form.elements["sjzl"].focus();
				}
			if(code==-5){
					form.elements["chan"].focus();
				}
			if(code==-12){
				form.elements["deng"].focus();
			}
			
			if(code==-7){
				form.elements["czdm"].focus();
			}
			if(code==-8){
				form.elements["qqdm"].focus();
			}
			if(code==-9){
				form.elements["qqd2"].focus();
			}
			if(code==-10){
				form.elements["qqd3"].focus();
			}
			if(code==-11){
				form.elements["jdyn"].focus();
			}
			if(code==-6){
				form.elements["jsyn"].focus();
			}
			if(code==-13){
				form.elements["zshu"].focus();
			}
			if(code==-14){
				form.elements["dmrk"].focus();
			}
			if(code==-15){
				form.elements["cqpl"].focus();
			}
			if(code==-16){
				form.elements["ddno"].focus();
			}
			if(code==-17){
				form.elements["sckn"].focus();
			}
			if(code==-18){
				form.elements["jdcn"].focus();
			}
			if(code==-19){
				form.elements["lnsd"].focus();
			}
			if(code==-20){
				form.elements["rjet"].focus();
			}
			if(code==-21){
				form.elements["cm05"].focus();
			}
			if(code==-22){
				form.elements["cp00"].focus();
			}
			if(code==-23){
				form.elements["cp05"].focus();
			}
			if(code==-24){
				form.elements["cp10"].focus();
			}
			if(code==-25){
				form.elements["cp15"].focus();
			}
			if(code==-26){
				form.elements["bopg"].focus();
			}
			if(code==-27){
				form.elements["bdrg"].focus();
			}
			if(code==-28){
				form.elements["bdji"].focus();
			}
			if(code==-29){
				form.elements["zopf"].focus();
			}
			if(code==-30){
				form.elements["zdrf"].focus();
			}
			if(code==-31){
				form.elements["zndz"].focus();
			}
			if(code==-32){
				form.elements["hndz"].focus();
			}
			if(code==-33){
				form.elements["zbog"].focus();
			}
			if(code==-34){
				form.elements["qduz"].focus();
			}
			if(code==-35){
				form.elements["maos"].focus();
			}
			if(code==-36){
				form.elements["maox"].focus();
			}
			if(code==-37){
				form.elements["siql"].focus();
			}
			if(code==-38){
				form.elements["jmjc"].focus();
			}
			if(code==-39){
				form.elements["dmqr"].focus();
			}
			if(code==-40){
				form.elements["zkqr"].focus();
			}
			if(code==-41){
				form.elements["dbbj"].focus();
			}
			if(code==-42){
				form.elements["ying"].focus();
			}
			if(code==-43){
				form.elements["ban"].focus();
			}
		}
		//检查实测宽和实测剪断长
		function checkKuanCang(){
			
			 var dataForm=document.forms["DataForm"];
		
			 var sckn=dataForm.elements["sckn"].value;
			 var jdcn=dataForm.elements["jdcn"].value;
			 var zsno=dataForm.elements["zsno"].value;
			 var ajax = new Cocoajax();
				ajax.callback = function() {
					if(this.code <= 0) {
						 var msg= new Array(); 
						  msg=this.msg.split('&');
						  alert(msg[0]);
						  exceptionPosition(this.code);	
						return;
					}
					
				};
				var key=coco.parseParams([{name : "sckn",value: sckn},
				                          {name : "jdcn",value: jdcn},
				                          {name : "zsno",value: zsno}
				]);
				ajax.post("checkKuanCang.do", key, "POST");
			 
		}
		//显示计算重量
		function showJSZL(){
			
			 var dataForm=document.forms["DataForm"];
			
			var ajax = new Cocoajax();
			ajax.callback = function() {
				if(this.code <= 0) {
					coco.alert(this.msg);
					return;
				}
				
				dataForm.elements["jszl"].value =this.result;
				
			};
			var zsno=dataForm.elements["zsno"].value;
			var zshu=dataForm.elements["zshu"].value;
			if(zshu==""){
				zshu=0;
			}
			 var key=coco.parseParams([{name : "zsno",value: zsno},
			                           {name : "zshu",value: zshu}
			                   
			 ]);
			ajax.post("getJszl.do", key, "POST");
		}
		// 查看原材：附页、业务、生产备注
		function doViewKNYL() {
			//指示书号
			 var dataForm=document.forms["LeadForm"];
			 var jbno=dataForm.elements["rczpno"].value;
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
		// 查看指示：附页、业务、生产备注
		function doViewZs() {
			
			//指示书号
			 var dataForm=document.forms["LeadForm"];
			 var jbno=dataForm.elements["rczpno"].value;
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