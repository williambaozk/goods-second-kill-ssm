package com.william.web;

import com.william.dto.Exposer;
import com.william.dto.GoodExecution;
import com.william.dto.GoodKillResult;
import com.william.entity.Goods;
import com.william.enums.GoodStateEnum;
import com.william.exception.GoodCloseException;
import com.william.exception.RepeatKillException;
import com.william.service.GoodKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 * Created by Baozhikuan on 2018/8/22.
 */
@Controller
@RequestMapping("/goodsKill")
public class GoodKillController {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private GoodKillService goodKillService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        List<Goods> list=goodKillService.getGoodList();
        model.addAttribute("list",list);
        return "list";
    }
    @RequestMapping(value="/{goodId}/detail",method=RequestMethod.GET)
    public String detail(@PathVariable("goodId") Long goodId, Model model){
        if(goodId==null){
            return "redirect:/goodsKill/list";
        }
        Goods good=goodKillService.getById(goodId);
        if(good==null){
            return "forward:/goodsKill/list";
        }
        model.addAttribute("good",good);
        return "detail";
    }

    @RequestMapping(value="/{goodId}/exposer",method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public GoodKillResult<Exposer> exposer(@PathVariable("goodId") Long goodId){
        GoodKillResult<Exposer> result;
        try{
            Exposer exposer=goodKillService.exportGoodUrl(goodId);
            result = new GoodKillResult<Exposer>(true,exposer);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            result= new GoodKillResult<Exposer>(false,e.getMessage());
        }
        return result;
    }

    @RequestMapping(value="/{goodId}/{md5}/execution",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public GoodKillResult<GoodExecution> execute(@PathVariable("goodId") Long goodId, @PathVariable("md5") String md5, @CookieValue(value = "killPhone",required = false) Long userPhone){
        if(userPhone == null){
            return new GoodKillResult<GoodExecution>(false,"未注册");
        }
        try{
            GoodExecution goodExecution=goodKillService.executeGoodKillProcedure(goodId,userPhone,md5);
            return new GoodKillResult<GoodExecution>(true,goodExecution);
        }catch(RepeatKillException e){
            GoodExecution execution=new GoodExecution(goodId, GoodStateEnum.REPEAT_KILL);
            return new GoodKillResult<GoodExecution>(true,execution);
        }catch (GoodCloseException e){
            GoodExecution execution=new GoodExecution(goodId, GoodStateEnum.END);
            return new GoodKillResult<GoodExecution>(true,execution);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            GoodExecution execution=new GoodExecution(goodId,GoodStateEnum.INNER_ERROR);
            return new GoodKillResult<GoodExecution>(true,execution);
        }
    }
    @RequestMapping(value = "/time/now",method =RequestMethod.GET )
    @ResponseBody
    public GoodKillResult<Long> time(){
        Date now =new Date();
        return new GoodKillResult<Long>(true,now.getTime());
    }
}
