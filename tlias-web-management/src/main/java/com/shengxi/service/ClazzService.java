package com.shengxi.service;

import com.shengxi.pojo.Clazz;
import com.shengxi.pojo.ClazzQueryParam;
import com.shengxi.pojo.PageResult;

public interface ClazzService {
    PageResult<Clazz> query(ClazzQueryParam param);
    void deleteById(Integer id);
    void add(Clazz clazz);
    Clazz getById(Integer id);
    void update(Clazz clazz);
    java.util.List<Clazz> listAll();
}
