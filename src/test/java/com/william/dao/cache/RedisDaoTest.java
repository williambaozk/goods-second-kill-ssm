package com.william.dao.cache;

import com.william.dao.GoodsDao;
import com.william.entity.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Baozhikuan on 2018/8/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {
    private long id=1001;
    @Autowired
    private RedisDao redisDao;
    @Autowired
    private GoodsDao goodsDao;
    @Test
    public void testGood() throws Exception {
        Goods goods=redisDao.getGood(id);
        if(goods==null){
            goods=goodsDao.queryById(id);
            if(goods!=null){
                String result=redisDao.putGood(goods);
                System.out.println(result);
                goods=redisDao.getGood(id);
                System.out.println(goods);
            }
        }

    }

}