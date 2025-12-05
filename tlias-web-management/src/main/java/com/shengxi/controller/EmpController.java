package com.shengxi.controller;

import com.shengxi.pojo.Emp;
import com.shengxi.pojo.EmpQueryParam;
import com.shengxi.pojo.PageResult;
import com.shengxi.pojo.Result;
import com.shengxi.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * 员工管理Controller
 */
@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 分页查询
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询: {}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /** 别名：支持 /emps/list 作为分页查询路径 */
    @GetMapping("/list")
    public Result pageAlias(EmpQueryParam empQueryParam){
        return page(empQueryParam);
    }

    /**
     * 新增员工
     */
    @PostMapping
    public Result save(@RequestBody Emp emp) throws Exception {
        log.info("新增员工: {}", emp);
        empService.save(emp);
        return Result.success();
    }


    /**
     * 删除员工
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工: {}", ids);
        empService.delete(ids);
        return Result.success();
    }


    /**
     * 根据ID查询员工信息
     */
    @GetMapping("/{id:\\d+}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据ID查询员工信息: {}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    /**
     * 更新员工信息
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息, {}", emp);
        empService.update(emp);
        return Result.success();
    }

    /**
     * 员工下拉选项（默认返回班主任）
     */
    @GetMapping(value = "", params = "type=options")
    public Result options(@RequestParam(required = false) Integer job){
        Integer j = (job == null ? 1 : job); // 1: 班主任
        java.util.List<com.shengxi.pojo.Emp> list = empService.listByJob(j);
        java.util.List<java.util.Map<String,Object>> opts = list.stream()
                .map(e -> {
                    java.util.Map<String,Object> m = new java.util.HashMap<>();
                    // 兼容两种字段命名，前端可任选其一
                    // m.put("label", e.getName());
                    // m.put("value", e.getId());
                    m.put("id", e.getId());
                    m.put("name", e.getName());
                    return m;
                }).toList();
        return Result.success(opts);
    }

    /** 同路径别名：/emps/options */
    @GetMapping("/options")
    public Result optionsAlias(@RequestParam(required = false) Integer job){
        return options(job);
    }
}
