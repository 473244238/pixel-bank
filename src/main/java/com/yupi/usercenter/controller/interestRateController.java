package com.yupi.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yupi.usercenter.common.BaseResponse;
import com.yupi.usercenter.common.ErrorCode;
import com.yupi.usercenter.common.ResultUtils;
import com.yupi.usercenter.exception.BusinessException;
import com.yupi.usercenter.model.domain.Interestrate;
import com.yupi.usercenter.model.domain.User;
import com.yupi.usercenter.model.domain.request.RateAddRequest;
import com.yupi.usercenter.model.domain.request.addRequest;
import com.yupi.usercenter.model.domain.request.loanRequest;
import com.yupi.usercenter.model.domain.request.updateRequest;
import com.yupi.usercenter.service.InterestrateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
@Slf4j
@RestController
@RequestMapping("/rate")
public class interestRateController {

    @Resource
    private InterestrateService interestrateService;



    @PostMapping("/add")
    public BaseResponse<Long> addRate(@RequestBody addRequest addRequest, HttpServletRequest request){
        log.info(addRequest.toString());
        if(StringUtils.isAnyBlank(addRequest.getRate(),addRequest.getStoreTime())){
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        long UserId=getId("token",request);
        String storeTime=addRequest.getStoreTime();
        String rate=addRequest.getRate();
        int type=addRequest.getType();
        long result=interestrateService.add(storeTime,rate,type);
        return ResultUtils.success(result);

    }

    /**
     * 存款,贷款
     * @param loanRequest
     * @param request
     * @return
     */
    @PostMapping("/count")
    public BaseResponse<Integer> Deposit(@RequestBody loanRequest loanRequest,HttpServletRequest request){
        log.info(loanRequest.toString());
        if(loanRequest==null){
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        long UserId=getId("token",request);
        String year=loanRequest.getYear();
        switch (year){
            case "三个月":
                year="0.25";
                break;
            case "六个月":
                year="0.5";
                break;
            case "半年":
                year="0.5";
                break;
            case "一年":
                year="1";
                break;
            case "二年":
                year="2";
                break;
            case "三年":
                year="3";
                break;
            case "五年":
                year="5";
                break;
            case "一至三年":
                year="2";
                break;
            case "三至五年":
                year="4";
        }

        log.info("年份是:{}",year);
        if(loanRequest.getType()!=0){
        LambdaQueryWrapper<Interestrate> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Interestrate::getType,loanRequest.getType());
        queryWrapper.eq(Interestrate::getStoretime,year);
        Interestrate interestrate=interestrateService.getOne(queryWrapper);
        String rate=interestrate.getRate();
        double NewYear=interestrate.getNumber();
        double NewRate=Double.parseDouble(rate);
        double result= NewYear*NewRate* loanRequest.getMoney()/100;
        int result2=(int)result;
        return ResultUtils.success(result2);
        }else{
            LambdaQueryWrapper<Interestrate> queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper.eq(Interestrate::getType,loanRequest.getType());
            Interestrate interestrate=interestrateService.getOne(queryWrapper);
            String rate=interestrate.getRate();
            double NewYear=Double.parseDouble(loanRequest.getYear());
            double NewRate=Double.parseDouble(rate);
            double result= NewYear*NewRate* loanRequest.getMoney()/100;
            int result2=(int)result;
            return ResultUtils.success(result2);
        }



    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public BaseResponse<Long> updateRate(@RequestBody updateRequest updateRequest,HttpServletRequest request){
        if(StringUtils.isAnyBlank(updateRequest.getRate())){
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        String rate=updateRequest.getRate();
        String year=updateRequest.getYear();
        log.info("year:{}",year);
        int type=updateRequest.getType();
        switch (year){
            case "三个月":
                year="0.25";
                break;
            case "六个月":
                year="0.5";
                break;
            case "半年":
                year="0.5";
                break;
            case "一年":
                year="1";
                break;
            case "二年":
                year="2";
                break;
            case "三年":
                year="3";
                break;
            case "五年":
                year="5";
                break;
            case "一至三年":
                year="2";
                break;
            case "三至五年":
                year="4";
        }
        log.info("year:{}",year);
        log.info("rate:{}",rate);
        log.info("type:{}",type);
        long id=interestrateService.NewUpdate(rate,year,type);
        return ResultUtils.success(id);

    }



    public long getId(String msg,HttpServletRequest request){
        String token=request.getHeader(msg);
        log.info("你的token:{}",token);
        if(token==null){
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        Object userObject=request.getSession().getAttribute(token);
        User user=(User) userObject;
        long userId=user.getId();
        return  userId;
    }
}
