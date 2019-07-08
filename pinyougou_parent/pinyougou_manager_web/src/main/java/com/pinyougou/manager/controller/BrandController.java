package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.entity.MessageResult;
import com.pinyougou.entity.PageResult;
import com.pinyougou.pojo.TbBrand;
import org.springframework.web.bind.annotation.*;
import com.pinyougou.sellergoods.service.BrandService;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    @RequestMapping("/findList.do")
    public PageResult<TbBrand> findList(
            @RequestParam(name = "pageNum",required = false,defaultValue = "1")Integer pageNum,
            @RequestParam(name = "pageSize",required = false,defaultValue = "10")Integer pageSize,
            @RequestBody(required = false) TbBrand searchBrand
    )
    {
        return brandService.findList(pageNum,pageSize,searchBrand);
    }

    @RequestMapping("/add.do")
    public MessageResult add(@RequestBody TbBrand brand){
        return brandService.add(brand);
    }

    @RequestMapping("/findById.do")
    public TbBrand findById(Long id){
        return brandService.findById(id);
    }

    @RequestMapping("/update.do")
    public MessageResult update(@RequestBody TbBrand tbBrand){
        return brandService.update(tbBrand);
    }

    @RequestMapping("/delete.do")
    public MessageResult delete(@RequestBody List<String> list){
        return brandService.delete(list);
    }
}
