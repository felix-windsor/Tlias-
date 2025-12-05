package com.shengxi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shengxi.mapper.ClazzMapper;
import com.shengxi.pojo.Clazz;
import com.shengxi.pojo.ClazzQueryParam;
import com.shengxi.pojo.PageResult;
import com.shengxi.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult<Clazz> query(ClazzQueryParam param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());
        List<Clazz> list = clazzMapper.list(param);
        LocalDate today = LocalDate.now();
        for (Clazz c : list) {
            if (c.getBeginDate() != null && c.getEndDate() != null) {
                if (today.isBefore(c.getBeginDate())) {
                    c.setStatus("未开班");
                } else if (today.isAfter(c.getEndDate())) {
                    c.setStatus("已结课");
                } else {
                    c.setStatus("已开班");
                }
            }
        }
        Page<Clazz> p = (Page<Clazz>) list;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }

    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz getById(Integer id) {
        Clazz clazz = clazzMapper.getById(id);
        if (clazz != null && clazz.getBeginDate() != null && clazz.getEndDate() != null) {
            LocalDate today = LocalDate.now();
            if (today.isBefore(clazz.getBeginDate())) {
                clazz.setStatus("未开班");
            } else if (today.isAfter(clazz.getEndDate())) {
                clazz.setStatus("已结课");
            } else {
                clazz.setStatus("已开班");
            }
        }
        return clazz;
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    @Override
    public java.util.List<Clazz> listAll() {
        return clazzMapper.listAll();
    }
}
