package com.wu.xxljobdemo.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class Local implements Serializable {
    private Date date;

    private String county;

    private String state;

    private Integer cases;

    private Integer deaths;

    private double cfs;
}
