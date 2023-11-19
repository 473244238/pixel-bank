package com.yupi.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yupi.usercenter.common.ErrorCode;
import com.yupi.usercenter.exception.BusinessException;
import com.yupi.usercenter.mapper.ResultMapper;
import com.yupi.usercenter.model.domain.Result;
import com.yupi.usercenter.service.ResultService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 473244238
* @description 针对表【result(计算结果)】的数据库操作Service实现
* @createDate 2023-11-18 09:50:32
*/
@Service
public class ResultServiceImpl extends ServiceImpl<ResultMapper, Result>
    implements ResultService {

    @Override
    public List<Result> searchList(long userId) {
    if(userId<=0){
        throw new BusinessException(ErrorCode.SYSTEM_ERROR);
    }
        LambdaQueryWrapper<Result> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Result::getUserid,userId);
        queryWrapper.orderByAsc(Result::getCreatetime);
        List<Result> list=this.list(queryWrapper);

        return list;
    }
}




