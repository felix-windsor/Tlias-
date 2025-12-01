package com.shengxi.service;

import com.shengxi.pojo.Emp;
import com.shengxi.pojo.EmpQueryParam;
import com.shengxi.pojo.PageResult;

public interface EmpService {

    /**
     * 分页查询
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 分页查询
     * @param page 页码
     * @param pageSize 每页记录数
     */
    //PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);
}