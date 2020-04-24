package com.zhancheng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 表单数据
 * zc_form 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-27 14:16:30
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_form")
public class Form extends Model<Form> {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(name = "id", value = "主键id")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(name = "uid", value = "用户id")
	private Integer uid;

	@ApiModelProperty(name = "name", value = "姓名")
	private String name;

	@ApiModelProperty(name = "phone", value = "联系方式")
	private String phone;

	@ApiModelProperty(name = "content", value = "表单内容")
	private String content;

	@ApiModelProperty(name = "is_deleted", value = "是否删除 0:未删除; 1:删除")
	@TableLogic
	private Integer isDeleted;

	@ApiModelProperty(name = "gmt_create", value = "创建时间")
	private Date gmtCreate;

	@ApiModelProperty(name = "gmt_modified", value = "修改时间")
	private Date gmtModified;

	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
