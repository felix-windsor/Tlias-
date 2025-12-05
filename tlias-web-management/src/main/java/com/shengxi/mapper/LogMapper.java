package com.shengxi.mapper;

import com.shengxi.pojo.OperateLog;
import com.shengxi.pojo.LogQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogMapper {
    List<OperateLog> list(LogQueryParam param);
}

