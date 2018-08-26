package com.william.dao;

import com.william.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Baozhikuan on 2018/8/18.
 */
public interface GoodsDao {
    int reduceNumber(@Param("goodId") long goodId,@Param("killTime") Date killTime);

    Goods queryById(long goodId);

    List<Goods> queryAll(@Param("offset") int offset,@Param("limit") int limit);

    /**
     * 使用存储过程执行秒杀
     * @param paramMap
     */
    void killByProcedure(Map<String,Object> paramMap);
}
