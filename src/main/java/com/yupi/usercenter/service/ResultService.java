package com.yupi.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.usercenter.model.domain.Result;

import java.util.List;


/**
* @author 473244238
* @description 针对表【result(计算结果)】的数据库操作Service
* @createDate 2023-11-18 09:50:32
*/
public interface ResultService extends IService<Result> {
    /**
     * 查询历史记录
     * @param userId
     * @return
     */
    List<Result> searchList(long userId);

}
