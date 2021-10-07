package com.wu.xxljobdemo.xlljobcenter;

import com.wu.xxljobdemo.entity.Local;
import com.wu.xxljobdemo.mapper.LocalMapper;
import com.wu.xxljobdemo.vo.*;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * XxlJob开发示例（Bean模式）
 *
 * 开发步骤：
 *      1、任务开发：在Spring Bean实例中，开发Job方法；
 *      2、注解配置：为Job方法添加注解 "@XxlJob(value="自定义jobhandler名称", init = "JobHandler初始化方法", destroy = "JobHandler销毁方法")"，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 *      3、执行日志：需要通过 "XxlJobHelper.log" 打印执行日志；
 *      4、任务结果：默认任务结果为 "成功" 状态，不需要主动设置；如有诉求，比如设置任务结果为失败，可以通过 "XxlJobHelper.handleFail/handleSuccess" 自主设置任务结果；
 *
 * @author xuxueli 2019-12-11 21:52:51
 */
@Component
public class XxlJobRedis {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private LocalMapper localMapper;

    private static Logger logger = LoggerFactory.getLogger(XxlJobRedis.class);


    /*
     * 1、简单任务示例（Bean模式）
     */
    //定时查询数据存入到redis中

    @XxlJob("startJobHandler")
    public void startJobHandler() throws Exception{
        List<String> findstatename = localMapper.findstatename();
        SetOperations<String,String> setOperations = redisTemplate.opsForSet();
        for (String state : findstatename) {
            setOperations.add("allstate",state);
        }
    }


    @XxlJob("demoJobHandler")
    public void demoJobHandleraddstateandcases() throws Exception {
        List<Integer> cases=new ArrayList<>();
        List<String> state=new ArrayList<>();
        List<String> findstatename = localMapper.findstatename();
        for (String name : findstatename) {
            Local geo = localMapper.geo(name);
            DateVO dateVO=new DateVO();
            state.add(geo.getState());
            cases.add(geo.getCases());
        }
        StateandCases stateandCases=new StateandCases();
        stateandCases.setState(state);
        stateandCases.setCases(cases);
        redisTemplate.opsForValue().set("stateandcases",stateandCases);
    }

    @XxlJob("demoJobHandler1")
    public void demoJobHandlerLocal() throws Exception{
        List<String> findstatename = localMapper.findstatename();
        int i=0;
        int cases=0;
        int deaths=0;
        for (String name : findstatename) {
            Local newdate = localMapper.newdate(name);
            cases+= newdate.getCases();
            deaths+= newdate.getDeaths();
        }
        double cfr=(deaths*0.1)/(cases*0.1);
        Local local=new Local();
        local.setCases(cases);
        local.setDeaths(deaths);
        local.setCfs(cfr);
        redisTemplate.opsForValue().set("local",local);
    }

    @XxlJob("demoJobHandler2")
    public void demoJobHandlerLineVO() throws Exception{
        List<Local> list=new ArrayList<>();
        List<Date> dateList=new ArrayList<>();
        List<Double> cfr=new ArrayList<>();
        Calendar c=Calendar.getInstance();
        Date beginDate =  localMapper.mindate();
        Date endDate=  localMapper.maxdate();
        Date date=  beginDate;
        while(!date.equals(endDate)){
            Local line = localMapper.line(date);
            list.add(line);
            c.setTime(date);
            c.add(Calendar.DATE,1);
            date= c.getTime();
        }
        for (Local local : list) {
            dateList.add(local.getDate());
            cfr.add(local.getCfs());
        }
        LineVO lineVO=new LineVO();
        lineVO.setCfrlist(cfr);
        lineVO.setDateList(dateList);
        redisTemplate.opsForValue().set("lineVO",lineVO);
    }

    @XxlJob("demoJobHandler3")
    public void demoJobHandler3(){
        List<String> findstatename = localMapper.findstatename();
        for (String state : findstatename) {
            List<Local> list=new ArrayList<>();
            List<Date> dateList=new ArrayList<>();
            List<Double> cfr=new ArrayList<>();
            Calendar c=Calendar.getInstance();
            Date beginDate =  localMapper.statemindate(state);
            Date endDate=  localMapper.statemaxdate(state);
            Date date=  beginDate;
            while(!date.equals(endDate)){
                Local line = localMapper.searchstateline(date,state);
                list.add(line);
                c.setTime(date);
                c.add(Calendar.DATE,1);
                date= c.getTime();
            }
            for (Local local : list) {
                dateList.add(local.getDate());
                cfr.add(local.getCfs());
            }
            LineVO lineVO=new LineVO();
            lineVO.setCfrlist(cfr);
            lineVO.setDateList(dateList);
            redisTemplate.opsForValue().set(state,lineVO);
        }
    }

}
