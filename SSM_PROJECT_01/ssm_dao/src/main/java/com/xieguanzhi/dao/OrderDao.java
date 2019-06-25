package com.xieguanzhi.dao;

import com.xieguanzhi.domain.Order;
import com.xieguanzhi.domain.Traveller;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {
    @Select("SELECT * FROM orders")
    @Results(
            id = "orderMapper",
            value = {
                    @Result(
                            column = "ID",
                            property = "id"
                    ),
                    @Result(
                            column = "PRODUCTID",
                            property = "product",
                            one = @One(
                                    select = "com.xieguanzhi.dao.ProductDao.findById",
                                    fetchType = FetchType.LAZY
                            )
                    ),
                    @Result(
                            column = "MEMBERID",
                            property = "member",
                            one = @One(
                                    select = "com.xieguanzhi.dao.MemberDao.findById",
                                    fetchType = FetchType.LAZY
                            )
                    ),
                    @Result(
                            column = "ID",
                            property = "travellers",
                            many = @Many(
                                    select = "com.xieguanzhi.dao.OrderDao.findTravellers",
                                    fetchType = FetchType.LAZY
                            )
                    )
            })
    List<Order> findAll();

    @Select("SELECT * FROM orders WHERE id = #{id}")
    @ResultMap("orderMapper")
    Order findById(String id);

    //查询订单表,旅行表,中间表
    @Select("select * from order_traveller ot,traveller t where ot.travellerid=t.id and ot.orderid=#{id}")
    List<Traveller> findTravellers(String id);
}
