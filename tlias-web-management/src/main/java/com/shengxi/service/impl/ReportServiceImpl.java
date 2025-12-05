package com.shengxi.service.impl;

import com.shengxi.mapper.EmpMapper;
import com.shengxi.mapper.StudentMapper;
import com.shengxi.pojo.JobOption;
import com.shengxi.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<String,Object>> list = empMapper.countEmpJobData();
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map> getEmpGenderData() {
        List<Map> list = empMapper.countEmpGenderData();
        return list.stream().map(m -> {
            String n = String.valueOf(m.get("name"));
            m.put("name", "男".equals(n) ? "男性员工" : "女性员工");
            return m;
            // 把性别从数据库中查询出来的男/女 替换为 男性员工/女性员工 
        }).toList();
    }

    @Override
    public List<Map> getStudentDegreeData() {
        return studentMapper.countStudentDegreeData();
    }

    @Override
    public Map getStudentCountData() {
        List<Map> list = studentMapper.countStudentCountData();
        List<Object> clazzList = list.stream().map(m -> m.get("name")).toList();
        List<Object> dataList = list.stream().map(m -> m.get("value")).toList();
        java.util.Map<String, Object> res = new java.util.LinkedHashMap<>();
        res.put("clazzList", clazzList);
        res.put("dataList", dataList);
        return res;
    }
}
