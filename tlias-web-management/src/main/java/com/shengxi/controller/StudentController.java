package com.shengxi.controller;

import com.shengxi.pojo.PageResult;
import com.shengxi.pojo.Result;
import com.shengxi.pojo.Student;
import com.shengxi.pojo.StudentQueryParam;
import com.shengxi.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Slf4j
@Tag(name = "Student", description = "学员管理")
@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Operation(summary = "学员列表条件分页查询")
    @GetMapping
    public Result page(StudentQueryParam param){
        log.info("学员列表查询: {}", param);
        PageResult<Student> page = studentService.query(param);
        return Result.success(page);
    }

    @Operation(summary = "批量删除学员")
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids){
        log.info("批量删除学员: {}", ids);
        java.util.List<Integer> idList = java.util.Arrays.stream(ids.split(","))
                .filter(s -> !s.isEmpty())
                .map(Integer::valueOf)
                .toList();
        studentService.delete(idList);
        return Result.success();
    }

    @Operation(summary = "新增学员")
    @PostMapping
    public Result add(@RequestBody @Valid Student student, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String msg = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .findFirst().orElse("参数错误");
            return Result.error(msg);
        }
        log.info("新增学员: {}", student);
        studentService.add(student);
        return Result.success();
    }

    @Operation(summary = "修改学员")
    @PutMapping
    public Result update(@RequestBody @Valid Student student, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String msg = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .findFirst().orElse("参数错误");
            return Result.error(msg);
        }
        if(student.getId() == null){
            return Result.error("缺少ID");
        }
        log.info("修改学员: {}", student);
        studentService.update(student);
        return Result.success();
    }

    @Operation(summary = "违纪处理")
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Integer score){
        log.info("违纪处理, id:{}, score:{}", id, score);
        studentService.violation(id, score);
        return Result.success();
    }
}
