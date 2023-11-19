package com.yupi.usercenter.controller;

import com.yupi.usercenter.common.BaseResponse;
import com.yupi.usercenter.common.ErrorCode;
import com.yupi.usercenter.common.ResultUtils;
import com.yupi.usercenter.exception.BusinessException;
import com.yupi.usercenter.model.domain.Result;
import com.yupi.usercenter.model.domain.User;
import com.yupi.usercenter.model.domain.request.ResultAddRequest;
import com.yupi.usercenter.service.ResultService;
import com.yupi.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 计算器接口
 */
@RestController
@RequestMapping("/result")
public class resultController {
    @Resource
    private ResultService resultService;

    @Resource
    private UserService userService;

    /**
     * 添加计算机
     * @param ResultAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addResult(@RequestBody ResultAddRequest ResultAddRequest, HttpServletRequest request){
        long userId=getId("token",request);
        Result result=new Result();
        result.setUserid(userId);
        if(StringUtils.isAnyBlank(ResultAddRequest.getHisResult())){
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        result.setHisresult(ResultAddRequest.getHisResult());
       boolean r=resultService.save(result);
       if(r==true){
        return ResultUtils.success(result.getId());}
       else{
           return null;
       }

    }

    /**
     * 查询结果历史记录
     * @param request
     * @return
     */
    @PostMapping("/search")
    public BaseResponse<List<Result>> searchResult(HttpServletRequest request){
        long userId=getId("token",request);
        List<Result> resultList=resultService.searchList(userId);
        return ResultUtils.success(resultList);

    }

    public long getId(String msg,HttpServletRequest request){
        String token=request.getHeader(msg);
        if(token==null){
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        Object userObject=request.getSession().getAttribute(token);
        User user=(User) userObject;
        long userId=user.getId();
        return  userId;
    }
}


