package com.zhancheng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 案例浏览点赞收藏
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_case_like")
public class CaseLike extends Model<CaseLike> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(name = "uid", value = "用户id")
    private Integer uid;

    @ApiModelProperty(name = "cid", value = "案例id")
    private Integer cid;

    @ApiModelProperty(name = "count", value = "")
    private Integer count;

    @ApiModelProperty(name = "count", value = "是否为浏览")
    private Boolean isWatch;

    @ApiModelProperty(name = "count", value = "是否为点赞")
    private Boolean isLike;

    @ApiModelProperty(name = "isCollection", value = "是否为收藏")
    private Boolean isCollection;

    @TableLogic
    private Boolean isDeleted;

    private Date gmtCreate;

    private Date gmtModified;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
