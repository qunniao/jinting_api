package com.zhancheng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 公司信息
 * zc_company_info 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-14 14:13:43
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("zc_company_info")
@Valid
public class CompanyInfo extends Model<CompanyInfo> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 公司名称
	 */
	private String name;

	/**
	 * 公司介绍
	 */
	private String intro;

	/**
	 * 微信号
	 */
	private String wxNumber;

	/**
	 * qq号
	 */
	private String qqNumber;

	/**
	 * 邮箱
	 */
	@Email(message = "请输入正确的邮箱格式")
	private String email;

	/**
	 * 手机号
	 */
	@Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "请输入正确的手机号")
	private String phone;

	/**
	 * 公司地址
	 */
	private String address;

	/**
	 * 主营业务
	 */
	private String businessJson;

	/**
	 * 办公环境
	 */
	private String environmentJson;

	/**
	 * 荣誉资质
	 */
	private String certificateJson;

	/**
	 * 团队活动
	 */
	private String activitiesJson;

	/**
	 * 修改时间
	 */
	private Date gmtModified;

	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
