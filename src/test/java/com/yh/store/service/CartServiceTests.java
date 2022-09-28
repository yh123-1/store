package com.yh.store.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CartServiceTests {

    @Autowired
    private ICartService cartService;

    @Test
    public void addToCart(){
//        cartService.addToCart(8,10000001,5,"张三");
        cartService.addToCart(8,10000002,1,"张三");
    }

    @Test
    public void reduceNum(){
        Integer num = cartService.reduceNum(3, 8, "test03");
        System.out.println(num);
    }
}
