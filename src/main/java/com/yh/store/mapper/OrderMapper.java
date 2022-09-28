package com.yh.store.mapper;

import com.yh.store.pojo.Order;
import com.yh.store.pojo.OrderItem;
import com.yh.store.vo.OrderItemVO;

import java.util.List;

//订单的持久层接口
public interface OrderMapper {

    /**
     * 插入订单数据
     * @param order 订单数据
     * @return 受影响的行数
     */
    Integer insertOrder(Order order);

    /**
     * 插入订单项的数据
     * @param orderItem 订单项的数据
     * @return 受影响的行数
     */
    Integer insertOrderItem(OrderItem orderItem);

    /**
     * 查询订单数据
     * @param oid
     * @return 查询到的订单数据
     */
    Order queryOrderByOid(Integer oid);

    /**
     * 根据oid修改order
     * @param order
     * @return
     */
    Integer updateOrderByOid(Order order);

    /**
     * 根据oid查询OrderItem
     * @param oid
     * @return
     */
    List<OrderItem> queryOrderItemByOid(Integer oid);

    /**
     * 根据uid查询订单列表
     * @param uid
     * @return
     */
    List<OrderItemVO> queryOrderItemVOByUid(Integer uid);
}
