package com.wu.xxljobdemo.util;

import java.util.HashMap;
import java.util.Map;

public class CountyVOutil {
    public static Map<String,String> casegetcolor(Integer cases){
        String color=null;
        if (cases<10){
             color="#000000";
        }else if (cases>=10&&cases<50){
             color="#111111";
        }else if (cases>=50&&cases<100){
             color="#222222";
        }else if (cases>=100){
             color="#333333";
        }
        Map<String,String> map=new HashMap<>();
        map.put("color",color);
        return map;
    }
}
