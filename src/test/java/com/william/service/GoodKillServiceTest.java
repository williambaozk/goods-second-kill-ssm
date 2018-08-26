package com.william.service;

import com.william.dto.Exposer;
import com.william.dto.GoodExecution;
import com.william.entity.Goods;
import com.william.exception.GoodCloseException;
import com.william.exception.GoodException;
import com.william.exception.RepeatKillException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Baozhikuan on 2018/8/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class GoodKillServiceTest {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private GoodKillService goodKillService;
    @Test
    public void getGoodList() throws Exception {
        List<Goods> list=goodKillService.getGoodList();
        logger.info("list{}",list);
    }

    @Test
    public void getById() throws Exception {
        long id=1000;
        Goods goods=goodKillService.getById(id);
        logger.info("goods={}",goods);
    }

    @Test
    public void exportGoodUrl() throws Exception {
        long id=1000;
        Exposer exposer=goodKillService.exportGoodUrl(id);
        if(exposer.isExposed()){
            logger.info("exposer={}",exposer);
            long userPhone=18767186836L;
            try{
                GoodExecution goodExecution=goodKillService.executeGoodKill(id,userPhone,exposer.getMd5());
                logger.info("goodExecution={}",goodExecution);
            }catch (RepeatKillException e1){
                logger.error(e1.getMessage());
            }catch (GoodCloseException e2){
                logger.error(e2.getMessage());
            }
        }else {
            logger.warn("exposer={}",exposer);
        }

    }

    @Test
    public void executeGoodKill() throws Exception {
        long id=1000;
        long userPhone=18786889665L;
        GoodExecution goodExecution=goodKillService.executeGoodKill(id,userPhone,"74cb02dc260480508c0a40fca566b41c");
        logger.info("goodExecution={}",goodExecution);
    }
    @Test
    public void executeGoodKillProcedure(){
        long goodId=1000;
        long phone=18767186833L;
        Exposer exposer=goodKillService.exportGoodUrl(goodId);
        if(exposer.isExposed()){
            String md5=exposer.getMd5();
            GoodExecution goodExecution=goodKillService.executeGoodKillProcedure(goodId,phone,md5);
            logger.info(goodExecution.getStateInfo());
        }
    }
}