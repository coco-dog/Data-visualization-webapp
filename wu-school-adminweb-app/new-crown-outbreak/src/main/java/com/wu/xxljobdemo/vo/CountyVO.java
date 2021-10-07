package com.wu.xxljobdemo.vo;

import java.util.List;

public class CountyVO {
    private List<String> county;
    private List<DateVO> dateVOS;
    private List<Integer> deaths;

    public List<String> getCounty() {
        return county;
    }

    public void setCounty(List<String> county) {
        this.county = county;
    }

    public List<DateVO> getDateVOS() {
        return dateVOS;
    }

    public void setDateVOS(List<DateVO> dateVOS) {
        this.dateVOS = dateVOS;
    }

    public List<Integer> getDeaths() {
        return deaths;
    }

    public void setDeaths(List<Integer> deaths) {
        this.deaths = deaths;
    }
}
