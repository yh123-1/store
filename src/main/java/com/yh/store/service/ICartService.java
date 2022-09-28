package com.yh.store.service;

import com.yh.store.pojo.Cart;
import com.yh.store.vo.CartVO;

import java.util.List;

public interface ICartService {

    /**
     * 添加商品到购物车
     * @param uid 用户id
     * @param pid 商品id
     * @param amount 数量
     * @param username 用户名
     */
    void addToCart(Integer uid,Integer pid,Integer amount,String username);

    /**
     * 查询购物车列表
     * @param uid
     * @return
     */
    List<CartVO> getVOByUid(Integer uid);

    /**
     * 增加购物车商品数量
     * @param cid
     * @param uid
     * @param username
     * @return 增加成功后新的数量
     */
    Integer addNum(Integer cid,Integer uid,String username);

    /**
     * 减少购物车商品数量
     * @param cid
     * @param uid
     * @param username
     * @return 减少成功后新的数量
     */
    Integer reduceNum(Integer cid,Integer uid,String username);

    /**
     * 获得选中的购物车列表
     * @param uid
     * @param cids
     * @return CartVO集合
     */
    List<CartVO> getVOByCid(Integer uid,Integer[] cids);

    /**
     * 删除多个购物车数据
     * @param cids
     * @return
     */
    void delCartByCid(Integer[] cids);
}
