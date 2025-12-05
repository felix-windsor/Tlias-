package com.shengxi.service;

import com.shengxi.pojo.PageResult;
import com.shengxi.pojo.Student;
import com.shengxi.pojo.StudentQueryParam;

public interface StudentService {
    PageResult<Student> query(StudentQueryParam param);
    void delete(java.util.List<Integer> ids);
    void add(Student student);
    void update(Student student);
    void violation(Integer id, Integer score);
}
