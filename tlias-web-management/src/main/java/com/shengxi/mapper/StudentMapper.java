package com.shengxi.mapper;

import com.shengxi.pojo.Student;
import com.shengxi.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.Map;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> list(StudentQueryParam param);
    void deleteByIds(java.util.List<Integer> ids);
    void insert(Student student);
    void update(Student student);
    void updateViolation(@Param("id") Integer id, @Param("score") Integer score);
    java.util.List<Map> countStudentDegreeData();
    java.util.List<Map> countStudentCountData();
}
