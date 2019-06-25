package com.xieguanzhi.service.impl;

import com.github.pagehelper.PageHelper;
import com.xieguanzhi.dao.OrderDao;
import com.xieguanzhi.domain.Order;
import com.xieguanzhi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Override
    public List<Order> findAll(Integer page,Integer pagesize) {
        PageHelper.startPage(page,pagesize);
        return orderDao.findAll();
    }

    @Override
    public Order findById(String id) {
        return orderDao.findById(id);
    }

}
