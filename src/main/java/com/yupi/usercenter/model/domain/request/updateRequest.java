package com.yupi.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class updateRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String rate;
    private String year;
    private int type;
}
