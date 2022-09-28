package com.yh.store.mapper;


import com.yh.store.pojo.Order;
import com.yh.store.pojo.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrderMapperTests {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void insertOrder(){
        Order order = new Order();
        order.setUid(8);
        order.setRecvName("test01");
        order.setRecvPhone("123456789");
        Integer rows = orderMapper.insertOrder(order);
        System.out.println(rows);
    }

    @Test
    public void insertOrderItem(){
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(10000002);
        orderItem.setTitle("广博(GuangBo)皮面日程本子 计划记事本效率手册米色FB60322");
        Integer rows = orderMapper.insertOrderItem(orderItem);
        System.out.println(rows);
    }

    @Test
    public void queryOrderByOid(){
        Order order = orderMapper.queryOrderByOid(3);
        System.out.println(order);
    }

    @Test
    public void updateOrderByOid(){
        Order order = new Order();
        order.setOid(18);
        order.setStatus(1);
        Integer row = orderMapper.updateOrderByOid(order);
        System.out.println(row);
    }

    @Test
    public void queryOrderItemByOid(){
        List<OrderItem> orderItems = orderMapper.queryOrderItemByOid(21);
        for (OrderItem orderItem : orderItems){
            System.out.println(orderItem);
        }
    }
}
