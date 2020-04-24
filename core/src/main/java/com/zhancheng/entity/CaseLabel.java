package com.zhancheng.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * <p>
 * 案例分类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_case_label")
public class CaseLabel extends Model<CaseLabel> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "bid", type = IdType.AUTO)
    @ApiModelProperty(name = "bid", value = "案例分类id")
    private Integer bid;

    @ApiModelProperty(name = "pid", value = "上级id")
    private Integer pid;

    @ApiModelProperty(name = "labelName", value = "标签名称")
    private String labelName;

    @ApiModelProperty(name = "cover", value = "图标")
    private String cover;
    /**
     * 上级标签名称
     */
    @ApiModelProperty(name = "superiorLabelName", value = "上级标签名称")
    @TableField(exist = false)
    private String superiorLabelName;

    @TableLogic
    @JsonIgnore
    private Boolean isDeleted;
    @JsonIgnore
    private Date gmtCreate;
    @JsonIgnore
    private Date gmtModified;

    @TableField(exist = false)
    private Set<CaseLabel> caseLabelSet;

    @Override
    protected Serializable pkVal() {
        return this.bid;
    }

}
