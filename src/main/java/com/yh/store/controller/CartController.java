package com.yh.store.controller;

import com.yh.store.exception.DeleteException;
import com.yh.store.service.ICartService;
import com.yh.store.utils.GetFromSession;
import com.yh.store.utils.JsonResult;
import com.yh.store.vo.CartVO;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {
    
    @Autowired
    private ICartService cartService;

    @RequestMapping("/add_to_cart")
    public JsonResult<Void> addCart(Integer pid, Integer amount, HttpSession session){

        Integer uid = GetFromSession.getUidFromSession(session);
        String username = GetFromSession.getUsernameFromSession(session);

        cartService.addToCart(uid,pid,amount,username);

        return JsonResult.success();
    }


    @RequestMapping({"/",""})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session){
        Integer uid = GetFromSession.getUidFromSession(session);
        List<CartVO> cartVOList = cartService.getVOByUid(uid);
        return new JsonResult<>(JsonResult.OK,cartVOList);
    }

    @RequestMapping("/{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session){
        String username = GetFromSession.getUsernameFromSession(session);
        Integer uid = GetFromSession.getUidFromSession(session);
        Integer num = cartService.addNum(cid, uid, username);
        return new JsonResult<>(JsonResult.OK,num);
    }

    @RequestMapping("/{cid}/num/reduce")
    public JsonResult<Integer> reduceNum(@PathVariable("cid") Integer cid, HttpSession session){
        String username = GetFromSession.getUsernameFromSession(session);
        Integer uid = GetFromSession.getUidFromSession(session);
        Integer num = cartService.reduceNum(cid, uid, username);
        return new JsonResult<>(JsonResult.OK,num);
    }

    @RequestMapping("/list")
    public JsonResult<List<CartVO>> getVOByCid(Integer[] cids,HttpSession session){
        Integer uid = GetFromSession.getUidFromSession(session);
        List<CartVO> cartVOList = cartService.getVOByCid(uid, cids);
        return new JsonResult<>(JsonResult.OK,cartVOList);
    }

    @RequestMapping("/delCarts")
    public JsonResult<Void> delCarts(Integer[] cids){
        cartService.delCartByCid(cids);
        return JsonResult.success("删除购物车数据成功");
    }
}
