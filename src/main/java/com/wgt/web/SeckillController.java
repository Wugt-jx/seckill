package com.wgt.web;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.wgt.domain.Seckill;
import com.wgt.dto.Exposer;
import com.wgt.dto.SeckillExecution;
import com.wgt.enums.ResponseEnum;
import com.wgt.dto.Response;
import com.wgt.exception.DataRewriteException;
import com.wgt.exception.RepeatKillException;
import com.wgt.exception.SeckillCloseException;
import com.wgt.exception.SeckillException;
import com.wgt.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/27.
 */
@RestController
public class SeckillController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;




    /**
     * 秒杀商品列表请求
     * @return
     */
    @RequestMapping(name = "/list",method = RequestMethod.GET)
    public Response<List<Seckill>> list(){
        List<Seckill>seckills = seckillService.getSeckillList();
        if (seckills == null){
            return new Response<>(ResponseEnum.NONE_RESULT);
        }
        logger.info("result={}",seckills);
        return new Response<List<Seckill>>(ResponseEnum.SUCCESS,seckills);
    }

    /**
     * 秒杀商品详情请求
     * @param seckillId
     * @return
     */
    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public Response<Seckill> detail(@PathVariable("seckillId") Long seckillId){
        if (seckillId == null){
            return new Response<>(ResponseEnum.LACK_PARAMS);
        }
        Seckill seckill = seckillService.getById(seckillId);
        if (seckill == null){
            return new Response<>(ResponseEnum.NONE_RESULT);
        }
        logger.info("result={}",seckill);
        return new Response<>(ResponseEnum.SUCCESS,seckill);
    }

    /**
     * 暴漏秒杀接口请求
     * @param seckillId
     * @return
     */
    @RequestMapping(value = "/{seckillId}/exposer",method = RequestMethod.POST)
    public Response<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
        if (seckillId==null){
            return new Response<>(ResponseEnum.LACK_PARAMS);
        }
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            logger.info("exposer={}", exposer);
            return new Response<>(ResponseEnum.SUCCESS, exposer);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new Response<>("9999",e.getMessage());
        }
    }

    /**
     * 秒杀商品请求
     * @param seckillId
     * @param md5
     * @param phone
     * @return
     */
    @RequestMapping(value = "/{seckillId}/{md5}/execution")
    public Response<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                              @PathVariable("md5") String md5,
                                              @CookieValue(value = "userPhone",required = false) Long phone){
        if (phone==null){
            return new Response<>(ResponseEnum.NOT_LOGIN);
        }
        if (seckillId==null||md5==null){
            return new Response<>(ResponseEnum.LACK_PARAMS);
        }
        try {
            SeckillExecution seckillExecution = seckillService.excuteSeckill(seckillId,phone,md5);
            return new Response<>(ResponseEnum.SUCCESS,seckillExecution);
        }catch (RepeatKillException e){
            return new Response<>(ResponseEnum.PEREAT_KILL);
        }catch (SeckillCloseException e){
            return new Response<>(ResponseEnum.KILL_CLOSE);
        }catch (DataRewriteException e){
            return new Response<>(ResponseEnum.DATA_REWRTITE);
        }catch (SeckillException e){
            return new Response<>("9999",e.getMessage());
        }
    }

    /**
     * 获取系统时间请求
     * @return
     */
    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    public Response<Long> getTime(){
        return new Response<>(ResponseEnum.SUCCESS,new Date().getTime());
    }
}

