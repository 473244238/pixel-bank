package com.yupi.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.usercenter.model.domain.Interestrate;


/**
* @author 473244238
* @description 针对表【interestrate(利率)】的数据库操作Service
* @createDate 2023-11-18 15:16:28
*/
public interface InterestrateService extends IService<Interestrate> {
public long add(String storeTime,String rate,int type);

public long NewUpdate(String rate,String year,int type);
}
