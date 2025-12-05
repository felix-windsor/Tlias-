package com.shengxi.service;

import com.shengxi.pojo.Emp;
import com.shengxi.pojo.EmpQueryParam;
import com.shengxi.pojo.PageResult;

import java.util.List;

public interface EmpService {

    /**
     * 分页查询
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增员工
     */
    void save(Emp emp) throws Exception;

    /**
     * 删除员工
     */
    void delete(List<Integer> ids);

    Emp getInfo(Integer id);

    /**
     * 更新员工信息
     * @param emp
     */
    void update(Emp emp);

    /**
     * 分页查询
     * @param page 页码
     * @param pageSize 每页记录数
     */
    //PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);

    /**
     * 根据职位查询员工（用于下拉）
     */
    java.util.List<Emp> listByJob(Integer job);
}
