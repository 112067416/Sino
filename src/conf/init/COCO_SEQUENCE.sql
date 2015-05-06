--序号生成
delete from COCO_SEQUENCE_RULE;
delete from COCO_SEQUENCE;

insert into COCO_SEQUENCE(ID_,DESCRIPTION_, SCALE_, STEP_, VALID_) values('SINO_YBNO', '原材卷板号', 6, 1, 1);
insert into COCO_SEQUENCE(ID_,DESCRIPTION_, SCALE_, STEP_, VALID_) values('SINO_FPNO', '在库分配No', 7, 1, 1);
insert into COCO_SEQUENCE(ID_,DESCRIPTION_, SCALE_, STEP_, VALID_) values('SINO_CYRZ', '采样单No', 8, 1, 1);
insert into COCO_SEQUENCE(ID_,DESCRIPTION_, SCALE_, STEP_, VALID_) values('SINO_DHZS', '订货指示书No', 5, 1, 1);
insert into COCO_SEQUENCE(ID_,DESCRIPTION_, SCALE_, STEP_, VALID_) values('SINO_ZXZS', '装箱指示书No', 10, 1, 1);
insert into COCO_SEQUENCE(ID_,DESCRIPTION_, SCALE_, STEP_, VALID_) values('SINO_SLZS', 'SL指示书No', 5, 1, 1);
insert into COCO_SEQUENCE(ID_,DESCRIPTION_, SCALE_, STEP_, VALID_) values('SINO_JBDDNO', '基板订单NO', 8, 1, 1);
insert into COCO_SEQUENCE(ID_,DESCRIPTION_, SCALE_, STEP_, VALID_) values('SINO_DBNO', '端板卷板No', 11, 1, 1);

insert into COCO_SEQUENCE_RULE(ID_, SEQ_, EXPR_, ORDER_, RESETABLE_, TYPE_) values('0001', 'SINO_CYRZ', 'D', '1', '1', '1');
insert into COCO_SEQUENCE_RULE(ID_, SEQ_, EXPR_, ORDER_, RESETABLE_, TYPE_) values('0002', 'SINO_CYRZ', NULL, '2', '0', '0');
insert into COCO_SEQUENCE_RULE(ID_, SEQ_, EXPR_, ORDER_, RESETABLE_, TYPE_) values('0003', 'SINO_DHZS', 'N', '1', '1', '1');
insert into COCO_SEQUENCE_RULE(ID_, SEQ_, EXPR_, ORDER_, RESETABLE_, TYPE_) values('0004', 'SINO_DHZS', NULL, '2', '0', '0');
insert into COCO_SEQUENCE_RULE(ID_, SEQ_, EXPR_, ORDER_, RESETABLE_, TYPE_) values('0005', 'SINO_SLZS', NULL, '1', '0', '0');
