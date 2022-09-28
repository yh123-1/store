package com.yh.store.controller;


import com.yh.store.pojo.Order;
import com.yh.store.service.ICartService;
import com.yh.store.service.IOrderService;
import com.yh.store.utils.GetFromSession;
import com.yh.store.utils.JsonResult;
import com.yh.store.vo.OrderItemVO;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;
    @Autowired
    private ICartService cartService;

    @RequestMapping("/create_order")
    public JsonResult<Order> createOrder(Integer aid, Integer[] cids,Long totalPrice, HttpSession session){

        Integer uid = GetFromSession.getUidFromSession(session);
        String username = GetFromSession.getUsernameFromSession(session);

        Order order = orderService.createOrder(aid, uid, totalPrice, username, cids);
        cartService.delCartByCid(cids);
        return new JsonResult<>(JsonResult.OK,order);
    }

    @RequestMapping("/query_order")
    public JsonResult<Order> queryOrder(Integer oid){
        Order order = orderService.queryOrder(oid);
        System.out.println(order);
        return new JsonResult<>(JsonResult.OK,order);
    }

    @RequestMapping("/update_status")
    public JsonResult<Void> updateStatus(Integer oid,Integer status){
        orderService.updateOrderStatus(oid,status);
        return JsonResult.success("付款成功");
    }

    @RequestMapping("/get_orders")
    public JsonResult<List<OrderItemVO>> getOrders(HttpSession session){
        Integer uid = GetFromSession.getUidFromSession(session);
        List<OrderItemVO> orderItemVOS = orderService.getOrderItemsVOByUid(uid);
        return new JsonResult<>(JsonResult.OK,orderItemVOS);
    }
}
