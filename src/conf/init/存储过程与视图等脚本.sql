SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [SINO_REPORT_RIQI](
	[RIQI] [datetime] NOT NULL
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [SINO_REPORT_CPCKTZ](
	[RIQI] [datetime] NOT NULL,
	[CPLB] [varchar](1) NOT NULL,
	[CHOU] [varchar](1) NOT NULL,
	[RICC] [numeric](9, 3) NULL,
	[RSYB] [numeric](9, 3) NULL,
	[RSBS] [numeric](9, 3) NULL,
	[RICK] [numeric](9, 3) NULL,
	[RCHY] [numeric](9, 3) NULL,
	[RCBS] [numeric](9, 3) NULL,
	[JIEC] [numeric](20, 0) NULL,
	[JCYB] [numeric](20, 0) NULL,
	[JCBS] [numeric](20, 0) NULL,
	[TH] [numeric](9, 3) NULL,
	[THYB] [numeric](9, 3) NULL,
	[THBS] [numeric](9, 3) NULL,
	[CTOD] [numeric](9, 3) NULL,
	[DTOC] [numeric](9, 3) NULL,
	[XPXG] [numeric](9, 3) NULL,
	[XPXGYB] [numeric](9, 3) NULL,
	[XPXGBS] [numeric](9, 3) NULL,
	[XPZC] [numeric](9, 3) NULL,
	[XPZCYB] [numeric](9, 3) NULL,
	[XPZCBS] [numeric](9, 3) NULL,
	[XPSC] [numeric](9, 3) NULL,
	[XPSCYB] [numeric](9, 3) NULL,
	[XPSCBS] [numeric](9, 3) NULL,
	[FHQX] [numeric](9, 3) NULL
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create PROCEDURE [insertDate]
-- Add the parameters for the stored procedure here
 @begin datetime 
 ,@end datetime
AS
declare @n int
declare @AgedayInt int
declare @sql varchar(100)
declare @tempDate datetime
declare @dateStr varchar(100)

set @n=0
set @AgedayInt=datediff(day, @begin, @end)
while @n<@AgedayInt
begin
 set @tempDate=dateadd(day,@n,@begin)
 set @dateStr=CONVERT(varchar(100), @tempDate, 120)
 Set @Sql='Insert into dbo.SINO_REPORT_RIQI Values('''+@dateStr+''')' 

 Exec (@Sql)  
 set @n=@n+1
End
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [SINO_V_SLCCTJ] AS
--SL制品按原材NO分等级产出统计
select YCNO_,max(slsd_) as SLSD_,
sum(case when chan_ in('1','9') then zpzl_ else 0 end)  as 一级,
sum(case when chan_ ='2' then zpzl_ else 0 end)  as 二级,
sum(case when chan_ ='3' then zpzl_ else 0 end)  as 三级,
sum(case when chan_ ='4' then zpzl_ else 0 end)  as 四级,
sum(case when chan_ in('7','5','S') then zpzl_ else 0 end)  as 屑
from sino_zpngtp 
where datepart(yy,getdate()) =datepart(yy,slsd_) and datepart(mm,getdate()) =datepart(mm,slsd_) group by YCNO_
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [SINO_V_SLBY] AS  --本月SL按原材NO合计
select ycno_ AS YCNO,max(slsd_) as SLSD,sum(zpzl_) as ZPZL
from sino_zpngtp 
where datepart(yy,getdate()) =datepart(yy,slsd_) and datepart(mm,getdate()) =datepart(mm,slsd_) group by ycno_
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [SINO_V_ETLZRL] AS
--ETL的装入量
select A.ZRZL_,A.JBNO_ from SINO_YCAITP A   
where
 A.SCBJ_<>'0'  --原材卷已终了
and datepart(yy,getdate()) =datepart(yy,A.SJSJ_) and datepart(mm,getdate()) =datepart(mm,A.SJSJ_)--ETL实绩完成时间为本月
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [SINO_V_ETLCCTJ] AS
--ETL制品分等级产出统计
select YCNO_,max(ETSD_) as ETSD,
sum(case when chan_ in('1','9') then zpzl_ else 0 end)  as 一级,
sum(case when chan_ ='2' then zpzl_ else 0 end)  as 二级,
sum(case when chan_ ='3' then zpzl_ else 0 end)  as 三级,
sum(case when chan_ ='4' then zpzl_ else 0 end)  as 四级,
sum(case when chan_ in('7','5','S') then zpzl_ else 0 end)  as 屑
from sino_zpngtp 
where  LEN(RCZPNO_)<=7 
and datepart(yy,getdate()) =datepart(yy,ETSD_) and datepart(mm,getdate()) =datepart(mm,ETSD_) group by YCNO_
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [SINO_V_ETLBY] AS  --本月ETL生产
--1、查出本月镀锡制品号、入侧卷板号、产量代码、ETL实绩登录年月日，制品重量，如果入侧卷板仍没有终了，则不算装入量
select RCZPNO_,max(ETSD_) AS ETSD,sum(ZPZL_) AS ZPZL
from sino_zpngtp 
where  LEN(RCZPNO_)<=7 
and datepart(yy,getdate()) =datepart(yy,ETSD_) and datepart(mm,getdate()) =datepart(mm,ETSD_) group by rczpno_ --ETL实绩完成时间为本月
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create VIEW [SINO_V_YCBSQD] AS
--本月所有保税原材在库清单　FOR 保税品在库清单
SELECT JBNO_,ZPZL_,ZRZL_,SJSJ_,SPBH_
FROM SINO_YCAITP A
WHERE A.SFBS_='2'
 AND DUIC_<>'X'
 AND A.SJSJ_ IS NULL OR  (datepart(yy,getdate()) =datepart(yy,A.SJSJ_) and datepart(mm,getdate()) =datepart(mm,A.SJSJ_))
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [CPCKTZ_ZJP_RPOC]
 -- @tjdate datetime 
AS
--中间品仓库台账
    DECLARE @tjdate datetime
	DECLARE @RIQI_ datetime    --
	DECLARE @CPLB_ varchar(1)  --成品类别　２、中间品　３、剪切材
	DECLARE @CHOU_ varchar(1)  --商品编码　
	DECLARE @RICC_ numeric(9,3) --日产出合计
	DECLARE @RSYB_ numeric(9,3) --日产出一般贸易
	DECLARE @RSBS_ numeric(9,3) --日产出保税
	DECLARE @RICK_ numeric(9,3) --日出货合计
	DECLARE @RCHY_ numeric(9,3) --日出库一般贸易
	DECLARE @RCBS_ numeric(9,3) --日出库保税
	DECLARE @JIEC_ numeric(20,0) --结存合计
	DECLARE @JCYB_ numeric(20,0) --一般贸易结存
	DECLARE @JCBS_ numeric(20,0) --保税品结存
	DECLARE @TH_   numeric(9,3) --退货合计
	DECLARE @THYB_ numeric(9,3) --退货一般贸易　
	DECLARE @THBS_ numeric(9,3) --退货保税
	DECLARE @CTOD_ numeric(9,3) --C堆场转D
	DECLARE @DTOC_ numeric(9,3) --D堆场转C
	DECLARE @XPXG_ numeric(9,3) --现品情报修改合计
	DECLARE @XPXGYB_ numeric(9,3) --现品情报修改一般贸易
	DECLARE @XPXGBS_ numeric(9,3) --现品情报修改保税
	DECLARE @XPZC_ numeric(9,3) --现品情报作成合计
	DECLARE @XPZCYB_ numeric(9,3) --现品情报作成一般贸易
	DECLARE @XPZCBS_ numeric(9,3) --现品情报作成保税
	DECLARE @XPSC_ numeric(9,3) --现品情报删除合计
	DECLARE @XPSCYB_ numeric(9,3) --现品情报删除一般贸易
	DECLARE @XPSCBS_ numeric(9,3) --现品情报删除保税
	DECLARE @FHQX_  numeric(9,3)  --发货取消
	
	
BEGIN
	print '往成品仓库台账表插入每日统计数据--'
--商品编号１厚度<0.3	
	--中间每日产出量　当日产出后转中间库生产的要不要统计？
	SET @tjdate=GETDATE()-1
  SELECT @RICC_=SUM(ZPZL_) ,
         @RSYB_=SUM(CASE WHEN SFBS_='1' THEN ZPZL_ ELSE 0 END),
         @RSBS_=SUM(CASE WHEN SFBS_='2' THEN ZPZL_ ELSE 0 END)
  FROM  SINO_ZPNGTP 
  WHERE PZNO_ LIKE '1%' AND spbh_='1' and
         DATEDIFF(DY,ETSD_,@tjdate)=0
    --中间品领用     
  SELECT @RICK_=SUM(ZRZL_) ,
         @RCHY_=SUM(CASE WHEN SFBS_='1' THEN ZRZL_ ELSE 0 END) ,
         @RCBS_=SUM(CASE WHEN SFBS_='2' THEN ZRZL_ ELSE 0 END)
  FROM   SINO_ZPNGTP
  WHERE  SCBJ_='1' AND  DUIC_='D' AND SPBH_='1' AND
         datediff(Dy,SJSJ_,@tjdate)=0
  --领用取消量
   
  --堆场转换－－品种NO的判定遗漏   
  SELECT @CTOD_=SUM(CASE WHEN NDUIC_='C' AND ODUIC_='D' THEN A.ZPZL_ ELSE 0 END),--C堆场转中间品
         @DTOC_=SUM(CASE WHEN NDUIC_='D' AND ODUIC_='C' THEN A.ZPZL_ ELSE 0 END)--中间品转C堆场
  FROM SINO_DCRZ A ,SINO_ZPNGTP B 
  WHERE A.JBNO_=B.JBNO_ AND B.SPBH_='1' AND datediff(Dy,A.XGSJ_,@tjdate)=0 
  --现品情报修改--修改为当天，但镀锡日不为当天，因为当天生产量的统计统计的为修改后的重量 修改量差： 修改后重量－修改前重量
  SELECT @XPXG_=sum(B.ZPZL_)-sum(A.ZPZL_) ,
         @XPXGYB_=SUM(CASE WHEN B.SFBS_='1' THEN B.ZPZL_-A.ZPZL_ ELSE 0 END),
         @XPXGBS_=SUM(CASE WHEN B.SFBS_='2' THEN B.ZPZL_-A.ZPZL_ ELSE 0 END)
  FROM SINO_XPXX A,SINO_ZPNGTP B 
  WHERE  A.JBNO_=B.JBNO_ AND B.DUIC_='D' AND SPBH_='1' AND A.MKLX_='0' AND datediff(Dy,B.ETSD_,@tjdate)<>0 AND datediff(Dy,SCSJ_,@tjdate)=0  
  --现品情报作成,作成为当天 但生成制品的镀锡日不为当天，因为当天生产量的统计已经包含了当天镀锡的重量
   
  SELECT @XPZC_=SUM(A.ZPZL_),
       @XPZCYB_=SUM(CASE WHEN A.SFBS_='1' THEN A.ZPZL_ ELSE 0 END),
       @XPZCBS_=SUM(CASE WHEN A.SFBS_='2' THEN A.ZPZL_ ELSE 0 END)     
  FROM SINO_ZPNGTP A,
      (SELECT CONVERT(VARCHAR(10),SCSJ_,120) SCSJ_,NJBNO_ FROM SINO_XPXX WHERE MKLX_='1'  GROUP BY NJBNO_,CONVERT(VARCHAR(10),SCSJ_,120)) B
  WHERE A.JBNO_ = B.NJBNO_ AND A.DUIC_='D' AND A.SPBH_='1' AND datediff(Dy,B.SCSJ_,@tjdate)=0 AND datediff(Dy,A.ETSD_,@tjdate)<>0
 --现品情报删除,删除为当天 但生成制品的镀锡日不为当天，因为当天生产量的统计不包含该重量
   
  SELECT @XPSC_=SUM(A.ZPZL_),
       @XPSCYB_=SUM(CASE WHEN A.SFBS_='1' THEN A.ZPZL_ ELSE 0 END),
       @XPSCBS_=SUM(CASE WHEN A.SFBS_='2' THEN A.ZPZL_ ELSE 0 END)     
  FROM SINO_ZPNGTP A,
      (SELECT CONVERT(VARCHAR(10),XGSJ_,120) XGSJ_,JBNO_ FROM SINO_XPXX WHERE MKLX_='2'  GROUP BY JBNO_,CONVERT(VARCHAR(10),XGSJ_,120)) B
  WHERE A.JBNO_ = B.JBNO_ AND A.DUIC_='D' AND A.SPBH_='1' AND datediff(Dy,B.XGSJ_,@tjdate)=0 AND datediff(Dy,A.ETSD_,@tjdate)<>0
     
 --结存
 SELECT
    @JIEC_=SUM(case when  SCBJ_='0' then zpzl_ else 0 end) ,
    @JCYB_=SUM(case when  SCBJ_='0' and sfbs_='1' then zpzl_ else 0 end),
    @JCBS_=SUM(case when  SCBJ_='0' and sfbs_='2' then zpzl_ else 0 end) 
 FROM sino_zpngtp A   where  A.DUIC_='D' AND SPBH_='1'
 
 
	
	insert into Sino.dbo.SINO_REPORT_CPCKTZ (
		RIQI,CPLB,CHOU,RICC,RSYB,RSBS,RICK,RCHY,RCBS,JIEC,JCYB,JCBS,TH,THYB,THBS,
		XPXG,XPXGYB,XPXGBS,XPZC,XPZCYB,XPZCBS,XPSC,XPSCYB,XPSCBS,CTOD,DTOC
	)
	values(
		@tjdate,'3', '1', @RICC_,@RSYB_,@RSBS_,@RICK_,@RCHY_,@RCBS_,@JIEC_,@JCYB_,@JCBS_,@TH_,@THYB_,@THBS_,
		@XPXG_,@XPXGYB_,@XPXGBS_,@XPZC_,@XPZCYB_,@XPZCBS_,@XPSC_,@XPSCYB_,@XPSCBS_,@CTOD_,@DTOC_)

	print '中间品在库台账商品编码１结束'
	
	
	--商品编号２	
	--中间品每日产出量
	
   SELECT @RICC_=SUM(ZPZL_) ,
         @RSYB_=SUM(CASE WHEN SFBS_='1' THEN ZPZL_ ELSE 0 END),
         @RSBS_=SUM(CASE WHEN SFBS_='2' THEN ZPZL_ ELSE 0 END)
  FROM  SINO_ZPNGTP 
  WHERE PZNO_ LIKE '1%' AND spbh_='2' and
         DATEDIFF(DY,ETSD_,@tjdate)=0
    --中间品领用     
  SELECT @RICK_=SUM(ZRZL_) ,
         @RCHY_=SUM(CASE WHEN SFBS_='1' THEN ZRZL_ ELSE 0 END) ,
         @RCBS_=SUM(CASE WHEN SFBS_='2' THEN ZRZL_ ELSE 0 END)
  FROM   SINO_ZPNGTP
  WHERE  SCBJ_='1' AND  DUIC_='D' AND SPBH_='2' AND
         datediff(Dy,SJSJ_,@tjdate)=0
  --领用取消量
   
  --堆场转换－－品种NO的判定遗漏   
  SELECT @CTOD_=SUM(CASE WHEN NDUIC_='C' AND ODUIC_='D' THEN A.ZPZL_ ELSE 0 END),--C堆场转中间品
         @DTOC_=SUM(CASE WHEN NDUIC_='D' AND ODUIC_='C' THEN A.ZPZL_ ELSE 0 END)--中间品转C堆场
  FROM SINO_DCRZ A ,SINO_ZPNGTP B 
  WHERE A.JBNO_=B.JBNO_ AND B.SPBH_='2' AND datediff(Dy,A.XGSJ_,@tjdate)=0 
  --现品情报修改--修改为当天，但镀锡日不为当天，因为当天生产量的统计统计的为修改后的重量 修改量差： 修改后重量－修改前重量
  SELECT @XPXG_=sum(B.ZPZL_)-sum(A.ZPZL_) ,
         @XPXGYB_=SUM(CASE WHEN B.SFBS_='1' THEN B.ZPZL_-A.ZPZL_ ELSE 0 END),
         @XPXGBS_=SUM(CASE WHEN B.SFBS_='2' THEN B.ZPZL_-A.ZPZL_ ELSE 0 END)
  FROM SINO_XPXX A,SINO_ZPNGTP B 
  WHERE  A.JBNO_=B.JBNO_ AND B.DUIC_='D' AND SPBH_='2' AND A.MKLX_='0' AND datediff(Dy,B.ETSD_,@tjdate)<>0 AND datediff(Dy,SCSJ_,@tjdate)=0  
  --现品情报作成,作成为当天 但生成制品的镀锡日不为当天，因为当天生产量的统计已经包含了当天镀锡的重量
   
  SELECT @XPZC_=SUM(A.ZPZL_),
       @XPZCYB_=SUM(CASE WHEN A.SFBS_='1' THEN A.ZPZL_ ELSE 0 END),
       @XPZCBS_=SUM(CASE WHEN A.SFBS_='2' THEN A.ZPZL_ ELSE 0 END)     
  FROM SINO_ZPNGTP A,
      (SELECT CONVERT(VARCHAR(10),SCSJ_,120) SCSJ_,NJBNO_ FROM SINO_XPXX WHERE MKLX_='1'  GROUP BY NJBNO_,CONVERT(VARCHAR(10),SCSJ_,120)) B
  WHERE A.JBNO_ = B.NJBNO_ AND A.DUIC_='D' AND A.SPBH_='2' AND datediff(Dy,B.SCSJ_,@tjdate)=0 AND datediff(Dy,A.ETSD_,@tjdate)<>0
 --现品情报删除,删除为当天 但生成制品的镀锡日不为当天，因为当天生产量的统计不包含该重量
   
  SELECT @XPSC_=SUM(A.ZPZL_),
       @XPSCYB_=SUM(CASE WHEN A.SFBS_='1' THEN A.ZPZL_ ELSE 0 END),
       @XPSCBS_=SUM(CASE WHEN A.SFBS_='2' THEN A.ZPZL_ ELSE 0 END)     
  FROM SINO_ZPNGTP A,
      (SELECT CONVERT(VARCHAR(10),XGSJ_,120) XGSJ_,JBNO_ FROM SINO_XPXX WHERE MKLX_='2'  GROUP BY JBNO_,CONVERT(VARCHAR(10),XGSJ_,120)) B
  WHERE A.JBNO_ = B.JBNO_ AND A.DUIC_='D' AND A.SPBH_='2' AND datediff(Dy,B.XGSJ_,@tjdate)=0 AND datediff(Dy,A.ETSD_,@tjdate)<>0
     
 --结存
 SELECT
    @JIEC_=SUM(case when  SCBJ_='0' then zpzl_ else 0 end) ,
    @JCYB_=SUM(case when  SCBJ_='0' and sfbs_='1' then zpzl_ else 0 end),
    @JCBS_=SUM(case when  SCBJ_='0' and sfbs_='2' then zpzl_ else 0 end) 
 FROM sino_zpngtp A   where  A.DUIC_='D' AND SPBH_='2'
 
 
	
	insert into Sino.dbo.SINO_REPORT_CPCKTZ (
		RIQI,CPLB,CHOU,RICC,RSYB,RSBS,RICK,RCHY,RCBS,JIEC,JCYB,JCBS,TH,THYB,THBS,
		XPXG,XPXGYB,XPXGBS,XPZC,XPZCYB,XPZCBS,XPSC,XPSCYB,XPSCBS,CTOD,DTOC
	)
	values(
		@tjdate,'3', '2', @RICC_,@RSYB_,@RSBS_,@RICK_,@RCHY_,@RCBS_,@JIEC_,@JCYB_,@JCBS_,@TH_,@THYB_,@THBS_,
		@XPXG_,@XPXGYB_,@XPXGBS_,@XPZC_,@XPZCYB_,@XPZCBS_,@XPSC_,@XPSCYB_,@XPSCBS_,@CTOD_,@DTOC_)

	print '中间品在库台账商品编码2结束'
	
	--商品编号3厚度<0.3	
	--中间品每日产出量
	
  SELECT @RICC_=SUM(ZPZL_) ,
         @RSYB_=SUM(CASE WHEN SFBS_='1' THEN ZPZL_ ELSE 0 END),
         @RSBS_=SUM(CASE WHEN SFBS_='2' THEN ZPZL_ ELSE 0 END)
  FROM  SINO_ZPNGTP 
  WHERE PZNO_ LIKE '1%' AND spbh_='3' and
         DATEDIFF(DY,ETSD_,@tjdate)=0
    --中间品领用     
  SELECT @RICK_=SUM(ZRZL_) ,
         @RCHY_=SUM(CASE WHEN SFBS_='1' THEN ZRZL_ ELSE 0 END) ,
         @RCBS_=SUM(CASE WHEN SFBS_='2' THEN ZRZL_ ELSE 0 END)
  FROM   SINO_ZPNGTP
  WHERE  SCBJ_='1' AND  DUIC_='D' AND SPBH_='3' AND
         datediff(Dy,SJSJ_,@tjdate)=0
  --领用取消量
   
  --堆场转换－－品种NO的判定遗漏   
  SELECT @CTOD_=SUM(CASE WHEN NDUIC_='C' AND ODUIC_='D' THEN A.ZPZL_ ELSE 0 END),--C堆场转中间品
         @DTOC_=SUM(CASE WHEN NDUIC_='D' AND ODUIC_='C' THEN A.ZPZL_ ELSE 0 END)--中间品转C堆场
  FROM SINO_DCRZ A ,SINO_ZPNGTP B 
  WHERE A.JBNO_=B.JBNO_ AND B.SPBH_='3' AND datediff(Dy,A.XGSJ_,@tjdate)=0 
  --现品情报修改--修改为当天，但镀锡日不为当天，因为当天生产量的统计统计的为修改后的重量 修改量差： 修改后重量－修改前重量
  SELECT @XPXG_=sum(B.ZPZL_)-sum(A.ZPZL_) ,
         @XPXGYB_=SUM(CASE WHEN B.SFBS_='1' THEN B.ZPZL_-A.ZPZL_ ELSE 0 END),
         @XPXGBS_=SUM(CASE WHEN B.SFBS_='2' THEN B.ZPZL_-A.ZPZL_ ELSE 0 END)
  FROM SINO_XPXX A,SINO_ZPNGTP B 
  WHERE  A.JBNO_=B.JBNO_ AND B.DUIC_='D' AND SPBH_='3' AND A.MKLX_='0' AND datediff(Dy,B.ETSD_,@tjdate)<>0 AND datediff(Dy,SCSJ_,@tjdate)=0  
  --现品情报作成,作成为当天 但生成制品的镀锡日不为当天，因为当天生产量的统计已经包含了当天镀锡的重量
   
  SELECT @XPZC_=SUM(A.ZPZL_),
       @XPZCYB_=SUM(CASE WHEN A.SFBS_='1' THEN A.ZPZL_ ELSE 0 END),
       @XPZCBS_=SUM(CASE WHEN A.SFBS_='2' THEN A.ZPZL_ ELSE 0 END)     
  FROM SINO_ZPNGTP A,
      (SELECT CONVERT(VARCHAR(10),SCSJ_,120) SCSJ_,NJBNO_ FROM SINO_XPXX WHERE MKLX_='1'  GROUP BY NJBNO_,CONVERT(VARCHAR(10),SCSJ_,120)) B
  WHERE A.JBNO_ = B.NJBNO_ AND A.DUIC_='D' AND A.SPBH_='3' AND datediff(Dy,B.SCSJ_,@tjdate)=0 AND datediff(Dy,A.ETSD_,@tjdate)<>0
 --现品情报删除,删除为当天 但生成制品的镀锡日不为当天，因为当天生产量的统计不包含该重量
   
  SELECT @XPSC_=SUM(A.ZPZL_),
       @XPSCYB_=SUM(CASE WHEN A.SFBS_='1' THEN A.ZPZL_ ELSE 0 END),
       @XPSCBS_=SUM(CASE WHEN A.SFBS_='2' THEN A.ZPZL_ ELSE 0 END)     
  FROM SINO_ZPNGTP A,
      (SELECT CONVERT(VARCHAR(10),XGSJ_,120) XGSJ_,JBNO_ FROM SINO_XPXX WHERE MKLX_='2'  GROUP BY JBNO_,CONVERT(VARCHAR(10),XGSJ_,120)) B
  WHERE A.JBNO_ = B.JBNO_ AND A.DUIC_='D' AND A.SPBH_='3' AND datediff(Dy,B.XGSJ_,@tjdate)=0 AND datediff(Dy,A.ETSD_,@tjdate)<>0
     
 --结存
 SELECT
    @JIEC_=SUM(case when  SCBJ_='0' then zpzl_ else 0 end) ,
    @JCYB_=SUM(case when  SCBJ_='0' and sfbs_='1' then zpzl_ else 0 end),
    @JCBS_=SUM(case when  SCBJ_='0' and sfbs_='2' then zpzl_ else 0 end) 
 FROM sino_zpngtp A   where  A.DUIC_='D' AND SPBH_='3'
 
 
	
	insert into Sino.dbo.SINO_REPORT_CPCKTZ (
		RIQI,CPLB,CHOU,RICC,RSYB,RSBS,RICK,RCHY,RCBS,JIEC,JCYB,JCBS,TH,THYB,THBS,
		XPXG,XPXGYB,XPXGBS,XPZC,XPZCYB,XPZCBS,XPSC,XPSCYB,XPSCBS,CTOD,DTOC
	)
	values(
		@tjdate,'3', '3', @RICC_,@RSYB_,@RSBS_,@RICK_,@RCHY_,@RCBS_,@JIEC_,@JCYB_,@JCBS_,@TH_,@THYB_,@THBS_,
		@XPXG_,@XPXGYB_,@XPXGBS_,@XPZC_,@XPZCYB_,@XPZCBS_,@XPSC_,@XPSCYB_,@XPSCBS_,@CTOD_,@DTOC_)

	print '中间品在库台账商品编码3结束'
END
	--delete from SINO_REPORT_CPCKTZ	
	--CPCKTZ_RPOC 
	
	--ALTER TABLE SINO_REPORT_CPCKTZ ALTER COLUMN JCBS NUMERIC(20,0)
	
	--SELECT * FROM SINO_REPORT_CPCKTZ
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [SINO_V_COILCY_YC] AS 
--本月生产涉及到的所有原材
select  B.ZZSD_, A.YCNO_ AS YCNO_ ,B.XPHO_,B.XPKN_,B.YUNY_,B.SPBH_,B.SFBS_,B.ZRZL_
from sino_zpngtp A ,SINO_YCAITP  B
where  LEN(A.RCZPNO_)<=7 AND A.RCZPNO_=B.JBNO_
and datepart(yy,getdate()) =datepart(yy,ETSD_) and datepart(mm,getdate()) =datepart(mm,ETSD_) --ETL实绩为本月
group by A.YCNO_,B.ZZSD_,B.XPHO_,B.XPKN_,B.YUNY_,B.SPBH_,B.SFBS_,B.ZRZL_
union all
select B.ZZSD_, A.YCNO_ AS YCNO_ ,B.XPHO_,B.XPKN_,B.YUNY_,B.SPBH_,B.SFBS_,B.ZRZL_
from sino_zpngtp A,SINO_YCAITP B 
where 
A.YCNO_=B.JBNO_ AND
 datepart(yy,getdate()) =datepart(yy,slsd_) and datepart(mm,getdate()) =datepart(mm,slsd_) group by A.YCNO_,B.ZZSD_,B.XPHO_,B.XPKN_,B.YUNY_,B.SPBH_,B.SFBS_,B.ZRZL_
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [SINO_V_CPCKTZ_JCPCC] AS
--卷成品每日产出量
  SELECT CONVERT(VARCHAR(10),ETSD_,111) ETSD,SUM(ZPZL_) AS CCZL
  FROM  SINO_ZPNGTP 
  WHERE PZNO_ LIKE '2%' AND 
         datepart(yy,getdate()) =datepart(yy,ETSD_) and datepart(mm,getdate()) =datepart(mm,ETSD_)
         GROUP BY CONVERT(VARCHAR(10),ETSD_,111)
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [SINO_V_COILCYSJ_SLZRL] AS 
--COIL单位操业实绩--SL装入量
--SL的装入量统计，按原材卷板号，只有镀锡卷已经剪切终了的才计算装入量
select sum(case a.sjsj_ when null then 0 else a.zrzl_ end) as SLZRL,sum(b.zpzl) as  SLCCL,a.ycno_ AS YCNO
from 
   sino_zpngtp a ,
  (select ycno_ AS YCNO,rczpno_ as rczpno ,max(slsd_) as SLSD,sum(zpzl_) as ZPZL
    from sino_zpngtp 
    where DUIC_<>'E' AND  datepart(yy,getdate()) =datepart(yy,slsd_) and datepart(mm,getdate()) =datepart(mm,slsd_) group by ycno_ ,rczpno_) b
 where  a.jbno_=b.rczpno 
 group by a.ycno_
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [SINO_V_COILCYSJ_SLCCL]
AS
SELECT     YCNO_ AS YCNO, CONVERT(varchar(12), MAX(SLSD_), 111) AS SLSD, 
                      SUM(CASE WHEN chan_ IN ('1', '9') THEN zpzl_ ELSE 0 END) AS SL1J, 
                      SUM(CASE WHEN chan_ = '2' THEN zpzl_ ELSE 0 END) AS SL2J, 
                      SUM(CASE WHEN chan_ = '3' THEN zpzl_ ELSE 0 END) AS SL3J, 
                      SUM(CASE WHEN chan_ = '4' THEN zpzl_ ELSE 0 END) AS SL4J, SUM(CASE WHEN chan_ IN ('7', '5', 'S') THEN zpzl_ ELSE 0 END) AS SLX
FROM         dbo.SINO_ZPNGTP 
WHERE  DUIC_<>'E' AND   (DATEPART(yy, GETDATE()) = DATEPART(yy, SLSD_)) AND (DATEPART(mm, GETDATE()) = DATEPART(mm, SLSD_))
GROUP BY YCNO_
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [SINO_V_COILCYSJ_ETLZRL] AS
--ETL装入量
--查出所有本月生产镀锡卷对应入侧卷板的装入重量作为ETL的装入量，如果原材卷还未终了，则装入量计0
 --在原材卷板DB和制品在库DB中增加装入卷重，如果有半切中止，则装入卷重为原卷重－半切中止的重量。
select (case A.SCBJ_ WHEN '0' THEN 0 ELSE A.ZRZL_ END) AS ETLZRL,A.JBNO_ AS YCNO
from SINO_YCAITP A,SINO_ZPNGTP B   
where A.JBNO_=B.RCZPNO_  
and datepart(yy,getdate()) =datepart(yy,B.ETSD_) and datepart(mm,getdate()) =datepart(mm,B.ETSD_)--ETL实绩完成时间为本月
GROUP BY B.RCZPNO_,A.ZRZL_,A.JBNO_,A.SCBJ_
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [SINO_V_COILCYSJ_ETLCCL] AS
--ETL制品分等级产出统计
select YCNO_,convert(varchar(12),max(ETSD_),111) as ETSD,substring(pzno_,1,1) as pzno,(case sfbs_ when '2' then spbh_ else '' end ) as no,
sfbs_ as sfbs,FUZM_,FUFM_,
SUM(ZPZL_) AS ETLCCL,
sum(case when chan_ in('1','9') then zpzl_ else 0 end)  as ETL1J,
sum(case when chan_ ='2' then zpzl_ else 0 end)  as ETL2J,
sum(case when chan_ ='3' then zpzl_ else 0 end)  as ETL3J,
sum(case when chan_ ='4' then zpzl_ else 0 end)  as ETL4J,
sum(case when chan_ in('7','5','S') then zpzl_ else 0 end)  as ETLX
from sino_zpngtp 
where  LEN(RCZPNO_)<=7 
and datepart(yy,getdate()) =datepart(yy,ETSD_) and datepart(mm,getdate()) =datepart(mm,ETSD_) --ETL实绩为本月
group by YCNO_,pzno_,spbh_,sfbs_,FUZM_,FUFM_
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE view [SINO_V_YCRK] AS
--原材入库　FOR 原材料仓库台账
SELECT convert(varchar(10),duib_,111) AS DUIB,SPBH_,
SUM(zpzl_) AS 购进总量,
SUM(CASE WHEN SFBS_='1'  THEN ZPZL_ ELSE 0 END) AS 一般贸易,
SUM(CASE WHEN SFBS_='2'  THEN ZPZL_ ELSE 0 END) AS 保税
FROM SINO_YCAITP 
WHERE len(jbno_)=6 
 AND datepart(yy,getdate()) =datepart(yy,DUIB_) and datepart(mm,getdate()) =datepart(mm,DUIB_)
 group by convert(varchar(10),duib_,111),SPBH_
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [SINO_V_YCLY] AS 
--原材领用　FOR 原材料仓库台账
SELECT SPBH_,CONVERT(VARCHAR(10),SJSJ_,111) AS SJSJ,
SUM(ZRZL_) AS 领用总量,
SUM(CASE WHEN SFBS_='1' THEN ZRZL_ ELSE 0 END) AS 一般贸易,
SUM(CASE WHEN SFBS_='2' THEN ZRZL_ ELSE 0 END) AS 保税
FROM SINO_YCAITP 
WHERE 
  (datepart(yy,getdate()) =datepart(yy,SJSJ_) and datepart(mm,getdate()) =datepart(mm,SJSJ_)) 
GROUP BY CONVERT(VARCHAR(10),SJSJ_,111),SPBH_
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [SINO_V_YCJC_SP3] AS 
--原材结存　FOR 原材料仓库台账
SELECT A.RIQI ,
(SELECT SUM(ZPZL_) FROM SINO_YCAITP WHERE SPBH_='3' AND (SJSJ_ IS NULL OR CONVERT(VARCHAR(12), SJSJ_, 111)>A.RIQI) AND CONVERT(VARCHAR(12), DUIB_, 111)<=A.RIQI) AS 结存重量,
(SELECT SUM(ZPZL_) FROM SINO_YCAITP WHERE SPBH_='3' AND (SJSJ_ IS NULL OR CONVERT(VARCHAR(12), SJSJ_, 111)>A.RIQI) AND SFBS_='2' AND CONVERT(VARCHAR(12), DUIB_, 111)<=A.RIQI) AS 保税品结存,
(SELECT SUM(ZPZL_) FROM SINO_YCAITP WHERE SPBH_='3' AND (SJSJ_ IS NULL OR CONVERT(VARCHAR(12), SJSJ_, 111)>A.RIQI) AND SFBS_='1' AND CONVERT(VARCHAR(12), DUIB_, 111)<=A.RIQI) AS 一般贸易结存
FROM SINO_REPORT_RIQI A    
WHERE (datepart(yy,getdate()) =datepart(yy,A.RIQI) and datepart(mm,getdate()) =datepart(mm,A.RIQI))
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [SINO_V_YCJC_SP2] AS 
--原材结存　FOR 原材料仓库台账
SELECT A.RIQI ,
(SELECT SUM(ZPZL_) FROM SINO_YCAITP WHERE SPBH_='2' AND (SJSJ_ IS NULL OR CONVERT(VARCHAR(12), SJSJ_, 111)>A.RIQI) AND CONVERT(VARCHAR(12), DUIB_, 111)<=A.RIQI) AS 结存重量,
(SELECT SUM(ZPZL_) FROM SINO_YCAITP WHERE SPBH_='2' AND (SJSJ_ IS NULL OR CONVERT(VARCHAR(12), SJSJ_, 111)>A.RIQI) AND SFBS_='2' AND CONVERT(VARCHAR(12), DUIB_, 111)<=A.RIQI) AS 保税品结存,
(SELECT SUM(ZPZL_) FROM SINO_YCAITP WHERE SPBH_='2' AND (SJSJ_ IS NULL OR CONVERT(VARCHAR(12), SJSJ_, 111)>A.RIQI) AND SFBS_='1' AND CONVERT(VARCHAR(12), DUIB_, 111)<=A.RIQI) AS 一般贸易结存
FROM SINO_REPORT_RIQI A    
WHERE (datepart(yy,getdate()) =datepart(yy,A.RIQI) and datepart(mm,getdate()) =datepart(mm,A.RIQI))
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [SINO_V_YCJC_SP1] AS 
--原材结存　FOR 原材料仓库台账
SELECT A.RIQI ,
(SELECT SUM(ZPZL_) FROM SINO_YCAITP WHERE SPBH_='1' AND (SJSJ_ IS NULL OR CONVERT(VARCHAR(10), SJSJ_, 111)>A.RIQI) AND CONVERT(VARCHAR(10), DUIB_, 111)<=A.RIQI) AS 结存重量,
(SELECT SUM(ZPZL_) FROM SINO_YCAITP WHERE SPBH_='1' AND (SJSJ_ IS NULL OR CONVERT(VARCHAR(10), SJSJ_, 111)>A.RIQI) AND SFBS_='2' AND CONVERT(VARCHAR(10), DUIB_, 111)<=A.RIQI) AS 保税品结存,
(SELECT SUM(ZPZL_) FROM SINO_YCAITP WHERE SPBH_='1' AND (SJSJ_ IS NULL OR CONVERT(VARCHAR(10), SJSJ_, 111)>A.RIQI) AND SFBS_='1' AND CONVERT(VARCHAR(10), DUIB_, 111)<=A.RIQI) AS 一般贸易结存
FROM SINO_REPORT_RIQI A    
WHERE (datepart(yy,getdate()) =datepart(yy,A.RIQI) and datepart(mm,getdate()) =datepart(mm,A.RIQI))
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [SINO_V_YCJC] AS 
--原材结存　FOR 原材料仓库台账
SELECT A.RIQI ,
(SELECT SUM(ZPZL_) FROM SINO_YCAITP WHERE SPBH_='1' AND (SJSJ_ IS NULL OR CONVERT(VARCHAR(12), SJSJ_, 111)>A.RIQI) AND CONVERT(VARCHAR(12), DUIB_, 111)<=A.RIQI) AS 结存重量,
(SELECT SUM(ZPZL_) FROM SINO_YCAITP WHERE SPBH_='1' AND (SJSJ_ IS NULL OR CONVERT(VARCHAR(12), SJSJ_, 111)>A.RIQI) AND SFBS_='2' AND CONVERT(VARCHAR(12), DUIB_, 111)<=A.RIQI) AS 保税品结存,
(SELECT SUM(ZPZL_) FROM SINO_YCAITP WHERE SPBH_='1' AND (SJSJ_ IS NULL OR CONVERT(VARCHAR(12), SJSJ_, 111)>A.RIQI) AND SFBS_='1' AND CONVERT(VARCHAR(12), DUIB_, 111)<=A.RIQI) AS 一般贸易结存
FROM SINO_REPORT_RIQI A    
WHERE (datepart(yy,getdate()) =datepart(yy,A.RIQI) and datepart(mm,getdate()) =datepart(mm,A.RIQI))
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [SINO_V_YCCKTZ3] AS

SELECT A.RIQI,C.购进总量,C.一般贸易 as 购进一般贸易,C.保税 as 购进保税,b.领用总量,b.一般贸易 领用一般贸易,b.保税 as 领用保税,a.结存重量,a.保税品结存,a.一般贸易结存
 FROM SINO_V_YCJC_SP3 A LEFT JOIN SINO_V_YCLY B ON A.RIQI=B.SJSJ and b.SPBH_='3'
      LEFT JOIN SINO_V_YCRK C ON A.RIQI=C.DUIB and c.spbh_='3'
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [SINO_V_YCCKTZ2] AS

SELECT A.RIQI,C.购进总量,C.一般贸易 as 购进一般贸易,C.保税 as 购进保税,b.领用总量,b.一般贸易 领用一般贸易,b.保税 as 领用保税,a.结存重量,a.保税品结存,a.一般贸易结存
 FROM SINO_V_YCJC_SP2 A LEFT JOIN SINO_V_YCLY B ON A.RIQI=B.SJSJ and b.SPBH_='2'
      LEFT JOIN SINO_V_YCRK C ON A.RIQI=C.DUIB and c.spbh_='2'
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [SINO_V_YCCKTZ1]
AS
SELECT     A.RIQI, C.购进总量, C.一般贸易 AS 购进一般贸易, C.保税 AS 购进保税, B.领用总量, B.一般贸易 AS 领用一般贸易, B.保税 AS 领用保税, A.结存重量, A.保税品结存, 
                      A.一般贸易结存
FROM         dbo.SINO_V_YCJC_SP1 AS A LEFT OUTER JOIN
                      dbo.SINO_V_YCLY AS B ON A.RIQI = B.SJSJ AND B.SPBH_ = '1' LEFT OUTER JOIN
                      dbo.SINO_V_YCRK AS C ON A.RIQI = C.DUIB AND C.SPBH_ = '1'
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [SINO_V_COILCYSJ] AS
--COIL操业实绩总表
SELECT A.ZZSD_, A.YCNO_,A.ZRZL_/1000.0 as YBZL, A.XPHO_,A.XPKN_,A.YUNY_, B.ETSD,B.pzno,A.SPBH_,A.SFBS_,
D.ETLZRL/1000.0 AS ETLZRL,case when B.ETL1J<>0 then B.ETL1J/1000.0 else null end AS ETL1J,
      case when B.ETL2J<>0 then B.ETL2J/1000.0 else null end  AS ETL2J,case when B.ETL3J<>0 then  B.ETL3J/1000.0 else null end  AS ETL3J,case when  B.ETL4J<>0 then  B.ETL4J/1000.0 else null end  AS ETL4J, 
      case when  B.ETLCCL<>0 then  B.ETLCCL/1000.0 else null end AS ETLCCL,
      case when B.ETLX<>0 then  B.ETLX/1000.0 else null end  AS ETLX,B.FUZM_,B.FUFM_,
       C.SLSD,'' AS SLSCX,'' AS ZJBB ,case when E.SLZRL<>0 then E.SLZRL/1000.0 else null end AS SLZRL,
       case when C.SL1J<>0 then  C.SL1J/1000.0 else null end AS SL1J,
       case when C.SL2J<>0 then  C.SL2J/1000.0 else null end AS SL2J,
       case when C.SL3J<>0 then C.SL3J/1000.0 else null end  AS SL3J,
       case when C.SL4J<>0 then C.SL4J/1000.0 else null end AS SL4J,
       case when E.SLCCL<>0 then E.SLCCL/1000.0 else null end  AS SLCCL,
       case when C.SLX<>0 then C.SLX/1000.0 else null end  AS SLX
 FROM SINO_V_COILCY_YC A LEFT JOIN SINO_V_COILCYSJ_ETLCCL B ON A.YCNO_=B.YCNO_ 
      LEFT JOIN SINO_V_COILCYSJ_SLCCL C ON A.YCNO_=C.YCNO
      LEFT JOIN SINO_V_COILCYSJ_ETLZRL D ON A.YCNO_=D.YCNO 
      LEFT JOIN SINO_V_COILCYSJ_SLZRL E ON A.YCNO_=E.YCNO
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create VIEW [SINO_V_BSQDGL2] AS 
 SELECT * FROM SINO_V_YCBSQD A LEFT JOIN SINO_V_SLCCTJ B ON A.JBNO_=B.YCNO_
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create VIEW [SINO_V_BSQDGL1] AS 
SELECT * FROM 
SINO_V_YCBSQD A LEFT JOIN SINO_V_ETLCCTJ B ON A.JBNO_=B.YCNO_
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [CPCKTZ_QBCP_RPOC]
 -- @tjdate datetime 
AS
--切板仓库台账
    DECLARE @tjdate datetime
	DECLARE @RIQI_ datetime    --
	DECLARE @CPLB_ varchar(1)  --成品类别　１、卷成品　２、中间品　３、剪切材
	DECLARE @CHOU_ varchar(1)  --商品编码　
	DECLARE @RICC_ numeric(9,3) --日产出合计
	DECLARE @RSYB_ numeric(9,3) --日产出一般贸易
	DECLARE @RSBS_ numeric(9,3) --日产出保税
	DECLARE @RICK_ numeric(9,3) --日出货合计
	DECLARE @RCHY_ numeric(9,3) --日出库一般贸易
	DECLARE @RCBS_ numeric(9,3) --日出库保税
	DECLARE @JIEC_ numeric(20,0) --结存合计
	DECLARE @JCYB_ numeric(20,0) --一般贸易结存
	DECLARE @JCBS_ numeric(20,0) --保税品结存
	DECLARE @TH_   numeric(9,3) --退货合计
	DECLARE @THYB_ numeric(9,3) --退货一般贸易　
	DECLARE @THBS_ numeric(9,3) --退货保税
	DECLARE @CTOD_ numeric(9,3) --C堆场转D
	DECLARE @DTOC_ numeric(9,3) --D堆场转C
	DECLARE @XPXG_ numeric(9,3) --现品情报修改合计
	DECLARE @XPXGYB_ numeric(9,3) --现品情报修改一般贸易
	DECLARE @XPXGBS_ numeric(9,3) --现品情报修改保税
	DECLARE @XPZC_ numeric(9,3) --现品情报作成合计
	DECLARE @XPZCYB_ numeric(9,3) --现品情报作成一般贸易
	DECLARE @XPZCBS_ numeric(9,3) --现品情报作成保税
	DECLARE @XPSC_ numeric(9,3) --现品情报删除合计
	DECLARE @XPSCYB_ numeric(9,3) --现品情报删除一般贸易
	DECLARE @XPSCBS_ numeric(9,3) --现品情报删除保税
	DECLARE @FHQX_  numeric(9,3)  --发货取消
	
	
BEGIN
	print '往成品仓库台账表插入每日统计数据--'
--商品编号１厚度<0.3	
	--切板成品每日产出量
	SET @tjdate=GETDATE()-1
  SELECT @RICC_=SUM(ZPZL_) ,
         @RSYB_=SUM(CASE WHEN SFBS_='1' THEN ZPZL_ ELSE 0 END),
         @RSBS_=SUM(CASE WHEN SFBS_='2' THEN ZPZL_ ELSE 0 END)
  FROM  SINO_ZPNGTP 
  WHERE (DUIC_='F' or DUIC_='G') AND spbh_='1' and
         DATEDIFF(DY,SLSD_,@tjdate)=0
    --切板成品每日发货量     
  SELECT @RICK_=SUM(ZPZL_) ,
         @RCHY_=SUM(CASE WHEN SFBS_='1' THEN ZPZL_ ELSE 0 END) ,
         @RCBS_=SUM(CASE WHEN SFBS_='2' THEN ZPZL_ ELSE 0 END)
  FROM   SINO_ZPNGTP
  WHERE  SCBJ_='3' AND (DUIC_='F' OR DUIC_='G') AND SPBH_='1' AND
         datediff(Dy,CHSD_,@tjdate)=0
  --发货取消量
   
   
  
  --现品情报修改--修改为当天，但镀锡日不为当天，因为当天生产量的统计统计的为修改后的重量 修改量差： 修改后重量－修改前重量
  SELECT @XPXG_=sum(B.ZPZL_)-sum(A.ZPZL_) ,
         @XPXGYB_=SUM(CASE WHEN B.SFBS_='1' THEN B.ZPZL_-A.ZPZL_ ELSE 0 END),
         @XPXGBS_=SUM(CASE WHEN B.SFBS_='2' THEN B.ZPZL_-A.ZPZL_ ELSE 0 END)
  FROM SINO_XPXX A,SINO_ZPNGTP B 
  WHERE  A.JBNO_=B.JBNO_ AND (B.DUIC_='F' OR DUIC_='G') AND SPBH_='1' AND A.MKLX_='0' AND datediff(Dy,B.SLSD_,@tjdate)<>0 AND datediff(Dy,SCSJ_,@tjdate)=0  
  --现品情报作成,作成为当天 但生成制品的镀锡日不为当天，因为当天生产量的统计已经包含了当天镀锡的重量
   
  SELECT @XPZC_=SUM(A.ZPZL_),
       @XPZCYB_=SUM(CASE WHEN A.SFBS_='1' THEN A.ZPZL_ ELSE 0 END),
       @XPZCBS_=SUM(CASE WHEN A.SFBS_='2' THEN A.ZPZL_ ELSE 0 END)     
  FROM SINO_ZPNGTP A,
      (SELECT CONVERT(VARCHAR(10),SCSJ_,120) SCSJ_,NJBNO_ FROM SINO_XPXX WHERE MKLX_='1'  GROUP BY NJBNO_,CONVERT(VARCHAR(10),SCSJ_,120)) B
  WHERE A.JBNO_ = B.NJBNO_ AND (A.DUIC_='F' OR A.DUIC_='G') AND A.SPBH_='1' AND datediff(Dy,B.SCSJ_,@tjdate)=0 AND datediff(Dy,A.SLSD_,@tjdate)<>0
 --现品情报删除,删除为当天 但生成制品的镀锡日不为当天，因为当天生产量的统计不包含该重量
   
  SELECT @XPSC_=SUM(A.ZPZL_),
       @XPSCYB_=SUM(CASE WHEN A.SFBS_='1' THEN A.ZPZL_ ELSE 0 END),
       @XPSCBS_=SUM(CASE WHEN A.SFBS_='2' THEN A.ZPZL_ ELSE 0 END)     
  FROM SINO_ZPNGTP A,
      (SELECT CONVERT(VARCHAR(10),XGSJ_,120) XGSJ_,JBNO_ FROM SINO_XPXX WHERE MKLX_='2'  GROUP BY JBNO_,CONVERT(VARCHAR(10),XGSJ_,120)) B
  WHERE A.JBNO_ = B.JBNO_ AND (A.DUIC_='F' OR A.DUIC_='G') AND A.SPBH_='1' AND datediff(Dy,B.XGSJ_,@tjdate)=0 AND datediff(Dy,A.SLSD_,@tjdate)<>0
     
 --结存
 SELECT
    @JIEC_=SUM(case when  SCBJ_='0' then zpzl_ else 0 end) ,
    @JCYB_=SUM(case when  SCBJ_='0' and sfbs_='1' then zpzl_ else 0 end),
    @JCBS_=SUM(case when  SCBJ_='0' and sfbs_='2' then zpzl_ else 0 end) 
 FROM sino_zpngtp A   where  (A.DUIC_='F' OR A.DUIC_='G') AND SPBH_='1'
 
 --每日退货--建议增加商品编号和是否保税字段
  SELECT @TH_=SUM(A.ZPZL_) 
  FROM SINO_TS A ,SINO_TH B,SINO_DHUOTP C 
  WHERE A.ID_ = B.TSTP_ AND C.PZNO_ LIKE '1%' AND A.DHNO_=C.DHNO_ AND A.LINE_=C.LINE_  
    AND DATEDIFF(DY,B.THRI_, @tjdate)=0 AND CAST(C.HOUU_ AS NUMERIC(6,3))<0.3  
	
	insert into Sino.dbo.SINO_REPORT_CPCKTZ (
		RIQI,CPLB,CHOU,RICC,RSYB,RSBS,RICK,RCHY,RCBS,JIEC,JCYB,JCBS,TH,THYB,THBS,
		XPXG,XPXGYB,XPXGBS,XPZC,XPZCYB,XPZCBS,XPSC,XPSCYB,XPSCBS,CTOD,DTOC
	)
	values(
		@tjdate,'1', '1', @RICC_,@RSYB_,@RSBS_,@RICK_,@RCHY_,@RCBS_,@JIEC_,@JCYB_,@JCBS_,@TH_,@THYB_,@THBS_,
		@XPXG_,@XPXGYB_,@XPXGBS_,@XPZC_,@XPZCYB_,@XPZCBS_,@XPSC_,@XPSCYB_,@XPSCBS_,@CTOD_,@DTOC_)

	print '切板成品在库台账商品编码１结束'
	
	
	--商品编号２	
	--切板成品每日产出量
  SELECT @RICC_=SUM(ZPZL_) ,
         @RSYB_=SUM(CASE WHEN SFBS_='1' THEN ZPZL_ ELSE 0 END),
         @RSBS_=SUM(CASE WHEN SFBS_='2' THEN ZPZL_ ELSE 0 END)
  FROM  SINO_ZPNGTP 
  WHERE (DUIC_='F' or DUIC_='G') AND spbh_='2' and
         DATEDIFF(DY,SLSD_,@tjdate)=0
    --切板成品每日发货量     
  SELECT @RICK_=SUM(ZPZL_) ,
         @RCHY_=SUM(CASE WHEN SFBS_='1' THEN ZPZL_ ELSE 0 END) ,
         @RCBS_=SUM(CASE WHEN SFBS_='2' THEN ZPZL_ ELSE 0 END)
  FROM   SINO_ZPNGTP
  WHERE  SCBJ_='3' AND (DUIC_='F' OR DUIC_='G') AND SPBH_='2' AND
         datediff(Dy,CHSD_,@tjdate)=0
  --发货取消量
   
   
  
  --现品情报修改--修改为当天，但镀锡日不为当天，因为当天生产量的统计统计的为修改后的重量 修改量差： 修改后重量－修改前重量
  SELECT @XPXG_=sum(B.ZPZL_)-sum(A.ZPZL_) ,
         @XPXGYB_=SUM(CASE WHEN B.SFBS_='1' THEN B.ZPZL_-A.ZPZL_ ELSE 0 END),
         @XPXGBS_=SUM(CASE WHEN B.SFBS_='2' THEN B.ZPZL_-A.ZPZL_ ELSE 0 END)
  FROM SINO_XPXX A,SINO_ZPNGTP B 
  WHERE  A.JBNO_=B.JBNO_ AND (B.DUIC_='F' OR DUIC_='G') AND SPBH_='2' AND A.MKLX_='0' AND datediff(Dy,B.SLSD_,@tjdate)<>0 AND datediff(Dy,SCSJ_,@tjdate)=0  
  --现品情报作成,作成为当天 但生成制品的镀锡日不为当天，因为当天生产量的统计已经包含了当天镀锡的重量
   
  SELECT @XPZC_=SUM(A.ZPZL_),
       @XPZCYB_=SUM(CASE WHEN A.SFBS_='1' THEN A.ZPZL_ ELSE 0 END),
       @XPZCBS_=SUM(CASE WHEN A.SFBS_='2' THEN A.ZPZL_ ELSE 0 END)     
  FROM SINO_ZPNGTP A,
      (SELECT CONVERT(VARCHAR(10),SCSJ_,120) SCSJ_,NJBNO_ FROM SINO_XPXX WHERE MKLX_='1'  GROUP BY NJBNO_,CONVERT(VARCHAR(10),SCSJ_,120)) B
  WHERE A.JBNO_ = B.NJBNO_ AND (A.DUIC_='F' OR A.DUIC_='G') AND A.SPBH_='2' AND datediff(Dy,B.SCSJ_,@tjdate)=0 AND datediff(Dy,A.SLSD_,@tjdate)<>0
 --现品情报删除,删除为当天 但生成制品的镀锡日不为当天，因为当天生产量的统计不包含该重量
   
  SELECT @XPSC_=SUM(A.ZPZL_),
       @XPSCYB_=SUM(CASE WHEN A.SFBS_='1' THEN A.ZPZL_ ELSE 0 END),
       @XPSCBS_=SUM(CASE WHEN A.SFBS_='2' THEN A.ZPZL_ ELSE 0 END)     
  FROM SINO_ZPNGTP A,
      (SELECT CONVERT(VARCHAR(10),XGSJ_,120) XGSJ_,JBNO_ FROM SINO_XPXX WHERE MKLX_='2'  GROUP BY JBNO_,CONVERT(VARCHAR(10),XGSJ_,120)) B
  WHERE A.JBNO_ = B.JBNO_ AND (A.DUIC_='F' OR A.DUIC_='G') AND A.SPBH_='2' AND datediff(Dy,B.XGSJ_,@tjdate)=0 AND datediff(Dy,A.SLSD_,@tjdate)<>0
     
 --结存
 SELECT
    @JIEC_=SUM(case when  SCBJ_='0' then zpzl_ else 0 end) ,
    @JCYB_=SUM(case when  SCBJ_='0' and sfbs_='1' then zpzl_ else 0 end),
    @JCBS_=SUM(case when  SCBJ_='0' and sfbs_='2' then zpzl_ else 0 end) 
 FROM sino_zpngtp A   where  (A.DUIC_='F' OR A.DUIC_='G') AND SPBH_='2'
 
 --每日退货--建议增加商品编号和是否保税字段
  SELECT @TH_=SUM(A.ZPZL_) 
  FROM SINO_TS A ,SINO_TH B,SINO_DHUOTP C 
  WHERE A.ID_ = B.TSTP_ AND C.PZNO_ LIKE '1%' AND A.DHNO_=C.DHNO_ AND A.LINE_=C.LINE_  
    AND DATEDIFF(DY,B.THRI_, @tjdate)=0  AND CAST(C.HOUU_ AS NUMERIC(6,3))>=0.3 AND CAST(C.HOUU_ AS NUMERIC(6,3))<0.5
    
 
	insert into Sino.dbo.SINO_REPORT_CPCKTZ (
		RIQI,CPLB,CHOU,RICC,RSYB,RSBS,RICK,RCHY,RCBS,JIEC,JCYB,JCBS,TH,THYB,THBS,
		XPXG,XPXGYB,XPXGBS,XPZC,XPZCYB,XPZCBS,XPSC,XPSCYB,XPSCBS,CTOD,DTOC
	)
	values(
		@tjdate,'1', '2', @RICC_,@RSYB_,@RSBS_,@RICK_,@RCHY_,@RCBS_,@JIEC_,@JCYB_,@JCBS_,@TH_,@THYB_,@THBS_,
		@XPXG_,@XPXGYB_,@XPXGBS_,@XPZC_,@XPZCYB_,@XPZCBS_,@XPSC_,@XPSCYB_,@XPSCBS_,@CTOD_,@DTOC_)

	print '切板成品在库台账商品编码2结束'
	
		--商品编号3	
	--切板成品每日产出量
  SELECT @RICC_=SUM(ZPZL_) ,
         @RSYB_=SUM(CASE WHEN SFBS_='1' THEN ZPZL_ ELSE 0 END),
         @RSBS_=SUM(CASE WHEN SFBS_='2' THEN ZPZL_ ELSE 0 END)
  FROM  SINO_ZPNGTP 
  WHERE (DUIC_='F' or DUIC_='G') AND spbh_='3' and
         DATEDIFF(DY,SLSD_,@tjdate)=0
    --切板成品每日发货量     
  SELECT @RICK_=SUM(ZPZL_) ,
         @RCHY_=SUM(CASE WHEN SFBS_='1' THEN ZPZL_ ELSE 0 END) ,
         @RCBS_=SUM(CASE WHEN SFBS_='2' THEN ZPZL_ ELSE 0 END)
  FROM   SINO_ZPNGTP
  WHERE  SCBJ_='3' AND (DUIC_='F' OR DUIC_='G') AND SPBH_='3' AND
         datediff(Dy,CHSD_,@tjdate)=0
  --发货取消量
   
   
  
  --现品情报修改--修改为当天，但镀锡日不为当天，因为当天生产量的统计统计的为修改后的重量 修改量差： 修改后重量－修改前重量
  SELECT @XPXG_=sum(B.ZPZL_)-sum(A.ZPZL_) ,
         @XPXGYB_=SUM(CASE WHEN B.SFBS_='1' THEN B.ZPZL_-A.ZPZL_ ELSE 0 END),
         @XPXGBS_=SUM(CASE WHEN B.SFBS_='2' THEN B.ZPZL_-A.ZPZL_ ELSE 0 END)
  FROM SINO_XPXX A,SINO_ZPNGTP B 
  WHERE  A.JBNO_=B.JBNO_ AND (B.DUIC_='F' OR DUIC_='G') AND SPBH_='3' AND A.MKLX_='0' AND datediff(Dy,B.SLSD_,@tjdate)<>0 AND datediff(Dy,SCSJ_,@tjdate)=0  
  --现品情报作成,作成为当天 但生成制品的镀锡日不为当天，因为当天生产量的统计已经包含了当天镀锡的重量
   
  SELECT @XPZC_=SUM(A.ZPZL_),
       @XPZCYB_=SUM(CASE WHEN A.SFBS_='1' THEN A.ZPZL_ ELSE 0 END),
       @XPZCBS_=SUM(CASE WHEN A.SFBS_='2' THEN A.ZPZL_ ELSE 0 END)     
  FROM SINO_ZPNGTP A,
      (SELECT CONVERT(VARCHAR(10),SCSJ_,120) SCSJ_,NJBNO_ FROM SINO_XPXX WHERE MKLX_='1'  GROUP BY NJBNO_,CONVERT(VARCHAR(10),SCSJ_,120)) B
  WHERE A.JBNO_ = B.NJBNO_ AND (A.DUIC_='F' OR A.DUIC_='G') AND A.SPBH_='3' AND datediff(Dy,B.SCSJ_,@tjdate)=0 AND datediff(Dy,A.SLSD_,@tjdate)<>0
 --现品情报删除,删除为当天 但生成制品的镀锡日不为当天，因为当天生产量的统计不包含该重量
   
  SELECT @XPSC_=SUM(A.ZPZL_),
       @XPSCYB_=SUM(CASE WHEN A.SFBS_='1' THEN A.ZPZL_ ELSE 0 END),
       @XPSCBS_=SUM(CASE WHEN A.SFBS_='2' THEN A.ZPZL_ ELSE 0 END)     
  FROM SINO_ZPNGTP A,
      (SELECT CONVERT(VARCHAR(10),XGSJ_,120) XGSJ_,JBNO_ FROM SINO_XPXX WHERE MKLX_='2'  GROUP BY JBNO_,CONVERT(VARCHAR(10),XGSJ_,120)) B
  WHERE A.JBNO_ = B.JBNO_ AND (A.DUIC_='F' OR A.DUIC_='G') AND A.SPBH_='3' AND datediff(Dy,B.XGSJ_,@tjdate)=0 AND datediff(Dy,A.SLSD_,@tjdate)<>0
     
 --结存
 SELECT
    @JIEC_=SUM(case when  SCBJ_='0' then zpzl_ else 0 end) ,
    @JCYB_=SUM(case when  SCBJ_='0' and sfbs_='1' then zpzl_ else 0 end),
    @JCBS_=SUM(case when  SCBJ_='0' and sfbs_='2' then zpzl_ else 0 end) 
 FROM sino_zpngtp A   where  (A.DUIC_='F' OR A.DUIC_='G') AND SPBH_='3'
 
 --每日退货--建议增加商品编号和是否保税字段
  SELECT @TH_=SUM(A.ZPZL_) 
  FROM SINO_TS A ,SINO_TH B,SINO_DHUOTP C 
  WHERE A.ID_ = B.TSTP_ AND C.PZNO_ LIKE '1%' AND A.DHNO_=C.DHNO_ AND A.LINE_=C.LINE_  
    AND DATEDIFF(DY,B.THRI_, @tjdate)=0  AND CAST(C.HOUU_ AS NUMERIC(6,3))>=0.5 
    
 
	insert into Sino.dbo.SINO_REPORT_CPCKTZ (
		RIQI,CPLB,CHOU,RICC,RSYB,RSBS,RICK,RCHY,RCBS,JIEC,JCYB,JCBS,TH,THYB,THBS,
		XPXG,XPXGYB,XPXGBS,XPZC,XPZCYB,XPZCBS,XPSC,XPSCYB,XPSCBS,CTOD,DTOC
	)
	values(
		@tjdate,'1', '3', @RICC_,@RSYB_,@RSBS_,@RICK_,@RCHY_,@RCBS_,@JIEC_,@JCYB_,@JCBS_,@TH_,@THYB_,@THBS_,
		@XPXG_,@XPXGYB_,@XPXGBS_,@XPZC_,@XPZCYB_,@XPZCBS_,@XPSC_,@XPSCYB_,@XPSCBS_,@CTOD_,@DTOC_)

	print '切板成品在库台账商品编码3结束'
	
END
	--delete from SINO_REPORT_CPCKTZ	
	--CPCKTZ_RPOC 
	
	--ALTER TABLE SINO_REPORT_CPCKTZ ALTER COLUMN JCBS NUMERIC(20,0)
	
	--SELECT * FROM SINO_REPORT_CPCKTZ
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [CPCKTZ_JCP_RPOC]
 -- @tjdate datetime 
AS
--成品仓库台账
    DECLARE @tjdate datetime
	DECLARE @RIQI_ datetime    --
	DECLARE @CPLB_ varchar(1)  --成品类别　１、卷成品　２、中间品　３、剪切材
	DECLARE @CHOU_ varchar(1)  --商品编码　
	DECLARE @RICC_ numeric(9,3) --日产出合计
	DECLARE @RSYB_ numeric(9,3) --日产出一般贸易
	DECLARE @RSBS_ numeric(9,3) --日产出保税
	DECLARE @RICK_ numeric(9,3) --日出货合计
	DECLARE @RCHY_ numeric(9,3) --日出库一般贸易
	DECLARE @RCBS_ numeric(9,3) --日出库保税
	DECLARE @JIEC_ numeric(20,0) --结存合计
	DECLARE @JCYB_ numeric(20,0) --一般贸易结存
	DECLARE @JCBS_ numeric(20,0) --保税品结存
	DECLARE @TH_   numeric(9,3) --退货合计
	DECLARE @THYB_ numeric(9,3) --退货一般贸易　
	DECLARE @THBS_ numeric(9,3) --退货保税
	DECLARE @CTOD_ numeric(9,3) --C堆场转D
	DECLARE @DTOC_ numeric(9,3) --D堆场转C
	DECLARE @XPXG_ numeric(9,3) --现品情报修改合计
	DECLARE @XPXGYB_ numeric(9,3) --现品情报修改一般贸易
	DECLARE @XPXGBS_ numeric(9,3) --现品情报修改保税
	DECLARE @XPZC_ numeric(9,3) --现品情报作成合计
	DECLARE @XPZCYB_ numeric(9,3) --现品情报作成一般贸易
	DECLARE @XPZCBS_ numeric(9,3) --现品情报作成保税
	DECLARE @XPSC_ numeric(9,3) --现品情报删除合计
	DECLARE @XPSCYB_ numeric(9,3) --现品情报删除一般贸易
	DECLARE @XPSCBS_ numeric(9,3) --现品情报删除保税
	DECLARE @FHQX_  numeric(9,3)  --发货取消
	
	
BEGIN
	print '往成品仓库台账表插入每日统计数据--'
--商品编号１厚度<0.3	
	--卷成品每日产出量
	SET @tjdate=GETDATE()-1
  SELECT @RICC_=SUM(ZPZL_) ,
         @RSYB_=SUM(CASE WHEN SFBS_='1' THEN ZPZL_ ELSE 0 END),
         @RSBS_=SUM(CASE WHEN SFBS_='2' THEN ZPZL_ ELSE 0 END)
  FROM  SINO_ZPNGTP 
  WHERE DUIC_='C' AND spbh_='1' and
         DATEDIFF(DY,ETSD_,@tjdate)=0
    --卷成品每日发货量     
  SELECT @RICK_=SUM(ZPZL_) ,
         @RCHY_=SUM(CASE WHEN SFBS_='1' THEN ZPZL_ ELSE 0 END) ,
         @RCBS_=SUM(CASE WHEN SFBS_='2' THEN ZPZL_ ELSE 0 END)
  FROM   SINO_ZPNGTP
  WHERE  SCBJ_='3' AND  DUIC_='C' AND SPBH_='1' AND
         datediff(Dy,CHSD_,@tjdate)=0
  --发货取消量
   
  --堆场转换   
  SELECT @CTOD_=SUM(CASE WHEN NDUIC_='C' AND ODUIC_='D' THEN A.ZPZL_ ELSE 0 END),--C堆场转中间品
         @DTOC_=SUM(CASE WHEN NDUIC_='D' AND ODUIC_='C' THEN A.ZPZL_ ELSE 0 END)--中间品转C堆场
  FROM SINO_DCRZ A ,SINO_ZPNGTP B 
  WHERE A.JBNO_=B.JBNO_ AND B.SPBH_='1' AND datediff(Dy,A.XGSJ_,@tjdate)=0 
  --现品情报修改--修改为当天，但镀锡日不为当天，因为当天生产量的统计统计的为修改后的重量 修改量差： 修改后重量－修改前重量
  SELECT @XPXG_=sum(B.ZPZL_)-sum(A.ZPZL_) ,
         @XPXGYB_=SUM(CASE WHEN B.SFBS_='1' THEN B.ZPZL_-A.ZPZL_ ELSE 0 END),
         @XPXGBS_=SUM(CASE WHEN B.SFBS_='2' THEN B.ZPZL_-A.ZPZL_ ELSE 0 END)
  FROM SINO_XPXX A,SINO_ZPNGTP B 
  WHERE  A.JBNO_=B.JBNO_ AND B.DUIC_='C' AND SPBH_='1' AND A.MKLX_='0' AND datediff(Dy,B.ETSD_,@tjdate)<>0 AND datediff(Dy,SCSJ_,@tjdate)=0  
  --现品情报作成,作成为当天 但生成制品的镀锡日不为当天，因为当天生产量的统计已经包含了当天镀锡的重量
   
  SELECT @XPZC_=SUM(A.ZPZL_),
       @XPZCYB_=SUM(CASE WHEN A.SFBS_='1' THEN A.ZPZL_ ELSE 0 END),
       @XPZCBS_=SUM(CASE WHEN A.SFBS_='2' THEN A.ZPZL_ ELSE 0 END)     
  FROM SINO_ZPNGTP A,
      (SELECT CONVERT(VARCHAR(10),SCSJ_,120) SCSJ_,NJBNO_ FROM SINO_XPXX WHERE MKLX_='1'  GROUP BY NJBNO_,CONVERT(VARCHAR(10),SCSJ_,120)) B
  WHERE A.JBNO_ = B.NJBNO_ AND A.DUIC_='C' AND A.SPBH_='1' AND datediff(Dy,B.SCSJ_,@tjdate)=0 AND datediff(Dy,A.ETSD_,@tjdate)<>0
 --现品情报删除,删除为当天 但生成制品的镀锡日不为当天，因为当天生产量的统计不包含该重量
   
  SELECT @XPSC_=SUM(A.ZPZL_),
       @XPSCYB_=SUM(CASE WHEN A.SFBS_='1' THEN A.ZPZL_ ELSE 0 END),
       @XPSCBS_=SUM(CASE WHEN A.SFBS_='2' THEN A.ZPZL_ ELSE 0 END)     
  FROM SINO_ZPNGTP A,
      (SELECT CONVERT(VARCHAR(10),XGSJ_,120) XGSJ_,JBNO_ FROM SINO_XPXX WHERE MKLX_='2'  GROUP BY JBNO_,CONVERT(VARCHAR(10),XGSJ_,120)) B
  WHERE A.JBNO_ = B.JBNO_ AND A.DUIC_='C' AND A.SPBH_='1' AND datediff(Dy,B.XGSJ_,@tjdate)=0 AND datediff(Dy,A.ETSD_,@tjdate)<>0
     
 --结存
 SELECT
    @JIEC_=SUM(case when  SCBJ_='0' then zpzl_ else 0 end) ,
    @JCYB_=SUM(case when  SCBJ_='0' and sfbs_='1' then zpzl_ else 0 end),
    @JCBS_=SUM(case when  SCBJ_='0' and sfbs_='2' then zpzl_ else 0 end) 
 FROM sino_zpngtp A   where  A.DUIC_='C' AND SPBH_='1'
 
 --每日退货--建议增加商品编号和是否保税字段
  SELECT @TH_=SUM(A.ZPZL_) 
  FROM SINO_TS A ,SINO_TH B,SINO_DHUOTP C 
  WHERE A.ID_ = B.TSTP_ AND C.PZNO_ LIKE '2%' AND A.DHNO_=C.DHNO_ AND A.LINE_=C.LINE_  
    AND DATEDIFF(DY,B.THRI_, @tjdate)=0 AND CAST(C.HOUU_ AS NUMERIC(6,3))<0.3   
	
	insert into Sino.dbo.SINO_REPORT_CPCKTZ (
		RIQI,CPLB,CHOU,RICC,RSYB,RSBS,RICK,RCHY,RCBS,JIEC,JCYB,JCBS,TH,THYB,THBS,
		XPXG,XPXGYB,XPXGBS,XPZC,XPZCYB,XPZCBS,XPSC,XPSCYB,XPSCBS,CTOD,DTOC
	)
	values(
		@tjdate,'2', '1', @RICC_,@RSYB_,@RSBS_,@RICK_,@RCHY_,@RCBS_,@JIEC_,@JCYB_,@JCBS_,@TH_,@THYB_,@THBS_,
		@XPXG_,@XPXGYB_,@XPXGBS_,@XPZC_,@XPZCYB_,@XPZCBS_,@XPSC_,@XPSCYB_,@XPSCBS_,@CTOD_,@DTOC_)

	print '卷成品在库台账商品编码１结束'
	
	
	--商品编号２	
	--卷成品每日产出量
	
  SELECT @RICC_=SUM(ZPZL_) ,
         @RSYB_=SUM(CASE WHEN SFBS_='1' THEN ZPZL_ ELSE 0 END),
         @RSBS_=SUM(CASE WHEN SFBS_='2' THEN ZPZL_ ELSE 0 END)
  FROM  SINO_ZPNGTP 
  WHERE DUIC_='C' AND spbh_='2' and
         DATEDIFF(DY,ETSD_,@tjdate)=0
    --卷成品每日发货量     
  SELECT @RICK_=SUM(ZPZL_) ,
         @RCHY_=SUM(CASE WHEN SFBS_='1' THEN ZPZL_ ELSE 0 END) ,
         @RCBS_=SUM(CASE WHEN SFBS_='2' THEN ZPZL_ ELSE 0 END)
  FROM   SINO_ZPNGTP
  WHERE  SCBJ_='3' AND  DUIC_='C' AND SPBH_='2' AND
         datediff(Dy,CHSD_,@tjdate)=0
  --发货取消量
   
  --堆场转换   
  SELECT @CTOD_=SUM(CASE WHEN NDUIC_='C' AND ODUIC_='D' THEN A.ZPZL_ ELSE 0 END),--C堆场转中间品
         @DTOC_=SUM(CASE WHEN NDUIC_='D' AND ODUIC_='C' THEN A.ZPZL_ ELSE 0 END)--中间品转C堆场
  FROM SINO_DCRZ A ,SINO_ZPNGTP B 
  WHERE A.JBNO_=B.JBNO_ AND B.SPBH_='2' AND datediff(Dy,A.XGSJ_,@tjdate)=0 
  --现品情报修改--修改为当天，但镀锡日不为当天，因为当天生产量的统计统计的为修改后的重量 修改量差： 修改后重量－修改前重量
  SELECT @XPXG_=sum(B.ZPZL_)-sum(A.ZPZL_) ,
         @XPXGYB_=SUM(CASE WHEN B.SFBS_='1' THEN B.ZPZL_-A.ZPZL_ ELSE 0 END),
         @XPXGBS_=SUM(CASE WHEN B.SFBS_='2' THEN B.ZPZL_-A.ZPZL_ ELSE 0 END)
  FROM SINO_XPXX A,SINO_ZPNGTP B 
  WHERE  A.JBNO_=B.JBNO_ AND B.DUIC_='C' AND SPBH_='2' AND A.MKLX_='0' AND datediff(Dy,B.ETSD_,@tjdate)<>0 AND datediff(Dy,SCSJ_,@tjdate)=0  
  --现品情报作成,作成为当天 但生成制品的镀锡日不为当天，因为当天生产量的统计已经包含了当天镀锡的重量
   
  SELECT @XPZC_=SUM(A.ZPZL_),
       @XPZCYB_=SUM(CASE WHEN A.SFBS_='1' THEN A.ZPZL_ ELSE 0 END),
       @XPZCBS_=SUM(CASE WHEN A.SFBS_='2' THEN A.ZPZL_ ELSE 0 END)     
  FROM SINO_ZPNGTP A,
      (SELECT CONVERT(VARCHAR(10),SCSJ_,120) SCSJ_,NJBNO_ FROM SINO_XPXX WHERE MKLX_='1'  GROUP BY NJBNO_,CONVERT(VARCHAR(10),SCSJ_,120)) B
  WHERE A.JBNO_ = B.NJBNO_ AND A.DUIC_='C' AND A.SPBH_='2' AND datediff(Dy,B.SCSJ_,@tjdate)=0 AND datediff(Dy,A.ETSD_,@tjdate)<>0
 --现品情报删除,删除为当天 但生成制品的镀锡日不为当天，因为当天生产量的统计不包含该重量
   
  SELECT @XPSC_=SUM(A.ZPZL_),
       @XPSCYB_=SUM(CASE WHEN A.SFBS_='1' THEN A.ZPZL_ ELSE 0 END),
       @XPSCBS_=SUM(CASE WHEN A.SFBS_='2' THEN A.ZPZL_ ELSE 0 END)     
  FROM SINO_ZPNGTP A,
      (SELECT CONVERT(VARCHAR(10),XGSJ_,120) XGSJ_,JBNO_ FROM SINO_XPXX WHERE MKLX_='2'  GROUP BY JBNO_,CONVERT(VARCHAR(10),XGSJ_,120)) B
  WHERE A.JBNO_ = B.JBNO_ AND A.DUIC_='C' AND A.SPBH_='2' AND datediff(Dy,B.XGSJ_,@tjdate)=0 AND datediff(Dy,A.ETSD_,@tjdate)<>0
     
 --结存
 SELECT
    @JIEC_=SUM(case when  SCBJ_='0' then zpzl_ else 0 end) ,
    @JCYB_=SUM(case when  SCBJ_='0' and sfbs_='1' then zpzl_ else 0 end),
    @JCBS_=SUM(case when  SCBJ_='0' and sfbs_='2' then zpzl_ else 0 end) 
 FROM sino_zpngtp A   where  A.DUIC_='C' AND SPBH_='2'
 
 --每日退货--建议增加商品编号和是否保税字段
  SELECT @TH_=SUM(A.ZPZL_) 
  FROM SINO_TS A ,SINO_TH B,SINO_DHUOTP C 
  WHERE A.ID_ = B.TSTP_ AND C.PZNO_ LIKE '2%' AND A.DHNO_=C.DHNO_ AND A.LINE_=C.LINE_  
    AND DATEDIFF(DY,B.THRI_, @tjdate)=0 AND CAST(C.HOUU_ AS NUMERIC(6,3))>=0.3 AND  CAST(C.HOUU_ AS NUMERIC(6,3))<0.5  
	
	insert into Sino.dbo.SINO_REPORT_CPCKTZ (
		RIQI,CPLB,CHOU,RICC,RSYB,RSBS,RICK,RCHY,RCBS,JIEC,JCYB,JCBS,TH,THYB,THBS,
		XPXG,XPXGYB,XPXGBS,XPZC,XPZCYB,XPZCBS,XPSC,XPSCYB,XPSCBS,CTOD,DTOC
	)
	values(
		@tjdate,'2', '2', @RICC_,@RSYB_,@RSBS_,@RICK_,@RCHY_,@RCBS_,@JIEC_,@JCYB_,@JCBS_,@TH_,@THYB_,@THBS_,
		@XPXG_,@XPXGYB_,@XPXGBS_,@XPZC_,@XPZCYB_,@XPZCBS_,@XPSC_,@XPSCYB_,@XPSCBS_,@CTOD_,@DTOC_)

	print '卷成品在库台账商品编码2结束'
	
	
	--商品编号3厚度<0.3	
	--卷成品每日产出量
	
  SELECT @RICC_=SUM(ZPZL_) ,
         @RSYB_=SUM(CASE WHEN SFBS_='1' THEN ZPZL_ ELSE 0 END),
         @RSBS_=SUM(CASE WHEN SFBS_='2' THEN ZPZL_ ELSE 0 END)
  FROM  SINO_ZPNGTP 
  WHERE DUIC_='C' AND spbh_='3' and
         DATEDIFF(DY,ETSD_,@tjdate)=0
    --卷成品每日发货量     
  SELECT @RICK_=SUM(ZPZL_) ,
         @RCHY_=SUM(CASE WHEN SFBS_='1' THEN ZPZL_ ELSE 0 END) ,
         @RCBS_=SUM(CASE WHEN SFBS_='2' THEN ZPZL_ ELSE 0 END)
  FROM   SINO_ZPNGTP
  WHERE  SCBJ_='3' AND  DUIC_='C' AND SPBH_='3' AND
         datediff(Dy,CHSD_,@tjdate)=0
  --发货取消量
   
  --堆场转换   
  SELECT @CTOD_=SUM(CASE WHEN NDUIC_='C' AND ODUIC_='D' THEN A.ZPZL_ ELSE 0 END),--C堆场转中间品
         @DTOC_=SUM(CASE WHEN NDUIC_='D' AND ODUIC_='C' THEN A.ZPZL_ ELSE 0 END)--中间品转C堆场
  FROM SINO_DCRZ A ,SINO_ZPNGTP B 
  WHERE A.JBNO_=B.JBNO_ AND B.SPBH_='3' AND datediff(Dy,A.XGSJ_,@tjdate)=0 
  --现品情报修改--修改为当天，但镀锡日不为当天，因为当天生产量的统计统计的为修改后的重量 修改量差： 修改后重量－修改前重量
  SELECT @XPXG_=sum(B.ZPZL_)-sum(A.ZPZL_) ,
         @XPXGYB_=SUM(CASE WHEN B.SFBS_='1' THEN B.ZPZL_-A.ZPZL_ ELSE 0 END),
         @XPXGBS_=SUM(CASE WHEN B.SFBS_='2' THEN B.ZPZL_-A.ZPZL_ ELSE 0 END)
  FROM SINO_XPXX A,SINO_ZPNGTP B 
  WHERE  A.JBNO_=B.JBNO_ AND B.DUIC_='C' AND SPBH_='3' AND A.MKLX_='0' AND datediff(Dy,B.ETSD_,@tjdate)<>0 AND datediff(Dy,SCSJ_,@tjdate)=0  
  --现品情报作成,作成为当天 但生成制品的镀锡日不为当天，因为当天生产量的统计已经包含了当天镀锡的重量
   
  SELECT @XPZC_=SUM(A.ZPZL_),
       @XPZCYB_=SUM(CASE WHEN A.SFBS_='1' THEN A.ZPZL_ ELSE 0 END),
       @XPZCBS_=SUM(CASE WHEN A.SFBS_='2' THEN A.ZPZL_ ELSE 0 END)     
  FROM SINO_ZPNGTP A,
      (SELECT CONVERT(VARCHAR(10),SCSJ_,120) SCSJ_,NJBNO_ FROM SINO_XPXX WHERE MKLX_='1'  GROUP BY NJBNO_,CONVERT(VARCHAR(10),SCSJ_,120)) B
  WHERE A.JBNO_ = B.NJBNO_ AND A.DUIC_='C' AND A.SPBH_='3' AND datediff(Dy,B.SCSJ_,@tjdate)=0 AND datediff(Dy,A.ETSD_,@tjdate)<>0
 --现品情报删除,删除为当天 但生成制品的镀锡日不为当天，因为当天生产量的统计不包含该重量
   
  SELECT @XPSC_=SUM(A.ZPZL_),
       @XPSCYB_=SUM(CASE WHEN A.SFBS_='1' THEN A.ZPZL_ ELSE 0 END),
       @XPSCBS_=SUM(CASE WHEN A.SFBS_='2' THEN A.ZPZL_ ELSE 0 END)     
  FROM SINO_ZPNGTP A,
      (SELECT CONVERT(VARCHAR(10),XGSJ_,120) XGSJ_,JBNO_ FROM SINO_XPXX WHERE MKLX_='2'  GROUP BY JBNO_,CONVERT(VARCHAR(10),XGSJ_,120)) B
  WHERE A.JBNO_ = B.JBNO_ AND A.DUIC_='C' AND A.SPBH_='3' AND datediff(Dy,B.XGSJ_,@tjdate)=0 AND datediff(Dy,A.ETSD_,@tjdate)<>0
     
 --结存
 SELECT
    @JIEC_=SUM(case when  SCBJ_='0' then zpzl_ else 0 end) ,
    @JCYB_=SUM(case when  SCBJ_='0' and sfbs_='1' then zpzl_ else 0 end),
    @JCBS_=SUM(case when  SCBJ_='0' and sfbs_='2' then zpzl_ else 0 end) 
 FROM sino_zpngtp A   where  A.DUIC_='C' AND SPBH_='3'
 
 --每日退货--建议增加商品编号和是否保税字段
  SELECT @TH_=SUM(A.ZPZL_) 
  FROM SINO_TS A ,SINO_TH B,SINO_DHUOTP C 
  WHERE A.ID_ = B.TSTP_ AND C.PZNO_ LIKE '2%' AND A.DHNO_=C.DHNO_ AND A.LINE_=C.LINE_  
    AND DATEDIFF(DY,B.THRI_, @tjdate)=0 AND CAST(C.HOUU_ AS NUMERIC(6,3))>=0.5  
	
	insert into Sino.dbo.SINO_REPORT_CPCKTZ (
		RIQI,CPLB,CHOU,RICC,RSYB,RSBS,RICK,RCHY,RCBS,JIEC,JCYB,JCBS,TH,THYB,THBS,
		XPXG,XPXGYB,XPXGBS,XPZC,XPZCYB,XPZCBS,XPSC,XPSCYB,XPSCBS,CTOD,DTOC
	)
	values(
		@tjdate,'2', '3', @RICC_,@RSYB_,@RSBS_,@RICK_,@RCHY_,@RCBS_,@JIEC_,@JCYB_,@JCBS_,@TH_,@THYB_,@THBS_,
		@XPXG_,@XPXGYB_,@XPXGBS_,@XPZC_,@XPZCYB_,@XPZCBS_,@XPSC_,@XPSCYB_,@XPSCBS_,@CTOD_,@DTOC_)

	print '卷成品在库台账商品编码3结束'
	
END
	--delete from SINO_REPORT_CPCKTZ	
	--CPCKTZ_RPOC 
	
	--ALTER TABLE SINO_REPORT_CPCKTZ ALTER COLUMN JCBS NUMERIC(20,0)
	
	--SELECT * FROM SINO_REPORT_CPCKTZ
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [SINO_V_CPCKTZ_JCPTH] AS
--卷成品每日退货量
SELECT SUM(A.ZPZL_) THZL_,B.THRI_ 
  FROM SINO_TS A ,SINO_TH B,SINO_DHUOTP C 
  WHERE A.ID_ = B.TSTP_ AND C.PZNO_ LIKE '2%' AND A.DHNO_=C.DHNO_ AND A.LINE_=C.LINE_  
  GROUP BY B.THRI_
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [SINO_V_CPCKTZ_JCPCH] AS 
--1、卷成品每日发货量
 SELECT SUM(B.CHZL_) AS CHZL,B.CHRI_ AS CHRI
    FROM  SINO_ZXZS B LEFT JOIN SINO_ZNG1TP C
    ON B.ID_=C.ZXZSTP_ WHERE B.STAT_='1' AND C.PZNO_ LIKE '2%' AND 
    datepart(yy,getdate()) =datepart(yy,B.CHRI_) and datepart(mm,getdate()) =datepart(mm,B.CHRI_)
    GROUP BY B.CHRI_
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [SINO_V_SLZRL] AS
--2、查出入侧卷板的装入重量作为SL的装入量
select SUM(a.zrzl_) AS ZRZL_,A.JBNO_ from sino_zpngtp a,sino_v_slby b   --在原材卷板DB和制品在库DB中增加装入卷重，如果有半切中止，则装入卷重为原卷重－半切中止的重量。
where a.jbno_= b.ycno and
 SCBJ_<>'0'  --镀锡卷已终了
and datepart(yy,getdate()) =datepart(yy,a.sjsj_) and datepart(mm,getdate()) =datepart(mm,a.sjsj_) GROUP BY A.JBNO_--sl实绩完成时间为本月
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create VIEW [SINO_V_DIS_YCBSQD] AS 
--原材保税品在库清单-FOR 保税品在库清单
SELECT A.JBNO_,A.ZPZL_,convert(varchar(12),A.ETSD,111) as ETSD,A.SPBH_,'1' AS 保, A.ZRZL_ AS ETLZRZL,A.一级 AS ETL一级,A.二级 as ETL二级,A.三级 as ETL三级,A.四级 as ETL四级,(A.一级+A.二级+A.三级+A.四级) AS 计1,A.屑 as ETL屑,
      convert(varchar(12),B.SLSD_,111) AS SLSD_,B.ZRZL_ AS SLZRZL_,B.一级,B.二级,B.三级,B.四级,(b.一级+b.二级+b.三级+b.四级) AS 计2,B.屑
FROM SINO_V_BSQDGL1 A LEFT JOIN SINO_V_BSQDGL2 B ON A.JBNO_=B.JBNO_
GO
