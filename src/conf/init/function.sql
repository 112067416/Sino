-- =============================================
-- Author:		方元龙
-- Create date: 2011-02-09
-- Description:	根据入端号找到其对应的中止卷号
-- =============================================
CREATE FUNCTION [dbo].[GET_ZZNO]
(
	@RCZPNO VARCHAR(11)	--装入中止前的卷号
)
RETURNS VARCHAR(11)
AS
BEGIN

	DECLARE @ZZNO VARCHAR(11)	--中止卷号,用于返回
	DECLARE @TAIL INT			--已中止卷号的最后一位
	DECLARE @LEN INT

	-- 非空判断
	IF @RCZPNO IS NULL BEGIN
		SET @ZZNO = ''
	END
	ELSE BEGIN
		-- 长度判断
		SET @RCZPNO = LTRIM(RTRIM(@RCZPNO))
		SET @LEN = LEN(@RCZPNO)
		IF @LEN < 6 OR @LEN > 9 BEGIN
			SET @ZZNO = ''
		END
		ELSE BEGIN

			-- 原板/中间卷
			IF @LEN = 6 OR @LEN = 8 BEGIN
				SET @ZZNO = @RCZPNO + '1'
			END
			ELSE BEGIN
				-- 已产生中止卷的原板
				IF @LEN = 7 BEGIN
					SET @TAIL = CAST(SUBSTRING(@RCZPNO, 6, 1) AS INT)
					IF(@TAIL IS NULL OR @TAIL <= 0) BEGIN
						SET @ZZNO = ''
					END
					ELSE BEGIN
						SET @ZZNO = SUBSTRING(@RCZPNO, 1, 6) + CAST(@TAIL + 1 AS VARCHAR(1))
					END
				END

				-- 中间卷
				IF @LEN = 9 BEGIN
					SET @TAIL = CAST(SUBSTRING(@RCZPNO, 8, 1) AS INT)
					IF(@TAIL IS NULL OR @TAIL <= 0) 
						SET @ZZNO = ''
					ELSE BEGIN
						SET @ZZNO = SUBSTRING(@RCZPNO, 1, 8) + CAST(@TAIL + 1 AS VARCHAR(1))
					END
				END
			END
		END
	END
	RETURN @ZZNO
END

GO