package com.yh.store.service.impl;

import com.yh.store.exception.InsertException;
import com.yh.store.exception.OrderNotExistException;
import com.yh.store.exception.UpdateException;
import com.yh.store.mapper.OrderMapper;
import com.yh.store.pojo.Address;
import com.yh.store.pojo.Order;
import com.yh.store.pojo.OrderItem;
import com.yh.store.service.IAddressService;
import com.yh.store.service.ICartService;
import com.yh.store.service.IOrderService;
import com.yh.store.vo.CartVO;
import com.yh.store.vo.OrderItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;

    @Override
    public Order createOrder(Integer aid, Integer uid, Long totalPrice, String username,Integer[] cids) {

        Address address = addressService.queryOneAddressByAid(aid, uid);
        Order order = new Order();
        //补齐信息
        order.setUid(uid);
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        order.setTotalPrice(totalPrice);
        order.setStatus(0);//表示未支付
        Date date = new Date();
        order.setOrderTime(date);
        order.setPayTime(null);
        order.setCreatedUser(username);
        order.setCreatedTime(date);
        order.setModifiedUser(username);
        order.setModifiedTime(date);

        //调用持久层插入
        Integer rows = orderMapper.insertOrder(order);
        if (rows != 1){
            throw new InsertException("创建订单失败");
        }

        List<CartVO> cartVOList = cartService.getVOByCid(uid, cids);
        for (CartVO cartVO: cartVOList) {
            //创建订单项数据对象
            OrderItem orderItem = new OrderItem();
            orderItem.setOid(order.getOid());
            orderItem.setPid(cartVO.getPid());
            orderItem.setTitle(cartVO.getTitle());
            orderItem.setImage(cartVO.getImage());
            orderItem.setPrice(cartVO.getRealPrice());
            orderItem.setNum(cartVO.getNum());
            //日志字段
            orderItem.setCreatedUser(username);
            orderItem.setCreatedTime(date);
            orderItem.setModifiedUser(username);
            orderItem.setModifiedTime(date);
            //执行插入操作
            Integer row = orderMapper.insertOrderItem(orderItem);
            if (row != 1){
                throw new InsertException("创建订单项数据发生异常");
            }
        }

        return order;
    }

    @Override
    public Order queryOrder(Integer oid) {
        Order order = orderMapper.queryOrderByOid(oid);
        if (order == null){
            throw new OrderNotExistException("订单不存在");
        }
        return order;
    }

    @Override
    public void updateOrderStatus(Integer oid, Integer status) {
        Order order = new Order();
        order.setOid(oid);
        order.setStatus(status);
        order.setPayTime(new Date());
        Integer row = orderMapper.updateOrderByOid(order);

        if (row != 1){
            throw new UpdateException("修改订单状态发生异常");
        }
    }

    @Override
    public List<OrderItemVO> getOrderItemsVOByUid(Integer uid) {
        List<OrderItemVO> orderItemVOS = orderMapper.queryOrderItemVOByUid(uid);

        return orderItemVOS;
    }
}
