package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.entity.MessageResult;
import com.pinyougou.entity.PageResult;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.pojo.TbBrandExample.Criteria;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private TbBrandMapper brandMapper;
    @Override
    public PageResult<TbBrand> findList(Integer pageNum, Integer pageSize, TbBrand tbBrand) {
        PageResult<TbBrand> pageResult = new PageResult<>();
        Page<TbBrand> tbBrands = null;
        PageHelper.startPage(pageNum,pageSize);
        if(tbBrand!=null){
            TbBrandExample tbBrandExample = new TbBrandExample();
            Criteria criteria = tbBrandExample.createCriteria();
            if(tbBrand.getFirstChar()!=null&&tbBrand.getFirstChar().length()!=0){
                criteria.andFirstCharLike("%"+tbBrand.getFirstChar()+"%");
            }
            if(tbBrand.getName()!=null&&tbBrand.getName().length()!=0){
                criteria.andNameLike("%"+tbBrand.getName()+"%");
            }
            tbBrands = (Page<TbBrand>)brandMapper.selectByExample(tbBrandExample);
        }else {
            tbBrands = (Page<TbBrand>)brandMapper.selectByExample(null);
        }
        pageResult.setList(tbBrands.getResult());
        pageResult.setTotal(tbBrands.getTotal());
        return pageResult;
    }

    @Override
    public MessageResult add(TbBrand tbBrand) {
        MessageResult mr = new MessageResult();
        try {
            brandMapper.insert(tbBrand);
            mr.setSuccess(true);
        }catch (Exception e){
            mr.setMessage("出错了,请重新输入...");
            mr.setSuccess(false);
        }
        return mr;
    }

    @Override
    public TbBrand findById(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public MessageResult update(TbBrand tbBrand) {
        MessageResult mr = new MessageResult();
        try {
            brandMapper.updateByPrimaryKey(tbBrand);
            mr.setSuccess(true);
        }catch (Exception e){
            mr.setMessage("出错了,请重新输入...");
            mr.setSuccess(false);
        }
        return mr;
    }

    @Override
    public MessageResult delete(List<String> list) {
        MessageResult mr = new MessageResult();
        try {
            for (String s : list) {
                long l = Long.parseLong(s);
                brandMapper.deleteByPrimaryKey(l);
            }
            mr.setSuccess(true);
        }catch (Exception e){
            mr.setMessage("出错了,请重新输入....");
            mr.setSuccess(false);
        }
        return mr;
    }
}
