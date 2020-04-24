package com.zhancheng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 新闻资讯
 * zc_news 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-20 16:35:52
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_news")
public class News extends Model<News> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * zc_news_type 分类id  
	 */
	private Integer typeId;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 封面图
	 */
	private String cover;
	/**
	 * 新闻内容
	 */
	private String content;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 浏览量
	 */
	private Integer watchNum;
	/**
	 * 是否删除 0:未删除; 1:删除
	 */
	@TableLogic
	private Integer isDeleted;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 修改时间
	 */
	private Date gmtModified;

	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
