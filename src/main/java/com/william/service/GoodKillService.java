package com.william.service;

import com.william.dto.Exposer;
import com.william.dto.GoodExecution;
import com.william.entity.Goods;
import com.william.exception.GoodCloseException;
import com.william.exception.GoodException;
import com.william.exception.RepeatKillException;

import java.util.List;

/**
 * Created by Baozhikuan on 2018/8/19.
 */
public interface GoodKillService {
    /**
     * 查询所有秒杀
     * @return
     */
    List<Goods> getGoodList();

    /**
     * 查询单个秒杀
     * @param goodId
     * @return
     */
    Goods getById(long goodId);

    /**
     * 秒杀开始时输出秒杀地址
     * 否则输出系统时间和秒杀时间
     * @param goodId
     * @return
     */
    Exposer exportGoodUrl(long goodId);
    /**
     * 执行秒杀
     */
    GoodExecution executeGoodKill(long goodId, long userPhone, String md5 )
        throws GoodException,GoodCloseException,RepeatKillException;

    /**
     * 存储过程执行秒杀
     * @param goodId
     * @param userPhone
     * @param md5
     * @return
     * @throws GoodException
     * @throws GoodCloseException
     * @throws RepeatKillException
     */
    GoodExecution executeGoodKillProcedure(long goodId, long userPhone, String md5 );
}
