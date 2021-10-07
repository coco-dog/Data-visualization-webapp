package com.wu.xxljobdemo.vo;

import java.util.List;

public class StateVO {
    private List<DateVO> dateVOS;
    private List<Integer> deaths;
    private List<String> state;

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

    public List<String> getState() {
        return state;
    }

    public void setState(List<String> state) {
        this.state = state;
    }
}
