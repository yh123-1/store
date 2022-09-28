package com.yh.store.mapper;

import com.yh.store.pojo.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductMapperTests {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void findHotList(){
        List<Product> hotList = productMapper.findHotList();
        for (Product product : hotList){
            System.out.println(product);
        }
    }
}
