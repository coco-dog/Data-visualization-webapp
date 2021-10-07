package com.wu.xxljobdemo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class LineVO implements Serializable {
    private List<Date> dateList;
    private List<Double> cfrlist;

    public List<Date> getDateList() {
        return dateList;
    }

    public void setDateList(List<Date> dateList) {
        this.dateList = dateList;
    }

    public List<Double> getCfrlist() {
        return cfrlist;
    }

    public void setCfrlist(List<Double> cfrlist) {
        this.cfrlist = cfrlist;
    }

}
