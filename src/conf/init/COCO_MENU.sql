delete from COCO_ROLE_MENU;
delete from COCO_MENU;

insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('1', '0', '中日达生产管理系统',null,0,'0',1);

insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('10', '1', '采购管理',null,0,'10',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('1001', '10', '采购导入','/sino/cg/indexCgdr.do',0,'1',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('1002', '10', '采购登录','/sino/cg/indexCgdl.do',0,'2',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('1003', '10', '采购维护','/sino/cg/indexCgwh.do',0,'3',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('1004', '10', '采购余额表','/coco/report/load!cgyeTable.do',0,'4',1);

insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('20', '1', '原板进货管理',null,0,'20',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('2000', '20', '原板导入','/sino/ycai/indexYbdr.do',0,'1',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('2001', '20', '原板登录','/sino/ycai/indexYbdl.do',0,'2',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('2002', '20', '原板商品检查书导入','/sino/ycai/spjcs/indexDr.do',0,'3',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('2003', '20', '原板入库','/sino/ycai/indexYbrk.do',0,'4',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('2004', '20', '原板维护','/sino/ycai/index!1.do','0','5',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('2005', '20', '原板查看','/sino/ycai/index!2.do','0','6',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('2006', '20', '原板商品检查书查询','/sino/ycai/spjcs/index.do',0,'7',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('2007', '20', '原板情报对比表','/coco/report/load!ycqbdbbTable.do',0,'8',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('2008', '20', '原材履历表','/coco/report/load!ycllbTable.do',0,'9',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('2009', '20', '库位标签制作','/sino/dy/kwbq.do',0,'10',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('2010', '20', '原板标签打印','/sino/dy/ycai.do',0,'11',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('2011', '20', '设置入库时间','/sino/ycai/indexSzsj.do',0,'12',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('2012', '20', '原板成本管理','/sino/ycai/indexYbcb.do',0,'13',1);

insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('30', '1', '订货管理',null,0,'30',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3001', '30', '订货登录','/sino/dh/indexDhdl.do',0,'1',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3002', '30', '订货合同管理','/sino/dh/index.do',0,'2',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3003', '30', '结算条件登录','/sino/dh/indexJstj.do',0,'3',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3004', '30', '订货确认表打印','/sino/dh/indexDhqrb.do',0,'4',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3005', '30', '仕样未确认打印','/sino/dh/indexSywqr.do',0,'5',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3006', '30', '仕样确认','/sino/dh/indexSyqr.do',0,'6',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3007', '30', '订货合同完成确认','/sino/dh/indexDhwcqr.do',0,'7',1);


insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('31', '1', '营业管理',null,0,'31',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3101', '31', '基板订购单登录','/sino/yygl/jbdd/index.do',0,'1',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3102', '31', '制作基板订购单','/sino/yygl/jbdd/queryJbdd.do',0,'2',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3103', '31', '基板订购单查询','/sino/yygl/jbdd/indexJbdd.do',0,'3',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3104', '31', '次日出货联络单登录','/sino/yygl/chlld/index.do',0,'4',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3105', '31', '昨天出货实绩单','/sino/yygl/khyf/index.do',0,'5',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3106', '31', '次日出货联络单','/sino/yygl/chlld/indexChlld.do',0,'6',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3107', '31', '运费单价管理','/sino/yygl/khyfdj/index.do',0,'7',1);

insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('32', '1', '应收帐款管理',null,0,'32',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3201', '32', '出货实绩查询','/coco/report/load!chsjTable.do',0,'1',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3202', '32', '付款发票管理','/sino/hkgl/yszk/fpbj.do',0,'2',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3203', '32', '付款冲帐管理','/sino/hkgl/yszk/fkcz.do',0,'5',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3204', '32', '客户付款管理','/sino/hkgl/khfk/khfk.do',0,'3',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3205', '32', '客户余额查询','/coco/report/load!khyeTable.do',0,'4',1);

insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('33', '1', '在库分配',null,0,'33',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3301', '33', '余材化','/sino/zkfp/ych/index.do',0,'1',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3302', '33', '分配指示','/sino/zkfp/fp/indexYcai.do',0,'2',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3303', '33', '分配取消','/sino/zkfp/fp/indexJqc.do',0,'3',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3304', '33', '分配参考一览','/sino/zkfp/fp/fpckList.do',0,'4',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3305', '33', '分配结果一览','/coco/report/load!zkfpjg.do',0,'5',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('3306', '33', '分配错误一览','/sino/zkfp/fp/zkfpErrors.do',0,'6',1);

insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('40', '1', 'ETL生产',null,0,'40',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4001', '40', '指示书作成','/sino/zs/etl/indexZc.do',0,'1',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4002', '40', '指示书打印','/sino/zs/etl/indexDy.do',0,'2',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4003', '40', '钢卷缺陷打印','/sino/zs/etl/indexQxDy.do',0,'3',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4004', '40', '指示书分派','/sino/zs/etl/indexFp.do',0,'4',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4005', '40', '指示书待生产','/sino/zs/etl/indexDsc!1.do',0,'5',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4006', '40', '指示书待生产查看','/sino/zs/etl/indexDsc!2.do',0,'6',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4007', '40', '指示书已完成','/sino/zs/etl/indexYwc.do',0,'7',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4008', '40', '入侧','/sino/etl/rc/index.do',0,'8',1);	
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4009', '40', '实绩录入','/sino/etl/sj/sjlr.do',0,'9',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4010', '40', '实绩订正','/sino/etl/sj/sjdz.do',0,'10',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4011', '40', '实绩维护','/sino/etl/sj/query.do',0,'11',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4012', '40', '不良扣除联络单打印','/sino/etl/sj/indexLosscDy.do',0,'12',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4013', '40', 'ETL参数管理记录','/sino/etl/sj/indexCsgl.do',0,'13',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4014', '40', '采样记录维护','/sino/fxs/cyrz/index.do?type=etl',0,'14',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4015', '40', '生产日志','/coco/report/load!etlrz.do',0,'15',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4016', '40', 'ETL日产量一览明细','/coco/report/load!etlmxTable.do',0,'16',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4017', '40', 'ETL锡原单位明细表','/coco/report/load!etlxydwmxb.do',0,'17',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4018', '40', 'ETL锡原单位统计表(按月)','/sino/etl/sj/etlsy.do',0,'18',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4019', '40', '各班日产量','/coco/report/load!etlbanrz.do',0,'19',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4020', '40', '待生产切板制品','/sino/etl/sj/querysl.do',0,'20',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4021', '40', '垫木个数登录','/sino/etl/mg/toCreate.do',0,'21',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4022', '40', '垫木库存查看','/sino/etl/mg/index!1.do',0,'22',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4023', '40', 'ETL生产速报','/sino/etl/sj/etlsb.do',0,'23',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4024', '40', '指示书垫木设置','/sino/zs/etl/indexETLDm.do',0,'24',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4025', '40', '垫木库存日志','/coco/report/load!dmrj.do',0,'25',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4026', '40', '垫木库存移动','/sino/etl/mg/indexMove.do',0,'26',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4027', '40', '垫木库存查询','/sino/etl/mg/index!2.do',0,'27',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4028', '40', 'ETL速报登录','/sino/etl/sj/indexsbdl.do',0,'28',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('4029', '40', 'ETL停线登录','/sino/etl/sj/indexsstop.do',0,'29',1);

insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('50', '1', 'SL生产',null,0,'50',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('5001', '50', '指示书作成','/sino/zs/sl/indexJqc.do',0,'1',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('5002', '50', '指示书打印','/sino/zs/sl/indexDy.do',0,'2',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('5003', '50', '指示书紧急设置','/sino/zs/sl/indexSLFp.do',0,'3',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('5004', '50', '指示书待生产','/sino/zs/sl/indexWwc!1.do',0,'4',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('5005', '50', '指示书待生产查看','/sino/zs/sl/indexWwc!2.do',0,'5',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('5006', '50', '指示书已完成','/sino/zs/sl/indexYwc.do',0,'6',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('5007', '50', '实绩录入','/sino/sl/lr.do',0,'7',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('5008', '50', '实绩订正','/sino/sl/dz.do',0,'8',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('5009', '50', '实绩维护','/sino/sl/query.do',0,'9',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('5010', '50', '硬度录入','/sino/yd/query.do',0,'10',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('5011', '50', '采样记录维护','/sino/fxs/cyrz/index.do?type=sl',0,'11',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('5012', '50', '生产日志','/coco/report/load!slrz.do',0,'12',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('5013', '50', '各班日产量','/coco/report/load!slbanrz.do',0,'13',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('5014', '50', 'Coil步留计集','/coco/report/load!etlbl.do',0,'14',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('5015', '50', '指示书垫木设置','/sino/zs/sl/indexSLDm.do',0,'15',1);



insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('51', '1', '端板管理',null,0,'51',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('5101', '51', '端板登录','/sino/dbgl/toCreate.do',0,'1',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('5102', '51', '端板订正','/sino/dbgl/toDz.do',0,'2',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('5103', '51', '端板维护','/sino/dbgl/index.do',0,'3',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('5104', '51', '端板日产量','/coco/report/load!dbrz.do',0,'4',1);


insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('60', '1', 'SS生产',null,0,'60',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('6001', '60', 'PILE生成','/sino/ss/toCreate.do',0,'1',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('6002', '60', 'PILE消灭','/sino/ss/toDestroy.do',0,'2',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('6003', '60', 'PILE订正','/sino/ss/dz.do',0,'3',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('6004', '60', 'PILE维护','/sino/ss/query.do',0,'4',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('6005', '60', '分选紧急设置','/sino/ss/indexFP.do',0,'5',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('6006', '60', '分选检索','/sino/ss/index.do',0,'6',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('6007', '60', '步留明细','/coco/report/load!blmx.do',0,'7',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('6008', '60', '步留统计','/coco/report/load!bltj.do',0,'8',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('6009', '60', '分选日志','/sino/ss/rizhi.do',0,'9',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('6010', '60', '消灭日志','/coco/report/load!pilxmrz.do',0,'10',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('6011', '60', 'Pile生成日产量一览表','/coco/report/load!pilescrclyl.do',0,'11',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('6012', '60', 'Pile消灭日产量一览表','/coco/report/load!pilxmcrclyl.do',0,'12',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('6013', '60', 'Pile生成检查速报','/coco/report/load!pilehz.do',0,'13',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('6014', '60', 'PILE删除','/sino/ss/deleteindex.do',0,'14',1);


insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('70', '1', '捆包生产',null,0,'70',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('7001', '70', '设定紧急材','/sino/kb/sdjjc.do',0,'1',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('7002', '70', '待捆包制品检索','/sino/kb/queryDkb.do',0,'2',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('7003', '70', '捆包实绩','/sino/kb/indexKbsj.do',0,'3',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('7004', '70', '捆包历史','/sino/kb/indexKbls.do',0,'4',1);

insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('71', '1', '中控',null,0,'71',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('7101', '71', 'ETL实时品质管理','/sino/zk/indexPzgl.do',0,'1',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('7102', '71', 'ETL实时品质记录表','/sino/dy/indexPzjl.do',0,'2',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('7103', '71', 'ETL药液管理记录','/sino/zk/indexYygl.do',0,'3',1);

insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('72', '1', '分析室',null,0,'72',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('7201', '72', '采样记录维护','/sino/fxs/cyrz/index.do?type=fxs',0,'1',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('7202', '72', '马口铁分析数据表','/coco/report/load!mktfxsj.do',0,'2',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('7203', '72', '药液分析结果管理','/sino/fxs/yyfx/index.do',0,'3',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('7204', '72', '药液分析结果记录表','/coco/report/load!yyfxjg.do',0,'4',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('7205', '72', '分析作业日志登陆','/sino/fxs/fxzy/indexDl.do',0,'5',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('7206', '72', '分析作业日志管理','/sino/fxs/fxzy/index.do',0,'6',1);


insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('79', '1', '打印管理',null,0,'79',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('7901', '79', '原板标签打印','/sino/dy/ycai.do',0,'1',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('7902', '79', '服务卡打印','/sino/dy/zpk.do',0,'2',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('7903', '79', '制品标签打印','/sino/dy/zpbq.do',0,'3',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('7904', '79', '端板标签打印','/sino/dy/dbbq.do',0,'4',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('7905', '79', 'ETL指示书打印','/sino/dy/zss!1.do',0,'5',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('7906', '79', 'SL指示书打印','/sino/dy/zss!2.do',0,'6',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('7907', '79', '卷板缺陷表打印','/sino/dy/jbqxb.do',0,'7',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('7908', '79', '不良扣除联络单打印','/sino/dy/blkclld.do',0,'8',1);

insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('80', '1', '出货管理',null,0,'80',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('8001', '80', '今日出货联络单','/sino/ch/zxzs/chlld.do',0,'1',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('8002', '80', '制作装箱指示书','/sino/ch/zxzs/zzZxzs.do',0,'2',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('8003', '80', '装箱指示书管理','/sino/ch/zxzs/index.do',0,'3',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('8004', '80', '装箱对照','/sino/ch/zxdz/indexZxdz.do',0,'4',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('8005', '80', '设置发货制品车牌号','/sino/ch/zxdz/indexCp.do',0,'5',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('8006', '80', '送货单管理','/sino/ch/shd/index.do',0,'6',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('8007', '80', '制作包装清单','/sino/dy/chdy/pageZp.do',0,'7',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('8008', '80', '检查证明书管理','/sino/ch/jczms/index.do',0,'8',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('8009', '80', '投诉登录','/sino/ch/ts/indexTs.do',0,'9',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('8010', '80', '投诉记录管理','/sino/ch/ts/index.do',0,'10',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('8011', '80', '退货记录管理','/sino/ch/th/index.do',0,'11',1);

insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('89', '1', '异常处理',null,0,'89',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('8901', '89', '现品情报修正','/sino/yccl/indexXpqbxz.do',0,'1',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('8902', '89', '现品情报作成','/sino/yccl/indexXpqbzc.do',0,'2',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('8903', '89', '现品情报删除','/sino/yccl/indexXpqbsc.do',0,'3',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('8904', '89', '作业停止登录','/sino/yccl/indexZyzt.do',0,'4',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('8906', '89', '业连NO登录','/sino/yccl/indexYldl.do',0,'6',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('8907', '89', '堆场实绩维护','/sino/yccl/indexDcsj.do',0,'7',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('8908', '89', '现品保留登录','/sino/yccl/indexZyzpbl.do',0,'8',1);

insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('90', '1', '报表管理',null,0,'90',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9005', '90', '修正结果确认LIST','/coco/report/load!xzycTable.do',0,'5',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9009', '90', '堆场状况一览(ABX)','/coco/report/load!dczkylb1Table.do',0,'9',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9010', '90', '堆场状况一览(CDEFG)','/coco/report/load!dczkylb2Table.do',0,'9',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9011', '90', '堆场累计一览','/coco/report/load!dcljylbTable.do',0,'10',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9015', '90', 'SL日产量一览明细','/coco/report/load!slrclmx.do',0,'15',1);
--insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9016', '90', '2SL日产量一览明细','/coco/report/load!2slTable.do',0,'16',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9017', '90', '装入预定一览（明细版）','/coco/report/load!zrydyl.do',0,'17',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9019', '90', '生成结果确认LIST','/coco/report/load!scycTable.do',0,'19',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9020', '90', '删除结果确认LIST','/coco/report/load!shcycTable.do',0,'20',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9021', '90', '作业停止日志','/coco/report/load!zyycTable.do',0,'21',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9022', '90', '业连日志','/coco/report/load!ylycTable.do',0,'22',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9023', '90', '堆场日志','/coco/report/load!dcycTable.do',0,'23',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9024', '90', 'coil操业实绩(按月)','/coco/report/load!coilcysj.do',0,'24',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9026', '90', '成品仓库台帐','/coco/report/load!zpkctzTable.do',0,'25',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9027', '90', '订货进度表','/coco/report/load!dh!dhjd.do',0,'26',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9028', '90', '出货实绩明细','/coco/report/load!ch!chsjmx.do',0,'27',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9029', '90', '制品出库集计表','/coco/report/load!dh!ckzjb.do',0,'28',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9030', '90', '退货实绩明细','/coco/report/load!ch!thsjmx.do',0,'29',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9031', '90', '成品仓库台帐(年度)','/coco/report/load!zpkctzNdTable.do',0,'30',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9032', '90', '原材料仓库台帐','/coco/report/load!yckctzTable.do',0,'31',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9033', '90', '原材料仓库台帐(年度)','/coco/report/load!yckctzNdTable.do',0,'32',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9034', '90', '保税品在库清单','/coco/report/load!bspzkqdTable.do',0,'33',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9035', '90', '发货运费明细','/coco/report/load!ch!chyfmx.do',0,'34',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9036', '90', '发货保险费明细','/coco/report/load!ch!chbxf.do',0,'35',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9037', '90', '订货进度明细表','/sino/dh/indexDhjdmx.do',0,'35',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9038', '90', '原板成本明细','/coco/report/load!ybcbmxTable.do',0,'36',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9039', '90', '硬度检查统计','/coco/report/load!slydjl.do',0,'37',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9040', '90', '保留品统计','/coco/report/load!blzptj.do',0,'38',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9041', '90', '垫木库存比较','/coco/report/load!dmkzbj.do',0,'39',1);


insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('91', '1', '库存管理',null,0,'90',1);
insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('9104', '91', '制品入库','/sino/kcgl/indexZprk.do',0,'4',1);

insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('92', '1', '资料室',null,0,'90',1);
insert into COCO_MENU (ID_,PARENT_,NAME_,URL_,POPUP_, ORDER_,VALID_) values('9201', '92', '目录管理','/sys/directory/index.do',0,'1',1);
insert into COCO_MENU (ID_,PARENT_,NAME_,URL_,POPUP_, ORDER_,VALID_) values('9202', '92', '资料管理','/sino/cmn/inform/index.do',0,'2',1);
insert into COCO_MENU (ID_,PARENT_,NAME_,URL_,POPUP_, ORDER_,VALID_) values('9203', '92', '资料阅读管理','/sino/cmn/inform/index!view.do',0,'3',1);
insert into COCO_MENU (ID_,PARENT_,NAME_,URL_,POPUP_, ORDER_,VALID_) values('9204', '92', '导入文件管理','/sino/cmn/inform/index!dr.do',0,'4',1);

insert into COCO_MENU (id_,parent_,name_,url_,popup_, order_,valid_) values('98', '1', '主文件管理',null,0,'98',1);
insert into COCO_MENU (ID_,PARENT_,NAME_,URL_,POPUP_, ORDER_,VALID_) values('9802', '98', '用户主文件','/sino/cmn/yong/index.do',0,'2',1);
insert into COCO_MENU (ID_,PARENT_,NAME_,URL_,POPUP_, ORDER_,VALID_) values('9803', '98', '仕样主文件','/sino/cmn/zzsy/index.do',0,'3',1);
insert into COCO_MENU (ID_,PARENT_,NAME_,URL_,POPUP_, ORDER_,VALID_) values('9804', '98', '短语信息库','/sino/cmn/datainfo/index.do',0,'4',1);
insert into COCO_MENU (ID_,PARENT_,NAME_,URL_,POPUP_, ORDER_,VALID_) values('9805', '98', '生产线别配置','/sino/cmn/scxb/index.do',0,'5',1);
insert into COCO_MENU (ID_,PARENT_,NAME_,URL_,POPUP_, ORDER_,VALID_) values('9806', '98', '制品查看','/sino/cmn/zp/view.do',0,'6',1);

insert into COCO_MENU (ID_,PARENT_,NAME_,URL_,POPUP_, ORDER_,VALID_) values('99', '1', '系统管理',null,0,'99',1);
insert into COCO_MENU (ID_,PARENT_,NAME_,URL_,POPUP_, ORDER_,VALID_) values('9901', '99', '机构管理','/sys/dept/index.do',	0,	'1',	1);
insert into COCO_MENU (ID_,PARENT_,NAME_,URL_,POPUP_, ORDER_,VALID_) values('9902', '99', '员工管理','/sys/person/index.do',	0,	'2',	1);
insert into COCO_MENU (ID_,PARENT_,NAME_,URL_,POPUP_, ORDER_,VALID_) values('9903', '99', '用户管理','/sys/user/index.do',	0,	'3',	1);
insert into COCO_MENU (ID_,PARENT_,NAME_,URL_,POPUP_, ORDER_,VALID_) values('9904', '99', '岗位管理','/sys/post/index.do',	0,	'4',	1);
insert into COCO_MENU (ID_,PARENT_,NAME_,URL_,POPUP_, ORDER_,VALID_) values('9905', '99', '权限管理','/sys/role/index.do',	0,	'5',	1);
insert into COCO_MENU (ID_,PARENT_,NAME_,URL_,POPUP_, ORDER_,VALID_) values('9906', '99', '功能管理','/sys/menu/index.do',	0,	'6',	1);
insert into COCO_MENU (ID_,PARENT_,NAME_,URL_,POPUP_, ORDER_,VALID_) values('9907', '99', '资源管理','/sys/source/index.do',	0,	'7',	1);
insert into COCO_MENU (ID_,PARENT_,NAME_,URL_,POPUP_, ORDER_,VALID_) values('9908', '99', '代码管理','/sys/code/index.do',	0,	'8',	1);
insert into COCO_MENU (ID_,PARENT_,NAME_,URL_,POPUP_, ORDER_,VALID_) values('9909', '99', '序号管理','/sys/seq/index.do',	0,	'9',	1);
insert into COCO_MENU (ID_,PARENT_,NAME_,URL_,POPUP_, ORDER_,VALID_) values('9910', '99', '转换配置','/sys/convert/index.do',	0,	'A',	1);