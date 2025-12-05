package com.shengxi.pojo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OperateLog {
    private Integer id;
    private Integer operateEmpId;
    private LocalDateTime operateTime;
    private String className;
    private String methodName;
    private String methodParams;
    private String returnValue;
    private Integer costTime;
    private String operateEmpName;
}

