package com.zhancheng.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author tangchao
 */
@Data
@ApiModel(value = "分页参数实体类")
public class PageDto<T> {

    @ApiModelProperty(name= "current", value = "当前页码", required = true)
    @NotNull(message = "页码不能为空")
    private Integer current;
    @ApiModelProperty(name= "size", value = "页容量", required = true)
    @NotNull(message = "页容量不能为空")
    private Integer size;

    public Page<T> getPage(){

        return new Page<T>(current, size);
    }

}
