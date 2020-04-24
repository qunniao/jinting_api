package com.zhancheng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 友情链接表
 * zc_link 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-26 15:27:44
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_link")
public class Link extends Model<Link> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id", value = "主键id")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	@ApiModelProperty(name = "name", value = "名称")
	private String name;
	@ApiModelProperty(name = "cover", value = "图标")
	private String cover;
	@ApiModelProperty(name = "www", value = "跳转网站")
	private String www;
	@ApiModelProperty(name = "province", value = "省")
	private String province;

	@ApiModelProperty(name = "city", value = "市")
	private String city;

	@ApiModelProperty(name = "typeInt", value = "0表示所有页面都放")
	private String typeInt;

	@ApiModelProperty(name = "sort", value = "排序")
	private Integer sort;

	@ApiModelProperty(name = "isDeleted", value = "是否删除 0:未删除; 1:删除")
	private Integer isDeleted;

	@ApiModelProperty(name = "gmtCreate", value = "创建时间")
	private Date gmtCreate;

	@ApiModelProperty(name = "gmtModified", value = "修改时间")
	private Date gmtModified;

	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
