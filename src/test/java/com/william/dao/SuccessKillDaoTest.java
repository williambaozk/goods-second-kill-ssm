package com.william.dao;

import com.william.entity.Successkill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Baozhikuan on 2018/8/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKillDaoTest {
    @Resource
    private SuccessKillDao successKillDao;
    @Test
    public void insertSuccessKill() throws Exception {
        long goodid=1000;
        long userPhone=18767186837L;
        System.out.println(successKillDao.insertSuccessKill(goodid,userPhone));
    }

    @Test
    public void querySuccessByGoodId() throws Exception {
        long goodid=1000;
        long userPhone=18767186837L;
        Successkill successkill=successKillDao.querySuccessByGoodId(goodid,userPhone);
        System.out.println(successkill);
        System.out.println(successkill.getGood());
    }

}