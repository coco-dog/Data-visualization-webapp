package com.wu.xxljobdemo.service.impl;

import com.wu.xxljobdemo.entity.Local;
import com.wu.xxljobdemo.mapper.LocalMapper;
import com.wu.xxljobdemo.service.LocalService;
import com.wu.xxljobdemo.util.CountyVOutil;
import com.wu.xxljobdemo.util.StateVOutil;
import com.wu.xxljobdemo.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Line;
import javax.xml.ws.ServiceMode;
import java.util.*;

@Service
public class LocalServiceImpl implements LocalService {
    @Autowired
    private LocalMapper localMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Local total() {
        return (Local) redisTemplate.opsForValue().get("local");
    }

    @Override
    public List<Local> excel() {
        List<String> findstatename = localMapper.findstatename();
        List<Local> morelocallist=new ArrayList<>();
        Set<Local> localList=new LinkedHashSet<>();
        for (String name : findstatename) {
            Local excel = localMapper.excel(name);
            localList.add(excel);
        }
        int size = localList.size();
        for (int i = 0; i < size-1; i++) {
            Local local1=new Local();
            int temp=0;
            for (Local local : localList) {
                if (local.getCases()>temp){
                    temp=local.getCases();
                    local1=local;
                }
            }
            morelocallist.add(local1);
            localList.remove(local1);
        }
        List<Local> locals=new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            locals.add(morelocallist.get(i));
        }
        return locals;


//        Map<String,List<Integer>> map=new LinkedHashMap<>();
//        for (String name : findstatename) {
//            List<Integer> list=new ArrayList<>();
//            Line line = localMapper.line(name);
//            list.add(line.getTotalcases());
//            list.add(line.getTotaldeaths());
//            map.put(name,list);
//        }
//
//        Map<String,List<Integer>> map1=new LinkedHashMap<>();
//
//        int size = findstatename.size();
//
//        for (int i = 0; i < size-1; i++) {
//            List<Integer> list=new ArrayList<>();
//            String a=null;
//            int temp=0;
//            for (String key : map.keySet()) {
//                if (map.get(key).get(0)>temp){
//                    temp=map.get(key).get(0);
//                    a=key;
//                }
//            }
//            list.add(map.get(a).get(0));
//            list.add(map.get(a).get(1));
//            map1.put(a,list);
//            map.remove(a);
//        }
//
//        int i=0;
//        for (String key: map1.keySet()) {
//            if (i>=7){
//                break;
//            }
//            state.add(key);
//            cases.add(map1.get(key).get(0));
//            deaths.add(map1.get(key).get(1));
//            i++;
//        }
//        StateVO stateVO=new StateVO();
//        stateVO.setState(state);
//        stateVO.setCases(cases);
//        stateVO.setDeaths(deaths);
//
//        return stateVO;
    }

    @Override
    public CountyVO search(String state) {
        List<Local> search = localMapper.search(state);
        List<String> county=new ArrayList<>();
        List<Integer> cases=new ArrayList<>();
        List<Integer> deaths=new ArrayList<>();
        List<DateVO> dateVOS=new ArrayList<>();
        for (Local list : search) {
            county.add(list.getCounty());
            cases.add(list.getCases());
            deaths.add(list.getDeaths());
        }

        for (Integer a : cases) {
            DateVO dateVO=new DateVO();
            dateVO.setCases(a);
            dateVO.setColor(CountyVOutil.casegetcolor(a));
            dateVOS.add(dateVO);
        }
        CountyVO countyVO=new CountyVO();
        countyVO.setCounty(county);
        countyVO.setDeaths(deaths);
        countyVO.setDateVOS(dateVOS);
        return countyVO;
    }

    @Override
    public LineVO cfr() {
        return (LineVO) redisTemplate.opsForValue().get("lineVO");
    }


    @Override
    public LineVO searchstateline(String state) {
        LineVO list = (LineVO) redisTemplate.opsForValue().get(state);

        return (LineVO)redisTemplate.opsForValue().get(state);
    }


    @Override
    public List<Local> detaildata(String state) {
        List<Local> morelocallist=new ArrayList<>();
        Set<Local> detaildata = localMapper.detaildata(state);
        int size = detaildata.size();
        for (int i = 0; i < size-1; i++) {
            Local local1=new Local();
            int temp=0;
            for (Local local : detaildata) {
                if (local.getCases()>temp){
                    temp=local.getCases();
//                    local1.setCases(local.getCases());
//                    local1.setState(local.getState());
//                    local1.setDeaths(local.getDeaths());
//                    local1.setCfs(local.getCfs());
                    local1=local;
                }
            }
            morelocallist.add(local1);
            detaildata.remove(local1);
        }
        return morelocallist;
    }

    @Override
    public StateandCases geo() {
        return  (StateandCases) redisTemplate.opsForValue().get("stateandcases");
    }

}
