package com.yupi.usercenter.utils;

import java.util.UUID;

/**
 * token工具类名称
 */
public class MakeToken {
    public  static String uuid(){
        //使用UUID来生成唯一标识
        return UUID.randomUUID().toString();
    }
}
