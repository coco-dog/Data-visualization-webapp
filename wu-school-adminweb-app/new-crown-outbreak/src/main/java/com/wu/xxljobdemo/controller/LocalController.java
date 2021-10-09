package com.wu.xxljobdemo.controller;

import com.wu.xxljobdemo.entity.Local;
import com.wu.xxljobdemo.mapper.LocalMapper;
import com.wu.xxljobdemo.service.LocalService;
import com.wu.xxljobdemo.vo.CountyVO;
import com.wu.xxljobdemo.vo.LineVO;
import com.wu.xxljobdemo.vo.StateVO;
import com.wu.xxljobdemo.vo.StateandCases;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Api(tags = "疫情接口文档")
@RestController
@RequestMapping("//local")
public class LocalController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private LocalService localService;


    @ApiOperation(value = "累积感染死亡和病死率")
    @GetMapping("/total")
    public Local total(){return localService.total();}


    @ApiOperation(value = "全国地图数据")
    @GetMapping("/statemap")
    public StateandCases geo(){
        return localService.geo();
    }


    @ApiOperation(value = "病死率折线图")
    @GetMapping("/line")
    public LineVO line(){
        return localService.cfr();
    }


    @ApiOperation(value = "搜索框报表区数据")
    @GetMapping("/countymap")
    public CountyVO state(String state){ return localService.search(state); }


    @ApiOperation(value = "区数据,感染死亡率")
    @GetMapping("/countydate")
    public List<Local> county(){return localService.excel();}

    /*搜索框中州的数据*/
    @ApiOperation(value = "报表中州的数据")
    @GetMapping("/searchstateline")
    public LineVO searchstateline(String state){
        return localService.searchstateline(state);
    }


    @ApiOperation(value = "某个州中各区的数据")
    @GetMapping("/detaildata")
    public List<Local> detaildata(String state){
        return localService.detaildata(state);
    }


    @ApiOperation(value = "所有州名")
    @GetMapping("/allstate")
    public Set<String> allstate(){
        return (Set<String>)redisTemplate.opsForSet().members("allstate");
    }
}
