package com.zhancheng.vo;

import com.zhancheng.entity.Case;
import com.zhancheng.entity.CaseAttr;
import com.zhancheng.entity.CaseImage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author tangchao
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CaseVo extends Case {

    @ApiModelProperty(value = "属性信息", name = "caseAttr")
    private List<CaseAttr> caseAttr;

    @ApiModelProperty(value = "点赞数量", name = "likeNum")
    private Integer likeNum;

    @ApiModelProperty(value = "是否收藏", name = "favorite")
    private Boolean isCollection;

    private Integer caseLikeId;
}
