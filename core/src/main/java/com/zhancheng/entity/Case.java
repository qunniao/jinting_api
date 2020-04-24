package com.zhancheng.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 案例表
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_case")
public class Case extends Model<Case> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "cid", value = "案例id", example = "1")
    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;

    @ApiModelProperty(name = "sid", value = "店铺id", example = "1")
    private Integer sid;

    @ApiModelProperty(name = "tid", value = "案例类型id", example = "1")
    private Integer tid;

    @ApiModelProperty(name = "name", value = "案例名称", example = "APP开发")
    private String name;

    @ApiModelProperty(name = "cover", value = "案例图片地址")
    private String cover;

    @ApiModelProperty(name = "video", value = "视频")
    private String video;

    @ApiModelProperty(name = "minPrice", value = "最小价格", example = "1.00")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private BigDecimal minPrice;
    @ApiModelProperty(name = "maxPrice", value = "最大价格", example = "1.00")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private BigDecimal maxPrice;

    @ApiModelProperty(name = "mobileDetail", value = "移动端详情")
    private String mobileDetail;
    @ApiModelProperty(name = "content", value = "详细内容")
    private String content;

    @ApiModelProperty(name = "address", value = "地址")
    private String address;

    @TableField(value = "is_recommend")
    @ApiModelProperty(name = "recommend", value = "是否推荐", example = "1")
    private Boolean recommend;

    @TableLogic
    @JsonIgnore
    @ApiModelProperty(name = "isDeleted", value = "是否删除", example = "1")
    private Boolean isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "gmtCreate", value = "创建时间", example = "1")
    private Date gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "gmtModified", value = "修改时间", example = "1")
    private Date gmtModified;

    @TableField(exist = false)
    private List<CaseAttr> caseAttrs;

    @TableField(exist = false)
    private List<CaseImage> caseImageList;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist = false)
    private CaseLabel caseLabels;

    @Override
    protected Serializable pkVal() {
        return this.cid;
    }
}
