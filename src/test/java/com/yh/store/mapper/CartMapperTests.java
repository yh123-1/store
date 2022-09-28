package com.yh.store.mapper;

import com.yh.store.pojo.Cart;
import com.yh.store.pojo.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class CartMapperTests {

    @Autowired
    private CartMapper cartMapper;

    @Test
    public void insertCart(){

        Cart cart = new Cart();
        cart.setUid(8);
        cart.setPid(10000001);
        cart.setNum(2);
        cart.setPrice(23L);
        Integer rows = cartMapper.insertCart(cart);
        System.out.println(rows);
    }

    @Test
    public void updateNumByCid(){
        Integer rows = cartMapper.updateNumByCid(1, 4, "张三", new Date());
        System.out.println(rows);
    }

    @Test
    public void findByUidAndPid(){
        Cart cart = cartMapper.findByUidAndPid(8, 10000001);
        System.out.println(cart);
    }

    @Test
    public void findVOByUid(){
        System.out.println(cartMapper.findVOByUid(8));
    }

    @Test
    public void findByCid(){
        System.out.println(cartMapper.findByCid(1));
    }

    @Test
    public void findVOListByCid(){
        Integer[] cids = {3,4,5,6};
        System.out.println(cartMapper.findVOListByCid(cids));
    }

    @Test
    public void delByCids(){
        Integer[] cids = {3,6};
        Integer rows = cartMapper.delByCids(cids);
        System.out.println(rows);
    }
}
