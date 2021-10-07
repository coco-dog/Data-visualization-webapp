package com.wu.xxljobdemo.controller;

import com.wu.xxljobdemo.entity.Local;
import com.wu.xxljobdemo.mapper.LocalMapper;
import com.wu.xxljobdemo.service.LocalService;
import com.wu.xxljobdemo.vo.CountyVO;
import com.wu.xxljobdemo.vo.LineVO;
import com.wu.xxljobdemo.vo.StateVO;
import com.wu.xxljobdemo.vo.StateandCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("//local")
public class LocalController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private LocalService localService;

    /*累积感染死亡和病死率*/
    @GetMapping("/total")
    public Local total(){return localService.total();}

    /*全国地图数据*/
    @GetMapping("/statemap")
    public StateandCases geo(){
        return localService.geo();
    }

    /*病死率折线图*/
    @GetMapping("/line")
    public LineVO line(){
        return localService.cfr();
    }
    /*右侧搜索框数据*/
    @GetMapping("/countymap")
    public CountyVO state(String state){ return localService.search(state); }

    /*区数据,感染死亡感染率*/
    @GetMapping("/countydate")
    public List<Local> county(){return localService.excel();}

    /*搜索框中州的数据*/
    @GetMapping("/searchstateline")
    public LineVO searchstateline(String state){
        return localService.searchstateline(state);
    }

    /*某个州中各区的数据*/
    @GetMapping("/detaildata")
    public List<Local> detaildata(String state){
        return localService.detaildata(state);
    }

    /*所有州名*/
    @GetMapping("/allstate")
    public Set<String> allstate(){
        return (Set<String>)redisTemplate.opsForSet().members("allstate");
    }
}
