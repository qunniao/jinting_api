package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.dao.CaseLabelMapper;
import com.zhancheng.entity.CaseLabel;
import com.zhancheng.backstage.service.CaseLabelService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 案例标签 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
@Service
public class CaseLabelServiceImpl extends ServiceImpl<CaseLabelMapper, CaseLabel> implements CaseLabelService {


    @Override
    public List<CaseLabel> queryList() {

        return baseMapper.queryList();
    }

    @Override
    public CaseLabel queryInfo(Integer bid) {
        return baseMapper.queryInfo(bid);
    }

    @Override
    public List<CaseLabel> secondList(Integer pid) {

        return baseMapper.selectList(new QueryWrapper<CaseLabel>().eq("pid", pid));
    }
}
