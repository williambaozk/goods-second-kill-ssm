package com.william.dao;

import com.william.entity.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Baozhikuan on 2018/8/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class GoodsDaoTest {
    @Resource
    private GoodsDao goodsDao;
    @Test
    public void queryById() throws Exception {
        long id=1000;
        Goods goods=goodsDao.queryById(id);
        System.out.println(goods.getName());
        System.out.println(goods);
    }

    @Test
    public void reduceNumber() throws Exception {
        long id=1000;
        Date date=new Date();
        int return_number=goodsDao.reduceNumber(id,date);
        System.out.println(return_number);
    }

    @Test
    public void queryAll() throws Exception {
        List<Goods> goods=goodsDao.queryAll(0,100);
        for(Goods good:goods){
            System.out.println(good);
        }
    }

}