package com.shengxi.mapper;

import com.shengxi.pojo.Clazz;
import com.shengxi.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {
    List<Clazz> list(ClazzQueryParam param);
    void deleteById(Integer id);
    void insert(Clazz clazz);
    Clazz getById(Integer id);
    void update(Clazz clazz);
    List<Clazz> listAll();
}
