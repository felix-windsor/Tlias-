package com.shengxi.service;

import com.shengxi.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 统计各个职位的员工人数
     * @return
     */
    JobOption getEmpJobData();

    /**
     * 统计员工性别信息
     */
    List<Map> getEmpGenderData();

    /**
     * 统计学员学历信息
     */
    List<Map> getStudentDegreeData();

    /**
     * 统计每个班级人数
     */
    Map getStudentCountData();


}
