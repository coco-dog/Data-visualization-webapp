package com.wu.xxljobdemo.mapper;

import com.wu.xxljobdemo.entity.Local;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Mapper
public interface LocalMapper {
    /*查询库中所有地名数据*/
    public List<String> findstatename();
    /*查询某个地方的感染或死亡的人数和*/
    public Local newdate(String state);
    /*查询地图对应数据*/
    public Local geo(String state);
    /*查询排名表*/
    public Local excel(String state);
    /*查询某个州中各区的感染或死亡人数*/
    public List<Local> search(String state);
    /*查询各州的病死率折线图*/
    public Local line(Date date);
    /*查询州的病死率折线图*/
    public Local searchstateline(@Param("date") Date date, @Param("state") String state);
    /*时间查询*/
    public java.sql.Date maxdate();
    public java.sql.Date mindate();
    /*查询目标地区最大最小时间*/
    public java.sql.Date statemaxdate(String state);
    public java.sql.Date statemindate(String state);
    /*查询一个州的详细数据*/
    public Set<Local> detaildata(String state);
}
