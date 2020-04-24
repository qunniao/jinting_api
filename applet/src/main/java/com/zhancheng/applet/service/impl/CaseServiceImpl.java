package com.zhancheng.applet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.CaseService;
import com.zhancheng.commom.MapFactory;
import com.zhancheng.constant.R;
import com.zhancheng.dao.CaseAttrMapper;
import com.zhancheng.dao.CaseLabelMapper;
import com.zhancheng.dao.CaseLikeMapper;
import com.zhancheng.dao.CaseMapper;
import com.zhancheng.dto.PageDto;
import com.zhancheng.entity.Case;
import com.zhancheng.entity.CaseAttr;
import com.zhancheng.entity.CaseLabel;
import com.zhancheng.entity.CaseLike;
import com.zhancheng.vo.CaseVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 案例表 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
@Service
public class CaseServiceImpl extends ServiceImpl<CaseMapper, Case> implements CaseService {

    @Resource
    private CaseAttrMapper caseAttrMapper;

    @Resource
    private CaseLabelMapper caseLabelMapper;

    @Resource
    private CaseLikeMapper caseLikeMapper;

    @Override
    public R getCase(String bid, Integer page, String word) {
        ArrayList<Map<String, Object>> array = new ArrayList();
        QueryWrapper<Case> queryWrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(bid)) {
            queryWrapper.lambda().eq(Case::getTid, bid);
        }
        queryWrapper.lambda().eq(Case::getIsDeleted, false).orderByDesc(Case::getGmtCreate).like(Case::getName, word);
        //先分页查询案例
        IPage<Case> cases = page(new Page<>(page, 10), queryWrapper);
        List<Case> records = cases.getRecords();
        //再查询案例的属性
        records.stream().forEach(x -> {
            //List<CaseAttr> caseAttrs = caseAttrMapper.selectList(new QueryWrapper<CaseAttr>().eq("cid", x.getCid()));

            List<CaseAttr> caseAttrs = caseAttrMapper.queryList(x.getCid());
            HashMap<String, Object> temp = new HashMap<>(2);
            temp.put("case", x);
            temp.put("caseAttrs", caseAttrs);
            array.add(temp);
            Map<String, Object> map = MapFactory.getHashMap();
            map.put("bid", x.getTid());
            List<CaseLabel> caseLabels = caseLabelMapper.selectByMap(map);
            if (CollectionUtil.isNotEmpty(caseLabels)) {
                temp.put("typeName", caseLabels.get(0).getLabelName());
            }
        });

        return R.ok(array);
    }

    @Override
    public IPage<Case> getList(PageDto<Case> pageDto, String sortType, String address) {
        QueryWrapper<Case> caseQueryWrapper = new QueryWrapper<Case>();

        if (StrUtil.isBlank(sortType)){
            sortType = "";
        }

        caseQueryWrapper.eq("is_deleted", 0);
        caseQueryWrapper.like(StrUtil.isNotBlank(address), "address", address);
        switch (sortType){
            case "1": {
                caseQueryWrapper.orderByDesc("gmt_create");
            }
                break;
            case "2": {
                caseQueryWrapper.orderByAsc("min_price");
            }
                break;
            case "3": {
                caseQueryWrapper.orderByDesc("min_price");
            }
                break;
            default:
                break;
        }

        return baseMapper.selectPage(pageDto.getPage(), caseQueryWrapper);
    }

    @Override
    public CaseVo queryInfo(Integer cid, Integer uid) {

        Case aCase = baseMapper.queryInfo(cid);

        if(ObjectUtil.isNull(aCase)){
            return null;
        }

        CaseVo caseVo = new CaseVo();
        BeanUtil.copyProperties(aCase, caseVo,"attrs", "caseLabels");

        Integer likeNum = caseLikeMapper.selectCount(new QueryWrapper<CaseLike>().eq("cid", cid)
                .eq("is_like", 1).eq("is_deleted", 0));

        CaseLike caseLike = caseLikeMapper.selectOne(new QueryWrapper<CaseLike>().eq("cid", cid)
                .eq("is_deleted", 0).eq("uid", uid));

        // 非空 并且 为 收藏为true
        if (ObjectUtil.isNotNull(caseLike)){
            caseVo.setCaseLikeId(caseLike.getId());
            caseVo.setIsCollection(caseLike.getIsCollection());
        }

        caseVo.setLikeNum(likeNum);

        System.out.println(caseVo);

        return caseVo;
    }
}
