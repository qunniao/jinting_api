package com.zhancheng.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zhancheng.entity.CaseImage;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project CaseDto
 * @date 2019/9/23 21:16
 */
@Data
public class CaseDto implements Serializable {

    private Integer cid;
    private Integer sid;
    private Integer tid;
    private String name;
    private String cover;
    private String video;
    private String address;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String mobileDetail;
    private String content;
    private Boolean recommend;
    private JsonDto[] attrs;

    private List<CaseImage> caseImageList;
}
