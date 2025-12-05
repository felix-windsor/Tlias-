package com.shengxi.controller;

import com.shengxi.pojo.LogQueryParam;
import com.shengxi.pojo.OperateLog;
import com.shengxi.pojo.PageResult;
import com.shengxi.pojo.Result;
import com.shengxi.service.LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "Log", description = "日志管理")
@RequestMapping("/log")
@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @Operation(summary = "日志列表查询")
    @GetMapping("/page")
    public Result page(LogQueryParam param){
        PageResult<OperateLog> page = logService.page(param);
        return Result.success(page);
    }
}

