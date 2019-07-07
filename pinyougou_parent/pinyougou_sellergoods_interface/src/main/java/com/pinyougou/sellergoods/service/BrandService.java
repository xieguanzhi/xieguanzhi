package com.pinyougou.sellergoods.service;


import java.util.List;

import com.pinyougou.pojo.TbBrand;

import entity.MessageResult;
import entity.PageResult;

public interface BrandService {

	public PageResult<TbBrand> findAll(Integer pageNum, Integer pageSize, TbBrand tbBrand);

	public MessageResult add(TbBrand tbBrand);
	
	public TbBrand findById(Long id);
	
	public MessageResult update(TbBrand tbBrand);

	public MessageResult delete(List<String> list);
}
