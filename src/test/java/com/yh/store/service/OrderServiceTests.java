package com.yh.store.service;

import com.yh.store.pojo.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceTests {

    @Autowired
    private IOrderService orderService;

    @Test
    public void createOrder(){
        Integer[] cids = {9};
        Order order = orderService.createOrder(7, 8, 9000L, "test03", cids);
        System.out.println(order);
    }

    @Test
    public void updateOrderStatus(){
        orderService.updateOrderStatus(27,1);
    }
}
