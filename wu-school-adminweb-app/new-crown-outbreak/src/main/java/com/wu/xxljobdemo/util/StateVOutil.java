package com.wu.xxljobdemo.util;

import java.util.HashMap;
import java.util.Map;

public class StateVOutil {
    public static Map<String,String> casegetcolor(Integer cases){
        String color=null;
        if (cases<500){
            color="#000000";
        }else if (cases>=500&&cases<1000){
            color="#111111";
        }else if (cases>=1000&&cases<10000){
            color="#222222";
        }else if (cases>=10000){
            color="#333333";
        }
        Map<String,String> map=new HashMap<>();
        map.put("color",color);
        return map;
    }
}
