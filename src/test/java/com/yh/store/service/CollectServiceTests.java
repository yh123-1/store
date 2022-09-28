package com.yh.store.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CollectServiceTests {

    @Autowired
    private ICollectService collectService;

    @Test
    public void addCollect(){

        Integer row = collectService.addCollect(8, 10000007, "test03");
        System.out.println(row);
    }

    @Test
    public void updateStatus(){
        collectService.updateStatus(10000042,0);
    }

}
