package com.yh.store.service;

import com.yh.store.pojo.Order;
import com.yh.store.vo.OrderItemVO;

import java.util.List;

public interface IOrderService {

    /**
     * 添加订单数据
     * @param aid
     * @param uid
     * @param totalPrice
     * @param username
     * @param cids
     * @return
     */
    Order createOrder(Integer aid,Integer uid,Long totalPrice,String username,Integer[] cids);


    /**
     * 获取订单数据
     * @param oid
     * @return
     */
    Order queryOrder(Integer oid);

    /**
     * 修改订单状态
     * @param oid
     * @param status
     */
    void updateOrderStatus(Integer oid,Integer status);

    /**
     * 获取订单列表数据
     * @param uid
     * @return
     */
    List<OrderItemVO> getOrderItemsVOByUid(Integer uid);
}
