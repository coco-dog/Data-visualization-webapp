package com.wu.xxljobdemo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class StateandCases implements Serializable {
    private List<String> state;
    private List<Integer> cases;
}
