package com.shengxi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shengxi.mapper.LogMapper;
import com.shengxi.pojo.LogQueryParam;
import com.shengxi.pojo.OperateLog;
import com.shengxi.pojo.PageResult;
import com.shengxi.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public PageResult<OperateLog> page(LogQueryParam param) {
        if (param.getPage() == null || param.getPage() < 1) param.setPage(1);
        if (param.getPageSize() == null || param.getPageSize() < 1) param.setPageSize(10);
        PageHelper.startPage(param.getPage(), param.getPageSize());
        List<OperateLog> list = logMapper.list(param);
        Page<OperateLog> p = (Page<OperateLog>) list;
        return new PageResult<>(p.getTotal(), p.getResult());
    }
}

