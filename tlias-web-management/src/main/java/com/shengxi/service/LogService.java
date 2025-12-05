package com.shengxi.service;

import com.shengxi.pojo.LogQueryParam;
import com.shengxi.pojo.OperateLog;
import com.shengxi.pojo.PageResult;

public interface LogService {
    PageResult<OperateLog> page(LogQueryParam param);
}

