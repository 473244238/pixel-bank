package com.yupi.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class addRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String storeTime;

    private String rate;

    private int type;
}
