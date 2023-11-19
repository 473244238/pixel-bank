package com.yupi.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 利率增加表
 */
@Data
public class RateAddRequest implements Serializable {
    private static final long serialVersionUID = 3191241716373120793L;

    private String storeTime;

    private String rate;

    private int type;
}
