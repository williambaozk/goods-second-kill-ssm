-- 定义存储过程
-- row_count():返回上一条修改类型 0：未修改 >0：修改行数 <0:sql出错/未执行sql

DELIMITER $$
CREATE PROCEDURE execute_goodKill
  (in v_good_id bigint,in v_phone bigint,in v_kill_time timestamp,out r_result int)
  BEGIN
    DECLARE insert_count int DEFAULT 0;
    start TRANSACTION ;
    INSERT IGNORE INTO successkill(good_id,user_phone,create_time)
      VALUES (v_good_id,v_phone,v_kill_time);
    select row_count() into insert_count ;
    IF (insert_count = 0) THEN
      ROLLBACK ;
      set r_result=-1;
    ELSEIF (insert_count<0) THEN
      ROLLBACK ;
      SET r_result=-2;
    ELSE
      UPDATE goods set number=number-1
      WHERE good_id=v_good_id and end_time>v_kill_time and start_time<v_kill_time and number>0;
      SELECT row_count() into insert_count;
      IF (insert_count=0) THEN
        ROLLBACK ;
        SET r_result=0;
      ELSEIF (insert_count<0) THEN
        ROLLBACK ;
        SET r_result=-2;
      ELSE
        COMMIT ;
        SET r_result=1;
      END IF;
    END IF;
  END;
$$
-- 存储过程定义结束
DELIMITER ;

set @r_result=-3;
-- 执行存储过程
call execute_goodKill(1003,18767186834,now(),@r_result);
-- 获取结果
SELECT @r_result;