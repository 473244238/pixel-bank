package com.yupi.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yupi.usercenter.common.ErrorCode;
import com.yupi.usercenter.exception.BusinessException;
import com.yupi.usercenter.mapper.InterestrateMapper;
import com.yupi.usercenter.model.domain.Interestrate;
import com.yupi.usercenter.service.InterestrateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 473244238
* @description 针对表【interestrate(利率)】的数据库操作Service实现
* @createDate 2023-11-18 15:16:28
*/
@Service
@Slf4j
public class InterestrateServiceImpl extends ServiceImpl<InterestrateMapper, Interestrate>
    implements InterestrateService {

    @Override
    public long add(String storeTime, String rate, int type) {
        if(type==0){
            if(StringUtils.isAnyBlank(rate)){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
            Interestrate interestrate=new Interestrate();
            interestrate.setRate(rate);
            interestrate.setType(type);
            this.save(interestrate);
            return 1;
        }else{
            if(StringUtils.isAnyBlank(storeTime,rate)){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
            Interestrate interestrate=new Interestrate();
            interestrate.setRate(rate);
            interestrate.setType(type);
            interestrate.setStoretime(storeTime);
            this.save(interestrate);
            return 1;
        }


    }

    @Override
    public long NewUpdate(String rate, String year, int type) {
        if(type==0){
            if(StringUtils.isAnyBlank(rate)){
                throw new BusinessException(ErrorCode.NULL_ERROR);
            }
            LambdaQueryWrapper<Interestrate> queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper.eq(Interestrate::getType,type);
            Interestrate interestrate=this.getOne(queryWrapper);
            interestrate.setRate(rate);
            this.updateById(interestrate);
            return 1;
        }else if(type==1){
            if(StringUtils.isAnyBlank(rate,year)){
                throw new BusinessException(ErrorCode.NULL_ERROR);
            }
            LambdaQueryWrapper<Interestrate> queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper.eq(Interestrate::getType,type);
            queryWrapper.eq(Interestrate::getStoretime,year);
            Interestrate interestrate=this.getOne(queryWrapper);
            interestrate.setRate(rate);
            this.updateById(interestrate);
            return 1;
        }
       else{
            if(StringUtils.isAnyBlank(rate,year)){
                throw new BusinessException(ErrorCode.NULL_ERROR);
            }
            if(year.equals("4")){


                LambdaQueryWrapper<Interestrate> queryWrapper=new LambdaQueryWrapper<>();
                queryWrapper.eq(Interestrate::getType,type);
                queryWrapper.eq(Interestrate::getStoretime,"3");
                Interestrate interestrate=this.getOne(queryWrapper);
                interestrate.setRate(rate);
                this.updateById(interestrate);
                LambdaQueryWrapper<Interestrate> queryWrapper2=new LambdaQueryWrapper<>();
                queryWrapper2.eq(Interestrate::getType,type);
                queryWrapper2.eq(Interestrate::getStoretime,"4");
                Interestrate interestrate2=this.getOne(queryWrapper2);
                interestrate2.setRate(rate);
                this.updateById(interestrate2);

                return 1;
            }
            else{
                LambdaQueryWrapper<Interestrate> queryWrapper=new LambdaQueryWrapper<>();
                queryWrapper.eq(Interestrate::getType,type);
                queryWrapper.eq(Interestrate::getStoretime,year);
                Interestrate interestrate=this.getOne(queryWrapper);
                interestrate.setRate(rate);
                this.updateById(interestrate);
                return 1;
            }
        }
    }
}




