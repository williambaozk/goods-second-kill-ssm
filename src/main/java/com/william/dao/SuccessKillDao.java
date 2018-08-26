package com.william.dao;

import com.william.entity.Successkill;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Baozhikuan on 2018/8/18.
 */

public interface SuccessKillDao {
    int insertSuccessKill(@Param("goodId") long goodId,@Param("userPhone") long userPhone);

    Successkill querySuccessByGoodId(@Param("goodId") long goodId,@Param("userPhone") long userPhone);
}
