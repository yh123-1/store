package com.yh.store.service;

import com.yh.store.pojo.Collect;

import java.util.List;

public interface ICollectService {

    /**
     * 添加收藏
     * @param uid 用户id
     * @param pid 商品id
     * @param username 用户名
     * @return 在收藏中的数量
     */
    Integer addCollect(Integer uid,Integer pid,String username);

    /**
     * 获得用户所有收藏
     * @param uid 用户id
     * @return 所有收藏数据
     */
    List<Collect> getCollects(Integer uid);

    /**
     * 修改收藏状态
     * @param pid
     * @param status
     */
    void updateStatus(Integer pid,Integer status);
}
