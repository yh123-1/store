package com.yh.store.mapper;

import com.yh.store.vo.CartVO;
import com.yh.store.pojo.Cart;

import java.util.Date;
import java.util.List;

public interface CartMapper {
    /**
     * 插入购物车数据
     * @param cart 购物车数据
     * @return 受影响的行数
     */
    Integer insertCart(Cart cart);

    /**
     * 更新购物车某件商品的数量
     * @param cid 购物车id
     * @param num 更新的数量
     * @param modifiedTime 更新的时间
     * @param modifiedUser 更新的用户
     * @return
     */
    Integer updateNumByCid(Integer cid, Integer num, String modifiedUser, Date modifiedTime);

    /**
     * 根据uid，pid查询用户某个商品的信息
     * @param uid 用户id
     * @param pid 商品id
     * @return 购物车数据
     */
    Cart findByUidAndPid(Integer uid,Integer pid);

    /**
     * 根据uid查询购物车列表
     * @param uid 用户id
     * @return 购物车列表数据
     */
    List<CartVO> findVOByUid(Integer uid);

    /**
     * 根据cid查询购物车数据
     * @param cid
     * @return 购物车数据
     */
    Cart findByCid(Integer cid);

    /**
     * 根据cid删除购物车数据
     * @param cid
     * @return 受影响的行数
     */
    Integer deleteByCid(Integer cid);

    /**
     * 根据选中的商品cid查询VO
     * @param cids
     * @return VO集合
     */
    List<CartVO> findVOListByCid(Integer[] cids);

    Integer delByCids(Integer[] cids);
}
