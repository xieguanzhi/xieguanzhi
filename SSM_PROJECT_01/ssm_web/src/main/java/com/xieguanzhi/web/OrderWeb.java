package com.xieguanzhi.web;

import com.github.pagehelper.PageInfo;
import com.xieguanzhi.domain.Order;
import com.xieguanzhi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderWeb {

    @Autowired
    OrderService orderServiceImpl;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",defaultValue = "1",required = false) Integer page,
                                @RequestParam(name = "pageSize",defaultValue = "4",required = false) Integer pageSize)
    {
        List<Order> all = orderServiceImpl.findAll(page,pageSize);
        ModelAndView mv = new ModelAndView();
        PageInfo pageInfo = new PageInfo(all);
        mv.addObject("ordersList",pageInfo.getList());
        mv.addObject("page",page);
        mv.addObject("pageSize",pageSize);
        mv.addObject("totalPage",pageInfo.getPages());
        mv.setViewName("orders-list");
        return mv;
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        ModelAndView mv = new ModelAndView();
        Order order = orderServiceImpl.findById(id);
        mv.addObject("orders",order);
        mv.setViewName("orders-show");
        return mv;
    }
}
