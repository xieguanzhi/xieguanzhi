package com.pinyougou.sellergoods.service;


import java.util.List;

import com.pinyougou.pojo.TbBrand;

import com.pinyougou.entity.MessageResult;
import com.pinyougou.entity.PageResult;

public interface BrandService {

    public PageResult<TbBrand> findList(Integer pageNum,Integer pageSize,TbBrand tbBrand);

    public MessageResult add(TbBrand tbBrand);

    public TbBrand findById(Long id);

    public MessageResult update(TbBrand tbBrand);

    public MessageResult delete(List<String> list);
}

