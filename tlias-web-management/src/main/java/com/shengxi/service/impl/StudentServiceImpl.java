package com.shengxi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shengxi.mapper.StudentMapper;
import com.shengxi.pojo.PageResult;
import com.shengxi.pojo.Student;
import com.shengxi.pojo.StudentQueryParam;
import com.shengxi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> query(StudentQueryParam param) {
        if (param.getPage() == null || param.getPage() < 1) param.setPage(1);
        if (param.getPageSize() == null || param.getPageSize() < 1) param.setPageSize(10);
        PageHelper.startPage(param.getPage(), param.getPageSize());
        Page<Student> page = (Page<Student>) studentMapper.list(param);
        return new PageResult<>(page.getTotal(), page.getResult());
    }

    @Override
    public void delete(java.util.List<Integer> ids) {
        studentMapper.deleteByIds(ids);
    }

    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void violation(Integer id, Integer score) {
        studentMapper.updateViolation(id, score);
    }
}
