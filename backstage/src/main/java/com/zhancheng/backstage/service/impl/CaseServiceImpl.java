package com.zhancheng.backstage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.zhancheng.backstage.service.CaseImageService;
import com.zhancheng.backstage.service.CaseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.config.exception.MyException;
import com.zhancheng.constant.CodeMsg;
import com.zhancheng.dao.CaseAttrMapper;
import com.zhancheng.dao.CaseMapper;
import com.zhancheng.dto.CaseDto;
import com.zhancheng.dto.JsonDto;
import com.zhancheng.entity.Case;
import com.zhancheng.entity.CaseAttr;
import com.zhancheng.backstage.service.CaseAttrService;

import com.zhancheng.entity.CaseImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    private CaseAttrService caseAttrService;

    @Resource
    private CaseAttrMapper caseAttrMapper;

    @Resource
    private CaseImageService caseImageService;

    @Override
    public IPage<Case> queryList(Page page) {

        return baseMapper.queryList(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveInfo(CaseDto caseDto) {

        if (ObjectUtil.isNull(caseDto)) {
            throw new MyException(CodeMsg.PARAMETER_NULL);
        }

        Case caseInfo = new Case();
        BeanUtil.copyProperties(caseDto, caseInfo);
        boolean insert = caseInfo.insert();
        caseDto.setCid(caseInfo.getCid());

        addAttr(caseDto);

        addCaseImage(caseDto.getCaseImageList(), caseInfo.getCid());

        return insert;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateInfo(CaseDto caseDto) {

        System.err.println(caseDto);

        //轮播图
        List<CaseImage> caseImageList = caseDto.getCaseImageList();

        if (ObjectUtil.isNull(caseDto)) {
            throw new MyException(CodeMsg.PARAMETER_NULL);
        }

        Case caseInfo = baseMapper.queryInfo(caseDto.getCid());
        BeanUtil.copyProperties(caseDto, caseInfo);
        System.err.println(caseInfo);
        int update = baseMapper.updateById(caseInfo);
        System.err.println(caseInfo);
        List<CaseAttr> caseAttrList = caseAttrMapper.selectList(new QueryWrapper<CaseAttr>().eq("cid", caseDto.getCid()));

        System.out.println(caseAttrList);
        if (ObjectUtil.isNotEmpty(caseAttrList)) {
            int cid = caseAttrMapper.delete(new QueryWrapper<CaseAttr>().eq("cid", caseDto.getCid()));
            System.out.println(cid);
        }

        List<CaseImage> caseImages = caseImageService.list(new QueryWrapper<CaseImage>().eq("cid", caseDto.getCid()));

        if (ObjectUtil.isNotNull(caseImages)) {
            caseImageService.remove(new QueryWrapper<CaseImage>().eq("cid",caseDto.getCid()));
        }

        // 添加轮播图
        addCaseImage(caseImageList, caseInfo.getCid());

        // 添加案例标签
        addAttr(caseDto);

        return update > 0;
    }

    @Override
    public Case queryInfo(Integer cid) {

        return baseMapper.queryInfo(cid);
    }

    private Boolean addAttr(CaseDto caseDto) {
        JsonDto[] attrs = caseDto.getAttrs();
        if (ArrayUtil.isNotEmpty(attrs)) {
            List<CaseAttr> caseAttrList = new LinkedList<>();
            String[] nameStr;
            for (JsonDto jsonDto : attrs) {
                System.err.println("jsonDto" + jsonDto);
                CaseAttr caseAttr = new CaseAttr();
                nameStr = jsonDto.getName().split("###");
                if(null !=nameStr && nameStr.length>1){
                    //最新，把排序和推荐放名称里了
                    caseAttr.setAttrName(nameStr[0]);
                    caseAttr.setSort(new Integer(nameStr[1]));
                    caseAttr.setIsRecommend(new Integer(nameStr[2]));
                }else{
                    caseAttr.setAttrName(jsonDto.getName());
                    caseAttr.setSort(0);
                    caseAttr.setIsRecommend(1);
                }
                caseAttr.setAttrValue(jsonDto.getValue()).setCid(caseDto.getCid());
                caseAttrList.add(caseAttr);
            }
            return caseAttrService.saveBatch(caseAttrList);
        }

        return false;
    }

    private Boolean addCaseImage(List<CaseImage> caseImageList, Integer cid) {

        if (ObjectUtil.isNotEmpty(caseImageList)) {

            for (CaseImage caseImage : caseImageList) {
                caseImage.setCid(cid);
            }

            return  caseImageService.saveBatch(caseImageList);
        }

        return false;
    }
}
