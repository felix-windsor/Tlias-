package com.shengxi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shengxi.mapper.EmpMapper;
import com.shengxi.pojo.Emp;
import com.shengxi.pojo.EmpQueryParam;
import com.shengxi.pojo.PageResult;
import com.shengxi.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    /**
     * 原始分页查询
     * @param page 页码
     * @param pageSize 每页记录数
     * @return
     */
    /*@Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        //1. 调用mapper接口, 查询总记录数
        Long total = empMapper.count();

        //2. 调用mapper接口, 查询结果列表
        Integer start = (page - 1) * pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);

        //3. 封装结果 PageResult
        return new PageResult<Emp>(total, rows);
    }*/

    /**
     * PageHelper分页查询
     * @param empQueryParam 页码
     */
    /*@Override
    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
        //1. 设置分页参数(PageHelper)
        PageHelper.startPage(page, pageSize);

        //2. 执行查询
        List<Emp> empList = empMapper.list(name, gender, begin, end);

        //3. 解析查询结果, 并封装
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }*/

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1. 设置分页参数(PageHelper)
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        //2. 执行查询
        List<Emp> empList = empMapper.list(empQueryParam);

        //3. 解析查询结果, 并封装
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }
}