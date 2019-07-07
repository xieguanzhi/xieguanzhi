package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import entity.PageResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    BrandService brandServiceImpl;

    @RequestMapping("/findAll.do")
    public PageResult<TbBrand> findAll(
            @RequestParam(name = "pageNum",required = false,defaultValue = "1")Integer pageNum,
            @RequestParam(name = "pageSize",required = false,defaultValue = "10")Integer pageSize,
            @RequestBody(required = false)TbBrand tbBrand
    )
    {
        return brandServiceImpl.findAll(pageNum,pageSize,tbBrand);
    }

}
