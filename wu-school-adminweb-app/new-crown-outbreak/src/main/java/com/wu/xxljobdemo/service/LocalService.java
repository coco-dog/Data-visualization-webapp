package com.wu.xxljobdemo.service;

import com.wu.xxljobdemo.entity.Local;
import com.wu.xxljobdemo.vo.CountyVO;
import com.wu.xxljobdemo.vo.LineVO;
import com.wu.xxljobdemo.vo.StateVO;
import com.wu.xxljobdemo.vo.StateandCases;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LocalService {
    public List<Local> excel();
    public Local total();
    public CountyVO search(String state);
    public LineVO cfr();
    public LineVO searchstateline(String state);
    public List<Local> detaildata(String state);
    public StateandCases geo();
}
