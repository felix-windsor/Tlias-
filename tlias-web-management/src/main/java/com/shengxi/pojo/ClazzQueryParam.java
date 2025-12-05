package com.shengxi.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "班级查询参数")
public class ClazzQueryParam {

    @Schema(description = "班级名称")
    @Size(max = 100, message = "班级名称长度不能超过100")
    private String name;

    @Schema(description = "开课时间，格式yyyy-MM-dd")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "begin日期格式应为yyyy-MM-dd")
    private String begin;

    @Schema(description = "结课时间，格式yyyy-MM-dd")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "end日期格式应为yyyy-MM-dd")
    private String end;

    @Schema(description = "页码，从1开始")
    @Min(value = 1, message = "页码至少为1")
    private Integer page = 1;

    @Schema(description = "每页记录数")
    @Min(value = 1, message = "每页记录数至少为1")
    @Max(value = 200, message = "每页记录数最大200")
    private Integer pageSize = 10;

}
