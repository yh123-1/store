package com.yh.store.mapper;

import com.yh.store.pojo.Collect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CollectMapperTests {

    @Autowired
    private CollectMapper collectMapper;

    @Test
    public void insertCollect(){
        Collect collect = new Collect();
        collect.setUid(6);
        collect.setPid(10000004);
        collect.setPrice(58L);
        collect.setTitle("得力（deli）1548A商务办公桌面计算器 太阳能双电源");
        Integer row = collectMapper.insertCollect(collect);
        System.out.println(row);
    }

    @Test
    public void queryCollectByUidAndPid(){
        Collect collect = collectMapper.queryCollectByUidAndPid(8, 10000004);
        System.out.println(collect);
    }

    @Test
    public void queryAllByUid(){
        List<Collect> collects = collectMapper.queryAllByUid(8);
        for (Collect collect : collects) {
            System.out.println(collect);
        }
    }

    @Test
    public void updateStatusByPid(){
        Integer row = collectMapper.updateStatusByPid(10000042, 1);
        System.out.println(row);
    }
}
