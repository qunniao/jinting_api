package com.zhancheng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 轮播图
 * zc_banner 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:30
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_banner")
@ApiModel(value = "轮播图")
public class Banner extends Model<Banner> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id", value = "轮播图id")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(name = "type", value = "类型：1.首页")
	private Integer type;

	@ApiModelProperty(name = "fileType", value = "文件类型 1: 图片，2：视频")
	private Integer fileType;

	@ApiModelProperty(name = "cover", value = "图片")
	private String cover;

	@ApiModelProperty(name = "productId", value = "商品id")
	private Integer productId;

	@ApiModelProperty(name = "url", value = "外部链接")
	private String url;

	@ApiModelProperty(name = "sort", value = "排序")
	private Integer sort;

	@ApiModelProperty(name = "isDeleted", value = "是否删除 0:未删除; 1:删除")
	@TableLogic
	private Integer isDeleted;

	@ApiModelProperty(name = "gmtCreate", value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtCreate;

	@ApiModelProperty(name = "gmtModified", value = "修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtModified;

	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
