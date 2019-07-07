package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.pojo.TbBrandExample.Criteria;
import com.pinyougou.sellergoods.service.BrandService;
import entity.MessageResult;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    TbBrandMapper tbBrandMapper;

    @Override
    public PageResult<TbBrand> findAll(Integer pageNum, Integer pageSize, TbBrand tbBrand) {
        PageResult<TbBrand> pageResult = new PageResult<>();
        TbBrandExample tbBrandExample = new TbBrandExample();
        PageHelper.startPage(pageNum,pageSize);
        Page<TbBrand> page = null;
        Criteria criteria = tbBrandExample.createCriteria();
        if(tbBrand!=null){
            if(tbBrand.getName().length()!=0) {
                criteria.andNameLike("%" + tbBrand.getName() + "%");
            }
            if(tbBrand.getFirstChar().length()!=0) {
                criteria.andFirstCharLike("%" + tbBrand.getFirstChar() + "%");
            }
            page = (Page<TbBrand>) tbBrandMapper.selectByExample(tbBrandExample);
        }else {
            page = (Page<TbBrand>) tbBrandMapper.selectByExample(null);
        }
        pageResult.setList(page.getResult());
        pageResult.setTotal(page.getTotal());
        return pageResult;
    }

    @Override
    public MessageResult add(TbBrand tbBrand) {
        return null;
    }

    @Override
    public TbBrand findById(Long id) {
        return null;
    }

    @Override
    public MessageResult update(TbBrand tbBrand) {
        return null;
    }

    @Override
    public MessageResult delete(List<String> list) {
        return null;
    }
}