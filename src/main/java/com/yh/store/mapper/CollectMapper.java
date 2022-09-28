package com.yh.store.mapper;

import com.yh.store.pojo.Collect;

import java.util.List;

public interface CollectMapper {

    /**
     * 插入collect数据
     * @param collect
     * @return
     */
    Integer insertCollect(Collect collect);

    /**
     * 根据uid和pid查询collect
     * @param uid
     * @param pid
     * @return
     */
    Collect queryCollectByUidAndPid(Integer uid,Integer pid);

    /**
     * 根据uid查询所有收藏
     * @param uid 用户id
     * @return 所有收藏数据
     */
    List<Collect> queryAllByUid(Integer uid);

    /**
     * 根据pid修改收藏状态
     * @param pid 商品id
     * @param status 状态
     * @return 受影响的行数
     */
    Integer updateStatusByPid(Integer pid,Integer status);
}
