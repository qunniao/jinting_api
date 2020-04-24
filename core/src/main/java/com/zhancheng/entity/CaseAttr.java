package com.zhancheng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 案例属性名称和值
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_case_attr")
public class CaseAttr extends Model<CaseAttr> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "aid", type = IdType.AUTO)
    @ApiModelProperty(name = "aid", value = "属性id", example = "1")
    private Integer aid;

    @ApiModelProperty(name = "cid", value = "案例id", example = "1")
    private Integer cid;

    @ApiModelProperty(name = "attrName", value = "属性名", example = "颜色")
    private String attrName;

    @ApiModelProperty(name = "attrValue", value = "属性值", example = "红色")
    private String attrValue;

    @ApiModelProperty(name = "isRecommend", value = "是否推荐，是否显示在首页", example = "1")
    private Integer isRecommend;

    @ApiModelProperty(name = "sort", value = "排序", example = "1")
    private Integer sort;

    @JsonIgnore
    private Date gmtCreate;
    @JsonIgnore
    private Date gmtModified;

    @Override
    protected Serializable pkVal() {
        return this.aid;
    }

}
