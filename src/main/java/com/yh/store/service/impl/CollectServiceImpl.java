package com.yh.store.service.impl;

import com.yh.store.exception.InsertException;
import com.yh.store.exception.UpdateException;
import com.yh.store.mapper.CollectMapper;
import com.yh.store.mapper.ProductMapper;
import com.yh.store.pojo.Collect;
import com.yh.store.pojo.Product;
import com.yh.store.service.ICollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CollectServiceImpl implements ICollectService {

    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Integer addCollect(Integer uid, Integer pid, String username) {
        Collect result = collectMapper.queryCollectByUidAndPid(uid, pid);
       if (result == null){
           //添加收藏
           Collect collect = new Collect();
           //补全数据
           Date date = new Date();
           collect.setUid(uid);
           collect.setPid(pid);
           collect.setStatus(1);
           collect.setCreatedUser(username);
           collect.setCreatedTime(date);
           collect.setModifiedUser(username);
           collect.setModifiedTime(date);

           //调用produceMapper查询商品
           Product product = productMapper.findProductById(pid);
           collect.setPrice(product.getPrice());
           collect.setTitle(product.getTitle());
           collect.setImage(product.getImage());
           Integer row = collectMapper.insertCollect(collect);
           if (row != 1){
               throw new InsertException("添加收藏商品时发生异常");
           }
           return row;
       }else if (result.getStatus().equals(0)){
           Integer row = collectMapper.updateStatusByPid(pid, 1);
           if (row != 1){
               throw new UpdateException("更新时发生异常");
           }
           return row;
       }else {
           return 0;
       }
    }

    @Override
    public List<Collect> getCollects(Integer uid) {
        List<Collect> collects = collectMapper.queryAllByUid(uid);
        return collects;
    }

    @Override
    public void updateStatus(Integer pid, Integer status) {
        Integer row = collectMapper.updateStatusByPid(pid, status);
        if (row != 1){
            throw new UpdateException("修改收藏状态发生异常");
        }
    }
}
