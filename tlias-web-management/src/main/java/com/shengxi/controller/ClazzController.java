package com.shengxi.controller;

import com.shengxi.pojo.Clazz;
import com.shengxi.pojo.ClazzQueryParam;
import com.shengxi.pojo.PageResult;
import com.shengxi.pojo.Result;
import com.shengxi.service.ClazzService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "Clazz", description = "班级列表查询")
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @Operation(summary = "班级列表条件分页查询")
    @GetMapping
    public Result page(ClazzQueryParam param){
        if (param.getPage() == null || param.getPage() < 1) param.setPage(1);
        if (param.getPageSize() == null || param.getPageSize() < 1) param.setPageSize(10);
        log.info("班级列表查询: {}", param);
        PageResult<Clazz> page = clazzService.query(param);
        return Result.success(page);
    }

    @Operation(summary = "新增班级")
    @PostMapping
    public Result add(@RequestBody Clazz clazz){
        log.info("新增班级: {}", clazz);
        clazzService.add(clazz);
        return Result.success();
    }

    @Operation(summary = "修改班级")
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级: {}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    @Operation(summary = "根据ID查询班级")
    @GetMapping("/{id:\\d+}") //这是什么意思
    // 解释上面代码的含义：id必须是数字 ，如果不是数字，会报错 400 Bad Request
    public Result getInfo(@PathVariable Integer id){
        log.info("根据ID查询班级: {}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    @Operation(summary = "删除班级")
    @DeleteMapping("/{id:\\d+}")
    public Result delete(@PathVariable Integer id){
        log.info("删除班级: {}", id);
        clazzService.deleteById(id);
        return Result.success();
    }

    @Operation(summary = "查询所有班级(默认分页)")
    @GetMapping("/list")
    public Result list(){
        ClazzQueryParam param = new ClazzQueryParam();
        PageResult<Clazz> page = clazzService.query(param);
        return Result.success(page);
    }


    @Operation(summary = "获取全部班级(下拉选项)")
    @GetMapping(value = "", params = "type=options")
    public Result options(){
        java.util.List<Clazz> list = clazzService.listAll();
        java.util.List<java.util.Map<String,Object>> opts = list.stream()
                .map(c -> {
                    java.util.Map<String,Object> m = new java.util.HashMap<>();
                    m.put("label", c.getName());
                    m.put("value", c.getId());
                    return m;
                }).toList();
        return Result.success(opts);
    }
}
