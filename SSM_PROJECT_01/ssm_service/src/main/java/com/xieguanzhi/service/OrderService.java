package com.xieguanzhi.service;

import com.xieguanzhi.domain.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll(Integer page,Integer pagesize);

    Order findById(String id);
}
