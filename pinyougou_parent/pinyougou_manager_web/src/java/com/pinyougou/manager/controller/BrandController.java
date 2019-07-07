package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import entity.PageResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    BrandService brandServiceImpl;

    @RequestMapping("/findAll.do")
    public PageResult<TbBrand> findAll(
            @RequestParam(required = false,defaultValue = "1")Integer pageNum,
            @RequestParam(required = false,defaultValue = "10")Integer pageSize,
            @RequestParam(required = false)TbBrand tbBrand
    )
    {
        return brandServiceImpl.findAll(pageNum,pageSize,tbBrand);
    }

}
