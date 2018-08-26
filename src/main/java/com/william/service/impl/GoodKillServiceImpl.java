package com.william.service.impl;
import com.william.dao.GoodsDao;
import com.william.dao.SuccessKillDao;
import com.william.dao.cache.RedisDao;
import com.william.dto.Exposer;
import com.william.dto.GoodExecution;
import com.william.entity.Goods;
import com.william.entity.Successkill;
import com.william.enums.GoodStateEnum;
import com.william.exception.GoodCloseException;
import com.william.exception.GoodException;
import com.william.exception.RepeatKillException;
import com.william.service.GoodKillService;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Baozhikuan on 2018/8/19.
 */
@Service
public class GoodKillServiceImpl implements GoodKillService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //注入service依赖
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private SuccessKillDao successKillDao;
    @Autowired
    private RedisDao redisDao;

    private final String slat="jdifwjfvfieji149858jie**9";
    @Override
    public List<Goods> getGoodList() {
        return goodsDao.queryAll(0,8);
    }

    @Override
    public Goods getById(long goodId) {
        return goodsDao.queryById(goodId);
    }

    @Override
    public Exposer exportGoodUrl(long goodId) {
        //Goods good=goodsDao.queryById(goodId);
        Goods good=redisDao.getGood(goodId);
        if(good==null){
            good=goodsDao.queryById(goodId);
            if(good==null){
                return new Exposer(false,goodId);
            }else{
                redisDao.putGood(good);
            }
        }
        if(good==null){
            return new Exposer(false,goodId);
        }
        Date startTime=good.getStartTime();
        Date endTime=good.getEndTime();
        Date nowTime=new Date();
        if(nowTime.getTime()<startTime.getTime() || nowTime.getTime()>endTime.getTime()){
            return new Exposer(false,goodId,nowTime.getTime(),startTime.getTime(),endTime.getTime());
        }
        String md5=getMD5(goodId);
        return new Exposer(true,md5,goodId);
    }
    private String getMD5(long goodId){
        String base=goodId+"/"+slat;
        String md5= DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
    @Transactional
    @Override
    public GoodExecution executeGoodKill(long goodId, long userPhone, String md5) throws GoodException, GoodCloseException, RepeatKillException {
        if(md5==null || !md5.equals(getMD5(goodId))){
            throw new GoodException("goodurl was rewrite");
        }
        Date nowTime=new Date();
        try{
            int insertCoun=successKillDao.insertSuccessKill(goodId,userPhone);
            if(insertCoun<=0){
                throw new RepeatKillException("repeat kill");
            }else{
                int updateCount=goodsDao.reduceNumber(goodId,nowTime);
                if(updateCount<=0){
                    throw new GoodCloseException("soodkill is closed");
                }else{
                    Successkill successkill=successKillDao.querySuccessByGoodId(goodId,userPhone);
                    return new GoodExecution(goodId, GoodStateEnum.SUCCESS,successkill);
                }
            }
        }catch (GoodCloseException e1){
            throw e1;
        }catch(RepeatKillException e2){
            throw e2;
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new GoodException("kill inner error"+e.getMessage());
        }
    }

    @Override
    public GoodExecution executeGoodKillProcedure(long goodId, long userPhone, String md5){
        if(md5==null || !md5.equals(getMD5(goodId))){
            return new GoodExecution(goodId,GoodStateEnum.DATA_REWRITE);
        }
        Date killTime=new Date();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("goodId",goodId);
        map.put("phone",userPhone);
        map.put("killTime",killTime);
        map.put("result",null);
        try{
            goodsDao.killByProcedure(map);
            int result= MapUtils.getInteger(map,"result",-2);
            if(result==1){
                Successkill sk=successKillDao.querySuccessByGoodId(goodId,userPhone);
                return new GoodExecution(goodId,GoodStateEnum.SUCCESS,sk);
            }else{
                return new GoodExecution(goodId,GoodStateEnum.stateOf(result));
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new GoodExecution(goodId,GoodStateEnum.INNER_ERROR);
        }
    }

}
