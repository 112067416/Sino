--转换配置生成
delete from COCO_CONVERT_FIELD;
delete from COCO_CONVERT_ENTITY;

insert into COCO_CONVERT_ENTITY( ID_, FIRST_, LENGTH_, NAME_, TITLE_ROW_, TYPE_) values('JchaTp',0,0,'原板检查证书导入',0,'0');
insert into COCO_CONVERT_ENTITY( ID_, FIRST_, LENGTH_, NAME_, TITLE_ROW_, TYPE_) values('Wwhttp',0,0,'供应商合同导入',0,'0');
insert into COCO_CONVERT_ENTITY( ID_, FIRST_, LENGTH_, NAME_, TITLE_ROW_, TYPE_) values('Ybrk',0,0,'原板入库',0,'1');
insert into COCO_CONVERT_ENTITY( ID_, FIRST_, LENGTH_, NAME_, TITLE_ROW_, TYPE_) values('Ycaitp',0,0,'原板清单导入',0,'0');
insert into COCO_CONVERT_ENTITY( ID_, FIRST_, LENGTH_, NAME_, TITLE_ROW_, TYPE_) values('Zprk',0,0,'制品入库',0,'1');

insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0001','htno','供应商合同-行号','Wwhttp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0002','zzsd','制造商','Wwhttp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0003','face','表面','Wwhttp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0004','houu','尺寸.厚','Wwhttp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0005','kuan','尺寸.宽','Wwhttp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0006','htzl','重量','Wwhttp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0007','htdj','单价','Wwhttp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0008','zzgg','制造商规格略称','Wwhttp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0009','abbr','用户略称','Wwhttp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0010','pzno','品种','Wwhttp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0011','neij','内径','Wwhttp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0012','fpdj','等级','Wwhttp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0013','ybno','供应商合同','Ycaitp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0014','xpho','厚','Ycaitp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0015','xpkn','宽','Ycaitp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0016','zzsj','制造商卷板NO','Ycaitp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0017','zzzp','制造商制品NO','Ycaitp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0018','tun','重量','Ycaitp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0019','face','表面','Ycaitp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0020','ybgg','原板导入规格','Ycaitp');

insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0021','zzsj','制造商卷板NO','JchaTp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0022','cfcc','C','JchaTp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0023','cfsi','Si','JchaTp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0024','cfmn','Mn','JchaTp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0025','cfpp','P','JchaTp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0026','cfss','S','JchaTp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0027','ying','硬度','JchaTp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0028','chui','吹练NO','JchaTp');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0029','yp','YP','JchaTp');

insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0030','operate','操作类别','Ybrk');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0031','jbno','原材卷板NO','Ybrk');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0032','kw','库位','Ybrk');

insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0033','operate','操作类别','Zprk');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0034','jbno','制品NO','Zprk');
insert into COCO_CONVERT_FIELD( ID_, FIELD_, TITLE_, ENTITY_) values('0035','kw','库位','Zprk');
