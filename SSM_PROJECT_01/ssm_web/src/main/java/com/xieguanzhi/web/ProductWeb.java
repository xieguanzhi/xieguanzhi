package com.xieguanzhi.web;

import com.xieguanzhi.domain.Product;
import com.xieguanzhi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequestMapping("/product")
public class ProductWeb {
    @Autowired
    private ProductService productServiceImpl;
    @RequestMapping("/test.do")
    public ModelAndView test(){
        List<Product> all = productServiceImpl.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg",all);
        mv.setViewName("success");
        return mv;
    }
    @RequestMapping("findAll.do")
    public ModelAndView findAll(){
        List<Product> all = productServiceImpl.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("productList",all);
        mv.setViewName("product-list");
        return mv;
    }

    @RequestMapping("save.do")
    public ModelAndView save(Product product){
        System.out.println(product);
        productServiceImpl.saveProduct(product);
        return findAll();
    }
}
